package com.uptc.frw.cardealership.POA;

import com.uptc.frw.cardealership.model.AuditLog;
import com.uptc.frw.cardealership.model.Auditoria;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Aspect
@Component
public class AuditoriaAspect {

    @Autowired
    private AuditoriaRepository auditoriaRepository;
    private String userName = System.getProperty("user.name");

    @After("execution(* com.uptc.frw.*.repository.*.save(..))")
    public void auditarInsert(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            Object entity = args[0];

            Document newData = new Document();
            Object idRegister = null;

            for (Field field : entity.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    Object value = field.get(entity);
                    newData.append(field.getName(), value);
                    if (field.getName().equalsIgnoreCase("id")) {
                        idRegister = value;
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }

            AuditLog auditLog = new AuditLog(
                    entity.getClass().getSimpleName(),
                    idRegister,
                    "INSERT",
                    userName,
                    null,
                    newData
            );

            auditoriaRepository.save(auditLog);
//            AuditLog auditoria = new AuditLog(entity.getClass().getSimpleName(),entity[1]);
        }
    }


}
