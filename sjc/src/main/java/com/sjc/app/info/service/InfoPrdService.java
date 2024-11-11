package com.sjc.app.info.service;

import java.util.List;

import com.sjc.app.sales.service.ProductVO;

public interface InfoPrdService {
	// 제품 조회
	public List<ProductVO> prdList(ProductVO productVO);
    // 제품 삭제
	public List<String> deletePrds(List<String> prdCodes);
    // 제품 수정
    public List<ProductVO> updatePrds(List<ProductVO> productVOs);
    // 제품 코드 존재 확인
    public int checkPrdCode(ProductVO productVO);
}
