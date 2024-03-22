package com.devdive.studylog.service;

// Todo: 구현체 필요
// 요구사항
// 1. 사용자 회원가입 순서를 유추할 수 없다.
// 2. 데이터베이스에서 중복 확인
// 3. 너무 길면 안됨
// 4. 고유번호로 데이터베이스 키를 유추할 수 없다.
public interface UniqueNumberGenerator {
    Integer generate();
}
