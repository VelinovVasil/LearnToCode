package org.example.events.npmg.controller;


import com.azure.core.annotation.Get;
import lombok.RequiredArgsConstructor;
import org.example.events.npmg.payload.DTOs.QuestionDto;
import org.example.events.npmg.service.QuestionService;
import org.example.events.npmg.service.ReplyService;
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
@RequestMapping("/api/replies")
public class ReplyController {

    private final ReplyService replyService;

    @GetMapping("/{id}")
    public ResponseEntity<ReplyDto> getReplyByID(@PathVariable Long id) {
        return replyService.getReplyById(id);
    }


    @GetMapping("/")
    public ResponseEntity<List<ReplyDto>> getAllReplies() {
        return replyService.getAllReplies();
    }

    @GetMapping("/{id}/reply")
    public ResponseEntity<MessageResponse> changeReply(@PathVariable Long id, String reply) {
        return replyService.changeReply(id, reply);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteReply(@PathVariable Long id) {
        return replyService.deleteReply(id);
    }

    @GetMapping("/{author_id}/{question_id}/reply")
    public ResponseEntity<MessageResponse> postReplyToQuestion(@PathVariable Long userId, Long questionId, String reply) {
        return replyService.postReplyToQuestion(userId, questionId, reply);
    }

    @GetMapping("/{author_id}/{reply_id}/reply")
    public ResponseEntity<MessageResponse> postReplyToReply(@PathVariable Long userId, Long replyId, String reply) {
        return replyService.postReplyToReply(userId, replyId, reply);
    }

}
