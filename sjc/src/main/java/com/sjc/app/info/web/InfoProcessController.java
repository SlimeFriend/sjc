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
	// 공정 관리 페이지 - 테스트
	@GetMapping("infoProcessListGridFetch")
	public String infoProcessListGridFetch() {
		return "info/processListGridFetch";
	}
	// 공정 관리 페이지
	@GetMapping("infoProcessList")
	public String infoProcessList() {
		return "info/processList";
	}

}
