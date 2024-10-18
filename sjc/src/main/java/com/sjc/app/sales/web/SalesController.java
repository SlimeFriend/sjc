package com.sjc.app.sales.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sjc.app.sales.service.OrderVO;
import com.sjc.app.sales.service.ProductVO;
import com.sjc.app.sales.service.SalesDTO;
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
		model.addAttribute("orderVO", new OrderVO());

		return "sales/orderReception";
	}
	
	// 주문접수 프로세스
	@PostMapping("/orderReception")
	public String insertOrder(@RequestBody SalesDTO salesDTO) {
		
		 String ordCode = "ORD" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + (int)(Math.random() * 1000);
		 
	    OrderVO orderVO = salesDTO.getOrderVO();
	    List<ProductVO> productVOList = salesDTO.getProductVO(); 
	    
	    orderVO.setOrdCode(ordCode);
	    
	    System.err.println(orderVO);
	    System.err.println(productVOList);
	    
	    int orderResult = salesService.insertOrder(orderVO);
	    
	    if(orderResult > 0) {
	        productVOList.forEach(productVO -> {
	            salesService.insertOrderDetail(productVO, orderVO.getOrdCode());
	        });
	    }

	    return "redirect:/main";
	}
	
	// 주문내역 페이지
	@GetMapping("/orderHistory")
	public String orderHistoryPage(Model model) {
		List<OrderVO> orderList = salesService.orderHistory();
		model.addAttribute("ord", orderList);
		model.addAttribute("ordDetail", orderList);
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
