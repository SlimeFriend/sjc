package com.sjc.app.quality.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjc.app.mt.service.MtlOdVO;
import com.sjc.app.quality.mapper.QualityMapper;
import com.sjc.app.quality.service.InsItemVO;
import com.sjc.app.quality.service.InspectionVO;
import com.sjc.app.quality.service.QualityService;

@Service
public class QualityServiceImpl implements QualityService{
	private QualityMapper qualityMapper;
	
	@Autowired
	public QualityServiceImpl (QualityMapper qualityMapper) {
		this.qualityMapper = qualityMapper;
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
	public List<InsItemVO> incomingQualityTestInfo(MtlOdVO mtlOdVO) {
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
	
	
    @Override
    @Transactional
    public List<InspectionVO> updateIncoming(List<InspectionVO> inspectionVOs) {
        List<InspectionVO> list = new ArrayList<>();

        for (InspectionVO inspectionVO : inspectionVOs) {
        		System.out.println(inspectionVO);
        	
                qualityMapper.updateInspectionDone(inspectionVO);

                qualityMapper.updateMtlOdDone(inspectionVO);

                list.add(inspectionVO);
        }

        return list;
    }	

}
