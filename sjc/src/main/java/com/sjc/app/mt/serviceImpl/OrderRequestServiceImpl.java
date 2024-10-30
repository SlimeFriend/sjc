package com.sjc.app.mt.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjc.app.mt.mapper.OrderRequestMapper;
import com.sjc.app.mt.service.MtVO;
import com.sjc.app.mt.service.MtlOdVO;
import com.sjc.app.mt.service.OrderRequestService;
import com.sjc.app.sales.service.CpVO;

@Service
public class OrderRequestServiceImpl implements OrderRequestService {

    @Autowired
    private OrderRequestMapper orderRequestMapper;

    @Override
    public List<MtlOdVO> getAllOrderRequestsByCpCode(String cpCode) {
        return orderRequestMapper.getAllOrderRequestsByCpCode(cpCode);
    }

    @Override
    public List<MtlOdVO> getGroupedOrderRequestsByCpCode(String cpCode) {
        return orderRequestMapper.getGroupedOrderRequestsByCpCode(cpCode);
    }

    @Override
    public MtlOdVO getOrderRequestById(String orderRequestCode) {
        return orderRequestMapper.getOrderRequestById(orderRequestCode);
    }

    @Override
    @Transactional
    public void insertOrderRequest(MtlOdVO order) {
        orderRequestMapper.insertOrderRequest(order);
    }

    // 발주 요청 상세 정보 삽입 메서드 구현
    @Override
    @Transactional
    public void insertOrderRequestDetails(List<MtVO> details, String mtlOdCode) {
        details.forEach(detail -> {
                       
            orderRequestMapper.insertOrderRequestDetails(detail);  // 메서드 이름 그대로 사용
        });
    }

    @Override
    public void updateOrderRequest(MtlOdVO order) {
        orderRequestMapper.updateOrderRequest(order);
    }

    @Transactional
    public void deleteOrderRequest(String mtlOdCode) {
        orderRequestMapper.deleteOrderRequestDetails(mtlOdCode); // 먼저 상세 목록 삭제
        orderRequestMapper.deleteOrderRequest(mtlOdCode); // 그 후 발주 요청 삭제
    }
    @Override
    public List<MtVO> getOrderRequestDetailsByOrderRequestCode(String orderRequestCode) {
        return orderRequestMapper.getOrderRequestDetailsByOrderRequestCode(orderRequestCode);
    }

    @Override
    public List<CpVO> getAllCpInfo() {
        return orderRequestMapper.getAllCpInfo();
    }

    @Override
    public List<MtVO> getItemsByCpCode(String cpCode) {
        return orderRequestMapper.getItemsByCpCode(cpCode);
    }

    @Override
    public List<MtVO> getOrderRequestDetailsByCpCode(String cpCode) {
        return orderRequestMapper.getOrderRequestDetailsByCpCode(cpCode);
    }
}
