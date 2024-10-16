package com.sjc.app.info.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.sjc.app.info.service.InfoProcessService;

@Controller
public class InfoProcessController {
	private InfoProcessService infoProcessService;
	
	@Autowired
	InfoProcessController(InfoProcessService infoProcessService){
		this.infoProcessService = infoProcessService;
	}

	@GetMapping("infoProcessListGridFetch")
	public String infoProcessListGridFetch() {
		return "info/processListGridFetch";
	}

}
