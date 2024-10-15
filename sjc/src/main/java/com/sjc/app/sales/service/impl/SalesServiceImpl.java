package com.sjc.app.sales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjc.app.sales.mapper.SalesMapper;
import com.sjc.app.sales.service.PrdVO;
import com.sjc.app.sales.service.SalesService;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service 
public class SalesServiceImpl implements SalesService {
	private SalesMapper salesMapper;
	
	@Autowired
	SalesServiceImpl(SalesMapper salesMapper, MeterRegistry registry) {
		this.salesMapper = salesMapper;
	}
	
	@Override
	public List<PrdVO> prdList() {
		return salesMapper.selectPrdAllList();
	}

}
