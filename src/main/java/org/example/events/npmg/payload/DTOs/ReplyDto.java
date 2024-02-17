package org.example.events.npmg.payload.DTOs;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Value;
import org.example.events.npmg.models.Reply;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Reply}
 */
@Data
public class ReplyDto {
    Long userId;
    String text;
    LocalDateTime dateOfCreation;
    Long questionId;
}