package com.devdive.studylog.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Primary
@Repository
public class SimpleTokenRepository implements TokenRepository {
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
