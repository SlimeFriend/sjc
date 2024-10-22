package com.sjc.app.mt.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sjc.app.mt.service.MtInVO;
import com.sjc.app.mt.service.MtVO;
import com.sjc.app.mt.service.StockService;

@Controller
public class StockController {

    @Autowired
    private StockService stockService;

    // 전체 재고 리스트를 조회
    @GetMapping("/stock")
    public String getStockList(Model model) {
        // 전체 자재 목록을 가져와 모델에 추가
        List<MtVO> materials = stockService.getAllMaterials();
        model.addAttribute("materials", materials);
        return "mt/stockPage"; // 재고 페이지로 이동
    }

    // 자재 구분 업데이트 기능 추가
    @PostMapping("/stock/updateMaterialType")
    public String updateMaterialType(@RequestParam("mtCode") String mtCode,
                                     @RequestParam("materialType") String materialType, 
                                     Model model) {
        // 자재 구분 업데이트
        stockService.updateMaterialType(mtCode, materialType);
        
        // 업데이트 후 전체 자재 목록을 다시 가져와서 뷰에 반영
        List<MtVO> materials = stockService.getAllMaterials();
        model.addAttribute("materials", materials); 
        model.addAttribute("message", "자재 구분이 성공적으로 업데이트되었습니다.");
        return "mt/stockPage"; 
    }

    // 품질검사 완료된 자재의 수량을 현재 재고에 추가하는 기능
    @PostMapping("/stock/addCompletedInspectionToCurrentStock")
    public String addCompletedInspectionToCurrentStock(Model model) {
        // 품질검사 완료된 자재 목록을 가져옴
        List<MtVO> completedMaterials = stockService.getCompletedInspectionMaterials();

        // 완료된 자재의 수량을 현재 재고에 추가
        for (MtVO material : completedMaterials) {
            stockService.updateCurrentStock(material.getMtCode(), material.getQuantity());
        }

        // 전체 자재 목록을 다시 조회하여 뷰에 반영
        List<MtVO> materials = stockService.getAllMaterials();
        model.addAttribute("materials", materials);
        model.addAttribute("message", "품질검사 완료된 자재의 수량이 성공적으로 현재 재고에 추가되었습니다.");
        
        return "mt/stockPage";
    }

 // 자재 코드로 로트번호별 자재 수량을 조회하는 기능
    @GetMapping("/stock/{mtCode}/lots")
    public String getMaterialsByLotNo(@PathVariable String mtCode, Model model) {
        List<MtInVO> lotDetails = stockService.getMaterialsByLotNo(mtCode);
        Integer totalQuantity = stockService.getTotalQuantityByLotNo(mtCode);

        model.addAttribute("lotDetails", lotDetails);
        model.addAttribute("totalQuantity", totalQuantity);
        return "mt/lotDetailsPage"; 
    }
}
