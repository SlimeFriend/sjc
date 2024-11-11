package com.sjc.app.info.service;

import java.util.List;

import com.sjc.app.sales.service.CpVO;

public interface InfoLineService {
	// 라인 조회
	public List<LineVO> lineList(LineVO lineVO);
	// 라인 수정
	public List<LineVO> modifyLines(List<LineVO> lineVOs);
	// 라인 입력
	public List<LineVO> insertLines(List<LineVO> lineVOs);	
	// 라인 삭제
	public List<LineVO> deleteLines(List<LineVO> lineVOs);	
	
}
