package ua.edu.ucu.apps.lab7.lab7.delivery;

import java.util.List;

import ua.edu.ucu.apps.lab7.lab7.Item;

public interface Delivery {
    void collectDeliveryDetails();
    void deliver(List<Item> items);   
}
