CREATE TABLE Buchtyp
(
--18 Zeichen reichen für einen ISBN
--aber falls ein Buch keinen ISBN hat, wird das Feld mit einem beliebigen Namen beschrieben.
--Jahr ist das Erscheinungsjahr
	buchtyp_isbn  varchar(30) PRIMARY KEY 
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
	,ausleihe_schueler_id int not null references schueler	on delete cascade
	,ausleihe_datum date not null
    ,ausleihe_status bit not null
    ,constraint ausleihe_pk primary key (ausleihe_buchexemplar_id, ausleihe_schueler_id, ausleihe_datum)
);


INSERT INTO Schueler VALUES (123, 'Simon', 'Schiller', 2011);
INSERT INTO Schueler VALUES (333, 'Fabian', 'Kirschbaum', 2020);
INSERT INTO Schueler VALUES (567, 'Michael', 'Reiter', 2017);
INSERT INTO Schueler VALUES (987, 'Daniel', 'Mayer', 2018);

INSERT INTO Buchtyp VALUES ('111111111', 'Datenbanken 1', 'Max Mustermann',4);
INSERT INTO Buchtyp VALUES ('222222222', 'Programmieren 1', 'Paul Pfiel',2);
INSERT INTO Buchtyp VALUES ('333333333', 'Wie man lebt', 'Max Lackner',2);
INSERT INTO Buchtyp VALUES ('444444444', 'Wie man isst', 'Robin Karner',3);


INSERT INTO Buchexemplar (buchexemplar_verschollen, buchexemplar_buchtyp_isbn) VALUES (0, '111111111');
INSERT INTO Buchexemplar (buchexemplar_verschollen, buchexemplar_buchtyp_isbn) VALUES (0, '111111111');
INSERT INTO Buchexemplar (buchexemplar_verschollen, buchexemplar_buchtyp_isbn) VALUES (1, '222222222');
INSERT INTO Buchexemplar (buchexemplar_verschollen, buchexemplar_buchtyp_isbn) VALUES (0, '222222222');