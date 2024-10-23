package com.sjc.app.eq.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class EqVO {
	private String eqCode;		  // 설비 코드
	private String eqMdnm;		  // 모델명
	private String use;			  // 사용 여부
	private String eqMdno;		  // 모델 번호
	private String eqDep;		  // 담당부서
	private Integer eqTemp;		  // 가용 온도
	private Integer eqMin;		  // 최저 온도
	private Integer eqMax;		  // 최고 온도
	private Integer eqStandTemp;  // 기준 온도
	private Integer eqPeriod;	  // 점검 주기
	private String manager;		  // 담당자
	private String eqLocation;	  // 설치 위치
	private String eqImg;		  // 이미지
	private String lineCode;	  // 라인
	private String eqType;		  // 설비 구분
	private String eqName;		  // 설비명
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date eqCkDate;		  // 점검일자
	private String description;	  // 설명
	private Integer userId;	  	  // 사용자 번호
	
	// 조인 정보 받아온 속성
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date eqNextChck;	// 다음 점검일
	private String eqChckOx;	// 점검 유무
}
