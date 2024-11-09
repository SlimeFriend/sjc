package com.sjc.app.info.mapper;

import java.util.List;

import com.sjc.app.info.service.EqDTO;
import com.sjc.app.info.service.EqLogVO;

public interface InfoEqMapper {
	// 설비 로그 저장
	public int insertEqLog(EqLogVO eqLogVO);
	// 설비 로그 조회
	public double selectEqLog(EqLogVO eqLogVO);
	// 설비 로그 시간 조회
	public String selectEqLogTime();
	// 설비 조회
	public List<EqDTO> selectEqCount();
	// 설비 가동,비가동,점검 조회
	public List<EqDTO> selectEqChckCount();

}
