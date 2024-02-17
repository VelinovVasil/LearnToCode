package org.example.events.npmg.service;

import lombok.RequiredArgsConstructor;
import org.example.events.npmg.config.Mapper.QuestionMapper;
import org.example.events.npmg.models.Question;
import org.example.events.npmg.payload.DTOs.QuestionDto;
import org.example.events.npmg.payload.response.MessageResponse;
import org.example.events.npmg.repository.QuestionRepository;
import org.example.events.npmg.repository.TagRepository;
import org.example.events.npmg.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.example.events.npmg.util.RepositoryUtil.findById;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final ModelMapper modelMapper;

    public ResponseEntity<MessageResponse> createQuestion(QuestionDto data) {
        //maybe implement custom exceptions and validations for the data
        Question question = questionMapper.toEntity(data);
        questionRepository.save(question);

        return ResponseEntity.ok(new MessageResponse("The question has been created successfully!"));
    }

    public ResponseEntity<QuestionDto> getQuestionById(Long questionId) {
        Question question = findById(questionRepository, questionId);
        System.out.println(question);
        QuestionDto questionDto = questionMapper.toDto(question);
        return ResponseEntity.ok(questionDto);
    }

    public ResponseEntity<List<QuestionDto>> getAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        List<QuestionDto> questionDtos = questionMapper.toDto(questions);
        return ResponseEntity.ok(questionDtos);
    }

    public ResponseEntity<MessageResponse> updateQuestion(Long id, QuestionDto data) {
        Question question = findById(questionRepository, id);
        modelMapper.map(data, question);//this maps the data from the dto to the entity and scip the null values, because the modelmapper is configured to do so
        questionRepository.save(question);
        return ResponseEntity.ok(new MessageResponse("Event updated successfully!"));
    }

    //useless method, you have updateQuestion
    /*
    * if you want to update a question text you this json:
    * {
    *   text: "newText"
    * }
    * everything else is automatically null, and it won't be mapped because we have configured the ModelMapper*/
    public ResponseEntity<MessageResponse> changeQuestion(Long questionId, String question) {
        Question question1 = findById(questionRepository, questionId);
        question1.setText(question);
        questionRepository.save(question1);

        return ResponseEntity.ok(new MessageResponse("The question has been changed successfully!"));
    }

    public ResponseEntity<MessageResponse> deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
        return ResponseEntity.ok(new MessageResponse("The question has been deleted successfully!"));
    }


    //too long
//
//    public ResponseEntity<MessageResponse> createQuestion(Long userId, String questionContent, List<Long> tagIds) {
//
//        // Each question has a body (content),
//        // userId by which the author is found in the userRepository,
//        // tagIds (similar logic to userId)
//
//
//        User author = findById(userRepository, userId);
//
//        Question question = new Question();
//        question.setQuestion(questionContent);
//        question.setUser(author);
//
//        Set<Tag> tags = new LinkedHashSet<>();
//        for (Long tagId : tagIds) {
//            Tag tag = findById(tagRepository, tagId);
//            tags.add(tag);
//        }
//
//        question.setTags(tags);
//
//        questionRepository.save(question);
//
//        return ResponseEntity.ok(new MessageResponse("The question has been posted successfully!"));
//    }
//
//

}