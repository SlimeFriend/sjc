package com.sjc.app.info.mapper;

import java.util.List;

import com.sjc.app.sales.service.PrdVO;

public interface InfoPrdMapper {
	public List<PrdVO> selectPrdAllList(PrdVO prdvo);
}
