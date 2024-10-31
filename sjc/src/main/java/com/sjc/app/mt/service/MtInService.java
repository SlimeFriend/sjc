package com.sjc.app.mt.service;

import java.util.List;

public interface MtInService {

    /**
     * 품질검사 완료된 자재 목록 조회
     * @return 품질검사 완료된 자재 목록
     */
    List<MtInVO> getCompletedMtInList(); 

    /**
     * 자재 입고 후 현재 재고를 업데이트
     * @param materialIn 입고된 자재 정보
     */
    void updateCurrentStockAfterReceiving(MtInVO materialIn); 
}
