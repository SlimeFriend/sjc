package com.sjc.app.pr.service;

import lombok.Data;

@Data
public class NeedVO {
	private String mtCode;				// 자재코드
	private String mtName;				// 자재이름
	private Integer quantityRequired;    // 필요수량
	private Integer currentStc;         // 재고량
}
