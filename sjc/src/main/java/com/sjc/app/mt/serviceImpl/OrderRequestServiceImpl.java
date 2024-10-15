package com.sjc.app.mt.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjc.app.mt.mapper.OrderRequestMapper;
import com.sjc.app.mt.service.OrderRequestService;
import com.sjc.app.mt.service.OrderRequestVO;

@Service
public class OrderRequestServiceImpl implements OrderRequestService {

    private final OrderRequestMapper orderRequestMapper;

    @Autowired
    public OrderRequestServiceImpl(OrderRequestMapper orderRequestMapper) {
        this.orderRequestMapper = orderRequestMapper;
    }

    // 발주 요청 목록을 조회하여 반환
    @Override
    public List<OrderRequestVO> getAllOrderRequests() {
        return orderRequestMapper.selectAllOrderRequests();
    }

    // 특정 발주 요청 코드를 통해 단건 발주 요청을 조회하여 반환
    @Override
    public OrderRequestVO getOrderRequestByCode(String orderRequestCode) {
        return orderRequestMapper.selectOrderRequestByCode(orderRequestCode);
    }

    // 새로운 발주 요청을 생성
    @Override
    public void insertOrderRequest(OrderRequestVO orderRequestVO) {
        orderRequestMapper.insertOrderRequest(orderRequestVO);
    }

    // 기존 발주 요청을 수정
    @Override
    public void updateOrderRequest(OrderRequestVO orderRequestVO) {
        orderRequestMapper.updateOrderRequest(orderRequestVO);
    }

    // 발주 요청 코드를 통해 발주 요청을 삭제
    @Override
    public void deleteOrderRequest(String orderRequestCode) {
        orderRequestMapper.deleteOrderRequest(orderRequestCode);
    }
}
