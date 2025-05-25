package Model;

import Controller.TransactionController;

public class Main {
    public static void main(String[] args) {
        TransactionController controller = new TransactionController();
        controller.simulate();
    }
}