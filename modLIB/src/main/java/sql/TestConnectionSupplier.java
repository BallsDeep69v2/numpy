package sql;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestConnectionSupplier {

    @SneakyThrows
    private static void initialize() {
        Connection connection = DriverManager.getConnection("""
                jdbc:h2:mem:test;\
                """);
        PreparedStatement statement = connection.prepareStatement("""
                CREATE TABLE Buchtyp
                (
                --18 Zeichen reichen für einen ISBN
                --aber falls ein Buch keinen ISBN hat, wird das Feld mit einem beliebigen Namen beschrieben.
                --Jahr ist das Erscheinungsjahr
                	buchtyp_isbn  varchar(30) PRIMARY KEY\s
                	,buchtyp_titel Varchar(300) Not Null
                	,buchtyp_autor varchar(300) Not Null
                	,buchtyp_beschreibung varchar(900)
                    ,buchtyp_genre varchar(30)
                    ,buchtyp_jahr int
                    ,buchtyp_seitenzahl int check (buchtyp_seitenzahl >= 0)
                );


                CREATE TABLE Buchexemplar
                (
                	buchexemplar_id identity primary key
                	,buchexemplar_buchtyp_isbn varchar(30) Not Null references Buchtyp(buchtyp_isbn)  On delete No Action
                );


                CREATE TABLE Schueler
                (
                	schueler_id IDENTITY PRIMARY KEY
                	,schueler_vorname Varchar(300) Not Null
                	,schueler_nachname varchar(300) Not NUll
                	,schueler_klasse VARCHAR(30) not null
                    ,schueler_email VARCHAR(100) not null
                );


                CREATE TABLE Ausleihe
                (
                --ausleihe_datum ist das Datum, an dem das Buch ausgeborgt wurde
                --ausleihe_status ist 0, wenn das Buch noch nicht zurueckgegeben wurde, und 1, wenn es zurueckgegeben wurde
                	ausleihe_buchexemplar_id int not null references buchexemplar on delete cascade
                	,ausleihe_schueler_id int not null references Schueler	on delete cascade
                	,ausleihe_datum date not null
                    ,ausleihe_status bit not null
                    ,constraint ausleihe_pk primary key (ausleihe_buchexemplar_id, ausleihe_schueler_id, ausleihe_datum)
                );
                                
                insert into Schueler(schueler_vorname, schueler_nachname, schueler_klasse, schueler_email) values('Max', 'Mustermann', '4BHIF', 'm.mustermann@htlstp.at');
                insert into Schueler(schueler_vorname, schueler_nachname, schueler_klasse, schueler_email) values('Kavid', 'Doglbauer', '4BHIF', 'k.doglbauer@htlstp.at');
                insert into Schueler(schueler_vorname, schueler_nachname, schueler_klasse, schueler_email) values('Neimuraz', 'Tatroschvili', '4BHIF', 'n.tatroshvili@htlstp.at');
                insert into Schueler(schueler_vorname, schueler_nachname, schueler_klasse, schueler_email) values('Jhen', 'Cunbo', '4BHIF', 'c.jhen@htlstp.at');
                insert into Schueler(schueler_vorname, schueler_nachname, schueler_klasse, schueler_email) values('Saul', 'Pchöppl', '4BHIF', 's.pchoeppl@htlstp.at');
                                
                insert into Buchtyp(buchtyp_isbn, buchtyp_titel, buchtyp_autor) values('1', 'Faust 1', 'Goethe');
                insert into Buchtyp(buchtyp_isbn, buchtyp_titel, buchtyp_autor) values('2', 'Faust 2', 'Goethe');
                insert into Buchtyp(buchtyp_isbn, buchtyp_titel, buchtyp_autor) values('3', 'Die Räuber', 'Schiller');
                insert into Buchtyp(buchtyp_isbn, buchtyp_titel, buchtyp_autor) values('4', 'Die neuen Leiden des jungen Csambal', '"Wilhem Arthur Ferdinand Emanuel Tröstler"');
                insert into Buchtyp(buchtyp_isbn, buchtyp_titel, buchtyp_autor) values('5', 'Der Weg zum Ruhm', '"Wilhem Arthur Ferdinand Emanuel Tröstler"');
                                
                insert into BuchExemplar(buchexemplar_buchtyp_isbn) values('1');
                insert into BuchExemplar(buchexemplar_buchtyp_isbn) values('1');
                insert into BuchExemplar(buchexemplar_buchtyp_isbn) values('1');
                insert into BuchExemplar(buchexemplar_buchtyp_isbn) values('2');
                insert into BuchExemplar(buchexemplar_buchtyp_isbn) values('2');
                insert into BuchExemplar(buchexemplar_buchtyp_isbn) values('3');
                                
                insert into Ausleihe values(1, 1, '2022-02-02', false);
                insert into Ausleihe values(1, 2, '2022-02-02', true);
                """);

        statement.executeUpdate();
    }

    public Connection getConnectionWithTestData() throws SQLException {
        return DriverManager.getConnection("""
                jdbc:h2:mem:test;\
                """);
    }

    static {
        initialize();
    }
}
