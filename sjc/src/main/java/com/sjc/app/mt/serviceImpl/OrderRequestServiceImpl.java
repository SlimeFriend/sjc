package com.sjc.app.mt.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjc.app.mt.mapper.OrderRequestMapper;
import com.sjc.app.mt.service.OrderRequestService;
import com.sjc.app.mt.service.OrderRequestVO;

@Service

public class OrderRequestServiceImpl implements OrderRequestService {

    @Autowired
    private OrderRequestMapper orderRequestMapper;

    @Override
    public List<OrderRequestVO> getAllOrderRequests() {
        return orderRequestMapper.getAllOrderRequests(); // 전체 발주 요청 목록 조회
    }

    @Override
    public OrderRequestVO getOrderRequestById(String orderId) {
        return orderRequestMapper.getOrderRequestById(orderId); // 특정 발주 요청 조회
    }

    @Override
    public void registerOrderRequest(OrderRequestVO orderRequest) {
        orderRequestMapper.insertOrderRequest(orderRequest); // 발주 요청 등록
    }

    @Override
    public void updateOrderRequest(OrderRequestVO orderRequest) {
        orderRequestMapper.updateOrderRequest(orderRequest); // 발주 요청 수정
    }

    @Override
    public void deleteOrderRequest(String orderId) {
        orderRequestMapper.deleteOrderRequest(orderId); // 발주 요청 삭제
    }
}
