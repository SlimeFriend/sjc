package com.sjc.app.quality.service;

import java.util.List;
import java.util.Map;

import com.sjc.app.mt.service.MtlOdVO;


public interface QualityService {
	// 조회 - 입고검사대기 조회페이지
	public List<MtlOdVO> incomingWaitInfo();
	
	// 조회 - 입고검사완료 조회페이지
	public List<InspectionVO> incomingDoneInfo();

	//	// 전체조회 - 입고등록 페이지
//	public List<InspectionVO> incomingRegistrationInfo();
	
	// 단건조회 - 입고등록 페이지
	public InspectionVO incomingRegistrationInfo(InspectionVO inspectionVO);
	// 검사항목 - 입고등록 페이지
	public List<InsItemVO> incomingQualityTestInfo(MtlOdVO mtlOdVO);

	// 입고검사완료페이지(임의로 만든거) - 값 입고처리 버튼 누르면 수정
	public void updateIncoming(List<Map<String, Object>> items);
	
	// 입고검사완료페이지 - 값 입고처리 버튼 누르면 자재발주상태(mtl_od/ status), ins(품질검사 상태) 완료로 넘기기
	public Map<String, Object> inspectionDoneUpdate(String insStatus);
	public Map<String, Object> mtlOdDoneUpdate(String mtlOdStatus);
}
