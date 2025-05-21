package com.uptc.frw.cardealership.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "SALES_OPTIONS")
public class SalesOption {

    @Id
    @Column(name = "SALE_OPTION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "SALE_ID",insertable = false,updatable = false)
    private long saleId;
    @Column(name = "VEHICLE_OPTION_ID",insertable = false,updatable = false)
    private long vehicleOptionId;

    @ManyToOne()
    @JoinColumn(name = "SALE_ID",nullable = false)
    private Sale sales;
    @ManyToOne()
    @JoinColumn(name = "VEHICLE_OPTION_ID",nullable = false)
    private VehicleOption vehicleOption;

    public SalesOption() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSaleId() {
        return saleId;
    }

    public void setSaleId(long saleId) {
        this.saleId = saleId;
    }

    public long getVehicleOptionId() {
        return vehicleOptionId;
    }

    public void setVehicleOptionId(long vehicleOptionId) {
        this.vehicleOptionId = vehicleOptionId;
    }

    public Sale getSales() {
        return sales;
    }

    public void setSales(Sale sales) {
        this.sales = sales;
    }

    public VehicleOption getVehicleOption() {
        return vehicleOption;
    }

    public void setVehicleOption(VehicleOption vehicleOption) {
        this.vehicleOption = vehicleOption;
    }

    @Override
    public String toString() {
        return "SalesOption{" +
                "id=" + id +
                ", saleId=" + saleId +
                ", vehicleOptionId=" + vehicleOptionId +
                '}';
    }
}
