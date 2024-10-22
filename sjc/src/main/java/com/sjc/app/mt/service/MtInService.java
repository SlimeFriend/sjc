package com.sjc.app.mt.service;

import java.util.List;

public interface MtInService {

    // 품질검사 완료된 입고 목록 조회
    List<MtInVO> getCompletedMtInList(); // 품질검사 완료된 자재만 조회

    // 자재 입고 시 재고 업데이트
    void updateCurrentStockAfterReceiving(MtInVO materialIn); // 자재 입고 후 재고 업데이트
}
