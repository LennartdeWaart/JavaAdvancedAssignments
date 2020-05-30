package nl.inholland.myfirstapi.configuration;

import nl.inholland.myfirstapi.interfaces.IStockService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationRunner implements ApplicationRunner {
    private final IStockService stockService;
    private final StockConfig stockConfig;

    public MyApplicationRunner(IStockService stockService, StockConfig stockConfig) {
        this.stockService = stockService;
        this.stockConfig = stockConfig;
    }

    @Override
    public void run(ApplicationArguments args) {
        stockService.saveAll(stockConfig.getStock()); // Retrieve data from config
    }
}