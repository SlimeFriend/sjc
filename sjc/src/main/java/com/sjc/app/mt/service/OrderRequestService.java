package com.sjc.app.mt.service;

import java.util.List;

public interface OrderRequestService {
    // 전체 발주 요청 목록 조회
    List<OrderRequestVO> getAllOrderRequests();

    // 특정 발주 요청 조회
    OrderRequestVO getOrderRequestById(String orderId);

    // 발주 요청 등록
    void registerOrderRequest(OrderRequestVO orderRequest);

    // 발주 요청 수정
    void updateOrderRequest(OrderRequestVO orderRequest);

    // 발주 요청 삭제
    void deleteOrderRequest(String orderId);
    
    // 자재 상태 업데이트
    void updateOrderRequestStatus(String orderRequestCode, String status);
}
