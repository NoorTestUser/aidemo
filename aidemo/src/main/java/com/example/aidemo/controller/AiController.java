package com.example.aidemo.controller;

import com.example.aidemo.service.AIService1;
import com.example.aidemo.service.AiService;
import com.example.aidemo.service.DocumentStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ai")
public class AiController {
    private final AiService aiService;
    private final AIService1 aiService1;
    private final DocumentStore documentStore;

    public AiController(AiService aiService, AIService1 aiService1, DocumentStore documentStore) {
        this.aiService = aiService;
        this.aiService1 = aiService1;
        this.documentStore = documentStore;
    }

    @GetMapping("/ask")
    public String ask(@RequestParam String question) {
        return aiService.ask(question);
    }


    @GetMapping("/askrInlineRAG")
    public String askrInlineRAG(@RequestParam String question) {
        String context = documentStore.getPolicyText();
        return aiService.askInHouseRAG(context, question);
    }


//    @GetMapping("/ai/askRAG")
//    public String askRAG(@RequestParam String question) {
//
//        List<String> contextChunks = vectorStoreService.search(question);
//
//        if (contextChunks.isEmpty()) {
//            return "Information is not available in the uploaded documents.";
//        }
//
//        return aiService1.askWithContext(
//                String.join("\n", contextChunks),
//                question
//        );
//    }
}

