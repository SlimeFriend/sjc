package com.sjc.app.info.web;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sjc.app.info.service.BomVO;
import com.sjc.app.info.service.InfoBomService;
import com.sjc.app.info.service.PrdBomDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InfoBomRestController {
	
	private final InfoBomService bomService;

	@PutMapping("registerPrdBoms")
	public List<BomVO> registerPrdBoms(@RequestBody List<BomVO> bomVOs) {
		return bomService.registerPrdBoms(bomVOs);
	}
	
	@PutMapping("registerBoms")
	public List<BomVO> registerBoms(@RequestBody List<BomVO> bomVOs) {
		return bomService.registerBoms(bomVOs);
	}
	
	@GetMapping("boms")
	public List<BomVO> bomList(){
		return bomService.bomList();
	}
	
	@GetMapping("bomDetails")
	public List<BomVO> bomDetails(BomVO bomVO){
		return bomService.bomDetailList(bomVO);
	}
	
	@PutMapping("prdBom")
	public Map<String, Object> updatePrdBom(@RequestBody PrdBomDTO prdBomDTO) {
	    return bomService.modifyPrdBom(prdBomDTO);
	}	
}
