package com.sjc.app.sales.service;

import java.util.List;
import java.util.Map;

public interface SalesService {
	
	// 주문접수
    public int insertOrder(SalesDTO salesDTO);
    //public String getOrdCode();
    //public int insertOrderDetail(ProductVO productVO, String ordCode);
	
	public List<OrderVO> order();
	public List<Map<String, Object>> orderDetail(String ordCode);
    
	// 출고접수 프로세스
	public int productOutProcess(Map<String, Object> data);
	
	// 입고접수 프로세스
	public List<ProductVO> productIn();
	
	// 출고내역
	public List<outHistoryVO> outHistory();
	
	
	// 제품
	public List<ProductVO> productList();
	public List<ProductVO> productManagement();
	public List<ProductVO> productLot();
	
	public List<Map<String, Object>> productDetail(String prdCode);
	
	public List<OrderVO> productOut();
	
	// 업체
	public List<OrderVO> companyList();

}
