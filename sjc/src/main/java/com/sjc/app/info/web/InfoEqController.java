package com.sjc.app.info.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InfoEqController {
	// 설비 차트 페이지
	@GetMapping("infoEqChart")
	public String infoEqChart() {
		return "info/eqChart";
	}
	// 설비 차트
	@GetMapping("infoEqChartQuad")
	public String infoEqChartQuad() {
		return "info/eqChartQuad";
	}
	// 설비 차트
	@GetMapping("infoEqChartQuintuple")
	public String infoEqChartQuintuple() {
		return "info/eqChartQuintuple";
	}
}
