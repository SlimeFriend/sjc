package com.sjc.app.info.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sjc.app.info.service.InfoCpService;
import com.sjc.app.sales.service.CpVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InfoCpRestController {
	
	private final InfoCpService infoCpService;

	@GetMapping("cps")
	public List<CpVO> cpList(CpVO cpVO){
		return infoCpService.cpList(cpVO);
	}

}
