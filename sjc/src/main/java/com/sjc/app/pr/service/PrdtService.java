package com.sjc.app.pr.service;

import java.util.List;
import java.util.Map;

import com.sjc.app.info.service.InfoUserVO;
import com.sjc.app.sales.service.ProductVO;

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
//	public String outMt(String prdCd, int accounts, String vLineCode);
	void outMt(Map<String, Object> params);
	
	
	// 공정 시작
	void pstart(Map<String, Object> params);
	
	// 라인 매니저 화면 출력
	public List<InfoUserVO> lmanager(String ldetailCode);
	
	public List<ProductVO> productList();
	
	// 계획 생성
	public int insertPlan(CplanVO cplanVO);
	
	// 계획 삭제
	public int deletePlan(String pCode);
	
	// 가동 라인 제품
	public List<LinePrdVO> linePrdList();
	
	// 라인 제품 수량
	public List<NeedVO> orderMt(List<LinePrdVO> linePrd);
	
	// 상태 a1인 계획 코드
	public List<String> findPC();
	
	// 오더 생성
	public int insertOrd(PoVO poVO);
	
}
