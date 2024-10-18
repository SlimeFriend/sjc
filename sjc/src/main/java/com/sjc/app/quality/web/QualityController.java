package com.sjc.app.quality.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sjc.app.mt.service.MtlOdVO;
import com.sjc.app.quality.service.InsItemVO;
import com.sjc.app.quality.service.InspectionVO;
import com.sjc.app.quality.service.QualityService;

@Controller
public class QualityController {
    private QualityService qualityService;
    @Autowired
    public QualityController(QualityService qualityService) {
    	this.qualityService = qualityService;
    }

	// 조회 - 입고대기 조회페이지
    @GetMapping("incomingQualityWaitInfo")
    public String incomingWaitInfo(MtlOdVO mtlOdVO, Model model) {
    	List<MtlOdVO> list = qualityService.incomingWaitInfo();
    	model.addAttribute("incomingQualityWaits", list);
		return "quality/incomingQualityWait";
    }
    
    
    // 조회 - 입고대기 조회페이지
    @GetMapping("incomingQualityDoneInfo")
    public String incomingQualityDone(MtlOdVO mtlOdVO, Model model) {
    	List<InspectionVO> list = qualityService.incomingDoneInfo();
    	model.addAttribute("incomingQualityDones", list);
    	return "quality/incomingQualityDone";
    }
    
	// 전체 조회 - 입고등록 페이지    
    @GetMapping("incomingQualityRegistrationInfo")
    public String incomingRegistrationInfo(InspectionVO inspectionVO, Model model) {
    	InspectionVO findVO = qualityService.incomingRegistrationInfo(inspectionVO);
    	model.addAttribute("incomingQualityRegistrationList", findVO);
    	
    	String mtlOdCode = findVO.getMtlOdCode();
    	MtlOdVO mtlOdVO = new MtlOdVO();
    	mtlOdVO.setMtlOdCode(mtlOdCode);
    	// 단건조회 - 입고등록 페이지
    	List<InsItemVO> insItemlist = qualityService.incomingQualityTestInfo(mtlOdVO);
    	model.addAttribute("testList", insItemlist);
    	
    	return "quality/incomingQualityRegistration";
    }
    
	/*
	 * // 단건조회 - 입고등록 페이지
	 * 
	 * @GetMapping("incomingQualityRegistrationInfo") public String
	 * incomingRegistrationInfo(MtlOdVO mtlOdVO, Model model) { List<MtlOdVO> list =
	 * qualityService.incomingQualityTestInfo(mtlOdVO);
	 * model.addAttribute("testList", list); return
	 * "quality/incomingQualityRegistration"; }
	 */
    
    
}
