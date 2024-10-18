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
	private String insSort; // 품질검사 종류 - 제품, 자재, ...
	private String insTarget; // 품질검사 대상 - 제품번호, 자재번호, ...
	private String insTypeCode; // 품질검사 유형코드
	private String mtlOdCode; // 자재발주 코드
	private int numberOfTests;// 검사수
	private int numberOfPasses;// 합격수
	private int numberOfFailed;// 불합격수
	private String totalPass; // 총합격여부

	
	
	// MtVO 
		private String mtCode; //자재코드
		private String mtName; //자재이름
		private String materialType; //자재구분
		private String specification; //규격
		private String unit; //단위
		private int unitPrice; //단가
		private int safetyStock; //안전재고
		private String comm; //비고
		private String stcCode; //재고코드
		private Date updateDate; //재고변동일
		private int currentStc; //현재재고
		private int leadtime; // 리드타임

		private int quantityRequired; //현재수량 
		private String prdName; //제품명
		
		
	// MtlOdVO
		private int mtlOdQuantity; //자재발주 수량
		private Date mtlOdDate;//자재발주 일자
		private Date diliveryDate;//납기일
		private int price;//금액
		private String cpCode;//업체 코드
		private String mtlOdStatus;//발주상태
		
		
	// InsItemVO
		private String insItemCode;
		private String insItemName;
		private String insItemCiteria;
		
		
	// InsTypeVO
		private String insTypeName;
		
}
