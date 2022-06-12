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

class JdbcBuchTypRepositoryTest {

    private final TestConnectionSupplier connectionSupplier = new TestConnectionSupplier();
    private Connection connection;

    private JdbcBuchTypRepository buchTypRepository;

    @BeforeEach
    void createRepository() throws SQLException {
        connection = connectionSupplier.getConnectionWithEmptyTables();
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

            assertThat(buchTypRepository.findByISBN(buchTypToAdd.getIsbn())).isPresent();
        }

        @Test
        void savesBuchtypWithAllAttributes(){
            BuchTyp buchTypToAdd = new BuchTyp("1234", "irgendwas", "irgendwer", "tolle Buch", "Online Marketing", 2018, 500);
            buchTypRepository.save(buchTypToAdd);
            //Vergleich, ob alle Attribute passen, daher mit toString()
            assertThat(buchTypRepository.findByISBN(buchTypToAdd.getIsbn())).get().asString().isEqualTo(buchTypToAdd.toString());
        }
    }

    @Nested
    class Updating{
        @Test
        void updatingWorks(){
            BuchTyp buchTypToAdd = new BuchTyp("1234", "irgendwas", "irgendwer");
            buchTypRepository.save(buchTypToAdd);
            buchTypToAdd.setAuthor("Goethe");
            buchTypRepository.update(buchTypToAdd);

            assertThat(buchTypRepository.findByISBN(buchTypToAdd.getIsbn()).orElseThrow().getAuthor()).isEqualTo("Goethe");
        }
    }
}