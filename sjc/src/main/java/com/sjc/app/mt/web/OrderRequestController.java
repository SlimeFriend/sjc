package com.sjc.app.mt.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.sjc.app.mt.service.OrderRequestService;
import com.sjc.app.mt.service.OrderRequestVO;

@Controller
public class OrderRequestController {

    @Autowired
    private OrderRequestService orderRequestService;

    @GetMapping("/orderRequest")
    public String getAllOrderRequests(Model model) {
        List<OrderRequestVO> orderRequests = orderRequestService.getAllOrderRequests();
        model.addAttribute("orderRequests", orderRequests); // 모델에 발주 요청 목록 추가
        return "mt/orderRequestList"; 
    }

    @GetMapping("/orderRequestNew")
    public String newOrderRequestForm(Model model) {
        model.addAttribute("orderRequest", new OrderRequestVO()); // 새로운 OrderRequestVO 객체 생성
        return "mt/orderRequestForm"; // 발주 요청 등록 폼의 템플릿 이름
    }

    @GetMapping("/orderRequestDetail/{orderRequestCode}")
    public String getOrderRequest(@PathVariable String orderRequestCode, Model model) {
        OrderRequestVO orderRequest = orderRequestService.getOrderRequestById(orderRequestCode);
        model.addAttribute("orderRequest", orderRequest); // 모델에 발주 요청 추가
        return "mt/orderRequestDetail"; // 세부 페이지 템플릿 이름 반환
    }

    @PostMapping("/orderRequest")
    public String registerOrderRequest(@ModelAttribute OrderRequestVO orderRequest) {
        orderRequestService.registerOrderRequest(orderRequest); // 발주 요청 등록
        return "redirect:/orderRequest"; // 등록 후 목록으로 리다이렉트
    }

    @PutMapping("/orderRequest")
    public String updateOrderRequest(@ModelAttribute OrderRequestVO orderRequest) {
        orderRequestService.updateOrderRequest(orderRequest); // 발주 요청 수정
        return "redirect:/orderRequest"; // 수정 후 목록으로 리다이렉트
    }

    @DeleteMapping("/orderRequest/{orderRequestCode}")
    public String deleteOrderRequest(@PathVariable String orderRequestCode) {
        orderRequestService.deleteOrderRequest(orderRequestCode); // 발주 요청 삭제
        return "redirect:/orderRequest"; // 삭제 후 목록으로 리다이렉트
    }
}
