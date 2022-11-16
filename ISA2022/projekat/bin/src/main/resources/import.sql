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
INSERT INTO public.korisnik(adresa, datum_rodjenja, email, ime, kor_ime, lozinka, poslednja_donacija, prezime, status_naloga, tip_korisnika) VALUES ('Sindjeliceva 22 Becej', '2022-04-14', 'temp@gmail.com', 'zdravko', 'premijer', '$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq', '2022-11-22', 'krivokapic', 0, 0);
INSERT INTO public.korisnik(adresa, datum_rodjenja, email, ime, kor_ime, lozinka, poslednja_donacija, prezime, status_naloga, tip_korisnika) VALUES ('Arsenija Carnojevica 1 Becej', '1996-04-14', 'arsen@gmail.com', 'mirjana', 'ludamira', '$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq', '2022-11-22', 'markovic', 1, 1);
INSERT INTO public.korisnik(adresa, datum_rodjenja, email, ime, kor_ime, lozinka, poslednja_donacija, prezime, status_naloga, tip_korisnika) VALUES ('Nemanjina 11 Beograd', '2022-04-14', 'smajser@gmail.com', 'Slobodan', 'elpresidente', '$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq', '2022-11-22', 'milosevic', 2, 2);
INSERT INTO public.korisnik(adresa, datum_rodjenja, email, ime, kor_ime, lozinka, poslednja_donacija, prezime, status_naloga, tip_korisnika) VALUES ('Svetozara Markovica 7 Nis', '2022-04-14', 'temp2@gmail.com', 'Milos', 'snp1389', '$2a$12$.RdHV2luFjgp5vR2LQSjWeLjU3WRzUEOmaqqsL51CD/L.5sPFoxoq', '2022-11-22', 'Markovic', 2, 0);

--RADNO VREME
INSERT INTO public.radno_vreme(kraj, kraj_pauze, pocetak, pocetak_pauze) VALUES ('2022-06-19T01:26:05', '08:00:00', '2022-06-19T01:26:05', '08:00:00');
INSERT INTO public.radno_vreme(kraj, kraj_pauze, pocetak, pocetak_pauze) VALUES ('2022-06-19T01:26:05', '08:00:00', '2022-06-19T01:26:05', '08:00:00');
INSERT INTO public.radno_vreme(kraj, kraj_pauze, pocetak, pocetak_pauze) VALUES ('2022-06-19T01:26:05', '08:00:00', '2022-06-19T01:26:05', '08:00:00');

--MEDICINSKI CENTRI
INSERT INTO public.med_centar(a_grupa, ab_grupa, adresa, b_grupa, naziv, nulta_grupa, ocena, opis, radnovreme_id) VALUES (5, 10, 'Mileticeva 55, Novi Sad, Srbija', 44, 'Medicinski centar Novi Sad', 4, 2, 'Strucno i prijatno osoblje...', 1);
INSERT INTO public.med_centar(a_grupa, ab_grupa, adresa, b_grupa, naziv, nulta_grupa, ocena, opis, radnovreme_id) VALUES (5, 10, 'Svetozara Markovica 1, Beograd, Srbija', 23, 'Opsta bolnica Beograd', 4, 2, 'Bolnica izgradjena 1966. godine u beogradskom naselju...', 1);
INSERT INTO public.med_centar(a_grupa, ab_grupa, adresa, b_grupa, naziv, nulta_grupa, ocena, opis, radnovreme_id) VALUES (5, 10, 'Stevana Sindjelica 1A Nis', 13, 'Niska gradska bolnica', 4, 2, 'Bolnica izgradjena u Nisu 1971. godine...', 2);
INSERT INTO public.med_centar(a_grupa, ab_grupa, adresa, b_grupa, naziv, nulta_grupa, ocena, opis, radnovreme_id) VALUES (5, 10, 'Milosa Crnjanskog, Subotica, Srbija', 6, 'Dom Zdravlja Subotica', 3, 50, 'Najstarija ambulanta u Subotici...', 1);


--REGKORISNICI
INSERT INTO public.regkorisnik(penali, poeni, id, kategorija_id, upitnik_id) VALUES (2, 3, 1, 1, NULL);
INSERT INTO public.regkorisnik(penali, poeni, id, kategorija_id, upitnik_id) VALUES (2, 3, 4, 1, NULL);