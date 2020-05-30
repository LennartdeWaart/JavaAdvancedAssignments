package nl.inholland.myfirstapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="Pianos")
//@JsonIgnoreProperties({ "getId", "getBrand", "setBrand", "getColour", "setColour", "getPrice", "setPrice" }) // Hide getters and setters in JSON response
public class Piano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty // Show private properties in JSON response
    @Setter(AccessLevel.NONE)
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

    // Constructor with all class properties
    public Piano(String brand, Colours colour, Double price) {
        this.brand = brand;
        this.colour = colour;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Piano [id=" + id + ", brand=" + brand + ", colour=" + colour + ", price=" + price + "]";
    }

    // Function to check if the model is valid
    public boolean isValid() {
        return this.brand != null && this.colour != null && this.price != null;
    }
}
