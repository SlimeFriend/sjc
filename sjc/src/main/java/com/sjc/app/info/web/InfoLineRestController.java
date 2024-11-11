package com.sjc.app.info.web;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sjc.app.info.service.LineVO;
import com.sjc.app.mt.service.MtVO;
import com.sjc.app.sales.service.CpVO;
import com.sjc.app.info.service.InfoLineService;
import com.sjc.app.info.service.InfoUserVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InfoLineRestController {
	
	private final InfoLineService lineService;
	// 라인 조회
	@GetMapping("lines")
	public List<LineVO> lineList(LineVO lineVO){
		return lineService.lineList(lineVO);
	}
	
    // 라인 수정
	@PostMapping("lines")
    public List<LineVO> updateLines(@RequestBody List<LineVO> lineVOs) {
        return lineService.modifyLines(lineVOs);
    }	

	// 라인 등록
    @PutMapping("lines")
    public List<LineVO> insertLines(@RequestBody List<LineVO> lineVOs) {
        return lineService.insertLines(lineVOs);
    }
    
    // 라인 삭제
    @DeleteMapping("lines")
    public List<LineVO> deleteLines(@RequestBody List<LineVO> lineVOs) {
        return lineService.deleteLines(lineVOs);
    }    
}
