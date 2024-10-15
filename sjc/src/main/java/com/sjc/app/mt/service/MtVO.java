package com.sjc.app.mt.service;

import java.util.Date;

import lombok.Data;

@Data
public class MtVO {
private String mtCode; //자재코드
private String mtName; //자재이름
private String materialType; //자재구분
private String specification; //규격
private String unit; //단위
private Integer unitPrice; //단가
private Integer safetyStock; //안전재고
private String comm; //비고
private String stcCode; //재고코드
private Date updateDate; //재고변동일
private Integer currentStc; //현재재고
private Integer leadtime; // 리드타임

private Integer quantityRequired; //현재수량 
private String prdName; //제품명
}
