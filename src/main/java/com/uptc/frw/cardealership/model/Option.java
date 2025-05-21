package com.uptc.frw.cardealership.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "OPTIONS")
public class Option {
    @Id
    @Column(name = "OPTION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @JsonIgnore
    @OneToMany(mappedBy = "option")
    private List<VehicleOption> vehicleOptions;

    public Option() {
    }

    public Option(String name, String description) {
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<VehicleOption> getVehicleOptions() {
        return vehicleOptions;
    }

    public void setVehicleOptions(List<VehicleOption> vehicleOptions) {
        this.vehicleOptions = vehicleOptions;
    }

    @Override
    public String toString() {
        return "Option{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


}
