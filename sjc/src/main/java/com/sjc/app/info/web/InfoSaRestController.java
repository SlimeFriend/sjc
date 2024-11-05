package com.sjc.app.info.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sjc.app.info.service.InfoSaService;
import com.sjc.app.info.service.OrdDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InfoSaRestController {
	
	private final InfoSaService infoSaService;
	// 업체 조회
	@GetMapping("getChartOrd")
	public List<OrdDTO> getChartOrd(){
		return infoSaService.getOrd();
	}
	
}
