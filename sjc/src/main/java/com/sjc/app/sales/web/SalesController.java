package com.sjc.app.sales.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SalesController {
	@GetMapping("order")
	public String order() {
		return "sales/order";
	}
}
