package com.sjc.app.info.service;

import java.util.List;
import java.util.Map;

import com.sjc.app.sales.service.ProductVO;

public interface InfoBomService {
	// 제품, BOM 등록
	public List<BomVO> registerPrdBoms(List<BomVO> bomVOs);
	// BOM 등록
    public List<BomVO> registerBoms(List<BomVO> bomVOs);
    // BOM 조회
    public List<BomVO> bomList();
    // BOM 상제 조회
    public List<BomVO> bomDetailList(BomVO bomVO);
    // 제품, BOM 수정
    public Map<String, Object> modifyPrdBom(PrdBomDTO prdBomDTO);
    // 제품 수정
    public int modifyPrd(ProductVO productVO);
    // BOM 수정
    public int modifyBom(BomVO bomVO);
    // BOM, BOM 상세 삭제
    public List<BomVO> deleteBoms(List<BomVO> bomVOs);    
	
}
