package com.sjc.app.sales.service;

import java.util.List;
import java.util.Map;

public interface SalesService {
	
	// 업체 선택
	public List<CpVO> cpList();
	
	// 주문접수 프로세스
    public int insertOrder(SalesDTO salesDTO);
	public List<Map<String, Object>> orderDetail(String ordCode);
	
	
	
	// 주문내역 조회
	public List<OrderVO> order();
	
	// 주문내역 검색 프로세스
	public List<OrderVO> searchOrder(String companyName, String orderStartDate, String orderEndDate, String deliveryStartDate, String deliveryEndDate, String orderStatus);
	
	
	
	// 주문내역 삭제 조회
	public List<OrderVO> deleteOrderList();
	
	// 주문내역 삭제 상세 조회
	public List<Map<String, Object>> outDetail(String ordCode);
		
	// 주문내역 삭제 프로세스
	public int deleteOrder(List<String> ordCodes);
	
	
    
	// 출고접수 프로세스
	public List<OrderVO> getOrdersByStatus(String status);
	public int productOutProcess(Map<String, Object> data);
	
	
	// 입/출고내역
	public List<PrdManagementVO> inHistory();
	public List<outHistoryVO> outHistory();
	
	// 입고 내역 검색 프로세스
	public List<PrdManagementVO> inSearch(String prdName, String inStartDate, String inEndDate);
	
	// 출고 내역 검색 프로세스
	public List<outHistoryVO> outSearch(String prdName, String cpName, String outStartDate, String outEndDate);
	
	// 제품목록
	public List<ProductVO> productList();
	public List<ProductVO> productManagement();
	public List<ProductVO> productLot();
	
	public List<Map<String, Object>> productDetail(String prdCode);
	

}
