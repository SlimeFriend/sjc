package com.sjc.app.mt.serviceImpl;

import java.util.List;
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

    /**
     * 업체 코드에 따른 모든 발주 요청 조회
     */
    @Override
    public List<MtlOdVO> getAllOrderRequestsByCpCode(String cpCode) {
        return orderRequestMapper.getAllOrderRequestsByCpCode(cpCode);
    }

    /**
     * 업체 코드에 따른 발주 요청 목록을 그룹화하여 조회
     */
    @Override
    public List<MtlOdVO> getGroupedOrderRequestsByCpCode(String cpCode) {
        return orderRequestMapper.getGroupedOrderRequestsByCpCode(cpCode);
    }

    /**
     * 발주 요청 코드로 발주 요청 조회
     */
    @Override
    public MtlOdVO getOrderRequestById(String orderRequestCode) {
        return orderRequestMapper.getOrderRequestById(orderRequestCode);
    }

    /**
     * 새로운 발주 요청을 삽입
     */
    @Override
    @Transactional
    public void insertOrderRequest(MtlOdVO order) {
        orderRequestMapper.insertOrderRequest(order);
    }

    /**
     * 발주 요청 상세 정보 삽입
     */
    @Override
    @Transactional
    public void insertOrderRequestDetails(List<MtVO> details, String mtlOdCode) {
        details.forEach(detail -> orderRequestMapper.insertOrderRequestDetails(detail));
    }

    /**
     * 발주 요청 업데이트
     */
    @Override
    public void updateOrderRequest(MtlOdVO order) {
        orderRequestMapper.updateOrderRequest(order);
    }

    /**
     * 발주 요청 삭제 (상세 정보 포함)
     */
    @Transactional
    public void deleteOrderRequest(String mtlOdCode) {
        orderRequestMapper.deleteOrderRequestDetails(mtlOdCode); // 상세 목록 삭제
        orderRequestMapper.deleteOrderRequest(mtlOdCode); // 발주 요청 삭제
    }

    /**
     * 발주 요청 코드로 발주 요청 상세 정보 조회
     */
    @Override
    public List<MtVO> getOrderRequestDetailsByOrderRequestCode(String orderRequestCode) {
        return orderRequestMapper.getOrderRequestDetailsByOrderRequestCode(orderRequestCode);
    }

    /**
     * 모든 업체 정보 조회
     */
    @Override
    public List<CpVO> getAllCpInfo() {
        return orderRequestMapper.getAllCpInfo();
    }

    /**
     * 업체 코드에 따른 발주 가능한 품목 목록 조회
     */
    @Override
    public List<MtVO> getItemsByCpCode(String cpCode) {
        return orderRequestMapper.getItemsByCpCode(cpCode);
    }

    /**
     * 업체 코드에 따른 발주 요청 상세 목록 조회
     */
    @Override
    public List<MtVO> getOrderRequestDetailsByCpCode(String cpCode) {
        return orderRequestMapper.getOrderRequestDetailsByCpCode(cpCode);
    }

    /**
     * 발주 요청 상태 변경 시 상세 자재 상태도 함께 업데이트
     */
    @Override
    @Transactional
    public void updateDetailStatuses(String orderRequestCode, String status) {
        orderRequestMapper.updateDetailStatuses(orderRequestCode, status);
    }
}
