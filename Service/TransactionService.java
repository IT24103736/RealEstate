package Service;

import Model.Transaction;
import Model.Property;
import Model.User;

import java.util.ArrayList;
import java.util.List;

public class TransactionService {
    private List<Transaction> transactions = new ArrayList<>();

    // Create
    public Transaction createTransaction(String id, Property property, User buyer, User seller, double amount) {
        Transaction transaction = new Transaction(id, property, buyer, seller, amount);
        transactions.add(transaction);
        return transaction;
    }

    // Read
    public Transaction getTransaction(String id) {
        for (Transaction t : transactions) {
            if (t.getTransactionID().equals(id)) {
                return t;
            }
        }
        return null;
    }

    public List<Transaction> getAllTransactions() {
        return new ArrayList<>(transactions);
    }

    public List<Transaction> getActiveTransactions() {
        List<Transaction> active = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.isActive()) {
                active.add(t);
            }
        }
        return active;
    }

    // Update
    public boolean completeTransaction(String id) {
        Transaction t = getTransaction(id);
        if (t != null && t.isActive()) {
            t.completeTransaction();
            return true;
        }
        return false;
    }

    // Delete (Soft delete / cancel)
    public boolean cancelTransaction(String id) {
        Transaction t = getTransaction(id);
        if (t != null && t.isActive()) {
            t.cancelTransaction();
            return true;
        }
        return false;
    }

    // Business logic
    public double calculateTotalSales() {
        double total = 0;
        for (Transaction t : transactions) {
            if (t.isCompleted()) {
                total += t.getAmount();
            }
        }
        return total;
    }
}
