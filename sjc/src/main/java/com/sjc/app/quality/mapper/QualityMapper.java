package com.sjc.app.quality.mapper;

import java.util.List;

import com.sjc.app.mt.service.MtlOdVO;

public interface QualityMapper {
	// 조회 - 대기상태
	public List<MtlOdVO> selectQualityWaitInfo();

}
