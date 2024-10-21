package com.sjc.app.info.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
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
//            		String password = "1234";
                	String password = userVO.getPassword();
            		userVO.setPassword(passwordEncoder.encode(password));	                	
                    userMapper.insertUser(userVO);
                }

                int roleUpdateCount = userMapper.updateUserRole(userVO);
                if (roleUpdateCount == 0) {
                    userMapper.insertUserRole(userVO);
                }

                InfoUserVO savedInfoUserVO = userMapper.getUserById(userVO.getUserId());
                savedUsers.add(savedInfoUserVO);

            } catch (Exception e) {
                log.error("error : " , e);
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
    public List<String> deleteUsersProcedure(List<String> userIds) {
    	String stringUserIds = String.join(",", userIds);
    	userMapper.deleteUsersProcedure(stringUserIds);        
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

    @Override
    public Map<String, Object> getUserListResponse(InfoUserVO infoUserVO) {
        List<InfoUserVO> users = getUserList(infoUserVO);
        int totalCount = getTotalUserCount(infoUserVO);

        Map<String, Object> pagination = new HashMap<>();
        pagination.put("page", infoUserVO.getPage());
        pagination.put("totalCount", totalCount);

        Map<String, Object> data = new HashMap<>();
        data.put("contents", users);
        data.put("pagination", pagination);

        Map<String, Object> response = new HashMap<>();
        response.put("result", true);
        response.put("data", data);

        return response;
    }

    public List<InfoUserVO> getUserList(InfoUserVO infoUserVO) {
    	int page = infoUserVO.getPage();
    	int perPage = infoUserVO.getPerPage();
        Map<String, Object> params = new HashMap<>();
        params.put("startRow", (page - 1) * perPage);
        params.put("endRow", page * perPage);
        params.putAll(convertVoToMap(infoUserVO));

        return userMapper.selectUserList(params);
    }
//    public List<InfoUserVO> getUserList(InfoUserVO searchParams, int page, int perPage) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("startRow", (page - 1) * perPage);
//        params.put("endRow", page * perPage);
//        params.putAll(convertVoToMap(searchParams));
//
//        return userMapper.selectUserList(params);
//    }

    public int getTotalUserCount(InfoUserVO infoUserVO) {
        return userMapper.countUserList(convertVoToMap(infoUserVO));
    }

    public Map<String, Object> convertVoToMap(InfoUserVO infoUserVO) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", infoUserVO.getUserId());
        map.put("loginId", infoUserVO.getLoginId());
        map.put("userName", infoUserVO.getUserName());
        map.put("roleName", infoUserVO.getRoleName());
        map.put("deptCode", infoUserVO.getDeptCode());
        map.put("deptName", infoUserVO.getDeptName());
        return map;
    }
	
}
