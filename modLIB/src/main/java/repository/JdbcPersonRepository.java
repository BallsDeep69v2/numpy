package repository;

import domain.Person;
import errorhandling.RuntimeSQLException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

public record JdbcPersonRepository(Connection connection) implements PersonRepository {
    @Override
    public List<Person> findAll() {
        var sql = """
                select *
                from Person;""";
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
                insert into Person(person_vorname , person_nachname , person_klasse , person_email)
                values( ? , ? , ? , ? );""";
        try (var statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, s.getFirstName());
            statement.setString(2, s.getLastName());
            statement.setString(3, s.getSchoolClass());
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
                from Person
                where person_id = ?;
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
                update Person
                set person_vorname = ?,
                person_nachname = ?,
                person_klasse = ?,
                person_email = ?
                where person_id = ?;""";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setString(1, s.getFirstName());
            statement.setString(2, s.getLastName());
            statement.setString(3, s.getSchoolClass());
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
                delete from Person
                where person_id = ?;""";
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
        String sql = " delete from Person ";
        try (var statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeSQLException(e.getMessage(), e.getCause());
        }

    }

    public Set<String> getAllSchoolClasses() {
        var sql = """
                select distinct person_klasse
                from Person;""";
        try (var statement = connection.prepareStatement(sql)) {
            var resultSet = statement.executeQuery();
            Set<String> classes = new TreeSet<>();

            while (resultSet.next()) {
                classes.add(resultSet.getString(1));
            }
            return classes;
        } catch (SQLException throwables) {
            throw new RuntimeSQLException(throwables.getMessage(), throwables.getCause());
        }
    }

    private Person getSchuelerFromResultSet(ResultSet set) throws SQLException {
        return new Person(set.getInt("person_id"), set.getString("person_vorname"), set.getString("person_nachname"), set.getString("person_klasse"), set.getString("person_email"));
    }
}