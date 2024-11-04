package com.sjc.app.info.mapper;

import java.util.List;

import com.sjc.app.mt.service.MtVO;

public interface InfoMtMapper {
	// 자재 조회
	public List<MtVO> selectMtAllList(MtVO mtVO);
}
