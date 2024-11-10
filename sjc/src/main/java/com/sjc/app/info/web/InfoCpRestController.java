package com.sjc.app.info.web;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public List<CpVO> insertCps(@RequestBody List<CpVO> cpVOs) {
        return infoCpService.modifyCps(cpVOs);
    }	
    // 업체 삭제
    @DeleteMapping("cps")
    public List<CpVO> deleteCps(@RequestBody List<CpVO> cpVOs) {
        return infoCpService.deleteCps(cpVOs);
    }	
    // 업체 수정
    @PostMapping("cps")
    public List<CpVO> updateCps(@RequestBody List<CpVO> cpVOs) {
    	return infoCpService.updateCps(cpVOs);
    }	
}
