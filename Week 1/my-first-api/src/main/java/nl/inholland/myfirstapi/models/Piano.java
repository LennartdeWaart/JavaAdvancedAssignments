package nl.inholland.myfirstapi.models;

import org.springframework.stereotype.Component;

@Component
public class Piano {

    public Long id;
    public String brand;
    public Colours colour;

    // Parameterless constructor for and Component annotation
    public Piano() { }

    // Constructor with all class properties
    public Piano(Long id, String brand, Colours colour) {
        this.id = id;
        this.brand = brand;
        this.colour = colour;
    }
}
