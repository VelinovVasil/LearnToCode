package org.example.events.npmg.payload.DTOs;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.example.events.npmg.models.Question;
import org.example.events.npmg.models.User;

import java.time.LocalDateTime;

/**
 * DTO for {@link Question}
 */


@Data
public class QuestionDto {

    @NotEmpty(message = "Question is mandatory!")
    private String question;

    private User user;
    private LocalDateTime date;

}
