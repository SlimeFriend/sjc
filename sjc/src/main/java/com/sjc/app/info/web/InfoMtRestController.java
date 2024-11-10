package com.sjc.app.info.web;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sjc.app.info.service.InfoMtService;
import com.sjc.app.mt.service.MtVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InfoMtRestController {
	
	private final InfoMtService infoMtService;
	// 자재 조회
	@GetMapping("mts")
	public List<MtVO> mtList(MtVO mtVO){
		return infoMtService.mtList(mtVO);
	}
	// 자재 조회
	@GetMapping("getChartMt")
	public List<MtVO> getChartMt(){
		return infoMtService.mtStockList();
	}
	// 자재 등록
    @PutMapping("mts")
    public List<MtVO> insertMts(@RequestBody List<MtVO> mtVOs) {
        return infoMtService.insertMts(mtVOs);
    }
    // 자재 삭제
    @DeleteMapping("mts")
    public List<MtVO> deleteMts(@RequestBody List<MtVO> mtVOs) {
        return infoMtService.deleteMts(mtVOs);
    }	    
    // 자재 수정
    @PostMapping("mts")
    public List<MtVO> updateMts(@RequestBody List<MtVO> mtVOs) {
    	return infoMtService.updateMts(mtVOs);
    }	    
    
}
