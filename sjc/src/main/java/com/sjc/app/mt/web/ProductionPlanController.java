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
     * 모든 생산 계획 목록을 가져와서 productionPlanList 페이지에 전달
     */
    @GetMapping("/productionPlanList")
    public String getProductionPlans(Model model) {
        List<PlanVO> productionPlans = productionPlanService.getAllProductionPlans();
        model.addAttribute("productionPlans", productionPlans);
        return "mt/productionPlanList";   
    }

    /**
     * 선택된 생산 계획 코드(planCode)에 따른 자재 목록을 반환
     */
    @GetMapping("/getMaterialsByPlan")
    @ResponseBody
    public List<MtVO> getMaterialsByPlan(@RequestParam String planCode) {
        return materialService.getMaterialsByPlanCode(planCode);
    }
}
