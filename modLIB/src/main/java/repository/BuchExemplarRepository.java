package repository;

import domain.BuchExemplar;
import domain.BuchTyp;

import java.util.List;
import java.util.Optional;

public interface BuchExemplarRepository {

    //Liefert alle Buchexemplare von der DB
    List<BuchExemplar> findAll();

    //Liefert alle Buchexemplare von einem BuchTyp
    //Wenn Buchtyp nicht vorhanden - Exception
    List<BuchExemplar> findByBuchTyp(BuchTyp buchTyp);

    //Speichert das uebergebene BuchExemplar in die DB
    //Wenn das Buchexemplar schon vorhanden ist - Exception
    BuchExemplar save(BuchExemplar buchExemplar);

    //Liefert das BuchExemplar anhand der ID
    Optional<BuchExemplar> findById(int id);

    //Speichert eine ganze Liste von BuchExemplaren in die DB
    //Die Methode save wird auf die ganze Liste aufgerufen --> Exception bei doppleten Exemplaren
    boolean saveAll(List<BuchExemplar> buchExemplar);

    //Loescht ein BuchExemplar von der DB
    //Falls nicht vorhanden - return false
    boolean delete(BuchExemplar buchExemplar);

}
