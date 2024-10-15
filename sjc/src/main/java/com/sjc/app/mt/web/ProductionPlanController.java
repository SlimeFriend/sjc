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

    @GetMapping("/productionPlanList")
    public String getProductionPlans(Model model) {
        List<PlanVO> productionPlans = productionPlanService.getAllProductionPlans();
        model.addAttribute("productionPlans", productionPlans);
        return "mt/productionPlanList";   // Thymeleaf 템플릿 이름
    }

    // 선택된 생산 계획에 따른 자재 목록을 반환하는 메서드
    @GetMapping("/getMaterialsByPlan")
    @ResponseBody
    public List<MtVO> getMaterialsByPlan(@RequestParam String planCode) {
        // 선택된 생산 계획 코드에 해당하는 자재 목록을 가져옴
        return materialService.getMaterialsByPlanCode(planCode);
    }
}
