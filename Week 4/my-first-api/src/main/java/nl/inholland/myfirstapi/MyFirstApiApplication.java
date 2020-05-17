package nl.inholland.myfirstapi;

import lombok.extern.java.Log;
import nl.inholland.myfirstapi.config.Config;
import nl.inholland.myfirstapi.interfaces.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log
public class MyFirstApiApplication implements ApplicationRunner {

	@Autowired
	private Config config;
	@Autowired
	private IStockService stockService;


	public static void main(String[] args) {
		try {
			log.info("Starting Len's Piano Shop application...");
			SpringApplication.run(MyFirstApiApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Populate in-memory database on appStart
	@Override
	public void run(ApplicationArguments args) throws Exception {
		stockService.saveAll(config.getStock()); // Retrieve data from config
	}
}
