package com.sjc.app.info.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjc.app.info.mapper.InfoLineMapper;
import com.sjc.app.info.service.LineVO;
import com.sjc.app.info.service.InfoLineService;
import com.sjc.app.info.service.InfoUserVO;

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

	// 사용자 수정
    @Override
    @Transactional
    public List<LineVO> modifyLines(List<LineVO> lineVOs) {
    	
        for (LineVO lineVO : lineVOs) {
            int lineUpdateCount = infoLineMapper.updateLine(lineVO);
            // 라인 없으면 등록
            
            if (lineUpdateCount == 0) {
//            	infoLineMapper.insertLine(lineVO);
            }

        }
        return lineVOs;
    }

}
