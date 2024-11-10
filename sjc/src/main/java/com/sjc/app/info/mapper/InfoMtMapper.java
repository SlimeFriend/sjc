package com.sjc.app.info.mapper;

import java.util.List;

import com.sjc.app.mt.service.MtVO;
import com.sjc.app.sales.service.CpVO;

public interface InfoMtMapper {
	// 자재 조회
	public List<MtVO> selectMtAllList(MtVO mtVO);
	// 자재 재고 조회
	public List<MtVO> selectMtStockList();
    // 자재 등록
	public int insertMt(MtVO mtVO);
    // 자재 삭제
    public int deleteMt(MtVO mtVO);	
    // 자재 수정
    public int updateMt(MtVO mtVO);	
}
