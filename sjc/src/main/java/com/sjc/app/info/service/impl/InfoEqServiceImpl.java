package com.sjc.app.info.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjc.app.info.mapper.InfoEqMapper;
import com.sjc.app.info.service.ChartDataVO;
import com.sjc.app.info.service.ChartDataVO.SeriesVO;
import com.sjc.app.info.service.EqDTO;
import com.sjc.app.info.service.EqLogVO;
import com.sjc.app.info.service.InfoEqService;
@Service
public class InfoEqServiceImpl implements InfoEqService {
	private InfoEqMapper infoEqMapper;
	
	@Autowired
	public InfoEqServiceImpl(InfoEqMapper infoEqMapper) {
		this.infoEqMapper = infoEqMapper;
	}

	@Override
	public void getTemperature() {
		System.out.println("--------");
		System.out.println("온도 모니터링");
		System.out.println("--------");
	}

	// 랜덤 온도 생성
	@Override
    public ChartDataVO getChartData() {
        ChartDataVO chartData = new ChartDataVO();
        
        // 현재 시간 기준으로 카테고리(시간) 생성
        String currentTime = LocalDateTime.now()
        		//.format(DateTimeFormatter.ofPattern("MM-dd HH:mm"));
        		.format(DateTimeFormatter.ofPattern("MM-dd HH:mm:ss"));

        List<String> categories = new ArrayList<>();
        categories.add(currentTime);
        
        // 140~145 사이의 랜덤 값 생성
        double moldValue = Math.round((140 + Math.random() * 5) * 10.0) / 10.0;
        double mixValue = Math.round((140 + Math.random() * 5) * 10.0) / 10.0;
//        String moldValue =  Double.toString(Math.round((140 + Math.random() * 5) * 10.0) / 10.0);
//        String mixValue = Double.toString(Math.round((140 + Math.random() * 5) * 10.0) / 10.0);
        
        // 성형기 데이터
        SeriesVO moldSeries = new ChartDataVO.SeriesVO();
        moldSeries.setName("성형기");
        moldSeries.setData(Arrays.asList(moldValue));
        
        // 혼합기 데이터
        SeriesVO mixSeries = new ChartDataVO.SeriesVO();
        mixSeries.setName("혼합기");
        mixSeries.setData(Arrays.asList(mixValue));
        
        // series 데이터 설정
        chartData.setSeries(Arrays.asList(moldSeries, mixSeries));
        chartData.setCategories(categories);
        
        return chartData;
    }
	
	@Override
	public ChartDataVO getEqLog() {

        ChartDataVO chartData = new ChartDataVO();

		List<String> categories = new ArrayList<>();
		String currentTime = infoEqMapper.selectEqLogTime();
		categories.add(currentTime);
		
		EqLogVO moldEqLogVO = new EqLogVO();
		EqLogVO mixEqLogVO = new EqLogVO();		
		
		moldEqLogVO.setEqName("성형기");
		mixEqLogVO.setEqName("배합기");
		
//		double moldValue = infoEqMapper.selectEqLog(moldEqLogVO);
//		double mixValue = infoEqMapper.selectEqLog(mixEqLogVO);
		moldEqLogVO.setEqLogData(infoEqMapper.selectEqLog(moldEqLogVO));
		mixEqLogVO.setEqLogData(infoEqMapper.selectEqLog(mixEqLogVO));
		
		// 성형기 데이터
		SeriesVO moldSeries = new ChartDataVO.SeriesVO();
		moldSeries.setName(moldEqLogVO.getEqName());
//		moldSeries.setData(Arrays.asList(moldEqLogVO.getEqLogData()));
	    moldSeries.setData(Arrays.asList(Double.valueOf(moldEqLogVO.getEqLogData())));

		// 혼합기 데이터
		SeriesVO mixSeries = new ChartDataVO.SeriesVO();
		mixSeries.setName(mixEqLogVO.getEqName());
//		mixSeries.setData(Arrays.asList(mixEqLogVO.getEqLogData()));
		mixSeries.setData(Arrays.asList(Double.valueOf(mixEqLogVO.getEqLogData())));
		
		// series 데이터 설정
		chartData.setSeries(Arrays.asList(moldSeries, mixSeries));
		chartData.setCategories(categories);
		
		return chartData;
	}

	// 주문 조회
	@Override
	public List<EqDTO> getEq() {
		return infoEqMapper.selectEqCount();
	}	
	
}
