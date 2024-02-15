package org.example.events.npmg.config.Mapper;


import org.example.events.npmg.models.Question;
import org.example.events.npmg.models.Tag;
import org.example.events.npmg.payload.DTOs.QuestionDto;
import org.example.events.npmg.payload.DTOs.TagDto;
import org.example.events.npmg.payload.DTOs.UserDto;
import org.example.events.npmg.repository.QuestionRepository;
import org.example.events.npmg.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import static org.example.events.npmg.util.RepositoryUtil.findById;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class QuestionMapper {

    private final TagMapper tagMapper;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;


    public QuestionMapper(ModelMapper modelMapper, TagMapper tagMapper,
                          UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.tagMapper = tagMapper;
        this.userRepository = userRepository;
    }

    public QuestionDto toDto(Question question) {
        return modelMapper.map(question, QuestionDto.class);
    }

    public List<QuestionDto> toDtoList(List<Question> questions) {
        return questions.stream().map(this::toDto).collect(Collectors.toList());
    }

    private Set<TagDto> mapTagsToDto(Set<Tag> tags) {
        return tags.stream().map(tagMapper::toDto).collect(Collectors.toSet());
    }

    public Question toEntity(QuestionDto questionDto) {
        Question question = modelMapper.map(questionDto, Question.class);
        UserDto author = questionDto.getUser();
        question.setUser(findById(userRepository, author.getId()));
        return question;
    }
}

