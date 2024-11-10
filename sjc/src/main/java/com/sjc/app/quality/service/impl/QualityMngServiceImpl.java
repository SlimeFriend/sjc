package com.sjc.app.quality.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjc.app.quality.mapper.QualityMngMapper;
import com.sjc.app.quality.service.InspectionVO;
import com.sjc.app.quality.service.QualityMngService;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
@Timed("quality.mng")
@Slf4j
@Service // AOP 적용가능한 Bean
public class QualityMngServiceImpl implements QualityMngService {
	
	private QualityMngMapper qualityMngMapper;
	
	@Autowired
	QualityMngServiceImpl(QualityMngMapper qualityMngMapper, MeterRegistry registry){
		this.qualityMngMapper = qualityMngMapper;
	}
	
	@Override
	public List<InspectionVO> insSelect(InspectionVO inspectionVO) {
		return qualityMngMapper.selectIns(inspectionVO);
	}
	
	
	// 검사 완료 필터링 ( 전체, MTD, PD )
	@Override
	public List<InspectionVO> insRadio(String rad) {
		if (rad == null || rad.equals("all")) {
			return qualityMngMapper.radioAllIns();
		} else if (rad.equals("MTLD")) {
			return qualityMngMapper.radioMTLDIns();
		} else if (rad.equals("PD")) {
			return qualityMngMapper.radioPDIns();
		}
		
		return new ArrayList<>();
		
	}

}
