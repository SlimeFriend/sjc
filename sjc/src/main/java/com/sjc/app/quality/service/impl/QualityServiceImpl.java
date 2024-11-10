package com.sjc.app.quality.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjc.app.mt.service.MtlOdVO;
import com.sjc.app.quality.mapper.QualityMapper;
import com.sjc.app.quality.service.InsDetailVO;
import com.sjc.app.quality.service.InspectionVO;
import com.sjc.app.quality.service.QualityService;

@Service
public class QualityServiceImpl implements QualityService{
	private QualityMapper qualityMapper;
	
	@Autowired
	public QualityServiceImpl (QualityMapper qualityMapper) {
		this.qualityMapper = qualityMapper;
	}
	
	
	
// 품질검사등록모달 공통
	//insDetail - insValue 업데이트
	@Override
	public List<InsDetailVO> insValueUpdate(List<InsDetailVO> list) {
		
		for(InsDetailVO insDetailVO : list) {
			
			qualityMapper.updateInsValue(insDetailVO);
		}
		
		
		return list;
	}

	
	
	
	
	
//입고
	// 자재입고품질검사 대기목록
	// 발주목록전체
	@Override
	public List<InspectionVO> mtlOdList() {
		return qualityMapper.selectMtlOd();
	}
	
	// 발주목록검색
	@Override
	public List<InspectionVO> moSearch(String cpCode, String cpName, String moStartDate, String moEndDate) {
		return qualityMapper.searchMO(cpCode, cpName, moStartDate, moEndDate);
	}
	
	// 발주목록상세 테이블
	@Override
	public List<Map<String, Object>> MtlOdDetail(String mtlOdCode) {
		return qualityMapper.selectMtlOdDetail(mtlOdCode);
	}
//	// 품질검사상세페이지.
//	@Override
//	public List<Map<String, Object>> inspectionDetail(String mtlOdDetailCode) {
//		return qualityMapper.selectInspectionDetail(mtlOdDetailCode);
//	}
	// 검사대기->검사중 - mtlOdStatus, mtlOdDetailStatus
	@Override
	public Map<String, Object> mtlOdStatusUpdate(InspectionVO inspectionVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = qualityMapper.updateMtlOdStatus(inspectionVO);
		
		if(result == 1) {
			isSuccessed = true;
		}
		
		map.put("result", isSuccessed);
		map.put("target", inspectionVO);
		
		return map;
	
	}
	
	@Override
	public Map<String, Object> mtlOdDetailStatusUpdate(InspectionVO inspectionVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = qualityMapper.updateMtlOdDetailStatus(inspectionVO);
		
		if(result == 1) {
			isSuccessed = true;
		}
		
		map.put("result", isSuccessed);
		map.put("target", inspectionVO);
		
		return map;
	
	
		}
	// 자재품질검사등록모달-inspection 데이터 갯수 카운트
	@Override
	public int whetherInspection(InspectionVO inspectionVO) {
		return qualityMapper.whetherInspection(inspectionVO);
	}
	// 자재품질검사등록모달-inspection 생성
	@Transactional
	@Override
	public int insertInspection(InspectionVO inspectionVO) {
		return qualityMapper.insertInspection(inspectionVO);
	}
	// 자재품질검사등록모달-inspection 데이터 출력
	@Override
	public List<InspectionVO> inspectionList(InspectionVO inspectionVO) {
		return qualityMapper.selectInspection(inspectionVO);
	}
	// 자재품질검사등록모달-insDetail 데이터 갯수 카운트
	@Override
	public int insItemCount(InspectionVO inspectionVO) {
		return qualityMapper.countInsItem(inspectionVO);
	}
	// 자재품질검사등록모달- insDetail 생성
	@Transactional
	@Override
	public int insertInsDetail(InspectionVO inspectionVO) {
		return qualityMapper.insertInsDetail(inspectionVO);
	}
	// 자재품질검사등록모달- 검사리스트 출력
	@Override
	public List<InspectionVO> testDetailSelect(InspectionVO inspectionVO) {
		return qualityMapper.selectTestDetail(inspectionVO);
	}
	@Override
	public List<InspectionVO> insDetailList(InspectionVO inspectionVO) {
		return qualityMapper.selectInsDetailList(inspectionVO);
	}
	//품질검사 등록
	@Override
	@Transactional
	public List<InspectionVO> insUpdate(List<InspectionVO> insData) {
	    for (InspectionVO inspection : insData) {
	        qualityMapper.updateIns(
	            inspection.getUserId(),
	            inspection.getUserName(),
	            inspection.getNumberOfTests(),
	            inspection.getNumberOfPasses(),
	            inspection.getNumberOfFailed(),
	            inspection.getTotalPass(),
	            inspection.getNumberOfTotalPass(),
	            inspection.getInsCode());
	        
			// mtl_od.mtl_od_status 입고품질검사완료
			qualityMapper.updateMtlOdSt(inspection);
	    }
	    return insData;
	}

	
	
	
	
	//검사기준목록
	@Override
	public List<InspectionVO> testList() {
		return qualityMapper.selectTest();
	}
	//검사기준목록
	@Override
	public List<InspectionVO> testList2() {
		return qualityMapper.selectTest2();
	}
	
	// 자재입고검사완료 조회페이지
	@Override
	public List<InspectionVO> incomingDoneInfo() {
		return qualityMapper.selectQualityDoneInfo();
	}
	// 자재입고검사완료 조회페이지 - 입고처리 버튼
	@Override
	@Transactional 
	public List<InspectionVO> mtlOdMtInUpdateInsert(List<InspectionVO> list) {
		
		for (InspectionVO inspectionVO : list) {
			// 입고검사완료페이지 - 입고처리 버튼 - mtl_od.mtl_od_status 입고완료
			qualityMapper.updateMtlOdDone(inspectionVO);
			// 입고검사완료페이지 - 입고처리 버튼 - MtInVO로 post
			//qualityMapper.selectMtIn(inspectionVO);
			// 입고검사완료페이지 - 입고처리 버튼 - mt_in으로 데이터 넣기
			qualityMapper.insertMtInInfo(inspectionVO);
		}
		return list;
	}
	// 입고검사완료페이지 - 반품 버튼 - mtl_od.mtl_od_status 반품
	@Override
	public List<InspectionVO> mtlOdBackUpdate(List<InspectionVO> list) {
	
		for (InspectionVO inspectionVO : list) {
			// 입고검사완료페이지 - 입고처리 버튼 - mtl_od.mtl_od_status 반품
			qualityMapper.updateMtlOdBack(inspectionVO);
	
		
		}
		return list;
	}

	
	
	
	
// 출고
	// 생산지시목록검색
	@Override
	public List<InspectionVO> prdSearch(String porderCode, String userName, String poStartDate, String poEndDate) {
		return qualityMapper.searchPrd(porderCode, userName, poStartDate, poEndDate);
	}
	// 완제품품질검사 대기목록1
	@Override
	public List<InspectionVO> pOrderSelect() {
		return qualityMapper.selectPOrder();
	}
	// 완제품품질검사 대기목록2
	@Override
	public List<Map<String, Object>> pDetailSelect(String porderCode) {
		return qualityMapper.selectPDetail(porderCode);
	}
	
	// 검사대기->검사중 - updatePOrderStatus, updatePDetailStatus
	@Override
	public Map<String, Object> pOrderStatusUpdate(InspectionVO inspectionVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = qualityMapper.updatePOrderStatus(inspectionVO);
		
		if(result == 1) {
			isSuccessed = true;
		}
		
		map.put("result", isSuccessed);
		map.put("target", inspectionVO);
		
		return map;
	
	}
	
	@Override
	public Map<String, Object> pDetailStatusUpdate(InspectionVO inspectionVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = qualityMapper.updatePDetailStatus(inspectionVO);
		
		if(result == 1) {
			isSuccessed = true;
		}
		
		map.put("result", isSuccessed);
		map.put("target", inspectionVO);
		
		return map;
	
	
		}
	
	// 완제품품질검사등록모달-inspection 데이터 갯수 카운트
	@Override
	public int pDtlInsCnt(InspectionVO inspectionVO) {
		return  qualityMapper.cntPDtlIns(inspectionVO);
	}
	// 완제품품질검사등록모달-inspection 생성
	@Override
	public int dtlInsInsert(InspectionVO inspectionVO) {
		return  qualityMapper.insertPDtlIns(inspectionVO);
	}
	// 완제품품질검사등록모달-inspection 데이터 출력
	@Override
	public List<InspectionVO> pDtlInsSelect(InspectionVO inspectionVO) {
		return  qualityMapper.selectPDtlIns(inspectionVO);
	}
	// 완제품품질검사등록모달-insDetail 데이터 갯수 카운트
	@Override
	public int pDInsDCount(InspectionVO inspectionVO) {
		return qualityMapper.countInsItem2(inspectionVO);
	}
	// 완제품품질검사등록모달- insDetail 생성
	@Transactional
	@Override
	public int pDtlInsDInsert(InspectionVO inspectionVO) {
		return qualityMapper.insertInsDetail(inspectionVO);
	}
	// 완제품품질검사등록모달- 검사리스트 출력
	@Override
	public List<InspectionVO> pDtlTestSelect(InspectionVO inspectionVO) {
		return qualityMapper.selectPDtlTest(inspectionVO);
	}
	@Override
	public List<InspectionVO> pDtlInsDListSelect(InspectionVO inspectionVO) {
		return qualityMapper.selectInsDetailList(inspectionVO);
	}
	//품질검사 등록
	@Override
	public List<InspectionVO> insPdUpdate(List<InspectionVO> insPdData) {
	    for (InspectionVO inspection : insPdData) {
	        qualityMapper.updateIns(
	            inspection.getUserId(),
	            inspection.getUserName(),
	            inspection.getNumberOfTests(),
	            inspection.getNumberOfPasses(),
	            inspection.getNumberOfFailed(),
	            inspection.getTotalPass(),
	            inspection.getNumberOfTotalPass(),
	            inspection.getInsCode());
	        
			// mtl_od.mtl_od_status 입고품질검사완료
			qualityMapper.updatePdSt(inspection);
			qualityMapper.updatePoSt(inspection);
	    }
	    return insPdData;
	}

	
	
	
	
	
	
	
	
	
	// 제품출고검사완료 조회
	@Override
	public List<InspectionVO> outDoneInfoSelect() {
		return qualityMapper.selectOutDoneInfo2();
	}
	
	
	
	
	
	// 제품출고검사완료 - 입고처리 버튼
	@Override
	@Transactional 
	public List<InspectionVO> upPOrdInPMan(List<InspectionVO> list) {
		
		for (InspectionVO inspectionVO : list) {
			// pd.status 입고처리
			qualityMapper.updatePOrderDone(inspectionVO);
			// pmanagement로 데이터 넣기
			qualityMapper.insertPManage(inspectionVO);
		}
		return list;
	}
	// 제품출고검사완료 - 불량 버튼 - pd.status 반품
	@Override
	public List<InspectionVO> pdBackUpdate(List<InspectionVO> list) {
	
		for (InspectionVO inspectionVO : list) {

			qualityMapper.updatePdBack(inspectionVO);
	
		
		}
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 조회 - 입고검사대기 조회페이지
	@Override
	public List<MtlOdVO> incomingWaitInfo() {
		return qualityMapper.selectQualityWaitInfo();
	}

	//	// 전체 조회 - 입고등록 페이지   
//	@Override
//	public List<InspectionVO> incomingRegistrationInfo() {
//		// TODO Auto-generated method stub
//		return qualityMapper.selectQualityRegistrationInfo();
//	}

    // 단건조회 - 입고등록 페이지
	@Override
	public InspectionVO incomingRegistrationInfo(InspectionVO inspectionVO) {
		// TODO Auto-generated method stub
		return qualityMapper.selectQualityRegistrationInfo(inspectionVO);
	}
	
	// 검사항목 - 입고등록 페이지
	@Override
	//public List<InsItemVO> incomingQualityTestInfo(MtlOdVO mtlOdVO) {
	public List<InspectionVO> incomingQualityTestInfo(MtlOdVO mtlOdVO) {
		// TODO Auto-generated method stub
		return qualityMapper.selectQualityTestInfo(mtlOdVO);
	}
	
	/*
	 * // 입고검사완료페이지(임의로 만든거) - 값 입고처리 버튼 누르면 수정
	 * 
	 * @Override public void updateIncoming(List<Map<String, Object>> items) { for
	 * (Map<String, Object> item : items) { // DB 업데이트 로직
	 * qualityMapper.updateIncoming(item); }
	 * 
	 * }
	 */
	
	/*
	 * // 입고검사완료페이지 - 값 입고처리 버튼 누르면 자재발주상태(mtl_od/ status), ins(품질검사 상태) 완료로 넘기기
	 * 
	 * @Override public Map<String, Object> inspectionDoneUpdate(InspectionVO
	 * inspectionVO) { Map<String, Object> map = new HashMap<>(); boolean
	 * isSuccessed = false;
	 * 
	 * int result = qualityMapper.updateInspectionDone(inspectionVO.getInsStatus(),
	 * inspectionVO); //+ qualityMapper.updateMtlOdDone(mtlOdStatus);
	 * 
	 * if(result == 1) { isSuccessed = true; }
	 * 
	 * map.put("result", isSuccessed); map.put("target", inspectionVO);
	 * 
	 * return map; }
	 */
	
	/*
	 * // 입고검사완료페이지 - 값 입고처리 버튼 누르면 자재발주상태(mtl_od/ status), ins(품질검사 상태) 완료로 넘기기
	 * 
	 * @Override public Map<String, Object> mtlOdDoneUpdate(InspectionVO
	 * inspectionVO) { Map<String, Object> map = new HashMap<>(); boolean
	 * isSuccessed = false;
	 * 
	 * int result = qualityMapper.updateMtlOdDone(inspectionVO.getMtlOdStatus(),
	 * inspectionVO); //+ qualityMapper.updateMtlOdDone(mtlOdStatus);
	 * 
	 * if(result == 1) { isSuccessed = true; }
	 * 
	 * map.put("result", isSuccessed); map.put("target", inspectionVO);
	 * 
	 * return map; }
	 */
	
	

	/*
	 * @Override
	 * 
	 * @Transactional 
	 * public List<InspectionVO> updateIncoming(List<InspectionVO> inspectionVOs) { List<InspectionVO> list = new ArrayList<>();
	 * 
	 * for (InspectionVO inspectionVO : inspectionVOs) {
	 * System.out.println(inspectionVO);
	 * 
	 * qualityMapper.updateInspectionDone(inspectionVO);
	 * 
	 * qualityMapper.updateMtlOdDone(inspectionVO);
	 * 
	 * list.add(inspectionVO); }
	 * 
	 * return list; }
	 */
	
	// 입고등록페이지 - 저장버튼 - inspection.ins_status 검사완료
	@Override
	@Transactional 
	public List<InspectionVO> inspectionDoneUpdate(List<InspectionVO> list) {
		//List<InspectionVO> list = new ArrayList<>();
		for (InspectionVO inspectionVO : list) {
		qualityMapper.updateInspectionDone(inspectionVO);
		//list.add(inspectionVO);
		}
		return list;
	}









	
	
	
	
//	
//	@Override
//	@Transactional
//	public List<InspectionVO> insertMtIn(List<InspectionVO> inspectionVOs) {
//		return qualityMapper.insertMtIn(inspectionVOs);
//			
//	}

}
