package nl.inholland.myfirstapi.interfaces;

import nl.inholland.myfirstapi.models.Piano;

import java.util.List;
import java.util.Optional;

public interface IPianoService {
    Piano save(Piano piano);
    Iterable<Piano> saveAll(Iterable<Piano> pianos);
    Optional<Piano> findById(int id);
    boolean existsById(int id);
    boolean deleteById(int id);
    boolean deleteAll(Iterable<Piano> pianos);
    boolean deleteAll();
    boolean delete(Piano piano);
    long count();
    Iterable<Piano> findAll();
    List<Piano> getPianosByBrand(String brand);
}
