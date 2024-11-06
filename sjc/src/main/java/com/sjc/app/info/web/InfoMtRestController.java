package com.sjc.app.info.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sjc.app.info.service.InfoMtService;
import com.sjc.app.mt.service.MtVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InfoMtRestController {
	
	private final InfoMtService infoMtService;
	// 자재 조회
	@GetMapping("mts")
	public List<MtVO> mtList(MtVO mtVO){
		return infoMtService.mtList(mtVO);
	}
	// 자재 조회
	@GetMapping("getChartMt")
	public List<MtVO> getChartMt(){
		return infoMtService.mtStockList();
	}

}
