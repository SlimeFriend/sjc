package com.sjc.app.info.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sjc.app.info.service.InfoPrService;
import com.sjc.app.info.service.PResultDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InfoPrRestController {
	
	private final InfoPrService infoPrService;
	// 생산 조회
	@GetMapping("getChartPr")
	public List<PResultDTO> getChartPr(){
		return infoPrService.PResultInOutList();
	}
	
}
