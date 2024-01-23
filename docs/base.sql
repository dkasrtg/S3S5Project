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
select mpsm.*,m.nom as nom_materiau,m.id_type_materiau,tm.nom as nom_type_materiau 
from materiau_possible_style_meuble mpsm 
join materiau m on m.id=mpsm.id_materiau 
join type_materiau tm on tm.id=m.id_type_materiau;

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

create table client(
    id serial primary key,
    nom varchar(200),
    prenom varchar(200),
    telephone varchar(30)
);

create table vente_meuble(
    id serial primary key,
    date_vente timestamp,
    id_client integer,
    prix_ht numeric,
    remise numeric,
    taxe numeric,
    prix_ttc numeric,
    foreign key (id_client) references client(id)
);

create table detail_vente_meuble(
    id serial primary key,
    id_vente_meuble integer,
    id_formule_meuble integer,
    quantite numeric,
    prix_unitaire numeric,
    remise numeric,
    prix_unitaire_avec_remise numeric,
    prix_total numeric,
    foreign key(id_formule_meuble) references formule_meuble(id)
);

drop table mouvement_meuble cascade;
create table mouvement_meuble(
    id serial primary key,
    date_mouvement timestamp,
    id_formule_meuble integer,
    quantite numeric,
    type_mouvement integer,
    id_mouvement_mere integer,
    total_materiaux numeric,
    total_salaires numeric,
    prix_total numeric,
    prix_unitaire numeric,
    id_detail_vente_meuble integer,
    description varchar(200),
    foreign key(id_formule_meuble) references formule_meuble(id)
);

create table mouvement_materiau(
    id serial primary key,
    date_mouvement timestamp,
    id_materiau integer,
    quantite numeric,
    prix_unitaire numeric,
    type_mouvement integer,
    id_mouvement_mere integer,
    description varchar(200),
    id_mouvement_meuble integer,
    foreign key(id_materiau) references materiau(id)
);

create table employe(
    id serial primary key,
    nom varchar(200)
);

create table salaire_employe(
    id serial primary key,
    id_employe integer,
    date_debut timestamp,
    date_fin timestamp,
    valeur numeric,
    foreign key(id_employe) references employe(id)
);

create table detail_employe_meuble(
    id serial primary key,
    id_formule_meuble integer,
    id_employe integer,
    nombre integer,
    duree numeric,
    foreign key(id_formule_meuble) references formule_meuble(id),
    foreign key(id_employe) references employe(id)
);

create table prix_de_vente_meuble(
    id serial primary key,
    id_formule_meuble integer,
    date_debut timestamp,
    date_fin timestamp,
    valeur numeric,
    foreign key(id_formule_meuble) references formule_meuble(id)
);

create table utilisation_employe(
    id serial primary key,
    id_mouvement_meuble integer,
    date_utilisation timestamp,
    id_employe integer,
    nombre integer,
    duree_utilisation numeric,
    salaire_unitaire numeric,
    salaire_total numeric,
    description varchar(200),
    foreign key(id_employe) references employe(id)
);



-- Employe
INSERT INTO employe (nom) VALUES
    ('Menuisier'),
    ('Assembleur'),
    ('Operateur de machine'),
    ('Technicien de bois'),
    ('Technicien en finition');

INSERT INTO salaire_employe(id_employe,date_debut,date_fin,valeur) values
    (1,'01-01-2023 00:00','12-12-9999 23:59',30000),
    (2,'01-01-2023 00:00','12-12-9999 23:59',10000),
    (3,'01-01-2023 00:00','12-12-9999 23:59',20000),
    (4,'01-01-2023 00:00','12-12-9999 23:59',40000),
    (5,'01-01-2023 00:00','12-12-9999 23:59',50000);

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

create or replace view v_meuble_contenant_materiau as 
select fm.*,vm.nom as nom_meuble,vm.id_style_meuble,vm.id_categorie_meuble,vm.nom_style_meuble,vm.nom_categorie_meuble,
dfm.quantite,dfm.id_materiau,tm.nom as nom_taille_meuble
from detail_formule_meuble dfm
join formule_meuble fm on fm.id=dfm.id_formule_meuble 
join v_meuble vm on vm.id=fm.id_meuble
join taille_meuble tm on tm.id=fm.id_taille_meuble;

DROP view v_mouvement_materiau;
create or replace view v_mouvement_materiau as 
select mm.*,m.id_type_materiau ,m.nom as nom_materiau,tm.nom as nom_type_materiau,mm.prix_unitaire*mm.quantite as prix_total 
from mouvement_materiau mm join materiau m on m.id=mm.id_materiau
join type_materiau tm on tm.id=m.id_type_materiau
order by mm.date_mouvement desc
;

select * from v_mouvement_materiau where type_mouvement=1 and date_mouvement>='' and date_mouvement<='';



select * from mouvement_materiau where type_mouvement=1 and date_mouvement<='02-01-2024 10:00';
select * from mouvement_materiau where type_mouvement=-1 and date_mouvement<='02-01-2024 10:00';


create or replace view v_materiau_restant as
select * from (
select id,date_mouvement,id_materiau,q_e-t_q_s as quantite, p_e as prix_unitaire from 
(select id,date_mouvement,id_materiau,q_e,p_e,sum(q_s) as t_q_s from 
(select mm_e.id,mm_e.date_mouvement,mm_e.id_materiau,mm_e.quantite as q_e,mm_e.prix_unitaire as p_e,
coalesce(mm_s.quantite,0) as q_s
from 
(select id,date_mouvement,id_materiau,quantite,prix_unitaire from mouvement_materiau where type_mouvement=1) as mm_e 
left join 
(select id_mouvement_mere,quantite from mouvement_materiau where type_mouvement=-1) as mm_s 
on mm_e.id=mm_s.id_mouvement_mere) 
as q1
group by id,date_mouvement,id_materiau,q_e,p_e )
as q2
order by date_mouvement asc)
as q3
where quantite>0
;



DROP view v_mouvement_meuble;
create or replace view v_mouvement_meuble as 
select 
mm.*,fm.id_taille_meuble,fm.id_meuble,
tm.nom as nom_taille_meuble,
vm.nom as nom_meuble,vm.id_style_meuble,vm.id_categorie_meuble,vm.nom_style_meuble,vm.nom_categorie_meuble
from mouvement_meuble mm 
join formule_meuble fm on mm.id_formule_meuble=fm.id
join v_meuble vm on vm.id=fm.id_meuble
join taille_meuble tm on tm.id = fm.id_taille_meuble
order by mm.date_mouvement desc
;


create or replace view v_detail_formule_meuble as 
select dfm.*,m.nom as nom_materiau
from detail_formule_meuble dfm
join materiau m on m.id=dfm.id_materiau
;


-- reste total materiau

select v_materiau.id,v_materiau.nom,v_materiau.id_type_materiau,v_materiau.nom_type_materiau,
coalesce(q7.quantite_restant,0) as quantite_restant,
coalesce(q7.prix_total,0) as prix_total,
coalesce(q7.prix_unitaire_moyen,0) as prix_unitaire_moyen
from v_materiau
left join 
(select id_materiau,q_r as quantite_restant,p_r as prix_total,p_r/q_r as prix_unitaire_moyen from (
select id_materiau,sum(q_r) as q_r,sum(p_r) as p_r from (
select id,id_materiau,q_r,q_r*prix_unitaire as p_r from (
select id,id_materiau,prix_unitaire,q_e-q_s as q_r from (
select id,id_materiau,prix_unitaire,q_e,sum(q_s) as q_s from (
select id,id_materiau,prix_unitaire,q_e,coalesce(q_s,0) as q_s from (
select mm_e.id,mm_e.id_materiau,mm_e.quantite as q_e ,mm_e.prix_unitaire, mm_s.quantite as q_s from
(select * from mouvement_materiau where date_mouvement<='02-02-2024 00:00' and type_mouvement=1) as mm_e
left join 
(select * from mouvement_materiau where date_mouvement<='02-02-2024 00:00' and type_mouvement=-1) as mm_s
on mm_e.id = mm_s.id_mouvement_mere)
as q1)
as q2
group by id,id_materiau,prix_unitaire,q_e)
as q3)
as q4)
as q5
group by id_materiau)
as q6)
as q7
on v_materiau.id=q7.id_materiau
;


create or replace view v_salaire_employe as 
select se.*,e.nom as nom_employe from salaire_employe se 
join employe e on e.id=se.id_employe
;

drop view v_prix_de_vente_meuble;
create or replace view v_prix_de_vente_meuble as
select
pdvm.id,pdvm.date_debut,pdvm.date_fin,pdvm.valeur,fm.id as id_formule_meuble,fm.id_meuble,fm.id_taille_meuble,m.nom as nom_meuble,tm.nom as nom_taille_meuble 
from prix_de_vente_meuble pdvm
join formule_meuble fm on fm.id=pdvm.id_formule_meuble
join meuble m on m.id=fm.id_meuble
join taille_meuble tm on tm.id=fm.id_taille_meuble
;

drop view v_benefice_meuble;
create or replace view v_benefice_meuble as
select
q1.id_formule_meuble,q1.id_meuble,q1.id_taille_meuble,q1.total_materiaux,q2.total_salaires,q3.prix_de_vente,
m.nom as nom_meuble,tm.nom as nom_taille_meuble,
(q1.total_materiaux+q2.total_salaires) as prix_de_revient,
(q3.prix_de_vente-(q1.total_materiaux+q2.total_salaires)) as benefice
from 

(select q1.id_formule_meuble,fm.id_meuble,fm.id_taille_meuble,q1.total_materiaux
from formule_meuble fm
join 
(select dfm.id_formule_meuble,sum(dfm.quantite*p.prix_unitaire) as total_materiaux from detail_formule_meuble dfm
join (select id_materiau,avg(prix_unitaire) as prix_unitaire from v_materiau_restant group by id_materiau) as p
on p.id_materiau = dfm.id_materiau
group by dfm.id_formule_meuble) as q1
on q1.id_formule_meuble=fm.id
) as q1

join
(select 
dem.id_formule_meuble,sum(dem.nombre*dem.duree*p.valeur) as total_salaires
from detail_employe_meuble dem
join
(select * from salaire_employe where date_fin='12-12-9999 23:59') as p
on p.id_employe=dem.id_employe
group by dem.id_formule_meuble ) as q2

on q1.id_formule_meuble=q2.id_formule_meuble

join 
(select id_formule_meuble,valeur as prix_de_vente from prix_de_vente_meuble where date_fin='12-12-9999 23:59') as q3

on q2.id_formule_meuble=q3.id_formule_meuble

join meuble m on m.id=q1.id_meuble
join taille_meuble tm on tm.id=q1.id_taille_meuble
;

create or replace view v_utilisation_employe as 
select ue.*, e.nom as nom_employe
from
utilisation_employe ue 
join employe e
on e.id=ue.id_employe
;

create or replace view v_formule_meuble as
select fm.id,fm.id_meuble,fm.id_taille_meuble,tm.nom as nom_taille_meuble
from
formule_meuble fm
join taille_meuble tm
on tm.id=fm.id_taille_meuble
;

create or replace view v_detail_employe_meuble as
select dem.*,e.nom as nom_employe
from detail_employe_meuble dem
join employe e 
on e.id=dem.id_employe
;


create or replace view v_meuble_restant as
select * from (
select id,date_mouvement,id_formule_meuble,prix_unitaire,sum(q_e-q_s) as quantite from
(select
mme.id,mme.date_mouvement,mme.id_formule_meuble,mme.q_e,mme.prix_unitaire,coalesce(mms.q_s,0) as q_s
from 
(select id,date_mouvement,id_formule_meuble,quantite as q_e,prix_unitaire from mouvement_meuble where type_mouvement=1) as mme
left join
(select quantite as q_s,id_mouvement_mere from mouvement_meuble where type_mouvement=-1) as mms
on mme.id=mms.id_mouvement_mere) as q1
group by id,date_mouvement,id_formule_meuble,prix_unitaire
order by date_mouvement asc ) as q2
where quantite>0
;


create or replace view v_meuble_possible_a_vendre as
select fm.id,fm.id_meuble,fm.id_taille_meuble,m.nom as nom_meuble,tm.nom as nom_taille_meuble,r.quantite
from
(select id_formule_meuble,sum(quantite) as quantite from v_meuble_restant group by id_formule_meuble) as r
join formule_meuble fm on fm.id=r.id_formule_meuble
join meuble m on m.id=fm.id_meuble
join taille_meuble tm on tm.id=fm.id_taille_meuble
;

create or replace view v_vente_meuble as
select vm.*,c.nom as nom_client,c.prenom as prenom_client,c.telephone as telephone_client 
from vente_meuble vm
join client c on c.id=vm.id_client
;

select id_materiau,sum(quantite) as quantite from v_materiau_restant group by id_materiau;


create or replace view v_prix_fabrication_meuble as
select 
q2.*, m.nom as nom_meuble, tm.nom as nom_taille_meuble
from
(select q1.id_formule_meuble,fm.id_meuble,fm.id_taille_meuble,q1.prix_fabrication
from formule_meuble fm
join 
(select dfm.id_formule_meuble,sum(dfm.quantite*p.prix_unitaire) as prix_fabrication from detail_formule_meuble dfm
join (select id_materiau,avg(prix_unitaire) as prix_unitaire from v_materiau_restant group by id_materiau) as p
on p.id_materiau = dfm.id_materiau
group by dfm.id_formule_meuble) as q1
on q1.id_formule_meuble=fm.id) as q2
join meuble m on m.id=q2.id_meuble
join taille_meuble tm on tm.id=q2.id_taille_meuble
;
