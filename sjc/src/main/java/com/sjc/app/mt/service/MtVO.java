package com.sjc.app.mt.service;

import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class MtVO {

    private String mtCode;             // 자재 코드
    private String mtName;             // 자재 이름
    private String materialType;       // 자재 구분
    private String specification;      // 규격
    private String unit;               // 단위
    private Integer unitPrice;         // 단가
    private Integer safetyStock;       // 안전 재고
    private String comm;               // 비고
    private String stcCode;            // 재고 코드
    private Date updateDate;           // 재고 변동일
    private Integer currentStc;        // 현재 재고
  
    private Integer quantityRequired;  // 필요 수량
    private String prdName;            // 제품명
    private Integer totalRequired;     // 총 소요량
    private Integer quantity;          // 제품 수량
    private String lotNo;              // LOT 번호
    private List<MtInVO> lotDetails;   // LOT 상세 정보
    private Integer totalQuantity;     // 수량 합계
    private String mtlOdCode;          // 자재 발주 코드
    private String cpCode;             // 업체 코드

    private String status;             // 상태 필드 추가
}
