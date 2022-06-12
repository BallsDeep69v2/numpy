package repository;

import domain.BuchTyp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import repository.setup.TestConnectionSupplier;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class JdbcBuchExemplarRepositoryTest {

    private final TestConnectionSupplier connectionSupplier = new TestConnectionSupplier();
    private Connection connection;

    private JdbcBuchExemplarRepository exemplarRepository;
    private JdbcBuchTypRepository buchTypRepository;

    @BeforeEach
    void createRepository() throws SQLException {
        connection = connectionSupplier.getConnection();
        buchTypRepository = new JdbcBuchTypRepository(connection);
        exemplarRepository = new JdbcBuchExemplarRepository(connection);
    }

    @AfterEach
    void closeConnection() throws SQLException {
        connection.close();
    }

    @Nested
    class Saving {
        //ist im AusleiheRepository schon getestet
    }

    @Nested
    class Finding {
        @Test
        void findNumberOfTypeWorks(){
            BuchTyp buchtyp = new BuchTyp("isbn", "buchtyp", "test");
            buchTypRepository.save(buchtyp);
            exemplarRepository.saveMultiple(buchtyp, 5);
            assertThat(exemplarRepository.getNumberOfBooksFromType(buchtyp)).isEqualTo(5);
        }
    }

}