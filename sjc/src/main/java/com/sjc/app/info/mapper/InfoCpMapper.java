package com.sjc.app.info.mapper;

import java.util.List;

import com.sjc.app.sales.service.CpVO;

public interface InfoCpMapper {
	// 업체 조회
	public List<CpVO> selectCpAllList(CpVO cpVO);
	// 업체 수정
    int updateCp(CpVO cpVO);
    // 업체 등록
    int insertCp(CpVO cpVO);	
}

