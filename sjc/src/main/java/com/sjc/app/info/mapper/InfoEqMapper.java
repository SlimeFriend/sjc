package com.sjc.app.info.mapper;

import com.sjc.app.info.service.EqLogVO;

public interface InfoEqMapper {
	// 설비 로그 저장
	int insertEqLog(EqLogVO eqLogVO);
	// 설비 로그 조회
	double selectEqLog(EqLogVO eqLogVO);
	// 설비 로그 시간 조회
	String selectEqLogTime();
}
