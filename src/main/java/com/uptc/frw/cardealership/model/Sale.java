package com.uptc.frw.cardealership.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "SALES")
public class Sale {
    @Id
    @Column(name = "SALE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "CLIENT_ID", insertable = false, updatable = false)
    private long clientId;
    @Column(name = "SELLER_ID", insertable = false, updatable = false)
    private long sellerId;
    @Column(name = "NEW_VEHICLE_ID", insertable = false, updatable = false)
    private long vehicleId;
    @Column(name = "NEW_LICENSE_PLATE")
    private String newLicense;
    @Column(name = "SALE_DATE")
    private Date saleDate;
    @Column(name = "ASSIGNMENT_ID", insertable = false, updatable = false)
    private long assignmentId;
    @Column(name = "FINAL_PRICE")
    private long price;

    @ManyToOne()
    @JoinColumn(name = "CLIENT_ID",nullable = false)
    private Person client;

    @ManyToOne()
    @JoinColumn(name = "SELLER_ID",nullable = false)
    private Person seller;

    @ManyToOne()
    @JoinColumn(name = "ASSIGNMENT_ID",nullable = true)
    private Assignment assignment;

    @ManyToOne()
    @JoinColumn(name = "NEW_VEHICLE_ID",nullable = false)
    private Vehicle vehicle;
    @JsonIgnore
    @OneToMany(mappedBy = "sales")
    private List<SalesOption> salesOptions;


    public Sale() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getNewLicense() {
        return newLicense;
    }

    public void setNewLicense(String newLicense) {
        this.newLicense = newLicense;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public long getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(long assignmentId) {
        this.assignmentId = assignmentId;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Person getClient() {
        return client;
    }

    public void setClient(Person client) {
        this.client = client;
    }

    public Person getSeller() {
        return seller;
    }

    public void setSeller(Person seller) {
        this.seller = seller;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public List<SalesOption> getSalesOptions() {
        return salesOptions;
    }

    public void setSalesOptions(List<SalesOption> salesOptions) {
        this.salesOptions = salesOptions;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", sellerId=" + sellerId +
                ", vehicleId=" + vehicleId +
                ", newLicense='" + newLicense + '\'' +
                ", saleDate=" + saleDate +
                ", price=" + price +
                '}';
    }
}
