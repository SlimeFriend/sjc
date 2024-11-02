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
	// 자제 관리 페이지 - 테스트
	@GetMapping("infoMtListGridFetch")
	public String infoMtListGridFetch() {
		return "info/mtListGridFetch";
	}
	// 자재 관리 페이지
	@GetMapping("infoMtList")
	public String infoMtList() {
		return "info/mtList";
	}

}
