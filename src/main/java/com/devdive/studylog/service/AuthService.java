package com.devdive.studylog.service;

import com.devdive.studylog.entity.Member;
import com.devdive.studylog.repository.MemberRepository;
import com.devdive.studylog.repository.TokenRepository;
import com.devdive.studylog.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TokenRepository tokenRepository;
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;
    private final UniqueNumberGenerator uniqueNumberGenerator;
    private final EmailService emailService;

    public String signUp(String token, String nickname) {
        String email = tokenRepository.findEmailByToken(token)
                .orElseThrow(NoSuchElementException::new);
        if (memberRepository.existsByEmail(email)) {
            throw new NoSuchElementException();
        }

        Integer uniqueNumber = uniqueNumberGenerator.generate();

        Member member = new Member(nickname, email, uniqueNumber);
        memberRepository.save(member);
        return jwtProvider.createToken(email);
    }

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
