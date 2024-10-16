package com.sjc.app.info.mapper;

import java.util.List;

import com.sjc.app.info.service.BomVO;

public interface InfoBomMapper {
	public List<BomVO> selectBomAllList(BomVO bomvo);
}
