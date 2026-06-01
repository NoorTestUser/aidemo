package com.example.aidemo.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RAGService {

    private final VectorStore vectorStore;
    private final ChatClient chatClient;

    public RAGService(VectorStore vectorStore, ChatClient chatClient) {
        this.vectorStore = vectorStore;
        this.chatClient = chatClient;
    }

    // ✅ Step 1: Add data into vector store
    public void addDocument(String content) {
        Document doc = new Document(content);
        vectorStore.add(List.of(doc));
    }

    // ✅ Step 2: Ask question
    public String ask(String question) {

        // Retrieve similar documents
        List<Document> docs = vectorStore.similaritySearch(question);

        StringBuilder context = new StringBuilder();
        for (Document d : docs) {
            context.append(d.getFormattedContent()).append("\n");
        }

        // fallback
        if (context.isEmpty()) {
            return "Information is not available in the uploaded documents.";
        }

        // ✅ Prompt
        String prompt = """
            You are a strict policy-bound assistant.

            RULES:
            - Answer ONLY using the context
            - Do NOT guess
            - If not found, say:
              Information is not available in the uploaded documents.

            Context:
            %s

            Question:
            %s

            Answer:
            """.formatted(context, question);

        return chatClient.prompt(prompt)
                .call()
                .content();
    }
}