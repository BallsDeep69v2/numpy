package repository;

import domain.Ausleihe;
import domain.BuchExemplar;
import errorhandling.RuntimeSQLException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcAusleiheRepository implements AusleiheRepository {

    private final Connection connection;
    private final JdbcBuchExemplarRepository buchExemplarRepository;
    private final JdbcSchuelerRepository schuelerRepository;

    public JdbcAusleiheRepository(Connection connection) {
        this.connection = connection;
        buchExemplarRepository = new JdbcBuchExemplarRepository(connection);
        schuelerRepository = new JdbcSchuelerRepository(connection);
    }

    @Override
    public List<Ausleihe> findAll() {
        var sql = """
                select *
                from Ausleihe;""";
        try (var statement = connection.prepareStatement(sql)) {
            var resultSet = statement.executeQuery();
            List<Ausleihe> ausleihen = new ArrayList<>();

            while (resultSet.next()) {
                ausleihen.add(getAusleiheFromResultSet(resultSet));
            }
            return ausleihen;
        } catch (SQLException throwables) {
            throw new RuntimeSQLException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public List<Ausleihe> findAllPending() {
        var sql = """
                select *
                from Ausleihe
                where ausleihe_status = false;""";
        try (var statement = connection.prepareStatement(sql)) {
            var resultSet = statement.executeQuery();
            List<Ausleihe> ausleihen = new ArrayList<>();

            while (resultSet.next()) {
                ausleihen.add(getAusleiheFromResultSet(resultSet));
            }
            return ausleihen;
        } catch (SQLException throwables) {
            throw new RuntimeSQLException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Ausleihe> findPendingByExemplar(BuchExemplar buchExemplar) {
        var sql = """
                select *
                from Ausleihe
                where ausleihe_status = false
                and ausleihe_buchexemplar_id = ?;""";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setInt(1, buchExemplar.getId());

            var resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(getAusleiheFromResultSet(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException throwables) {
            throw new RuntimeSQLException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Ausleihe save(Ausleihe ausleihe) {
        if (findPendingByExemplar(ausleihe.getExemplar()).isPresent())
            throw new IllegalArgumentException("Dieses Buch ist bereits ausgeborgt");

        var sql = """
                insert into ausleihe
                values(?,?,?,?);""";
        try (var statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1,ausleihe.getExemplar().getId());
            statement.setInt(2,ausleihe.getAusleiher().getId());
            statement.setDate(3,Date.valueOf(ausleihe.getBeginDate()));
            statement.setBoolean(4,ausleihe.isStatus());

            statement.executeUpdate();
            ResultSet generatedKey = statement.getGeneratedKeys();

            if (generatedKey.next()) {
                return ausleihe;
            } else {
                throw new SQLException("Ausleihe konnte nicht gespeichert werden");
            }
        } catch (SQLException throwables) {
            throw new RuntimeSQLException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void update(Ausleihe ausleihe) {
        var sql = """
                update ausleihe
                set ausleihe_status = ?;""";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setBoolean(1,ausleihe.isStatus());

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throw new RuntimeSQLException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void delete(Ausleihe ausleihe) {
        var sql = """
                delete 
                from Ausleihe
                where ausleihe_buchexemplar_id = ? and ausleihe_schueler_id = ? and ausleihe_datum = ?;""";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setInt(1,ausleihe.getExemplar().getId());
            statement.setInt(2,ausleihe.getAusleiher().getId());
            statement.setDate(3, Date.valueOf(ausleihe.getBeginDate()));

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throw new RuntimeSQLException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void deleteAllBefore(LocalDate date) {
        var sql = """
                delete 
                from Ausleihe
                where ausleihe_status = true and ausleihe_datum < ?;""";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setDate(1, Date.valueOf(date));

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throw new RuntimeSQLException(throwables.getMessage(), throwables.getCause());
        }
    }

    private Ausleihe getAusleiheFromResultSet(ResultSet set) throws SQLException {
        return new Ausleihe(buchExemplarRepository.findById(set.getInt("ausleihe_buchexemplar_id")).orElseThrow(),
                schuelerRepository.findBySchuelerID(set.getInt("ausleihe_schueler_id")).orElseThrow(),
                set.getDate("ausleihe_datum").toLocalDate(),
                set.getBoolean("ausleihe_status"));
    }
}
