package com.sjc.app.quality.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	// 입고검사완료페이지(임의로 만든거) - 값 입고처리 버튼 누르면 수정
	@Override
	public void updateIncoming(List<Map<String, Object>> items) {
        for (Map<String, Object> item : items) {
            // DB 업데이트 로직
            qualityMapper.updateIncoming(item);
        }
		
	}
	@Override
	public Map<String, Object> inspectionDoneUpdate(String insStatus) {
		Map<String, Object> map = new HashMap<>();
		return null;
	}
	@Override
	public Map<String, Object> mtlOdDoneUpdate(String mtlOdStatus) {
		Map<String, Object> map = new HashMap<>();
		return null;
	}

}
