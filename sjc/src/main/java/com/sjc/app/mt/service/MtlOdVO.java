package com.sjc.app.mt.service;

import java.util.Date;

import lombok.Data;

@Data
public class MtlOdVO {
private String mtlOdCode; //자재발주 코드

private Integer mtlOdQuantity; //자재발주 수량
private Date mtlOdDate;//자재발주 일자
private Date diliveryDate;//납기일
private Integer price;//금액
private String mtCode;//자재 코드
private String cpCode;//업체 코드
private Integer userId;//사용자번호
private String mtlOdStatus;//발주상태


}
