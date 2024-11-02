package com.sjc.app.info.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sjc.app.info.service.InfoPrdService;
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

}
