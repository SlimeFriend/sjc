package com.sjc.app.mt.service;

import java.util.List;

public interface OrderRequestService {
    // 발주 요청 목록 가져오기
    List<OrderRequestVO> getAllOrderRequests();

    // 발주 요청 단건 조회
    OrderRequestVO getOrderRequestByCode(String orderRequestCode);

    // 발주 요청 생성
    void insertOrderRequest(OrderRequestVO orderRequestVO);

    // 발주 요청 수정
    void updateOrderRequest(OrderRequestVO orderRequestVO);

    // 발주 요청 삭제
    void deleteOrderRequest(String orderRequestCode);
}