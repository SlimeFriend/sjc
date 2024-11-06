package com.sjc.app.mt.service;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class MtOutVO {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm", timezone = "Asia/Seoul")
    private Date outDate;            // 출고일자
	
    private Integer mtOutCount;       // 출고수량
    private String outputTarger;      // 출고처
    private String outCode;           // 출고 코드
    private String lotNo;             // LOT 번호
    private String mtCode;            // 자재 코드
    private Integer userId;           // 사용자 번호
    private String pDetailCode;       // 생산지시 상세 코드

}
