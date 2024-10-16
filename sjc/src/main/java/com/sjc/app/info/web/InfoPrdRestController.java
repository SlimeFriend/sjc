package com.sjc.app.info.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sjc.app.info.service.InfoPrdService;
import com.sjc.app.sales.service.PrdVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InfoPrdRestController {
	
	private final InfoPrdService prdService;

	@GetMapping("prds")
	public List<PrdVO> prdList(PrdVO prdvo){
		return prdService.prdList(prdvo);
	}

}
