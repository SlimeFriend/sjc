package com.sjc.app.sales.service;

import java.util.List;
import java.util.Map;

public interface SalesService {
	
	// 주문접수
    public int insertOrder(OrderVO orderVO);
    public int getOrdCode();
    public int insertOrderDetail(ProductVO productVO, String ordCode);
	
	public List<OrderVO> order();
	public List<Map<String, Object>> orderDetail(String ordCode);
    
	// 제품
	public List<ProductVO> productList();
	public List<ProductVO> productManagement();
	public List<ProductVO> productLot();
	public List<ProductVO> productIn();
	
	public List<OrderVO> productOut();
	public List<ProductVO> inoutHistory();
	
	// 업체
	public List<OrderVO> companyList();
}
