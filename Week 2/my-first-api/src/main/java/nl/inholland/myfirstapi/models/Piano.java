package nl.inholland.myfirstapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Component
@JsonIgnoreProperties({ "getId", "getBrand", "setBrand", "getColour", "setColour" }) // Hide getters and setters in JSON response
public class Piano {
    @JsonProperty // Show private properties in JSON response
    private Long id;
    public Long getId = id;

    @JsonProperty
    private String brand;
    public String getBrand = brand;
    public Consumer<String> setBrand = s -> this.brand = s;

    @JsonProperty
    private Colours colour;
    public Colours getColour = colour;
    public Consumer<Colours> setColour = c -> this.colour = c;

    // Parameterless constructor for and Component annotation because "Consider defining a bean of type 'java.lang.Long' in your configuration." keeps coming up
    public Piano() { }

    // Constructor with all class properties
    public Piano(Long id, String brand, Colours colour) {
        this.id = id;
        this.brand = brand;
        this.colour = colour;
    }
}
