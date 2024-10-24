package com.sjc.app.pr.mapper;

import java.util.List;
import java.util.Map;

import com.sjc.app.pr.service.NeedVO;
import com.sjc.app.pr.service.PDetailVO;
import com.sjc.app.pr.service.POrderVO;
import com.sjc.app.pr.service.PResultVO;
import com.sjc.app.pr.service.PlanDVO;
import com.sjc.app.pr.service.PlanVO;
import com.sjc.app.pr.service.PrcVO;

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
	
	// 계획 조건 검색
	public List<PlanVO> selectPlanS(String status);
	
	// 관리 리스트 조회
	public List<PDetailVO> useD();
	
	// 관리 필요 자재
	public List<NeedVO> pNeed(String prdCode);
	
	// 관리 공정
	public List<PrcVO> pPrc(String lineCode);
	
	// 실적 생성
	public int insertR(PResultVO pvo);
	
	// 지시에 있는지 실적 조회
	public List<PResultVO> searchR(String pdetailCode);
	
	// 공정 자재 가져오기
	void outMt(String mc, int needs, int mng, String lcode);
}
