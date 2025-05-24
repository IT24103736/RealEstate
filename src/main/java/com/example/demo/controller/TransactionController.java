package com.example.demo.controller;

import com.example.demo.model.Property;
import com.example.demo.model.Transaction;
import com.example.demo.model.User;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @PostMapping("/simulate")
    public String simulateTransaction() {
        // Sample Users
//        User buyer = new User("U001", "John Smith", "john@gmail.com");
//        User seller = new User("U002", "Allen White", "allen@gmail.com");
//
//        // Sample Property
//        Property property = new Property("P001", "123 Main St", 150000, true);
//
//        // Create a transaction
//        service.createTransaction("T001", property, buyer, seller, 150000);
//
//        // Complete it
//        service.completeTransaction("T001");

        return "Transaction simulation completed. Total sales: $" + service.calculateTotalSales();
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return service.getAllTransactions();
    }

    @GetMapping("/total-sales")
    public double getTotalSales() {
        return service.calculateTotalSales();
    }

    @PostMapping("/complete/{id}")
    public boolean completeTransaction(@PathVariable String id) {
        return service.completeTransaction(id);
    }
}