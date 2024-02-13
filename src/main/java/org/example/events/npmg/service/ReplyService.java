package org.example.events.npmg.service;

import lombok.RequiredArgsConstructor;
import org.example.events.npmg.config.Mapper.ReplyMapper;
import org.example.events.npmg.config.Mapper.UserMapper;
import org.example.events.npmg.models.Question;
import org.example.events.npmg.models.Reply;
import org.example.events.npmg.models.User;
import org.example.events.npmg.payload.DTOs.ReplyDto;
import org.example.events.npmg.payload.response.MessageResponse;
import org.example.events.npmg.repository.QuestionRepository;
import org.example.events.npmg.repository.ReplyRepository;
import org.example.events.npmg.repository.RoleRepository;
import org.example.events.npmg.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.events.npmg.util.RepositoryUtil.findById;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final ReplyMapper replyMapper;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public ResponseEntity<ReplyDto> getReplyById(Long userId) {
        Reply reply = findById(replyRepository, userId);
        return ResponseEntity.ok(replyMapper.toDto(reply));
    }

    public ResponseEntity<List<ReplyDto>> getAllReplies() {
        List<Reply> replies = replyRepository.findAll();
        List<ReplyDto> replyDtos = replyMapper.toDto(replies);
        return ResponseEntity.ok(replyDtos);
    }

    public ResponseEntity<MessageResponse> changeReply(Long replyId, String reply) {
        Reply reply1 = findById(replyRepository, replyId);
        reply1.setReply(reply);
        replyRepository.save(reply1);

        return ResponseEntity.ok(new MessageResponse("The reply has been changed successfully!"));
    }

    public ResponseEntity<MessageResponse> deleteReply(Long replyId) {
        replyRepository.deleteById(replyId);
        return ResponseEntity.ok(new MessageResponse("The reply has been deleted successfully!"));
    }

    public ResponseEntity<MessageResponse> postReplyToQuestion(Long authorId, Long questionId, String replyComment) {

        // This method posts a reply to a certain Question

        Reply reply = new Reply();

        Question question = findById(questionRepository, questionId);
        reply.setQuestion(question);

        User author = findById(userRepository, authorId);
        reply.setUser(author);

        reply.setReply(replyComment);

        replyRepository.save(reply);

        return ResponseEntity.ok(new MessageResponse("A reply to the question was successfully posted"));
    }

    public ResponseEntity<MessageResponse> postReplyToReply(Long authorId, Long replyId, String replyComment) {

        // This method posts a reply to a certain reply (reply to a reply)

        Reply reply = new Reply();

        User author = findById(userRepository, authorId);
        reply.setUser(author);

        Reply repliedReply = findById(replyRepository, replyId);
        reply.setReplyToReply(repliedReply);

        reply.setReply(replyComment);

        replyRepository.save(reply);

        return ResponseEntity.ok(new MessageResponse("A reply to the reply was successfully posted"));
    }
}
