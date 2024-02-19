package org.example.events.npmg.config.Mapper;


import org.example.events.npmg.models.Question;
import org.example.events.npmg.models.Tag;
import org.example.events.npmg.payload.DTOs.TagDto;
import org.example.events.npmg.repository.QuestionRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.example.events.npmg.util.RepositoryUtil.findById;

@Component
public class TagMapper {
    private final ModelMapper modelMapper;
//    private final QuestionRepository questionRepository;

    public TagMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
//        this.questionRepository = questionRepository;
    }

    public TagDto toDto(Tag tag) {
        return modelMapper.map(tag, TagDto.class);
    }

    public List<TagDto> toDto(List<Tag> tags) {
        return tags.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Tag toEntity(TagDto dto) {
        Tag tag = modelMapper.map(dto, Tag.class);
        return tag;
    }
    public List<Tag> toEntity(List<TagDto> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
