package com.sjc.app.mt.service;

import java.util.Date;
import lombok.Data;

@Data
public class MtlOdVO {
    // MTL_OD
    private String mtlOdCode;         // 자재발주 코드
    private Integer mtlOdQuantity;    // 자재발주 수량
    private Date mtlOdDate;           // 자재발주 일자
    private Date diliveryDate;        // 납기일
    private Integer price;            // 금액
    private String mtCode;            // 자재 코드

    private Integer userId;           // 사용자번호
    private String mtlOdStatus;       // 발주상태
    private String comm;              // 비고

    // MTL_OD_DETAIL
    private String mtlOdDetailCode;   // 자재발주상세코드
    private Integer quantity;         // 자재수량
    private String cpCode;            // 업체 코드

   
    private long totalAmount;         // 총 금액
    private String specification;     // 규격
    private Integer unitPrice;        // 단가
    private String mtName;            // 자재이름
    private String userName;          // 사용자 이름
}
