package com.sjc.app.info.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjc.app.info.mapper.InfoBomMapper;
import com.sjc.app.info.service.BomVO;
import com.sjc.app.info.service.InfoBomService;
import com.sjc.app.info.service.InfoUserVO;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;

@Timed("info.bom")
@Slf4j
@Service // AOP 적용가능한 Bean
public class InfoBomServiceImpl implements InfoBomService {
	//private MeterRegistry registry;
	private InfoBomMapper infoBomMapper;
	
	@Autowired
	InfoBomServiceImpl(InfoBomMapper infoBomMapper, MeterRegistry registry){
		this.infoBomMapper = infoBomMapper;
	}
	
	@Override
    @Transactional
//	public List<String> registerBoms(List<String> mtCodes) {
	public List<BomVO> registerBoms(List<BomVO> bomVOs) {
		
		/*
        List<BomVO> BomVOs = new ArrayList<>();
        
        for(String mtCode : mtCodes) {
        	BomVO bomVO = new BomVO();
        	bomVO.setMtCode(mtCode);
        	BomVOs.add(bomVO);
        }
        
		infoBomMapper.insertBom();
				
        for(String mtCode : mtCodes) {
            infoBomMapper.insertBomDetail(mtCode);  // 단건 처리로 변경
        }

		return mtCodes;
		*/
		
		infoBomMapper.insertBom();
        for (BomVO bomVO : bomVOs) {
        	infoBomMapper.insertBomDetail(bomVO);  // 단건 처리로 변경
        }

        return bomVOs;		
		
		
	}
	
	@Override
	public List<BomVO> bomList() {
		return infoBomMapper.selectBomAllList();
	}
	
	@Override
	public List<BomVO> bomDetailList() {
		return infoBomMapper.selectBomDetailAllList();
	}
	
}
