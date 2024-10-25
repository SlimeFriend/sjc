package com.sjc.app.mt.mapper;

import java.util.List;
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

    void insertOrderRequestDetail(MtlOdVO detail);

    void updateOrderRequest(MtlOdVO order);

    void deleteOrderRequest(String orderRequestCode);

    List<MtVO> getOrderRequestDetailsByOrderRequestCode(String orderRequestCode);

    List<CpVO> getAllCpInfo();

    List<MtVO> getItemsByCpCode(String cpCode);

    List<MtVO> getOrderRequestDetailsByCpCode(String cpCode);
}
