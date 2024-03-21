package com.devdive.studylog.service;

import com.devdive.studylog.entity.Member;
import com.devdive.studylog.repository.MemberRepository;
import com.devdive.studylog.repository.TokenRepository;
import com.devdive.studylog.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TokenRepository tokenRepository;
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;
    private final UniqueNumberGenerator uniqueNumberGenerator;

    public String signUp(String token, String nickname) {
        // 사전조건
        // 1. 토큰이 유효한지 확인
        // 2. 이미 등록된 이메일인지 확인
        String email = tokenRepository.findEmailByToken(token)
                .orElseThrow(RuntimeException::new);
        if (memberRepository.existsByEmail(email)) {
            throw new RuntimeException();
        }

        // 유니크넘버 생성
        Integer uniqueNumber = uniqueNumberGenerator.generate();

        // 회원가입
        Member member = new Member(nickname, email, uniqueNumber);
        memberRepository.save(member);
        return jwtProvider.createToken(email);
    }
}
