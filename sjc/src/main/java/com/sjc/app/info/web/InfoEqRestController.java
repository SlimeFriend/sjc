package com.sjc.app.info.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sjc.app.info.service.ChartDataVO;
import com.sjc.app.info.service.InfoEqService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InfoEqRestController {
	private final InfoEqService infoEqService;
	
    @GetMapping("/getTemperature")
    public String getTemperature() {
    	infoEqService.getTemperature();
    	return "getTemperature()";
    }
    
    @GetMapping("/getChartData")
    public ChartDataVO getChartData() {
    	return infoEqService.getChartData();
    }
    
    @GetMapping("/getEqLog")
    public ChartDataVO getEqLog() {
    	return infoEqService.getEqLog();
    }
}
