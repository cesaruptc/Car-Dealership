package com.uptc.frw.cardealership.model;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "VEHICLE_OPTIONS")
public class VehicleOption {
    @Id
    @Column(name = "VEHICLE_OPTION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "VEHICLE_ID",insertable = false,updatable = false)
    private long vehicleId;

    @Column(name = "OPTION_ID",insertable = false,updatable = false)
    private long optionId;

    @Column(name = "PRICE")
    private long price;

    @ManyToOne
    @JoinColumn(name = "OPTION_ID",nullable = false)
    private Option option;

    @ManyToOne
    @JoinColumn(name = "VEHICLE_ID",nullable = false)
    private Vehicle vehicle;

    @OneToMany(mappedBy = "vehicleOption" )
    private List<SalesOption> salesOptions;

    public VehicleOption() {
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
    public long getOptionId() {
        return optionId;
    }
    public void setOptionId(long optionId) {
        this.optionId = optionId;
    }
    public long getPrice() {
        return price;
    }
    public void setPrice(long price) {
        this.price = price;
    }

    public Option getOption() {
        return option;
    }
    public void setOption(Option option) {
        this.option = option;
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
        return "VehicleOption{" +
                "id=" + id +
                ", vehicleId=" + vehicleId +
                ", optionId=" + optionId +
                ", price=" + price +
                '}';
    }

}
