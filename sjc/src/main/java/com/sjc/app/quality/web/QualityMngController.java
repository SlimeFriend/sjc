package com.sjc.app.quality.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.sjc.app.quality.service.QualityMngService;

@Controller
public class QualityMngController {
	private QualityMngService qualityMngService;
	
	@Autowired
	public QualityMngController(QualityMngService qualityMngService) {
		this.qualityMngService = qualityMngService;
	}
	
	// 품질검사 완료 조회
	@GetMapping("qualityManage")
	public String infoInsList() {
		return "quality/qualityManage";
	}
	
}
