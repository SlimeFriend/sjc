package com.sjc.app.security.mapper;

import java.util.List;

import com.sjc.app.security.service.UserVO;

public interface UserMapper {
	public UserVO getUserInfo(String username);
	public List<UserVO> selectUserAllList();
}
