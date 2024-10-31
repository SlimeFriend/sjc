package com.sjc.app.pr.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjc.app.info.service.InfoUserVO;
import com.sjc.app.pr.mapper.PrdtMapper;
import com.sjc.app.pr.service.CplanVO;
import com.sjc.app.pr.service.LinePrdVO;
import com.sjc.app.pr.service.NeedVO;
import com.sjc.app.pr.service.PDetailVO;
import com.sjc.app.pr.service.POrderVO;
import com.sjc.app.pr.service.PResultVO;
import com.sjc.app.pr.service.PlanDVO;
import com.sjc.app.pr.service.PlanVO;
import com.sjc.app.pr.service.PoVO;
import com.sjc.app.pr.service.PrcVO;
import com.sjc.app.pr.service.PrdtService;
import com.sjc.app.sales.service.ProductVO;

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

	@Override
	public List<PrcVO> pPrc(String lindCode) {
		// TODO Auto-generated method stub
		return prdtMapper.pPrc(lindCode);
	}

	@Override
	public String insertR(PResultVO pvo) {
		if(prdtMapper.insertR(pvo) == 1) {
			return "success";
		}
		else {
			return "fail";
		}
	}

	@Override
	public List<PResultVO> searchR(String pdetailCode) {
		// TODO Auto-generated method stub
		return prdtMapper.searchR(pdetailCode);
	}

//	@Override
//	public String outMt(String prdCd, int accounts, String vLineCode) {
//		String res = "";
//		
//		prdtMapper.outMt(prdCd, accounts, vLineCode, res);
//		
//		return res;
//		
//	}
    @Override
    public void outMt(Map<String, Object> params) {
        prdtMapper.outMt(params);
    }
    
    
    @Override
    public void pstart(Map<String, Object> params) {
        prdtMapper.pstart(params);
    }

	@Override
	public List<InfoUserVO> lmanager(String ldetailCode) {
		// TODO Auto-generated method stub
		return prdtMapper.lmanager(ldetailCode);
	}

	@Override
	public List<ProductVO> productList() {
		// TODO Auto-generated method stub
		return prdtMapper.productList();
	}

	@Override
	public int insertPlan(CplanVO cplanVO) {
		
		// 생산계획 코드
		String nextId = prdtMapper.getPlanCode();
		String planCode = String.valueOf(nextId);
		
		// 생산계획 
		PlanVO planVO = cplanVO.getPlanVO();
		planVO.setPlanCode(planCode);
		int pinresult = prdtMapper.insertPlan(planVO);
		
		
		List<ProductVO> productVOList = cplanVO.getProductVO(); 
	    if(pinresult > 0) {
	        productVOList.forEach(productVO -> {
	        	prdtMapper.insertPlanDetail(productVO, planVO.getPlanCode());
	        });
	    }
		
		
		
		return 1;
	}

	@Override
	public int deletePlan(String pCode) {
		int drs = prdtMapper.deletePlanD(pCode);
		int rs = prdtMapper.deletePlan(pCode);
		
		if(drs > 0 && rs> 0 ) {
			return 1;
		}
		
		return 0;
	}

	@Override
	public List<LinePrdVO> linePrdList() {
		return prdtMapper.linePrdList();
	}

	@Override
	public List<NeedVO> orderMt(List<LinePrdVO> linePrd) {
	    Map<String, NeedVO> needsMap = new HashMap<>(); // 자재 코드를 키로 사용
	    List<NeedVO> needs = new ArrayList<>(); // 최종 결과 리스트

	    linePrd.forEach(lpd -> {
	        prdtMapper.orderMt(lpd).forEach(need -> {
	            String code = need.getMtCode(); // NeedVO에서 자재 코드 가져오기

	            if (needsMap.containsKey(code)) {
	                // 이미 존재하는 자재이면 수량 합산
	                NeedVO existingNeed = needsMap.get(code);
	                existingNeed.setQuantityRequired(existingNeed.getQuantityRequired() + need.getQuantityRequired());
	            } else {
	                // 새 자재이면 맵에 추가
	                needsMap.put(code, need);
	            }
	        });
	    });

	    // 맵의 값을 리스트로 변환
	    needs.addAll(needsMap.values());

	    return needs;
	}

	@Override
	public List<String> findPC() {
		
		return prdtMapper.findPC();
	}

	@Override
	public int insertOrd(PoVO poVO) {

		// 생산계획 코드
		String nextId = prdtMapper.getOrdCode();
		String ordCode = String.valueOf(nextId);
				
		// 생산계획 
		POrderVO porderVO = poVO.getPorderVO();
		porderVO.setPorderCode(ordCode);
		int pinresult = prdtMapper.insertOrd(porderVO);
				
				
		List<LinePrdVO> linePrdList = poVO.getLinePrdVO(); 
		if(pinresult > 0) {
			linePrdList.forEach(lp -> {
			     prdtMapper.insertDetail(lp, porderVO.getPorderCode());
			     });
			        
			        
			return 1;
		}
		return 0;

	}

	@Override
	public List<PlanDVO> planPrd(String planCode) {
		return prdtMapper.planPrd(planCode);
	}
}
