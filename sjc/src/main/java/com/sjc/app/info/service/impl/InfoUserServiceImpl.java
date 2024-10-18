package com.sjc.app.info.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjc.app.info.mapper.InfoUserMapper;
import com.sjc.app.info.service.InfoCopyDetailVO;
import com.sjc.app.info.service.InfoCopyLogVO;
import com.sjc.app.info.service.InfoUserService;
import com.sjc.app.info.service.InfoUserVO;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;

@Timed("info.user")
@Slf4j
@Service // AOP 적용가능한 Bean
public class InfoUserServiceImpl implements InfoUserService {
	//private MeterRegistry registry;
	private InfoUserMapper userMapper;
	
	@Autowired // 생성자 1개면 자동 autowired됨.
	InfoUserServiceImpl(InfoUserMapper userMapper, MeterRegistry registry){
//	UserServiceImpl(InfoUserMapper userMapper, MeterRegistry registry){
		this.userMapper = userMapper;
		//this.registry = registry;
	}
	
	@Override
	public List<InfoUserVO> userList(InfoUserVO userVO) {
		return userMapper.selectUserAllList(userVO);
	}
	
    @Override
    @Transactional
    public List<InfoUserVO> modifyUsers(List<InfoUserVO> InfoUserVOs) {
        List<InfoUserVO> savedUsers = new ArrayList<>();

        for (InfoUserVO userVO : InfoUserVOs) {
            try {
                int userUpdateCount = userMapper.updateUser(userVO);
                if (userUpdateCount == 0) {
                    throw new RuntimeException("User not found with id: " + userVO.getUserId());
                }

                int roleUpdateCount = userMapper.updateUserRole(userVO);
                if (roleUpdateCount == 0) {
                    userMapper.insertUserRole(userVO);
                }

                InfoUserVO savedInfoUserVO = userMapper.getUserById(userVO.getUserId());
                savedUsers.add(savedInfoUserVO);

            } catch (Exception e) {
                log.error("Error updating user with id: " + userVO.getUserId(), e);
            }
        }

        return savedUsers;
    }
    
    @Override
    @Transactional
    public InfoUserVO insertUser(InfoUserVO userVO) {
        userMapper.insertUser(userVO);
        userMapper.insertUserRole(userVO);
        return userMapper.getUserById(userVO.getUserId());
    }

    @Override
    @Transactional
    public List<String> deleteUsers(List<String> userIds) {
        userMapper.deleteUsers(userIds);
        userMapper.deleteUserRoles(userIds);
        return userIds;
    }
    
    
    @Override
    @Transactional
    public List<String> copyUsers(List<String> userIds) {
        List<InfoUserVO> usersToCopy = userMapper.getUsersByIds(userIds);

        userMapper.insertCopyLog();
        userMapper.insertCopyDetail(usersToCopy);

        return userIds;
    }
    
	@Override
	public List<InfoCopyLogVO> copyLogList() {
		return userMapper.selectCopyLogAllList();
	}
	
	@Override
	public List<InfoCopyDetailVO> copyDetailList() {
		return userMapper.selectCopyDetailAllList();
	}
    
}
