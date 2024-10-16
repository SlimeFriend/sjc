package com.sjc.app.info.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjc.app.info.mapper.InfoMtMapper;
import com.sjc.app.info.service.InfoMtService;
import com.sjc.app.mt.service.MtVO;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;

@Timed("info.mt")
@Slf4j
@Service // AOP 적용가능한 Bean
public class InfoMtServiceImpl implements InfoMtService {
	//private MeterRegistry registry;
	private InfoMtMapper infoMtMapper;
	
	@Autowired
	InfoMtServiceImpl(InfoMtMapper infoMtMapper, MeterRegistry registry){
		this.infoMtMapper = infoMtMapper;
	}
	
	@Override
	public List<MtVO> mtList(MtVO mtVO) {
		return infoMtMapper.selectMtAllList(mtVO);
	}

}
