package nl.inholland.myfirstapi.controllers;

import nl.inholland.myfirstapi.services.PianoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("pianos")
public class PianoController {

    @Autowired
    private PianoService pianoService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllPianos() {
        return ResponseEntity.status(200).body(pianoService.getAllPianos());
    }
}
