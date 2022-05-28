package repository;

import domain.Schueler;

import java.util.List;
import java.util.Optional;

public interface SchuelerRepository {
    List<Schueler> findAll();

    Schueler save(Schueler s);

    Optional<Schueler> findBySchuelerID(int schuelerkennung);

    void saveAll(List<Schueler> s);

    Optional<Schueler> update(Schueler s);

    //Liefert true, wenn Schueler geloescht wurde, und false, wenn kein passender Schueler geloescht wurde
    boolean delete(Schueler s);
}
