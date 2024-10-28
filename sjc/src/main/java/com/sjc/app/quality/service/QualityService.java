package com.sjc.app.quality.service;

import java.util.List;
import java.util.Map;

import com.sjc.app.mt.service.MtlOdVO;


public interface QualityService {
	// 발주목록전체
	public List<InspectionVO> mtlOdList();
	// 발주목록상세 테이블
	public List<Map<String, Object>> MtlOdDetail(String mtlOdCode);
	// 품질검사상세페이지.
//	public List<Map<String, Object>> inspectionDetail(String mtlOdDetailCode);
	// 품질검사상세페이지.
	public int insertInspection(InspectionVO inspectionVO);
	//검사목록
	public List<InspectionVO> inspectionList(InspectionVO inspectionVO);
	// 입고품질검사 상세목록 /
	public List<Map<String, Object>> incomingTestList(String mtlOdDetailCode);
	
	//검사기준목록
	public List<InspectionVO> testList();
	
	
	
	
	
	// 조회 - 입고검사대기 조회페이지
	public List<MtlOdVO> incomingWaitInfo();
	
	// 조회 - 입고검사완료 조회페이지
	public List<InspectionVO> incomingDoneInfo();

	//	// 전체조회 - 입고등록 페이지
//	public List<InspectionVO> incomingRegistrationInfo();
	
	// 단건조회 - 입고등록 페이지
	public InspectionVO incomingRegistrationInfo(InspectionVO inspectionVO);
	// 검사항목 - 입고등록 페이지
	//public List<InsItemVO> incomingQualityTestInfo(MtlOdVO mtlOdVO);
	public List<InspectionVO> incomingQualityTestInfo(MtlOdVO mtlOdVO);

	//// 입고검사완료페이지(임의로 만든거) - 값 입고처리 버튼 누르면 수정
	////public void updateIncoming(List<Map<String, Object>> items);
    //public List<InspectionVO> updateIncoming(List<InspectionVO> inspectionVOs);

	
	// 입고검사완료페이지 - 값 입고처리 버튼 누르면 자재발주상태(mtl_od/ status), ins(품질검사 상태) 완료로 넘기기
	//public Map<String, Object> inspectionDoneUpdate(InspectionVO inspectionVO);
	//public Map<String, Object> mtlOdDoneUpdate(InspectionVO inspectionVO);
	//public List<InspectionVO> inspectionDoneUpdate(List<InspectionVO> inspectionVO);
	//public List<InspectionVO> mtlOdDoneUpdate(List<InspectionVO> inspectionVO);
	// 입고등록페이지 - 저장버튼 - inspection.ins_status 검사완료
	public List<InspectionVO> inspectionDoneUpdate(List<InspectionVO> inspectionVOs);
	
	// 입고검사완료페이지 - 입고처리 버튼 - mtl_od.mtl_od_status 입고품질검사완료
	public List<InspectionVO> mtlOdMtInUpdateInsert(List<InspectionVO> inspectionVOs);
	// 입고검사완료페이지 - 입고처리 버튼 - mtl_od.mtl_od_status 반품
	public List<InspectionVO> mtlOdBackUpdate(List<InspectionVO> inspectionVOs);

	
	
	
//	// 입고검사완료페이지 - 입고처리 버튼 - MtInVO로 post
//	public List<InspectionVO> mtInSelect(List<InspectionVO> inspectionVOs);
//	// 입고검사완료페이지 - 입고처리 버튼 - mt_in으로 데이터 넣기
//	public List<InspectionVO> insertMtIn(List<InspectionVO> inspectionVOs);
	


}
