package com.alten.challenge.demo;

import com.alten.challenge.demo.Customer.CustomerController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class APITest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void jsonDataGet() throws Exception{
        assertThat(this.testRestTemplate.getForObject("http://localhost:8080/" +
                "api/customers/customervehicles", String.class)).contains("Browseblab");

        assertThat(this.testRestTemplate.getForObject("http://localhost:8080/" +
                "api/customers", String.class)).contains("Skipstorm");
    }



}
