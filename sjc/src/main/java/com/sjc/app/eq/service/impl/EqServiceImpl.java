package com.sjc.app.eq.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	/*
	 * // 수정
	 * 
	 * @Override public Map<String, Object> eqUpdate(EqVO eqVO) { Map<String,
	 * Object> map = new HashMap<>();
	 * 
	 * boolean isSuccessed = false;
	 * 
	 * int result = eqMapper.updateEqInfo(eqVO);
	 * 
	 * if(result == 1) { isSuccessed = true; }
	 * 
	 * String updateDate = getUpdateDate(); // 내부 메소드를 사용
	 * 
	 * map.put("date", updateDate); map.put("result", isSuccessed);
	 * map.put("target", eqVO); return map; }
	 * 
	 * private String getUpdateDate() { // 날짜를 다루는 클래스 > now() : 현재 시점 // format 때문에
	 * 사용한다. LocalDate today = LocalDate.now(); String updateDt =
	 * today.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")); // 자신이 가지고 있는 날짜에
	 * 대해선 출력하고자 하는 포멧을 결정하는것. return updateDt; }
	 */

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



} // end of class
