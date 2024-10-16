package com.sjc.app.eq.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
		model.addAttribute("eq", findVO);
		return "equip/equipInfo";
	}

	// 등록 : URI - eqInsert / RETURN - eq/eqInsert
	@GetMapping("eqInsert")
	public String eqInsertForm() {
		return "equip/equipInsert";
	}

} // end of class
