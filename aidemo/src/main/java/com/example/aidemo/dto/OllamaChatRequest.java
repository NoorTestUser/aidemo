package com.example.aidemo.dto;

import com.example.aidemo.model.Message;

import java.util.List;


public class OllamaChatRequest {
    private String model;
    private List<Message> messages;
    private boolean stream = false;

    public OllamaChatRequest(String model, List<Message> messages) {
        this.model = model;
        this.messages = messages;
    }
}
