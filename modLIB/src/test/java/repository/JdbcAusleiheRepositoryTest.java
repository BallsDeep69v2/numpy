package repository;

import domain.Ausleihe;
import domain.BuchExemplar;
import domain.BuchTyp;
import domain.Schueler;
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
import static org.junit.jupiter.api.Assertions.*;

class JdbcAusleiheRepositoryTest {

    private final TestConnectionSupplier connectionSupplier = new TestConnectionSupplier();
    private Connection connection;

    private JdbcAusleiheRepository ausleiheRepository;
    private JdbcSchuelerRepository schuelerRepository;
    private JdbcBuchExemplarRepository exemplarRepository;
    private JdbcBuchTypRepository buchTypRepository;

    @BeforeEach
    void createRepository() throws SQLException {
        connection = connectionSupplier.getConnection();
        ausleiheRepository = new JdbcAusleiheRepository(connection);
        schuelerRepository = new JdbcSchuelerRepository(connection);
        exemplarRepository = new JdbcBuchExemplarRepository(connection);
        buchTypRepository = new JdbcBuchTypRepository(connection);
    }

    @AfterEach
    void closeConnection() throws SQLException {
        connection.close();
    }

    void fillDBWithStandardTestData() {
        Schueler schueler = new Schueler();
        BuchTyp buchtyp = new BuchTyp("isbn", "buchtyp", "test");
        schuelerRepository.save(schueler);
        buchTypRepository.save(buchtyp);
        var exemplar = exemplarRepository.save(buchtyp);
        Ausleihe ausleihe = new Ausleihe(exemplar, schueler, LocalDate.now());

        ausleiheRepository.save(ausleihe);
    }

    @Nested
    class Saving {
        @Test
        void saveSingleAusleiheWorks() {
            Schueler schueler = new Schueler();
            BuchTyp buchtyp = new BuchTyp("isbn", "buchtyp", "test");
            schuelerRepository.save(schueler);
            buchTypRepository.save(buchtyp);
            var exemplar = exemplarRepository.save(buchtyp);
            Ausleihe ausleihe = new Ausleihe(exemplar, schueler, LocalDate.now());
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