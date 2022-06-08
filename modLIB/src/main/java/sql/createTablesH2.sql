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
	,ausleihe_schueler_id int not null references person	on delete cascade
	,ausleihe_datum date not null
    ,ausleihe_status bit not null
    ,constraint ausleihe_pk primary key (ausleihe_buchexemplar_id, ausleihe_schueler_id, ausleihe_datum)
);