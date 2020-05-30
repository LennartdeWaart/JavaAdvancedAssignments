package nl.inholland.myfirstapi.configuration;

import nl.inholland.myfirstapi.dao.UserRepository;
import nl.inholland.myfirstapi.interfaces.IStockService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationRunner implements ApplicationRunner {
    private final IStockService stockService;
    private final StockConfig stockConfig;
    private final UserRepository userRepository;
    private final UserConfig userConfig;

    public MyApplicationRunner(IStockService stockService, StockConfig stockConfig, UserRepository userRepository, UserConfig userConfig) {
        this.stockService = stockService;
        this.stockConfig = stockConfig;
        this.userRepository = userRepository;
        this.userConfig = userConfig;
    }

    @Override
    public void run(ApplicationArguments args) {
        stockService.saveAll(stockConfig.getStock()); // Retrieve data from config
        userRepository.saveAll(userConfig.getUsers());
    }
}