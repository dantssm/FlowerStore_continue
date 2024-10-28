package ua.edu.ucu.apps.lab7.lab7.payment;


import java.util.HashMap;
import java.util.Map;

public class PayPalPaymentStrategy implements Payment {
    private static final Map<String, String> DATA_BASE = new HashMap<>();
    private String email;
    private String password;
    private boolean signedIn;

    static {
        DATA_BASE.put("amanda1985", "amanda@ya.com");
        DATA_BASE.put("qwerty", "john@amazon.eu");
    }

    public void setCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public void collectPaymentDetails() {
        if (verify()) {
            System.out.println("Data verification has been successful.");
            signedIn = true;
        } else {
            throw new IllegalStateException("Invalid email or password");
        }
    }

    private boolean verify() {
        return email != null && password != null && email.equals(DATA_BASE.get(password));
    }

    @Override
    public boolean pay(double paymentAmount) {
        if (signedIn) {
            System.out.println("Paying " + paymentAmount + " using PayPal.");
            return true;
        } else {
            System.out.println("Payment failed");
            return false;
        }
    }
}