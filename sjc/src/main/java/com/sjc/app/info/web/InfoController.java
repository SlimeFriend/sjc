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
	
	@GetMapping("infoPrdBomList")
	public String infoPrdBomList() {
		return "info/prdBomList";
	}
	
	@GetMapping("infoPrdList")
	public String infoPrdList() {
		return "info/prdList";
	}
	
	@GetMapping("infoLineProcessList")
	public String infoLineProcessList() {
		return "info/lineProcessList";
	}
	
}
