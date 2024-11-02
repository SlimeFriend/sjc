package com.sjc.app.info.mapper;

import java.util.List;

import com.sjc.app.sales.service.ProductVO;

public interface InfoPrdMapper {
	// 제품 조회
	public List<ProductVO> selectPrdAllList(ProductVO prductVO);
	// 제품 입력
    int insertProduct(ProductVO productVO);
}
