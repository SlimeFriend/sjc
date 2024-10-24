package com.sjc.app.eq.web;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

	// 등록 - 페이지 : URI - eqInsert / RETURN - eq/eqInsert
	@GetMapping("eqInsert")
	public String eqInsertForm() {
		return "equip/equipInsert";
	}
	
	// 등록 - 처리 : URI - eqInsert / PARAMETER - BoardVO(QueryString)=> 화면까지 설정한 것
	// RETURN - 단건조회 다시 호출
	@PostMapping("eqInsert") //처리는 무조건 Post
	public String eqInsertProcess(EqVO eqVO) { // 화면에서 <form/> 태그를 활용한 submit을 사용하겠다는 의미
		int eid = eqService.eqInsert(eqVO);

		String url = null;
		System.err.print(eqVO);
		if (eid > 0) {
			// 정상적으로 등록된 경우
//			url = "redirect:equipInfo?eqCode=" + eid;
			url = "redirect:eqList";
			// redirect: 가 가능한 경우는 GetMapping 경우밖에 없다
		} else {
			// 등록되지 않은 경우
			url = "redirect:eqInsert";
		}
		return url;
	} // end
	
	
	
	  // 수정 - 페이지 : URI - eqUpdate / PARAMETER - EqVO(QueryString) // RETURN - equip/equipUpdate
	  @GetMapping("eqUpdate") public String eqUpdateForm(EqVO eqVO, Model model) {
		  EqVO findVO = eqService.eqInfo(eqVO);
		  model.addAttribute("equip", findVO);
		  return "equip/equipUpdate"; 
	  }// end eqUpdateForm
	  
	  // 수정 - 처리 : URI - eqUpdate / PARAMETER - EqVO(JSON) // RETURN - 수정결과 데이터(Map)
	  @PostMapping("eqUpdate")
	  //@ResponseBody 
	  public Map<String, Object> eqUpdateAJAXJSON(@RequestBody EqVO eqVO) {
		  return eqService.eqUpdate(eqVO); 
		  
	  }// end eqUpdateAJAXJSON
	 	  
	 	
	// 삭제 - 처리 : URI - eqDelete / PARAMETER - Integer
	// RETURN - 전체조회 다시 호출
	@GetMapping("eqDelete") // QueryString : @RequestParam
	public String eqDelete(@RequestParam String eqCode) {
		eqService.eqDelete(eqCode);
		return "redirect:eqList"; //redirect 걸리는 건 경로를 다시 요청, 페이지 요청아님!!
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
