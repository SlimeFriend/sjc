package com.sjc.app.sales.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjc.app.sales.mapper.SalesMapper;
import com.sjc.app.sales.service.OrderVO;
import com.sjc.app.sales.service.ProductVO;
import com.sjc.app.sales.service.SalesService;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service 
public class SalesServiceImpl implements SalesService {
	private SalesMapper salesMapper;
	
	@Autowired
	SalesServiceImpl(SalesMapper salesMapper, MeterRegistry registry) {
		this.salesMapper = salesMapper;
	}
	
	// 주문접수
	@Override
	public int insertOrder(OrderVO orderVO) {
		return salesMapper.insertOrder(orderVO);
	}
	
	@Override
	public int insertOrderDetail(ProductVO productVO, String ordCode) {
		System.err.print(productVO);
	    return salesMapper.insertOrderDetail(productVO, ordCode);
	}
	
	@Override
	public List<ProductVO> productList() {
		return salesMapper.selectProduct();
	}

	@Override
	public List<OrderVO> order() {
		return salesMapper.selectOrder();
	}
	
	@Override
	public List<Map<String, Object>> orderDetail(String ordCode) {
	    return salesMapper.selectOrderDetail(ordCode);
	}

	@Override
	public List<ProductVO> productManagement() {
		return salesMapper.selectProductManagement();
	}

	@Override
	public List<ProductVO> productIn() {
		return salesMapper.selectProductIn();
	}

	@Override
	public List<OrderVO> productOut() {
		return salesMapper.selectProductOut();
	}

	@Override
	public List<ProductVO> inoutHistory() {
		return salesMapper.selectInOutHistory();
	}

	@Override
	public List<OrderVO> companyList() {
		return salesMapper.selectCompany();
	}

}
