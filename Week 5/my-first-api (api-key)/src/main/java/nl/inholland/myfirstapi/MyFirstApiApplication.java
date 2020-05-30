package nl.inholland.myfirstapi;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log
public class MyFirstApiApplication {

	public static void main(String[] args) {
		try {
			log.info("Starting Len's Piano Shop application...");
			SpringApplication.run(MyFirstApiApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
