package com.sjc.app.quality.service;

import java.util.List;

import com.sjc.app.mt.service.MtlOdVO;


public interface QualityService {
	// 조회 - 대기상태
	public List<MtlOdVO> incomingWaitInfo();
}
