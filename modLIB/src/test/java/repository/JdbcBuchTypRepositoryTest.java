package repository;

import domain.BuchTyp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import repository.setup.TestConnectionSupplier;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class JdbcBuchTypRepositoryTest {

    private final TestConnectionSupplier connectionSupplier = new TestConnectionSupplier();
    private Connection connection;

    private JdbcBuchTypRepository buchTypRepository;

    @BeforeEach
    void createRepository() throws SQLException {
        connection = connectionSupplier.getConnection();
        buchTypRepository = new JdbcBuchTypRepository(connection);
    }

    @AfterEach
    void closeConnection() throws SQLException {
        connection.close();
    }

    @Nested
    class Saving{
        @Test
        void savesBuchtyp(){
            BuchTyp buchTypToAdd = new BuchTyp("1234", "irgendwas", "irgendwer");

            buchTypRepository.save(buchTypToAdd);

            assertThat(buchTypRepository.findByISBN(buchTypToAdd.getIsbn())).isNotEmpty();
        }
    }

    @Nested
    class Updating{
    }
}