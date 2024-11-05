package com.sjc.app.info.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Route : 사용자의 요청(endpoint)와 그에 대한 처리
// URI + METHOD => Service => View
//@RequiredArgsConstructor
public class InfoController {
	// 제품,자제,BOM 관리 페이지
	@GetMapping("infoPrdMtBomList")
	public String infoPrdMtBomList() {
		return "info/prdMtBomList";
	}
	// 제품,BOM 관리 페이지
	@GetMapping("infoPrdBomList")
	public String infoPrdBomList() {
		return "info/prdBomList";
	}
	// 라인,공정 페이지
	@GetMapping("infoLineProcessList")
	public String infoLineProcessList() {
		return "info/lineProcessList";
	}
	// 라인,공정 페이지
	@GetMapping("infoChartOcta")
	public String infoChartOcta() {
		return "info/chartOcta";
	}
}
