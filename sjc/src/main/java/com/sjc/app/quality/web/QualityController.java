package com.sjc.app.quality.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sjc.app.mt.service.MtlOdVO;
import com.sjc.app.pr.service.PDetailVO;
import com.sjc.app.quality.service.InsDetailVO;
import com.sjc.app.quality.service.InspectionVO;
import com.sjc.app.quality.service.QualityService;
import com.sjc.app.security.service.LoginUserVO;

@Controller
public class QualityController {
	
    private QualityService qualityService;
    
    @Autowired
    public QualityController(QualityService qualityService) {
    	this.qualityService = qualityService;
    }
    
    
    
    
    
    
 // 품질검사등록모달 공통
	// insDetail - insValue 업데이트
	@PostMapping("insValueUpdate")
	@ResponseBody
	public List<InsDetailVO> insValueUpdate(@RequestBody List<InsDetailVO> insDetailVO) {
		return qualityService.insValueUpdate(insDetailVO);
	}


    
    
    
    
    
    
// 입고    
    // 발주목록전체
    @GetMapping("incomingQualityWaitHistory")
    public String incomingQualityWaitHistory(Model model) {
    	List<InspectionVO> list = qualityService.mtlOdList();
    	model.addAttribute("mtlList", list);
    	return "quality/incomingQualityWaitHistory";
    }
    
    // 발주목록 검색
    @PostMapping("/searchMO")
    @ResponseBody
    public List<InspectionVO> moSearch(@RequestBody Map<String, Object> request) {
    	String cpCode = (String) request.get("cpCode");
	    String cpName = (String) request.get("cpName");
	    String moStartDate = (String) request.get("moStartDate");
	    String moEndDate = (String) request.get("moEndDate");
	    
	    
	    return qualityService.moSearch(cpCode, cpName, moStartDate, moEndDate);
    }
    
    
    // 발주목록상세 테이블
    @PostMapping("getIncomingQualityWaitDetail")
    @ResponseBody
    public List<Map<String, Object>> getIncomingQualityWaitDetail(@RequestBody Map<String, String> requestData) {
    	String mtlOdCode = requestData.get("mtlOdCode");
    	
    	List<Map<String, Object>> mtlOdDetail = qualityService.MtlOdDetail(mtlOdCode);
    	
    	return mtlOdDetail;
    }

		// 자재품질검사 모달창
		@PostMapping("/incomingInspection")
		@ResponseBody
		@Transactional
		public Map<String, Object> insertInspection(@RequestBody InspectionVO inspectionVO) {
			
			
			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String id = null;
            String name = null;
            if (authentication.getPrincipal() instanceof LoginUserVO) {
                LoginUserVO loginUserVO = (LoginUserVO) authentication.getPrincipal();
                id = loginUserVO.getUserVO().getUserId();
                name = loginUserVO.getUserVO().getUserName();
            }
			
			

			List<InspectionVO> insList;
			Map<String, Object> map = new HashMap<String, Object>();

			int countIns = qualityService.whetherInspection(inspectionVO);

			if (countIns > 0) {
				insList = qualityService.inspectionList(inspectionVO);

			} else {
				// 검사대기->검사중 - mtlOdStatus, mtlOdDetailStatus
				 qualityService.mtlOdStatusUpdate(inspectionVO);
				 qualityService.mtlOdDetailStatusUpdate(inspectionVO);
				// inspection 데이터 생성
				qualityService.insertInspection(inspectionVO);
				// inspection 데이터 출력
				insList = qualityService.inspectionList(inspectionVO);

			}
			String insCode = null;
			for (InspectionVO insVO : insList) {

				insCode = insVO.getInsCode();
			}

			List<InspectionVO> testList = new ArrayList<>();
			List<InspectionVO> insDetailList = new ArrayList<>();

			int countInsItem = qualityService.insItemCount(inspectionVO);

			// 자재입고검사완료페이지- 검사리스트 출력
			testList = qualityService.testDetailSelect(inspectionVO);
			if (countInsItem == 0) {

				for (InspectionVO insVO : testList) {
					insVO.setInsCode(insCode);
					// 자재입고검사완료페이지- insDetail 생성
					qualityService.insertInsDetail(insVO);
				}

			}

			InspectionVO insDetailVO = new InspectionVO();
			insDetailVO.setInsCode(insCode);
			// 자재입고검사완료페이지- insDetail 데이터 출력
			insDetailList = qualityService.insDetailList(insDetailVO);

			List<InspectionVO> newList = new ArrayList<>();

			for (int i = 0; i < testList.size(); i++) {
				InspectionVO insVO = testList.get(i);
				InspectionVO idVO = insDetailList.get(i);

				insVO.setInsDetailCode(idVO.getInsDetailCode());
				insVO.setInsCode(idVO.getInsCode());
				insVO.setInsValue(idVO.getInsValue());
				insVO.setInsResult(idVO.getInsResult());

				newList.add(insVO);
			}
			List<InspectionVO> newInsList = new ArrayList<>();
			for(InspectionVO vo : insList) {
                vo.setUserId(Integer.parseInt(id));
                vo.setUserName(name);
                newInsList.add(vo);
            }

//			map.put("insList", insList);
            map.put("insList", newInsList);
            map.put("testList", testList);
            map.put("newList", newList);

			return map;

		}
		// 품질검사 등록
		@PostMapping("incomingQualityWaitHistory")
		@ResponseBody
		public List<InspectionVO> insUpdate(@RequestBody List<InspectionVO> insData) {
			
			
			return qualityService.insUpdate(insData);
		}


		
		
		
		
		
		
		

		// 자재입고검사완료페이지 - 조회
		@GetMapping("incomingQualityDone")
		public String incomingQualityDone(Model model) {
			List<InspectionVO> list = qualityService.incomingDoneInfo();
			model.addAttribute("incomingQualityDones", list);
			return "quality/incomingQualityDone";
		}
		// 자재입고검사완료페이지 - 입고처리 버튼 - mtl_od.mtl_od_status 입고품질검사완료
		// 자재입고검사완료페이지 - 입고처리 버튼 - mt_in으로 데이터 넣기
		@PostMapping("updateIncoming")
		@ResponseBody
		public List<InspectionVO> updateMtlOdDone(@RequestBody List<InspectionVO> inspectionVOs) {
			return qualityService.mtlOdMtInUpdateInsert(inspectionVOs);
			
		}	
		
	    // 입고검사완료페이지 - 반품 버튼 - mtl_od.mtl_od_status 반품
	    @PostMapping("updateMtlOdBack")
	    @ResponseBody
	    public List<InspectionVO> updateMtlOdBack(@RequestBody List<InspectionVO> inspectionVOs) {
	    	return qualityService.mtlOdBackUpdate(inspectionVOs);
	    }
		
		
		
// 출고
	    // 생산지시목록 검색
	    @PostMapping("/searchPrd")
	    @ResponseBody
	    public List<InspectionVO> prdSearch(@RequestBody Map<String, Object> request) {
	    	String porderCode = (String) request.get("porderCode");
		    String userName = (String) request.get("userName");
		    String poStartDate = (String) request.get("poStartDate");
		    String poEndDate = (String) request.get("poEndDate");
		    
		    
		    return qualityService.prdSearch(porderCode, userName, poStartDate, poEndDate);
	    }
		// 완제품품질검사 대기목록1
		@GetMapping("finishQualityWait")
		public String pOrderSelect(Model model) {
			List<InspectionVO> pOrderList = qualityService.pOrderSelect();
			model.addAttribute("pOrderList", pOrderList);
			return "quality/finishQualityWait";
		}

		// 완제품품질검사 대기목록2
		@PostMapping("finishQualityWaitDetail")
		@ResponseBody
		public List<Map<String, Object>> pDetailSelect(@RequestBody Map<String, String> requestData) {
			String porderCode = requestData.get("porderCode");
			List<Map<String, Object>> pDetail = qualityService.pDetailSelect(porderCode);

			return pDetail;
		}

		
		// 완제품품질검사 모달창
		@PostMapping("finishQualityInspection")
		@ResponseBody
		@Transactional
		public Map<String, Object> insertInsPDtl(@RequestBody InspectionVO inspectionVO) {

			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String id = null;
            String name = null;
            if (authentication.getPrincipal() instanceof LoginUserVO) {
                LoginUserVO loginUserVO = (LoginUserVO) authentication.getPrincipal();
                id = loginUserVO.getUserVO().getUserId();
                name = loginUserVO.getUserVO().getUserName();
            }
						
			
			
			
			
			
			List<InspectionVO> pDInsList;
			Map<String, Object> map = new HashMap<String, Object>();

			int countIns = qualityService.pDtlInsCnt(inspectionVO);

			if (countIns == 0) {

			
				// 검사대기->검사중 - updatePOrderStatus, updatePDetailStatus
				 qualityService.pOrderStatusUpdate(inspectionVO);
				 qualityService.pDetailStatusUpdate(inspectionVO);
				// inspection 데이터 생성
				qualityService.dtlInsInsert(inspectionVO);
//				pDInsList = qualityService.pDtlInsSelect(inspectionVO);

			}
			// inspection 데이터 출력
			pDInsList = qualityService.pDtlInsSelect(inspectionVO);
			String insCode = null;
			for (InspectionVO insVO : pDInsList) {

				insCode = insVO.getInsCode();
			}

			List<InspectionVO> testList = new ArrayList<>();
			List<InspectionVO> insDetailList = new ArrayList<>();

			int countInsItem = qualityService.pDInsDCount(inspectionVO);

			// 품질검사상세- 검사리스트 출력
			testList = qualityService.pDtlTestSelect(inspectionVO);
			if (countInsItem == 0) {

				for (InspectionVO insVO : testList) {
					insVO.setInsCode(insCode);
					// 품질검사상세- insDetail 생성
					qualityService.pDtlInsDInsert(insVO);
				}

			}

			InspectionVO insDetailVO = new InspectionVO();
			insDetailVO.setInsCode(insCode);
			// 품질검사상세- insDetail 데이터 출력
			insDetailList = qualityService.pDtlInsDListSelect(insDetailVO);

			List<InspectionVO> pDInsDtlList = new ArrayList<>();

			for (int i = 0; i < testList.size(); i++) {
				InspectionVO insVO = testList.get(i);
				InspectionVO idVO = insDetailList.get(i);

				insVO.setInsDetailCode(idVO.getInsDetailCode());
				insVO.setInsCode(idVO.getInsCode());
				insVO.setInsValue(idVO.getInsValue());
				insVO.setInsResult(idVO.getInsResult());

				pDInsDtlList.add(insVO);
			}
			List<InspectionVO> newpDInsList = new ArrayList<>();
			for(InspectionVO vo : pDInsList) {
                vo.setUserId(Integer.parseInt(id));
                vo.setUserName(name);
                newpDInsList.add(vo);
            }

			map.put("pDInsList", newpDInsList);
			map.put("testList", testList);
			map.put("pDInsDtlList", pDInsDtlList);

			return map;

		}
		// 완제품품질검사 등록
		@PostMapping("finishQualityWait")
		@ResponseBody
		public List<InspectionVO> insPdUpdate(@RequestBody List<InspectionVO> insPdData) {
			
			
			return qualityService.insPdUpdate(insPdData);
		}

		
		
		
		
		// 제품출고검사완료페이지
		@GetMapping("outQualityDone")
		public String outDoneInfoSelect(Model model) {
			List<InspectionVO> pDetailDones = qualityService.outDoneInfoSelect();
			model.addAttribute("pDetailDones", pDetailDones);
			return "quality/outQualityDone";
		}
		
		// 자재입고검사완료페이지 - 입고처리 버튼 - mtl_od.mtl_od_status 입고품질검사완료
		// 자재입고검사완료페이지 - 입고처리 버튼 - mt_in으로 데이터 넣기
		@PostMapping("updateFinish")
		@ResponseBody
		public List<InspectionVO> upPOrdInPMan(@RequestBody List<InspectionVO> inspectionVO) {
			return qualityService.upPOrdInPMan(inspectionVO);
			
		}	
		
	    // 입고검사완료페이지 - 반품 버튼 - mtl_od.mtl_od_status 반품
	    @PostMapping("updatePdBack")
	    @ResponseBody
	    public List<InspectionVO> pdBackUpdate(@RequestBody List<InspectionVO> inspectionVO) {
	    	return qualityService.pdBackUpdate(inspectionVO);
	    }
    
    
    

    
    

    
    
    
    
    //=====================================================================================================
   // 최초 버전 1 -- 아래부터는 프로젝트 방향 변경으로 지금 안써서 남겨두기만 함.====================================================================
//========================================================================================================
    
    

	// 조회 - 입고검사대기 조회페이지
    @GetMapping("incomingQualityWaitInfo")
    public String incomingWaitInfo(MtlOdVO mtlOdVO, Model model) {
    	List<MtlOdVO> list = qualityService.incomingWaitInfo();
    	model.addAttribute("incomingQualityWaits", list);
		return "quality/incomingQualityWait";
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
    
//    @PostMapping("updateIncoming")
//    @ResponseBody
//    public List<InspectionVO> updateMtlOdDone(@RequestBody List<InspectionVO> inspectionVOs) {
//    	return qualityService.mtlOdMtOdUpdate(inspectionVOs);
//    }	

    
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
