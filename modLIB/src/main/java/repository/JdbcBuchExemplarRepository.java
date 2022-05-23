package repository;

import domain.BuchExemplar;
import domain.BuchTyp;
import errorhandling.RuntimeSQLException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcBuchExemplarRepository implements BuchExemplarRepository {

    private Connection connection;
    private JdbcBuchTypRepository buchTypRepository;

    public JdbcBuchExemplarRepository(Connection connection){
        this.connection = connection;
        buchTypRepository = new JdbcBuchTypRepository(connection);
    }

    @Override
    public List<BuchExemplar> findAll() {
        var sql = """
                select *
                from BuchExemplar;""";
        try (var statement = connection.prepareStatement(sql)) {
            var resultSet = statement.executeQuery();
            List<BuchExemplar> exemplare = new ArrayList<>();

            while(resultSet.next()){
                exemplare.add(getBuchExemplarFromResultSet(resultSet));
            }
            return exemplare;
        } catch (SQLException throwables) {
            throw new RuntimeSQLException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public List<BuchExemplar> findByBuchTyp(BuchTyp buchTyp) {
        var sql = """
                select *
                from BuchExemplar
                where buchexemplar_buchtyp_isbn = ?;""";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setString(1, buchTyp.getIsbn());
            var resultSet = statement.executeQuery();
            List<BuchExemplar> exemplare = new ArrayList<>();

            while(resultSet.next()){
                exemplare.add(getBuchExemplarFromResultSet(resultSet));
            }
            return exemplare;
        } catch (SQLException throwables) {
            throw new RuntimeSQLException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public BuchExemplar save(BuchExemplar buchExemplar) {
        var sql = """
                insert into BuchExemplar
                values(?);""";
        try (var statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, buchExemplar.getTyp().getIsbn());

            statement.executeUpdate();
            ResultSet generatedKey = statement.getGeneratedKeys();

            if (generatedKey.next()) {
                buchExemplar.setId((int)generatedKey.getLong(1));
                return buchExemplar;
            } else {
                throw new SQLException("Buchexemplar konnte nicht gespeichert werden");
            }
        } catch (SQLException throwables) {
            throw new RuntimeSQLException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<BuchExemplar> findById(int id) {
        var sql = """
                select *
                from BuchExemplar
                where buchexemplar_id = ?;
                """;
        try (var statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getBuchExemplarFromResultSet(resultSet));
            }else{
                return Optional.empty();
            }
        } catch (SQLException throwables) {
            throw new RuntimeSQLException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public boolean saveAll(List<BuchExemplar> buchExemplar) {
        try{
            try {
                connection.setAutoCommit(false);
                List<BuchExemplar> exemplare = new ArrayList<>();
                for (BuchExemplar b : buchExemplar) {
                    this.save(b);
                }
                connection.commit();
                return true;
            } catch (RuntimeSQLException e) {
                connection.rollback();
                return false;
            } finally {
                connection.setAutoCommit(true);
            }
        }catch (SQLException sqlE){
            throw new RuntimeSQLException(sqlE.getMessage(), sqlE.getCause());
        }
    }

    @Override
    public boolean delete(BuchExemplar buchExemplar) {
        var sql = """
                delete from Buchexemplar
                where buchExemplar_id = ?;""";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setInt(1, buchExemplar.getId());

            int update = statement.executeUpdate();
            return update == 1;
        } catch (SQLException throwables) {
            throw new RuntimeSQLException(throwables.getMessage(), throwables.getCause());
        }
    }

    private BuchExemplar getBuchExemplarFromResultSet(ResultSet set) throws SQLException {
        return new BuchExemplar(set.getInt("buchexemplar_id"),
                buchTypRepository.findByISBN(set.getString("buchexemplar_buchtyp_isbn")).orElseThrow());
    }
}
