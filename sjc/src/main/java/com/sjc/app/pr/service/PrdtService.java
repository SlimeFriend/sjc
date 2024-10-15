package com.sjc.app.pr.service;

import java.util.List;

public interface PrdtService {
	// 전체 계획 조회
	public List<PlanVO> planList();
	
	// 상세 계획 조회
	public List<PlanDVO> planDetail();
}
