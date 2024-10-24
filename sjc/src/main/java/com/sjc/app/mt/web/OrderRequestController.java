package com.sjc.app.mt.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.sjc.app.mt.service.MtlOdVO;
import com.sjc.app.mt.service.MtVO;
import com.sjc.app.mt.service.OrderRequestService;
import com.sjc.app.sales.service.CpVO;

@Controller
public class OrderRequestController {

    @Autowired
    private OrderRequestService orderRequestService;

    // 발주 요청 목록 페이지
    @GetMapping("/orderRequestList")
    public String getOrderRequestList(@RequestParam(value = "cpCode", required = false) String cpCode, Model model) {
        List<MtlOdVO> orderRequests = orderRequestService.getGroupedOrderRequestsByCpCode(cpCode);
        model.addAttribute("orderRequests", orderRequests);

        List<CpVO> cpInfoList = orderRequestService.getAllCpInfo();
        model.addAttribute("cpInfoList", cpInfoList);

        return "mt/orderRequestList";
    }

    // 발주 요청 상세 정보 가져오기 (AJAX)
    @GetMapping("/orderRequestDetails")
    public List<MtVO> getOrderRequestDetailsByOrderRequestCode(@RequestParam String cpCode){
        return orderRequestService.getOrderRequestDetailsByOrderRequestCode(cpCode);
    }

    // 발주 요청 등록 페이지
    @GetMapping("/orderRequestNew")
    public String createOrderRequest(Model model) {
        List<CpVO> cpInfoList = orderRequestService.getAllCpInfo();
        model.addAttribute("cpInfoList", cpInfoList);
        model.addAttribute("orderRequest", new MtlOdVO());

        return "mt/orderRequestForm";
    }

    // 발주 요청 등록 처리
    @PostMapping("/orderRequestForm")
    public String insertOrderRequest(@ModelAttribute MtlOdVO orderRequest, @RequestParam("cpCode") String cpCode) {
        orderRequestService.insertOrderRequest(orderRequest);
        return "redirect:/orderRequestList?cpCode=" + cpCode;
    }

    // 발주 요청 삭제 처리
    @PostMapping("/orderRequest/delete")
    public String deleteOrderRequest(@RequestParam("orderRequestCode") String orderRequestCode,
                                     @RequestParam("cpCode") String cpCode) {
        orderRequestService.deleteOrderRequest(orderRequestCode);
        return "redirect:/orderRequestList?cpCode=" + cpCode;
    }

    // 발주 요청 상태 업데이트 처리
    @PostMapping("/orderRequest/updateStatus")
    public String updateOrderRequestStatus(@RequestParam("orderRequestCode") String orderRequestCode,
                                           @RequestParam("status") String status, @RequestParam("cpCode") String cpCode) {
        MtlOdVO orderRequest = orderRequestService.getOrderRequestById(orderRequestCode);
        orderRequest.setMtlOdStatus(status);
        orderRequestService.updateOrderRequest(orderRequest);
        return "redirect:/orderRequestList?cpCode=" + cpCode;
    }

    // 업체 코드에 따른 발주 가능한 품목 목록 조회 (AJAX)
    @GetMapping("/getItemsForCpCode")
    @ResponseBody
    public List<MtVO> getItemsByCpCode(@RequestParam("cpCode") String cpCode) {
        return orderRequestService.getItemsByCpCode(cpCode);
    }
}
