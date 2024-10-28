package ua.edu.ucu.apps.lab7.lab7.payment;

public interface Payment {
    void collectPaymentDetails();
    boolean pay(double price);
}
