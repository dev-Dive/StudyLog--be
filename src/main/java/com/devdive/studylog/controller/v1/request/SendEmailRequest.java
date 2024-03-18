package com.devdive.studylog.controller.v1.request;

import jakarta.validation.constraints.Email;

public record SendEmailRequest(
        @Email String email
) {
}
