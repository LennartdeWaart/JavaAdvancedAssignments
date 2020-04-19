package nl.inholland.myfirstapi.interfaces;

import nl.inholland.myfirstapi.models.Piano;

import java.util.List;

public interface IPianoService {
    List<Piano> getAllPianos();
    List<Piano> getPianosByBrand(String brand);
}
