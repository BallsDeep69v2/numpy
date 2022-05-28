package repository;

import domain.Schueler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import repository.setup.TestConnectionSupplier;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
    class Saving{
        //ist im AusleiheRepository schon getestet
    }

}