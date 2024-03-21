package com.devdive.studylog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

@Configuration
public class EmailConfig {

    private static final String FROM_EMAIL = "noreply@studylog.com";

    @Bean
    public SimpleMailMessage templateMailMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(FROM_EMAIL);
        message.setSubject("StudyLog 회원가입");
        message.setText(
                "StudyLog 회원가입을 위해 다음 링크를 클릭해주세요\n%s\n"
        );
        return message;
    }
//
//    @Bean
//    public SimpleMailMessage singInTemplateSimpleMessage() {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(FROM_EMAIL);
//        message.setSubject("StudyLog 로그인");
//        message.setText(
//                "StudyLog 로그인을 위해 다음 링크를 클릭해주세요\n%s\n"
//        );
//        return message;
//    }

}
