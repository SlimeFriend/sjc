package com.sjc.app.eq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjc.app.eq.mapper.EqChckMapper;
import com.sjc.app.eq.service.EqChckService;
import com.sjc.app.eq.service.EqChckVO;

@Service
public class EqChckServiceImpl implements EqChckService {
	private EqChckMapper eqChckMapper;

	@Autowired
	EqChckServiceImpl(EqChckMapper eqChckMapper) {
		this.eqChckMapper = eqChckMapper;
	}

	// 전체조회
	@Override
	public List<EqChckVO> eqChckList() {
		return eqChckMapper.selctEqChckAll();
	}

} // end of class
