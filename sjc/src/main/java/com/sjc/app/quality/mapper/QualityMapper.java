package com.sjc.app.quality.mapper;

import java.util.List;
import java.util.Map;

import com.sjc.app.mt.service.MtlOdVO;
import com.sjc.app.quality.service.InsDetailVO;
import com.sjc.app.quality.service.InspectionVO;


public interface QualityMapper {
	// 발주목록전체
	public List<InspectionVO> selectMtlOd();
	
	// 발주목록상세 테이블
	public List<Map<String, Object>> selectMtlOdDetail(String mtlOdCode);
	// 품질검사상세페이지
//	public List<Map<String, Object>> selectInspectionDetail(String mtlOdDetailCode);
	// 검사대기->검사중 - mtlOdStatus, mtlOdDetailStatus
	public int updateMtlOdStatus(InspectionVO inspectionVO);
	public int updateMtlOdDetailStatus(InspectionVO inspectionVO);
	// 품질검사상세페이지
	public int whetherInspection(InspectionVO inspectionVO);
	public int insertInspection(InspectionVO inspectionVO);
	public List<InspectionVO> selectInspection(InspectionVO inspectionVO);
	
	// 품질검사값 입력
	public int insertInsDetail(InspectionVO inspectionVO);
	public List<InsDetailVO> selectInsDetail(InspectionVO inspectionVO);
	
	// 입고품질검사 상세목록 /
	public List<Map<String, Object>> selectIncomingTest(String mtlOdDetailCode);
	
	
	//검사기준목록
	public List<InspectionVO> selectTest();
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 조회 - 입고검사대기 조회페이지
	public List<MtlOdVO> selectQualityWaitInfo();
	
	// 조회 - 입고검사완료 조회페이지
	public List<InspectionVO> selectQualityDoneInfo();

//	// 전체 조회 - 입고등록 페이지
//	public List<InspectionVO> selectQualityRegistrationInfo();
	
	// 단건조회 - 입고등록 페이지
	public InspectionVO selectQualityRegistrationInfo(InspectionVO inspectionVO);
	
	
	// 검사항목 - 입고등록 페이지
	//public  List<InsItemVO> selectQualityTestInfo(MtlOdVO mtlOdVO);
	public  List<InspectionVO> selectQualityTestInfo(MtlOdVO mtlOdVO);

	
	// 입고검사완료페이지(임의로 만든거) - 값 입고처리 버튼 누르면 수정
	public void updateIncoming(Map<String, Object> item);
	
	
	// 입고등록페이지 - 저장버튼 - inspection.ins_status 검사완료
	public int updateInspectionDone(InspectionVO inspectionVO);
	// 입고검사완료페이지 - 입고처리 버튼 - mtl_od.mtl_od_status 입고품질검사완료
	public int updateMtlOdDone(InspectionVO inspectionVO);
	// 입고검사완료페이지 - 입고처리 버튼 - mtl_od.mtl_od_status 반품
	public void updateMtlOdBack(InspectionVO inspectionVO);
	// 입고검사완료페이지 - 입고처리 버튼 - MtInVO로 post
	public void selectMtIn(InspectionVO inspectionVO);
	// 입고검사완료페이지 - 입고처리 버튼 - mt_in으로 데이터 넣기
	public List<InspectionVO> insertMtIn(InspectionVO inspectionVOs);
	// 
	//public int updateMtlOdDone(@Param("mtlOdStatus")String mtlOdStatus, InspectionVO inspectionVO);

	public void insertMtInInfo(InspectionVO inspectionVO);


	

}
