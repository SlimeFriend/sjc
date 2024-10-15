package com.sjc.app.pr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjc.app.pr.mapper.PrdtMapper;
import com.sjc.app.pr.service.PlanDVO;
import com.sjc.app.pr.service.PlanVO;
import com.sjc.app.pr.service.PrdtService;

@Service
public class PrdtServiceImpl implements PrdtService {
	private PrdtMapper prdtMapper;
	
	
	@Autowired 
	public PrdtServiceImpl(PrdtMapper prdtMapper) {
		this.prdtMapper = prdtMapper;
	}

	@Override
	public List<PlanVO> planList() {
		return prdtMapper.selectPlanAll();
	}
	
	@Override
	public List<PlanDVO> planDetail() {
		return prdtMapper.selectPlan();
	}

}
