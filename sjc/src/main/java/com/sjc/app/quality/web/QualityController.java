package com.sjc.app.quality.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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

	// 조회 - 입고검사대기 조회페이지
    @GetMapping("incomingQualityWaitInfo")
    public String incomingWaitInfo(MtlOdVO mtlOdVO, Model model) {
    	List<MtlOdVO> list = qualityService.incomingWaitInfo();
    	model.addAttribute("incomingQualityWaits", list);
		return "quality/incomingQualityWait";
    }
    
    
    // 조회 - 입고검사완료 조회페이지
    @GetMapping("incomingQualityDoneInfo")
    public String incomingQualityDone(MtlOdVO mtlOdVO, Model model) {
    	List<InspectionVO> list = qualityService.incomingDoneInfo();
    	model.addAttribute("incomingQualityDones", list);
    	return "quality/incomingQualityDone";
    }
    
    
	// 전체 조회 - 입고검사등록 페이지    
    @GetMapping("incomingQualityRegistrationInfo")
    public String incomingRegistrationInfo(InspectionVO inspectionVO, Model model) {
    	// 발주목록 단건조회
    	InspectionVO findVO = qualityService.incomingRegistrationInfo(inspectionVO);
    	model.addAttribute("incomingQualityRegistrationList", findVO);
    	
    	String mtlOdCode = findVO.getMtlOdCode();
    	MtlOdVO mtlOdVO = new MtlOdVO();
    	mtlOdVO.setMtlOdCode(mtlOdCode);
    	
    	// 단건조회 - 입고검사등록 페이지
    	//List<InsItemVO> insItemlist = qualityService.incomingQualityTestInfo(mtlOdVO);
    	List<InspectionVO> insItemlist = qualityService.incomingQualityTestInfo(mtlOdVO);
    	model.addAttribute("testList", insItemlist);
    	
    	return "quality/incomingQualityRegistration";
    }
    
	/*
	 * // 입고검사완료페이지(임의로 만든거) - 값 입고처리 버튼 누르면 수정
	 * 
	 * @PostMapping("/updateIncoming") public ResponseEntity<String>
	 * updateIncoming(@RequestBody List<Map<String, Object>> items) { try {
	 * 
	 * // DB 업데이트 로직 qualityService.updateIncoming(items); // 적절한 서비스 메서드를 호출
	 * 
	 * return ResponseEntity.ok("입고 처리 완료"); } catch (Exception e) {
	 * e.printStackTrace(); // 예외 처리 return
	 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("입고 처리 실패"); } }
	 */
	/*
	 * // 단건조회 - 입고등록 페이지
	 * 
	 * @GetMapping("incomingQualityRegistrationInfo") public String
	 * incomingRegistrationInfo(MtlOdVO mtlOdVO, Model model) { List<MtlOdVO> list =
	 * qualityService.incomingQualityTestInfo(mtlOdVO);
	 * model.addAttribute("testList", list); return
	 * "quality/incomingQualityRegistration"; }
	 */
    
    
	/*
	 * // 입고검사완료페이지 - 값 입고처리 버튼 누르면 자재발주상태(mtl_od/ status), ins(품질검사 상태) 완료로 넘기기 //
	 * 수정 : PUT + URI(자원 => emps)
	 * 
	 * @PostMapping("inspectionDoneUpdate")
	 * 
	 * @ResponseBody // AJAX public Map<String, Object>
	 * inspectionDoneUpdateAJAXJSON(@RequestBody InspectionVO inspectionVO){ return
	 * qualityService.inspectionDoneUpdate(inspectionVO); } // 수정 : PUT + URI(자원 =>
	 * emps)
	 * 
	 * @PostMapping("mtlOdDoneUpdate")
	 * 
	 * @ResponseBody // AJAX public Map<String, Object>
	 * mtlOdDoneUpdateAJAXJSON(@RequestBody InspectionVO inspectionVO){ return
	 * qualityService.mtlOdDoneUpdate(inspectionVO); }
	 */
	

	
//	@PutMapping("emps/{employeeId}")
//	public Map<String, Object>
//			empUpdate(@PathVariable Integer employeeId, 
//							@RequestBody EmpVO empVO) {
//		empVO.setEmployeeId(employeeId);
//		
//		return empService.empUpdate(empVO);
//	}
    
    
//    @PostMapping("updateIncoming")
//    @ResponseBody
//    public List<InspectionVO> updateIncoming(@RequestBody List<InspectionVO> inspectionVOs) {
//        return qualityService.updateIncoming(inspectionVOs);
//    }	
    
    // 입고등록페이지 - 저장버튼 - inspection.ins_status 검사완료
    @PostMapping("updateInspectionDone")
    @ResponseBody
    public List<InspectionVO> updateinspectionDone(@RequestBody List<InspectionVO> inspectionVOs) {
    	return qualityService.inspectionDoneUpdate(inspectionVOs);
    }	
    
    // 입고검사완료페이지 - 입고처리 버튼 - mtl_od.mtl_od_status 입고품질검사완료
    // 입고검사완료페이지 - 입고처리 버튼 - MtInVO로 post
    // 입고검사완료페이지 - 입고처리 버튼 - mt_in으로 데이터 넣기
    @PostMapping("updateIncoming")
    @ResponseBody
    public List<InspectionVO> updateMtlOdDone(@RequestBody List<InspectionVO> inspectionVOs) {
    	return qualityService.mtlOdMtInUpdateInsert(inspectionVOs);

    }	
//    @PostMapping("updateIncoming")
//    @ResponseBody
//    public List<InspectionVO> updateMtlOdDone(@RequestBody List<InspectionVO> inspectionVOs) {
//    	return qualityService.mtlOdMtOdUpdate(inspectionVOs);
//    }	
    // 입고검사완료페이지 - 반품 버튼 - mtl_od.mtl_od_status 반품
    @PostMapping("updateMtlOdBack")
    @ResponseBody
    public List<InspectionVO> updateMtlOdBack(@RequestBody List<InspectionVO> inspectionVOs) {
    	return qualityService.mtlOdBackUpdate(inspectionVOs);
    }
    
//    // 입고검사완료페이지 - 입고처리 버튼 - MtInVO로 post
//    @PostMapping("updateIncoming")
//    @ResponseBody
//    public String insertMtIn(@RequestBody InspectionVO inspectionVOs) {
//    	return qualityService.mtInInsert(inspectionVOs);
//    	
//    	
//    }	
//    

//    @PostMapping("insertMtIn")
//	@ResponseBody
//	public List<InspectionVO> insertMtIn(@RequestBody List<InspectionVO> list) {
//		return qualityService.insertMtIn(list);
//	}
    
}
