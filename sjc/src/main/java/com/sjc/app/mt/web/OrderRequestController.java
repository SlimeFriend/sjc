package com.sjc.app.mt.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

    // 발주 요청 목록 및 상세 정보를 한 페이지에서 처리
    @GetMapping("/orderRequestList")
    public String getOrderRequestList(@RequestParam(value = "cpCode", required = false) String cpCode, Model model) {
        List<MtlOdVO> orderRequests = orderRequestService.getGroupedOrderRequestsByCpCode(cpCode);
        model.addAttribute("orderRequests", orderRequests);

        List<CpVO> cpInfoList = orderRequestService.getAllCpInfo();
        model.addAttribute("cpInfoList", cpInfoList);

        model.addAttribute("orderDetails", List.of());
        return "mt/orderRequestList";
    }

    // 발주 요청 상세 정보 가져오기 (AJAX)
    @GetMapping("/orderRequestDetails")
    @ResponseBody
    public List<MtVO> showOrderRequestDetails(@RequestParam String cpCode) {
        return orderRequestService.getOrderRequestDetailsByOrderRequestCode(cpCode);
    }

    // 발주 요청 등록 페이지
    @GetMapping("/orderRequestNew")
    public String createOrderRequest(Model model) {
        List<CpVO> cpInfoList = orderRequestService.getAllCpInfo();
        model.addAttribute("cpInfoList", cpInfoList);
        model.addAttribute("orderRequest", new MtlOdVO());
        return "mt/orderRequestForm";
    }

    // 발주 요청 등록 처리
    @PostMapping("/orderRequestForm")
    public String insertOrderRequest(@ModelAttribute MtlOdVO orderRequest, 
                                     @RequestParam("cpCode") String cpCode,
                                     @RequestParam Map<String, String> allParams) {
        // 품목 목록에서 발주 수량 데이터를 추출하여 처리
        List<MtVO> orderItems = extractOrderItemsFromParams(allParams);

        // 발주 요청과 발주 품목을 함께 삽입
        orderRequestService.insertOrderRequestWithDetails(orderRequest, orderItems);

        return "redirect:/orderRequestList?cpCode=" + cpCode;
    }

    // 발주 요청 삭제 처리
    @PostMapping("/orderRequest/delete")
    public String deleteOrderRequest(@RequestParam String cpCode) {
        orderRequestService.deleteOrderRequest(cpCode);
        return "redirect:/orderRequestList"; // 성공적으로 삭제한 후 목록으로 리다이렉트
    }
    // 발주 요청 상태 업데이트 처리
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
            logger.info("Fetching order request by ID: {}", orderRequestCode);
            MtlOdVO orderRequest = orderRequestService.getOrderRequestById(orderRequestCode);

            if (orderRequest != null) {
                logger.info("Order request found: {}", orderRequest);
                orderRequest.setMtlOdStatus(status);
                orderRequestService.updateOrderRequest(orderRequest);
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

    // 업체 코드에 따른 발주 가능한 품목 목록 조회 (AJAX)
    @GetMapping("/getItemsForCpCode")
    @ResponseBody
    public List<MtVO> getItemsByCpCode(@RequestParam("cpCode") String cpCode) {
        return orderRequestService.getItemsByCpCode(cpCode);
    }

    // 발주 요청 품목 정보 추출 메서드 (폼에서 전달된 데이터 처리)
    private List<MtVO> extractOrderItemsFromParams(Map<String, String> allParams) {
        List<MtVO> orderItems = new ArrayList<>();
        
        allParams.forEach((key, value) -> {
            if (key.startsWith("items[")) {
                // 품목 코드 추출
                String mtCode = key.substring(key.indexOf("[") + 1, key.indexOf("]"));
                String quantityStr = value;

                // 단가, 규격 등의 정보도 함께 처리
                String unitPriceKey = "unitPrice[" + mtCode + "]";
                String specificationKey = "specification[" + mtCode + "]";
                
                if (allParams.containsKey(unitPriceKey) && allParams.containsKey(specificationKey)) {
                    String unitPriceStr = allParams.get(unitPriceKey);
                    String specification = allParams.get(specificationKey);

                    MtVO item = new MtVO();
                    item.setMtCode(mtCode);
                    item.setQuantity(Integer.parseInt(quantityStr));
                    item.setUnitPrice(Integer.parseInt(unitPriceStr));
                    item.setSpecification(specification);

                    orderItems.add(item);
                }
            }
        });

        return orderItems;
    }
}
