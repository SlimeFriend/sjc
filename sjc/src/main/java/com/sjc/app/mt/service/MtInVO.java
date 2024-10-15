package com.sjc.app.mt.service;

import java.util.Date;

import lombok.Data;

@Data
public class MtInVO {
private Date inputDate; //입고일
private Integer inquantity; //입고수량
private Date ed; //유통기한
private String inCode; //입고코드
private Integer userId; // 유저아이디
private String mtlOdCode; //자재발주 코드
private String insCode; //품질검사코드
private String mtCode; //자재 코드
private String cpCode; // 업체 코드
private String comm; //비고
private String lotNo; //LOT번호
}
