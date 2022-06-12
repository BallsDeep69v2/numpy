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