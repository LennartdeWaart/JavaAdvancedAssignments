package nl.inholland.myfirstapi.services;

import com.sun.xml.bind.v2.model.core.ID;
import nl.inholland.myfirstapi.interfaces.IPianoService;
import nl.inholland.myfirstapi.interfaces.IStockRepository;
import nl.inholland.myfirstapi.interfaces.IStockService;
import nl.inholland.myfirstapi.models.Piano;
import nl.inholland.myfirstapi.models.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

@Service
public class StockService implements IStockService { // StockService must contain everything in contract IStockService

    @Autowired
    private IStockRepository stockRepository;
    @Autowired
    private IPianoService pianoService;

    // Save a stock item to the database
    public Stock save(Stock stock) {
        try {
            Piano piano = pianoService.save(stock.getPiano());
            if (piano.isValid()) {
                Stock saved = stockRepository.save(stock);
                if (saved.isValid()) {
                    return saved;
                }
            }

            throw new Exception("Model is invalid.");
        }
        catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    // Save multiple stock items to the database
    public Iterable<Stock> saveAll(Iterable<Stock> stock) {
        try {
            stock.forEach(s -> {
                Piano piano = pianoService.save(s.getPiano());
            });
             return stockRepository.saveAll(stock);
        }
        catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    // Retrieve stock by id
    public Optional<Stock> findById(ID id) {
        try {
            return stockRepository.findById(id);
        }
        catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    // Check if an id exists in the database
    public boolean existsById(ID id) {
        try {
            return stockRepository.existsById(id);
        }
        catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    // Delete a stock item by its id
    public boolean deleteById(ID id) {
        try {
            stockRepository.deleteById(id);
            return true;
        }
        catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    // Delete a subset of stock in the database
    public boolean deleteAll(Iterable<Stock> stock) {
        try {
            stockRepository.deleteAll(stock);
            return true;
        }
        catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    // Delete all stock items in the database
    public boolean deleteAll() {
        try {
            stockRepository.deleteAll();
            return true;
        }
        catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    // Delete a stock item in the database
    public boolean delete(Stock stock) {
        try {
            stockRepository.delete(stock);
            return true;
        }
        catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    // Returns the number of records in the Stock table
    public long count() {
        try {
            return stockRepository.count();
        }
        catch (Exception e) {
            System.out.println(e.toString());
            return 0;
        }
    }

    // Returns a list of all stock items in the database
    public List<Stock> findAll() {
        try {
            var it = stockRepository.findAll();

            var stock = new ArrayList<Stock>();
            it.forEach(e -> stock.add(e));

            return stock;
        }
        catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    // Returns all stock items in the database with a specific piano brand
    public List<Stock> getStockByPianoBrand(String brand) {
        try {
            return stockRepository.getStockByPianoBrand(brand);
        }
        catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
}
