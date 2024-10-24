package com.sjc.app.mt.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjc.app.mt.mapper.OrderRequestMapper;
import com.sjc.app.mt.service.MtlOdVO;
import com.sjc.app.mt.service.MtVO;
import com.sjc.app.mt.service.OrderRequestService;
import com.sjc.app.sales.service.CpVO;  // CpVO 클래스 import

@Service
public class OrderRequestServiceImpl implements OrderRequestService {

    @Autowired
    private OrderRequestMapper orderRequestMapper;

    @Override
    public List<MtlOdVO> getAllOrderRequestsByCpCode(String cpCode) {
        return orderRequestMapper.getAllOrderRequestsByCpCode(cpCode);
    }

    @Override
    public MtlOdVO getOrderRequestById(String orderRequestCode) {
        return orderRequestMapper.getOrderRequestById(orderRequestCode);
    }

    @Override
    public void insertOrderRequest(MtlOdVO order) {
        orderRequestMapper.insertOrderRequest(order);
    }

    @Override
    public void updateOrderRequest(MtlOdVO order) {
        orderRequestMapper.updateOrderRequest(order);
    }

    @Override
    public void deleteOrderRequest(String orderRequestCode) {
        orderRequestMapper.deleteOrderRequest(orderRequestCode);
    }

    @Override
    public void updateOrderRequestStatusByCpCode(String cpCode) {
        orderRequestMapper.updateOrderRequestStatusByCpCode(cpCode);
    }

    @Override
    public void updateOrderRequestStatusForCpCode(String cpCode, String status) {
        orderRequestMapper.updateOrderRequestStatusForCpCode(cpCode, status);
    }

    @Override
    public List<MtVO> getOrderRequestDetailsByCpCode(String cpCode) {
        return orderRequestMapper.getOrderRequestDetailsByCpCode(cpCode);
    }

    @Override
    public CpVO getCpInfoByCpCode(String cpCode) {
        return orderRequestMapper.getCpInfoByCpCode(cpCode);
    }

    @Override
    public List<MtVO> getOrderRequestDetailsByOrderRequestCode(String orderRequestCode) {
        return orderRequestMapper.getOrderRequestDetailsByOrderRequestCode(orderRequestCode);
    }

    @Override
    public void saveOrderRequest(String cpCode, Map<String, String> orderData) {
        // 발주 요청 저장 처리 로직 (필요한 경우 구현)
        // 예를 들어, 주문 데이터 파싱 후 Mapper 호출
    }

    @Override
    public List<CpVO> getAllCpInfo() {
        return orderRequestMapper.getAllCpInfo(); // 모든 업체 정보 조회
    }
}
