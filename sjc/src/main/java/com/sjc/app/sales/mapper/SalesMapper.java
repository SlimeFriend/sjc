package com.sjc.app.sales.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sjc.app.sales.service.OrderVO;
import com.sjc.app.sales.service.PrdManagementVO;
import com.sjc.app.sales.service.ProductVO;
import com.sjc.app.sales.service.outHistoryVO;

public interface SalesMapper {

	// 주문접수
	public int insertOrder(OrderVO orderVO);
	
	// 주문내역
	public List<OrderVO> selectOrder();
	
	// 주문코드 불러오기
	public String getOrdCode();
	
	// 주문코드 불러오기
	public String getLot();
	
	// 주문상세
	public int insertOrderDetail(@Param("productVO") ProductVO productVO, @Param("ordCode") String ordCode);
	
	// 주문상세 테이블
	public List<Map<String, Object>> selectOrderDetail(String ordCode);
	
	// 제품 테이블
	public List<ProductVO> selectProduct();

	// 제품관리
	public List<ProductVO> selectProductManagement();
	public List<ProductVO> selectProductLot();
	public List<Map<String, Object>> selectProductDetail(String prdCode);

	// 제품입고
	public List<ProductVO> selectProductIn();

	// 제품 출고
	public List<OrderVO> selectOrdersByStatus(@Param("status") String status);
	
	// 입출고 내역
	public List<PrdManagementVO> selectInHistory();
	public List<outHistoryVO> selectOutHistory();
	
	// 업체 리스트
	public List<OrderVO> selectCompany();
	
	
	// 제품 출고 프로세스
	
	// 미출고량 계산
	int selectRemainData(@Param("ordCode") String ordCode, @Param("prdCode") String prdCode);
	
	// 출고내역 등록
	public int insertOutHistory(@Param("ordCode") String ordCode, @Param("prdCode") String prdCode, @Param("lot") String lot, @Param("outQuantity") int outQuantity, @Param("cpName") String cpName);
	public int prdLotOutProcess(@Param("outQuantity") int outQuantity, @Param("lot") String lot);
	public int updateOrdFinish(@Param("ordCode") String ordCode);

	public List<OrderVO> searchOrder(String companyName, String orderStartDate, String orderEndDate, String deliveryStartDate, String deliveryEndDate);
	
}
