package org.example.events.npmg.config.Mapper;


import org.example.events.npmg.models.Question;
import org.example.events.npmg.models.Tag;
import org.example.events.npmg.payload.DTOs.QuestionDto;
import org.example.events.npmg.payload.DTOs.TagDto;
import org.example.events.npmg.repository.TagRepository;
import org.example.events.npmg.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.example.events.npmg.util.RepositoryUtil.findById;

@Component
public class QuestionMapper {

    private final TagMapper tagMapper;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;


    public QuestionMapper(ModelMapper modelMapper, TagMapper tagMapper, UserRepository userRepository, TagRepository tagRepository) {
        this.modelMapper = modelMapper;
        this.tagMapper = tagMapper;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
        this.modelMapper.addMappings(new PropertyMap<Question, QuestionDto>() {
            @Override
            protected void configure() {
                map().setAuthorId(source.getAuthor().getId());
                if (source.getTags() != null) {
                    map().setTagIds(
                            source.getTags().stream().map(Tag::getId).collect(Collectors.toSet())
                    );
                }
            }
        });
    }

    public QuestionDto toDto(Question question) {
        return modelMapper.map(question, QuestionDto.class);
    }

    public List<QuestionDto> toDto(List<Question> questions) {
        return questions.stream().map(this::toDto).collect(Collectors.toList());
    }

    //this shouldn't be here, in the TagMapper
    private Set<TagDto> mapTagsToDto(Set<Tag> tags) {
        return tags.stream().map(tagMapper::toDto).collect(Collectors.toSet());
    }

    public Question toEntity(QuestionDto questionDto) {
        Question question = modelMapper.map(questionDto, Question.class);
        question.setAuthor(findById(userRepository, questionDto.getAuthorId()));
// this should work, but i don't know if its better to make a call to the db or just to map the ids, f.e if the tag's id does not exist in the db
/*
        if (questionDto.getTagIds() != null) {
            question.setTags(questionDto.getTagIds().stream().map(id -> {
                Tag tag = new Tag();
                tag.setId(id);
                return tag;
            }).collect(Collectors.toSet()));
        }
*/
        if (questionDto.getTagIds() != null) {
            question.setTags(questionDto.getTagIds().stream().map(id -> findById(tagRepository, id)).collect(Collectors.toSet()));
        }
        return question;
    }
}

