package com.sjc.app.mt.mapper;

import java.util.List;
import com.sjc.app.mt.service.MtInVO;

public interface MtInMapper {

    // 품질검사 완료된 자재 목록 조회
    List<MtInVO> getCompletedMaterials(); // 품질검사 완료된 자재만 조회

    // 자재 입고 시 재고 업데이트 (CURRENT_STC 값 갱신)
    void updateCurrentStockAfterReceiving(MtInVO materialIn); // 자재 입고 후 재고 업데이트
}
