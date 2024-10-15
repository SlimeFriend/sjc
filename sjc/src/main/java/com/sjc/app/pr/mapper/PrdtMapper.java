package com.sjc.app.pr.mapper;

import java.util.List;

import com.sjc.app.pr.service.PlanDVO;
import com.sjc.app.pr.service.PlanVO;

public interface PrdtMapper {
	// 계획 전체조회
	public List<PlanVO> selectPlanAll();
	
	// 계획 상세조회
	public List<PlanDVO> selectPlan();
}
