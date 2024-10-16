package com.sjc.app.eq.mapper;

import java.util.List;

import com.sjc.app.eq.service.EqVO;

public interface EqMapper {
	// 전체 조회
	public List<EqVO> selectEqAll();
	
	// 단건 조회
	public EqVO selectEqInfo(EqVO eqVO);
	
	// 등록 : 모델명, 사용여부, 설비코드, 모델번호, 담당부서, 가용온도, 점검주기, 사원번호, 담당자, 설치위치, 설비사진
	public int insertEqInfo(EqVO eqVO);
	
}
