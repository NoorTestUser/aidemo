package com.example.aidemo.service;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.chroma.ChromaEmbeddingStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VectorStoreService {

    private final ChromaEmbeddingStore embeddingStore;
    private final EmbeddingService embeddingService;

    public VectorStoreService(EmbeddingService embeddingService) {
        this.embeddingService = embeddingService;

        this.embeddingStore = ChromaEmbeddingStore.builder()
                .collectionName("policy-docs")
                .build();

        indexDocuments();
    }

    private void indexDocuments() {
        add("Employees are entitled to 24 days of annual leave per year.");
        add("Leave must be approved by the reporting manager.");
        add("Unused leave cannot be carried forward.");
    }

    private void add(String text) {
        Embedding embedding = embeddingService.embed(text);
        embeddingStore.add(text, embedding);
    }

    public List<String> search(String question) {
        Embedding queryEmbedding = embeddingService.embed(question);

        return embeddingStore.findRelevant(queryEmbedding, 3)
                .stream()
                .map(match -> match.embedded().text())
                .toList();
    }
}