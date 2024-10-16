package com.sjc.app.info.mapper;

import java.util.List;

import com.sjc.app.info.service.LineVO;

public interface InfoLineMapper {
	public List<LineVO> selectLineAllList(LineVO lineVO);
}
