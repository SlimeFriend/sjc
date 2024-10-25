package com.sjc.app.info.mapper;

import java.util.List;

import com.sjc.app.info.service.BomVO;
import com.sjc.app.info.service.InfoCopyDetailVO;
import com.sjc.app.info.service.InfoCopyLogVO;
import com.sjc.app.info.service.InfoUserVO;

public interface InfoBomMapper {
    Long insertBom();
    void insertBomDetail(List<String> mtCodes);	
    public List<BomVO> selectBomAllList();
    public List<BomVO> selectBomDetailAllList();
    
}
