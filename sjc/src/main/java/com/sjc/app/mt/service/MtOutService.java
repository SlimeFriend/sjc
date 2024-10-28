package com.sjc.app.mt.service;

import java.util.List;
import java.util.Map;

public interface MtOutService {
    // 출고 내역 조회 서비스 메서드
    List<Map<String, Object>> getOutgoingList();
    
    // 새로운 출고 등록 서비스 메서드
    void addOutgoing(Map<String, Object> outgoing);
}