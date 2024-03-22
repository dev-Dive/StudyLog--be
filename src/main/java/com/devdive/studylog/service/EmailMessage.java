package com.devdive.studylog.service;

import lombok.Getter;

import static java.lang.StringTemplate.STR;

public enum EmailMessage {

    SIGN_UP(
            "회원가입",
            "스터디로그 회원가입",
            "http://studylog.com?type=signup&token=%s"
            ),
    SIGN_IN(
            "로그인",
            "스터디로그 로그인",
            "http://studylog.com?type=signin&token=%s"
            );

    private final String type;
    @Getter
    private final String subject;
    private final String link;

    EmailMessage(String type, String subject, String link) {
        this.type = type;
        this.subject = subject;
        this.link = link;
    }

    public String getMessage(String token) {
        String link = String.format(this.link, token);
        return STR
                ."""
                    StudyLog \{this.type}을(를) 위해 다음 링크를 클릭해주세요.
                    \{link}
                """;
    }

}
