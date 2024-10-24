package com.sjc.app.mt.service;

import java.util.List;
import com.sjc.app.sales.service.CpVO;

public interface OrderRequestService {

    List<MtlOdVO> getAllOrderRequestsByCpCode(String cpCode);

    List<MtlOdVO> getGroupedOrderRequestsByCpCode(String cpCode);

    MtlOdVO getOrderRequestById(String orderRequestCode);

    void insertOrderRequest(MtlOdVO order);

    void updateOrderRequest(MtlOdVO order);

    void deleteOrderRequest(String orderRequestCode);

    List<MtVO> getOrderRequestDetailsByOrderRequestCode(String orderRequestCode);

    // CP 코드에 따른 발주 상세내역 가져오기 추가
    List<MtVO> getOrderRequestDetailsByCpCode(String cpCode);

    List<CpVO> getAllCpInfo();

    List<MtVO> getItemsByCpCode(String cpCode);
}
