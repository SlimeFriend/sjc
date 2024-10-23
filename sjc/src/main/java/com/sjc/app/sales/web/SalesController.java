package com.sjc.app.sales.web;

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
import com.sjc.app.sales.service.outHistoryVO;

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
		
		String nextId = salesService.getOrdCode();

		String ordCode = String.valueOf(nextId);
		 
	    OrderVO orderVO = salesDTO.getOrderVO();
	    List<ProductVO> productVOList = salesDTO.getProductVO(); 
	    
	    orderVO.setOrdCode(ordCode);
	    
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
	
	// 제품관리 상세 페이지
	@PostMapping("/getProductDetail")
	@ResponseBody
	public List<Map<String, Object>> getProductDetail(@RequestBody Map<String, String> requestData) {
		String prdCode = requestData.get("prdCode");
		
		List<Map<String, Object>> prdDetail = salesService.productDetail(prdCode);
		
		return prdDetail;
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
		model.addAttribute("orderGrid", list);
		return "sales/productOut";
	}
	
	// 제품 출고 상세 페이지
	@PostMapping("/getOrdDetail")
	@ResponseBody
	public List<Map<String, Object>> getOrdDetail(@RequestBody Map<String, String> requestData) {
	    String ordCode = requestData.get("ordCode");
	    return salesService.orderDetail(ordCode);  // 서비스 계층에서 제품 상세 정보를 가져옴
	}
	
	// 제품 출고 상세 페이지 LOT
	@PostMapping("/getPrdLotDetail")
	@ResponseBody
	public List<Map<String, Object>> getPrdLotDetail(@RequestBody Map<String, String> requestData) {
		String prdCode = requestData.get("prdCode");
		List<Map<String, Object>> prdDetail = salesService.productDetail(prdCode);
		return prdDetail;
	}
	
	// 제품출고 프로세스
	@PostMapping("/prdOutProcess")
	@ResponseBody
	public String prdOutProcess(@RequestBody List<Map<String, Object>> requestData) {
		for (Map<String, Object> data : requestData) {
	        String lot = (String) data.get("lot"); // LOT 값
	        int outQuantity = ((Number) data.get("outQuantity")).intValue(); // 출고량
	        
	        System.err.println("LOT: " + lot + ", Out Quantity: " + outQuantity);
	        salesService.productOutProcess(lot, outQuantity);
	    }
	    return "출고 처리 성공"; // 적절한 응답 반환
	}

	// 입/출고 내역 페이지
	@GetMapping("/inoutHistory")
	public String inoutHistoryPage(Model model) {
		List<outHistoryVO> list = salesService.outHistory();
		model.addAttribute("outHistory", list);
		return "sales/inoutHistory";
	}
	
	// 주문성공시 페이지
	@GetMapping("/salesOrderHistory")
	public String inoutHistoryPage() {
		return "sales/orderHistory";
	}

}
