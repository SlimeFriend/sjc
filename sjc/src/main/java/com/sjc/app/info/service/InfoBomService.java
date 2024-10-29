package com.sjc.app.info.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.sjc.app.sales.service.ProductVO;

public interface InfoBomService {
	public List<BomVO> registerPrdBoms(List<BomVO> bomVOs);
    public List<BomVO> registerBoms(List<BomVO> bomVOs);
    public List<BomVO> bomList();
    public List<BomVO> bomDetailList(BomVO bomVO);
    
    public ResponseEntity<Map<String, Object>> modifyPrdBom(PrdBomDTO prdBomDTO);
    public int modifyPrd(ProductVO productVO);
    public int modifyBom(BomVO bomVO);
	
}
