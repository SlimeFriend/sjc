package com.sjc.app.mt.mapper;

import java.util.List;
import java.util.Map;  // Map을 위한 임포트 추가
import com.sjc.app.mt.service.MtlOdVO;
import com.sjc.app.mt.service.MtVO;
import com.sjc.app.sales.service.CpVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderRequestMapper {

    // 특정 업체 코드에 따른 발주 요청 목록 조회
    List<MtlOdVO> getAllOrderRequestsByCpCode(String cpCode);

    // 특정 업체 코드에 따라 그룹화된 발주 요청 목록 조회
    List<MtlOdVO> getGroupedOrderRequestsByCpCode(String cpCode);

    // 발주 요청 ID로 발주 요청 정보 조회
    MtlOdVO getOrderRequestById(String orderRequestCode);

    // 발주 요청 추가
    void insertOrderRequest(MtlOdVO order);

    // 발주 요청 상세 항목을 다수 삽입 (자재 상세 항목 삽입 지원)
    void insertOrderRequestDetails(MtVO mtVo);

    // 발주 요청 정보 업데이트
    void updateOrderRequest(MtlOdVO order);

    // 발주 요청 삭제
    void deleteOrderRequest(String orderRequestCode);

    // 발주 요청 상세 삭제 (발주 요청과 연관된 모든 자재 상세 항목 삭제)
    void deleteOrderRequestDetails(String mtlOdCode);

    // 특정 발주 요청 코드로 발주 요청 상세 정보 조회
    List<MtlOdVO> getOrderRequestDetailsByOrderRequestCode(String orderRequestCode);

    // 모든 업체 정보 조회
    List<CpVO> getAllCpInfo();

    // 특정 업체 코드에 따른 발주 가능한 품목 목록 조회
    List<MtVO> getItemsByCpCode(String cpCode);

    // 업체 코드로 발주 요청 상세 정보 조회
    List<MtVO> getOrderRequestDetailsByCpCode(String cpCode);

    // 발주 요청 상태와 자재 상세 상태 업데이트
    void updateDetailStatuses(String orderRequestCode, String status);
}
