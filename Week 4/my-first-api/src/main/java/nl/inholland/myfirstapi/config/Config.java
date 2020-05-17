package nl.inholland.myfirstapi.config;

import nl.inholland.myfirstapi.models.Piano;
import nl.inholland.myfirstapi.models.Stock;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix="stock")
public class Config {
    private int quantity;
    private List<Piano> pianos = new ArrayList<>();
    private List<Stock> stock = new ArrayList<>();

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Piano> getPianos() {
        return pianos;
    }

    public void setPianos(List<Piano> pianos) {
        this.pianos = pianos;
    }

    public List<Stock> getStock() {
        getPianos().forEach(piano -> {
            stock.add(new Stock(piano, getQuantity()));
        });
        return stock;
    }

    public List<Stock> setStock() {
        return this.stock = null;
    }
}
