package repository;

import domain.Ausleihe;
import domain.BuchTyp;
import domain.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import repository.setup.TestConnectionSupplier;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class JdbcAusleiheRepositoryTest {

    private final TestConnectionSupplier connectionSupplier = new TestConnectionSupplier();
    private Connection connection;

    private JdbcAusleiheRepository ausleiheRepository;
    private JdbcPersonRepository schuelerRepository;
    private JdbcBuchExemplarRepository exemplarRepository;
    private JdbcBuchTypRepository buchTypRepository;

    @BeforeEach
    void createRepository() throws SQLException {
        connection = connectionSupplier.getConnectionWithEmptyTables();
        ausleiheRepository = new JdbcAusleiheRepository(connection);
        schuelerRepository = new JdbcPersonRepository(connection);
        exemplarRepository = new JdbcBuchExemplarRepository(connection);
        buchTypRepository = new JdbcBuchTypRepository(connection);
    }

    @AfterEach
    void closeConnection() throws SQLException {
        connection.close();
    }

    void fillDBWithStandardTestData() {
        Person person = new Person();
        BuchTyp buchtyp = new BuchTyp("1234567890123", "buchtyp", "test");
        schuelerRepository.save(person);
        buchTypRepository.save(buchtyp);
        var exemplar = exemplarRepository.save(buchtyp);
        Ausleihe ausleihe = new Ausleihe(exemplar,buchtyp, person, LocalDate.now());

        ausleiheRepository.save(ausleihe);
    }

    @Nested
    class Saving {
        @Test
        void saveSingleAusleiheWorks() {
            Person person = new Person();
            BuchTyp buchtyp = new BuchTyp("1234567890123", "buchtyp", "test");
            schuelerRepository.save(person);
            buchTypRepository.save(buchtyp);
            var exemplar = exemplarRepository.save(buchtyp);
            Ausleihe ausleihe = new Ausleihe(exemplar,buchtyp, person, LocalDate.now());
            ausleiheRepository.save(ausleihe);

            assertThat(ausleiheRepository.findAll()).containsExactly(ausleihe);
        }
    }

    @Nested
    class Finding {
        @Test
        void findPendingWorks() {
            fillDBWithStandardTestData();
            assertThat(ausleiheRepository.findAllPending()).allMatch(a -> !a.isStatus());
        }
    }

    @Nested
    class Updating {
        @Test
        void updateWorks() {
            fillDBWithStandardTestData();
            List<Ausleihe> ausleiheList = ausleiheRepository.findAll();
            Ausleihe randomAusleihe = ausleiheList.get(0);
            randomAusleihe.setStatus(true);

            ausleiheRepository.update(randomAusleihe);

            assertThat(ausleiheRepository.findAllPending()).doesNotContain(randomAusleihe);
        }
    }

    @Nested
    class Deleting{
        @Test
        void deleteSingleElementWorks(){
            fillDBWithStandardTestData();
            Ausleihe toDelete = ausleiheRepository.findAll().get(0);

            ausleiheRepository.delete(toDelete);

            assertThat(ausleiheRepository.findAll()).doesNotContain(toDelete);
        }
    }

}