package com.uptc.frw.cardealership.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "PERSONS")
public class Person {
    @Id
    @Column(name = "PERSON_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "IDENTIFICATION")
    private String identification;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PHONE")
    private long phone;

    @JsonIgnore
    @OneToMany(mappedBy = "person")
    private List<Assignment> assignments;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Sale> sales;

    @JsonIgnore
    @OneToMany(mappedBy = "seller")
    private List<Sale> salesSeller;

    public Person() {
    }
    public Person(String name, String identification, long phone) {
        this.name = name;
        this.identification = identification;
        this.phone = phone;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIdentification() {
        return identification;
    }
    public void setIdentification(String identification) {
        this.identification = identification;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public long getPhone() {
        return phone;
    }
    public void setPhone(long phone) {
        this.phone = phone;
    }
    public List<Assignment> getAssignments() {
        return assignments;
    }
    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    public List<Sale> getSalesSeller() {
        return salesSeller;
    }

    public void setSalesSeller(List<Sale> salesSeller) {
        this.salesSeller = salesSeller;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", identification='" + identification + '\'' +
                ", address='" + address + '\'' +
                ", phone=" + phone +
                '}';
    }
}

