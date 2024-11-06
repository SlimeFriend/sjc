package com.sjc.app.info.service;

import java.util.List;

public interface InfoLineService {
	// 라인 조회
	List<LineVO> lineList(LineVO lineVO);
	// 라인 수정
	List<LineVO> modifyLines(List<LineVO> lineVOs);
	
}
