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

  
    @Override
    public List<MtInVO> getMtInList() {
        return mtInMapper.getMtInList();
    }

    @Override
    public void insertMtIn(MtInVO mtInVO) {
        mtInMapper.insertMtIn(mtInVO);
    }

    @Override
    public void deleteMtIn(String inCode) {
        mtInMapper.deleteMtIn(inCode);
    }
}
