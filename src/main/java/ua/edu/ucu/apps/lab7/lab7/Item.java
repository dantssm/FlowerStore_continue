package ua.edu.ucu.apps.lab7.lab7;

public abstract class Item {
    private String description;

    public String getDescription() {
        return description;
    }
    
    public abstract double price();
}
