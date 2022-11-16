--INSERT INTO public.korisnik(id, adresa, datum_rodjenja, email, ime, kor_ime, lozinka, poslednja_donacija, prezime, tip_korisnika) VALUES (0, 'Sindjeliceva 22 Becej', '2022-04-14', 'temp@gmail.com', 'zdravko', 'premijer', '$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq', '2022-11-22', 'krivokapic', 0);
--INSERT INTO public.korisnik(id, adresa, datum_rodjenja, email, ime, kor_ime, lozinka, poslednja_donacija, prezime, tip_korisnika) VALUES (1, 'Arsenija Carnojevica 1 Becej', '1996-04-14', 'arsen@gmail.com', 'mirjana', 'ludamira', '$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq', '2022-11-22', 'markovic', 1);
--INSERT INTO public.korisnik(id, adresa, datum_rodjenja, email, ime, kor_ime, lozinka, poslednja_donacija, prezime, tip_korisnika) VALUES (2, 'Nemanjina 11 Beograd', '2022-04-14', 'smajser@gmail.com', 'Slobodan', 'elpresidente', '$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq', '2022-11-22', 'milosevic', 2);
--INSERT INTO public.korisnik(id, adresa, datum_rodjenja, email, ime, kor_ime, lozinka, poslednja_donacija, prezime, tip_korisnika) VALUES (3, 'Svetozara Markovica 7 Nis', '2022-04-14', 'temp2@gmail.com', 'Milos', 'snp1389', '$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq', '2022-11-22', 'Markovic', 0);

--INSERT INTO public.regkorisnik(id, adresa, datum_rodjenja, email, ime, kor_ime, lozinka, poslednja_donacija, prezime, tip_korisnika, penali, poeni, kategorija_id) VALUES (0, 'Sindjeliceva 22 Becej', '2022-04-14', 'temp@gmail.com', 'zdravko', 'premijer', '$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq', '2022-11-22', 'krivokapic', 0,  11, 2, null);
--INSERT INTO public.regkorisnik(id, adresa, datum_rodjenja, email, ime, kor_ime, lozinka, poslednja_donacija, prezime, tip_korisnika, penali, poeni, kategorija_id) VALUES (1, 'Arsenija Carnojevica 1 Becej', '1996-04-14', 'arsen@gmail.com', 'mirjana', 'ludamira', '$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq', '2022-11-22', 'markovic', 1, 0, 0, null);
--INSERT INTO public.regkorisnik(id, adresa, datum_rodjenja, email, ime, kor_ime, lozinka, poslednja_donacija, prezime, tip_korisnika, penali, poeni, kategorija_id) VALUES (2, 'Nemanjina 11 Beograd', '2022-04-14', 'smajser@gmail.com', 'Slobodan', 'elpresidente', '$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq', '2022-11-22', 'milosevic', 2, 3, 3, null);
--INSERT INTO public.regkorisnik(id, adresa, datum_rodjenja, email, ime, kor_ime, lozinka, poslednja_donacija, prezime, tip_korisnika, penali, poeni, kategorija_id) VALUES (3, 'Svetozara Markovica 7 Nis', '2022-04-14', 'temp2@gmail.com', 'Milos', 'snp1389', '$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq', '2022-11-22', 'Markovic', 0, 3, 0, null);

--KATEGORIJE
INSERT INTO public.kategorija(naziv, poeni) VALUES ('PLATINA', 90);
INSERT INTO public.kategorija(naziv, poeni) VALUES ('ZLATO', 70);
INSERT INTO public.kategorija(naziv, poeni) VALUES ('SREBRO', 50);
INSERT INTO public.kategorija(naziv, poeni) VALUES ('BRONZA', 30);


--KORISNICI
INSERT INTO public.korisnik(adresa, datum_rodjenja, email, ime, kor_ime, lozinka, poslednja_donacija, prezime, status_naloga, tip_korisnika) VALUES ('Sindjeliceva 22 Becej', '2022-04-14', 'temp@gmail.com', 'zdravko', 'tempUsernamae', '$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq', '2022-11-22', 'Todovski', 0, 1);
INSERT INTO public.korisnik(adresa, datum_rodjenja, email, ime, kor_ime, lozinka, poslednja_donacija, prezime, status_naloga, tip_korisnika) VALUES ('Arsenija Carnojevica 1 Becej', '1996-04-14', 'arsen@gmail.com', 'Mara', 'marica', '$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq', '2022-11-22', 'Markovic', 1, 0);
INSERT INTO public.korisnik(adresa, datum_rodjenja, email, ime, kor_ime, lozinka, poslednja_donacija, prezime, status_naloga, tip_korisnika) VALUES ('Nemanjina 11 Beograd', '2022-04-14', 'smajser@gmail.com', 'Slobodan', 'slobo', '$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq', '2022-11-22', 'Misovic', 2, 2);
INSERT INTO public.korisnik(adresa, datum_rodjenja, email, ime, kor_ime, lozinka, poslednja_donacija, prezime, status_naloga, tip_korisnika) VALUES ('Svetozara Markovica 7 Nis', '2022-04-14', 'temp2@gmail.com', 'Milos', 'snp1389', '$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq', '2022-11-22', 'Markovic', 2, 1);
INSERT INTO public.korisnik(adresa, datum_rodjenja, email, ime, kor_ime, lozinka, poslednja_donacija, prezime, status_naloga, tip_korisnika) VALUES ('Omladinska 10 Koceljeva Srbija', '1996-05-22', 'npele96@gmail.com', 'Nikola', 'pele96', '$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq', '2022-01-01', 'Petkovic', 0, 1);
INSERT INTO public.korisnik(adresa, datum_rodjenja, email, ime, kor_ime, lozinka, poslednja_donacija, prezime, status_naloga, tip_korisnika) VALUES ('Lasla Gala 28 Novi Sad Srbija', '1999-03-21', 'acpele96@gmail.com', 'Nikolina', 'niPet', '$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq', '2022-01-01', 'Petko', 0, 1);

--REGKORISNICI
INSERT INTO public.regkorisnik(penali, poeni, id, kategorija_id, upitnik_id) VALUES (2, 3, 1, 1, NULL);
INSERT INTO public.regkorisnik(penali, poeni, id, kategorija_id, upitnik_id) VALUES (2, 3, 4, 1, NULL);
INSERT INTO public.regkorisnik(penali, poeni, id, kategorija_id, upitnik_id) VALUES (0, 3, 5, 2, NULL);
INSERT INTO public.regkorisnik(penali, poeni, id, kategorija_id, upitnik_id) VALUES (0, 3, 6, 2, NULL);



--RADNO VREME
INSERT INTO public.radno_vreme(kraj, kraj_pauze, pocetak, pocetak_pauze) VALUES ('2022-06-19T01:26:05', '08:00:00', '2022-06-19T01:26:05', '08:00:00');
INSERT INTO public.radno_vreme(kraj, kraj_pauze, pocetak, pocetak_pauze) VALUES ('2022-06-19T01:26:05', '08:00:00', '2022-06-19T01:26:05', '08:00:00');
INSERT INTO public.radno_vreme(kraj, kraj_pauze, pocetak, pocetak_pauze) VALUES ('2022-06-19T01:26:05', '08:00:00', '2022-06-19T01:26:05', '08:00:00');

--MEDICINSKI CENTRI
INSERT INTO public.med_centar(a_grupa, ab_grupa, adresa, b_grupa, naziv, nulta_grupa, ocena, opis, radnovreme_id) VALUES (5, 10, 'Mileticeva 55, Novi Sad, Srbija', 44, 'Medicinski centar Novi Sad', 4, 2, 'Strucno i prijatno osoblje...', 1);
INSERT INTO public.med_centar(a_grupa, ab_grupa, adresa, b_grupa, naziv, nulta_grupa, ocena, opis, radnovreme_id) VALUES (5, 10, 'Svetozara Markovica 1, Beograd, Srbija', 23, 'Opsta bolnica Beograd', 4, 2, 'Bolnica izgradjena 1966. godine u beogradskom naselju...', 1);
INSERT INTO public.med_centar(a_grupa, ab_grupa, adresa, b_grupa, naziv, nulta_grupa, ocena, opis, radnovreme_id) VALUES (5, 10, 'Stevana Sindjelica 1A Nis', 13, 'Niska gradska bolnica', 4, 2, 'Bolnica izgradjena u Nisu 1971. godine...', 2);
INSERT INTO public.med_centar(a_grupa, ab_grupa, adresa, b_grupa, naziv, nulta_grupa, ocena, opis, radnovreme_id) VALUES (5, 10, 'Milosa Crnjanskog, Subotica, Srbija', 6, 'Dom Zdravlja Subotica', 3, 50, 'Najstarija ambulanta u Subotici...', 1);
INSERT INTO public.med_centar(a_grupa, ab_grupa, adresa, b_grupa, naziv, nulta_grupa, ocena, opis, radnovreme_id) VALUES (0, 0, 'Nemanjina 8, Koceljeva, Srbija', 0, 'Dom Zdravlja Koceljeva', 0,1, 'Novi objekat poceo sa radom 1978.', 3);
INSERT INTO public.med_centar(a_grupa, ab_grupa, adresa, b_grupa, naziv, nulta_grupa, ocena, opis, radnovreme_id) VALUES (12, 12, 'Bulevar cara Lazara 77, Novi Sad, Srbija', 14, 'Dom Zdravlja Novi Sad', 50,5, 'tempOpis.', 3);
INSERT INTO public.med_centar(a_grupa, ab_grupa, adresa, b_grupa, naziv, nulta_grupa, ocena, opis, radnovreme_id) VALUES (12, 12, 'Vuka Karadžića 287, Cenej, Srbija', 14, 'Sluzba opste medicine Cenej', 50,5, 'loremIpsum', 2);
INSERT INTO public.med_centar(a_grupa, ab_grupa, adresa, b_grupa, naziv, nulta_grupa, ocena, opis, radnovreme_id) VALUES (12, 12, 'Zmaj Ognjena Vuka 19, Novi Sad, Srbija', 14, 'Ambulanta: Jovan Jovanovic Zmaj', 50,2.5, 'loremIpsum', 2);
INSERT INTO public.med_centar(a_grupa, ab_grupa, adresa, b_grupa, naziv, nulta_grupa, ocena, opis, radnovreme_id) VALUES (12, 12, 'Lasla Gala 28, Novi Sad, Srbija', 14, 'dom zdravlja Grbavica', 50,2.5, 'loremIpsum', 2);


--TERMINI
INSERT INTO public.termin(br_slobodnih_mesta, broj_mesta, kraj_termina, pocetak_termina, med_centar_id)	VALUES (3, 10, '2022-11-19T10:00:00', '2022-11-19T09:00:00', 1);
INSERT INTO public.termin(br_slobodnih_mesta, broj_mesta, kraj_termina, pocetak_termina, med_centar_id)	VALUES (5, 8, '2022-11-20T10:00:00', '2022-11-20T09:30:00', 1);
INSERT INTO public.termin(br_slobodnih_mesta, broj_mesta, kraj_termina, pocetak_termina, med_centar_id)	VALUES (4, 5, '2022-11-20T17:30:00', '2022-11-20T16:30:00', 2);
INSERT INTO public.termin(br_slobodnih_mesta, broj_mesta, kraj_termina, pocetak_termina, med_centar_id)	VALUES (0, 10, '2022-11-21T09:30:00', '2022-11-21T08:00:00', 3);
INSERT INTO public.termin(br_slobodnih_mesta, broj_mesta, kraj_termina, pocetak_termina, med_centar_id)	VALUES (0, 10, '2022-11-21T09:30:00', '2022-11-21T08:00:00', 4);
