package com.sjc.app.mt.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sjc.app.mt.service.MaterialService;
import com.sjc.app.mt.service.MtVO;
import com.sjc.app.mt.service.ProductionPlanService;
import com.sjc.app.pr.service.PlanVO;

@Controller
public class ProductionPlanController {

    @Autowired
    private ProductionPlanService productionPlanService;

    @Autowired
    private MaterialService materialService;

    /**
     * 모든 생산 계획 목록을 가져와서 productionPlanList 페이지에 전달 (페이징 적용)
     */
    @GetMapping("/productionPlanList")
    public String getProductionPlans(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            Model model) {

        int offset = (page - 1) * size;

        // 페이징 적용된 생산 계획 목록 조회
        List<PlanVO> productionPlans = productionPlanService.getAllProductionPlansWithPaging(size, offset);
        model.addAttribute("productionPlans", productionPlans);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        
        return "mt/productionPlanList";   
    }

    /**
     * 선택된 생산 계획 코드(planCode)에 따른 자재 목록을 반환
     */
    @GetMapping("/getMaterialsByPlan")
    @ResponseBody
    public List<MtVO> getMaterialsByPlan(@RequestParam("planCode") String planCode) {
        System.out.println("Requested planCode: " + planCode);
        List<MtVO> materials = materialService.getMaterialsByPlanCode(planCode);
        System.out.println("Fetched materials from service: " + materials);
        return materials;
    }

}
