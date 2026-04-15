package com.example.aidemo.service;

import com.example.aidemo.dto.OllamaChatRequest;
import com.example.aidemo.dto.dto.OllamaChatResponse;
import com.example.aidemo.model.Message;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class AiService {

    @Value("${spring.ai.ollama.base-url}")
    private String OLLAMA_URL;

    private final ChatClient chatClient;


    private static final String MODEL = "phi3";

    private final RestTemplate restTemplate = new RestTemplate();
    private final List<Message> conversation = new ArrayList<>();


    public AiService(ChatClient chatClient){
        this.chatClient = chatClient;
    }

    public String ask(String question){
        return chatClient
                .prompt(question)
                .call()
                .content();
    }


    public synchronized String chat(String userInput) {

        // Add user message
        conversation.add(new Message("user", userInput));

        // Build request
        OllamaChatRequest request =
                new OllamaChatRequest(MODEL, conversation);

        // Call Ollama
        OllamaChatResponse response =
                restTemplate.postForObject(
                        OLLAMA_URL,
                        request,
                        OllamaChatResponse.class
                );

        String aiReply = response.getMessage().getContent();

        // Store assistant reply
        conversation.add(new Message("assistant", aiReply));

        return aiReply;
    }

}
