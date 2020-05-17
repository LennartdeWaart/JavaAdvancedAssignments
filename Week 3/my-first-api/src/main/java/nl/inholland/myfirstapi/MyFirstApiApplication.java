package nl.inholland.myfirstapi;

import nl.inholland.myfirstapi.interfaces.IPianoService;
import nl.inholland.myfirstapi.models.Colours;
import nl.inholland.myfirstapi.models.Piano;
import nl.inholland.myfirstapi.services.PianoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@SpringBootApplication
public class MyFirstApiApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(MyFirstApiApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
