insert into student (student_id, student_age, student_name) values (1, 21, 'marko');


/*Pacijent*/
insert into patient (Id_Pacijenta, Ime, Prezime, Jmbg, Telefon, Datum_rodjenja, Adresa)
values (1, 'Luka', 'Jovanovic', '2903996800005', '064/449-86-28', '29.03.1996.', 'Drage Spasic 7');
insert into patient (Id_Pacijenta, Ime, Prezime, Jmbg, Telefon, Datum_rodjenja, Adresa) 
values (2, 'Scepan', 'Scekic', '1234996800005', '064/449-99-28', '05.03.1966.', 'Lovcenska 12');
insert into patient (Id_Pacijenta, Ime, Prezime, Jmbg, Telefon, Datum_rodjenja, Adresa) 
values (3, 'Mitar', 'Miric', '0703996800005', '064/555-86-28', '06.03.1980.', 'Bulevar revolucije 5');

/*Anamneza*/
insert into physical_examination (physical_Examination_Id, Id_Pacijenta, Temperatura, Disajni_zvuk, Sumovi)
values (1, 1, 'povisena', 'Zvucno', 'Ima ih');

/*Fizikalni pregled*/



