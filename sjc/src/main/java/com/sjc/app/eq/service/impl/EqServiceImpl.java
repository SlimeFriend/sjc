package com.sjc.app.eq.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjc.app.eq.mapper.EqMapper;
import com.sjc.app.eq.service.EqChckVO;
import com.sjc.app.eq.service.EqService;
import com.sjc.app.eq.service.EqVO;

@Service // AOP => @Transactional
public class EqServiceImpl implements EqService {
	private EqMapper eqMapper;

	@Autowired
	EqServiceImpl(EqMapper eqMapper) {
		this.eqMapper = eqMapper;
	}

	// 전체 조회
	@Override
	public List<EqVO> eqList() {
		return eqMapper.selectEqAll();
	}

	// 등록
	@Override
	public int eqInsert(EqVO eqVO) {
		int result = eqMapper.insertEqInfo(eqVO);
		return result;
	}

	
	  // 설비 목록 수정
	  
//	  @Override 
//	  public Map<String, Object> eqUpdate(EqVO eqVO) { 
//		  
//		  Map<String, Object> map = new HashMap<>();
//	  
//	  boolean isSuccessed = false;
//	  
//	  int result = eqMapper.updateEqInfo(eqVO);
//	  
//	  if(result == 1) { isSuccessed = true; }
//	  
//	  
//	  map.put("result", isSuccessed);
//	  return map; 
//	  }
	
	  @Override
	  @Transactional
	  public Map<String, Object> eqUpdate(EqVO eqVO) { 
		  
		  Map<String, Object> map = new HashMap<>();
	  
		  boolean isSuccessed = false;
		  
		  int result = eqMapper.updateEqInfo(eqVO);
		  
		  eqMapper.updateLine(eqVO);
		  
		  if(result == 1) { isSuccessed = true; }
		  
		  map.put("result", isSuccessed);
		  
		  return map; 
	  }


	  // 설비 상세 수정
	  
	  @Override
	  public Map<String, Object> eqUpdate2(EqVO eqVO) {
		  
		  Map<String, Object> map = new HashMap<>();
	  
	  boolean isSuccessed = false;
	  
	  int result = eqMapper.updateEqInfo2(eqVO);
	  
	  if(result == 1) { isSuccessed = true; }
	  
	  
	  map.put("result", isSuccessed);
	  return map; 
	  }
	  

	// 단건 조회
	@Override
	public EqVO eqInfo(EqVO eqVO) {
		// TODO Auto-generated method stub
		return eqMapper.selectEqInfo(eqVO);
	}

	// 삭제
	@Override
	public int eqDelete(String eqCode) {
		return eqMapper.deleteEqInfo(eqCode);
	}

	// 설비 가동 상태 내역
	@Override
	public List<EqVO> eqList2() {
		return eqMapper.selectEqAll2();
	}

	// 비가동 목록 중 EqChck 속성 조회
	@Override
	public List<EqChckVO> eqChckList() {
		return eqMapper.selctEqChckAll();
	}

	// 점검 목록 조회
	@Override
	public List<EqChckVO> jgList() {
		return eqMapper.selectjumgumAll();
	}
	
	// 비가동 내역 등록
	@Override
	public void saveNonOperating(EqChckVO eqChckVO) {
		eqMapper.insertNonOperating(eqChckVO);
	}
	
	// 비가동 목록 "가동"으로 변경
	@Override
	  public Map<String, Object> updateEqChck(EqVO eqVO) {
		  
		  Map<String, Object> map = new HashMap<>();
	  
	  boolean isSuccessed = false;
	  
	  int result = eqMapper.updateEqChckInfo(eqVO);
	  
	  if(result == 1) { isSuccessed = true; }
	  
	  
	  map.put("result", isSuccessed);
	  return map; 
	}
	
	// 설비 점검 업데이트
    @Override
    @Transactional
    public void updateCheckStatus(EqChckVO eqChckVO) {
        eqMapper.updateCheckStatus(eqChckVO);
        
        EqVO eqVO = new EqVO();
        eqVO.setEqCkDate(eqChckVO.getEqCkDate());
        eqVO.setEqCode(eqChckVO.getEqCode());
        eqMapper.updateEqInfoJumgum(eqVO);
	}
	// 점검완료 버튼 업데이트    
	@Override
	    public int selectEqChckOx(EqVO eqVO) {
	    	return eqMapper.selectEqChckOx(eqVO);
	    }

	@Override
	public List<EqChckVO> eqSearch(String eqCode, String startDate, String endDate) {
		System.err.println("eqCode : " + eqCode);
		System.err.println("startDate : " + startDate);
		System.err.println("endDate : " + endDate);
		return eqMapper.eqSearch(eqCode, startDate, endDate);
	}
	
	
} // end of class
