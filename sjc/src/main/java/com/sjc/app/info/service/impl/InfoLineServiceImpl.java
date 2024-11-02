package com.sjc.app.info.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjc.app.info.mapper.InfoLineMapper;
import com.sjc.app.info.service.LineVO;
import com.sjc.app.info.service.InfoLineService;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;

@Timed("info.line")
@Slf4j
@Service // AOP 적용가능한 Bean
public class InfoLineServiceImpl implements InfoLineService {
	//private MeterRegistry registry;
	private InfoLineMapper infoLineMapper;
	
	@Autowired
	InfoLineServiceImpl(InfoLineMapper infoLineMapper, MeterRegistry registry){
		this.infoLineMapper = infoLineMapper;
	}
	
	// 라인 조회
	@Override
	public List<LineVO> lineList(LineVO lineVO) {
		return infoLineMapper.selectLineAllList(lineVO);
	}

}
