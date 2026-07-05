package com.shantanugupta.audittrail.repository;

import com.shantanugupta.audittrail.domain.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findByEntityIdOrderByTimestampDesc(Long entityId);
}