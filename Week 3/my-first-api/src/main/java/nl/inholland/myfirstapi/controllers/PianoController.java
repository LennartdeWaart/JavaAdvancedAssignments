package nl.inholland.myfirstapi.controllers;

import nl.inholland.myfirstapi.interfaces.IPianoService;
import nl.inholland.myfirstapi.models.Piano;
import nl.inholland.myfirstapi.services.PianoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("pianos")
public class PianoController {

    @Autowired
    private IPianoService pianoService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getPianos(@RequestParam(name = "brand", required = false) String brand) {
        List<Piano> pianos = null;
        if (brand != null && brand != "") {
            pianos = pianoService.getPianosByBrand(brand);
        } else {
            pianos = (List<Piano>)pianoService.findAll();
        }

        if (pianos != null) {
            return ResponseEntity.status(200).body(pianos);
        } else {
            return ResponseEntity.status(204).body("Could not find pianos.");
        }
    }

    @RequestMapping(value = "/save", headers = "Accept=application/json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity savePiano(@RequestBody Piano piano) {
        Piano saved = pianoService.save(piano);
        if (saved != null) {
            return ResponseEntity.status(200).body(saved);
        } else {
            return ResponseEntity.status(424).body("An error occurred, please try again.");
        }
    }

    @RequestMapping(value = "/save/subset", headers = "Accept=application/json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity savePianos(@RequestBody Iterable<Piano> pianos) {
        Iterable<Piano> saved = pianoService.saveAll(pianos);
        if (saved != null) {
            return ResponseEntity.status(200).body(saved);
        } else {
            return ResponseEntity.status(424).body("An error occurred, please try again.");
        }
    }
}
