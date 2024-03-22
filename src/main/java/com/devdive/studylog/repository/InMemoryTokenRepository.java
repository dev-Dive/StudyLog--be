package com.devdive.studylog.repository;

import com.devdive.studylog.service.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@RequiredArgsConstructor
public class InMemoryTokenRepository implements TokenRepository {

    private final Map<String, String> map = new ConcurrentHashMap<>();
    private final TokenProvider tokenProvider;

    @Override
    public String createAndSave(String email) {
        String token;
        do {
            token = tokenProvider.generate(email, LocalDateTime.now())
                    .substring(0, 8);
        } while (!map.containsKey(token));
        return token;
    }

    @Override
    public Optional<String> findEmailByToken(String token) {
        return Optional.ofNullable(map.get(token));
    }

    @Override
    public boolean containsToken(String token) {
        return map.containsKey(token);
    }

    @Override
    public boolean deleteByToken(String token) {
        return map.remove(token) != null;
    }

}
