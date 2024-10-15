package com.sjc.app.mt.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.sjc.app.mt.mapper.ProductionPlanMapper;
import com.sjc.app.mt.service.ProductionPlanService;
import com.sjc.app.pr.service.PlanVO;

@Service
public class ProductionPlanServiceImpl implements ProductionPlanService {

    private final ProductionPlanMapper productionPlanMapper;

    public ProductionPlanServiceImpl(ProductionPlanMapper productionPlanMapper) {
        this.productionPlanMapper = productionPlanMapper;
    }

    @Override
    public List<PlanVO> getAllProductionPlans() {
       
        return productionPlanMapper.selectAllProductionPlans();
    }
}
