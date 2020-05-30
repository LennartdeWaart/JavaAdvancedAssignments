package nl.inholland.myfirstapi.interfaces;

import nl.inholland.myfirstapi.models.Stock;

import java.util.List;
import java.util.Optional;

public interface IStockService {
    Stock save(Stock stock);
    Iterable<Stock> saveAll(Iterable<Stock> stock);
    Optional<Stock> findById(int id);
    boolean existsById(int id);
    boolean deleteById(int id);
    boolean deleteAll(Iterable<Stock> stock);
    boolean deleteAll();
    boolean delete(Stock stock);
    long count();
    Iterable<Stock> findAll();
    List<Stock> getStockByPianoBrand(String brand);
}
