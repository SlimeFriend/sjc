package com.sjc.app.mt.web;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sjc.app.mt.service.MtInVO;
import com.sjc.app.mt.service.MtVO;
import com.sjc.app.mt.service.StockService;

@Controller
public class StockController {

    private static final Logger logger = LoggerFactory.getLogger(StockController.class);

    @Autowired
    private StockService stockService;

    /**
     * 전체 재고 리스트를 조회하여 stockPage 페이지로 이동
     */
    @GetMapping("/stock")
    public String getStockList(Model model) {
        List<MtVO> materials = stockService.getAllMaterials();
        model.addAttribute("materials", materials);
        model.addAttribute("lotDetails", null);
        model.addAttribute("totalQuantity", 0);
        return "mt/stockPage";
    }

    /**
     * 자재 구분(materialType)을 업데이트
     */
    @PostMapping("/stock/updateMaterialType")
    public String updateMaterialType(@RequestParam("mtCode") String mtCode,
                                     @RequestParam("materialType") String materialType, 
                                     RedirectAttributes redirectAttributes) {
        try {
            stockService.updateMaterialType(mtCode, materialType);
            redirectAttributes.addFlashAttribute("message", "자재 구분이 성공적으로 업데이트되었습니다.");
        } catch (Exception e) {
            logger.error("자재 구분 업데이트 중 오류 발생: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("error", "자재 구분 업데이트에 실패했습니다.");
        }
        return "redirect:/stock";
    }

    /**
     * 품질검사 완료된 자재의 수량을 현재 재고에 추가
     */
//    @PostMapping("/stock/addCompletedInspectionToCurrentStock")
//    public String addCompletedInspectionToCurrentStock(RedirectAttributes redirectAttributes) {
//        try {
//            List<MtVO> completedMaterials = stockService.getCompletedInspectionMaterials();
//            for (MtVO material : completedMaterials) {
//                stockService.updateCurrentStock(material.getMtCode(), material.getQuantity());
//            }
//            redirectAttributes.addFlashAttribute("message", "품질검사 완료된 자재의 수량이 성공적으로 현재 재고에 추가되었습니다.");
//        } catch (Exception e) {
//            logger.error("품질검사 완료 자재 추가 중 오류 발생: {}", e.getMessage());
//            redirectAttributes.addFlashAttribute("error", "품질검사 완료 자재 추가에 실패했습니다.");
//        }
//        return "redirect:/stock";
//    }

    /**
     * 로트번호별 자재 수량 조회 및 stockPage의 stockDetail 영역으로 이동
     */
    @GetMapping("/stock/{mtCode}/lots")
    public String getMaterialsByLotNo(@PathVariable String mtCode, Model model) {
        List<MtInVO> lotDetails = stockService.getMaterialsByLotNo(mtCode);
        Integer totalQuantity = stockService.getTotalQuantityByLotNo(mtCode);

        model.addAttribute("lotDetails", lotDetails.isEmpty() ? null : lotDetails);
        model.addAttribute("totalQuantity", totalQuantity != null ? totalQuantity : 0);

        // 전체 자재 목록 다시 조회하여 모델에 추가
        List<MtVO> materials = stockService.getAllMaterials();
        model.addAttribute("materials", materials);

        return "mt/stockPage :: stockDeatil";
    }

    /**
     * 자재 코드에 해당하는 로트번호 목록을 반환하는 API (AJAX 요청 처리용)
     */
    @GetMapping("/api/stock/{mtCode}/lotNumbers")
    @ResponseBody
    public List<String> getLotNumbersByMtCode(@PathVariable("mtCode") String mtCode) {
        return stockService.getLotNumbersByMtCode(mtCode);
    }

    /**
     * 재고 조정 기능 (AJAX 처리) - 특정 자재 및 로트번호에 대해 수량 조정
     */
    @PostMapping("/stock/adjust")
    @ResponseBody
    public ResponseEntity<String> adjustStock(@RequestParam("mtCode") String mtCode,
                                              @RequestParam("lotNo") String lotNo,
                                              @RequestParam(value = "newStock", required = false) Integer newStock,
                                              @RequestParam(value = "stockDecrease", required = false) Integer stockDecrease) {
        if (newStock == null) newStock = 0;
        if (stockDecrease == null) stockDecrease = 0;

        try {
            int adjustedStock = newStock - stockDecrease;
            if (adjustedStock != 0) {
                stockService.addQuantityToLotAndUpdateStock(mtCode, lotNo, adjustedStock);
                return ResponseEntity.ok("재고 조정이 완료되었습니다.");
            } else {
                return ResponseEntity.badRequest().body("변경할 수량을 입력해야 합니다.");
            }
        } catch (Exception e) {
            logger.error("재고 조정 중 오류 발생: {}", e.getMessage());
            return ResponseEntity.status(500).body("재고 조정에 실패했습니다.");
        }
    }
    

}
