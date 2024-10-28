package ua.edu.ucu.apps.lab7.lab7.flower;

import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class Flower {
    private FlowerColor color;
    private FlowerType flowerType;
    private double price;
    private double sepalLength;

    public Flower(final Flower flower) {
        this.color = flower.color;
        this.flowerType = flower.flowerType;
        this.price = flower.price;
        this.sepalLength = flower.sepalLength;
    }

    public String getColor() {
        return color.name();
    }

    public List<Flower> getFlower() {
        return Arrays.asList();
    }
}
