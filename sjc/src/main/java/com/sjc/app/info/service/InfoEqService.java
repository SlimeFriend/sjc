package com.sjc.app.info.service;

import java.util.List;

public interface InfoEqService {
    // 온도 수집
    void getTemperature();
    // 온도 랜덤 생성
    ChartDataVO getChartData();
    // 최근 설비 로그 조회
    ChartDataVO getEqLog();
	// 설비 조회
	public List<EqDTO> getEq();    
}
