package com.sjc.app.pr.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sjc.app.pr.service.PlanVO;
import com.sjc.app.pr.service.PrdtService;

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
	
	
	
	
}
