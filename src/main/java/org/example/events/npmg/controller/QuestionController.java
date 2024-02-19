package org.example.events.npmg.controller;


import lombok.RequiredArgsConstructor;
import org.example.events.npmg.payload.DTOs.QuestionDto;
import org.example.events.npmg.payload.response.MessageResponse;
import org.example.events.npmg.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("/")
    public ResponseEntity<MessageResponse> createQuestion(@RequestBody QuestionDto data) {
        return questionService.createQuestion(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDto> getQuestionById(@PathVariable Long id) {
        return questionService.getQuestionById(id);
    }

    @GetMapping("/")
    public ResponseEntity<List<QuestionDto>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> updateQuestion(@PathVariable Long id, @RequestBody QuestionDto data) {
        return questionService.updateQuestion(id, data);
    }

    /*
    @RequestBody - to map the request body to a Java object
    @RequestParam - to extract query parameters
    @PathVariable - to extract parameters from the URI
    */

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteQuestion(@PathVariable Long id) {
        return questionService.deleteQuestion(id);
    }
}
