package com.sjc.app.quality.service;

import java.util.List;

public interface QualityMngService {
	// 검사 완료 조회
	public List<InspectionVO> insSelect(InspectionVO inspectionVO);
	
	// 검사 완료 필터링 ( 전체, MTD, PD )
	public List<InspectionVO> insRadio(String rad);
}
