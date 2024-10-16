package com.sjc.app.info.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjc.app.info.mapper.InfoCpMapper;
import com.sjc.app.info.service.InfoCpService;
import com.sjc.app.sales.service.CpVO;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;

@Timed("info.cp")
@Slf4j
@Service // AOP 적용가능한 Bean
public class InfoCpServiceImpl implements InfoCpService {
	//private MeterRegistry registry;
	private InfoCpMapper infoCpMapper;
	
	@Autowired
	InfoCpServiceImpl(InfoCpMapper infoCpMapper, MeterRegistry registry){
		this.infoCpMapper = infoCpMapper;
	}
	
	@Override
	public List<CpVO> cpList(CpVO cpVO) {
		return infoCpMapper.selectCpAllList(cpVO);
	}

}
