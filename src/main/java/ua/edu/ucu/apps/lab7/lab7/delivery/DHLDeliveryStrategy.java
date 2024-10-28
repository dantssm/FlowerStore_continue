package ua.edu.ucu.apps.lab7.lab7.delivery;

import java.util.List;

import ua.edu.ucu.apps.lab7.lab7.Item;

public class DHLDeliveryStrategy implements Delivery {
    private String address;

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void collectDeliveryDetails() {
        if (address == null || address.isEmpty()) {
            throw new IllegalStateException("Delivery address not set");
        }
        System.out.println("DHL delivery address collected: " + address);
    }

    @Override
    public void deliver(List<Item> items) {
        if (address == null || address.isEmpty()) {
            System.out.println("Delivery failed.");
        } else {
            System.out.println("The package containing " + items.size() + " items will be delivered to " + address + " via DHL.");
        }
    }
}
