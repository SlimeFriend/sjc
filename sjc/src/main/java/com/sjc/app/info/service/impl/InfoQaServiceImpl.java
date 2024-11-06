package com.sjc.app.info.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjc.app.info.mapper.InfoQaMapper;
import com.sjc.app.info.service.InfoQaService;
import com.sjc.app.info.service.InspectionDTO;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;

@Timed("info.qa")
@Slf4j
@Service // AOP 적용가능한 Bean
public class InfoQaServiceImpl implements InfoQaService {
	//private MeterRegistry registry;
	private InfoQaMapper infoQaMapper;
	
	@Autowired
	InfoQaServiceImpl(InfoQaMapper infoQaMapper, MeterRegistry registry){
		this.infoQaMapper = infoQaMapper;
	}

	@Override
	public List<InspectionDTO> qaPassRateList() {
		return infoQaMapper.selectQaPassRate();
	}
}
