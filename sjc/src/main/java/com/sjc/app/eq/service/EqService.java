package com.sjc.app.eq.service;

import java.util.List;

public interface EqService {
	// 전체 조회
	public List<EqVO> eqList();
	
	// 단건 조회
	public EqVO eqInfo(EqVO eqVO);
	
	// 등록
	public int eqInsert(EqVO eqVO);
	
	// 수정
	
	// 삭제
	
	// 비가동 목록 중 일부 Eq쪽 조회
	public List<EqVO> eqList2();
	
	// 비가동 목록 중 EqChck 조회
	public List<EqChckVO> eqChckList();
	
	// 점검 목록 조회 eqChckVO 쪽
	public List<EqChckVO> jgList();
	
}
