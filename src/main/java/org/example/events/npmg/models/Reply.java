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
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "replies")


public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @NotEmpty(message = "Reply to the question is manditory")
    @Column(nullable = false)
    private String text;

    @ManyToOne(optional = false)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "parent_id", nullable = false)
    private Reply parent;

    @Column(name = "time_published")
    private LocalDateTime dateOfCreation;

    @PrePersist
    protected void onCreate() {
        dateOfCreation = LocalDateTime.now();
    }
}
