package com.sjc.app.mt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sjc.app.mt.service.OrderRequestVO;

@Mapper
public interface OrderRequestMapper {
    List<OrderRequestVO> getAllOrderRequests(); // 전체 발주 요청 목록 조회
    OrderRequestVO getOrderRequestById(String orderId); // 특정 발주 요청 조회
    void insertOrderRequest(OrderRequestVO orderRequest); // 발주 요청 등록
    void updateOrderRequest(OrderRequestVO orderRequest); // 발주 요청 수정
    void deleteOrderRequest(String orderId); // 발주 요청 삭제
}
