package com.sjc.app.info.mapper;

import java.util.List;

import com.sjc.app.sales.service.ProductVO;

public interface InfoPrdMapper {
	public List<ProductVO> selectPrdAllList(ProductVO prductVO);
}
