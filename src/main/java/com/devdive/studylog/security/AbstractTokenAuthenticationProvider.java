package com.devdive.studylog.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

abstract class AbstractTokenAuthenticationProvider implements AuthenticationProvider {

    protected final TokenRepository tokenRepository;

    public AbstractTokenAuthenticationProvider(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String) authentication.getCredentials();

        if (!authenticate(token)) {
            throw new BadCredentialsException("Invalid Token");
        }

        String email = tokenRepository.findEmailByToken(token)
                        .orElseThrow(() -> new BadCredentialsException("Invalid Token"));
        tokenRepository.deleteByToken(token);
        TokenAuthenticationToken successToken = new TokenAuthenticationToken(email, null);
        successToken.setAuthenticated(true);
        return successToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (TokenAuthenticationToken.class.isAssignableFrom(authentication));
    }

    protected abstract boolean authenticate(String token);

}
