package ua.edu.ucu.apps.lab7.lab7.payment;

public class CreditCardPaymentStrategy implements Payment {
    public CreditCard card;

    public CreditCardPaymentStrategy() {
    }

    public void setCreditCardDetails(String number, String date, String cvv) {
        this.card = new CreditCard(number, date, cvv);
    }

    @Override
    public void collectPaymentDetails() {
        if (card == null) {
            throw new IllegalStateException("Credit card details not set");
        }
        System.out.println("Credit card details collected.");
    }

    @Override
    public boolean pay(double paymentAmount) {
        if (cardIsPresent() && card.getAmount() >= paymentAmount) {
            System.out.println("Paying " + paymentAmount + " using Credit Card.");
            card.setAmount(card.getAmount() - paymentAmount);
            return true;
        } else {
            System.out.println("Payment failed");
            return false;
        }
    }

    private boolean cardIsPresent() {
        return card != null;
    }
}