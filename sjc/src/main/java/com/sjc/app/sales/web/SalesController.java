package com.sjc.app.sales.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sjc.app.sales.service.PrdVO;
import com.sjc.app.sales.service.SalesService;

@Controller
public class SalesController {
	
	private SalesService salesService;
	
	@Autowired
	SalesController(SalesService salesService) {
		this.salesService = salesService;
	}
	
	// 주문접수 페이지 이동
	@GetMapping("/orderReception")
	public String orderReceptionPage(Model model) {
	    List<PrdVO> list = salesService.prdList();
	    model.addAttribute("products", list);
	    return "sales/orderReception";
	}
    
    @GetMapping("/orderHistory")
    public String orderHistoryPage() {
        return "sales/orderHistory";
    }
    
}
