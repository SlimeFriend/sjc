package com.sjc.app.pr.service;

import java.util.List;
import java.util.Map;

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
	
	// 관리 공정
	public List<PrcVO> pPrc(String lindCode);
	
	// 공정 실적(진행) 생성
	public String insertR(PResultVO pvo);
	
	// 생산 실적 확인
	public List<PResultVO> searchR(String pdetailCode);
	
	// 공정 자재 출고
	void outMt(String mc, int needs, int mng, String lcode);
}
