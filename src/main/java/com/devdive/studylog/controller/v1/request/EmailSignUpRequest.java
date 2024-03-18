package com.devdive.studylog.controller.v1.request;

public record EmailSignUpRequest(
        String token,
        String nickname
) {
}
