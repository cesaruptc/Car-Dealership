package com.uptc.frw.cardealership.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "VEHICLES")
public class Vehicle {
    @Id
    @Column(name = "VEHICLE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "BRAND")
    private String brand;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "ENGINE_CAPACITY")
    private String engineCapacity ;

    @Column(name = "PRICE")
    private long price;

    @Column(name = "TYPE")
    private String type;

    @OneToMany(mappedBy = "vehicle")
    private List<VehicleOption> vehicleOptions;

    @OneToMany(mappedBy = "vehicle")
    private List<Assignment> assignments;

    @OneToMany(mappedBy = "vehicle")
    private List<Sale> sales;

    public Vehicle() {
    }
    public Vehicle(String brand, String model, String engineCapacity, long price, String type) {
        this.brand = brand;
        this.model = model;
        this.engineCapacity = engineCapacity;
        this.price = price;
        this.type = type;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getEngineCapacity() {
        return engineCapacity;
    }
    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }
    public long getPrice() {
        return price;
    }
    public void setPrice(long price) {
        this.price = price;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public List<VehicleOption> getVehicleOptions() {
        return vehicleOptions;
    }
    public void setVehicleOptions(List<VehicleOption> vehicleOptions) {
        this.vehicleOptions = vehicleOptions;
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

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", engineCapacity='" + engineCapacity + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                '}';
    }
}
