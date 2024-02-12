package org.example.events.npmg.payload.DTOs;

import jakarta.validation.constraints.NotEmpty;
import lombok.Value;
import org.example.events.npmg.models.Question;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link Question}
 */
@Value
public class QuestionDto implements Serializable {
    @NotEmpty(message = "Question is mandatory!")
    String question;
    Long userId;
    LocalDateTime datePublished;
    Long questionId;
    List<String> imageUrls;
}