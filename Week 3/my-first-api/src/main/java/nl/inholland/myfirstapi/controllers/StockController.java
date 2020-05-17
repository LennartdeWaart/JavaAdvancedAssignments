package nl.inholland.myfirstapi.controllers;

import com.sun.xml.bind.v2.model.core.ID;
import nl.inholland.myfirstapi.interfaces.IStockService;
import nl.inholland.myfirstapi.models.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("stock")
public class StockController {

    @Autowired
    private IStockService stockService;

    @RequestMapping(value = "/pianos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getStock(@RequestParam(name = "brand", required = false) String brand) {
        List<Stock> stock = null;
        if (brand != null && brand != "") {
            stock = stockService.getStockByPianoBrand(brand);
        } else {
            stock = (List<Stock>)stockService.findAll();
        }

        if (stock != null) {
            return ResponseEntity.status(200).body(stock);
        } else {
            return ResponseEntity.status(204).body("No pianos in stock.");
        }
    }

    @RequestMapping(value = "/pianos/save", headers = "Accept=application/json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity savePianoToStock(@RequestBody Stock stock) {
        Stock saved = stockService.save(stock);
        if (saved != null) {
            return ResponseEntity.status(200).body(saved);
        } else {
            return ResponseEntity.status(424).body("An error occurred, please try again.");
        }
    }

    @RequestMapping(value = "pianos/save/subset", headers = "Accept=application/json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity savePianosToStock(@RequestBody Iterable<Stock> stock) {
        Iterable<Stock> saved = stockService.saveAll(stock);
        if (saved != null) {
            return ResponseEntity.status(200).body(saved);
        } else {
            return ResponseEntity.status(424).body("An error occurred, please try again.");
        }
    }

    @RequestMapping(value = "/pianos/update", headers = "Accept=application/json", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity UpdateStock(@RequestBody Stock stock) {
        Stock saved = stockService.save(stock);
        if (saved != null) {
            return ResponseEntity.status(200).body(saved);
        } else {
            return ResponseEntity.status(424).body("An error occurred, please try again.");
        }
    }

    @RequestMapping(value = "/pianos/delete", headers = "Accept=application/json", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity UpdateStock(@RequestBody Stock stock, @RequestParam(name = "id", required = false) ID id) {
        boolean result;
        if (id != null) {
            result = stockService.deleteById(id);
        } else {
            result = stockService.delete(stock);
        }
        if (result) {
            return ResponseEntity.status(200).body("Stock successfully deleted.");
        } else {
            return ResponseEntity.status(424).body("Could not delete stock.");
        }
    }
}
