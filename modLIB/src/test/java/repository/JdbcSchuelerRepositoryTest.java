package repository;

import domain.Schueler;
import org.junit.jupiter.api.*;
import repository.setup.TestConnectionSupplier;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    class Saving{
        @Test
        void savedSchuelerHasID(){
            Schueler schuelerToAdd = new Schueler("Paul", "Schöppl", "4BHIF", "p.schoeppl@htlstp.at");

            Schueler savedSchueler = schuelerRepository.save(schuelerToAdd);

            assertThat(savedSchueler).hasNoNullFieldsOrProperties();
        }

        @Test
        void savedSchuelerAppearsInDB(){
            Schueler schuelerToAdd = new Schueler("Paul", "Schöppl", "4BHIF", "p.schoeppl@htlstp.at");

            Schueler savedSchueler = schuelerRepository.save(schuelerToAdd);

            assertThat(schuelerRepository.findBySchuelerID(savedSchueler.getId())).isNotEmpty();
        }

        @Test
        void savesMultipleSchuelerWorks(){
            var schuelerToAdd = List.of(new Schueler("Paul", "Schöppl", "4BHIF", "p.schoeppl@htlstp.at"),
                    new Schueler("Temo", "Natroshvili", "4BHIF", "t.natroschwili@htlstp.at"));

            schuelerRepository.saveAll(schuelerToAdd);

            assertThat(schuelerRepository.findAll()).containsExactlyInAnyOrderElementsOf(schuelerToAdd);
        }
    }
}
