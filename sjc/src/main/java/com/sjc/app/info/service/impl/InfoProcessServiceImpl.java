package com.sjc.app.info.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjc.app.info.mapper.InfoProcessMapper;
import com.sjc.app.info.service.InfoProcessService;
import com.sjc.app.info.service.ProcessVO;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;

@Timed("info.process")
@Slf4j
@Service // AOP 적용가능한 Bean
public class InfoProcessServiceImpl implements InfoProcessService {
	//private MeterRegistry registry;
	private InfoProcessMapper infoProcessMapper;
	
	@Autowired
	InfoProcessServiceImpl(InfoProcessMapper infoProcessMapper, MeterRegistry registry){
		this.infoProcessMapper = infoProcessMapper;
	}
	
	@Override
	public List<ProcessVO> processList(ProcessVO processVO) {
		return infoProcessMapper.selectProcessAllList(processVO);
	}

}
