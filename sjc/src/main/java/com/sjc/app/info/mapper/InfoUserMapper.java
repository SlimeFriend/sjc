package com.sjc.app.info.mapper;

import java.util.List;

import com.sjc.app.info.service.InfoCopyDetailVO;
import com.sjc.app.info.service.InfoCopyLogVO;
import com.sjc.app.info.service.InfoUserVO;


public interface InfoUserMapper {
	public InfoUserVO getUserInfo(String username);
//	public List<InfoUserVO> selectUserAllList();
	public List<InfoUserVO> selectUserAllList(InfoUserVO userVO);
	
    int updateUser(InfoUserVO userVO);
    int updateUserRole(InfoUserVO userVO);
    int insertUserRole(InfoUserVO userVO);
    InfoUserVO getUserById(String userId);
    void insertUser(InfoUserVO userVO);
    void deleteUsers(List<String> userIds);
    void deleteUserRoles(List<String> userIds);
    Long insertCopyLog();
    void insertCopyDetail(List<InfoUserVO> userVOs);
    List<InfoUserVO> getUsersByIds(List<String> userIds);

    public List<InfoCopyLogVO> selectCopyLogAllList();
    public List<InfoCopyDetailVO> selectCopyDetailAllList();
    
    
}
