package com.sjc.app.sales.service;

import java.util.List;

import lombok.Data;

@Data
public class SalesDTO {
	private OrderVO orderVO;
	private List<ProductVO> productVO;
}
