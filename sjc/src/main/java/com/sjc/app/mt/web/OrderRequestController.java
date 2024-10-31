package com.sjc.app.mt.web;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<MtVO> showOrderRequestDetails(@RequestParam String mtlOdCode) {
        return orderRequestService.getOrderRequestDetailsByOrderRequestCode(mtlOdCode);
    }

    /**
     * 발주 요청 등록 페이지로 이동
     */
    @GetMapping("/orderRequestNew")
    public String createOrderRequest(Model model) {
        List<CpVO> cpInfoList = orderRequestService.getAllCpInfo();
        model.addAttribute("cpInfoList", cpInfoList);
        model.addAttribute("orderRequest", new MtlOdVO());
        return "mt/orderRequestForm";
    }

    /**
     * 발주 요청 등록 처리
     */
    @PostMapping("/orderRequest/submit")
    public String submitOrderRequest(@RequestBody Map<String, Object> payload) {
        String cpCode = (String) payload.get("cpCode");
        String userId = (String) payload.get("userId");
        List<Map<String, Object>> itemsData = (List<Map<String, Object>>) payload.get("items");

        if (cpCode == null || userId == null || itemsData == null) {
            throw new IllegalArgumentException("필수 파라미터가 누락되었습니다.");
        }

        
        MtlOdVO orderRequest = new MtlOdVO();
        orderRequest.setCpCode(cpCode);
        orderRequest.setUserId(Integer.parseInt(userId));
        orderRequestService.insertOrderRequest(orderRequest);

       
        String mtlOdCode = orderRequest.getMtlOdCode();

       
        List<MtVO> items = itemsData.stream().map(itemData -> {
            MtVO item = new MtVO();
            item.setMtCode((String) itemData.get("mtCode"));
            item.setQuantity((Integer) itemData.get("quantity"));
            item.setMtlOdCode(mtlOdCode);
            return item;
        }).collect(Collectors.toList());

        orderRequestService.insertOrderRequestDetails(items, mtlOdCode);

        return "redirect:/orderRequestList";
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
