package com.sjc.app.mt.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sjc.app.mt.mapper.OrderRequestMapper;
import com.sjc.app.mt.service.MtlOdVO;
import com.sjc.app.mt.service.MtVO;
import com.sjc.app.mt.service.OrderRequestService;
import com.sjc.app.sales.service.CpVO;

@Service
public class OrderRequestServiceImpl implements OrderRequestService {

	@Autowired
	private OrderRequestMapper orderRequestMapper;

	@Override
	public List<MtlOdVO> getAllOrderRequestsByCpCode(String cpCode) {
		return orderRequestMapper.getAllOrderRequestsByCpCode(cpCode);
	}

	@Override
	public List<MtlOdVO> getGroupedOrderRequestsByCpCode(String cpCode) {
		return orderRequestMapper.getGroupedOrderRequestsByCpCode(cpCode);
	}

	@Override
	public MtlOdVO getOrderRequestById(String orderRequestCode) {
		return orderRequestMapper.getOrderRequestById(orderRequestCode);
	}

	@Override
	public void insertOrderRequest(MtlOdVO order) {
		orderRequestMapper.insertOrderRequest(order);
	}

	// 추가된 발주 요청 및 상세 데이터 삽입 메서드
	@Override
	public void insertOrderRequestWithDetails(MtlOdVO orderRequest, List<MtVO> items) {
		// 발주 요청 데이터 삽입
		orderRequestMapper.insertOrderRequest(orderRequest);

		// 발주 상세 데이터 삽입
		for (MtVO item : items) {
			MtlOdVO detail = new MtlOdVO();
			detail.setMtlOdCode(orderRequest.getMtlOdCode());
			detail.setMtCode(item.getMtCode());
			detail.setQuantity(item.getQuantity());
			detail.setUnitPrice(item.getUnitPrice());
			detail.setTotalAmount(item.getQuantity() * item.getUnitPrice());
			orderRequestMapper.insertOrderRequestDetail(detail);
		}

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
	public List<MtVO> getOrderRequestDetailsByOrderRequestCode(String orderRequestCode) {
		return orderRequestMapper.getOrderRequestDetailsByOrderRequestCode(orderRequestCode);
	}

	@Override
	public List<CpVO> getAllCpInfo() {
		return orderRequestMapper.getAllCpInfo();
	}

	@Override
	public List<MtVO> getItemsByCpCode(String cpCode) {
		return orderRequestMapper.getItemsByCpCode(cpCode);
	}

	// CP 코드에 따른 발주 상세내역 가져오기 추가
	@Override
	public List<MtVO> getOrderRequestDetailsByCpCode(String cpCode) {
		return orderRequestMapper.getOrderRequestDetailsByCpCode(cpCode);
	}

}
