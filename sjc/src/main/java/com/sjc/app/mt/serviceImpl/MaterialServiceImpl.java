package com.sjc.app.mt.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjc.app.mt.mapper.MaterialMapper;
import com.sjc.app.mt.service.MaterialService;
import com.sjc.app.mt.service.MtVO;

import java.util.List;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialMapper materialMapper;

    @Override
    public List<MtVO> getAllMaterials() {
        return materialMapper.selectAllMaterials();
    }

    @Override
    public MtVO getMaterialById(String mtCode) {
        return materialMapper.selectMaterialById(mtCode);
    }

    @Override
    public List<MtVO> getMaterialsByCodes(List<String> materialCodes) {
        return materialMapper.selectMaterialsByCodes(materialCodes);
    }

    @Override
    public List<MtVO> getMaterialsByPlanCode(String planCode) {
        return materialMapper.selectMaterialsByPlanCode(planCode);
    }
}
