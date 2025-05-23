package com.uptc.frw.cardealership.POA;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uptc.frw.cardealership.component.DynamicEntityFetcher;
import com.uptc.frw.cardealership.model.AuditLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Aspect
public class AuditoriaAspect {

    @Autowired
    private AuditoriaRepository auditoriaRepository;

    @Autowired
    private DynamicEntityFetcher dynamicEntityFetcher;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private String userName = System.getProperty("user.name");

    private final ThreadLocal<Map<String, Object>> olDataThreadLocal = new ThreadLocal<>();
    private final ThreadLocal<Object> idRegisterThreadLocal = new ThreadLocal<>();
    private final ThreadLocal<String> tableNameThreadLocal = new ThreadLocal<>();

    @AfterReturning(value = "execution(* com.uptc.frw..service..*.save*(..))", returning = "result")
    public void auditarInsert(Object result) {
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

    @Before("execution(* com.uptc.frw.*.service.*.update*(..))")
    public void capOldData(JoinPoint joinPoint) {
        Object entity = joinPoint.getArgs()[0];
        if (entity == null) return;

        Map<String, Object> newData = convertToMap(entity);
        Object id = newData.get("id");

        Object oldEntity = dynamicEntityFetcher.searchById(entity.getClass(), id);
        if (oldEntity == null) return;

        Map<String, Object> oldData = convertToMap(oldEntity);

        olDataThreadLocal.set(oldData);
        idRegisterThreadLocal.set(id);
        tableNameThreadLocal.set(entity.getClass().getSimpleName());
    }

    @AfterReturning(value = "execution(* com.uptc.frw.*.service.*.update*(..))", returning = "result")
    public void auditarUpdate(Object result) {
        if (result == null) return;

        Map<String, Object> newData = convertToMap(result);

        AuditLog auditLog = new AuditLog(
                tableNameThreadLocal.get(),
                idRegisterThreadLocal.get(),
                "UPDATE",
                userName,
                new Document(olDataThreadLocal.get()),
                new Document(newData)
        );

        auditoriaRepository.save(auditLog);

        olDataThreadLocal.remove();
        idRegisterThreadLocal.remove();
        tableNameThreadLocal.remove();
    }

    private Map<String, Object> convertToMap(Object obj) {
        return objectMapper.convertValue(obj, new TypeReference<Map<String, Object>>() {});
    }
}