package com.sjc.app.info.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sjc.app.info.service.ProcessVO;
import com.sjc.app.info.service.InfoProcessService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InfoProcessRestController {
	
	private final InfoProcessService processService;
	// 공정 조회
	@GetMapping("processes")
	public List<ProcessVO> processList(ProcessVO processVO){
		return processService.processList(processVO);
	}

}
