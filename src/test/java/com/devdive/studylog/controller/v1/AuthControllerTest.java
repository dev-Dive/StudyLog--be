package com.devdive.studylog.controller.v1;

import com.devdive.studylog.security.WebSecurityConfig;
import com.devdive.studylog.controller.v1.request.EmailSignUpRequest;
import com.devdive.studylog.controller.v1.request.SendEmailRequest;
import com.devdive.studylog.security.SimpleTokenRepository;
import com.devdive.studylog.security.JwtProvider;
import com.devdive.studylog.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Todo: 컨트롤러 레이어에 대한 분리가 필요하다 느낌
@WebMvcTest(AuthController.class)
@Import(value = {
        WebSecurityConfig.class,
        JwtProvider.class,
        SimpleTokenRepository.class
})
class AuthControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    AuthService authService;

    @Test
    void sendEmail() throws Exception {
        SendEmailRequest request = new SendEmailRequest("test@test.com");
        String content = objectMapper.writeValueAsString(request);

        mvc.perform(
                post("/api/v1/auth/email/send")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    void signup() throws Exception {
        EmailSignUpRequest request = new EmailSignUpRequest("token", "nickname");
        String content = objectMapper.writeValueAsString(request);

        mvc.perform(
                post("/api/v1/auth/email/signup")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").hasJsonPath());
    }

    @Test
    void signin() throws Exception {
        Map<String, String> request = new HashMap<>();
        request.put("token", "token");
        String content = objectMapper.writeValueAsString(request);

        mvc.perform(
                        post("/api/v1/auth/email/signin")
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").exists());
    }

}
