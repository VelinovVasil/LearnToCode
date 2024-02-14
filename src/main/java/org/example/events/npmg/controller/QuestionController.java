package org.example.events.npmg.controller;


import lombok.RequiredArgsConstructor;
import org.example.events.npmg.payload.DTOs.QuestionDto;
import org.example.events.npmg.service.QuestionService;
import org.springframework.web.bind.annotation.RestController;
import org.example.events.npmg.models.Role.ERole;
import org.example.events.npmg.payload.DTOs.ReplyDto;
import org.example.events.npmg.payload.response.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService questionService;


    @GetMapping("/{id}")
    public ResponseEntity<QuestionDto> getQuestionById(@PathVariable Long id) {
        return questionService.getQuestionById(id) ;
    }

    @GetMapping("/")
    public ResponseEntity<List<QuestionDto>> getAllQuestions() {
        return questionService.getAllQuestions();
    }


    @GetMapping("/{id}/question")
    public ResponseEntity<MessageResponse> changeQuestion(@PathVariable Long id, String question) {
        return questionService.changeQuestion(id, question);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteQuestion(@PathVariable Long id) {
        return questionService.deleteQuestion(id);
    }


    @GetMapping("/{author_id}/question/tag")
    public ResponseEntity<MessageResponse> postQuestion(@PathVariable Long userId, String question, List<Long> tagIds) {
        return questionService.postQuestion(userId, question, tagIds);
    }
}