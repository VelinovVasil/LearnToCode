package org.example.events.npmg.service;

import lombok.RequiredArgsConstructor;
import org.example.events.npmg.config.Mapper.TagMapper;
import org.example.events.npmg.models.Tag;
import org.example.events.npmg.payload.DTOs.TagDto;
import org.example.events.npmg.payload.response.MessageResponse;
import org.example.events.npmg.repository.TagRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.events.npmg.util.RepositoryUtil.findById;


@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    public ResponseEntity<TagDto> createTag(TagDto data) {
        Tag tag = tagMapper.toEntity(data);
        tagRepository.save(tag);

        return ResponseEntity.ok(tagMapper.toDto(tag));
    }

    public ResponseEntity<TagDto> getTagById(Long tagId) {
        Tag tag = findById(tagRepository, tagId);
        TagDto tagDto = tagMapper.toDto(tag);

        return ResponseEntity.ok(tagDto);
    }

    public ResponseEntity<List<TagDto>> getAllTags() {
        List<Tag> tags = tagRepository.findAll();
        List<TagDto> tagDtos = tagMapper.toDto(tags);

        return ResponseEntity.ok(tagDtos);
    }

    //better to use TagDto instead of only 'name', what if you want to add more fields in the future?
    public ResponseEntity<MessageResponse> updateTag(Long tagId, String name) {
        Tag toUpdate = findById(tagRepository, tagId);
        toUpdate.setName(name);
        tagRepository.save(toUpdate);

        return ResponseEntity.ok(new MessageResponse("Tag name has been updated successfully."));
    }

    public ResponseEntity<MessageResponse> deleteTag(Long tagId) {
        tagRepository.deleteById(tagId);

        return ResponseEntity.ok(new MessageResponse("Tag has been deleted successfully."));
    }
}
