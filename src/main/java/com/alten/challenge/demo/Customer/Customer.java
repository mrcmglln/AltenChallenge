package com.alten.challenge.demo.Customer;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;


public class Customer {

    @NotBlank
    private final String name;

    @NotBlank
    private final String address;

    private final long id;

    //public Customer() {}

    public Customer(@JsonProperty("name") String name, @JsonProperty("address") String address, @JsonProperty("customerId") long id) {
        this.name = name;
        this.address = address;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public long getId() {
        return id;
    }

    /*
    public void setName(String name){
        this.name = name;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setId(long id){
        this.id = id;
    }
    */

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", id=" + id +
                '}';
    }
}
