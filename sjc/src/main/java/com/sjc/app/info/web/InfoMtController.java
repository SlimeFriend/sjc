package com.sjc.app.info.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.sjc.app.info.service.InfoMtService;

@Controller
public class InfoMtController {
	private InfoMtService infoMtService;
	
	@Autowired
	InfoMtController(InfoMtService infoMtService){
		this.infoMtService = infoMtService;
	}

	@GetMapping("infoMtListGridFetch")
	public String infoMtListGridFetch() {
		return "info/mtListGridFetch";
	}

}
