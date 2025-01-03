package com.sjc.app.quality.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class InspectionVO {
	private String insCode; // 품질검사 코드
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
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
	
	
	private String mtlPdetailCode; //제품코드(mtlod,Pdetail)

	
	
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
		
		
	// MtlOdVO
		private int mtlOdQuantity; //자재발주 수량
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
		private Date mtlOdDate;//자재발주 일자
		private Date diliveryDate;//납기일
		private int price;//금액
		private String cpCode;//업체 코드
		private String mtlOdStatus;//발주상태
		private String mtlOdComm;//발주비고
	// CpVO
		private String cpName; //업체이름
	// MtInVO
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
		private Date inputDate; //입고일
		private Integer inquantity; //입고수량
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
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
		
		
	//	PDetailVO 
		private String pdetailCode;	// 생산지시상세 코드
		private String prdName;		// 제품이름
		private Integer want;		// 목표량
		private Integer command;	// 지시량
		private Integer output;		// 생산량
		private Integer pdetailNo;			// 우선순위
		private String pdetailComm;		// 비고
		private String pdetailStatus;		// 상태
		private String lineCode;	// 라인 코드
		private String prdCode; 	// 제품 코드
	
	//	POrderVO 
	    private String porderCode;
    	@DateTimeFormat(pattern = "yyyy-MM-dd")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	    private Date startDate;
    	@DateTimeFormat(pattern = "yyyy-MM-dd")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	    private Date porderDate;
	    private String manager;
	    private String pordercomm;
	    private String porderStatus;
	    
	 //   PlanDVO 
	    private String planDetailCode;  // 생산계획상세 코드
	    private Integer planDetailQuantity;        // 수량
	    private Integer planDetailNo;                // 우선순위
	    private String planDetailComm;            // 비고
	    private String planCode;        // 생산코드
	    
	    
	    
	   // PResultVO 
	    	private String presultCode;		// 생산공정실적 코드
	    	private String startTime;		// 시작 시간
	    	private String endTime;			// 종료 시간
	    	private Integer err;			// 불량량
	    	@DateTimeFormat(pattern = "yyyy-MM-dd")
	        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	    	private Date processDate;		// 공정 일자
	    	
	    	
	    //	PrdManagementVO 
	    		private String inOutType;
	    		private String lot;
	    		//private Integer inQuantity;
	    		private Integer inQ;
	    		private Integer outQuantity;
	    		private Integer stcQuantity;
	    		private Integer remainQuantity;
	    		
	    	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	    		private Date md;

	    		
	    	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	    		private Date inDate;
	    	    
	    	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	    		private Date outDate;
	    

}
