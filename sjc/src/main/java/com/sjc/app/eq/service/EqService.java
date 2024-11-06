package com.sjc.app.eq.service;

import java.util.List;
import java.util.Map;


public interface EqService {
	// 전체 조회
	public List<EqVO> eqList();
	
	// 단건 조회
	public EqVO eqInfo(EqVO eqVO);
	
	// 등록
	public int eqInsert(EqVO eqVO);
	
	// 설비목록 수정
	public Map<String, Object> eqUpdate(EqVO eqVO);
	
	// 설비목록 수정
	public Map<String, Object> eqUpdate2(EqVO eqVO);
	
	// 삭제
	public int eqDelete(String eqCode);
	
	// 비가동 목록 중 일부 Eq쪽 조회
	public List<EqVO> eqList2();
	
	// 비가동 목록 중 EqChck 조회
	public List<EqChckVO> eqChckList();
	
	// 점검 목록 조회 eqChckVO 쪽
	public List<EqChckVO> jgList();
	
	// 비가동 목록 등록
	void saveNonOperating(EqChckVO eqChckVO);
	
	// 비가동 목록 "가동"으로 변경
	public Map<String, Object> updateEqChck(EqVO eqVO);
	
	// 점검 상태 업데이트
	public void updateCheckStatus(EqChckVO eqChckVO);

	// 점검 상
	public int selectEqChckOx(EqVO eqVO);
	
	// 비가동 내역 검색
	public List<EqChckVO> eqSearch(String eqCode, String startDate, String endDate);
	
}

