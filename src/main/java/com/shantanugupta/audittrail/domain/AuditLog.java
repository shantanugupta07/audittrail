package com.shantanugupta.audittrail.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String entityName; // Will always be "Transaction" for this project

    @Column(nullable = false)
    private Long entityId; // The ID of the transaction that changed

    @Column(nullable = false)
    private String action; // INSERT, UPDATE, DELETE

    @Column(columnDefinition = "TEXT")
    private String payloadDelta; // Capture JSON snapshot of what changed

    @Column(nullable = false)
    private String performedBy; // e.g., "SYSTEM_CRON", "USER_ADMIN"

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public AuditLog() {}

    public AuditLog(String entityName, Long entityId, String action, String payloadDelta, String performedBy) {
        this.entityName = entityName;
        this.entityId = entityId;
        this.action = action;
        this.payloadDelta = payloadDelta;
        this.performedBy = performedBy;
        this.timestamp = LocalDateTime.now();
    }

    // --- Getters and Setters ---
    public Long getId() { return id; }
    public String getEntityName() { return entityName; }
    public Long getEntityId() { return entityId; }
    public String getAction() { return action; }
    public String getPayloadDelta() { return payloadDelta; }
    public String getPerformedBy() { return performedBy; }
    public LocalDateTime getTimestamp() { return timestamp; }
}