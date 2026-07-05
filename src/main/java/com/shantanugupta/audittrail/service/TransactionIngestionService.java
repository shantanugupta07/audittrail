package com.shantanugupta.audittrail.service;

import com.shantanugupta.audittrail.domain.Transaction;
import com.shantanugupta.audittrail.dto.TransactionUploadRequest;
import com.shantanugupta.audittrail.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionIngestionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionIngestionService.class);
    private final TransactionRepository transactionRepository;

    public TransactionIngestionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Async // 👈 Offloads this method execution execution to a background thread pool
    @Transactional // Enforces transaction integrity limits per batch
    public void processBatchIngestion(List<TransactionUploadRequest> requests) {
        logger.info("Starting background ingestion pipeline for {} records", requests.size());

        for (TransactionUploadRequest item : requests) {
            try {
                // Check if reference code already exists to protect data integrity
                if (transactionRepository.findByTransactionReference(item.getTransactionReference()).isPresent()) {
                    logger.warn("Skipping record: Reference {} already exists", item.getTransactionReference());
                    continue;
                }

                Transaction txn = new Transaction(
                        item.getTransactionReference(),
                        item.getAccountId(),
                        item.getAmount(),
                        item.getCurrency(),
                        "COMPLETED" // Defaults state to processed directly
                );

                transactionRepository.save(txn);
                logger.info("Successfully ingested transaction: {}", txn.getTransactionReference());

            } catch (Exception ex) {
                logger.error("Failed processing record reference: {}. Reason: {}",
                        item.getTransactionReference(), ex.getMessage());
            }
        }
        logger.info("Completed background batch processing execution stream.");
    }
}