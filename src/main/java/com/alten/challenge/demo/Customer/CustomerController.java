package com.alten.challenge.demo.Customer;

import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("api")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("customers")
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("customervehicles")
    public List<CustomerVehicle> getAllCVs(){
        return customerService.getAllCustomersCVs();
    }



    @GetMapping("/customer/vehicles/{id}")
    public List<CustomerVehicle> getAllCustomerVehicles(@PathVariable("id") long id){
        System.out.println(id);
        return customerService.getAllCustomerVehicles(id);
    }

    @GetMapping("vehicles/{uuid}")
    public CustomerVehicle getCustomerVehicle(@PathVariable("uuid") UUID uuid){
        System.out.println(uuid);
        return customerService.getCustomerVehicle(uuid);
    }

    @GetMapping("vehicles/status/{status}")
    public List<CustomerVehicle> getAllCustomerVehiclesStatus(@PathVariable("status") String status){
        System.out.println(status);
        return customerService.getAllCustomerVehiclesStatus(status);
    }

    /*
    @PostMapping
    public void addNewCustomer(@RequestBody @Valid Customer customer) throws Exception {
        System.out.println(customer);
        customerService.addNewCustomer(customer);
    }
    */

    @PostMapping("/vehicles/{uuid}")
    public void changeVehicleStatus(@RequestBody String status, @PathVariable String uuid){
        CustomerVehicle customerVehicle = customerService.getCustomerVehicle(UUID.fromString(uuid));

        if(!(Objects.isNull(customerVehicle))) {
            JSONObject jsonObject = new JSONObject(status);
            System.out.println(jsonObject);
            String oStatus = jsonObject.getString("status");
            System.out.println(oStatus);
            customerService.updateCustomerVehicleStatus(oStatus, UUID.fromString(uuid));

        }
    }
}
