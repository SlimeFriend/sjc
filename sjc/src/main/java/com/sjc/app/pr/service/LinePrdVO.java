package com.sjc.app.pr.service;

import lombok.Data;

@Data
public class LinePrdVO {
	private String prdCode;				// 자재코드
	private String prdName;				// 자재이름
	private String lineCode;            // 라인코드
	private Integer want;				// 목표량
	private Integer command;			// 지시량
}
