package com.sjc.app.security.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sjc.app.security.service.UserVO;

public interface UserMapper {
	public UserVO getUserInfo(String username);
//	public List<UserVO> selectUserAllList();
	public List<UserVO> selectUserAllList(UserVO userVO);
}
