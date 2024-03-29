package com.mediashare.videoplatform.controller;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class CsrfController {
    @GetMapping("/csrf-token")
    public Map<String, String> getCsrfToken(CsrfToken csrfToken) {
        return Collections.singletonMap("csrfToken", csrfToken.getToken());
    }
}
