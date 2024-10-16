package com.sjc.app.info.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.sjc.app.info.service.InfoPrdService;
import com.sjc.app.info.service.InfoUserService;

@Controller
public class InfoPrdController {
	private InfoPrdService infoPrdService;
	
	@Autowired
	InfoPrdController(InfoPrdService infoPrdService){
		this.infoPrdService = infoPrdService;
	}

	@GetMapping("infoPrdListGridFetch")
	public String infoPrdListGridFetch() {
		return "info/prdListGridFetch";
	}

}
