package ua.edu.ucu.apps.lab7.lab7.decorators;

import ua.edu.ucu.apps.lab7.lab7.Item;

public class PaperDecorator extends ItemDecorator {

    public PaperDecorator(Item item) {
        super(item);
    }

    @Override
    public String getDescription() {
        return item.getDescription() + " wrapped in Paper";
    }

    @Override
    public double price() {
        return 13 + item.price();
    }
}