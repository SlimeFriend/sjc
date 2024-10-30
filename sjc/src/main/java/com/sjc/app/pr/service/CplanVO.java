package com.sjc.app.pr.service;

import java.util.List;

import com.sjc.app.sales.service.ProductVO;

import lombok.Data;

@Data
public class CplanVO {
	private PlanVO planVO;
	private List<ProductVO> productVO;
}
