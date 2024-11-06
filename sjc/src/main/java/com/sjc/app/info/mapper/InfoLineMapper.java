package com.sjc.app.info.mapper;

import java.util.List;

import com.sjc.app.info.service.LineVO;

public interface InfoLineMapper {
	// 라인 조회
	List<LineVO> selectLineAllList(LineVO lineVO);
	// 라인 수정
	int updateLine(LineVO lineVO);
	

}
