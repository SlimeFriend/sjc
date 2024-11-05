package com.sjc.app.pr.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sjc.app.info.service.InfoUserVO;
import com.sjc.app.pr.service.LinePrdVO;
import com.sjc.app.pr.service.NeedVO;
import com.sjc.app.pr.service.PDetailVO;
import com.sjc.app.pr.service.POrderVO;
import com.sjc.app.pr.service.PResultVO;
import com.sjc.app.pr.service.PlanDVO;
import com.sjc.app.pr.service.PlanVO;
import com.sjc.app.pr.service.PrcVO;
import com.sjc.app.sales.service.ProductVO;

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
//	public String outMt(String prdCd, int accounts, String vLineCode, String res);
	void outMt(Map<String, Object> params);
	
	// 공정시작
	void pstart(Map<String, Object> params);
	
	public List<InfoUserVO> lmanager(String ldetailCode);
	
	// 제품리스트
	public List<ProductVO> productList();
	
	// 계획 생성을 위한 코드 생성
	public String getPlanCode();
	
	// 계획 생성
	public int insertPlan(PlanVO planVO);
	
	// 계획 상세 생성
	public int insertPlanDetail(@Param("productVO") ProductVO productVO,@Param("planCode") String planCode);
	
	// 계획 상세 삭제
	public int deletePlanD(String pCode);
	
	// 계획 삭제
	public int deletePlan(String pCode);
	
	// 가동 라인 제품
	public List<LinePrdVO> linePrdList();
	
	// 지시 자재
	public List<NeedVO> orderMt(LinePrdVO linePrdVO);
	
	// 계획코드 
	public List<String> findPC();
	
	// 지시 생성 위한 코드
	public String getOrdCode();
	
	public int insertOrd(POrderVO porderVO);
	
	public int insertDetail(@Param("linePrdVO") LinePrdVO linePrdVO,@Param("porderCode") String porderCode);
	
	public List<PlanDVO> planPrd(String planCode);
	
	// 생산 계획에 표시할 주문코드
	public List<String> oList();
	
	// 주문코드 선택 시 나올 주문 제품 
	public List<ProductVO> ordPrd(String ordCode);
	
	// 계획 클릭 시 나올 라인
	public List<LinePrdVO> planL(String planCode);
	
	// 지시내리면 라인 사용 중 변경
	void updateLine(LinePrdVO linePrdVO);
	
	// 계획 검색
	public List<PlanVO> searchPlan(String planCode, String startDate, String endDate, String status);
	
	// 지시 검색
	public List<POrderVO> searchOrders(String porderCode, String startDate, String endDate, String status);
	
	// 삭제할 지시의 상세코드 찾기
	public List<String> findD(String porderCode);
	
	// 생산 공정 실적 삭제
	public int deleteResult(String pdetailCode);
	
	// 생산 지시 상세 삭제
	public int deleteDetail(String porderCode);
	
	// 생산 지시 삭제
	public int deleteOrder(String porderCode);
	
	// 생산 지시에 연결된 계획 코드
	public String searchOrderPlan(String porderCode);
	
	// 계획코드가 가진 생산지시수
	public int countOrder(String planCode);
	
	// 계획코드 상태 변경
	public int updatePlanS(String planCode);
}
