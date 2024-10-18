package com.sjc.app.sales.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sjc.app.sales.service.OrderVO;
import com.sjc.app.sales.service.ProductVO;

public interface SalesMapper {

	// 주문접수
	public int insertOrder(OrderVO orderVO);
	
	// 주문상세
	public int insertOrderDetail(@Param("productVO") ProductVO productVO, @Param("ordCode") String ordCode);
	
	// 제품 테이블
	public List<ProductVO> selectProduct();

	// 제품관리
	public List<ProductVO> selectProductManagement();

	// 제품입고
	public List<ProductVO> selectProductIn();

	// 제품 출고
	public List<OrderVO> selectProductOut();
	
	// 주문내역
	public List<OrderVO> selectOrderHistory();
	
	// 입출고 내역
	public List<ProductVO> selectInOutHistory();
	
	// 업체 리스트
	public List<OrderVO> selectCompany();


}
