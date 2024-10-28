package com.sjc.app.info.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Route : 사용자의 요청(endpoint)와 그에 대한 처리
// URI + METHOD => Service => View
//@RequiredArgsConstructor
public class InfoController {

	@GetMapping("infoPrdMtBomList")
	public String infoPrdMtBomList() {
		return "info/prdMtBomList";
	}
	
}
