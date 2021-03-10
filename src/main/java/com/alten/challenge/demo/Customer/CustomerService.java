package com.alten.challenge.demo.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerDataAccessObject customerDataAccessObject;

    @Autowired
    public CustomerService(CustomerDataAccessObject customerDataAccessObject) {
        this.customerDataAccessObject = customerDataAccessObject;
    }

    public List<Customer> getAllCustomers(){
        return customerDataAccessObject.selectAllCustomers();
    }

    List<CustomerVehicle> getAllCustomersCVs(){
        return customerDataAccessObject.selectAllCustomersCVs();
    }

    List<CustomerVehicle> getAllCustomerVehicles(long id){
        return customerDataAccessObject.selectAllCustomerVehicles(id);
    }

    List<CustomerVehicle> getAllCustomerVehiclesStatus(String status){
        return customerDataAccessObject.selectAllCustomerVehiclesStatus(status);
    }

    public CustomerVehicle getCustomerVehicle(UUID uuid) {
        return customerDataAccessObject.selectCustomerVehicle(uuid);
    }

    public void updateCustomerVehicleStatus(String status, UUID uuid) {
        customerDataAccessObject.updateCustomerVehicleStatus(status, uuid);
        return;
    }

    /*
    public void addNewCustomer(Customer customer) throws Exception {
        customerDataAccessObject.insertCustomer(customer);
    */
        /*
        if(customerDataAccessObject.isNameTaken(customer.getName())){
            throw new Exception("Name Already Taken");
        }

         */


    }


