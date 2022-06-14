use modLIB

drop table if exists Ausleihe
drop table if exists Buchexemplar
drop table if exists Person
drop table if exists Buchtyp

CREATE TABLE Buchtyp
(
    buchtyp_isbn         numeric(13, 0) PRIMARY KEY,
    buchtyp_titel        nVarchar(100) Not Null,
    buchtyp_autor        nvarchar(100) Not Null,
    buchtyp_beschreibung nvarchar(900),
    buchtyp_genre        nvarchar(30),
    buchtyp_jahr         int,
    buchtyp_seitenzahl   int check (buchtyp_seitenzahl >= 0)
);

CREATE TABLE Buchexemplar
(
    buchexemplar_id           numeric(2, 0),
    buchexemplar_buchtyp_isbn numeric(13, 0) Not Null references Buchtyp (buchtyp_isbn) On delete No Action,
    constraint buchexemplar_pk primary key (buchexemplar_id, buchexemplar_buchtyp_isbn)

);

CREATE TABLE Person
(
    person_id       int identity (1,1) primary key,
    person_vorname  nvarchar(300) Not Null,
    person_nachname nvarchar(300) Not NUll,
    person_klasse   nvarchar(30)  not null,
    person_email    nvarchar(100)
);

CREATE TABLE Ausleihe
(
    ausleihe_buchexemplar_id   numeric(2, 0)  not null,
    ausleihe_buchexemplar_isbn numeric(13, 0) not null,
    ausleihe_person_id         int            not null references Person on delete cascade,
    ausleihe_datum             date           not null,
    ausleihe_status            bit            not null,
    constraint buchtyp_exemplar_fk foreign key (ausleihe_buchexemplar_id, ausleihe_buchexemplar_isbn) references Buchexemplar (buchexemplar_id, buchexemplar_buchtyp_isbn) on delete cascade,
    constraint ausleihe_pk primary key (ausleihe_buchexemplar_id, ausleihe_buchexemplar_isbn, ausleihe_person_id,
                                        ausleihe_datum)
);