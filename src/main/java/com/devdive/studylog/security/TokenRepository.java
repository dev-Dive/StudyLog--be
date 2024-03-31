package com.devdive.studylog.security;

import java.util.Optional;

interface TokenRepository {
    /**
     * 이미 등록된 email을 받았을 경우
     * 1) 기존의 token을 삭제하고 재발급
     * 2) 기존의 token을 찾아서 반환 + 유효기간 연장
     * 3) 새로운 token 계속 발급
     * 결정 해야함
     * @param email
     * @return
     */
    String createAndSave(String email);
    Optional<String> findEmailByToken(String token);
    boolean containsToken(String token);
    boolean deleteByToken(String token);

}
