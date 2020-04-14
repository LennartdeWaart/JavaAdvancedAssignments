package nl.inholland.myfirstapi.interfaces;

import nl.inholland.myfirstapi.models.Colours;
import nl.inholland.myfirstapi.models.Piano;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@Service
public class PianoService {

    // Returns a list of all piano in the database
    public List<Piano> getAllPianos() {
        List<Piano> pianos = new ArrayList<>();
        try
        {
            // Convert to JSONObject
            JSONObject jsonObject = (JSONObject) new JSONParser()
                    .parse(new FileReader("src/main/resources/database.json"));
            // Read and parse data
            JSONArray jsonArray = (JSONArray) jsonObject.get("Pianos");
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObjectRow = (JSONObject) jsonArray.get(i);
                pianos.add(new Piano((Long) jsonObjectRow.get("id"), (String) jsonObjectRow.get("brand"),
                        Colours.valueOf((String) jsonObjectRow.get("colour"))));
            }
            return pianos;
        }
        catch (Exception e) {
            System.out.println(e.toString());
            return pianos;
        }
    }
}
