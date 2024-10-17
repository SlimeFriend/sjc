package com.sjc.app.quality.mapper;

import java.util.List;

import com.sjc.app.mt.service.MtlOdVO;
import com.sjc.app.quality.service.InspectionVO;


public interface QualityMapper {
	// 조회 - 입고대기 조회페이지
	public List<MtlOdVO> selectQualityWaitInfo();
	
//	// 전체 조회 - 입고등록 페이지
//	public List<InspectionVO> selectQualityRegistrationInfo();
	
	// 단건조회 - 입고등록 페이지
	public InspectionVO selectQualityRegistrationInfo(InspectionVO inspectionVO);

}
