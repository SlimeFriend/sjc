package com.sjc.app.info.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sjc.app.info.service.InfoQaService;
import com.sjc.app.info.service.InspectionDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InfoQaRestController {
	
	private final InfoQaService infoQaService;
	// 생산 조회
	@GetMapping("getChartQa")
	public List<InspectionDTO> getChartPr(){
		return infoQaService.qaPassRateList();
	}
	
}
