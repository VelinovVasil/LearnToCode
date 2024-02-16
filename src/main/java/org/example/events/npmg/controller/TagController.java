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

    @GetMapping("/{id}")
    public ResponseEntity<TagDto> getTagById(@PathVariable Long id) {
        return tagService.getTagById(id);
    }

    @GetMapping("/")
    public ResponseEntity<List<TagDto>> getAllTags() {
        return tagService.getAllTags();
    }

    @PutMapping("/{id}/text")
    public ResponseEntity<MessageResponse> updateTag(@PathVariable Long id, String text) {
        return tagService.updateTag(id, text);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteTag(@PathVariable Long id) {
        return tagService.deleteTag(id);
    }
}
