package com.sjc.app.quality.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

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
	//public  List<InsItemVO> selectQualityTestInfo(MtlOdVO mtlOdVO);
	public  List<InspectionVO> selectQualityTestInfo(MtlOdVO mtlOdVO);

	
	// 입고검사완료페이지(임의로 만든거) - 값 입고처리 버튼 누르면 수정
	public void updateIncoming(Map<String, Object> item);

	
	// 입고검사완료페이지 - 값 입고처리 버튼 누르면 ins(품질검사 상태) 완료로 넘기기
	public int updateInspectionDone(InspectionVO inspectionVO);
	// 입고검사완료페이지 - 값 입고처리 버튼 누르면 자재발주상태(mtl_od/ status)완료로 넘기기
	public int updateMtlOdDone(InspectionVO inspectionVO);

	public void updateMtlOdBack(InspectionVO inspectionVO);

	//public int updateMtlOdDone(@Param("mtlOdStatus")String mtlOdStatus, InspectionVO inspectionVO);


}
