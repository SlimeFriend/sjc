package com.sjc.app.info.service;

import com.sjc.app.sales.service.OrderVO;

import lombok.Data;

@Data
public class OrdDTO extends OrderVO{
	private String OrderCount;
	private String TotalQuantity;
	private String PrdName;
}
