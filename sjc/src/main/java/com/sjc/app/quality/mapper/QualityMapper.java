package com.sjc.app.quality.mapper;

import java.util.List;
import java.util.Map;

import com.sjc.app.mt.service.MtlOdVO;
import com.sjc.app.quality.service.InsItemVO;
import com.sjc.app.quality.service.InspectionVO;


public interface QualityMapper {
	// 조회 - 입고검사대기 조회페이지
	public List<MtlOdVO> selectQualityWaitInfo();
	
	// 조회 - 입고검사완료 조회페이지
	public List<InspectionVO> selectQualityDoneInfo();

//	// 전체 조회 - 입고등록 페이지
//	public List<InspectionVO> selectQualityRegistrationInfo();
	
	// 단건조회 - 입고등록 페이지
	public InspectionVO selectQualityRegistrationInfo(InspectionVO inspectionVO);
	
	
	// 검사항목 - 입고등록 페이지
	public  List<InsItemVO> selectQualityTestInfo(MtlOdVO mtlOdVO);

	public void updateIncoming(Map<String, Object> item);


}
