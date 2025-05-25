package Model;

import java.time.LocalDateTime;

public class Transaction {
    private String transactionID;
    private Property property;
    private User buyer;
    private User seller;
    private LocalDateTime date;
    private double amount;
    private boolean isCompleted;
    private boolean isCancelled;

    //Constructors
    public Transaction(String transactionID, Property property, User buyer, User seller, double amount) {
        this.transactionID = transactionID;
        this.property = property;
        this.buyer = buyer;
        this.seller = seller;
        this.amount = amount;
        this.date = LocalDateTime.now();
        this.isCompleted = false;
        this.isCancelled = false;
    }


    //Getters
    public String getTransactionID() {
        return transactionID;
    }
    public Property getProperty() {
        return property;
    }
    public User getBuyer() {
        return buyer;
    }
    public User getSeller() {
        return seller;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public double getAmount() {
        return amount;
    }


    public boolean isCompleted() {
        return isCompleted;
    }
    public boolean isCancelled() {
        return isCancelled;
    }
    public boolean isActive() {
        return !isCompleted && !isCancelled;
    }


    //Setters
    public void setTransactionID(String transactionID) {
        if(transactionID == null) {
            System.out.println("Transaction ID cannot be null.");
        }
        this.transactionID = transactionID;
    }

    public void setProperty(Property property) {
        if (property == null) {
            System.out.println("Property cannot be null");
        }
        this.property = property;
    }

    public void setAmount(double amount) {
        if (amount <= 0) {
            System.out.println("Amount must be positive");
        }
        this.amount = amount;
    }

    //Completion method
    public void completeTransaction() {
        if (isActive()) {
            this.isCompleted = true;
        }
    }

    //Cancellation method
    public void cancelTransaction() {
        if (isActive()) {
            this.isCancelled = true;
        }
    }

}
