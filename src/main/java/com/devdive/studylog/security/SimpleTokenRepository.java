package com.devdive.studylog.security;

import java.util.Optional;

class SimpleTokenRepository implements TokenRepository {
    @Override
    public String createAndSave(String email) {
        return "";
    }

    @Override
    public Optional<String> findEmailByToken(String token) {
        return Optional.of("");
    }

    @Override
    public boolean containsToken(String token) {
        return true;
    }

    @Override
    public boolean deleteByToken(String token) {
        return true;
    }
}
