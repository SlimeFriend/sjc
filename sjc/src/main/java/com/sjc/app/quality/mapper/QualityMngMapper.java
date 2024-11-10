package com.sjc.app.quality.mapper;

import java.util.List;

import com.sjc.app.quality.service.InspectionVO;

public interface QualityMngMapper {
	// 검사 완료 조회
	public List<InspectionVO> selectIns(InspectionVO inspectionVO);
	
	// 검사 완료 필터링 ( 전체, MTD, PD )
	public List<InspectionVO> radioAllIns();
	public List<InspectionVO> radioMTLDIns();
	public List<InspectionVO> radioPDIns();
}
