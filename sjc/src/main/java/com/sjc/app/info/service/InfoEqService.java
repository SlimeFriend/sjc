package com.sjc.app.info.service;

public interface InfoEqService {
    // 온도 수집
    void getTemperature();
    // 온도 랜덤 생성
    ChartDataVO getChartData();
    // 최근 설비 로그 조회
    ChartDataVO getEqLog();
}