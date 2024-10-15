package com.sjc.app.eq.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sjc.app.eq.service.EqService;
import com.sjc.app.eq.service.EqVO;

@Controller
public class EqController {
	private EqService eqService;
	
	// 전체조회 : URI - eqList / RETURN - eq/eqList
	@GetMapping("eqList")
	public String equipList(Model model) {
		List<EqVO> list = eqService.eqList();
		model.addAttribute("equips", list);
		return "equip/equipList";
	}
	
} // end of class
