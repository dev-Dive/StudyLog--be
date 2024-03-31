package com.devdive.studylog.service;

import com.devdive.studylog.entity.Member;
import com.devdive.studylog.repository.MemberRepository;
import com.devdive.studylog.security.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TokenRepository tokenRepository;
    private final MemberRepository memberRepository;
    private final UniqueNumberGenerator uniqueNumberGenerator;
    private final EmailService emailService;

    public void signUp(String nickname, String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new RuntimeException();
        }

        Integer uniqueNumber = uniqueNumberGenerator.generate();

        Member member = new Member(nickname, email, uniqueNumber);
        memberRepository.save(member);
    }

    // Todo: 인증 토큰 생성은 비즈니스 레이어의 책임일까? 웹 레이어의 책임일까?
    // 애초에 인증 메일의 내용 자체가 웹(인증) 레이어의 책임 아닐까?
    public void sendEmailForAuthentication(String email) {
        if (memberRepository.existsByEmail(email)) {
            String token = tokenRepository.createAndSave(email);
            String subject = EmailMessage.SIGN_IN.getSubject();
            String message = EmailMessage.SIGN_UP.getMessage(token);
            emailService.sendEmail(email, subject, message);
        } else {
            String token = tokenRepository.createAndSave(email);
            String subject = EmailMessage.SIGN_UP.getSubject();
            String message = EmailMessage.SIGN_UP.getMessage(token);
            emailService.sendEmail(email, subject, message);
        }
    }
}
