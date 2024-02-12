package org.example.events.npmg.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Question is mandatory!")
    @Column(nullable = false)
    private String question;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "date_published")
    private LocalDateTime datePublished;

    @PrePersist
    protected void onCreate() {
        this.datePublished = LocalDateTime.now();
    }



    // TODO: Add question tags and image urls
}
