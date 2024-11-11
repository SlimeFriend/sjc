package com.sjc.app.eq.mapper;

import java.util.List;

import com.sjc.app.eq.service.EqChckVO;
import com.sjc.app.eq.service.EqVO;

public interface EqMapper {
	// 전체 조회
	public List<EqVO> selectEqAll();
	
	// 단건 조회
	public EqVO selectEqInfo(EqVO eqVO);
	
	// 등록 : 모델명, 사용여부, 설비코드, 모델번호, 담당부서, 가용온도, 점검주기, 사원번호, 담당자, 설치위치, 설비사진
	public int insertEqInfo(EqVO eqVO);
	
	// 목록 수정
	public int updateEqInfo(EqVO eqVO);
	
	// 상세 수정
	public int updateEqInfo2(EqVO eqVO);
	
	// 삭제
	public int deleteEqInfo(String eqCode);

	// 설비 가동 상태
	public List<EqVO> selectEqAll2();
	
	// 비가동 목록
	public List<EqChckVO> selctEqChckAll();
	
	// 설비 점검 목록
	public List<EqChckVO> selectjumgumAll();
	
	// 설비 점검 상태 업데이트
	public int updateCheckStatus(EqChckVO eqChckVO);
	
	// 비가동 내역 등록
	void insertNonOperating(EqChckVO eqChckVO);
	

	// 비가동 목록 "가동"으로 변경
	public int updateEqChckInfo(EqVO eqVO);
	
	// 비가동 목록 "가동"으로 변경 New
	public int updateEqChckNew(EqVO eqVO);
	

	// 라인 수정
	int updateLine(EqVO eqVO);	

	// 점검 일자 등록
	public int updateEqInfoJumgum(EqVO eqVO);
	
	// 점검 유무 조회
	public int selectEqChckOx(EqVO eqVO);
	
	// 비가동 내역 검색
	public List<EqChckVO> eqSearch(String eqCode, String startDate, String endDate);

}
