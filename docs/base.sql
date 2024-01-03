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

create table categorie_meuble(
    id serial primary key,
    nom varchar(200)
);

create table style_meuble(
    id serial primary key,
    nom varchar(200)
);

create table meuble(
    id serial primary key,
    nom varchar(200),
    id_style_meuble integer,
    id_categorie_meuble integer,
    description text,
    foreign key(id_style_meuble) references style_meuble(id),
    foreign key(id_categorie_meuble) references categorie_meuble(id)
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

create or replace view v_materiau as 
select m.*,tm.nom as nom_type_materiau from materiau m join type_materiau tm  on m.id_type_materiau=tm.id;

create or replace view v_meuble as
select m.*,sm.nom as nom_style_meuble,cm.nom as nom_categorie_meuble from meuble m 
join style_meuble sm on sm.id=m.id_style_meuble
join categorie_meuble cm on cm.id=m.id_categorie_meuble;


CREATE TABLE taille_meuble (
    id serial primary key,
    nom varchar (100)
);

CREATE TABLE formule_meuble (
    id serial primary key,
    id_meuble integer,
    id_taille_meuble integer,
    foreign key(id_meuble) references meuble(id),
    foreign key(id_taille_meuble) references taille_meuble(id)
);

create table detail_formule_meuble(
    id serial primary key,
    id_formule_meuble integer,
    id_materiau integer,
    quantite numeric,
    foreign key(id_formule_meuble) references formule_meuble(id),
    foreign key(id_materiau) references materiau(id)
);

-- Categorie meuble
INSERT INTO categorie_meuble(nom ) VALUES ( 'Table');
INSERT INTO categorie_meuble(nom ) VALUES ( 'Chaise');
INSERT INTO categorie_meuble(nom ) VALUES ( 'Canape');
INSERT INTO categorie_meuble(nom ) VALUES ( 'Armoire');
INSERT INTO categorie_meuble(nom ) VALUES ( 'Coffre');
INSERT INTO categorie_meuble(nom ) VALUES ( 'Coiffeuse');
INSERT INTO categorie_meuble(nom ) VALUES ( 'Biblotheque');
INSERT INTO categorie_meuble(nom ) VALUES ( 'Vitrine');

-- Style meuble
INSERT INTO style_meuble( nom ) VALUES ( 'Boheme');
INSERT INTO style_meuble( nom ) VALUES ( 'Scandinave');
INSERT INTO style_meuble( nom ) VALUES ( 'Contemporaine');

-- Type materiau
INSERT INTO type_materiau(nom ) VALUES ('bois');
INSERT INTO type_materiau(nom ) VALUES ('metal');
INSERT INTO type_materiau(nom ) VALUES ('plastique');
INSERT INTO type_materiau(nom ) VALUES ('verre');
INSERT INTO type_materiau(nom ) VALUES ('tissus');
INSERT INTO type_materiau(nom ) VALUES ('contreplaque');

-- Materiau
-- Bois
INSERT INTO materiau(nom, id_type_materiau, description) VALUES ('Chene', 1, 'Materiau durable et resistant');
INSERT INTO materiau(nom, id_type_materiau, description) VALUES ('Pin', 1, 'Bois leger utilise pour differents usages');
INSERT INTO materiau(nom, id_type_materiau, description) VALUES ('Erable', 1, 'Bois dur souvent utilise dans la fabrication de meubles');

-- Metal
INSERT INTO materiau(nom, id_type_materiau, description) VALUES ('Acier inoxydable', 2, 'Resistant a la corrosion et polyvalent');
INSERT INTO materiau(nom, id_type_materiau, description) VALUES ('Aluminium', 2, 'Leger et malleable, utilise dans diverses industries');
INSERT INTO materiau(nom, id_type_materiau, description) VALUES ('Cuivre', 2, 'Bonne conductivite thermique et electrique');

-- Plastique
INSERT INTO materiau(nom, id_type_materiau, description) VALUES ('Polyethylene', 3, 'Resistant et flexible, utilise dans les emballages et les produits chimiques');
INSERT INTO materiau(nom, id_type_materiau, description) VALUES ('Polycarbonate', 3, 'Transparent et resistant aux chocs, utilise dans les lunettes et les vitres de protection');
INSERT INTO materiau(nom, id_type_materiau, description) VALUES ('Polypropylene', 3, 'Resistant a la chaleur, utilise dans les tapis et les conteneurs');

-- Verre
INSERT INTO materiau(nom, id_type_materiau, description) VALUES ('Verre borosilicate', 4, 'Resistant aux temperatures elevees, utilise dans les ustensiles de cuisine');
INSERT INTO materiau(nom, id_type_materiau, description) VALUES ('Verre trempe', 4, 'Renforce pour plus de resistance, utilise dans les smartphones et les pare-brise de voiture');
INSERT INTO materiau(nom, id_type_materiau, description) VALUES ('Verre cristallin', 4, 'Transparent et brillant, utilise dans la verrerie fine');

-- Tissus
INSERT INTO materiau(nom, id_type_materiau, description) VALUES ('Coton', 5, 'Respirant et confortable, utilise dans les vetements');
INSERT INTO materiau(nom, id_type_materiau, description) VALUES ('Laine', 5, 'Isolant thermique naturel, utilise dans les tapis et les vetements d hiver');
INSERT INTO materiau(nom, id_type_materiau, description) VALUES ('Soie', 5, 'Legere et brillante, utilisee dans la mode haut de gamme');

-- Contreplaque
INSERT INTO materiau(nom, id_type_materiau, description) VALUES ('Peuplier', 6, 'Leger et resistant, utilise dans la construction legere et le mobilier');
INSERT INTO materiau(nom, id_type_materiau, description) VALUES ('Epinette', 6, 'Stable et peu coûteux, utilise dans la fabrication de meubles');
INSERT INTO materiau(nom, id_type_materiau, description) VALUES ('Eucalyptus', 6, 'Durabilite et resistance a l humidite, utilise dans les revetements de sol');

-- Materiau possible style meuble
-- Boheme style
INSERT INTO materiau_possible_style_meuble(id_style_meuble, id_materiau) VALUES (1, 1); 
INSERT INTO materiau_possible_style_meuble(id_style_meuble, id_materiau) VALUES (1, 4); 
INSERT INTO materiau_possible_style_meuble(id_style_meuble, id_materiau) VALUES (1, 7); 
INSERT INTO materiau_possible_style_meuble(id_style_meuble, id_materiau) VALUES (1, 10);
INSERT INTO materiau_possible_style_meuble(id_style_meuble, id_materiau) VALUES (1, 13);
INSERT INTO materiau_possible_style_meuble(id_style_meuble, id_materiau) VALUES (1, 16);

-- Scandinave style
INSERT INTO materiau_possible_style_meuble(id_style_meuble, id_materiau) VALUES (2, 2);
INSERT INTO materiau_possible_style_meuble(id_style_meuble, id_materiau) VALUES (2, 5); 
INSERT INTO materiau_possible_style_meuble(id_style_meuble, id_materiau) VALUES (2, 8); 
INSERT INTO materiau_possible_style_meuble(id_style_meuble, id_materiau) VALUES (2, 11); 
INSERT INTO materiau_possible_style_meuble(id_style_meuble, id_materiau) VALUES (2, 14); 
INSERT INTO materiau_possible_style_meuble(id_style_meuble, id_materiau) VALUES (2, 17); 

-- Contemporaine style
INSERT INTO materiau_possible_style_meuble(id_style_meuble, id_materiau) VALUES (3, 3);
INSERT INTO materiau_possible_style_meuble(id_style_meuble, id_materiau) VALUES (3, 6); 
INSERT INTO materiau_possible_style_meuble(id_style_meuble, id_materiau) VALUES (3, 9);
INSERT INTO materiau_possible_style_meuble(id_style_meuble, id_materiau) VALUES (3, 12); 
INSERT INTO materiau_possible_style_meuble(id_style_meuble, id_materiau) VALUES (3, 15);
INSERT INTO materiau_possible_style_meuble(id_style_meuble, id_materiau) VALUES (3, 18);


-- Meuble
-- Table category
INSERT INTO meuble(nom, id_style_meuble, id_categorie_meuble, description) VALUES ('Table en Chene', 1, 1, 'Belle table en bois de chene pour la salle a manger');
INSERT INTO meuble(nom, id_style_meuble, id_categorie_meuble, description) VALUES ('Table Basse Scandinave', 2, 1, 'Table basse au style scandinave pour le salon');
INSERT INTO meuble(nom, id_style_meuble, id_categorie_meuble, description) VALUES ('Table de Travail Contemporaine', 3, 1, 'Table de travail moderne pour le bureau');

-- Chaise category
INSERT INTO meuble(nom, id_style_meuble, id_categorie_meuble, description) VALUES ('Chaise en Pin', 2, 2, 'Chaise legere en pin pour la salle a manger');
INSERT INTO meuble(nom, id_style_meuble, id_categorie_meuble, description) VALUES ('Chaise Bois et Metal', 3, 2, 'Chaise contemporaine en bois et metal pour differents usages');

-- Canape category
INSERT INTO meuble(nom, id_style_meuble, id_categorie_meuble, description) VALUES ('Canape Boheme', 1, 3, 'Canape confortable au style boheme pour le salon');
INSERT INTO meuble(nom, id_style_meuble, id_categorie_meuble, description) VALUES ('Canape Scandinave Convertible', 2, 3, 'Canape convertible au design scandinave pour un usage multifonctionnel');

-- Armoire category
INSERT INTO meuble(nom, id_style_meuble, id_categorie_meuble, description) VALUES ('Armoire en erable', 3, 4, 'Armoire spacieuse en erable pour la chambre a coucher');
INSERT INTO meuble(nom, id_style_meuble, id_categorie_meuble, description) VALUES ('Armoire Contemporaine avec Miroir', 2, 4, 'Armoire moderne avec miroir pour ranger vos vetements');

-- Coffre category
INSERT INTO meuble(nom, id_style_meuble, id_categorie_meuble, description) VALUES ('Coffre en Peuplier', 1, 5, 'Coffre en bois de peuplier pour stocker differents objets');

-- Coiffeuse category
INSERT INTO meuble(nom, id_style_meuble, id_categorie_meuble, description) VALUES ('Coiffeuse Scandinave', 2, 6, 'Coiffeuse au style scandinave avec miroir pour la chambre');

-- Bibliotheque category
INSERT INTO meuble(nom, id_style_meuble, id_categorie_meuble, description) VALUES ('Bibliotheque Contemporaine', 3, 7, 'Bibliotheque moderne pour exposer vos livres et objets decoratifs');

-- Vitrine category
INSERT INTO meuble(nom, id_style_meuble, id_categorie_meuble, description) VALUES ('Vitrine en Verre Trempe', 2, 8, 'Vitrine elegante en verre trempe pour exposer vos collections');


-- Taille meuble
insert into taille_meuble (nom) values('petit');
insert into taille_meuble (nom) values('moyen');
insert into taille_meuble (nom) values('grand');


select fm.*,vm.nom as nom_meuble,vm.id_style_meuble,vm.id_categorie_meuble,vm.description,vm.nom_style_meuble,vm.nom_categorie_meuble,
dfm.quantite,tm.nom as nom_taille_meuble
from detail_formule_meuble dfm 
join formule_meuble fm on fm.id=dfm.id_formule_meuble 
join v_meuble vm on vm.id=fm.id_meuble
join taille_meuble tm on tm.id=fm.id_taille_meuble
where dfm.id_materiau = 1;


