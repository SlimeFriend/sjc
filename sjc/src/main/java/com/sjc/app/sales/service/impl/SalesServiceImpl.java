package com.sjc.app.sales.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjc.app.sales.mapper.SalesMapper;
import com.sjc.app.sales.service.OrderVO;
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
	private DataSource dataSource;
	
	@Autowired
	SalesServiceImpl(SalesMapper salesMapper, DataSource dataSource, MeterRegistry registry) {
		this.salesMapper = salesMapper;
		this.dataSource = dataSource;
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
	
	
	// 출고접수 프로세스
	@Transactional
	@Override
	public int productOutProcess(Map<String, Object> data) {
		
		int totalRowsAffected = 0;
		
		// LOT별 제품 출고 프로세스
		List<Map<String, Object>> outLotData = (List<Map<String, Object>>) data.get("outLotData");
		for(Map<String, Object> lot : outLotData) {
			String prdCode = (String) lot.get("prdCode");
			String ordCode = (String) lot.get("ordCode");
			String lotNumber = (String) lot.get("lot");
			String cpName = (String) lot.get("cpName");
			int outQuantity = (Integer) lot.get("outQuantity");
			
			totalRowsAffected += salesMapper.insertOutHistory(ordCode, prdCode, lotNumber, outQuantity, cpName);
			totalRowsAffected += salesMapper.prdLotOutProcess(outQuantity, lotNumber);
		}
		
		/*
		 * // Prd별 제품 출고 프로세스 List<Map<String, Object>> outPrdData = (List<Map<String,
		 * Object>>) data.get("outPrdData"); for(Map<String, Object> prd : outPrdData) {
		 * String prdCode = (String) prd.get("prdCode"); String ordCode = (String)
		 * prd.get("ordCode"); int totalOutQuantity = (Integer)
		 * prd.get("totalOutQuantity");
		 * 
		 * totalRowsAffected += salesMapper.prdOutProcess(ordCode, prdCode,
		 * totalOutQuantity); }
		 */
	    
		return totalRowsAffected;
	}
	
	// 미출고량 계산 프로세스
	@Override
	public int remainProcess(List<Map<String, Object>> outRemainData) {
		System.err.print(outRemainData);
		return salesMapper.selectRemainData(outRemainData);
	}
	
	// 입고 접수
	@Override
	public List<ProductVO> productIn() {
		String lot = salesMapper.getLot();
		return salesMapper.selectProductIn();
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
	public List<OrderVO> productOut() {
		return salesMapper.selectProductOut();
	}

	@Override
	public List<outHistoryVO> outHistory() {
		return salesMapper.selectOutHistory();
	}

	@Override
	public List<OrderVO> companyList() {
		return salesMapper.selectCompany();
	}

	@Override
	public List<ProductVO> productManagement() {
		return salesMapper.selectProductManagement();
	}

}
