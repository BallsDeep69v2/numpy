package repository;

import domain.Ausleihe;
import domain.BuchExemplar;

import java.util.List;
import java.util.Optional;

public interface AusleiheRepository {
    //Liefert alle Ausleihen aus der DB zurück
    List<Ausleihe> findAll();

    //Liefer alle Ausleihen, welche noch nicht abgeschlosssen sind
    List<Ausleihe> findAllPending();

    //Liefer die ausständige Ausleihe zurück, welche das buchExemplar betrifft
    Optional<Ausleihe> findPendingByExemplar(BuchExemplar buchExemplar);

    //Speichert einen neuen Ausleihe-Eintrag in der DB
    Ausleihe save(Ausleihe ausleihe);

    //Setzt den Status des Buches auf 1 (zurückgegeben)
    void update(Ausleihe ausleihe);

}
