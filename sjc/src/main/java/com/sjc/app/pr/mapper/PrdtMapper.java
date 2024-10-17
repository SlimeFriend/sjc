package com.sjc.app.pr.mapper;

import java.util.List;

import com.sjc.app.pr.service.PDetailVO;
import com.sjc.app.pr.service.POrderVO;
import com.sjc.app.pr.service.PResultVO;
import com.sjc.app.pr.service.PlanDVO;
import com.sjc.app.pr.service.PlanVO;

public interface PrdtMapper {
	// 계획 전체조회
	public List<PlanVO> selectPlanAll();
	
	// 계획 상세조회
	public List<PlanDVO> selectPlan(String planCode);
	
	// 지시 전체조회
	public List<POrderVO> selectOrderAll();

	// 지시 상세조회
	public List<PDetailVO> selectOrder(String porderCode);
	
	// 공정 실적 조회
	public List<PResultVO> selectPResult();
}
