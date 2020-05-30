package nl.inholland.myfirstapi.services;

import lombok.extern.java.Log;
import nl.inholland.myfirstapi.interfaces.IPianoService;
import nl.inholland.myfirstapi.models.Piano;
import nl.inholland.myfirstapi.dao.PianoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

@Service
@Log
public class PianoService implements IPianoService { // PianoService must contain everything in contract IPianoService

    @Autowired
    private PianoRepository pianoRepository;

    // Save a piano to the database
    public Piano save(Piano piano) {
        try {
            Piano newPiano =  pianoRepository.save(piano);
            if (newPiano.isValid()) {
                return newPiano;
            } else {
                throw new Exception("Model is invalid.");
            }
        }
        catch (Exception e) {
            log.warning(e.toString());
            return null;
        }
    }

    // Save multiple pianos to the database
    public Iterable<Piano> saveAll(Iterable<Piano> pianos) {
        try {
            return pianoRepository.saveAll(pianos);
        }
        catch (Exception e) {
            log.warning(e.toString());
            return null;
        }
    }

    // Retrieve piano by id
    public Optional<Piano> findById(int id) {
        try {
            return pianoRepository.findById(id);
        }
        catch (Exception e) {
            log.warning(e.toString());
            return Optional.empty();
        }
    }

    // Check if an id exists in the database
    public boolean existsById(int id) {
        try {
            return pianoRepository.existsById(id);
        }
        catch (Exception e) {
            log.warning(e.toString());
            return false;
        }
    }

    // Delete a piano by its id
    public boolean deleteById(int id) {
        try {
            pianoRepository.deleteById(id);
            return true;
        }
        catch (Exception e) {
            log.warning(e.toString());
            return false;
        }
    }

    // Delete a subset of pianos in the database
    public boolean deleteAll(Iterable<Piano> pianos) {
        try {
            pianoRepository.deleteAll(pianos);
            return true;
        }
        catch (Exception e) {
            log.warning(e.toString());
            return false;
        }
    }

    // Delete all pianos in the database
    public boolean deleteAll() {
        try {
            pianoRepository.deleteAll();
            return true;
        }
        catch (Exception e) {
            log.warning(e.toString());
            return false;
        }
    }

    // Delete a piano object in the database
    public boolean delete(Piano piano) {
        try {
            pianoRepository.delete(piano);
            return true;
        }
        catch (Exception e) {
            log.warning(e.toString());
            return false;
        }
    }

    // Returns the number of records in the Pianos table
    public long count() {
        try {
            return pianoRepository.count();
        }
        catch (Exception e) {
            log.warning(e.toString());
            return 0;
        }
    }

    // Returns a list of all pianos in the database
    public List<Piano> findAll() {
        try {
            var it = pianoRepository.findAll();

            var pianos = new ArrayList<Piano>();
            it.forEach(pianos::add);

            return pianos;
        }
        catch (Exception e) {
            log.warning(e.toString());
            return null;
        }
    }

    // Returns all pianos in the database with a specific brand
    public List<Piano> getPianosByBrand(String brand) {
        try {
            return pianoRepository.getPianosByBrand(brand);
        }
        catch (Exception e) {
            log.warning(e.toString());
            return null;
        }
    }
}
