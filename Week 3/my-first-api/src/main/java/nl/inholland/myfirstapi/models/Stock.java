package nl.inholland.myfirstapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.function.Consumer;

@Entity
@Table(name="Stock")
//@JsonIgnoreProperties({ "getId", "getPiano", "setPiano", "getQuantity", "setQuantity" }) // Hide getters and setters in JSON response
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty // Show private properties in JSON response
    private Long id;

    @OneToOne
    @JsonProperty
    private Piano piano;

    @Column(name="quantity", nullable=false)
    @JsonProperty
    private Integer quantity;

    // Parameterless constructor for and Component annotation because "Consider defining a bean of type 'java.lang.Long' in your configuration." keeps coming up
    public Stock() { }

    // Constructor with all class properties
    public Stock(Piano piano, Integer quantity) {
        this.piano = piano;
        this.quantity = quantity;
    }

    // Getters
    public Long getId() { return this.id; }
    public Piano getPiano() { return this.piano; }
    public Integer getQuantity() { return this.quantity; }

    // Setters
    public void setPiano(Piano piano) { this.piano = piano; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return "Stock [stockId=" + id + ", piano=" + piano + ", quantity=" + quantity + "]";
    }

    // Function to check if the model is valid
    public boolean isValid() {
        if (this.piano != null && this.quantity != null)
            return true;

        return false;
    }
}
