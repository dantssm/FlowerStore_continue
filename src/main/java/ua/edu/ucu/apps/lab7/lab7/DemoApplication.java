package ua.edu.ucu.apps.lab7.lab7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.edu.ucu.apps.lab7.lab7.flower.Flower;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RestController 
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping
	public List<Flower> getItems() {
        return Arrays.asList();
	}
}
