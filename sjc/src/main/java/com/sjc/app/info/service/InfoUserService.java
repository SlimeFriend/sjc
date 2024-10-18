package com.sjc.app.info.service;

import java.util.List;

import com.sjc.app.security.service.UserVO;

public interface InfoUserService {
	public List<UserVO> userList(UserVO userVO);
	public List<UserVO> modifyUsers(List<UserVO> userVOs);
    UserVO insertUser(UserVO userVO);
    List<String> deleteUsers(List<String> userIds);
    public List<String> copyUsers(List<String> userIds);

}
