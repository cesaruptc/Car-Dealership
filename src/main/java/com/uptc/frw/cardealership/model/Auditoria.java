package com.uptc.frw.cardealership.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "auditoria")
public class Auditoria {

    @Id
    private String id;

    private String tabla;
    private String accion;
    private String datos;
    private LocalDateTime fecha;
    private String usuario;

    public Auditoria(String tabla, String accion, String datos) {
        this.tabla = tabla;
        this.accion = accion;
        this.datos = datos;
        this.fecha = LocalDateTime.now();
        this.usuario = System.getProperty("user.name");
    }

    public Auditoria() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
