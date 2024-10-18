package com.sjc.app.mt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sjc.app.mt.service.OrderRequestVO;

@Mapper
public interface OrderRequestMapper {
    
    // 전체 발주 요청 목록 조회
    List<OrderRequestVO> getAllOrderRequests();

    // 특정 발주 요청 조회
    OrderRequestVO getOrderRequestById(String orderId);

    // 발주 요청 등록
    void insertOrderRequest(OrderRequestVO orderRequest);

    // 발주 요청 수정 (동적 SQL에 맞게 처리)
    void updateOrderRequest(OrderRequestVO orderRequest);

    // 발주 요청 삭제
    void deleteOrderRequest(String orderId);

    // 자재 상태 업데이트 (상태와 발주 요청 코드로 업데이트)
    void updateOrderRequestStatus(@Param("orderRequestCode") String orderRequestCode, @Param("status") String status);
}
