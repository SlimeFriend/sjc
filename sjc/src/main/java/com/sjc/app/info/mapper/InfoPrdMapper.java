package com.sjc.app.info.mapper;

import java.util.List;

import com.sjc.app.sales.service.ProductVO;

public interface InfoPrdMapper {
	// 제품 조회
	public List<ProductVO> selectPrdAllList(ProductVO prductVO);
	// 제품 입력
	public int insertProduct(ProductVO productVO);
    // 제품 삭제
	public void deletePrds(String prdCode);    
    // 사용자 정보 수정
	public int updatePrds(ProductVO productVO);    
}
