package com.sjc.app.info.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sjc.app.info.service.InfoCpService;
import com.sjc.app.sales.service.CpVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InfoCpRestController {
	
	private final InfoCpService infoCpService;
	// 업체 조회
	@GetMapping("cps")
	public List<CpVO> cpList(CpVO cpVO){
		return infoCpService.cpList(cpVO);
	}
	// 업체 등록
    @PutMapping("cps")
    public List<CpVO> updateUsers(@RequestBody List<CpVO> cpVOs) {
        return infoCpService.modifyCps(cpVOs);
    }	
	
}
