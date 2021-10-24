package com.rent.rentshop.member.service;

import com.rent.rentshop.member.domain.User;
import java.util.List;

/**
 * 사용자를 저장, 조회, 수정, 삭제 기능 제공하는 서비스
 */
public interface UserService {

    /**
     * 사용자 목록을 조회하여 리턴합니다.
     * @return 사용자 목록
     */
    List<User> getUsers();

    /**
     * 사용자의 상세정보를 조회하여 리턴합니다.
     * @return 조회된 사용자
     */
    User getUser();

    /**
     * 사용자를 저장소에 저장후 리턴합니다.
     * @param form 저장할 사용자 정보
     * @return 저장된 사용자
     */
    User join(User form);

    /**
     * 사용자 아이디가 중복인지 검증합니다.
     * @param userId 검증할 아이디
     * @return true - 아이디중복 | false - 아이디 사용가능
     */
    boolean userIdCheck(String userId);

}
