package com.sjc.app.info.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjc.app.info.mapper.InfoSaMapper;
import com.sjc.app.info.service.InfoSaService;
import com.sjc.app.info.service.OrdDTO;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;

@Timed("info.sa")
@Slf4j
@Service // AOP 적용가능한 Bean
public class InfoSaServiceImpl implements InfoSaService {
	//private MeterRegistry registry;
	private InfoSaMapper infoSaMapper;
	
	@Autowired
	InfoSaServiceImpl(InfoSaMapper infoSaMapper, MeterRegistry registry){
		this.infoSaMapper = infoSaMapper;
	}
	
	// 주문 조회
	@Override
	public List<OrdDTO> getOrd() {
		return infoSaMapper.selectOrderCount();
	}


}
