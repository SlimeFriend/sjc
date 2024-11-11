package com.sjc.app.info.service;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class LineVO {
	private String lineCode;//라인코드
	private String lineName;//라인명
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String prdCode;	//제품코드
	private String prdName;	//제품코드
	private String use;		//사용여부
	private LocalDate lineDate;//수정일
}
