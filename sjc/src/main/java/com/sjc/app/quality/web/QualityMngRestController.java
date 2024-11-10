package com.sjc.app.quality.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sjc.app.quality.service.InspectionVO;
import com.sjc.app.quality.service.QualityMngService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class QualityMngRestController {
	
	private final QualityMngService qualityMngService;
	
	// 품질검사 조회
    @GetMapping("/inss")
    public List<InspectionVO> insList(InspectionVO inspectionVO){
        return qualityMngService.insSelect(inspectionVO);
    }
    
	// 검사 완료 필터링 ( 전체, MTD, PD )
	@GetMapping("/insRadio")
	public List<InspectionVO> insRadio(String rad) {
		return qualityMngService.insRadio(rad);
	}


}
