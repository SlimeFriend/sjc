package com.sjc.app.quality.service;

import java.util.List;

import com.sjc.app.mt.service.MtlOdVO;


public interface QualityService {
	// 조회 - 입고대기 조회페이지
	public List<MtlOdVO> incomingWaitInfo();
	
	// 조회 - 입고등록 페이지
	public List<InspectionVO> incomingRegistrationInfo();
}
