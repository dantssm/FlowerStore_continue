package ua.edu.ucu.apps.lab7.lab7.flower;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import ua.edu.ucu.apps.lab7.lab7.Item;
import java.util.stream.Collectors;

@Getter
public class FlowerBucket extends Item {

    private List<FlowerPack> flowerPacks;

    public FlowerBucket(){
        this.flowerPacks = new ArrayList<>();
    }
    public void addFlowerPack(FlowerPack flowerPack) {
        flowerPacks.add(flowerPack);
    }

    public double price() {
        double price = 0;
        for (int i = 0; i < flowerPacks.size(); i++) {
            price += flowerPacks.get(i).getAmount() * 
            flowerPacks.get(i).getFlower().getPrice();
        }

        return price;
    }

    public List<Flower> searchFlower(FlowerType flowerType, FlowerColor flowerColor) {
        List<Flower> match = new ArrayList<>();
        for (FlowerPack pack : flowerPacks) {
            Flower flower = pack.getFlower();
            if (flower.getFlowerType() == flowerType && flower.getColor().equals(flowerColor.getCode())) {
                match.add(flower);
            }
        }
        return match;
    }

    @Override
    public String getDescription() {
        if (flowerPacks.isEmpty()) {
            return "An empty Flower Bucket";
        }

        return "A bucket containing " + flowerPacks.stream()
                .map(pack -> pack.getAmount() + " " + pack.getFlower().getColor() + " " + pack.getFlower().getFlowerType())
                .collect(Collectors.joining(", "));
    }
}

