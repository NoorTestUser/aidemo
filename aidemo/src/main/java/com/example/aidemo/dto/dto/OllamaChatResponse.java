package com.example.aidemo.dto.dto;

public class OllamaChatResponse {
    private OllamaMessage message;

    public OllamaMessage getMessage() {
        return message;
    }

    public static class OllamaMessage {
        private String role;
        private String content;

        public String getContent() {
            return content;
        }
    }
}