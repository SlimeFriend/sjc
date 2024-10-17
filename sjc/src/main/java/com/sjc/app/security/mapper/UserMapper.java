package com.sjc.app.security.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sjc.app.security.service.UserVO;

public interface UserMapper {
	public UserVO getUserInfo(String username);
//	public List<UserVO> selectUserAllList();
	public List<UserVO> selectUserAllList(UserVO userVO);
	
    int updateUser(UserVO userVO);
    int updateUserRole(UserVO userVO);
    int insertUserRole(UserVO userVO);
    UserVO getUserById(String userId);
    void insertUser(UserVO userVO);
    void deleteUsers(List<String> userIds);
    void deleteUserRoles(List<String> userIds);
    Long insertCopyLog();
    void insertCopyDetail(List<UserVO> userVOs);
    List<UserVO> getUsersByIds(List<String> userIds);

    
}
