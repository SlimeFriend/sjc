package com.sjc.app.sales.service;

import java.util.List;
import java.util.Map;

public interface SalesService {
	
	// 주문접수 프로세스
    public int insertOrder(SalesDTO salesDTO);
	public List<OrderVO> order();
	public List<Map<String, Object>> orderDetail(String ordCode);
	
    
	// 출고접수 프로세스
	public List<OrderVO> getOrdersByStatus(String status);
	public int productOutProcess(Map<String, Object> data);
	public int remainProcess(List<Map<String, Object>> outRemainData);
	
	// 입고접수 프로세스
	public List<ProductVO> productIn();
	
	// 출고내역
	public List<PrdManagementVO> inHistory();
	public List<outHistoryVO> outHistory();
	
	// 주문내역 검색 프로세스
	public List<OrderVO> searchOrder(String companyName, String orderStartDate, String orderEndDate, String deliveryStartDate, String deliveryEndDate);

	// 제품
	public List<ProductVO> productList();
	public List<ProductVO> productManagement();
	public List<ProductVO> productLot();
	
	public List<Map<String, Object>> productDetail(String prdCode);
	
	// 업체
	public List<OrderVO> companyList();

}
