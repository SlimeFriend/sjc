package com.sjc.app.info.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjc.app.info.mapper.InfoBomMapper;
import com.sjc.app.info.mapper.InfoPrdMapper;
import com.sjc.app.info.service.BomVO;
import com.sjc.app.info.service.InfoPrdService;
import com.sjc.app.sales.service.ProductVO;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;

@Timed("info.prd")
@Slf4j
@Service // AOP 적용가능한 Bean
public class InfoPrdServiceImpl implements InfoPrdService {
	//private MeterRegistry registry;
	private InfoPrdMapper infoPrdMapper;
	private InfoBomMapper infoBomMapper;
	
	@Autowired
	InfoPrdServiceImpl(InfoPrdMapper infoPrdMapper,
			InfoBomMapper infoBomMapper,
			MeterRegistry registry){
		this.infoPrdMapper = infoPrdMapper;
		this.infoBomMapper = infoBomMapper;
	}
	
	// 제품 조회
	@Override
	public List<ProductVO> prdList(ProductVO productVO) {
		return infoPrdMapper.selectPrdAllList(productVO);
	}

	@Override
	@Transactional
	public List<String> deletePrds(List<String> prdCodes) {
		
		for(String prdCode : prdCodes) {
			// 제품 삭제
			infoPrdMapper.deletePrds(prdCode);
			
			BomVO bomVO = new BomVO();
			bomVO.setPrdCode(prdCode);
			
			// BOM에서 제품 삭제
			infoBomMapper.updateBomNull(bomVO);
		}
		return prdCodes;
	}

	@Override
	public List<ProductVO> updatePrds(List<ProductVO> productVOs) {
		
		for(ProductVO productVO : productVOs) {
			infoPrdMapper.updatePrds(productVO);
		}
		return productVOs;
	}

}
