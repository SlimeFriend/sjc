package com.sjc.app.eq.web;

import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sjc.app.eq.service.EqChckVO;
import com.sjc.app.eq.service.EqService;
import com.sjc.app.eq.service.EqVO;


@Controller
public class EqController {
	private EqService eqService;

	public EqController(EqService eqService) {
		this.eqService = eqService;
	}

	// 전체조회 : URI - eqList / RETURN - eq/eqList
	@GetMapping("eqList")
	public String equipList(Model model) {
		List<EqVO> list = eqService.eqList();
		model.addAttribute("equips", list);
		
		return "equip/equipList";
	}
	
	// 단건조회 : URI - eqInfo / PARAMETER - EqVO(QueryString)
    //          RETURN - equip/eqInfo
	// QueryString
	// 1) 객체 : 커맨드 객체
	// 2) 단일값 : @ReqeustParam
	@GetMapping("eqInfo")
	public String eqInfo(EqVO eqVO, Model model) {
		EqVO findVO = eqService.eqInfo(eqVO);
		model.addAttribute("equip", findVO);
		return "equip/equipInfo";
	}

	// 등록 : URI - eqInsert / RETURN - eq/eqInsert
	@GetMapping("eqInsert")
	public String eqInsertForm() {
		return "equip/equipInsert";
	}
	
	// 비가동 설비 목록 전체조회 : URI - eqList / RETURN - eq/eqList
	@GetMapping("eqChckList")
	public String eqChckList(Model model) {
		List<EqVO> list = eqService.eqList2();
		model.addAttribute("equips", list);
		
		List<EqChckVO> list2 = eqService.eqChckList();
		model.addAttribute("equipChck", list2);
		return "equip/eqChckList";
	}
	
	// 설비 점검 목록 조회
	@GetMapping("jgList")
	public String jgList(Model model) {
		List<EqChckVO> list = eqService.jgList();
		model.addAttribute("equipjg", list);
		
		return "equip/eqJumgum";
	}

} // end of class
