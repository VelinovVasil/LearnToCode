package org.example.events.npmg.controller;

//unused imports
//pres ctrl+alt+shift+l
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Post;
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

    //make create reply

    @GetMapping("/{id}")
    public ResponseEntity<ReplyDto> getReplyByID(@PathVariable Long id) {
        return replyService.getReplyById(id);
    }


    @GetMapping("/")
    public ResponseEntity<List<ReplyDto>> getAllReplies() {
        return replyService.getAllReplies();
    }

    //use update instead of change like in QuestionController
    @PutMapping("/{id}/reply")
    public ResponseEntity<MessageResponse> updateReply(@PathVariable Long id, String reply) {
        return replyService.updateReply(id, reply);
    }

    //delete mapping
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteReply(@PathVariable Long id) {
        return replyService.deleteReply(id);
    }

    //you don't need it
    @PostMapping("/{author_id}/{question_id}/reply")
    public ResponseEntity<MessageResponse> postReplyToQuestion(@PathVariable Long author_id, Long question_id, String reply) {
        return replyService.postReplyToQuestion(author_id, question_id, reply);
    }

    //think of a better way to receive data, usually path variables are used only for ids for the current controller
    //use post mapping
    @PostMapping("/{author_id}/{reply_id}/reply")
    public ResponseEntity<MessageResponse> postReplyToReply(@PathVariable Long author_id, Long reply_id, String reply) {
        return replyService.postReplyToReply(author_id, reply_id, reply);
    }

}
