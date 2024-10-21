package com.sjc.app.pr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjc.app.pr.mapper.PrdtMapper;
import com.sjc.app.pr.service.NeedVO;
import com.sjc.app.pr.service.PDetailVO;
import com.sjc.app.pr.service.POrderVO;
import com.sjc.app.pr.service.PResultVO;
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
	public List<PlanDVO> planDetail(String planCode) {
		return prdtMapper.selectPlan(planCode);
	}

	@Override
	public List<POrderVO> pOrderList() {
		
		return prdtMapper.selectOrderAll();
	}

	@Override
	public List<PDetailVO> pDetail(String porderCode) {
		return prdtMapper.selectOrder(porderCode);
	}

	@Override
	public List<PResultVO> pResultList() {
		// TODO Auto-generated method stub
		return prdtMapper.selectPResult();
	}

	@Override
	public List<PlanVO> planListS(String status) {
		// TODO Auto-generated method stub
		return prdtMapper.selectPlanS(status);
	}

	@Override
	public List<PDetailVO> useD() {

		return prdtMapper.useD();
	}

	@Override
	public List<NeedVO> pNeed(String prdCode) {
		return prdtMapper.pNeed(prdCode);
	}

}
