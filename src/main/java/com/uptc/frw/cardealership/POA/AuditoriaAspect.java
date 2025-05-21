package com.uptc.frw.cardealership.POA;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uptc.frw.cardealership.model.AuditLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Aspect
@Component
public class AuditoriaAspect {

    @Autowired
    private AuditoriaRepository auditoriaRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private String userName = System.getProperty("user.name");

    @AfterReturning(value = "execution(* com.uptc.frw.*.repository.*.save(..))", returning = "result")
    public void auditarInsert(JoinPoint joinPoint, Object result) {
        if (result == null) return;

        Map<String, Object> newData = convertToMap(result);
        Object idRegister = newData.getOrDefault("id", null);

        Document documentData = new Document(newData);

        AuditLog auditLog = new AuditLog(
                result.getClass().getSimpleName(),
                idRegister,
                "INSERT",
                userName,
                null,
                documentData
        );

        auditoriaRepository.save(auditLog);
    }

    @After("execution(* com.uptc.frw.*.repository.*.delete(..))")
    public void auditarDelete(JoinPoint joinPoint) {
        Object entity = joinPoint.getArgs()[0];
        if (entity == null) return;
        
        Map<String, Object> oldData = convertToMap(entity);
        Object idRegister = oldData.getOrDefault("id", null);
        
        Document documentData = new Document(oldData);

        AuditLog auditLog = new AuditLog(
                entity.getClass().getSimpleName(),
                idRegister,
                "DELETE",
                userName,
                documentData,
                null
        );

        auditoriaRepository.save(auditLog);
    }
    
    private Map<String, Object> convertToMap(Object obj) {
        return objectMapper.convertValue(obj, new TypeReference<Map<String, Object>>() {});
    }
}