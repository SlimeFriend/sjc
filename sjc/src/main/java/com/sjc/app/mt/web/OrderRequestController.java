package com.sjc.app.mt.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.sjc.app.mt.service.MtlOdVO;
import com.sjc.app.mt.service.MtVO;
import com.sjc.app.mt.service.OrderRequestService;
import com.sjc.app.sales.service.CpVO;  // CpVO import

@Controller
public class OrderRequestController {

    @Autowired
    private OrderRequestService orderRequestService;

    /**
     * 발주 요청 목록 페이지로 이동 (업체 코드별 발주 요청 목록 조회)
     */
    @GetMapping("/orderRequestList")
    public String orderRequestList(@RequestParam(value = "cpCode", required = false) String cpCode, Model model) {
        // 업체 코드에 따른 발주 요청 목록을 조회
        List<MtlOdVO> orderRequests = orderRequestService.getAllOrderRequestsByCpCode(cpCode);
        model.addAttribute("orderRequests", orderRequests);

        // 발주 요청 폼을 위한 빈 객체
        model.addAttribute("orderRequest", new MtlOdVO());

        // 업체 코드에 따른 발주 요청 상세 정보
        List<MtVO> orderRequestDetails = orderRequestService.getOrderRequestDetailsByCpCode(cpCode);
        model.addAttribute("orderRequestDetails", orderRequestDetails);

        // 업체 정보 조회 (CpVO 객체)
        CpVO cpInfo = orderRequestService.getCpInfoByCpCode(cpCode);
        model.addAttribute("cpInfo", cpInfo);  // CpVO 객체를 모델에 추가

        return "mt/orderRequestList";  // 'mt' 폴더 아래에 있는 orderRequestList.html로 이동
    }

    /**
     * 발주 요청 상세 정보 가져오기 (AJAX 요청 처리)
     */
    @GetMapping("/orderRequestDetails")
    @ResponseBody
    public List<MtVO> getOrderRequestDetails(@RequestParam("orderRequestCode") String orderRequestCode) {
        // 특정 발주 요청 코드에 따른 발주 상세 정보를 가져오는 처리
        return orderRequestService.getOrderRequestDetailsByOrderRequestCode(orderRequestCode);
    }

    /**
     * 발주 요청 등록 페이지로 이동
     */
    @GetMapping("/orderRequestNew")
    public String orderRequestNew(Model model) {
        // 업체 목록 조회
        List<CpVO> cpInfoList = orderRequestService.getAllCpInfo();  // 업체 목록을 조회하는 서비스 메서드
        model.addAttribute("cpInfoList", cpInfoList);  // 모델에 업체 목록 추가

        // 새로운 발주 요청을 위한 빈 객체 생성
        model.addAttribute("orderRequest", new MtlOdVO());

        return "mt/orderRequestForm";  // 'mt/orderRequestForm.html'로 이동
    }

    /**
     * 발주 요청 등록 및 저장 처리
     */
    @PostMapping("/orderRequestForm")
    public String saveOrderRequest(@RequestParam("cpCode") String cpCode, @RequestParam Map<String, String> allParams) {
        // allParams에서 발주 수량이 포함된 품목 데이터를 가져와 처리
        orderRequestService.saveOrderRequest(cpCode, allParams);

        // 발주 저장 후 발주 요청 목록 페이지로 이동
        return "redirect:/orderRequestList?cpCode=" + cpCode;
    }

    /**
     * 발주 요청 삭제 처리
     */
    @PostMapping("/orderRequest/delete")
    public String deleteOrderRequest(@RequestParam("orderRequestCode") String orderRequestCode, @RequestParam("cpCode") String cpCode) {
        orderRequestService.deleteOrderRequest(orderRequestCode);
        return "redirect:/orderRequestList?cpCode=" + cpCode;
    }

    /**
     * 발주 상태 업데이트 처리
     */
    @PostMapping("/orderRequest/updateStatus")
    public String updateOrderRequestStatus(@RequestParam("orderRequestCode") String orderRequestCode,
                                           @RequestParam("status") String status,
                                           @RequestParam("cpCode") String cpCode) {
        orderRequestService.updateOrderRequestStatusForCpCode(cpCode, status);
        return "redirect:/orderRequestList?cpCode=" + cpCode;
    }
}
