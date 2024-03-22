package com.devdive.studylog.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Slf4j
class TokenProviderTest {

    @Test
    void generate() {
        TokenProvider provider = new TokenProvider();
        log.info(provider.generate("test@test.com", LocalDateTime.MAX));
        log.info(provider.generate("test@test.com", LocalDateTime.MAX.minus(1, ChronoUnit.DAYS)));
    }
}
