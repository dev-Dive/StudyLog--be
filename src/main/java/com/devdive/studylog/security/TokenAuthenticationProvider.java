package com.devdive.studylog.security;

class TokenAuthenticationProvider extends AbstractTokenAuthenticationProvider {

    public TokenAuthenticationProvider(TokenRepository tokenRepository) {
        super(tokenRepository);
    }

    // Todo: 로그인 검증 시 MemberRepository 확인 여부 결정 해야 함
    // Todo: 인증 완료 후 토큰 제거 여부 결정 해야 함
    @Override
    protected boolean authenticate(String token) {
        return tokenRepository.containsToken(token);
    }
}
