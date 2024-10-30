package com.sjc.app.info.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.sjc.app.info.service.InfoLineService;

@Controller
public class InfoLineController {
	private InfoLineService infoLineService;
	
	@Autowired
	InfoLineController(InfoLineService infoLineService){
		this.infoLineService = infoLineService;
	}

	@GetMapping("infoLineListGridFetch")
	public String infoLineListGridFetch() {
		return "info/lineListGridFetch";
	}
	
	@GetMapping("infoLineList")
	public String infoLineList() {
		return "info/lineList";
	}

}
