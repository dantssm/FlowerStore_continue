package ua.edu.ucu.apps.lab7.lab7.decorators;

import ua.edu.ucu.apps.lab7.lab7.Item;

public class RibbonDecorator extends ItemDecorator {

    public RibbonDecorator(Item item) {
        super(item);
    }

    @Override
    public String getDescription() {
        return item.getDescription() + " wrapped in Ribbon";
    }

    @Override
    public double price() {
        return 40 + item.price();
    }
}
