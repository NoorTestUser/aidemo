package com.example.aidemo.service;

import org.springframework.stereotype.Service;

@Service
public class DocumentStore {

    public String getPolicyText() {
        return """
        Employees are entitled to 24 days of annual leave per year.
        Leave must be approved by the reporting manager.
        Unused leave cannot be carried forward.
        """;
    }
}