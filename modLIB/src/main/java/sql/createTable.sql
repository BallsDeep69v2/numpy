use modLIB

drop table if exists Ausleihe
drop table if exists Buchexemplar
drop table if exists Person
drop table if exists Buchtyp

CREATE TABLE Buchtyp
(
    buchtyp_isbn         varchar(13) PRIMARY KEY,
    buchtyp_titel        Varchar(100) Not Null,
    buchtyp_autor        varchar(100) Not Null,
    buchtyp_beschreibung varchar(900),
    buchtyp_genre        varchar(30),
    buchtyp_jahr         int,
    buchtyp_seitenzahl   int check (buchtyp_seitenzahl >= 0)
);

CREATE TABLE Buchexemplar
(
    buchexemplar_id int identity(1,1) primary key,
    buchexemplar_buchtyp_isbn varchar(13) Not Null references Buchtyp (buchtyp_isbn) On delete No Action
);

CREATE TABLE Person
(
    person_id int identity(1,1) primary key,
    person_vorname  Varchar(300) Not Null,
    person_nachname varchar(300) Not NUll,
    person_klasse   VARCHAR(30)  not null,
    person_email    VARCHAR(100) not null
);

CREATE TABLE Ausleihe
(
    ausleihe_buchexemplar_id int  not null references buchexemplar on delete cascade,
    ausleihe_schueler_id     int  not null references Person on delete cascade,
    ausleihe_datum           date not null,
    ausleihe_status          bit  not null,
    constraint ausleihe_pk primary key (ausleihe_buchexemplar_id, ausleihe_schueler_id, ausleihe_datum)
);