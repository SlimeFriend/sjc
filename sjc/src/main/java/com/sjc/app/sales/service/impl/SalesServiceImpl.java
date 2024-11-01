package com.sjc.app.sales.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjc.app.sales.mapper.SalesMapper;
import com.sjc.app.sales.service.CpVO;
import com.sjc.app.sales.service.OrderVO;
import com.sjc.app.sales.service.PrdManagementVO;
import com.sjc.app.sales.service.ProductVO;
import com.sjc.app.sales.service.SalesDTO;
import com.sjc.app.sales.service.SalesService;
import com.sjc.app.sales.service.outHistoryVO;

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
	
	// 업체 검색
	@Override
	public List<CpVO> cpList() {
		return salesMapper.selectCp();
	}
	
	// 주문접수 프로세스
	@Transactional
	@Override
	public int insertOrder(SalesDTO salesDTO) {
		// 키 생성
		String nextId = salesMapper.getOrdCode();
		String ordCode = String.valueOf(nextId);
		 
		// 주문 마스터 등록
	    OrderVO orderVO = salesDTO.getOrderVO();
	    orderVO.setOrdCode(ordCode);
	    int orderResult = salesMapper.insertOrder(orderVO);
	    
	    // 주문 디테일 등록
	    List<ProductVO> productVOList = salesDTO.getProductVO(); 
	    if(orderResult > 0) {
	        productVOList.forEach(productVO -> {
	        	salesMapper.insertOrderDetail(productVO, orderVO.getOrdCode());
	        });
	    }
		
		return 1;
		
		//return salesMapper.insertOrder(orderVO);
	}
	
	// 출고 화면
	@Override
	public List<OrderVO> getOrdersByStatus(String status) {
		return salesMapper.selectOrdersByStatus(status);
	}
	
	// 출고접수 프로세스
	@Transactional
	@Override
	public int productOutProcess(Map<String, Object> data) {
		
		int totalRowsAffected = 0;
		
		String ordCode = null;
		
		// LOT별 제품 출고 프로세스
		List<Map<String, Object>> outLotData = (List<Map<String, Object>>) data.get("outLotData");
		for(Map<String, Object> lot : outLotData) {
			String prdCode = (String) lot.get("prdCode");
			ordCode = (String) lot.get("ordCode");
			String lotNumber = (String) lot.get("lot");
			String cpName = (String) lot.get("cpName");
			int outQuantity = (Integer) lot.get("outQuantity");
			
			totalRowsAffected += salesMapper.insertOutHistory(ordCode, prdCode, lotNumber, outQuantity, cpName);
			totalRowsAffected += salesMapper.prdLotOutProcess(outQuantity, lotNumber);
		}
		
		salesMapper.updateOrdFinish(ordCode);
	    
		return totalRowsAffected;
	}
	
	
	
	// 미출고량 계산 프로세스
	@Override
	public int remainProcess(List<Map<String, Object>> outRemainData) {
		int totalRowsAffected = 0;
		
		for(Map<String, Object> item : outRemainData) {
			String ordCode = (String) item.get("ordCode");
			String prdCode = (String) item.get("prdCode");
			
			System.err.println("이 놈은" + ordCode + " " + prdCode);
			
			Integer remainQuantity = salesMapper.selectRemainData(ordCode, prdCode);
		}
		return totalRowsAffected;
	}
	
	// 입고 접수
	@Override
	public List<ProductVO> productIn() {
		String lot = salesMapper.getLot();
		return salesMapper.selectProductIn();
	}
	
	// 주문내역 검색
	@Override
	public List<OrderVO> searchOrder(String companyName, String orderStartDate, String orderEndDate, String deliveryStartDate, String deliveryEndDate, String orderStatus) {
		 return salesMapper.searchOrder(companyName, orderStartDate, orderEndDate, deliveryStartDate, deliveryEndDate, orderStatus);
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
	public List<Map<String, Object>> productDetail(String prdCode) {
		return salesMapper.selectProductDetail(prdCode);
	}

	@Override
	public List<ProductVO> productLot() {
		return salesMapper.selectProductLot();
	}

	@Override
	public List<ProductVO> productManagement() {
		return salesMapper.selectProductManagement();
	}
	
	@Override
	public List<PrdManagementVO> inHistory() {
		return salesMapper.selectInHistory();
	}

	@Override
	public List<outHistoryVO> outHistory() {
		return salesMapper.selectOutHistory();
	}
	
	// 입고 내역 검색 프로세스
	@Override
	public List<PrdManagementVO> inSearch(String prdName, String inStartDate, String inEndDate) {
		return salesMapper.inSearch(prdName, inStartDate, inEndDate);
	}
	
	// 출고 내역 검색 프로세스
	@Override
	public List<outHistoryVO> outSearch(String prdName, String cpName, String outStartDate, String outEndDate) {
		return salesMapper.outSearch(prdName, cpName, outStartDate, outEndDate);
	}

}
