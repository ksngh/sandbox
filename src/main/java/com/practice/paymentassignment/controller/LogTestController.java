package com.practice.paymentassignment.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class LogTestController {
    @GetMapping("/log")
    public String log() {
        log.info("테스트 로그입니다.");
        return "ok";
    }
}