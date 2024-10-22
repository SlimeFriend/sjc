package com.sjc.app.mt.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sjc.app.mt.service.OrderRequestService;
import com.sjc.app.mt.service.OrderRequestVO;

@Controller
public class OrderRequestController {

    @Autowired
    private OrderRequestService orderRequestService;

    // 전체 발주 요청 목록 조회
    @GetMapping("/orderRequest")
    public String getAllOrderRequests(Model model) {
        List<OrderRequestVO> orderRequests = orderRequestService.getAllOrderRequests();
        model.addAttribute("orderRequests", orderRequests); // 모델에 발주 요청 목록 추가
        return "mt/orderRequestList"; 
    }

    // 발주 요청 등록 폼 표시
    @GetMapping("/orderRequestNew")
    public String newOrderRequestForm(Model model) {
        model.addAttribute("orderRequest", new OrderRequestVO());
        return "mt/orderRequestForm"; 
    }

    // 특정 발주 요청 상세 조회
    @GetMapping("/orderRequest/{orderRequestCode}")
    public String getOrderRequest(@PathVariable String orderRequestCode, Model model) {
        OrderRequestVO orderRequest = orderRequestService.getOrderRequestById(orderRequestCode);
        
        if (orderRequest == null) {
            // 상세 정보를 찾을 수 없으면 에러 페이지로 이동
            return "error/404";
        }
        
        model.addAttribute("orderRequest", orderRequest); // 발주 요청 상세 추가
        return "mt/orderRequestDetail"; // 세부 페이지 템플릿 반환
    }

    // 발주 요청 등록 처리
    @PostMapping("/orderRequest")
    public String registerOrderRequest(@ModelAttribute OrderRequestVO orderRequest) {
        orderRequestService.registerOrderRequest(orderRequest); // 발주 요청 등록
        return "redirect:/orderRequest"; // 등록 후 목록으로 리다이렉트
    }

 // 발주 요청 수정 처리
    @PostMapping("/orderRequest/{orderRequestCode}/update")
    public String updateOrderRequest(@PathVariable String orderRequestCode, @ModelAttribute OrderRequestVO orderRequest) {
        orderRequest.setOrderRequestCode(orderRequestCode); // 요청 코드를 명시적으로 설정
        orderRequestService.updateOrderRequest(orderRequest); // 발주 요청 수정
        return "redirect:/orderRequest"; // 수정 후 목록으로 리다이렉트
    }

    // 발주 요청 삭제 처리
    @PostMapping("/orderRequest/{orderRequestCode}/delete")
    public String deleteOrderRequest(@PathVariable String orderRequestCode) {
        orderRequestService.deleteOrderRequest(orderRequestCode); // 발주 요청 삭제
        return "redirect:/orderRequest"; // 삭제 후 목록으로 리다이렉트
    }

    // 발주 요청 상태 업데이트 처리
    @PostMapping("/orderRequest/updateStatus")
    public String updateOrderRequestStatus(@RequestParam("orderRequestCode") String orderRequestCode,
                                           @RequestParam("status") String status) {
        orderRequestService.updateOrderRequestStatus(orderRequestCode, status); // 상태 업데이트
        return "redirect:/orderRequest"; // 목록으로 리다이렉트
    }
}
