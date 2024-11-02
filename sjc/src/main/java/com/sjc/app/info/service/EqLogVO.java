package com.sjc.app.info.service;

import java.util.Date;

import lombok.Data;

@Data
public class EqLogVO {
	Integer eqLogId;
	String 	eqName;
	double 	eqLogData;
	Date	eqLogDate;
}
