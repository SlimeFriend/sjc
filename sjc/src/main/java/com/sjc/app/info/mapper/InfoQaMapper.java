package com.sjc.app.info.mapper;

import java.util.List;

import com.sjc.app.info.service.InspectionDTO;

public interface InfoQaMapper {
	
	public List<InspectionDTO> selectQaPassRate();
}

