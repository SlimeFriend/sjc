package com.sjc.app.info.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sjc.app.info.service.BomVO;
import com.sjc.app.info.service.InfoBomService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InfoBomRestController {
	
	private final InfoBomService bomService;

	@GetMapping("boms")
	public List<BomVO> bomList(BomVO bomvo){
		return bomService.bomList(bomvo);
	}

}
