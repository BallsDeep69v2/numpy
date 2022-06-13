package repository;

import domain.Person;
import errorhandling.RuntimeSQLException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record JdbcSchuelerRepository(Connection connection)
        implements SchuelerRepository {
    @Override
    public List<Person> findAll() {
        var sql = """
                select *
                from Schueler;""";
        try (var statement = connection.prepareStatement(sql)) {
            var resultSet = statement.executeQuery();
            List<Person> person = new ArrayList<>();

            while (resultSet.next()) {
                person.add(getSchuelerFromResultSet(resultSet));
            }
            return person;
        } catch (SQLException throwables) {
            throw new RuntimeSQLException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Person save(Person s) {
        var sql = """
                insert into Schueler(schueler_vorname , schueler_nachname , schueler_klasse , schueler_email)
                values( ? , ? , ? , ? );""";
        try (var statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, s.getFirstName());
            statement.setString(2, s.getLastName());
            statement.setString(3, s.getKlasse());
            statement.setString(4, s.getEMail());

            statement.executeUpdate();
            ResultSet generatedKey = statement.getGeneratedKeys();

            if (generatedKey.next()) {
                s.setId((int) generatedKey.getLong(1));
                return s;
            } else {
                throw new SQLException("Schueler konnte nicht gespeichert werden");
            }
        } catch (SQLException throwables) {
            throw new RuntimeSQLException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Person> findBySchuelerID(int schuelerID) {
        var sql = """
                select *
                from Schueler
                where schueler_id = ?;
                """;
        try (var statement = connection.prepareStatement(sql)) {
            statement.setInt(1, schuelerID);

            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getSchuelerFromResultSet(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException throwables) {
            throw new RuntimeSQLException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void saveAll(List<Person> s) {
        try {
            try {
                connection.setAutoCommit(false);
                for (Person person : s) {
                    this.save(person);
                }
                connection.commit();
            } catch (RuntimeSQLException e) {
                connection.rollback();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException sqlE) {
            throw new RuntimeSQLException(sqlE.getMessage(), sqlE.getCause());
        }
    }

    @Override
    public Optional<Person> update(Person s) {
        if (s.getId() == null) throw new IllegalArgumentException("ID darf fuer diese Methode nicht leer sein");
        var sql = """
                update Schueler
                set schueler_vorname = ?,
                schueler_nachname = ?,
                schueler_klasse = ?,
                schueler_email = ?
                where schueler_id = ?;""";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setString(1, s.getFirstName());
            statement.setString(2, s.getLastName());
            statement.setString(3, s.getKlasse());
            statement.setString(4, s.getEMail());
            statement.setInt(5, s.getId());

            statement.executeUpdate();
            return findBySchuelerID(s.getId());
        } catch (SQLException throwables) {
            throw new RuntimeSQLException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public boolean delete(Person s) {
        if (s.getId() == null) throw new IllegalArgumentException("ID des Schuelers darf nicht null sein");
        var sql = """
                delete from Schueler
                where schueler_id = ?;""";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setInt(1, s.getId());

            int update = statement.executeUpdate();
            return update == 1;
        } catch (SQLException throwables) {
            throw new RuntimeSQLException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void deleteAll() {
        String sql = " delete from Schueler ";
        try (var statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeSQLException(e.getMessage(), e.getCause());
        }

    }

    private Person getSchuelerFromResultSet(ResultSet set) throws SQLException {
        return new Person(set.getInt("schueler_id"),
                set.getString("schueler_vorname"),
                set.getString("schueler_nachname"),
                set.getString("schueler_klasse"),
                set.getString("schueler_email"));
    }
}