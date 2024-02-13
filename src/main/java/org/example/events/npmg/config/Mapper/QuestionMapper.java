package org.example.events.npmg.config.Mapper;


import org.example.events.npmg.models.Question;
import org.example.events.npmg.models.Tag;
import org.example.events.npmg.payload.DTOs.QuestionDto;
import org.example.events.npmg.payload.DTOs.TagDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class QuestionMapper {

    private final TagMapper tagMapper;
    private final ModelMapper modelMapper;

    public QuestionMapper(ModelMapper modelMapper, TagMapper tagMapper) {
        this.modelMapper = modelMapper;
        this.tagMapper = tagMapper;
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
}

