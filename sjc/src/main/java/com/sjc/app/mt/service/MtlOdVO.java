package com.sjc.app.mt.service;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class MtlOdVO {
    
    // MTL_OD - 자재 발주 정보
    private String mtlOdCode;         // 자재 발주 코드
    private Integer mtlOdQuantity;    // 자재 발주 수량
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm", timezone = "Asia/Seoul")
    private Date mtlOdDate;           // 자재 발주 일자
    private Date diliveryDate;        // 납기일
    private Integer price;            // 금액
    private String mtCode;            // 자재 코드

    private Integer userId;           // 사용자 번호
    private String mtlOdStatus;       // 발주 상태
    private String comm;              // 비고
    
    // MTL_OD_DETAIL - 자재 발주 상세 정보
    private String mtlOdDetailCode;   // 자재 발주 상세 코드
    private Integer quantity;         // 자재 수량
    private String cpCode;            // 업체 코드
    private String cpName;            // 업체 이름

    private long totalAmount;         // 총 금액
    private String specification;     // 자재 규격
    private Integer unitPrice;        // 단가
    private String mtName;            // 자재 이름
    private String userName;          // 사용자 이름
}
