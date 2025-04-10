package com.practice.paymentassignment.controller;

import com.practice.paymentassignment.service.ViewCountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ViewTestController {

    private final ViewCountService viewCountService;

    @GetMapping("/view/test")
    public ResponseEntity<Void> viewTestPage(@RequestParam String articleId,
                                             @RequestParam String userId) {
        viewCountService.recordView(articleId, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/view/number")
    public ResponseEntity<Long> viewNumber(@RequestParam String articleId) {
        Long count = viewCountService.getUniqueViews(articleId);
        return ResponseEntity.ok(count);
    }

}
