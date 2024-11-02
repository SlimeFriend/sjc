package com.sjc.app.info.service;

import java.util.List;

import com.sjc.app.sales.service.ProductVO;

public interface InfoPrdService {
	// 제품 조회
	public List<ProductVO> prdList(ProductVO productVO);
	
}
