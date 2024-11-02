package com.sjc.app.info.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.sjc.app.info.service.InfoCpService;

@Controller
public class InfoCpController {
	private InfoCpService infoCpService;
	
	@Autowired
	InfoCpController(InfoCpService infoCpService){
		this.infoCpService = infoCpService;
	}
	// 업체 관리 페이지 - 테스트
	@GetMapping("infoCpListGridFetch")
	public String infoCpListGridFetch() {
		return "info/cpListGridFetch";
	}
	// 업체 관리 페이지
	@GetMapping("infoCpList")
	public String infoCpList() {
		return "info/cpList";
	}

}
