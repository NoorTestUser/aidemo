package com.example.aidemo.service;


import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;

@Service
public class AIService1 {

    private final ChatLanguageModel chatModel;

    public AIService1() {
        this.chatModel = OllamaChatModel.builder()
                .modelName("tinyllama")
                .temperature(0.0)
                .build();
    }


    public String askWithContext(String context, String question) {

        String prompt = """
        You are a policy-bound assistant.

        RULES (follow strictly):
        - Answer ONLY using the provided context.
        - Do NOT use general knowledge.
        - Do NOT guess or infer.
        - If the answer is not explicitly present, reply EXACTLY:
          Information is not available in the uploaded documents.

        CONTEXT:
        %s

        QUESTION:
        %s

        ANSWER:
        """.formatted(context, question);

        return chatModel.generate(prompt);
    }
}

