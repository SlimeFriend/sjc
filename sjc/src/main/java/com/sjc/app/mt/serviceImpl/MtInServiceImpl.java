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

    /**
     * 품질검사가 완료된 자재 목록을 조회하여 반환합니다.
     */
    @Override
    public List<MtInVO> getCompletedMtInList() {
        return mtInMapper.getCompletedMaterials();
    }

    /**
     * 자재 입고 후 현재 재고를 업데이트합니다.
     *
     * @param materialIn 입고된 자재 정보
     */
    @Override
    public void updateCurrentStockAfterReceiving(MtInVO materialIn) {
        mtInMapper.updateCurrentStockAfterReceiving(materialIn);
    }
}
