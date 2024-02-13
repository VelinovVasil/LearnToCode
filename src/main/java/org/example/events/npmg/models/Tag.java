package org.example.events.npmg.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "categories")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull
    private String name;

    @ManyToMany
    @JoinTable(name = "categories_questions",
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "questions_id"))
    private Set<Question> questions = new LinkedHashSet<>();

}
