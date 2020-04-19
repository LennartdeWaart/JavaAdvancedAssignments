package nl.inholland.myfirstapi.controllers;

import nl.inholland.myfirstapi.interfaces.IPianoService;
import nl.inholland.myfirstapi.services.PianoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("pianos")
public class PianoController {

    @Autowired
    private IPianoService pianoService = new PianoService();

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getPianos(@RequestParam(name = "brand", required = false) String brand) {
        if(brand != null && brand != "") {
            return ResponseEntity.status(200).body(pianoService.getPianosByBrand(brand)); }
        else {
            return ResponseEntity.status(200).body(pianoService.getAllPianos()); }
    }
}
