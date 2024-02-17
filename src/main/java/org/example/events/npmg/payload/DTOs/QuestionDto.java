package org.example.events.npmg.payload.DTOs;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Value;
import org.example.events.npmg.models.Question;
import org.example.events.npmg.payload.DTOs.TagDto;
import org.example.events.npmg.payload.DTOs.UserDto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * DTO for {@link Question}
 */
//use @Data because modelMapper won't be able to map the object to the DTO
@Data
public class QuestionDto {
    long id;
    String text;
    Long authorId;
    Set<Long> tagIds;
    List<String> imageUrls;
    LocalDateTime datePublished;
}