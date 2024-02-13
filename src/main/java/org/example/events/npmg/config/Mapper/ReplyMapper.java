package org.example.events.npmg.config.Mapper;


import org.example.events.npmg.models.Reply;
import org.example.events.npmg.payload.DTOs.ReplyDto;
import org.example.events.npmg.repository.QuestionRepository;
import org.example.events.npmg.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

import static org.example.events.npmg.util.RepositoryUtil.findById;

@Component
public class ReplyMapper {

    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    private final QuestionRepository questionRepository;

    public ReplyMapper(ModelMapper modelMapper, UserRepository userRepository, QuestionRepository questionRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.modelMapper.addMappings(new PropertyMap<Reply, ReplyDto>() {
            @Override
            protected void configure() {
                map().setUserId(source.getUser().getId());
                map().setQuestionId(source.getQuestion().getId());
            }
        });
    }

    public ReplyDto toDto(Reply reply) {
        return modelMapper.map(reply, ReplyDto.class);
    }

    public List<ReplyDto> toDto(List<Reply> replies) {
        Type listType = new TypeToken<List<ReplyDto>>() {
        }.getType();
        return modelMapper.map(replies, listType);
    }

    public Reply toEntity(ReplyDto replyDto) {
        Reply reply = modelMapper.map(replyDto, Reply.class);
        reply.setUser(findById(userRepository, replyDto.getUserId()));
        reply.setQuestion(findById(questionRepository, replyDto.getQuestionId()));
        return reply;
    }
}
