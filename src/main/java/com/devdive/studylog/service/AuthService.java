package com.devdive.studylog.service;

import com.devdive.studylog.entity.Member;
import com.devdive.studylog.repository.MemberRepository;
import com.devdive.studylog.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TokenRepository tokenRepository;
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;

    public String signUp(String token, String nickname) {
        String email = tokenRepository.findEmailByToken(token)
                .orElseThrow(RuntimeException::new);
        Member member = new Member(nickname, email);
        memberRepository.save(member);
        return jwtProvider.create(email);
    }
}
