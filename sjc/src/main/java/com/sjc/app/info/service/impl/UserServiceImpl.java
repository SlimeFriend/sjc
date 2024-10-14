package com.sjc.app.info.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjc.app.info.service.UserService;
import com.sjc.app.security.mapper.UserMapper;
import com.sjc.app.security.service.UserVO;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;

@Timed("my.user")
@Slf4j
@Service // AOP 적용가능한 Bean
public class UserServiceImpl implements UserService {
	//private MeterRegistry registry;
	private UserMapper userMapper;
	
	@Autowired // 생성자 1개면 자동 autowired됨.
	UserServiceImpl(UserMapper userMapper, MeterRegistry registry){
//	UserServiceImpl(UserMapper userMapper, MeterRegistry registry){
		this.userMapper = userMapper;
		//this.registry = registry;
	}
	
	@Override
	public List<UserVO> userList() {
		/*
		 * 
		Timer timer = Timer.builder("my.list")
				.tag("class", this.getClass().getName())
				.tag("method", "userList")
				.description("list")
				.register(registry);
		
		timer.record(() -> {
			log.info("userList");
			sleep(500);
		});
		 */
		
		return userMapper.selectUserAllList();
	}
	
//	private static void sleep(int i) {
//		
//		try {
//			Thread.sleep(i + new Random().nextInt(500));
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
//	
//
//	@Override
//	public UserVO userInfo(UserVO userVO) {
//		return userMapper.selectUserInfo(userVO);
//	}
//
//	@Override
//	public int userInsert(UserVO userVO) {
//		int result = userMapper.insertUserInfo(userVO);
//		
//		return result == 1 ? userVO.getUserloyeeId() : -1;
//	}
//
//	@Override
//	public Map<String, Object> userUpdate(UserVO userVO) {
//		Map<String, Object> map = new HashMap<>();
//		boolean isSuccessed = false;
//		
//		int result = userMapper.updateUserInfo(userVO.getUserloyeeId(), userVO);
//		
//		if(result == 1) {
//			isSuccessed = true;
//		}
//		
//		map.put("result", isSuccessed);
//		map.put("target", userVO);
//		/**
//		 * AJAX
//		{
//			"result" : true,
//			"target" : {
//							userloyeeId : 100,
//							lastName : "King",
//							...
//						}
//		}
//		 */
//		
//		return map;
//	}
//
//	@Override
//	public Map<String, Object> userDelete(int userId) {
//		Map<String, Object> map = new HashMap<>();
//		// 삭제가 안될 경우 : {}
//		// 삭제가 될 경우 : { "userloyeeId" : 104}
//		int result = userMapper.deleteUserInfo(userId);
//		
//		if(result == 1) {
//			map.put("userloyeeId", userId); 
//		}
//		
//		return map;
//	}

}
