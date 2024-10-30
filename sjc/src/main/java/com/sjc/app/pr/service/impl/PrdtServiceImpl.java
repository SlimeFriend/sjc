package com.sjc.app.pr.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjc.app.info.service.InfoUserVO;
import com.sjc.app.pr.mapper.PrdtMapper;
import com.sjc.app.pr.service.CplanVO;
import com.sjc.app.pr.service.NeedVO;
import com.sjc.app.pr.service.PDetailVO;
import com.sjc.app.pr.service.POrderVO;
import com.sjc.app.pr.service.PResultVO;
import com.sjc.app.pr.service.PlanDVO;
import com.sjc.app.pr.service.PlanVO;
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

}
