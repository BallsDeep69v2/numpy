package repository;

import domain.BuchTyp;

import java.util.List;
import java.util.Optional;

public interface BuchTypRepository {

    //Liefert alle Buchtypen aus der DB
    List<BuchTyp> findAll();

    //Speichert einen neuen BuchTyp in der DB
    //Wenn die ISBN schon vorhanden - Exception
    Optional<BuchTyp> findByISBN(int isbn);

    //Speichert einen neuen BuchTyp in der DB
    //Wenn die ISBN schon vorhanden - Exception
    BuchTyp save(BuchTyp buchTyp);

    //Überschreibt die Attribute mit den Übergebenen von der ISBN
    //Wenn die ISBN nicht vorhanden - Exception
    Optional<BuchTyp> update(BuchTyp buchTyp);

    //Löscht den BuchTyp
    //Wenn die ISBN nicht vorhanden - Exception
    boolean delete(BuchTyp buchTyp);

}
