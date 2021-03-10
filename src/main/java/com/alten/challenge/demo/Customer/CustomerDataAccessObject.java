package com.alten.challenge.demo.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

@Repository
public class CustomerDataAccessObject {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerDataAccessObject(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

     List<Customer> selectAllCustomers(){
        String sql ="SELECT customer_id, name, address FROM customer";
        return jdbcTemplate.query(sql, getRowMapper());
    }

    private RowMapper<Customer> getRowMapper() {
        return (resultSet, i) -> {
            long customerId = resultSet.getLong("customer_id");
            String customerName = resultSet.getString("name");
            String customerAddress = resultSet.getString("address");
            return new Customer(customerName, customerAddress, customerId);
        };
    }

    @SuppressWarnings("deprecation")
    List<CustomerVehicle> selectAllCustomerVehicles(long id){
        String sql = "SELECT c.name, c.address, c.customer_id, v.vin, v.regnr, v.status FROM customer as c" +
                " JOIN customer_vehicle USING (customer_id)" +
                " JOIN vehicle as v USING (vin)" +
                " WHERE c.customer_id = ?";
        System.out.println(jdbcTemplate.query(
                sql,
                new Object[]{id},
                mapCustomerVehicleFromDb()
        ));
        return jdbcTemplate.query(
                sql,
                new Object[]{id},
                mapCustomerVehicleFromDb()
        );

    }

    private RowMapper<CustomerVehicle> mapCustomerVehicleFromDb(){
        return (resultSet, i) -> {
            long customerId = resultSet.getLong("customer_id");
            return new CustomerVehicle(
                    customerId,
                    UUID.fromString(resultSet.getString("vin")),
                    resultSet.getString("name"),
                    resultSet.getString("address"),
                    resultSet.getString("regnr"),
                    resultSet.getString("status")
            );
        };
    }

    @SuppressWarnings("deprecation")
    List<CustomerVehicle> selectAllCustomerVehiclesStatus(String status){
        String sql = "SELECT c.name, c.address, c.customer_id, v.vin, v.regnr, v.status FROM customer as c" +
                " JOIN customer_vehicle USING (customer_id)" +
                " JOIN vehicle as v USING (vin)" +
                " WHERE v.status = ?";
        System.out.println(jdbcTemplate.query(
                sql,
                new Object[]{status},
                mapCustomerVehicleFromDb()
        ));
        return jdbcTemplate.query(
                sql,
                new Object[]{status},
                mapCustomerVehicleFromDb()
        );

    }

    @SuppressWarnings("deprecation")
    List<CustomerVehicle> selectAllCustomersCVs(){
        String sql = "SELECT c.name, c.address, c.customer_id, v.vin, v.regnr, v.status FROM customer as c" +
                " JOIN customer_vehicle USING (customer_id)" +
                " JOIN vehicle as v USING (vin)";
        System.out.println(jdbcTemplate.query(
                sql,
                mapCustomerVehicleFromDb()
        ));
        return jdbcTemplate.query(
                sql,
                mapCustomerVehicleFromDb()
        );

    }

    @SuppressWarnings("deprecation")
    public CustomerVehicle selectCustomerVehicle(UUID uuid) {
        String sql = "SELECT c.name, c.address, c.customer_id, v.vin, v.regnr, v.status FROM customer as c" +
                " JOIN customer_vehicle USING (customer_id)" +
                " JOIN vehicle as v USING (vin)" +
                " WHERE v.vin = ? ";
        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{uuid},
                (rs, rowNum) ->
                {
                    String vinString = rs.getString("vin");
                    UUID vin = UUID.fromString(vinString);
                    return new CustomerVehicle(
                       rs.getLong("customer_id"),
                       vin,
                       rs.getString("address"),
                       rs.getString("address"),
                       rs.getString("regNr"),
                       rs.getString("status")
                    );
                }
        );
    }

    public void updateCustomerVehicleStatus(String status, UUID uuid) {
        String sql = "UPDATE vehicle SET status = ? WHERE vin = ?";
        jdbcTemplate.update(sql, status, uuid);
        return;
    }

    /*
    public int insertCustomer(Customer customer) {
        long newId =  new Random().nextLong();
        String sql = "INSERT INTO customer (customer_id, name, address) " +
                     "VALUES(?,?,?)";

        return jdbcTemplate.update(
                sql,
                newId,
                customer.getName().toUpperCase(),
                customer.getAddress().toUpperCase()


        );
        return 0;

    }
    */


    /*
    @SuppressWarnings({"ConstantConditions", "deprecation"})
    boolean isNameTaken(String name) {
        String sql= "SELECT IF EXISTS ( " +
                " SELECT 1 FROM consumer WHERE name = ? ) ";
        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{name},
                (resultSet, i) -> resultSet.getBoolean(1)
        );
    }
    */

}
