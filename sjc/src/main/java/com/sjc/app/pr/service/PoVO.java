package com.sjc.app.pr.service;

import java.util.List;


import lombok.Data;

@Data
public class PoVO {
	 private POrderVO porderVO;
	 private List<LinePrdVO> linePrdVO;
}
