package com.sjc.app.info.service.impl;

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
	
	// 사용자 조회
	@Override
	public List<InfoUserVO> userList(InfoUserVO userVO) {
		return userMapper.selectUserAllList(userVO);
	}
	// 사용자 변경 이력 조회
	@Override
	public List<InfoUserVO> userHistoryList(InfoUserVO userVO) {
		return userMapper.selectUserHistory(userVO);
	}
	
	// 사용자 수정
    @Override
    @Transactional
    public List<InfoUserVO> modifyUsers(List<InfoUserVO> InfoUserVOs) {
    	
        for (InfoUserVO userVO : InfoUserVOs) {
            int userUpdateCount = userMapper.updateUser(userVO);
            // 사용자 없으면 등록
            if (userUpdateCount == 0) {
            	String password = userVO.getPassword();
            	// 비밀번호 암호화
        		userVO.setPassword(passwordEncoder.encode(password));	                	
                userMapper.insertUser(userVO);
            }

            int roleUpdateCount = userMapper.updateUserRole(userVO);
            // 권한 없으면 등록
            if (roleUpdateCount == 0) {
                userMapper.insertUserRole(userVO);
            }
        }
        return InfoUserVOs;
    }
    
    // 사용자 등록
    @Override
    @Transactional
    public InfoUserVO insertUser(InfoUserVO userVO) {
        userMapper.insertUser(userVO);
        userMapper.insertUserRole(userVO);
        return userMapper.getUserById(userVO.getUserId());
    }

    // 사용자 삭제
    // xml에서 반복문 처리
    @Override
    @Transactional
    public List<String> deleteUsers(List<String> userIds) {
        userMapper.deleteUsers(userIds);
        userMapper.deleteUserRoles(userIds);
        return userIds;
    }
    
    // 오라클 프로시저 : 사용자, 권한 삭제
    @Override
    @Transactional
    public List<String> deleteUsersProcedure(List<String> userIds) {
    	// 파라미터를 List -> String으로 변환
    	String stringUserIds = String.join(",", userIds);
    	userMapper.deleteUsersProcedure(stringUserIds);        
    	return userIds;
    }
    
    // 사용자 복사 - 테스트용
    @Override
    @Transactional
    public List<String> copyUsers(List<String> userIds) {
        List<InfoUserVO> infoUserVO = userMapper.getUsersByIds(userIds);

        userMapper.insertCopyLog();
        userMapper.insertCopyDetail(infoUserVO);

        return userIds;
    }
    
    // 사용자 복사 조회 - 테스트용
	@Override
	public List<InfoCopyLogVO> copyLogList() {
		return userMapper.selectCopyLogAllList();
	}
	
	// 사용자 복사 상세 조회 - 테스트용
	@Override
	public List<InfoCopyDetailVO> copyDetailList() {
		return userMapper.selectCopyDetailAllList();
	}
	
	// 그리드 API용 사용자 정보, 페이징, 결과 조회
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

	// 그리드 API용 사용자 정보 조회
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

	// 그리드 API용 사용자 수 조회
    public int getTotalUserCount(InfoUserVO infoUserVO) {
        return userMapper.countUserList(convertVoToMap(infoUserVO));
    }

	// 그리드 VO -> Map
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
