package com.sjc.app.info.mapper;

import java.util.List;

import com.sjc.app.info.service.BomVO;
import com.sjc.app.sales.service.ProductVO;

public interface InfoBomMapper {
    
    // BOM 등록
	//Long insertBom();
    int insertBom(BomVO bomVO);
    
    // BOM 상세 등록
    //void insertBomDetail(List<BomVO> BomVOs);	
    void insertBomDetail(BomVO bomVO);
    
    // 전체 BOM 조회
    public List<BomVO> selectBomAllList();
    
    // 특정 BOM 상세 조회
    public List<BomVO> selectBomDetailAllList(BomVO bomVO);
    
    // 제품 정보 수정
    int updatePrd(ProductVO productVO);
    
    // 제품의 BOM 지정 시 기존에 지정돈 BOM 정보 초기화
    int updatePrdNull(ProductVO productVO);
    
    // BOM 정보 수정
    int updateBom(BomVO bomVO);
    
    // 제품의 BOM 지정 시 기존에 지정돈 BOM 정보 초기화
    int updateBomNull(ProductVO productVO);
}
