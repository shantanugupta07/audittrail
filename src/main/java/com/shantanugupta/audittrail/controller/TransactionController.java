package com.shantanugupta.audittrail.controller;


import com.shantanugupta.audittrail.dto.TransactionUploadRequest;
import com.shantanugupta.audittrail.service.TransactionIngestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final TransactionIngestionService ingestionService;

    public TransactionController(TransactionIngestionService ingestionService) {
        this.ingestionService = ingestionService;
    }

    @PostMapping("/batch")
    public ResponseEntity<Map<String, String>> uploadBatch(@RequestBody List<TransactionUploadRequest> payload) {
        // Trigger the asynchronous process in the background
        ingestionService.processBatchIngestion(payload);

        // Instantly return an HTTP 202 Accepted response code without waiting
        return ResponseEntity.accepted().body(Map.of(
                "status", "ACCEPTED",
                "message", "Batch processing triggered asynchronously in background threads."
        ));
    }
}