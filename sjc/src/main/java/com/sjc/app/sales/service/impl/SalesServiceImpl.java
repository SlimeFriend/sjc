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
	private DataSource dataSource;
	
	@Autowired
	SalesServiceImpl(SalesMapper salesMapper, DataSource dataSource, MeterRegistry registry) {
		this.salesMapper = salesMapper;
		this.dataSource = dataSource;
	}
	
	// 주문접수
	@Override
	public int insertOrder(OrderVO orderVO) {
		return salesMapper.insertOrder(orderVO);
	}
	
	// 주문번호 시퀀스 값 가져오기
	@Override
	public String getOrdCode() {
		String nextId = "";
		String sql = "select"
				+ " case"
				+ " when (max(substr(ord_code, 4, 8))) = to_char(sysdate, 'yyyyMMdd') then ('ORD' || to_char(max(substr(ord_code, 4, 12)) + 1))"
				+ " else ('ORD' || to_char(sysdate, 'yyyyMMdd') || to_char(lpad(1, 3, 0)) )"
				+ " end ord_code"
				+ " from ord";
		try (Connection connection = dataSource.getConnection();
	             PreparedStatement ps = connection.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {
	            
	            if (rs.next()) {
	                nextId = rs.getString(1);  // 시퀀스 값 가져오기
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return nextId;
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
	public List<Map<String, Object>> productDetail(String prdCode) {
		return salesMapper.selectProductDetail(prdCode);
	}

	@Override
	public List<ProductVO> productLot() {
		return salesMapper.selectProductLot();
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

	@Override
	public List<ProductVO> productManagement() {
		return salesMapper.selectProductManagement();
	}

}
