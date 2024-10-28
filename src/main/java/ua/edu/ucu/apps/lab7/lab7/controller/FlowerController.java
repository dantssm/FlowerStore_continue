package ua.edu.ucu.apps.lab7.lab7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.ArrayList;

import ua.edu.ucu.apps.lab7.lab7.flower.Flower;


@RestController
@RequestMapping("/api/flowers")
public class FlowerController {

    private final List<Flower> flowers = new ArrayList<>();

    @GetMapping
    public List<Flower> getAllFlowers() {
        return flowers;
    }

    @PostMapping
    public Flower addFlower(@RequestBody Flower flower) {
        flowers.add(flower);
        return flower;
    }
}
