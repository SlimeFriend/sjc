package com.sjc.app.info.mapper;

import java.util.List;

import com.sjc.app.info.service.LineVO;
import com.sjc.app.sales.service.CpVO;

public interface InfoLineMapper {
	// 라인 조회
	public List<LineVO> selectLineAllList(LineVO lineVO);
	// 라인 수정
	public int updateLine(LineVO lineVO);
    // 라인 등록
	public int insertLine(LineVO lineVO);	
	// 라인 삭제
	public int deleteLine(LineVO lineVO);	

}
