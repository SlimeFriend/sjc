package com.sjc.app.info.web;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sjc.app.info.service.InfoPrdService;
import com.sjc.app.info.service.InfoUserVO;
import com.sjc.app.sales.service.ProductVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InfoPrdRestController {
	
	private final InfoPrdService prdService;
	// 제품 조회
	@GetMapping("prds")
	public List<ProductVO> prdList(ProductVO productVO){
		return prdService.prdList(productVO);
	}
	// 제품 조회
	@PostMapping("prds")
//	public List<ProductVO> getPrd(@ModelAttribute ProductVO productVO){
	public List<ProductVO> getPrd(@RequestBody ProductVO productVO){
		return prdService.prdList(productVO);
	}
    // 제품 삭제
    @DeleteMapping("prds")
    public List<String> deletePrds(@RequestBody List<String> prdCodes) {
        return prdService.deletePrds(prdCodes);
    }
    // 제품 수정
    @PutMapping("prds")
    public List<ProductVO> updatePrds(@RequestBody List<ProductVO> productVOs) {
        return prdService.updatePrds(productVOs);
    }    
}
