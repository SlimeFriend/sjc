package com.sjc.app.mt.serviceImpl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.sjc.app.mt.mapper.ProductionPlanMapper;
import com.sjc.app.mt.service.MtVO;
import com.sjc.app.mt.service.ProductionPlanService;
import com.sjc.app.pr.service.PlanVO;

@Service
public class ProductionPlanServiceImpl implements ProductionPlanService {

    private final ProductionPlanMapper productionPlanMapper;

    /**
     * ProductionPlanMapper를 주입받는 생성자
     */
    public ProductionPlanServiceImpl(ProductionPlanMapper productionPlanMapper) {
        this.productionPlanMapper = productionPlanMapper;
    }

    /**
     * 모든 생산 계획 조회
     */
    @Override
    public List<PlanVO> getAllProductionPlans() {
        return productionPlanMapper.selectAllProductionPlans();
    }

    /**
     * 생산 계획 코드에 따른 자재 목록 조회
     */
    @Override
    public List<MtVO> getMaterialsByPlanCode(String planCode) {
        return productionPlanMapper.selectMaterialsByPlanCode(planCode);
    }
}
