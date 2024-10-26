package com.sjc.app.info.mapper;

import java.util.List;

import com.sjc.app.info.service.BomVO;

public interface InfoBomMapper {
    Long insertBom();
    //void insertBomDetail(List<BomVO> BomVOs);	
    void insertBomDetail(BomVO bomVO);	
    public List<BomVO> selectBomAllList();
    public List<BomVO> selectBomDetailAllList();
    
}
