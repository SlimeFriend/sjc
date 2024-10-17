package com.sjc.app.sales.service;

import java.util.List;

public interface SalesService {
	
	// 주문접수
	public int insertOrder(OrderVO orderVO);
	
	// 제품
	public List<ProductVO> productList();
	public List<ProductVO> productManagement();
	public List<ProductVO> productIn();
	
	public List<OrderVO> productOut();
	public List<OrderVO> orderHistory();
	public List<ProductVO> inoutHistory();
	
	// 업체
	public List<OrderVO> companyList();
}
