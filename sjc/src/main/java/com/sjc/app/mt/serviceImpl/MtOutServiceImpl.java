package com.sjc.app.mt.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjc.app.mt.mapper.MtOutMapper;
import com.sjc.app.mt.service.MtOutService;

@Service
public class MtOutServiceImpl implements MtOutService {

    @Autowired
    private MtOutMapper mtOutMapper;

    @Override
    public List<Map<String, Object>> getOutgoingList() {
        return mtOutMapper.selectOutgoingList();
    }


}