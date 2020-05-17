package nl.inholland.myfirstapi.interfaces;

import com.sun.xml.bind.v2.model.core.ID;
import nl.inholland.myfirstapi.models.Piano;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPianoRepository extends CrudRepository<Piano, Integer> {
    <S extends Piano> S save(S piano);
    <S extends Piano> Iterable<S> saveAll(Iterable<S> pianos);
    Optional<Piano> findById(ID id);
    boolean existsById(ID id);
    void deleteById(ID id);
    void deleteAll(Iterable<? extends Piano> pianos);
    void deleteAll();
    void delete(Piano piano);
    long count();
    Iterable<Piano> findAll();

    // Returns a list of pianos in the database based on a given brand
    @Query("SELECT p FROM Piano p WHERE LOWER(p.brand) = ?1")
    List<Piano> getPianosByBrand(String brand);
}
