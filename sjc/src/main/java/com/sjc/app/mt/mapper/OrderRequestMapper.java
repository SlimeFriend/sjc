package com.sjc.app.mt.mapper;

import java.util.List;

import com.sjc.app.mt.service.OrderRequestVO;

public interface OrderRequestMapper {
    // 발주 요청 목록 가져오기
    List<OrderRequestVO> selectAllOrderRequests();

    // 발주 요청 단건 조회
    OrderRequestVO selectOrderRequestByCode(String orderRequestCode);

    // 발주 요청 생성
    void insertOrderRequest(OrderRequestVO orderRequestVO);

    // 발주 요청 수정
    void updateOrderRequest(OrderRequestVO orderRequestVO);

    // 발주 요청 삭제
    void deleteOrderRequest(String orderRequestCode);
}