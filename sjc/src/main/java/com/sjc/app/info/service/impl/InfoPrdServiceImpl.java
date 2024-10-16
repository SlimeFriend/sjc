package com.sjc.app.info.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjc.app.info.mapper.InfoPrdMapper;
import com.sjc.app.info.service.InfoPrdService;
import com.sjc.app.sales.service.PrdVO;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;

@Timed("info.prd")
@Slf4j
@Service // AOP 적용가능한 Bean
public class InfoPrdServiceImpl implements InfoPrdService {
	//private MeterRegistry registry;
	private InfoPrdMapper infoPrdMapper;
	
	@Autowired
	InfoPrdServiceImpl(InfoPrdMapper infoPrdMapper, MeterRegistry registry){
		this.infoPrdMapper = infoPrdMapper;
	}
	
	@Override
	public List<PrdVO> prdList(PrdVO prdvo) {
		return infoPrdMapper.selectPrdAllList(prdvo);
	}

}
