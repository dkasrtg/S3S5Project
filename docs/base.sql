\c postgres;
DROP ROLE s3s5;
CREATE ROLE s3s5 WITH SUPERUSER LOGIN PASSWORD 's3s5';
DROP DATABASE s3s5;
CREATE DATABASE s3s5;
\c s3s5;


CREATE TABLE option_reference(
    id serial primary key,
    option varchar(20)
);

insert into option_reference(option) values('Option 1');
insert into option_reference(option) values('Option 2');


create table checkbox(
    id serial primary key,
    nom varchar(20)
);

insert into checkbox(nom) values('HTML');
insert into checkbox(nom) values('CSS');
insert into checkbox(nom) values('JS');

CREATE TABLE reference(
    id serial primary key,
    string varchar(20),
    date_simple date,
    heure_simple time,
    date_heure timestamp,
    entier integer,
    pas_entier numeric,
    id_option_reference integer,
    id_radio_reference integer,
    foreign key(id_option_reference) references option_reference(id),
    foreign key(id_radio_reference) references option_reference(id)
);


insert into reference(string,date_simple,heure_simple,date_heure,entier,pas_entier,id_option_reference,id_radio_reference) values('string','2022-02-02','05:00:00','2022-02-02 05:00:00',1,12243.334234,1,2);
insert into reference(string,date_simple,heure_simple,date_heure,entier,pas_entier,id_option_reference,id_radio_reference) values('string','2022-02-02','05:00:00','2022-02-02 05:00:00',1,12243.334234,2,1);

CREATE OR REPLACE VIEW v_reference as 
select r.*,o1.option as option,o2.option as radio from reference r join option_reference o1 on o1.id=r.id_option_reference join option_reference o2 on o2.id=r.id_radio_reference;


create table checkbox_reference(
    id serial primary key,
    id_reference integer,
    id_checkbox integer,
    foreign key(id_reference) references reference(id) on DELETE CASCADE on UPDATE CASCADE,
    foreign key(id_checkbox) references checkbox(id)
);

insert into checkbox_reference(id_reference,id_checkbox) values(1,1);
insert into checkbox_reference(id_reference,id_checkbox) values(1,2);
insert into checkbox_reference(id_reference,id_checkbox) values(2,2);
insert into checkbox_reference(id_reference,id_checkbox) values(2,3);

create or replace view v_checkbox_reference as
select cr.*,c.nom from checkbox_reference cr join checkbox c on c.id=cr.id_checkbox;

CREATE TABLE details_reference(
    id serial primary key,
    id_reference integer,
    details text,
    note numeric,
    foreign key(id_reference) references reference(id) ON DELETE CASCADE ON UPDATE CASCADE
);


insert into details_reference(id_reference,details,note) values(1,'avgvwFVvWVwvWVWvev',10.5);
insert into details_reference(id_reference,details,note) values(1,'avgvwFVvWVwvWVWvev',10.5);
insert into details_reference(id_reference,details,note) values(2,'avgvwFVvWVwvWVWvev',10.5);
insert into details_reference(id_reference,details,note) values(2,'avgvwFVvWVwvWVWvev',10.5);

-- Reference

create table unite_materiau(
    id serial primary key,
    nom varchar(200)
);

create table type_materiau(
    id serial primary key,
    nom varchar(200)
);

create table materiau(
    id serial primary key,
    nom varchar(200),
    id_type_materiau integer,
    description text,
    foreign key(id_type_materiau) references type_materiau(id)
);

create table dimension_materiau(
    id serial primary key,
    longueur numeric,
    largeur numeric,
    hauteur numeric
);

create table dimension_unite_possible_materiau(
    id serial primary key,
    id_materiau integer,
    id_dimension_materiau integer,
    id_unite_materiau integer,
    foreign key(id_materiau) references materiau(id),
    foreign key(id_dimension_materiau) references dimension_materiau(id),
    foreign key(id_unite_materiau) references unite_materiau(id)
);


create table categorie_meuble(
    id serial primary key,
    nom varchar(200)
);

create table style_meuble(
    id serial primary key,
    nom varchar(200)
);

create table lieu_meuble(
    id serial primary key,
    nom varchar(200)
);

create table meuble(
    id serial primary key,
    nom varchar(200),
    id_style_meuble integer,
    id_categorie_meuble integer,
    longueur numeric,
    largeur numeric,
    hauteur numeric,
    volume numeric,
    volume_materiau numeric,
    description text,
    foreign key(id_style_meuble) references style_meuble(id),
    foreign key(id_categorie_meuble) references categorie_meuble(id)
);

create table lieu_possible_meuble(
    id serial primary key,
    id_meuble integer,
    id_lieu_meuble integer,
    foreign key(id_meuble) references meuble(id),
    foreign key(id_lieu_meuble) references lieu_meuble(id)
);

create table composant_meuble(
    id serial primary key,
    nom varchar(200),
    id_meuble integer,
    id_type_materiau integer,
    volume numeric,
    foreign key(id_meuble) references meuble(id),
    foreign key(id_type_materiau) references type_materiau(id)
);
    
create table materiau_possible_style_meuble(
    id serial primary key,
    id_style_meuble integer,
    id_materiau integer,
    foreign key(id_style_meuble) references style_meuble(id),
    foreign key(id_materiau) references materiau(id)
);



create or replace view v_materiau_possible_style_meuble as 
select mpsm.*,m.nom as nom_materiau,m.id_type_materiau,tm.nom as nom_type_materiau from materiau_possible_style_meuble mpsm join materiau m on m.id=mpsm.id_materiau join type_materiau tm on tm.id=m.id_type_materiau;

create table stockage_materiau(
    id serial primary key,
    id_materiau integer,
    id_dimension_materiau integer,
    id_unite_materiau integer,
    quantite_stockage numeric,
    date_stockage date,
    prix_unitaire numeric,
    prix_total numeric,
    foreign key(id_materiau) references materiau(id),
    foreign key(id_dimension_materiau) references dimension_materiau(id),
    foreign key(id_unite_materiau) references unite_materiau(id)
);

create table fabrication_meuble(
    id serial primary key,
    id_meuble integer,
    date_fabrication date,
    quantite integer,
    cout_unitaire_fabrication numeric,
    cout_total_fabrication numeric,
    marge_beneficiaire numeric,
    prix_unitaire_de_vente numeric,
    foreign key(id_meuble) references meuble(id)
);

create table detail_fabrication_meuble(
    id serial primary key,
    id_fabrication_meuble integer,
    id_composant_meuble integer,
    id_stockage_materiau integer,
    quantite numeric,
    cout_unitaire numeric,
    cout_total numeric,
    foreign key(id_fabrication_meuble) references fabrication_meuble(id),
    foreign key(id_composant_meuble) references composant_meuble(id),
    foreign key(id_stockage_materiau) references stockage_materiau(id)
);

create or replace view v_materiau as 
select m.*,tm.nom as nom_type_materiau from materiau m join type_materiau tm  on m.id_type_materiau=tm.id;

create or replace view v_dimension_possible_materiau as
select dupm.id,dupm.id_materiau,dupm.id_dimension_materiau,dm.longueur,dm.largeur,dm.hauteur from dimension_unite_possible_materiau dupm join dimension_materiau dm on dm.id = dupm.id_dimension_materiau;

create or replace view v_unite_possible_materiau as
select distinct dupm.id_materiau,dupm.id_unite_materiau,um.nom as nom_unite_materiau from dimension_unite_possible_materiau dupm join unite_materiau um on um.id=dupm.id_unite_materiau;

create or replace view v_stockage_materiau as
select sm.*,m.nom as nom_materiau, m.id_type_materiau, tm.nom as nom_type_materiau, dm.longueur, dm.largeur, dm.hauteur, um.nom as nom_unite_materiau 
from stockage_materiau sm join materiau m on m.id=sm.id_materiau 
join dimension_materiau dm on dm.id=sm.id_dimension_materiau 
join unite_materiau um on um.id=sm.id_unite_materiau
join type_materiau tm on tm.id = m.id_type_materiau ;

create or replace view v_meuble as
select m.*,sm.nom as nom_style_meuble,cm.nom as nom_categorie_meuble from meuble m join style_meuble sm on sm.id=m.id_style_meuble join categorie_meuble cm on cm.id=m.id_categorie_meuble;

create or replace view v_lieu_possible_meuble as
select lpm.*,lm.nom from lieu_possible_meuble lpm join lieu_meuble lm on lpm.id_lieu_meuble=lm.id;

create or replace view v_composant_meuble as
select cm.*,tm.nom as nom_type_materiau from composant_meuble cm join type_materiau tm on tm.id=cm.id_type_materiau ;

INSERT INTO categorie_meuble( id, nom ) VALUES ( 1, 'Table');
INSERT INTO categorie_meuble( id, nom ) VALUES ( 2, 'Chaise');
INSERT INTO categorie_meuble( id, nom ) VALUES ( 3, 'Canape');
INSERT INTO categorie_meuble( id, nom ) VALUES ( 4, 'Armoire');
INSERT INTO categorie_meuble( id, nom ) VALUES ( 5, 'Coffre');
INSERT INTO categorie_meuble( id, nom ) VALUES ( 6, 'Coiffeuse');
INSERT INTO categorie_meuble( id, nom ) VALUES ( 7, 'Biblotheque');
INSERT INTO categorie_meuble( id, nom ) VALUES ( 8, 'Vitrine');
INSERT INTO dimension_materiau( id, longueur, largeur, hauteur ) VALUES ( 1, 1, 1, 1);
INSERT INTO dimension_materiau( id, longueur, largeur, hauteur ) VALUES ( 2, 20, 2, 1);
INSERT INTO lieu_meuble( id, nom ) VALUES ( 1, 'Cuisine');
INSERT INTO lieu_meuble( id, nom ) VALUES ( 2, 'Salle a manger');
INSERT INTO lieu_meuble( id, nom ) VALUES ( 3, 'Salon');
INSERT INTO lieu_meuble( id, nom ) VALUES ( 4, 'Chambre a coucher');
INSERT INTO style_meuble( id, nom ) VALUES ( 1, 'Boheme');
INSERT INTO style_meuble( id, nom ) VALUES ( 2, 'Scandinave');
INSERT INTO style_meuble( id, nom ) VALUES ( 3, 'Contemporaine');
INSERT INTO type_materiau( id, nom ) VALUES ( 1, 'bois');
INSERT INTO type_materiau( id, nom ) VALUES ( 2, 'metal');
INSERT INTO type_materiau( id, nom ) VALUES ( 3, 'plastique');
INSERT INTO type_materiau( id, nom ) VALUES ( 4, 'verre');
INSERT INTO type_materiau( id, nom ) VALUES ( 5, 'tissus');
INSERT INTO type_materiau( id, nom ) VALUES ( 6, 'contreplaque');
INSERT INTO unite_materiau( id, nom ) VALUES ( 1, 'planche');
INSERT INTO unite_materiau( id, nom ) VALUES ( 2, 'panneau');
INSERT INTO unite_materiau( id, nom ) VALUES ( 3, 'feuille');
INSERT INTO unite_materiau( id, nom ) VALUES ( 4, 'poteau');
INSERT INTO unite_materiau( id, nom ) VALUES ( 5, 'barre');
INSERT INTO unite_materiau( id, nom ) VALUES ( 6, 'tube');
INSERT INTO unite_materiau( id, nom ) VALUES ( 7, 'bobine');
INSERT INTO materiau( id, nom, id_type_materiau, description ) VALUES ( 1, 'Palissandre', 1, 'Bois cool non nul');
INSERT INTO materiau( id, nom, id_type_materiau, description ) VALUES ( 2, 'Ebene', 1, 'Bois mlay');
INSERT INTO materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 1, 2, 1);
INSERT INTO materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 2, 3, 2);
INSERT INTO meuble( id, nom, id_style_meuble, id_categorie_meuble, longueur, largeur, hauteur, volume, volume_materiau, description ) VALUES ( 1, 'Tabouret Tabou', 1, 1, 10, 10, 10, 1000, 100, 'wer');
INSERT INTO composant_meuble( id, nom, id_meuble, id_type_materiau, volume ) VALUES ( 1, 'Structure', 1, 1, 100);
INSERT INTO dimension_unite_possible_materiau( id, id_materiau, id_dimension_materiau, id_unite_materiau ) VALUES ( 1, 1, 1, 1);
INSERT INTO dimension_unite_possible_materiau( id, id_materiau, id_dimension_materiau, id_unite_materiau ) VALUES ( 2, 1, 2, 1);
INSERT INTO dimension_unite_possible_materiau( id, id_materiau, id_dimension_materiau, id_unite_materiau ) VALUES ( 3, 2, 1, 1);
INSERT INTO lieu_possible_meuble( id, id_meuble, id_lieu_meuble ) VALUES ( 1, 1, 1);

-- additionnals

create table duree_fabrication(
    id serial primary key,
    id_categorie_meuble integer,
    id_style_meuble integer,
    heure_par_volume numeric,
    foreign key(id_categorie_meuble) references categorie_meuble(id),
    foreign key(id_style_meuble) references style_meuble(id)
);

