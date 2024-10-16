package com.sjc.app.info.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sjc.app.info.service.LineVO;
import com.sjc.app.info.service.InfoLineService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InfoLineRestController {
	
	private final InfoLineService lineService;

	@GetMapping("lines")
	public List<LineVO> lineList(LineVO lineVO){
		return lineService.lineList(lineVO);
	}

}
