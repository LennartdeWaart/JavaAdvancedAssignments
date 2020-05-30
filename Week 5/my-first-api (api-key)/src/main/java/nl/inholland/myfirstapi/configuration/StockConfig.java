package nl.inholland.myfirstapi.configuration;

import lombok.Getter;
import lombok.Setter;
import nl.inholland.myfirstapi.models.Piano;
import nl.inholland.myfirstapi.models.Stock;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix="stock")
public class StockConfig {
    private int defaultQuantity;
    private List<Piano> pianos = new ArrayList<>();
    private List<Stock> stock = new ArrayList<>();

    public List<Stock> getStock() {
        getPianos().forEach(piano -> {
            stock.add(new Stock(piano, getDefaultQuantity()));
        });
        return stock;
    }
}
