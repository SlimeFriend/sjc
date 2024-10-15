package com.sjc.app.mt.service;

import java.util.Date;

import lombok.Data;

@Data
public class MtOutVO {
private Date outDate; //출고일자
private Integer mtOutCount; //출고수량
private String outputTarger; //출고 처
private String outCode; //출고 코드
private String lotNo; //lot번호
private String mtCode; //자재 코드
private Integer userId; //사용자번호

}
