package com.devdive.studylog.security;

import com.devdive.studylog.repository.TokenRepository;

import java.util.Optional;

public class TokenAuthenticationProvider extends AbstractTokenAuthenticationProvider {

    public TokenAuthenticationProvider(TokenRepository tokenRepository) {
        super(tokenRepository);
    }

    // Todo: 로그인 검증 시 MemberRepository 확인 여부 결정 해야 함
    @Override
    protected boolean authenticate(String token) {
        return tokenRepository.containsToken(token);
    }
}
