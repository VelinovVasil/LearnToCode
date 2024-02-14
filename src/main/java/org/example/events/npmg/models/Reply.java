package org.example.events.npmg.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.events.npmg.models.Role.Role;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="replies")


public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    //change this to 'author'
    private User user;

    @NotEmpty(message = "Reply to the question is manditory")
    @Column(nullable = false)
    //change this to 'text'
    private String reply;


    @Column(name = "time_published")
    private LocalDateTime dateOfCreation;


    @ManyToOne(optional = false)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "reply_to_reply_id", nullable = false)
    private Reply replyToReply;

    @PrePersist
    protected void onCreate() {
        dateOfCreation = LocalDateTime.now();
    }
}
