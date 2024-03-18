package com.devdive.studylog.service;

import com.devdive.studylog.repository.MemberRepository;
import com.devdive.studylog.repository.TokenRepository;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final MemberRepository memberRepository;
    private final TokenRepository tokenRepository;
    private final SimpleMailMessage templateMailMessage;

    public void sendEmail(String email) {

        if (memberRepository.existsByEmail(email)) {
            // 로그인

        } else {
            // 회원가입
        }

        // uid 생성
        String token = tokenRepository.createAndSave(email);

        // email 내용 만들기

                // email 전송
        sendEmail(email, "");
    }

    public void sendEmail(String to, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage(templateMailMessage);
        mailMessage.setTo(to);
        mailMessage.setText(message);
        mailSender.send(mailMessage);

    }
}
