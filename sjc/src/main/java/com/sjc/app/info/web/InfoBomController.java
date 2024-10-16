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

	@GetMapping("infoBomListGridFetch")
	public String infoBomListGridFetch() {
		return "info/bomListGridFetch";
	}

}
