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
@Table(name="Stock")
//@JsonIgnoreProperties({ "getId", "getPiano", "setPiano", "getQuantity", "setQuantity" }) // Hide getters and setters in JSON response
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty // Show private properties in JSON response
    @Setter(AccessLevel.NONE)
    private Long id;

    @OneToOne
    @JsonProperty
    private Piano piano;

    @Column(name="quantity", nullable=false)
    @JsonProperty
    private Integer quantity;

    // Constructor with all class properties
    public Stock(Piano piano, Integer quantity) {
        this.piano = piano;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Stock [stockId=" + id + ", piano=" + piano + ", quantity=" + quantity + "]";
    }

    // Function to check if the model is valid
    public boolean isValid() {
        return this.piano != null && this.quantity != null;
    }
}
