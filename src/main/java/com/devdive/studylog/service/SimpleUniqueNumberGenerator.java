package com.devdive.studylog.service;

import org.springframework.stereotype.Component;

@Component
public class SimpleUniqueNumberGenerator implements UniqueNumberGenerator {
    @Override
    public Integer generate() {
        return 0;
    }
}
