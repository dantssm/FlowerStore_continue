package ua.edu.ucu.apps.lab7.lab7.decorators;

import ua.edu.ucu.apps.lab7.lab7.Item;

public abstract class ItemDecorator extends Item {
    protected Item item;

    public ItemDecorator(Item item) {
        this.item = item;
    }

    public abstract String getDescription();
}
