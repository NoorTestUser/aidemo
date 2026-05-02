package com.example.aidemo.service;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.ollama.OllamaEmbeddingModel;
import dev.langchain4j.model.output.Response;
import org.springframework.stereotype.Service;

@Service
public class EmbeddingService {

    private final EmbeddingModel embeddingModel;

    public EmbeddingService() {
        this.embeddingModel = OllamaEmbeddingModel.builder()
                .modelName("tinyllama")
                .build();
    }

    public Embedding embed(String text) {
        return embeddingModel.embed(text).content();
    }
}
