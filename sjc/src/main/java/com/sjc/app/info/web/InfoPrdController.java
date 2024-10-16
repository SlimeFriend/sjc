package com.sjc.app.info.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.sjc.app.info.service.InfoPrdService;
import com.sjc.app.info.service.InfoUserService;

@Controller // Route : 사용자의 요청(endpoint)와 그에 대한 처리
// URI + METHOD => Service => View
//@RequiredArgsConstructor
public class InfoPrdController {
	// 해당 컨트롤러에서 제공하는 서비스
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
