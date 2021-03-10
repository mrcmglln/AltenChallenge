package com.alten.challenge.demo.Customer;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class CustomerVehicle {
    private final long id;
    private final UUID vin;
    private final String name;
    private final String address;
    private final String regNr;
    private String status;

    public CustomerVehicle(@JsonProperty("id") long id, @JsonProperty("vin") UUID vin, @JsonProperty("name") String name, @JsonProperty("address") String address, @JsonProperty("regNr") String regNr, @JsonProperty("status") String status) {
        this.id = id;
        this.vin = vin;
        this.name = name;
        this.address = address;
        this.regNr = regNr;
        this.status = status;
    }

    public long id() {
        return id;
    }

    public UUID getVin() {
        return vin;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getRegNr() {
        return regNr;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
