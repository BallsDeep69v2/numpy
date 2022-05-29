package repository;

import domain.Schueler;
import org.junit.jupiter.api.*;
import repository.setup.TestConnectionSupplier;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class JdbcSchuelerRepositoryTest {

    private final TestConnectionSupplier connectionSupplier = new TestConnectionSupplier();
    private Connection connection;

    private JdbcSchuelerRepository schuelerRepository;

    @BeforeEach
    void createRepository() throws SQLException {
        connection = connectionSupplier.getConnection();
        schuelerRepository = new JdbcSchuelerRepository(connection);
    }

    @AfterEach
    void closeConnection() throws SQLException {
        connection.close();
    }

    @Nested
    class Saving {
        @Test
        void savedSchuelerHasID() {
            Schueler schuelerToAdd = new Schueler("Paul", "Schöppl", "4BHIF", "p.schoeppl@htlstp.at");

            Schueler savedSchueler = schuelerRepository.save(schuelerToAdd);

            assertThat(savedSchueler).hasNoNullFieldsOrProperties();
        }

        @Test
        void savedSchuelerAppearsInDB() {
            Schueler schuelerToAdd = new Schueler("Paul", "Schöppl", "4BHIF", "p.schoeppl@htlstp.at");

            Schueler savedSchueler = schuelerRepository.save(schuelerToAdd);

            assertThat(schuelerRepository.findBySchuelerID(savedSchueler.getId())).isNotEmpty();
        }

        @Test
        void savesMultipleSchuelerWorks() {
            var schuelerToAdd = List.of(new Schueler("Paul", "Schöppl", "4BHIF", "p.schoeppl@htlstp.at"),
                    new Schueler("Temo", "Natroshvili", "4BHIF", "t.natroschwili@htlstp.at"));

            schuelerRepository.saveAll(schuelerToAdd);

            assertThat(schuelerRepository.findAll()).containsExactlyInAnyOrderElementsOf(schuelerToAdd);
        }
    }

    @Nested
    class Deleting {
        @Test
        void works() {
            var schuelerToAdd = List.of(new Schueler("Paul", "Schöppl", "4BHIF", "p.schoeppl@htlstp.at"),
                    new Schueler("Temo", "Natroshvili", "4BHIF", "t.natroschwili@htlstp.at"));
            schuelerRepository.saveAll(schuelerToAdd);
            var schuelerToDelete = schuelerToAdd.get(0);

            schuelerRepository.delete(schuelerToDelete);

            assertThat(schuelerRepository.findAll()).doesNotContain(schuelerToDelete);
        }

        @Test
        void doesNotThrowExceptionIfSchuelerDoesNotExist() {
            Schueler schuelerToAdd = new Schueler(1, "Paul", "Schöppl", "4BHIF", "p.schoeppl@htlstp.at");

            assertDoesNotThrow(() -> schuelerRepository.delete(schuelerToAdd));
        }
    }
}
