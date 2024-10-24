package com.sjc.app.pr.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sjc.app.pr.service.NeedVO;
import com.sjc.app.pr.service.PDetailVO;
import com.sjc.app.pr.service.POrderVO;
import com.sjc.app.pr.service.PResultVO;
import com.sjc.app.pr.service.PlanDVO;
import com.sjc.app.pr.service.PlanVO;
import com.sjc.app.pr.service.PrcVO;
import com.sjc.app.pr.service.PrdtService;

/**
 * 개발자 조수호     
 * 생산 관리 
 */
@Controller
public class PrController {
	
	private PrdtService prdtService;
	
	@Autowired
	public PrController(PrdtService prdtService) {
		this.prdtService = prdtService;
	}
	
	// 계획 전체 조회
	@GetMapping("planList")
	public String planList(Model model) {
		List<PlanVO> list = prdtService.planList();
		
		model.addAttribute("list", list);
		
		return "pr/planList";
	}
	
	// 계획 조건 조회
	@GetMapping("planListS")
	@ResponseBody
	public List<PlanVO> planListS(@RequestParam String status) {
		return  prdtService.planListS(status);
	
	}
	
	// 계획 생성 Get
	@GetMapping("planCreate")
	public String planCreatePage(Model model) {
		return "pr/planCreate";
	}
	
	// 계획 생성 Post
	@PostMapping("planCreate")
	public int planCreate(Model model) {
		return 0;
	}
	
	// 계획 상세 조회
	@GetMapping("planDList")
	@ResponseBody
	public List<PlanDVO> planDList(@RequestParam String planCode) {
		
		return prdtService.planDetail(planCode);
	}
	
	// 지시 전체 조회
	@GetMapping("pOrderList")
	public String pOrderList(Model model) {
		List<POrderVO> list = prdtService.pOrderList();
		
		model.addAttribute("list", list);
		
		
		return "pr/pOrderList";
	}
	
	// 지시 페이지
	@GetMapping("pOrder")
	public String pOrder(Model model) {

		return "pr/pOrder";
	}
	
	// 생산 공정 실적
	@GetMapping("pResult")
	public String pResult(Model model) {
		List<PResultVO> list = prdtService.pResultList();
		
		model.addAttribute("list", list);
		
		return "pr/pResult";
	}
	
	// 생산 관리 페이지
	@GetMapping("pManage")
	public String pManage(Model model) {
		List<PDetailVO> list = prdtService.useD();
		
		model.addAttribute("list", list);
		
		return "pr/pManage";
	}
	
	// 지시 상세 조회
	@GetMapping("pOList")
	@ResponseBody
	public List<PDetailVO> pOList(@RequestParam String porderCode) {
		
		return prdtService.pDetail(porderCode);
	}
	
	// 생산 관리 필요 자재수
	@GetMapping("pNeed")
	@ResponseBody
	public List<NeedVO> pNeed(@RequestParam String prdCode) {
		
		return prdtService.pNeed(prdCode);
	}
	
	// 생산 관리 공정 흐름도
	@GetMapping("pPrc")
	@ResponseBody
	public List<PrcVO> pPrc(@RequestParam String lineCode) {
		
		return prdtService.pPrc(lineCode);
	}
	
	// 생산 실적(진행) 있는지 확인
	@GetMapping("searchR")
	@ResponseBody
	public List<PResultVO> searchR(@RequestParam String pdetailCode) {
		
		return prdtService.searchR(pdetailCode);
	}
	
	// 생산 실적 최초 생성
	@GetMapping("insertR")
	@ResponseBody
	public List<PResultVO> insertR(PResultVO pvo) {
		
		prdtService.insertR(pvo);
		
		return prdtService.searchR(pvo.getPdetailCode());
	}
		
	
	
	// 생산 공정 자재 가져오기
	@PostMapping("outMt")
	public String outMt(@RequestBody Map<String, Object> request) {
		
		 String prdCode = (String)request.get("prdCode");
	     int account = (Integer)request.get("account");
		
		
		
	     return "dtd";
	}

	
}
