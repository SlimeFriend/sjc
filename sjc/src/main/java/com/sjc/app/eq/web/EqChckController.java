package com.sjc.app.eq.web;

import org.springframework.stereotype.Controller;

import com.sjc.app.eq.service.EqChckService;

@Controller
public class EqChckController {
	private EqChckService eqChckService;
	
	public EqChckController(EqChckService eqChckService) {
		this.eqChckService = eqChckService;
	}

}
