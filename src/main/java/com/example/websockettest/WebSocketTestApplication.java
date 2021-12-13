package com.example.websockettest;

import com.example.websockettest.entity.Customer;
import com.example.websockettest.entity.CustomerRepository;
import com.example.websockettest.repos.CharacterRepository;
import com.example.websockettest.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebSocketTestApplication implements CommandLineRunner {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private CharacterRepository charRepo;

    @Autowired
    private LoginService loginService;

    public static void main(String[] args) {
        SpringApplication.run(WebSocketTestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        repository.deleteAll();

        // save a couple of customers
        repository.save(new Customer("Alice", "Smith"));
        repository.save(new Customer("Bob", "Smith"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : repository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByFirstName("Alice"));

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (Customer customer : repository.findByLastName("Smith")) {
            System.out.println(customer);
        }

        System.out.println("Rollo character:");
        System.out.println(charRepo.findByUsername("rlongo").getHitpoint().getAdj());
        System.out.println(charRepo.findByUsername("rlongo").getWeapons().get(0).getName());

        System.out.println(loginService.authenticate("rlongo", "blah"));
    }

}
