package Controller;

import Model.Property;
import Model.User;
import Service.TransactionService;
import Model.Transaction;

import java.util.List;

public class TransactionController {
    private TransactionService service = new TransactionService();

    public void simulate() {
        // Sample Users
        User buyer = new User("U001", "John Smith", "john@gmail.com");
        User seller = new User("U002", "Allen White", "allen@gmail.com");

        // Sample Property
        Property property = new Property("P001", "123 Main St", 150000, true);

        // Create a transaction
        Transaction t1 = service.createTransaction("T001", property, buyer, seller, 150000);

        // View transactions
        System.out.println("All Transactions:");
        printTransactions(service.getAllTransactions());

        // Complete it
        service.completeTransaction("T001");

        // Calculate total sales
        System.out.println("Total Completed Sales: $" + service.calculateTotalSales());
    }

    private void printTransactions(List<Transaction> transactions) {
        for (Transaction t : transactions) {
            System.out.println("Transaction ID: " + t.getTransactionID() + ", Transaction Amount: " + t.getAmount() + ", Completed: " + t.isCompleted());
        }
    }
}
