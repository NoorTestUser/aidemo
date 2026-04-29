package com.example.aidemo.controller;

import com.example.aidemo.service.AiService;
import com.example.aidemo.service.DocumentStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
public class AiController {
    private final AiService aiService;
    private final DocumentStore documentStore;

    public AiController(AiService aiService, DocumentStore documentStore) {
        this.aiService = aiService;
        this.documentStore = documentStore;
    }

    @GetMapping("/ask")
    public String ask(@RequestParam String question){
        return aiService.ask(question);
    }


    @GetMapping("/askrInlineRAG")
    public String askrInlineRAG(@RequestParam String question) {
        String context = documentStore.getPolicyText();
        return aiService.askInHouseRAG(context, question);
    }


    @GetMapping("/chat")
    public String chat(@RequestParam String question){
        return aiService.chat(question);
    }
}
