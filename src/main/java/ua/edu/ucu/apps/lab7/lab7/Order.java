package ua.edu.ucu.apps.lab7.lab7;

import java.util.LinkedList;
import java.util.List;

import ua.edu.ucu.apps.lab7.lab7.delivery.Delivery;
import ua.edu.ucu.apps.lab7.lab7.payment.Payment;

public class Order {
    private List<Item> items;
    private Payment payment;
    private Delivery delivery;

    public Order() {
        this.items = new LinkedList<>();
    }

    public void setPaymentStrategy(Payment payment) {
        this.payment = payment;
    }

    public void setDeliveryStrategy(Delivery delivery) {
        this.delivery = delivery;
    }

    public double calculateTotalPrice() {
        return items.stream().mapToDouble(Item::price).sum();
    }

    public void processOrder(Payment payment) {
        this.payment = payment;
        payment.collectPaymentDetails();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public List<Item> getItems() {
        return items;
    }
}
