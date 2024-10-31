package com.sjc.app.mt.service;

import java.util.List;
import com.sjc.app.sales.service.CpVO;

public interface OrderRequestService {

    // 업체 코드별 전체 발주 요청 조회
    List<MtlOdVO> getAllOrderRequestsByCpCode(String cpCode);

    // 업체 코드별 발주 요청 그룹화하여 조회
    List<MtlOdVO> getGroupedOrderRequestsByCpCode(String cpCode);

    // 발주 요청 ID로 발주 요청 세부 정보 조회
    MtlOdVO getOrderRequestById(String orderRequestCode);

    // 새로운 발주 요청 삽입
    void insertOrderRequest(MtlOdVO order);

    // 발주 요청 시 자재별 상세 정보를 함께 삽입하는 메서드
    void insertOrderRequestDetails(List<MtVO> details, String mtlOdCode);

    // 발주 요청 업데이트
    void updateOrderRequest(MtlOdVO order);

    // 발주 요청 삭제
    void deleteOrderRequest(String orderRequestCode);

    // 발주 요청 코드로 발주 요청 세부 정보 조회
    List<MtVO> getOrderRequestDetailsByOrderRequestCode(String orderRequestCode);

    // 업체 코드로 발주 요청 세부 정보 조회
    List<MtVO> getOrderRequestDetailsByCpCode(String cpCode);

    // 모든 업체 정보 조회
    List<CpVO> getAllCpInfo();

    // 업체 코드에 따른 발주 가능한 품목 목록 조회
    List<MtVO> getItemsByCpCode(String cpCode);
    
    // 발주 요청 상태 변경 시 상세 자재 상태도 함께 업데이트
    void updateDetailStatuses(String orderRequestCode, String status);

}
