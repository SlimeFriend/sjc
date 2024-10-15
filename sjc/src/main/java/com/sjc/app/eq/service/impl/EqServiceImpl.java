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
	
	@Override
	public List<EqVO> eqList() {
		return eqMapper.selectEqAll();
	}
	
} // end of class
