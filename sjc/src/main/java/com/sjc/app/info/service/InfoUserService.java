package com.sjc.app.info.service;

import java.util.List;


public interface InfoUserService {
	public List<InfoUserVO> userList(InfoUserVO userVO);
	public List<InfoUserVO> modifyUsers(List<InfoUserVO> userVOs);
    InfoUserVO insertUser(InfoUserVO userVO);
    List<String> deleteUsers(List<String> userIds);
    public List<String> copyUsers(List<String> userIds);
    
    public List<InfoCopyLogVO> copyLogList();
    public List<InfoCopyDetailVO> copyDetailList();
}
