package repository;

import domain.BuchTyp;
import errorhandling.RuntimeSQLException;
import repository.BuchTypRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public record JdbcBuchTypRepository(Connection connection) implements BuchTypRepository {

    @Override
    public List<BuchTyp> findAll() {
        var sql = """
                select *
                from Buchtyp;""";
        try (var statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            var resultSet = statement.executeQuery();
            List<BuchTyp> buchtypen = new ArrayList<>();

            while(resultSet.next()){
                buchtypen.add(getBuchTypFromResultSet(resultSet));
            }
            return buchtypen;
        } catch (SQLException throwables) {
            throw new RuntimeSQLException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<BuchTyp> findByISBN(String isbn) {
        Objects.requireNonNull(isbn);
        var sql = """
                select *
                from BuchTyp
                where buchtyp_isbn = ?;
                """;
        try (var statement = connection.prepareStatement(sql)) {
            statement.setString(1, isbn);

            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getBuchTypFromResultSet(resultSet));
            }else{
                return Optional.empty();
            }
        } catch (SQLException throwables) {
            throw new RuntimeSQLException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public BuchTyp save(BuchTyp buchTyp) {
        Objects.requireNonNull(buchTyp);
        var sql = """
                insert into Buchtyp
                values(?,?,?,?,?,?,?);""";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setString(1, buchTyp.getIsbn());
            statement.setString(2, buchTyp.getTitle());
            statement.setString(3, buchTyp.getAuthor());
            if (buchTyp.getDescription() == null) {
                statement.setNull(4, Types.NULL);
            } else {
                statement.setString(4, buchTyp.getDescription());
            }
            if (buchTyp.getGenre() == null) {
                statement.setNull(5, Types.NULL);
            } else {
                statement.setString(5, buchTyp.getGenre());
            }
            if (buchTyp.getYear() == null) {
                statement.setNull(6, Types.NULL);
            } else {
                statement.setInt(6, buchTyp.getYear());
            }
            if (buchTyp.getNumberOfPages() == null) {
                statement.setNull(7, Types.NULL);
            } else {
                statement.setInt(7, buchTyp.getNumberOfPages());
            }

            int update = statement.executeUpdate();

            if (update == 1) {
                return buchTyp;
            } else {
                throw new SQLException("Buchtyp konnte nicht gespeichert werden");
            }
        } catch (SQLException throwables) {
            throw new RuntimeSQLException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<BuchTyp> update(BuchTyp buchTyp) {
        Objects.requireNonNull(buchTyp);
        var sql = """
                update buchtyp
                set buchtyp_titel = ?,
                buchtyp_autor = ?,
                buchtyp_beschreibung = ?,
                buchtyp_genre = ?,
                buchtyp_jahr = ?,
                buchtyp_seitenzahl = ?,

                where buchtyp_isbn = ?;""";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setString(1, buchTyp.getTitle());
            statement.setString(2, buchTyp.getAuthor());
            if (buchTyp.getDescription() == null) {
                statement.setNull(3, Types.NULL);
            } else {
                statement.setString(3, buchTyp.getDescription());
            }
            if (buchTyp.getGenre() == null) {
                statement.setNull(4, Types.NULL);
            } else {
                statement.setString(4, buchTyp.getGenre());
            }
            if (buchTyp.getYear() == null) {
                statement.setNull(5, Types.NULL);
            } else {
                statement.setInt(5, buchTyp.getYear());
            }
            if (buchTyp.getNumberOfPages() == null) {
                statement.setNull(6, Types.NULL);
            } else {
                statement.setInt(6, buchTyp.getNumberOfPages());
            }
            statement.setString(7, buchTyp.getIsbn());

            statement.executeUpdate();
            return findByISBN(buchTyp.getIsbn());
        } catch (SQLException throwables) {
            throw new RuntimeSQLException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public boolean delete(BuchTyp buchTyp) {
        var sql = """
                delete from Buchtyp
                where buchtyp_isbn = ?;""";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setString(1, buchTyp.getIsbn());

            int update = statement.executeUpdate();
            return update == 1;
        } catch (SQLException throwables) {
            throw new RuntimeSQLException(throwables.getMessage(), throwables.getCause());
        }
    }

    private BuchTyp getBuchTypFromResultSet(ResultSet set) throws SQLException {
        return new BuchTyp(set.getString("buchtyp_isbn"),
                set.getString("buchtyp_titel"),
                set.getString("buchtyp_autor"),
                set.getString("buchtyp_beschreibung"),
                set.getString("buchtyp_genre"),
                set.getInt("buchtyp_jahr"),
                set.getInt("buchtyp_seitenzahl"));
    }
}
