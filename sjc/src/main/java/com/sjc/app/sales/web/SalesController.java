package com.sjc.app.sales.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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
		
		int nextId = salesService.getOrdCode();
		System.err.print(nextId);
		String ordCode = "ORD" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + String.format("%03d", nextId);
		 
	    OrderVO orderVO = salesDTO.getOrderVO();
	    List<ProductVO> productVOList = salesDTO.getProductVO(); 
	    
	    orderVO.setOrdCode(ordCode);
	    
	    System.err.println(orderVO);
	    System.err.println(productVOList);
	    System.err.println(salesDTO);
	    
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
		List<OrderVO> order = salesService.order();
		model.addAttribute("ord", order);
		return "sales/orderHistory";
	}
	
	// 주문내역 상세페이지
	@PostMapping("/getOrderDetail")
	@ResponseBody
	public List<Map<String, Object>> getOrderDetail(@RequestBody Map<String, String> requestData) {
		String ordCode = requestData.get("ordCode");
		
		List<Map<String, Object>> ordDetail = salesService.orderDetail(ordCode);
		
		return ordDetail;
	}
	

	// 제품관리 페이지
	@GetMapping("/productManagement")
	public String productManagementPage(Model model) {
		List<ProductVO> productList = salesService.productManagement();
		List<ProductVO> productLotList = salesService.productLot();
		model.addAttribute("products", productList);
		model.addAttribute("productManagement", productLotList);
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
