package com.sjc.app.info.mapper;

import java.util.List;

import com.sjc.app.info.service.ProcessVO;

public interface InfoProcessMapper {
	public List<ProcessVO> selectProcessAllList(ProcessVO processVO);
}
