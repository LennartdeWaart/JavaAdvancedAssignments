package nl.inholland.myfirstapi.configuration;

import nl.inholland.myfirstapi.dao.ApiKeyRepository;
import nl.inholland.myfirstapi.interfaces.IStockService;
import nl.inholland.myfirstapi.models.ApiKey;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MyApplicationRunner implements ApplicationRunner {
    private final IStockService stockService;
    private final StockConfig stockConfig;
    private final ApiKeyRepository apiKeyRepository;

    public MyApplicationRunner(IStockService stockService, StockConfig stockConfig, ApiKeyRepository apiKeyRepository) {
        this.stockService = stockService;
        this.stockConfig = stockConfig;
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        stockService.saveAll(stockConfig.getStock()); // Retrieve data from config
        ApiKey apiKey = apiKeyRepository.save(new ApiKey(UUID.randomUUID().toString()));
        System.out.println("Generated API Key: " + apiKey);
    }
}