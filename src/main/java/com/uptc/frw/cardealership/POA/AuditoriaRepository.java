package com.uptc.frw.cardealership.POA;

import com.uptc.frw.cardealership.model.AuditLog;
import com.uptc.frw.cardealership.model.Auditoria;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuditoriaRepository extends MongoRepository<AuditLog, String> {
}
