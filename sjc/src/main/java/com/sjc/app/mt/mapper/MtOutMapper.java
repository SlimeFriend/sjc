package com.sjc.app.mt.mapper;

import java.util.List;
import java.util.Map;

public interface MtOutMapper {
    // 출고 내역 조회 메서드
    List<Map<String, Object>> selectOutgoingList();
    

}