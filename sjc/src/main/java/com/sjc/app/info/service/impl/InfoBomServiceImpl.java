package com.sjc.app.info.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjc.app.info.mapper.InfoBomMapper;
import com.sjc.app.info.service.BomVO;
import com.sjc.app.info.service.InfoBomService;

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
	public List<String> registerBoms(List<String> mtCodes) {
		infoBomMapper.insertBom();
		infoBomMapper.insertBomDetail(mtCodes);

		return mtCodes;
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
