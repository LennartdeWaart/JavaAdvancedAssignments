package nl.inholland.myfirstapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.function.Consumer;

@Entity
@Table(name="Pianos")
//@JsonIgnoreProperties({ "getId", "getBrand", "setBrand", "getColour", "setColour", "getPrice", "setPrice" }) // Hide getters and setters in JSON response
public class Piano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty // Show private properties in JSON response
    private Long id;

    @Column(name="brand", nullable=false)
    @JsonProperty
    private String brand;

    @Column(name="colour", nullable=false)
    @JsonProperty
    private Colours colour;

    @Column(name="price", nullable=false)
    @JsonProperty
    private Double price;

    // Parameterless constructor for and Component annotation because "Consider defining a bean of type 'java.lang.Long' in your configuration." keeps coming up
    public Piano() { }

    // Constructor with all class properties
    public Piano(String brand, Colours colour, Double price) {
        this.brand = brand;
        this.colour = colour;
        this.price = price;
    }

    // Getters
    public Long getId() { return this.id; }
    public String getBrand() { return this.brand; }
    public Colours getColour() { return this.colour; }
    public Double getPrice() { return this.price; }

    // Setters
    public void setBrand(String brand) { this.brand = brand; }
    public void setColour(Colours colour) { this.colour = colour; }
    public void setPrice(Double price) { this.price = price; }

    @Override
    public String toString() {
        return "Piano [id=" + id + ", brand=" + brand + ", colour=" + colour + ", price=" + price + "]";
    }

    // Function to check if the model is valid
    public boolean isValid() {
        if (this.brand != null && this.colour != null &&
            this.price != null && this.price != Double.NaN)
            return true;

        return false;
    }
}
