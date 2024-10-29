package com.sjc.app.info.mapper;

import java.util.List;

import com.sjc.app.info.service.BomVO;
import com.sjc.app.sales.service.ProductVO;

public interface InfoBomMapper {
    //Long insertBom();
    int insertBom(BomVO bomVO);
    //void insertBomDetail(List<BomVO> BomVOs);	
    void insertBomDetail(BomVO bomVO);	
    public List<BomVO> selectBomAllList();
    public List<BomVO> selectBomDetailAllList(BomVO bomVO);
    
    int updatePrd(ProductVO productVO);
    int updateBom(BomVO bomVO);
}
