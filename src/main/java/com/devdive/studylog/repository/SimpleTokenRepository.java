package com.devdive.studylog.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SimpleTokenRepository implements TokenRepository {
    @Override
    public String createAndSave(String email) {
        return null;
    }

    @Override
    public Optional<String> findEmailByToken(String token) {
        return Optional.empty();
    }

    @Override
    public boolean containsToken(String token) {
        return false;
    }

    @Override
    public boolean deleteByToken(String token) {
        return false;
    }
}
