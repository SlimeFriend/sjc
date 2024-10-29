package com.sjc.app.info.service;

import com.sjc.app.sales.service.ProductVO;

import lombok.Data;

@Data
public class PrdBomDTO {
    private ProductVO prd;
    private BomVO bom;
}