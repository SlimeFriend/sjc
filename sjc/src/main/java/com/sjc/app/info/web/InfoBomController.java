package com.sjc.app.info.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.sjc.app.info.service.InfoBomService;

@Controller
public class InfoBomController {
	private InfoBomService infoBomService;
	
	@Autowired
	InfoBomController(InfoBomService infoBomService){
		this.infoBomService = infoBomService;
	}

	// BOM 관리 - 테스트
	@GetMapping("infoBomListGridFetch")
	public String infoBomListGridFetch() {
		return "info/bomListGridFetch";
	}
	
	// BOM 관리
	@GetMapping("infoBomList")
	public String infoBomList() {
		return "info/bomList";
	}

	
}
