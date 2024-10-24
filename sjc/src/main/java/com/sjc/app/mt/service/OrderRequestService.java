package com.sjc.app.mt.service;

import java.util.List;
import java.util.Map;

import com.sjc.app.sales.service.CpVO; // CpVO 클래스 import

public interface OrderRequestService {

    /**
     * 업체별 발주 요청 목록 조회
     * @param cpCode 업체 코드
     * @return 업체별 발주 요청 목록
     */
    List<MtlOdVO> getAllOrderRequestsByCpCode(String cpCode);

    /**
     * 특정 발주 요청 조회
     * @param orderRequestCode 발주 요청 코드
     * @return 발주 요청 상세
     */
    MtlOdVO getOrderRequestById(String orderRequestCode);

    /**
     * 발주 요청 등록
     * @param order 발주 요청 정보
     */
    void insertOrderRequest(MtlOdVO order);

    /**
     * 발주 요청 수정
     * @param order 발주 요청 정보
     */
    void updateOrderRequest(MtlOdVO order);

    /**
     * 발주 요청 삭제
     * @param orderRequestCode 발주 요청 코드
     */
    void deleteOrderRequest(String orderRequestCode);

    /**
     * 업체 코드에 해당하는 발주 요청 상태 업데이트
     * @param cpCode 업체 코드
     */
    void updateOrderRequestStatusByCpCode(String cpCode);

    /**
     * 발주 상태 업데이트
     * @param cpCode 업체 코드
     * @param status 변경할 상태
     */
    void updateOrderRequestStatusForCpCode(String cpCode, String status);

    /**
     * 발주 요청 상세 목록 조회
     * @param cpCode 업체 코드
     * @return 발주 요청 상세 목록
     */
    List<MtVO> getOrderRequestDetailsByCpCode(String cpCode);

    /**
     * 업체 정보 조회
     * @param cpCode 업체 코드
     * @return 업체 정보
     */
    CpVO getCpInfoByCpCode(String cpCode); // CpVO 관련 메서드 추가

    /**
     * 발주 요청 저장 처리 (발주 상세 목록을 포함하여 저장)
     * @param cpCode 업체 코드
     * @param orderData 발주 데이터 (품목 및 수량 등)
     */
    void saveOrderRequest(String cpCode, Map<String, String> orderData); // 발주 요청 저장 메서드 추가

    /**
     * 특정 발주 요청 코드에 따른 발주 상세 목록 조회
     * @param orderRequestCode 발주 요청 코드
     * @return 발주 요청 상세 목록
     */
    List<MtVO> getOrderRequestDetailsByOrderRequestCode(String orderRequestCode); // 발주 요청 코드로 상세 조회 메서드 추가
    
    /**
     * 모든 업체 정보 조회
     * @return 업체 정보 목록
     */
    List<CpVO> getAllCpInfo(); // 업체 정보 전체 조회 메서드 추가
}
