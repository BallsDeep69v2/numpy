use modLIB

delete from Ausleihe
delete from BuchExemplar
delete from Person
delete from Buchtyp

insert into Person(person_vorname, person_nachname, person_klasse, person_email) values('Max', 'Mustermann', '2BHIF', 'm.mustermann@htlstp.at');
insert into Person(person_vorname, person_nachname, person_klasse, person_email) values('David', 'Koglbauer', '4BHIF', 'd.koglbauer@htlstp.at');
insert into Person(person_vorname, person_nachname, person_klasse, person_email) values('Teimuraz', 'Natroschvili', '4BHIF', 't.natroschwili@htlstp.at');
insert into Person(person_vorname, person_nachname, person_klasse, person_email) values('Chen', 'Junbo', '4BHIF', 'j.chen@htlstp.at');
insert into Person(person_vorname, person_nachname, person_klasse, person_email) values('Paul', 'Schöppl', '4BHIF', 'p.schoeppl@htlstp.at');

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

insert into Ausleihe values(1, 1, '2022-02-02', 0);
insert into Ausleihe values(1, 2, '2022-02-02', 1);