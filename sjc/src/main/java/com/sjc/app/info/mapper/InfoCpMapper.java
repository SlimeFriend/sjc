package com.sjc.app.info.mapper;

import java.util.List;

import com.sjc.app.sales.service.CpVO;

public interface InfoCpMapper {
	public List<CpVO> selectCpAllList(CpVO cpVO);
    int updateCp(CpVO cpVO);
    int insertCp(CpVO cpVO);	
}
