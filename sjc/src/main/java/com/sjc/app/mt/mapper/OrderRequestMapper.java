package com.sjc.app.mt.mapper;

import java.util.List;
import java.util.Map;  // Map을 위한 임포트 추가
import com.sjc.app.mt.service.MtlOdVO;
import com.sjc.app.mt.service.MtVO;
import com.sjc.app.sales.service.CpVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderRequestMapper {

    List<MtlOdVO> getAllOrderRequestsByCpCode(String cpCode);

    List<MtlOdVO> getGroupedOrderRequestsByCpCode(String cpCode);

    MtlOdVO getOrderRequestById(String orderRequestCode);

    void insertOrderRequest(MtlOdVO order);

    // Map 타입으로 변경하여 다수의 자재 상세 항목 삽입 지원
    void insertOrderRequestDetails(MtVO mtVo);

    void updateOrderRequest(MtlOdVO order);

    void deleteOrderRequest(String orderRequestCode);
    
    void deleteOrderRequestDetails(String mtlOdCode); // 발주 요청 상세 삭제

    List<MtVO> getOrderRequestDetailsByOrderRequestCode(String orderRequestCode);

    List<CpVO> getAllCpInfo();

    List<MtVO> getItemsByCpCode(String cpCode);

    List<MtVO> getOrderRequestDetailsByCpCode(String cpCode);
}
