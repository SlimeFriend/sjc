package com.sjc.app.info.service;

import java.util.List;

import com.sjc.app.mt.service.MtVO;

public interface InfoMtService {
	// 자재 조회
	public List<MtVO> mtList(MtVO mtVO);
	// 자재 재고 조회
	public List<MtVO> mtStockList();
	
}
