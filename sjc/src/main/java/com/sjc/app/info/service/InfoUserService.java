package com.sjc.app.info.service;

import java.util.List;
import java.util.Map;


public interface InfoUserService {
	public List<InfoUserVO> userList(InfoUserVO userVO);
	public List<InfoUserVO> modifyUsers(List<InfoUserVO> userVOs);
    InfoUserVO insertUser(InfoUserVO userVO);
    List<String> deleteUsers(List<String> userIds);
    List<String> deleteUsersProcedure(List<String> userIds);
    public List<String> copyUsers(List<String> userIds);
    
    public List<InfoCopyLogVO> copyLogList();
    public List<InfoCopyDetailVO> copyDetailList();
    
    
    
    public Map<String, Object> getUserListResponse(InfoUserVO infoUserVO);


    public List<InfoUserVO> getUserList(InfoUserVO infoUserVO);


    public int getTotalUserCount(InfoUserVO infoUserVO);


    public Map<String, Object> convertVoToMap(InfoUserVO infoUserVO);


}
