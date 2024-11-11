package com.sjc.app.info.mapper;

import java.util.List;

import com.sjc.app.info.service.BomVO;
import com.sjc.app.sales.service.ProductVO;

public interface InfoBomMapper {
    
    // BOM 등록
	//Long insertBom();
    public int insertBom(BomVO bomVO);
    
    // BOM 상세 등록
    //void insertBomDetail(List<BomVO> BomVOs);	
    public void insertBomDetail(BomVO bomVO);
    
    // 전체 BOM 조회
    public List<BomVO> selectBomAllList();
    
    // 특정 BOM 상세 조회
    public List<BomVO> selectBomDetailAllList(BomVO bomVO);
    
    // 제품 정보 수정
    public int updatePrd(ProductVO productVO);
    
    // 제품의 BOM 지정 시 기존에 지정돈 BOM 정보 초기화
    public int updatePrdNull(ProductVO productVO);
    
    // BOM 정보 수정
    public int updateBom(BomVO bomVO);
    
    // 제품의 BOM 지정 시 기존에 지정돈 BOM 정보 초기화
    public int updateBomNull(ProductVO productVO);
    
    // BOM 삭제
    public int deleteBom(BomVO bomVO);
    // BOM 상세 삭제
    public int deleteBomDetail(BomVO bomVO);
}
