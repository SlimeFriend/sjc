package com.sjc.app.quality.service;

import java.util.Date;

import lombok.Data;

@Data
public class InspectionVO {
	private String insCode;
	private Date insDate;
	private int userNo;
	private String userName;
	private String insStatus;
	private String insSort;
	private String insTarget;
	private String insTypeCode;
	
}
