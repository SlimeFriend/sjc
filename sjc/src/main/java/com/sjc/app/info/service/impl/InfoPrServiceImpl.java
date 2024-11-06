package com.sjc.app.info.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjc.app.info.mapper.InfoPrMapper;
import com.sjc.app.info.service.InfoPrService;
import com.sjc.app.info.service.PResultDTO;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;

@Timed("info.pr")
@Slf4j
@Service // AOP 적용가능한 Bean
public class InfoPrServiceImpl implements InfoPrService {
	//private MeterRegistry registry;
	private InfoPrMapper infoPrMapper;
	
	@Autowired
	InfoPrServiceImpl(InfoPrMapper infoPrMapper, MeterRegistry registry){
		this.infoPrMapper = infoPrMapper;
	}

	@Override
	public List<PResultDTO> PResultInOutList() {
		return infoPrMapper.selectPResultInOut();
	}
	


}
