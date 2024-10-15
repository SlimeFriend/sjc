package com.sjc.app.mt.service;

import java.util.Date;

import lombok.Data;

@Data
public class OrderRequestVO {
    private String orderRequestCode; // 발주 요청 코드
    private Date orderDate;          // 발주일
    private String mtCode;           // 자재 코드
    private int quantity;            // 수량
    private String cpCode;           // 업체 코드
    private String userId;           // 사용자 ID
    private String comm;          // 비고
}