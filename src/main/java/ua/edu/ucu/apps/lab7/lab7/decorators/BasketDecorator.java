package ua.edu.ucu.apps.lab7.lab7.decorators;

import ua.edu.ucu.apps.lab7.lab7.Item;

public class BasketDecorator extends ItemDecorator {

    public BasketDecorator(Item item) {
        super(item);
    }

    @Override
    public String getDescription() {
        return item.getDescription() + " wrapped in Basket";
    }

    @Override
    public double price() {
        return 4 + item.price();
    }
}
