package repository;

import domain.Person;
import org.junit.jupiter.api.*;
import repository.setup.TestConnectionSupplier;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class JdbcPersonRepositoryTest {

    private final TestConnectionSupplier connectionSupplier = new TestConnectionSupplier();
    private Connection connection;

    private JdbcSchuelerRepository schuelerRepository;

    @BeforeEach
    void createRepository() throws SQLException {
        connection = connectionSupplier.getConnectionWithEmptyTables();
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
            Person personToAdd = new Person("Paul", "Schöppl", "4BHIF", "p.schoeppl@htlstp.at");

            Person savedPerson = schuelerRepository.save(personToAdd);

            assertThat(savedPerson).hasNoNullFieldsOrProperties();
        }

        @Test
        void savedSchuelerAppearsInDB() {
            Person personToAdd = new Person("Paul", "Schöppl", "4BHIF", "p.schoeppl@htlstp.at");

            Person savedPerson = schuelerRepository.save(personToAdd);

            assertThat(schuelerRepository.findBySchuelerID(savedPerson.getId())).isNotEmpty();
        }

        @Test
        void savesMultipleSchuelerWorks() {
            var schuelerToAdd = List.of(new Person("Paul", "Schöppl", "4BHIF", "p.schoeppl@htlstp.at"),
                    new Person("Temo", "Natroshvili", "4BHIF", "t.natroschwili@htlstp.at"));

            schuelerRepository.saveAll(schuelerToAdd);

            assertThat(schuelerRepository.findAll()).containsExactlyInAnyOrderElementsOf(schuelerToAdd);
        }
    }

    @Nested
    class Deleting {
        @Test
        void works() {
            var schuelerToAdd = List.of(new Person("Paul", "Schöppl", "4BHIF", "p.schoeppl@htlstp.at"),
                    new Person("Temo", "Natroshvili", "4BHIF", "t.natroschwili@htlstp.at"));
            schuelerRepository.saveAll(schuelerToAdd);
            var schuelerToDelete = schuelerToAdd.get(0);

            schuelerRepository.delete(schuelerToDelete);

            assertThat(schuelerRepository.findAll()).doesNotContain(schuelerToDelete);
        }

        @Test
        void doesNotThrowExceptionIfSchuelerDoesNotExist() {
            Person personToAdd = new Person(1, "Paul", "Schöppl", "4BHIF", "p.schoeppl@htlstp.at");

            assertDoesNotThrow(() -> schuelerRepository.delete(personToAdd));
        }
    }
}
