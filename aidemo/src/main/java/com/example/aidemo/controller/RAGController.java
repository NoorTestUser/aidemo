package com.example.aidemo.controller;

import com.example.aidemo.service.RAGService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rag")
public class RAGController {

    private final RAGService ragService;

    public RAGController(RAGService ragService) {
        this.ragService = ragService;
    }

    // ✅ Add knowledge
    @PostMapping("/add")
    public String add(@RequestParam String content) {
        ragService.addDocument(content);
        return "Added successfully ✅";
    }

    // ✅ Ask question
    @GetMapping("/ask")
    public String ask(@RequestParam String question) {
        return ragService.ask(question);
    }
}