package com.sjc.app.quality.service;

import java.util.List;
import java.util.Map;

import com.sjc.app.mt.service.MtlOdVO;
import com.sjc.app.pr.service.PDetailVO;


public interface QualityService {
// 품질검사등록모달 공통
	// insDetail - insValue 업데이트
	public List<InsDetailVO> insValueUpdate(List<InsDetailVO> insDetailVO);

	
//입고
	// 자재입고품질검사 대기목록
	// 발주목록전체
	public List<InspectionVO> mtlOdList();
	// 발주목록상세 테이블
	public List<Map<String, Object>> MtlOdDetail(String mtlOdCode);
	// 품질검사상세페이지.
//	public List<Map<String, Object>> inspectionDetail(String mtlOdDetailCode);
	
	
	// 검사대기->검사중 - mtlOdStatus, mtlOdDetailStatus
	public Map<String, Object> mtlOdStatusUpdate(InspectionVO inspectionVO);
	public Map<String, Object> mtlOdDetailStatusUpdate(InspectionVO inspectionVO);
	// 자재품질검사등록모달-inspection 데이터 갯수 카운트
	public int whetherInspection(InspectionVO inspectionVO);
	// 자재품질검사등록모달-inspection 생성
	public int insertInspection(InspectionVO inspectionVO);
	// 자재품질검사등록모달-inspection 데이터 출력
	public List<InspectionVO> inspectionList(InspectionVO inspectionVO);
	// 자재품질검사등록모달-insDetail 데이터 갯수 카운트
	public int insItemCount(InspectionVO inspectionVO);
	// 자재품질검사등록모달- insDetail 생성
	public int insertInsDetail(InspectionVO inspectionVO);
	// 자재품질검사등록모달- 검사리스트 출력
	public List<InspectionVO> testDetailSelect(InspectionVO inspectionVO);
	public List<InspectionVO> insDetailList(InspectionVO inspectionVO);
	// 품질검사등록
	public List<InspectionVO> insUpdate(List<InspectionVO> insData);


	
	//검사기준목록
	public List<InspectionVO> testList();
	//검사기준목록
	public List<InspectionVO> testList2();
	
	// 자재입고검사완료 - 조회
	public List<InspectionVO> incomingDoneInfo();
	// 자재입고검사완료  - 입고처리 버튼 - mtl_od.mtl_od_status 입고품질검사완료  
	// 자재입고검사완료  - 입고처리 버튼 - mt_in으로 데이터 넣기
	public List<InspectionVO> mtlOdMtInUpdateInsert(List<InspectionVO> inspectionVOs);
	// 입고검사완료페이지 - 반품 버튼 - mtl_od.mtl_od_status 반품
	public List<InspectionVO> mtlOdBackUpdate(List<InspectionVO> inspectionVOs);
	
	
// 출고
	// 완제품품질검사 대기목록1
	public List<InspectionVO> pOrderSelect();
	// 완제품품질검사 대기목록2
	public List<Map<String, Object>> pDetailSelect(String porderCode);
	// 검사대기->검사중 - updatePOrderStatus, updatePDetailStatus
	Map<String, Object> pOrderStatusUpdate(InspectionVO inspectionVO);
	Map<String, Object> pDetailStatusUpdate(InspectionVO inspectionVO);
	
	// 완제품품질검사등록모달-inspection 데이터 갯수 카운트
	public int pDtlInsCnt(InspectionVO inspectionVO);
	// 완제품품질검사등록모달-inspection 생성
	public int dtlInsInsert(InspectionVO inspectionVO);
	// 완제품품질검사등록모달-inspection 데이터 출력
	public List<InspectionVO> pDtlInsSelect(InspectionVO inspectionVO);
	// 완제품품질검사등록모달-insDetail 데이터 갯수 카운트
	public int pDInsDCount(InspectionVO inspectionVO);
	// 완제품품질검사등록모달- insDetail 생성
	public int pDtlInsDInsert(InspectionVO inspectionVO);
	// 완제품품질검사등록모달- 검사리스트 출력
	public List<InspectionVO> pDtlTestSelect(InspectionVO inspectionVO);
	public List<InspectionVO> pDtlInsDListSelect(InspectionVO inspectionVO);
	//완제품품질검사모달창 - pdetail.pdetail_code 완제품품질검사완료
	public List<InspectionVO> insPdUpdate(List<InspectionVO> insPdData);
	

	
	
	
	
	
	
	
	
	// 제품출고검사완료 조회
	public List<InspectionVO> outDoneInfoSelect();
	// 완제품품질검완료페이지 - 입고처리 버튼 - porder.status 출고대기
	// 완제품품질검완료페이지 - 입고처리 버튼 - prdManagement로 데이터 넣기
	public List<InspectionVO> upPOrdInPMan(List<InspectionVO> inspectionVO);
	// 완제품품질검완료페이지 - pdetail.status 반품
	public List<InspectionVO> pdBackUpdate(List<InspectionVO> inspectionVO);
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 조회 - 입고검사대기 조회페이지
	public List<MtlOdVO> incomingWaitInfo();
	

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


	

	

	
	
	
//	// 입고검사완료페이지 - 입고처리 버튼 - MtInVO로 post
//	public List<InspectionVO> mtInSelect(List<InspectionVO> inspectionVOs);
//	// 입고검사완료페이지 - 입고처리 버튼 - mt_in으로 데이터 넣기
//	public List<InspectionVO> insertMtIn(List<InspectionVO> inspectionVOs);
	


}
