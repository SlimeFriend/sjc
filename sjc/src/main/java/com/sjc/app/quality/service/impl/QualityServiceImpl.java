package com.sjc.app.quality.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjc.app.mt.service.MtlOdVO;
import com.sjc.app.quality.mapper.QualityMapper;
import com.sjc.app.quality.service.InspectionVO;
import com.sjc.app.quality.service.QualityService;

@Service
public class QualityServiceImpl implements QualityService{
	private QualityMapper qualityMapper;
	
	@Autowired
	public QualityServiceImpl (QualityMapper qualityMapper) {
		this.qualityMapper = qualityMapper;
	}
	
	// 발주목록전체
	@Override
	public List<InspectionVO> mtlOdList() {
		return qualityMapper.selectMtlOd();
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
	
	// 품질검사상세페이지.
	@Transactional
	@Override
	public int insertInspection(InspectionVO inspectionVO) {
		return qualityMapper.insertInspection(inspectionVO);
	}
	
	// 입고품질검사 상세목록 /
	@Override
	public List<Map<String, Object>> incomingTestList(String mtlOdDetailCode) {
		return qualityMapper.selectIncomingTest(mtlOdDetailCode);
	}
	
	//검사기준목록
	@Override
	public List<InspectionVO> testList() {
		return qualityMapper.selectTest();
	}
	//검사목록
	@Override
	public List<InspectionVO> inspectionList(InspectionVO inspectionVO) {
		return qualityMapper.selectInspection(inspectionVO);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 조회 - 입고검사대기 조회페이지
	@Override
	public List<MtlOdVO> incomingWaitInfo() {
		return qualityMapper.selectQualityWaitInfo();
	}
	
	// 조회 - 입고검사완료 조회페이지
	@Override
	public List<InspectionVO> incomingDoneInfo() {
		// TODO Auto-generated method stub
		return qualityMapper.selectQualityDoneInfo();
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
	// 입고검사완료페이지 - 입고처리 버튼 - mtl_od.mtl_od_status 입고품질검사완료
	@Override
	@Transactional 
	public List<InspectionVO> mtlOdMtInUpdateInsert(List<InspectionVO> list) {
	
		for (InspectionVO inspectionVO : list) {
			// 입고검사완료페이지 - 입고처리 버튼 - mtl_od.mtl_od_status 입고품질검사완료
			qualityMapper.updateMtlOdDone(inspectionVO);
			// 입고검사완료페이지 - 입고처리 버튼 - MtInVO로 post
			qualityMapper.selectMtIn(inspectionVO);
			// 입고검사완료페이지 - 입고처리 버튼 - mt_in으로 데이터 넣기
			qualityMapper.insertMtInInfo(inspectionVO);
		
			
		}
			return list;
	
	}
	// 입고검사완료페이지 - 입고처리 버튼 - mtl_od.mtl_od_status 반품
	@Override
	public List<InspectionVO> mtlOdBackUpdate(List<InspectionVO> list) {
	
		for (InspectionVO inspectionVO : list) {
			// 입고검사완료페이지 - 입고처리 버튼 - mtl_od.mtl_od_status 반품
			qualityMapper.updateMtlOdBack(inspectionVO);
	
		
		}
		return list;
	}
//	// 입고검사완료페이지 - 입고처리 버튼 - MtInVO로 post
//	@Override
//	public List<InspectionVO> mtInSelect(List<InspectionVO> inspectionVOs) {
//		List<InspectionVO> list = new ArrayList<>();
//		for (InspectionVO inspectionVO : inspectionVOs) {
//		qualityMapper.selectMtIn(inspectionVO);
//		list.add(inspectionVO);
//		
//	}
//		return list;
//	}




	
	
	
	
//	
//	@Override
//	@Transactional
//	public List<InspectionVO> insertMtIn(List<InspectionVO> inspectionVOs) {
//		return qualityMapper.insertMtIn(inspectionVOs);
//			
//	}

}
