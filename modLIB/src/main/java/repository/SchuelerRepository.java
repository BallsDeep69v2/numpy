package repository;

import domain.Person;

import java.util.List;
import java.util.Optional;

public interface SchuelerRepository {
    List<Person> findAll();

    Person save(Person s);

    Optional<Person> findBySchuelerID(int schuelerkennung);

    void saveAll(List<Person> s);

    Optional<Person> update(Person s);

    //Liefert true, wenn Schueler geloescht wurde, und false, wenn kein passender Schueler geloescht wurde
    boolean delete(Person s);

    void deleteAll();
}
