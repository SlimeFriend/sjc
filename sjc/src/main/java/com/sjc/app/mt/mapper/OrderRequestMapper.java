package com.sjc.app.mt.mapper;

import java.util.List;
import com.sjc.app.mt.service.MtlOdVO;
import com.sjc.app.mt.service.MtVO;
import com.sjc.app.sales.service.CpVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderRequestMapper {

    // 업체 코드에 따른 모든 발주 요청 목록 조회
    List<MtlOdVO> getAllOrderRequestsByCpCode(String cpCode);

    // 발주 요청 목록을 업체 코드와 날짜 기준으로 그룹화
    List<MtlOdVO> getGroupedOrderRequestsByCpCode(String cpCode);

    // 발주 요청 코드로 발주 요청 조회
    MtlOdVO getOrderRequestById(String orderRequestCode);

    // 발주 요청 등록
    void insertOrderRequest(MtlOdVO order);

    // 발주 요청 수정
    void updateOrderRequest(MtlOdVO order);

    // 발주 요청 삭제
    void deleteOrderRequest(String orderRequestCode);

    // 발주 요청 코드에 따른 발주 요청 상세 목록 조회
    List<MtVO> getOrderRequestDetailsByOrderRequestCode(String orderRequestCode);

    // 모든 업체 정보 조회
    List<CpVO> getAllCpInfo();

    // 업체 코드에 따른 발주 가능한 품목 목록 조회
    List<MtVO> getItemsByCpCode(String cpCode);

    // 업체 코드에 따른 발주 상세 목록 조회 추가
    List<MtVO> getOrderRequestDetailsByCpCode(String cpCode); // 추가된 메서드
}
