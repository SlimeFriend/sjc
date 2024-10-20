package com.sjc.app.pr.service;

import java.util.List;

public interface PrdtService {
	// 전체 계획 조회
	public List<PlanVO> planList();
	
	// 상세 계획 조회
	public List<PlanDVO> planDetail(String planCode);
	
	// 전체 지시 조회
	public List<POrderVO> pOrderList();
	
	// 상세 지시 조회
	public List<PDetailVO> pDetail(String porderCode);
	
	// 생산 공정 실적 조회
	public List<PResultVO> pResultList();
	
	// 계획 조건 조회
	public List<PlanVO> planListS(String status);

	// 관리 리스트 조회
	public List<PDetailVO> useD();
	
	// 관리 필요자재
	public List<NeedVO> pNeed(String prdCode);
}
