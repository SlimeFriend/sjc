package com.sjc.app.mt.service;

import java.util.List;

public interface OrderRequestService {
    List<OrderRequestVO> getAllOrderRequests(); // 전체 발주 요청 목록 조회
    OrderRequestVO getOrderRequestById(String orderId); // 특정 발주 요청 조회
    void registerOrderRequest(OrderRequestVO orderRequest); // 발주 요청 등록
    void updateOrderRequest(OrderRequestVO orderRequest); // 발주 요청 수정
    void deleteOrderRequest(String orderId); // 발주 요청 삭제
}
