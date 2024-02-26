package org.example.events.npmg.controller;

import com.azure.core.annotation.Post;
import org.example.events.npmg.models.ChatCompletionRequest;
import org.example.events.npmg.models.ChatCompletionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/openai")
public class ChatBotController {

    @Autowired
    RestTemplate restTemplate;
    @PostMapping("/")
    public String getOpenaiResponse(@RequestBody String prompt) {

        ChatCompletionRequest chatCompletionRequest = new ChatCompletionRequest("gpt-3.5-turbo", prompt);
        ChatCompletionResponse response = restTemplate.postForObject("https://api.openai.com/v1/chat/completions",
                chatCompletionRequest,
                ChatCompletionResponse.class);

        return response.getChoices().get(0).getMessage().getContent();

    }
}
