package com.sjc.app.mt.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sjc.app.mt.mapper.MtInMapper;
import com.sjc.app.mt.service.MtInService;
import com.sjc.app.mt.service.MtInVO;

@Service
public class MtInServiceImpl implements MtInService {

    @Autowired
    private MtInMapper mtInMapper;

    // 품질검사 완료된 자재 목록 조회
    @Override
    public List<MtInVO> getCompletedMtInList() {
        return mtInMapper.getCompletedMaterials(); 
    }

    // 자재 입고 후 재고 업데이트
    @Override
    public void updateCurrentStockAfterReceiving(MtInVO materialIn) {
       
        mtInMapper.updateCurrentStockAfterReceiving(materialIn);
    }
}
