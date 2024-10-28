package ua.edu.ucu.apps.lab7.lab7.delivery;

import java.util.List;

import ua.edu.ucu.apps.lab7.lab7.Item;

public class PostDeliveryStrategy implements Delivery {
    private String postOffice;

    public void setPostOffice(String postOffice) {
        this.postOffice = postOffice;
    }

    @Override
    public void collectDeliveryDetails() {
        if (postOffice == null || postOffice.isEmpty()) {
            throw new IllegalStateException("Post office address not set");
        }
        System.out.println("Post office address collected: " + postOffice);
    }

    @Override
    public void deliver(List<Item> items) {
        if (postOffice == null || postOffice.isEmpty()) {
            System.out.println("Delivery failed. Post office address is missing.");
        } else {
            System.out.println("The package containing " + items.size() + " items will be delivered to post office at " + postOffice + ".");
        }
    }
}
