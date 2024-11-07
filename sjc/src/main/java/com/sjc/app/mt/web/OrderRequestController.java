package com.sjc.app.mt.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sjc.app.mt.service.MtVO;
import com.sjc.app.mt.service.MtlOdVO;
import com.sjc.app.mt.service.OrderRequestService;
import com.sjc.app.sales.service.CpVO;

@Controller
public class OrderRequestController {

    private static final Logger logger = LoggerFactory.getLogger(OrderRequestController.class);

    @Autowired
    private OrderRequestService orderRequestService;

    /**
     * 발주 요청 목록 및 상세 정보를 조회하여 페이지로 반환
     */
    @GetMapping("/orderRequestList")
    public String getOrderRequestList(@RequestParam(value = "cpCode", required = false) String cpCode, Model model) {
        List<MtlOdVO> orderRequests;
        if (cpCode != null && !cpCode.isEmpty()) {
            
            orderRequests = orderRequestService.getGroupedOrderRequestsByCpCode(cpCode);
        } else {
          
            orderRequests = orderRequestService.getGroupedOrderRequestsByCpCode(null);
        }
        
        model.addAttribute("orderRequests", orderRequests);
        model.addAttribute("cpInfoList", orderRequestService.getAllCpInfo());
        model.addAttribute("orderDetails", List.of());
        return "mt/orderRequestList";
    }

    /**
     * 특정 발주 요청의 상세 정보를 가져옴 (AJAX 요청)
     */
    @GetMapping("/orderRequestDetails")
    @ResponseBody
    public List<MtlOdVO> showOrderRequestDetails(@RequestParam String mtlOdCode) {
        List<MtlOdVO> details = orderRequestService.getOrderRequestDetailsByOrderRequestCode(mtlOdCode);
        details.forEach(detail -> System.out.println("상세 상태: " + detail.getMtlOdStatus())); // 상태 정보 확인을 위한 로그
        return details;
    }


    /**
     * 발주 요청 목록 페이지로 이동
     */
    @GetMapping("/orderRequestNew")
    public String createOrderRequest(
            @RequestParam(value = "mtCode", required = false) String mtCode,
            @RequestParam(value = "cpCode", required = false) String cpCode,
            Model model) {

        // 전체 업체 목록을 조회하여 모델에 추가
    	List<CpVO> cpInfoList = orderRequestService.getAllCpInfo();
    	
      
        model.addAttribute("cpInfoList", cpInfoList);
       

        // 선택된 자재 코드와 업체 코드가 있을 때만 모델에 추가
        if (cpCode != null) {
            model.addAttribute("selectedCpCode", cpCode);
        }
        if (mtCode != null) {
            model.addAttribute("selectedMtCode", mtCode);
        }

        return "mt/orderRequestForm";
    }

    /**
     * 발주 요청 등록 처리
     */
   
    @PostMapping("/orderRequest/submit")
    @ResponseBody // JSON 형식으로 응답 반환
    public ResponseEntity<String> submitOrderRequest(@RequestBody Map<String, Object> payload) {
        String cpCode = (String) payload.get("cpCode");
        Integer userId =Integer.parseInt((String) payload.get("userId")) ; // '이성철' 그대로 받음
        List<Map<String, Object>> itemsData = (List<Map<String, Object>>) payload.get("items");

        // 필수 파라미터 검증
        if (cpCode == null || userId == null || itemsData == null) {
            throw new IllegalArgumentException("필수 파라미터가 누락되었습니다.");
        }

        // 발주 요청 생성
        MtlOdVO orderRequest = new MtlOdVO();
        orderRequest.setCpCode(cpCode);
        orderRequest.setUserId(userId); 
        orderRequestService.insertOrderRequest(orderRequest);

     

        // 발주 코드 생성 후 가져오기
        String mtlOdCode = orderRequest.getMtlOdCode();

        // 발주 상세 목록 생성
        List<MtVO> items = itemsData.stream().map(itemData -> {
            MtVO item = new MtVO();
            item.setMtCode((String) itemData.get("mtCode"));

            // quantity를 Integer로 변환
            Object quantityObj = itemData.get("quantity");
            if (quantityObj instanceof String) {
                item.setQuantity(Integer.parseInt((String) quantityObj));
            } else if (quantityObj instanceof Integer) {
                item.setQuantity((Integer) quantityObj);
            } else {
                throw new IllegalArgumentException("quantity가 올바르지 않은 형식입니다.");
            }

            item.setMtlOdCode(mtlOdCode);
            return item;
        }).collect(Collectors.toList());

        orderRequestService.insertOrderRequestDetails(items, mtlOdCode);

        return ResponseEntity.ok("발주 요청이 성공적으로 등록되었습니다.");
    }




    /**
     * 발주 요청 삭제 처리
     */
    @PostMapping("/orderRequest/delete")
    public String deleteOrderRequest(@RequestParam("mtlOdCode") String mtlOdCode) {
        orderRequestService.deleteOrderRequest(mtlOdCode);
        return "redirect:/orderRequestList";
    }
    /**
     * 발주 요청 상태 조회
     */
    @GetMapping("/orderRequest/status")
    @ResponseBody
    public Map<String, String> getOrderRequestStatus(@RequestParam("mtlOdCode") String mtlOdCode) {
        Map<String, String> response = new HashMap<>();
        
        // 발주 요청 상태 가져오기
        MtlOdVO orderRequest = orderRequestService.getOrderRequestById(mtlOdCode);
        if (orderRequest != null) {
            response.put("status", orderRequest.getMtlOdStatus());
        } else {
            response.put("status", "NOT_FOUND"); // 요청을 찾지 못한 경우의 응답
        }
        
        return response;
    }

    /**
     * 발주 요청 상태 업데이트
     */
    @PostMapping("/orderRequest/updateStatus")
    public String updateOrderRequestStatus(@RequestParam("orderRequestCode") String orderRequestCode,
                                           @RequestParam("status") String status,
                                           @RequestParam("cpCode") String cpCode) {
        logger.info("Updating order request status - OrderRequestCode: {}, Status: {}, CpCode: {}", orderRequestCode, status, cpCode);

        if (orderRequestCode == null || orderRequestCode.trim().isEmpty()) {
            logger.error("Order request code is null or empty.");
            return "redirect:/errorPage";
        }

        try {
            MtlOdVO orderRequest = orderRequestService.getOrderRequestById(orderRequestCode);

            if (orderRequest != null) {
                orderRequest.setMtlOdStatus(status);
                orderRequestService.updateOrderRequest(orderRequest);
                orderRequestService.updateDetailStatuses(orderRequestCode, status); 
                return "redirect:/orderRequestList?cpCode=" + cpCode;
            } else {
                logger.error("No order request found with the code: {}", orderRequestCode);
                return "redirect:/errorPage";
            }
        } catch (Exception e) {
            logger.error("Error updating order request status: ", e);
            return "redirect:/errorPage";
        }
    }

    /**
     * 발주 요청 상세 상태 업데이트
     */
    @PostMapping("/orderRequest/updateDetailStatuses")
    public String updateDetailStatuses(@RequestParam("orderRequestCode") String orderRequestCode,
                                       @RequestParam("status") String status) {
        try {
            orderRequestService.updateDetailStatuses(orderRequestCode, status);
            return "redirect:/orderRequestList";
        } catch (Exception e) {
            logger.error("Error updating detail statuses for order request: " + orderRequestCode, e);
            return "errorPage"; 
        }
    }

    /**
     * 업체 코드에 따른 발주 가능한 품목 목록 조회 (AJAX 요청)
     */
    @GetMapping("/getItemsForCpCode")
    @ResponseBody
    public List<MtVO> getItemsByCpCode(@RequestParam("cpCode") String cpCode) {
        return orderRequestService.getItemsByCpCode(cpCode);
    }
}
