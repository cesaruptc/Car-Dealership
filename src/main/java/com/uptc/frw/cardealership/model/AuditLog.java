package com.uptc.frw.cardealership.model;

import org.bson.Document;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class AuditLog {
    private String idAuditoria;
    private String tableName;
    private Object idRegister;
    private String action;
    private LocalDateTime date;
    private String userName;
    private Document oldData;
    private Document newData;

    public AuditLog() {
    }

    public AuditLog(String tableName, Object idRegister, String action, String userName, Document oldData, Document newData) {
        this.idAuditoria = UUID.randomUUID().toString();
        this.tableName = tableName;
        this.idRegister = idRegister;
        this.date = LocalDateTime.now();
        this.action = action;
        this.userName = userName;
        this.oldData = oldData;
        this.newData = newData;
    }

    public Document toDocument() {
        return new Document()
                .append("id_auditoria", idAuditoria)
                .append("table_name", tableName)
                .append("id_register", idRegister)
                .append("action", action)
                .append("date", date.toString())
                .append("user_name", userName)
                .append("old_data", oldData)
                .append("new_data", newData);
    }

    public String getIdAuditoria() {
        return idAuditoria;
    }

    public void setIdAuditoria(String idAuditoria) {
        this.idAuditoria = idAuditoria;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Object getIdRegister() {
        return idRegister;
    }

    public void setIdRegister(Object idRegister) {
        this.idRegister = idRegister;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Document getOldData() {
        return oldData;
    }

    public void setOldData(Document oldData) {
        this.oldData = oldData;
    }

    public Document getNewData() {
        return newData;
    }

    public void setNewData(Document newData) {
        this.newData = newData;
    }
}
