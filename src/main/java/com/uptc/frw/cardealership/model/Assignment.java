package com.uptc.frw.cardealership.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ASSIGNMENTS")
public class Assignment {

    @Id
    @Column(name = "ASSIGNMENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "VEHICLE_ID",insertable = false,updatable = false)
    private long vehicleId;

    @Column(name = "PERSON_ID",insertable = false,updatable = false)
    private long personId;

    @Column(name = "ASSIGNMENT_DATE")
    private Date date;

    @Column(name = "APPRAISAL_PRICE")
    private long priceAppraisal;

    @ManyToOne()
    @JoinColumn(name = "VEHICLE_ID",nullable = false)
    private Vehicle vehicle;
    @ManyToOne()
    @JoinColumn(name = "PERSON_ID",nullable = false)
    private Person person;

    @OneToMany(mappedBy = "assignment")
    private List<Sale> sales;

    public Assignment() {
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public long getPriceAppraisal() {
        return priceAppraisal;
    }
    public void setPriceAppraisal(long priceAppraisal) {
        this.priceAppraisal = priceAppraisal;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "id=" + id +
                ", vehicleId=" + vehicleId +
                ", personId=" + personId +
                ", date=" + date +
                ", priceAppraisal=" + priceAppraisal +
                '}';
    }
}
