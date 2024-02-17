package org.example.events.npmg.controller;

//unused imports
//pres ctrl+alt+shift+l

import lombok.RequiredArgsConstructor;
import org.example.events.npmg.payload.DTOs.ReplyDto;
import org.example.events.npmg.payload.response.MessageResponse;
import org.example.events.npmg.service.ReplyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/replies")
public class ReplyController {

    private final ReplyService replyService;

    //make create reply
    @PostMapping("/")
    public ResponseEntity<MessageResponse> createReply(@RequestBody ReplyDto data) {
        return replyService.createReply(data);
    }

    //make create child reply
    @PostMapping("/{parentId}")
    public ResponseEntity<MessageResponse> createChildReply(@PathVariable Long parentId, @RequestBody ReplyDto data) {
        return replyService.createChildReply(parentId, data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReplyDto> getReplyById(@PathVariable Long id) {
        return replyService.getReplyById(id);
    }


    @GetMapping("/")
    public ResponseEntity<List<ReplyDto>> getAllReplies() {
        return replyService.getAllReplies();
    }

    //path: /api/replies/{id}/reply?reply=yourReply
    //with longer text or more parameters text use @RequestBody and a class f.e. ReplyDto
    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> updateReply(@PathVariable Long id, @RequestParam String reply) {
        return replyService.updateReply(id, reply);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteReply(@PathVariable Long id) {
        return replyService.deleteReply(id);
    }

    //you don't need it
/*
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
*/
}
