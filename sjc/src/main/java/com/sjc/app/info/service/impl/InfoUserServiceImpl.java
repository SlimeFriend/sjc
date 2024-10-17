package com.sjc.app.info.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjc.app.info.service.InfoUserService;
import com.sjc.app.security.mapper.UserMapper;
import com.sjc.app.security.service.UserVO;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;

@Timed("info.user")
@Slf4j
@Service // AOP 적용가능한 Bean
public class InfoUserServiceImpl implements InfoUserService {
	//private MeterRegistry registry;
	private UserMapper userMapper;
	
	@Autowired // 생성자 1개면 자동 autowired됨.
	InfoUserServiceImpl(UserMapper userMapper, MeterRegistry registry){
//	UserServiceImpl(UserMapper userMapper, MeterRegistry registry){
		this.userMapper = userMapper;
		//this.registry = registry;
	}
	
	@Override
	public List<UserVO> userList(UserVO userVO) {
		return userMapper.selectUserAllList(userVO);
	}
	
    @Override
    @Transactional
    public List<UserVO> modifyUsers(List<UserVO> UserVOs) {
        List<UserVO> savedUsers = new ArrayList<>();

        for (UserVO userVO : UserVOs) {
            try {
                // 1. Update user information
                int userUpdateCount = userMapper.updateUser(userVO);
                if (userUpdateCount == 0) {
                    throw new RuntimeException("User not found with id: " + userVO.getUserId());
                }

                // 2. Update user role
                int roleUpdateCount = userMapper.updateUserRole(userVO);
                if (roleUpdateCount == 0) {
                    userMapper.insertUserRole(userVO);
                }

                // 3. Fetch updated user information
                UserVO savedUserVO = userMapper.getUserById(userVO.getUserId());
                savedUsers.add(savedUserVO);

            } catch (Exception e) {
                // 에러 로깅 및 처리
                log.error("Error updating user with id: " + userVO.getUserId(), e);
                // 여기서 예외를 다시 던지면 트랜잭션이 롤백됩니다.
                // throw new RuntimeException("Failed to update user", e);
            }
        }

        return savedUsers;
    }
    
    @Override
    @Transactional
    public UserVO insertUser(UserVO userVO) {
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
        List<UserVO> usersToCopy = userMapper.getUsersByIds(userIds);

        userMapper.insertCopyLog();
        userMapper.insertCopyDetail(usersToCopy);

        return userIds;
    }
}
