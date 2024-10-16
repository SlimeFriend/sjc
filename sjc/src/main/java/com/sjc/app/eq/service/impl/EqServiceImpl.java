package com.sjc.app.eq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjc.app.eq.mapper.EqMapper;
import com.sjc.app.eq.service.EqService;
import com.sjc.app.eq.service.EqVO;

@Service // AOP => @Transactional
public class EqServiceImpl implements EqService {
	private EqMapper eqMapper;
	
	@Autowired
	EqServiceImpl(EqMapper eqMapper){
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
	
	// 단건 조회
	@Override
	public EqVO eqInfo(EqVO eqVO) {
		// TODO Auto-generated method stub
		return eqMapper.selectEqInfo(eqVO);
	}
	
} // end of class
