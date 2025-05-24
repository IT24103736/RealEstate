package com.example.demo.service;

import com.example.demo.model.Property;
import com.example.demo.model.Transaction;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    private List<Transaction> transactions = new ArrayList<>();

    public Transaction createTransaction(String id, Property property, User buyer, User seller, double amount) {
        Transaction transaction = new Transaction(id, property, buyer, seller, amount);
        transactions.add(transaction);
        return transaction;
    }

    public Transaction getTransaction(String id) {
        return transactions.stream()
                .filter(t -> t.getTransactionID().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Transaction> getAllTransactions() {
        return new ArrayList<>(transactions);
    }

    public boolean completeTransaction(String id) {
        Transaction t = getTransaction(id);
        if (t != null && t.isActive()) {
            t.completeTransaction();
            return true;
        }
        return false;
    }

    public double calculateTotalSales() {
        return transactions.stream()
                .filter(Transaction::isCompleted)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }
}