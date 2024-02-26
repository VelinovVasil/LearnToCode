package org.example.events.npmg.models;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Entity
//@Table(name="chatMessages")
public class ChatMessage {

    private String role;
    private String content;




}
