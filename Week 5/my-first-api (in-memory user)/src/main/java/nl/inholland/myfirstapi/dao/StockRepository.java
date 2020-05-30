package nl.inholland.myfirstapi.dao;

import nl.inholland.myfirstapi.models.Stock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends CrudRepository<Stock, Integer> {
    // Returns a list of pianos in the database based on a given brand
    @Query("SELECT s FROM Stock as s JOIN Piano as p on s.piano=p.id WHERE LOWER(p.brand) = ?1")
    List<Stock> getStockByPianoBrand(String brand);
}
