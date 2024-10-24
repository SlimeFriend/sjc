package com.sjc.app.mt.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.sjc.app.mt.service.MtlOdVO;
import com.sjc.app.mt.service.MtVO;
import com.sjc.app.sales.service.CpVO;

@Mapper
public interface OrderRequestMapper {

    // 업체 코드로 모든 발주 요청 조회
    List<MtlOdVO> getAllOrderRequestsByCpCode(String cpCode);

    // 발주 요청 ID로 특정 발주 조회
    MtlOdVO getOrderRequestById(String orderRequestCode);

    // 발주 요청 등록
    void insertOrderRequest(MtlOdVO order);

    // 발주 요청 수정
    void updateOrderRequest(MtlOdVO order);

    // 발주 요청 삭제
    void deleteOrderRequest(String orderRequestCode);

    // 업체 코드로 발주 요청 상태 업데이트
    void updateOrderRequestStatusByCpCode(String cpCode);

    // 업체 코드로 발주 요청 상세 내역 조회
    List<MtVO> getOrderRequestDetailsByCpCode(String cpCode);

    // 발주 상태 업데이트
    void updateOrderRequestStatusForCpCode(String cpCode, String status);

    // CP_CODE로 업체 정보 조회
    CpVO getCpInfoByCpCode(String cpCode);

    // 발주 요청 코드로 발주 상세 내역 조회
    List<MtVO> getOrderRequestDetailsByOrderRequestCode(String orderRequestCode);

    // 발주 요청 저장 처리
    void saveOrderRequest(Map<String, String> orderData);  // 발주 요청 저장에 필요한 메서드

    // 모든 업체 정보 조회
    List<CpVO> getAllCpInfo();  // 모든 업체 정보 조회에 필요한 메서드 추가
}
