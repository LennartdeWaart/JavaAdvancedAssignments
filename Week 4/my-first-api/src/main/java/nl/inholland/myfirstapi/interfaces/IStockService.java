package nl.inholland.myfirstapi.interfaces;

import com.sun.xml.bind.v2.model.core.ID;
import nl.inholland.myfirstapi.models.Stock;

import java.util.List;
import java.util.Optional;

public interface IStockService {
    Stock save(Stock stock);
    Iterable<Stock> saveAll(Iterable<Stock> stock);
    Optional<Stock> findById(ID id);
    boolean existsById(ID id);
    boolean deleteById(ID id);
    boolean deleteAll(Iterable<Stock> stock);
    boolean deleteAll();
    boolean delete(Stock stock);
    long count();
    Iterable<Stock> findAll();
    List<Stock> getStockByPianoBrand(String brand);
}
