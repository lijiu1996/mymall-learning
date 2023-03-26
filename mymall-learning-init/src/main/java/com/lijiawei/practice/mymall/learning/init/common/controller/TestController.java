package com.lijiawei.practice.mymall.learning.init.common.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;

@RequestMapping("test")
@RestController
public class TestController {

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    static class TestData {

        private String message;
        private Date date;
        private LocalDateTime newDate;
    }

    @GetMapping("/jackson")
    public TestData testJackson() {
        TestData res = new TestData();
        res.setMessage("test");
        res.setDate(new Date());
        res.setNewDate(LocalDateTime.now());
        return res;
    }
}
