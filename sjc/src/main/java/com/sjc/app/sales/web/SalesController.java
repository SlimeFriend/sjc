package com.sjc.app.sales.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sjc.app.sales.service.OrderVO;
import com.sjc.app.sales.service.ProductVO;
import com.sjc.app.sales.service.SalesService;

@Controller
public class SalesController {
	
	private SalesService salesService;
	
	@Autowired
	SalesController(SalesService salesService) {
		this.salesService = salesService;
	}
	
	// 주문접수 페이지
	@GetMapping("/orderReception")
	public String orderReceptionPage(Model model) {
	    List<ProductVO> productList = salesService.productList();
	    List<OrderVO> companyList = salesService.companyList();
	    
	    model.addAttribute("products", productList);
	    model.addAttribute("companyList", companyList);
	    
	    return "sales/orderReception";
	}
    
	// 주문내역 페이지
    @GetMapping("/orderHistory")
    public String orderHistoryPage(Model model) {
    	List<OrderVO> list = salesService.orderHistory();
    	model.addAttribute("orderHistory", list);
        return "sales/orderHistory";
    }
    
	// 제품관리 페이지
    @GetMapping("/productManagement")
    public String productManagementPage(Model model) {
    	List<ProductVO> list = salesService.productManagement();
    	model.addAttribute("productManagement", list);
    	model.addAttribute("products", list);
        return "sales/productManagement";
    }
    
	// 제품 입고 페이지
    @GetMapping("/productIn")
    public String productInPage(Model model) {
    	List<ProductVO> list = salesService.productIn();
    	model.addAttribute("productIn", list);
        return "sales/productIn";
    }
    
	// 제품 출고 페이지
    @GetMapping("/productOut")
    public String productOutPage(Model model) {
    	List<OrderVO> list = salesService.productOut();
    	model.addAttribute("productOut", list);
        return "sales/productOut";
    }
    
	// 입/출고 내역 페이지
    @GetMapping("/inoutHistory")
    public String inoutHistoryPage(Model model) {
    	List<ProductVO> list = salesService.inoutHistory();
    	model.addAttribute("inoutHistory", list);
        return "sales/inoutHistory";
    }
    
}
