package com.sjc.app.info.mapper;

import java.util.List;

import com.sjc.app.info.service.BomVO;
import com.sjc.app.mt.service.MtVO;

public interface InfoBomMapper {
    Long insertBom();
    //void insertBomDetail(List<BomVO> BomVOs);	
    void insertBomDetail(String mtCode);	
    public List<BomVO> selectBomAllList();
    public List<BomVO> selectBomDetailAllList();
    
}
