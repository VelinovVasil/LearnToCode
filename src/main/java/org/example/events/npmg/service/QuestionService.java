package org.example.events.npmg.service;

import lombok.RequiredArgsConstructor;
import org.example.events.npmg.config.Mapper.QuestionMapper;
import org.example.events.npmg.config.Mapper.ReplyMapper;
import org.example.events.npmg.models.Question;
import org.example.events.npmg.models.Reply;
import org.example.events.npmg.payload.DTOs.QuestionDto;
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
public class QuestionService {


    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;


    public ResponseEntity<QuestionDto> getQuestionById(Long questionId) {
        Question question = findById(questionRepository, questionId);
        return ResponseEntity.ok(questionMapper.toDto(question));
    }

    public ResponseEntity<List<QuestionDto>> getAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        List<QuestionDto> questionDtos = questionMapper.toDtoList(questions);
        return ResponseEntity.ok(questionDtos);
    }

    public ResponseEntity<MessageResponse> changeQuestion(Long questionId, String question) {
        Question question1 = findById(questionRepository, questionId);
        question1.setQuestion(question);
        questionRepository.save(question1);

        return ResponseEntity.ok(new MessageResponse("The question has been changed successfully!"));
    }

    public ResponseEntity<MessageResponse> deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
        return ResponseEntity.ok(new MessageResponse("The question has been deleted successfully!"));
    }

    //public ResponseEntity<MessageResponse> postQuestion(Long questionId, String reply) {

    //}

}
