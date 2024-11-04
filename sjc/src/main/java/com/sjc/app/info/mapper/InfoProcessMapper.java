package com.sjc.app.info.mapper;

import java.util.List;

import com.sjc.app.info.service.ProcessVO;

public interface InfoProcessMapper {
	// 공정 조회
	public List<ProcessVO> selectProcessAllList(ProcessVO processVO);
}
