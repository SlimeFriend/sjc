package com.sjc.app.info.service;

import java.util.List;

import com.sjc.app.mt.service.MtVO;
import com.sjc.app.sales.service.CpVO;

public interface InfoMtService {
	// 자재 조회
	public List<MtVO> mtList(MtVO mtVO);
	// 자재 재고 조회
	public List<MtVO> mtStockList();
	// 자재 입력
	public List<MtVO> insertMts(List<MtVO> mtVOs);	
    // 자재 삭제
    public List<MtVO> deleteMts(List<MtVO> mtVOs);    		
    // 자재 수정
    public List<MtVO> updateMts(List<MtVO> mtVOs);    		
	
}
