package com.sjc.app.quality.service;

import java.util.Date;

import lombok.Data;

@Data
public class InspectionVO {
	private String insCode; // 품질검사 코드
	private Date insDate; // 품질검사 일자
	private int userId; // 사용자 번호
	private String userName; // 사용자 이름
	private String insStatus; // 품질검사 상태
	private String insTypeCode; // 품질검사 유형코드
	private String mtlOdCode; // 자재발주 코드
	private int numberOfTests;// 검사수
	private int numberOfPasses;// 합격수
	private int numberOfFailed;// 불합격수
	private int numberOfTotalPass;// 총합격수
	private String totalPass; // 총합격여부

	
	
	// MtVO 
		private String mtCode; //자재코드
		private String mtName; //자재이름
		private String materialType; //자재구분
		private String specification; //규격
		private String unit; //단위
		private int unitPrice; //단가
		private int safetyStock; //안전재고
		private String mtComm; //비고
		private String stcCode; //재고코드
		private Date updateDate; //재고변동일
		private int currentStc; //현재재고
		private int leadtime; // 리드타임

		private int mtQuantity; //현재수량 
		private String prdName; //제품명
		
		
	// MtlOdVO
		private int mtlOdQuantity; //자재발주 수량
		private Date mtlOdDate;//자재발주 일자
		private Date diliveryDate;//납기일
		private int price;//금액
		private String cpCode;//업체 코드
		private String mtlOdStatus;//발주상태
		private String mtlOdComm;//발주비고
	// CpVO
		private String cpName; //업체이름
	// MtInVO
		private Date inputDate; //입고일
		private Integer inquantity; //입고수량
		private Date ed; //유통기한
		private String inCode; //입고코드
		private String lotNo; //LOT번호
		private String mtInComm; //비고
		
		
	// MtlOdDetailVO
		private String mtlOdDetailCode;
		private int mtlOdDetailQuantity;
		private String mtlOdDetailStatus;
		
	// InsItemVO
		private String insItemCode;
		private String insItemName;
		private String insItemCiteria;
		
		
	// InsTypeVO
		private String insTypeName;
		
	// InsDetailVO
		private String insDetailCode;
		private String insValue;
		private String insResult;
		
}
