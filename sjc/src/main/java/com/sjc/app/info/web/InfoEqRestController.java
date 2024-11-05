package com.sjc.app.info.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sjc.app.info.service.ChartDataVO;
import com.sjc.app.info.service.EqDTO;
import com.sjc.app.info.service.InfoEqService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InfoEqRestController {
	private final InfoEqService infoEqService;
	// 온도 테스트용
    @GetMapping("/getTemperature")
    public String getTemperature() {
    	infoEqService.getTemperature();
    	return "getTemperature()";
    }
    // 차트 테스트용
    @GetMapping("/getChartData")
    public ChartDataVO getChartData() {
    	return infoEqService.getChartData();
    }
    // 설비 온도 조회
    @GetMapping("/getEqLog")
    public ChartDataVO getEqLog() {
    	return infoEqService.getEqLog();
    }
	// 설비 조회
	@GetMapping("getChartEq")
	public List<EqDTO> getChartEq(){
		return infoEqService.getEq();
	}
	// 설비 가동율 조회
	@GetMapping("getChartEqChck")
	public List<EqDTO> getChartEqChck(){
		return infoEqService.getEqChck();
	}    
}
