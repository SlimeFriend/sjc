package com.sjc.app.mt.service;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class MtVO {
	private String mtCode; // 자재코드
	private String mtName; // 자재이름
	private String materialType; // 자재구분
	private String specification; // 규격
	private String unit; // 단위
	private Integer unitPrice; // 단가
	private Integer safetyStock; // 안전재고
	private String comm; // 비고
	private String stcCode; // 재고코드
	private Date updateDate; // 재고변동일
	private Integer currentStc; // 현재재고
	private Integer leadtime; // 리드타임

	private Integer quantityRequired; // 필요수량
	private String prdName; // 제품명
	private Integer totalRequired; // 총소요량 추가
	private Integer quantity; // 제품수량
	private String lotNo; //LOT번호
	 private List<MtInVO> lotDetails;
	 private Integer totalQuantity; // 수량 합계
	
}