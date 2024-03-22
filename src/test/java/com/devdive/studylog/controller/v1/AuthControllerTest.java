package com.devdive.studylog.controller.v1;

import com.devdive.studylog.controller.v1.request.EmailSignUpRequest;
import com.devdive.studylog.controller.v1.request.SendEmailRequest;
import com.devdive.studylog.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
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

}
