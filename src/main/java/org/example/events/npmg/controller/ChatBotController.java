package org.example.events.npmg.controller;

import lombok.RequiredArgsConstructor;
import org.example.events.npmg.service.OpenAiApi;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/openai")
public class ChatBotController {

    private final OpenAiApi openAiApi;
    @PostMapping("/test")
    public String test(@RequestParam String prompt) {
        return openAiApi.getOpenaiResponse(prompt);
    }
}
