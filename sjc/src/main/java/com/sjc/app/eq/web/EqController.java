package com.sjc.app.eq.web;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	  
	  // 목록 수정 - 처리 : URI - eqUpdate / PARAMETER - EqVO(JSON) // RETURN - 수정결과 데이터(Map)
	  @PostMapping("eqUpdate")
	  @ResponseBody 
	  public Map<String, Object> eqUpdateAJAXJSON(@RequestBody EqVO eqVO) {
		  return eqService.eqUpdate(eqVO); 
		  
	  }// end eqUpdateAJAXJSON
	  
	  
	  // 상세 수정 - 처리 : URI - eqUpdate2 / PARAMETER - EqVO(JSON) // RETURN - 수정결과 데이터(Map)
	  @PostMapping("eqUpdate2")
	  @ResponseBody 
	  public Map<String, Object> eqUpdate2AJAXJSON(@RequestBody EqVO eqVO) {
		// 필요한 필드만 업데이트하는 로직으로 수정
		    EqVO targetEqVO = new EqVO();
		    targetEqVO.setEqCode(eqVO.getEqCode());
		    targetEqVO.setEqMdnm(eqVO.getEqMdnm());
		    targetEqVO.setEqMdno(eqVO.getEqMdno());
		    targetEqVO.setEqPeriod(eqVO.getEqPeriod());
		    targetEqVO.setEqLocation(eqVO.getEqLocation());
		    targetEqVO.setLineCode(eqVO.getLineCode());
		    targetEqVO.setManager(eqVO.getManager());
		    targetEqVO.setUse(eqVO.getUse());
		    
		  return eqService.eqUpdate2(eqVO); 
		  
	  }// end eqUpdateAJAXJSON
	 	  
	
	  
	// 삭제 - 처리 : URI - eqDelete / PARAMETER - Integer
	// RETURN - 전체조회 다시 호출
	@PostMapping("eqDelete") // QueryString : @RequestParam
	@ResponseBody
	public int eqDelete(String eqCode) {
		return eqService.eqDelete(eqCode);
		//return "redirect:eqList"; //redirect 걸리는 건 경로를 다시 요청, 페이지 요청아님!!
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
	
	// 비가동 목록 "가동"으로 변경
	  @PostMapping("updateUseStatus")
	  @ResponseBody 
	  public Map<String, Object> updateUseStatus(@RequestBody EqVO eqVO) {
		  return eqService.updateEqChck(eqVO); 
	  }
	  
	// 비가동 내역 검색
	  @PostMapping("/eqSearch")
		@ResponseBody
		public List<EqChckVO> eqSearch(@RequestBody Map<String, Object> request) {
		    String eqCode = (String) request.get("eqCode");
		    String startDate = (String) request.get("startDate");
		    String endDate = (String) request.get("endDate");
		    System.err.print(111);
		    return eqService.eqSearch(eqCode, startDate, endDate);
		}
	
	// 설비 점검 목록 조회
	@GetMapping("jgList")
	public String jgList(Model model) {
		List<EqChckVO> list = eqService.jgList();
		model.addAttribute("equipjg", list);
		
		return "equip/eqJumgum";
	}
	
	// 설비 점검 업데이트
    @PostMapping("/updateCheckStatus")
    @ResponseBody
    public String updateCheckStatus(@RequestBody EqChckVO eqChckVO) {
        eqService.updateCheckStatus(eqChckVO);
        return "success";
    }
	
	@PostMapping("nOpRegister")
	@ResponseBody
	public EqChckVO registerNonOperating(@RequestBody EqChckVO eqChckVO) {
	    // eqChckVO를 이용해 서비스 레이어에서 로직 처리 후 DB에 저장
		//int nid = eqService.nOpRegister(eqChckVO)
	    eqService.saveNonOperating(eqChckVO);
	    return eqChckVO;  // 저장된 데이터를 반환해 화면에서 바로 추가할 수 있게 함
	}

	@PostMapping("getEqChckOx")
	@ResponseBody
	public int getEqChckOx(@RequestBody EqVO eqVO) {
		return eqService.selectEqChckOx(eqVO);
	}
} // end of class
