package nl.inholland.myfirstapi.dao;

import nl.inholland.myfirstapi.models.Piano;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PianoRepository extends CrudRepository<Piano, Integer> {
    // Returns a list of pianos in the database based on a given brand
    @Query("SELECT p FROM Piano p WHERE LOWER(p.brand) = ?1")
    List<Piano> getPianosByBrand(String brand);
}
