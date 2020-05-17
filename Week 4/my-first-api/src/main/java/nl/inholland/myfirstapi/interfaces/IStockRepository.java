package nl.inholland.myfirstapi.interfaces;

import com.sun.xml.bind.v2.model.core.ID;
import nl.inholland.myfirstapi.models.Stock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IStockRepository extends CrudRepository<Stock, Integer> {
    <S extends Stock> S save(S stock);
    <S extends Stock> Iterable<S> saveAll(Iterable<S> stock);
    Optional<Stock> findById(ID id);
    boolean existsById(ID id);
    void deleteById(ID id);
    void deleteAll(Iterable<? extends Stock> stock);
    void deleteAll();
    void delete(Stock stock);
    long count();
    Iterable<Stock> findAll();

    // Returns a list of pianos in the database based on a given brand
    @Query("SELECT s FROM Stock as s JOIN Piano as p on s.piano=p.id WHERE LOWER(p.brand) = ?1")
    List<Stock> getStockByPianoBrand(String brand);
}
