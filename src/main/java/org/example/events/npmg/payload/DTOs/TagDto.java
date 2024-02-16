package org.example.events.npmg.payload.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Value;
import org.example.events.npmg.models.Question;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link org.example.events.npmg.models.Tag}
 */

@Data
public class TagDto {
    Long id;
    @NotNull
    String name;
    Set<Question> questions;
}