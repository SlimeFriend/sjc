package com.sjc.app.info.mapper;

import java.util.List;

import com.sjc.app.mt.service.MtVO;

public interface InfoMtMapper {
	public List<MtVO> selectMtAllList(MtVO mtVO);
}
