package com.devdive.studylog.controller.v1;

import com.devdive.studylog.controller.v1.request.EmailSignUpRequest;
import com.devdive.studylog.controller.v1.request.SendEmailRequest;
import com.devdive.studylog.controller.v1.response.SignInResponse;
import com.devdive.studylog.service.AuthService;
import com.devdive.studylog.service.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final EmailService emailService;
    private final AuthService authService;

    @PostMapping("/email/send")
    public ResponseEntity<Void> sendEmail(@RequestBody @Valid SendEmailRequest request) {
        emailService.sendAuthEmail(request.email());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/email/signup")
    public ResponseEntity<SignInResponse> signup(@RequestBody @Valid EmailSignUpRequest request) {
        String accessToken = authService.signUp(request.token(), request.nickname());
        return ResponseEntity.ok()
                .body(new SignInResponse(accessToken));
    }

}
