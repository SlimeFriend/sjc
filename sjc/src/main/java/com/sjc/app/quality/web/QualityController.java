package com.sjc.app.quality.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sjc.app.mt.service.MtlOdVO;
import com.sjc.app.quality.service.InspectionVO;
import com.sjc.app.quality.service.QualityService;

@Controller
public class QualityController {
    private QualityService qualityService;
    @Autowired
    public QualityController(QualityService qualityService) {
    	this.qualityService = qualityService;
    }
    @GetMapping("incomingQualityWaitInfo")
    public String incomingWaitInfo(MtlOdVO mtlOdVO, Model model) {
    	List<MtlOdVO> list = qualityService.incomingWaitInfo();
    	model.addAttribute("incomingQualityWaits", list);
		return "/quality/incomingQualityWait";
    }
    
    @GetMapping("incomingQualityRegistrationInfo")
    public String incomingRegistrationInfo(InspectionVO inspectionVO, Model model) {
    	List<InspectionVO> list = qualityService.incomingRegistrationInfo();
    	model.addAttribute("incomingQualityRegistrationList", list);
		return "/quality/incomingQualityRegistration";
    }
}
