package org.example.events.npmg.payload.DTOs;

import jakarta.validation.constraints.NotEmpty;
import lombok.Value;
import org.example.events.npmg.models.Reply;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Reply}
 */
@Value
public class ReplyDto implements Serializable {
    Long userId;
    @NotEmpty(message = "Reply to the question is mandatory")
    String reply;
    LocalDateTime dateOfCreation;
    Long questionId;
}