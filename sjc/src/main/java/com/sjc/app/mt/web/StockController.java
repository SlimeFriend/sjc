package com.sjc.app.mt.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sjc.app.mt.service.StockService;

@Controller
public class StockController {

    @Autowired
    private StockService stockService;

    // 전체 재고 리스트를 조회
    @GetMapping("/stock")
    public String getStockList(Model model) {
        model.addAttribute("materials", stockService.getAllMaterials());
        return "mt/stockPage"; // 재고 페이지로 이동
    }

    // 안전재고에서 현재 재고로 수량을 이동하는 기능
    @PostMapping("/stock/moveSafetyStock")
    public String moveSafetyStockToCurrent(@RequestParam("mtCode") String mtCode,
                                           @RequestParam("quantity") Integer quantity, 
                                           Model model) {
        boolean isSuccess = stockService.moveSafetyStockToCurrent(mtCode, quantity);
        model.addAttribute("materials", stockService.getAllMaterials()); 
        if (isSuccess) {
            model.addAttribute("message", "안전재고에서 현재 재고로 성공적으로 이동했습니다.");
        } else {
            model.addAttribute("error", "이동 실패: 안전재고가 부족하거나 오류가 발생했습니다.");
        }
        return "mt/stockPage"; 
    }

    // 자재 구분 업데이트 기능 추가
    @PostMapping("/stock/updateMaterialType")
    public String updateMaterialType(@RequestParam("mtCode") String mtCode,
                                     @RequestParam("materialType") String materialType, 
                                     Model model) {
        stockService.updateMaterialType(mtCode, materialType);
        model.addAttribute("materials", stockService.getAllMaterials()); 
        model.addAttribute("message", "자재 구분이 성공적으로 업데이트되었습니다.");
        return "mt/stockPage"; 
    }
}
