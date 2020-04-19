package nl.inholland.myfirstapi.services;

import com.jayway.jsonpath.JsonPath;
import nl.inholland.myfirstapi.interfaces.IPianoService;
import nl.inholland.myfirstapi.models.Colours;
import nl.inholland.myfirstapi.models.Piano;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@Service
public class PianoService implements IPianoService { // PianoService must contain everything in contract IPianoService

    // Returns a list of all pianos in the database
    public List<Piano> getAllPianos() {
        List<Piano> pianos = new ArrayList<>();
        try
        {
            // Read, filter and parse data
            ((List<Object>)((JSONObject) new JSONParser()
                    .parse(new FileReader("src/main/resources/database.json")))
                    .get("Pianos")) // Get Pianos from database
                    .stream() // Create temporary stream
                    .forEach(row -> { // For each piano...
                        JSONObject jsonObjectRow = (JSONObject) row;
                        pianos.add(new Piano((Long) jsonObjectRow.get("id"), (String) jsonObjectRow.get("brand"),
                                Colours.valueOf((String) jsonObjectRow.get("colour")))); // Add Piano to list
                    });

            return pianos;
        }
        catch (Exception e) {
            System.out.println(e.toString());
            return pianos;
        }
    }

    // Returns a list of pianos in the database based on a given brand
    public List<Piano> getPianosByBrand(String brand) {
        List<Piano> pianos = new ArrayList<>();
        try
        {
            // Read, filter and parse data
            ((List<Object>)((JSONObject) new JSONParser()
                    .parse(new FileReader("src/main/resources/database.json")))
                    .get("Pianos")) // Get Pianos from database
                    .stream() // Create temporary stream
                    .forEach(row -> { // For each piano...
                        JSONObject jsonObjectRow = (JSONObject) row;
                        if(jsonObjectRow.get("brand").toString().toLowerCase()
                                .equals(brand.toLowerCase())) { // Filter Pianos by parameter
                            pianos.add(new Piano((Long) jsonObjectRow.get("id"), (String) jsonObjectRow.get("brand"),
                                    Colours.valueOf((String) jsonObjectRow.get("colour")))); // Add Piano to list
                        }
                    });

            return pianos;
        }
        catch (Exception e) {
            System.out.println(e.toString());
            return pianos;
        }
    }
}
