package com.example.aidemo.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiService {
    private final ChatClient chatClient;

    public AiService(ChatClient chatClient){
        this.chatClient = chatClient;
    }

    public String ask(String question){
        return chatClient
                .prompt(question)
                .call()
                .content();
    }
}
