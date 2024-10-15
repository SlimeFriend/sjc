package com.sjc.app.mt.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sjc.app.mt.service.OrderRequestService;
import com.sjc.app.mt.service.OrderRequestVO;

@RestController
@RequestMapping("/order-request")
public class OrderRequestController {

    private final OrderRequestService orderRequestService;

    @Autowired
    public OrderRequestController(OrderRequestService orderRequestService) {
        this.orderRequestService = orderRequestService;
    }

    // 발주 요청 목록 조회
    @GetMapping("/list")
    public List<OrderRequestVO> getAllOrderRequests() {
        return orderRequestService.getAllOrderRequests();
    }

    // 특정 발주 요청 단건 조회
    @GetMapping("/{orderRequestCode}")
    public OrderRequestVO getOrderRequestByCode(@PathVariable String orderRequestCode) {
        return orderRequestService.getOrderRequestByCode(orderRequestCode);
    }

    // 새로운 발주 요청 생성
    @PostMapping("/create")
    public void insertOrderRequest(@RequestBody OrderRequestVO orderRequestVO) {
        orderRequestService.insertOrderRequest(orderRequestVO);
    }

    // 발주 요청 수정
    @PutMapping("/update")
    public void updateOrderRequest(@RequestBody OrderRequestVO orderRequestVO) {
        orderRequestService.updateOrderRequest(orderRequestVO);
    }

    // 발주 요청 삭제
    @DeleteMapping("/delete/{orderRequestCode}")
    public void deleteOrderRequest(@PathVariable String orderRequestCode) {
        orderRequestService.deleteOrderRequest(orderRequestCode);
    }
}
