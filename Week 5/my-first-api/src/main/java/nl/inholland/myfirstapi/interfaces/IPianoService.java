package nl.inholland.myfirstapi.interfaces;

import com.sun.xml.bind.v2.model.core.ID;
import nl.inholland.myfirstapi.models.Piano;

import java.util.List;
import java.util.Optional;

public interface IPianoService {
    Piano save(Piano piano);
    Iterable<Piano> saveAll(Iterable<Piano> pianos);
    Optional<Piano> findById(ID id);
    boolean existsById(ID id);
    boolean deleteById(ID id);
    boolean deleteAll(Iterable<Piano> pianos);
    boolean deleteAll();
    boolean delete(Piano piano);
    long count();
    Iterable<Piano> findAll();
    List<Piano> getPianosByBrand(String brand);
}
