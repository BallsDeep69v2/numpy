package repository;

import domain.Ausleihe;
import domain.BuchExemplar;
import domain.Schueler;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AusleiheRepository {

    Ausleihe save(Ausleihe ausleihe);

    //Liefert alle Ausleihen aus der DB zurück
    List<Ausleihe> findAll();

    //Liefer alle Ausleihen, welche noch nicht abgeschlosssen sind
    List<Ausleihe> findAllPending();

    //Liefer die ausständige Ausleihe zurück, welche das buchExemplar betrifft
    Optional<Ausleihe> findPendingByExemplar(BuchExemplar buchExemplar);

    //Setzt den Status des Buches auf 1 (zurückgegeben)
    void update(Ausleihe ausleihe);

    void delete(Ausleihe ausleihe);
    void deleteAllBefore(LocalDate date);
}
