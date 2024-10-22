package com.sjc.app.quality.mapper;

import java.util.List;
import java.util.Map;

import com.sjc.app.mt.service.MtlOdVO;
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
