package com.devdive.studylog.service;

import com.devdive.studylog.repository.MemberRepository;
import com.devdive.studylog.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final MemberRepository memberRepository;
    private final TokenRepository tokenRepository;
    private final EmailSender emailSender;

    public void sendAuthEmail(String email) {
        log.info(Thread.currentThread().getName());
        if (memberRepository.existsByEmail(email)) {
            // 로그인

        } else {
            // 회원가입
        }

        // uid 생성
        String token = tokenRepository.createAndSave(email);

        // email 내용 만들기
        String message = "";

        // email 전송
        emailSender.sendEmail(email, message);
    }


}
