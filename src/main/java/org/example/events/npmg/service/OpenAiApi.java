package org.example.events.npmg.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OpenAiApi {

    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    @Value("${openai.key}")
    private String token;

    @SneakyThrows
    public String getOpenaiResponse(String prompt) {

        Map<String, Object> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", prompt);

        Map<String, Object> body = new HashMap<>();
        body.put("model", "gpt-3.5-turbo");
        body.put("messages", Collections.singletonList(message));

        String jsonPrompt;
        try {
            jsonPrompt = objectMapper.writeValueAsString(body);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize prompt to JSON", e);
        }

        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .method("POST", RequestBody.create(MediaType.parse("application/json"), jsonPrompt))
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + token)
                .build();

        Response response = client.newCall(request).execute();

        return response.body().string();
    }


}
