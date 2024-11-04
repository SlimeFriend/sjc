package com.sjc.app.quality.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sjc.app.mt.service.MtlOdVO;
import com.sjc.app.pr.service.PDetailVO;
import com.sjc.app.quality.service.InsDetailVO;
import com.sjc.app.quality.service.InspectionVO;


public interface QualityMapper {
// 품질검사등록모달 공통
	// insDetail - insValue 업데이트
	public void updateInsValue (InsDetailVO insDetailVO);
	// 품질검사등록
	public int updateIns(@Param("userId") int userId, @Param("userName") String userName, @Param("numberOfTests") int numberOfTests, @Param("numberOfPasses") int numberOfPasses, @Param("numberOfFailed") int numberOfFailed, @Param("totalPass") String totalPass, @Param("numberOfTotalPass") int numberOfTotalPass, @Param("insCode") String insCode);

//입고
	// 발주목록전체
	public List<InspectionVO> selectMtlOd();
	
	// 발주목록상세 테이블
	public List<Map<String, Object>> selectMtlOdDetail(String mtlOdCode);

	// 검사대기->검사중 - mtlOdStatus, mtlOdDetailStatus
	public int updateMtlOdStatus(InspectionVO inspectionVO);
	public int updateMtlOdDetailStatus(InspectionVO inspectionVO);
	// 자재품질검사등록모달-inspection 데이터 갯수 카운트
	public int whetherInspection(InspectionVO inspectionVO);
	// 자재품질검사등록모달-inspection 생성
	public int insertInspection(InspectionVO inspectionVO);
	// 자재품질검사등록모달-inspection 데이터 출력
	public List<InspectionVO> selectInspection(InspectionVO inspectionVO);
	
	// 자재품질검사등록모달-insDetail 데이터 갯수 카운트
	public int countInsItem(InspectionVO inspectionVO);
	// 자재품질검사등록모달- insDetail 생성
	public int insertInsDetail(InspectionVO inspectionVO);
	// 자재품질검사등록모달- 검사리스트 출력
	public List<InspectionVO> selectTestDetail(InspectionVO inspectionVO);
	public List<InspectionVO> selectInsDetailList(InspectionVO inspectionVO);
	
	//검사기준목록
	public List<InspectionVO> selectTest();
	// 입고등록페이지 - 저장버튼 - inspection.ins_status 검사완료
	public void updateMtlOdSt(InspectionVO inspectionVO);
	
	
	// 자재입고검사완료 - 조회
	public List<InspectionVO> selectQualityDoneInfo();
	// 자재입고검사완료페이지 - 입고처리 버튼 - mtl_od.mtl_od_status 입고품질검사완료
	public int updateMtlOdDone(InspectionVO inspectionVO);
	// 자재입고검사완료페이지 - 입고처리 버튼 - mt_in으로 데이터 넣기
	public List<InspectionVO> insertMtIn(InspectionVO inspectionVOs);
	// 입고검사완료페이지 - 입고처리 버튼 - mtl_od.mtl_od_status 반품
	public int updateMtlOdBack(InspectionVO inspectionVO);

	
	
	
// 출고
	// 완제품품질검사 대기목록1
	public List<InspectionVO> selectPOrder();
	// 완제품품질검사 대기목록2
	public List<Map<String, Object>> selectPDetail(String porderCode);
	// 완제품품질검사등록모달-inspection 데이터 갯수 카운트
	public int cntPDtlIns(InspectionVO inspectionVO);
	// 완제품품질검사등록모달-inspection 생성
	public int insertPDtlIns(InspectionVO inspectionVO);
	// 완제품품질검사등록모달-inspection 데이터 출력
	public List<InspectionVO> selectPDtlIns(InspectionVO inspectionVO);
	// 완제품품질검사등록모달-insDetail 데이터 갯수 카운트
	public int countPDInsD(InspectionVO inspectionVO);
	// 완제품품질검사등록모달- insDetail 생성
	public int insertPDtlInsD(InspectionVO inspectionVO);
	// 완제품품질검사등록모달- 검사리스트 출력
	public List<InspectionVO> selectPDtlTest(InspectionVO inspectionVO);
	public List<InspectionVO> selectPDtlInsDList(InspectionVO inspectionVO);
	// 완제품품질검사모달창 - pdetail.pdetail_code 완제품품질검사완료
	public int updatePdSt(InspectionVO inspectionVO);

	
	// 완제품품질검완료페이지 조회
	public List<PDetailVO> selectOutDoneInfo();
	// 완제품품질검완료페이지 - 입고처리 버튼 - porder.status 출고대기
	public int updatePOrderDone(InspectionVO inspectionVO);
	// 완제품품질검완료페이지 - 입고처리 버튼 - prdManagement로 데이터 넣기
	public List<InspectionVO> insertPManage(InspectionVO inspectionVOs);
	// 완제품품질검완료페이지 - pdetail.status 반품
	public void updatePdBack(InspectionVO inspectionVO);
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	// 조회 - 입고검사대기 조회페이지
	public List<MtlOdVO> selectQualityWaitInfo();
	

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


	//public int updateMtlOdDone(@Param("mtlOdStatus")String mtlOdStatus, InspectionVO inspectionVO);

	public void insertMtInInfo(InspectionVO inspectionVO);





	

}
