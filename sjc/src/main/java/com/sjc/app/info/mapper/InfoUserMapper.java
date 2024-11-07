package com.sjc.app.info.mapper;

import java.util.List;
import java.util.Map;

import com.sjc.app.info.service.InfoCopyDetailVO;
import com.sjc.app.info.service.InfoCopyLogVO;
import com.sjc.app.info.service.InfoUserVO;


public interface InfoUserMapper {
    
    // 사용자 정보 조회
    public InfoUserVO getUserInfo(String username);
    
    // 사용자 목록 조회
    public List<InfoUserVO> selectUserAllList(InfoUserVO userVO);
    
    // 사용자 변경 이력 조회
    public List<InfoUserVO> selectUserHistory(InfoUserVO userVO);

    // 사용자 정보 수정
    int updateUser(InfoUserVO userVO);
    
    // 사용자 권한 수정
    int updateUserRole(InfoUserVO userVO);
    
    // 사용자 권한 등록
    int insertUserRole(InfoUserVO userVO);
    
    // 사용자 정보 조회
    InfoUserVO getUserById(String userId);
    
    // 사용자 정보 등록
    void insertUser(InfoUserVO userVO);
    
    // 선택한 사용자 삭제
    void deleteUsers(List<String> userIds);
    
    // 선택한 사용자 권한 삭제
    void deleteUserRoles(List<String> userIds);
    
    // 복사 등록
    Long insertCopyLog();
    
    // 복사 상세 등록
    void insertCopyDetail(List<InfoUserVO> userVOs);
    
    // 사용자 정보 조회
    List<InfoUserVO> getUsersByIds(List<String> userIds);
    
    // 복사 조회
    public List<InfoCopyLogVO> selectCopyLogAllList();
    
    // 복사 상세 조회
    public List<InfoCopyDetailVO> selectCopyDetailAllList();
    
    // 사용자 목록 조회
    List<InfoUserVO> selectUserList(Map<String, Object> params);
    
    // 사용자 수 조회
    int countUserList(Map<String, Object> params);
    
    // 사용자정보, 권한 삭제 프로시저
    List<InfoUserVO> deleteUsersProcedure(String userIds);    
}
