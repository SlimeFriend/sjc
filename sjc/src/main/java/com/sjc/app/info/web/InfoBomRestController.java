package com.sjc.app.info.web;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

	// 제품,BOM 등록
	@PutMapping("registerPrdBoms")
	public List<BomVO> registerPrdBoms(@RequestBody List<BomVO> bomVOs) {
		return bomService.registerPrdBoms(bomVOs);
	}
	// BOM 등록
	@PutMapping("registerBoms")
	public List<BomVO> registerBoms(@RequestBody List<BomVO> bomVOs) {
		return bomService.registerBoms(bomVOs);
	}
	// BOM 조회
	@GetMapping("boms")
	public List<BomVO> bomList(){
		return bomService.bomList();
	}
	// BOM 상세 조회
	@GetMapping("bomDetails")
	public List<BomVO> bomDetails(BomVO bomVO){
		return bomService.bomDetailList(bomVO);
	}
	// 제품,BOM 수정
	@PutMapping("prdBom")
	public Map<String, Object> updatePrdBom(@RequestBody PrdBomDTO prdBomDTO) {
	    return bomService.modifyPrdBom(prdBomDTO);
	}
    // BOM, BOM 상세 삭제
    @DeleteMapping("boms")
    public List<BomVO> deleteBoms(@RequestBody List<BomVO> bomVOs) {
        return bomService.deleteBoms(bomVOs);
    }	
}
