package org.example.events.npmg.controller;


import lombok.RequiredArgsConstructor;
import org.example.events.npmg.payload.DTOs.TagDto;
import org.example.events.npmg.payload.response.MessageResponse;
import org.example.events.npmg.service.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tags")
public class TagController {

    private final TagService tagService;

    @PostMapping("/")
    public ResponseEntity<TagDto> createTag(@RequestBody TagDto tagDto) {
        return tagService.createTag(tagDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDto> getTagById(@PathVariable Long id) {
        return tagService.getTagById(id);
    }

    @GetMapping("/")
    public ResponseEntity<List<TagDto>> getAllTags() {
        return tagService.getAllTags();
    }

    //path: /api/tags/{id}?name=updatedName
    //but it's better to use TagDto instead of only 'name', what if you want to add more fields in the future?
    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> updateTagName(@PathVariable Long id, @RequestParam String name) {
        return tagService.updateTag(id, name);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteTag(@PathVariable Long id) {
        return tagService.deleteTag(id);
    }
}
