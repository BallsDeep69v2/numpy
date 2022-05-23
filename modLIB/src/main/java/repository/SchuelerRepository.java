package repository;

import domain.Schueler;

import java.util.List;
import java.util.Optional;

public interface SchuelerRepository {
    List<Schueler> findAll();

    Schueler save(Schueler s);

    Optional<Schueler> findBySchuelerID(int schuelerkennung);

    boolean saveAll(List<Schueler> s);

    Optional<Schueler> update(Schueler s);

    boolean delete(Schueler s);
}
