-- DATABASE SETUP
\c postgres;
DROP ROLE s3s5;
CREATE ROLE s3s5 WITH SUPERUSER LOGIN PASSWORD 's3s5';
DROP DATABASE s3s5;
CREATE DATABASE s3s5;
\c s3s5;

-- TABLES
create table type_materiau(
    id serial primary key,
    nom varchar(200) not null unique
);

create table materiau(
    id serial primary key,
    nom varchar(200) not null unique,
    id_type_materiau integer,
    description text,
    foreign key(id_type_materiau) references type_materiau(id) on update cascade
);

create table categorie_meuble(
    id serial primary key,
    nom varchar(200) not null unique
);

create table style_meuble(
    id serial primary key,
    nom varchar(200) not null unique
);

create table meuble(
    id serial primary key,
    nom varchar(200) not null unique,
    id_style_meuble integer,
    id_categorie_meuble integer,
    description text,
    foreign key(id_style_meuble) references style_meuble(id) on update cascade,
    foreign key(id_categorie_meuble) references categorie_meuble(id) on update cascade
);
    
create table materiau_possible_style_meuble(
    id serial primary key,
    id_style_meuble integer,
    id_materiau integer,
    foreign key(id_style_meuble) references style_meuble(id) on update cascade,
    foreign key(id_materiau) references materiau(id) on update cascade
);


CREATE TABLE taille_meuble (
    id serial primary key,
    nom varchar (100) not null unique
);

CREATE TABLE formule_meuble (
    id serial primary key,
    id_meuble integer,
    id_taille_meuble integer,
    foreign key(id_meuble) references meuble(id) on update cascade,
    foreign key(id_taille_meuble) references taille_meuble(id) on update cascade
);

create table detail_formule_meuble(
    id serial primary key,
    id_formule_meuble integer,
    id_materiau integer,
    quantite double precision not null,
    foreign key(id_formule_meuble) references formule_meuble(id),
    foreign key(id_materiau) references materiau(id)
);

create table poste(
    id serial primary key,
    nom varchar(200) not null unique
);

create table niveau(
    id serial primary key,
    nom varchar(200) not null unique,
    ordre integer not null unique
);

create table genre(
    id serial primary key,
    nom varchar(200) not null unique
);

create table employe(
    id serial primary key,
    nom varchar(200) not null,
    prenom varchar(200),
    date_naissance date not null,
    id_genre integer,
    date_entree timestamp not null,
    foreign key(id_genre) references genre(id) on update cascade
);

create table base_taux_horaire(
    id serial primary key,
    id_poste integer,
    date_debut timestamp not null,
    date_fin timestamp not null,
    valeur double precision not null,
    foreign key(id_poste) references poste(id) on update cascade
);

create table detail_employe_meuble(
    id serial primary key,
    id_formule_meuble integer,
    id_poste integer,
    id_niveau integer,
    nombre integer not null,
    duree double precision not null,
    foreign key(id_formule_meuble) references formule_meuble(id) on update cascade,
    foreign key(id_poste) references poste(id) on update cascade,
    foreign key(id_niveau) references niveau(id) on update cascade
);

create table client(
    id serial primary key,
    nom varchar(200) not null,
    prenom varchar(200),
    telephone varchar(30),
    id_genre integer,
    date_entree timestamp not null,
    foreign key(id_genre) references genre(id) on update cascade
);

create table vente_meuble(
    id serial primary key,
    date_vente timestamp not null,
    id_client integer,
    prix_total double precision not null,
    foreign key (id_client) references client(id) on update cascade
);

create table detail_vente_meuble(
    id serial primary key,
    id_vente_meuble integer,
    id_formule_meuble integer,
    quantite double precision not null,
    prix_unitaire double precision not null,
    prix_total double precision not null,
    foreign key(id_formule_meuble) references formule_meuble(id) on update cascade
);

create table mouvement_meuble(
    id serial primary key,
    date_mouvement timestamp not null,
    id_formule_meuble integer,
    quantite double precision not null,
    type_mouvement integer not null,
    id_mouvement_mere integer,
    total_materiaux double precision,
    total_salaires double precision,
    prix_total double precision not null,
    prix_unitaire double precision not null,
    id_detail_vente_meuble integer,
    description varchar(200),
    foreign key(id_formule_meuble) references formule_meuble(id) on update cascade
);

create table mouvement_materiau(
    id serial primary key,
    date_mouvement timestamp not null,
    id_materiau integer,
    quantite double precision not null,
    prix_unitaire double precision not null,
    type_mouvement integer not null,
    id_mouvement_mere integer,
    description varchar(200),
    id_mouvement_meuble integer,
    foreign key(id_materiau) references materiau(id) on update cascade
);

create table prix_de_vente_meuble(
    id serial primary key,
    id_formule_meuble integer,
    date_debut timestamp not null,
    date_fin timestamp not null,
    valeur double precision not null,
    foreign key(id_formule_meuble) references formule_meuble(id) on update cascade
);

create table role_employe(
    id serial primary key,
    id_employe integer,
    id_poste integer,
    id_niveau integer,
    date_debut timestamp not null,
    date_fin timestamp not null,
    taux_horaire double precision not null,
    foreign key(id_employe) references employe(id) on update cascade,
    foreign key(id_poste) references poste(id) on update cascade,
    foreign key(id_niveau) references niveau(id) on update cascade
);

create table utilisation_employe(
    id serial primary key,
    id_mouvement_meuble integer,
    date_debut timestamp not null,
    date_fin timestamp not null,
    id_role_employe integer,
    duree_utilisation double precision not null,
    salaire_total double precision not null,
    description varchar(200),
    foreign key(id_role_employe) references role_employe(id) on update cascade
);

create table montee_niveau_employe(
    id serial primary key,
    id_poste integer,
    id_niveau_depart integer,
    id_niveau_arrive integer,
    duree double precision not null,
    date_debut timestamp not null,
    date_fin timestamp not null,
    foreign key(id_poste) references poste(id) on update cascade,
    foreign key(id_niveau_depart) references niveau(id) on update cascade,
    foreign key(id_niveau_arrive) references niveau(id) on update cascade
);

create table multiplication_salarial_employe(
    id serial primary key,
    id_poste integer,
    id_niveau_depart integer,
    id_niveau_arrive integer,
    multipliant double precision not null,
    date_debut timestamp not null,
    date_fin timestamp not null,
    foreign key(id_poste) references poste(id) on update cascade,
    foreign key(id_niveau_depart) references niveau(id) on update cascade,
    foreign key(id_niveau_arrive) references niveau(id) on update cascade
);

-- VIEWS
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

create or replace view v_client as 
select c.*, g.nom as genre
from client c join genre g on g.id=c.id_genre; 

create or replace view v_meuble_contenant_materiau as 
select fm.*,vm.nom as nom_meuble,vm.id_style_meuble,vm.id_categorie_meuble,vm.nom_style_meuble,vm.nom_categorie_meuble,
dfm.quantite,dfm.id_materiau,tm.nom as nom_taille_meuble
from detail_formule_meuble dfm
join formule_meuble fm on fm.id=dfm.id_formule_meuble 
join v_meuble vm on vm.id=fm.id_meuble
join taille_meuble tm on tm.id=fm.id_taille_meuble;

create or replace view v_mouvement_materiau as 
select mm.*,m.id_type_materiau ,m.nom as nom_materiau,tm.nom as nom_type_materiau,mm.prix_unitaire*mm.quantite as prix_total 
from mouvement_materiau mm join materiau m on m.id=mm.id_materiau
join type_materiau tm on tm.id=m.id_type_materiau
order by mm.date_mouvement desc
;

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

create or replace view v_prix_de_vente_meuble as
select
pdvm.id,pdvm.date_debut,pdvm.date_fin,pdvm.valeur,fm.id as id_formule_meuble,fm.id_meuble,fm.id_taille_meuble,m.nom as nom_meuble,tm.nom as nom_taille_meuble 
from prix_de_vente_meuble pdvm
join formule_meuble fm on fm.id=pdvm.id_formule_meuble
join meuble m on m.id=fm.id_meuble
join taille_meuble tm on tm.id=fm.id_taille_meuble
;

create or replace view v_benefice_meuble as
select
q3.id_formule_meuble,q3.prix_total_materiau,q4.valeur as prix_de_vente,
fm.id_meuble,fm.id_taille_meuble,
m.nom as nom_meuble,tm.nom as nom_taille_meuble
from 
(select 
id_formule_meuble,sum(quantite*prix_unitaire_moyen) as prix_total_materiau
from
(select
dfm.id_formule_meuble,dfm.id_materiau,dfm.quantite,q1.prix_unitaire_moyen
from detail_formule_meuble dfm
join (select id_materiau,avg(prix_unitaire) as prix_unitaire_moyen from v_materiau_restant group by id_materiau) as q1 on q1.id_materiau=dfm.id_materiau
) as q2 
group by id_formule_meuble ) as q3
join 
(select id_formule_meuble,valeur from prix_de_vente_meuble where date_fin='9999-12-31 23:59') as q4
on q4.id_formule_meuble=q3.id_formule_meuble
join formule_meuble fm on fm.id=q3.id_formule_meuble
join meuble m on m.id=fm.id_meuble
join taille_meuble tm on tm.id=fm.id_taille_meuble
;

create or replace view v_formule_meuble as
select fm.id,fm.id_meuble,fm.id_taille_meuble,tm.nom as nom_taille_meuble,m.nom as nom_meuble
from
formule_meuble fm
join taille_meuble tm
on tm.id=fm.id_taille_meuble
join meuble m on m.id=fm.id_meuble
;

create or replace view v_detail_employe_meuble as
select 
dem.*,p.nom as nom_poste,n.nom as nom_niveau,n.ordre as ordre_niveau
from detail_employe_meuble dem
join poste p on p.id=dem.id_poste
join niveau n on n.id=dem.id_niveau
;

create or replace view v_meuble_restant as
select * from (
select id,date_mouvement,id_formule_meuble,prix_unitaire,q_e-tqs as quantite from (
select id,date_mouvement,id_formule_meuble,prix_unitaire,q_e,sum(q_s) as tqs from
(select
mme.id,mme.date_mouvement,mme.id_formule_meuble,mme.q_e,mme.prix_unitaire,coalesce(mms.q_s,0) as q_s
from 
(select id,date_mouvement,id_formule_meuble,quantite as q_e,prix_unitaire from mouvement_meuble where type_mouvement=1) as mme
left join
(select quantite as q_s,id_mouvement_mere from mouvement_meuble where type_mouvement=-1) as mms
on mme.id=mms.id_mouvement_mere) as q1
group by id,date_mouvement,id_formule_meuble,prix_unitaire,q_e
order by date_mouvement asc ) as q2
) as q3 where quantite >0
;

create or replace view v_vente_meuble as 
select 
vm.*,c.nom as nom_client,c.prenom as prenom_client
from vente_meuble vm join client c on c.id=vm.id_client;

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

create or replace view v_vente_global_genre as
select g.*,coalesce(quantite,0) as quantite from 
genre g 
left join 
(select id_genre,sum(quantite) as quantite from 
(select 
dvm.id_formule_meuble, dvm.quantite,vc.id_genre
from detail_vente_meuble dvm
join vente_meuble vm on vm.id = dvm.id_vente_meuble
join v_client vc on vc.id=vm.id_client) as q1
group by id_genre ) as q2
on g.id = q2.id_genre
;

create or replace view v_employe as 
select
e.*,g.nom as genre
from 
employe e 
join genre g on g.id=e.id_genre
; 

create or replace view v_montee_niveau_employe as
select
mne.*,p.nom as nom_poste,n1.nom as nom_niveau_depart,n2.nom as nom_niveau_arrive
from
montee_niveau_employe mne 
join poste p on p.id=mne.id_poste
join niveau n1 on n1.id = mne.id_niveau_depart
join niveau n2 on n2.id = mne.id_niveau_arrive
;

create or replace view v_multiplication_salarial_employe as
select
mne.*,p.nom as nom_poste,n1.nom as nom_niveau_depart,n2.nom as nom_niveau_arrive
from
multiplication_salarial_employe mne 
join poste p on p.id=mne.id_poste
join niveau n1 on n1.id = mne.id_niveau_depart
join niveau n2 on n2.id = mne.id_niveau_arrive
;

create or replace view v_role_employe as 
select
re.*,ve.nom as nom_employe,ve.prenom as prenom_employe,ve.date_naissance as date_naissance_employe,ve.id_genre as id_genre_employe,
ve.date_entree as date_entree_employe,ve.genre as nom_genre_employe,p.nom as nom_poste, n.nom as nom_niveau,n.ordre as ordre_niveau
from
role_employe re
join v_employe ve on ve.id = re.id_employe
join poste p on p.id=re.id_poste
join niveau n on n.id=re.id_niveau
;

create or replace view v_utilisation_employe as
select 
ue.*,
re.id_employe,re.id_poste,re.id_niveau,re.taux_horaire,
e.nom as nom_employe,e.prenom as prenom_employe,e.genre as nom_genre_employe,e.id_genre as id_genre_employe,
p.nom as nom_poste,n.nom as nom_niveau
from 
utilisation_employe ue
join role_employe re on re.id=ue.id_role_employe
join v_employe e on e.id=re.id_employe
join poste p on p.id=re.id_poste
join niveau n on n.id=re.id_niveau
;

create or replace view v_base_taux_horaire as 
select bth.*,p.nom as nom_poste 
from base_taux_horaire bth 
join poste p on p.id=bth.id_poste
;

create or replace view v_detail_vente_meuble as
select
dvm.*,vfm.id_meuble,vfm.id_taille_meuble,vfm.nom_meuble,vfm.nom_taille_meuble
from detail_vente_meuble dvm
join v_formule_meuble vfm
on vfm.id=dvm.id_formule_meuble
;

create or replace view v_map_genre as
SELECT fm.id as id_formule_meuble, g.id as id_genre,g.nom as nom_genre
FROM formule_meuble fm
CROSS JOIN genre g
;

-- SELECT SPECIFIQUES
-- vente global genre
SELECT
total_quantite,
id_genre,
nom_genre,
CASE
    WHEN SUM(total_quantite) OVER () = 0 THEN 0
    ELSE total_quantite * 100.0 / SUM(total_quantite) OVER ()
END as pourcentage
FROM
(SELECT
    sum(dvm.quantite) as total_quantite,
    vc.id_genre,vc.genre as nom_genre
FROM
    detail_vente_meuble dvm
JOIN vente_meuble vm ON vm.id = dvm.id_vente_meuble
JOIN v_client vc ON vc.id = vm.id_client
WHERE
    vm.date_vente >= '2022-02-02 00:00' AND vm.date_vente <= '2024-02-01 00:00'
GROUP BY
    vc.id_genre,vc.genre) as q1;

-- vente global produit genre
SELECT
vmg.*,
coalesce(q1.quantite, 0) as quantite,
CASE
    WHEN SUM(coalesce(q1.quantite, 0)) OVER (PARTITION BY vmg.id_formule_meuble) = 0 THEN 0
    ELSE coalesce(q1.quantite, 0) * 100.0 / SUM(coalesce(q1.quantite, 0)) OVER (PARTITION BY vmg.id_formule_meuble)
END as pourcentage
FROM
(SELECT * FROM v_map_genre WHERE id_formule_meuble = 6) as vmg
LEFT JOIN
(SELECT
    dvm.id_formule_meuble,
    sum(dvm.quantite) as quantite,
    vc.id_genre
FROM
    detail_vente_meuble dvm
JOIN vente_meuble vm ON vm.id = dvm.id_vente_meuble
JOIN v_client vc ON vc.id = vm.id_client
WHERE
    vm.date_vente >= '2022-02-02 00:00' AND vm.date_vente <= '2024-02-01 00:00'
GROUP BY
    dvm.id_formule_meuble, vc.id_genre) as q1
ON
q1.id_formule_meuble = vmg.id_formule_meuble AND q1.id_genre = vmg.id_genre;

-- less nearest role from date employe
WITH RankedRoles AS (
    SELECT
         id,
 id_employe ,           
 id_poste    ,          
 id_niveau    ,         
 date_debut    ,        
 date_fin       ,       
 taux_horaire    ,      
 nom_employe      ,     
 prenom_employe    ,    
 date_naissance_employe,
 id_genre_employe      ,
 date_entree_employe   ,
 nom_genre_employe     ,
 nom_poste             ,
 nom_niveau            
 ordre_niveau          ,
        ROW_NUMBER() OVER (PARTITION BY id_employe ORDER BY date_debut DESC) AS rnk
    FROM v_role_employe
    WHERE date_debut <= '2021-02-01'
)
SELECT *
FROM RankedRoles
WHERE rnk = 1;

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


-- reste total meuble

select
vfm.*,
coalesce(q3.quantite_restant,0) as quantite_restant,
coalesce(q3.prix_total,0) as prix_total,
coalesce(q3.prix_unitaire_moyen,0) as prix_unitaire_moyen 
from v_formule_meuble vfm
left join
(select
id_formule_meuble,qr as quantite_restant,ptr as prix_total,ptr/qr as prix_unitaire_moyen
from
(select
id_formule_meuble,sum(qr) as qr,sum(ptr) as ptr
from
(select
id,id_formule_meuble,qe-qs as qr,pte-pts as ptr
from
(select
id,id_formule_meuble,qe,pte,sum(qs) as qs,sum(pts) as pts
from
(select
mme.id,mme.id_formule_meuble,mme.quantite as qe ,mme.prix_total pte,
coalesce(mms.quantite,0) as qs,coalesce(mms.prix_total,0) as pts
from 
(select * from mouvement_meuble where type_mouvement=1 and date_mouvement <= '2024-02-01 00:00') as mme
left join
(select * from mouvement_meuble where type_mouvement=-1 and date_mouvement <= '2024-02-01 00:00') as mms
on mme.id=mms.id_mouvement_mere) as q1
group by id,id_formule_meuble,qe,pte ) as q2 ) as q3
group by id_formule_meuble ) as q4) q3
on q3.id_formule_meuble=vfm.id
;

-- verification ordre niveau
select depart<arrive as result from
(select ordre as depart from niveau where id=2) as q1
cross join 
(select ordre as arrive from niveau where id=1) as q2
;


-- DATA
INSERT INTO categorie_meuble(nom) VALUES ('Table');
INSERT INTO categorie_meuble(nom) VALUES ('Chaise');
INSERT INTO categorie_meuble(nom) VALUES ('Canape');
INSERT INTO categorie_meuble(nom) VALUES ('Armoire');
INSERT INTO categorie_meuble(nom) VALUES ('Coffre');
INSERT INTO categorie_meuble(nom) VALUES ('Coiffeuse');
INSERT INTO categorie_meuble(nom) VALUES ('Biblotheque');
INSERT INTO categorie_meuble(nom) VALUES ('Vitrine');

INSERT INTO genre(nom) VALUES ('Homme');
INSERT INTO genre(nom) VALUES ('Femme');

INSERT INTO niveau(nom, ordre ) VALUES ('Debutant', 10);
INSERT INTO niveau(nom, ordre ) VALUES ('Senior', 20);
INSERT INTO niveau(nom, ordre ) VALUES ('Expert', 30);

INSERT INTO poste( nom ) VALUES ('Menuisier');
INSERT INTO poste( nom ) VALUES ('Assembleur');
INSERT INTO poste( nom ) VALUES ('Technicien en finition');
INSERT INTO poste( nom ) VALUES ('Technicien de bois');
INSERT INTO poste( nom ) VALUES ('Operateur de machine');

INSERT INTO style_meuble( nom ) VALUES ('Boheme');
INSERT INTO style_meuble( nom ) VALUES ('Scandinave');
INSERT INTO style_meuble( nom ) VALUES ('Contemporaine');

INSERT INTO taille_meuble( nom ) VALUES ( 'petit');
INSERT INTO taille_meuble( nom ) VALUES ( 'moyen');
INSERT INTO taille_meuble( nom ) VALUES ( 'grand');

INSERT INTO type_materiau( nom ) VALUES ( 'bois');
INSERT INTO type_materiau( nom ) VALUES ( 'metal');
INSERT INTO type_materiau( nom ) VALUES ( 'plastique');
INSERT INTO type_materiau( nom ) VALUES ( 'verre');
INSERT INTO type_materiau( nom ) VALUES ( 'tissus');
INSERT INTO type_materiau( nom ) VALUES ( 'contreplaque');

INSERT INTO base_taux_horaire( id_poste, date_debut, date_fin, valeur ) VALUES ( 1, '2022-01-01 00:00', '9999-12-31 23:59', 10.0);
INSERT INTO base_taux_horaire( id_poste, date_debut, date_fin, valeur ) VALUES ( 2, '2022-01-01 00:00', '9999-12-31 23:59', 10.0);
INSERT INTO base_taux_horaire( id_poste, date_debut, date_fin, valeur ) VALUES ( 3, '2022-01-01 00:00', '9999-12-31 23:59', 10.0);
INSERT INTO base_taux_horaire( id_poste, date_debut, date_fin, valeur ) VALUES ( 4, '2022-01-01 00:00', '9999-12-31 23:59', 10.0);
INSERT INTO base_taux_horaire( id_poste, date_debut, date_fin, valeur ) VALUES ( 5, '2022-01-01 00:00', '9999-12-31 23:59', 10.0);

INSERT INTO client(nom, prenom, telephone, id_genre, date_entree ) VALUES ('Rakoto', 'Koto', '0321289400', 1, '2024-01-01 12:00:00 am');
INSERT INTO client(nom, prenom, telephone, id_genre, date_entree ) VALUES ('Ravao', 'Vao', '0332744350', 2, '2024-01-01 12:00:00 am');
INSERT INTO client(nom, prenom, telephone, id_genre, date_entree ) VALUES ('Rabe', 'Be', '0330885002', 1, '2024-01-01 12:00:00 am');

INSERT INTO employe(nom, prenom, date_naissance, id_genre, date_entree ) VALUES ('Rajoelina', 'Andry', '2000-01-01', 1, '2019-01-01 12:00:00 am');
INSERT INTO employe(nom, prenom, date_naissance, id_genre, date_entree ) VALUES ('Randriasoloniako', 'Siteny', '2000-01-01', 1, '2021-01-01 12:00:00 am');
INSERT INTO employe(nom, prenom, date_naissance, id_genre, date_entree ) VALUES ('Tom', 'Sawyer', '2000-01-01', 1, '2023-01-01 12:00:00 am');
INSERT INTO employe(nom, prenom, date_naissance, id_genre, date_entree ) VALUES ('Do', 'Do', '2000-01-01', 1, '2010-01-01 12:00:00 am');

INSERT INTO materiau( nom, id_type_materiau, description ) VALUES ('Chene', 1, 'Materiau durable et resistant');
INSERT INTO materiau( nom, id_type_materiau, description ) VALUES ('Pin', 1, 'Bois leger utilise pour differents usages');
INSERT INTO materiau( nom, id_type_materiau, description ) VALUES ('Erable', 1, 'Bois dur souvent utilise dans la fabrication de meubles');
INSERT INTO materiau( nom, id_type_materiau, description ) VALUES ('Acier inoxydable', 2, 'Resistant a la corrosion et polyvalent');
INSERT INTO materiau( nom, id_type_materiau, description ) VALUES ('Aluminium', 2, 'Leger et malleable, utilise dans diverses industries');
INSERT INTO materiau( nom, id_type_materiau, description ) VALUES ('Cuivre', 2, 'Bonne conductivite thermique et electrique');
INSERT INTO materiau( nom, id_type_materiau, description ) VALUES ('Polyethylene', 3, 'Resistant et flexible, utilise dans les emballages et les produits chimiques');
INSERT INTO materiau( nom, id_type_materiau, description ) VALUES ('Polycarbonate', 3, 'Transparent et resistant aux chocs, utilise dans les lunettes et les vitres de protection');
INSERT INTO materiau( nom, id_type_materiau, description ) VALUES ('Polypropylene', 3, 'Resistant a la chaleur, utilise dans les tapis et les conteneurs');
INSERT INTO materiau( nom, id_type_materiau, description ) VALUES ( 'Verre borosilicate', 4, 'Resistant aux temperatures elevees, utilise dans les ustensiles de cuisine');
INSERT INTO materiau( nom, id_type_materiau, description ) VALUES ( 'Verre trempe', 4, 'Renforce pour plus de resistance, utilise dans les smartphones et les pare-brise de voiture');
INSERT INTO materiau( nom, id_type_materiau, description ) VALUES ( 'Verre cristallin', 4, 'Transparent et brillant, utilise dans la verrerie fine');
INSERT INTO materiau( nom, id_type_materiau, description ) VALUES ( 'Coton', 5, 'Respirant et confortable, utilise dans les vetements');
INSERT INTO materiau( nom, id_type_materiau, description ) VALUES ( 'Laine', 5, 'Isolant thermique naturel, utilise dans les tapis et les vetements d hiver');
INSERT INTO materiau( nom, id_type_materiau, description ) VALUES ( 'Soie', 5, 'Legere et brillante, utilisee dans la mode haut de gamme');
INSERT INTO materiau( nom, id_type_materiau, description ) VALUES ( 'Peuplier', 6, 'Leger et resistant, utilise dans la construction legere et le mobilier');
INSERT INTO materiau( nom, id_type_materiau, description ) VALUES ( 'Epinette', 6, 'Stable et peu co–teux, utilise dans la fabrication de meubles');
INSERT INTO materiau( nom, id_type_materiau, description ) VALUES ( 'Eucalyptus', 6, 'Durabilite et resistance a l humidite, utilise dans les revetements de sol');

INSERT INTO materiau_possible_style_meuble( id_style_meuble, id_materiau ) VALUES (1, 1);
INSERT INTO materiau_possible_style_meuble( id_style_meuble, id_materiau ) VALUES (1, 4);
INSERT INTO materiau_possible_style_meuble( id_style_meuble, id_materiau ) VALUES (1, 7);
INSERT INTO materiau_possible_style_meuble( id_style_meuble, id_materiau ) VALUES (1, 10);
INSERT INTO materiau_possible_style_meuble( id_style_meuble, id_materiau ) VALUES (1, 13);
INSERT INTO materiau_possible_style_meuble( id_style_meuble, id_materiau ) VALUES (1, 16);
INSERT INTO materiau_possible_style_meuble( id_style_meuble, id_materiau ) VALUES (2, 2);
INSERT INTO materiau_possible_style_meuble( id_style_meuble, id_materiau ) VALUES (2, 5);
INSERT INTO materiau_possible_style_meuble( id_style_meuble, id_materiau ) VALUES (2, 8);
INSERT INTO materiau_possible_style_meuble( id_style_meuble, id_materiau ) VALUES ( 2, 11);
INSERT INTO materiau_possible_style_meuble( id_style_meuble, id_materiau ) VALUES ( 2, 14);
INSERT INTO materiau_possible_style_meuble( id_style_meuble, id_materiau ) VALUES ( 2, 17);
INSERT INTO materiau_possible_style_meuble( id_style_meuble, id_materiau ) VALUES ( 3, 3);
INSERT INTO materiau_possible_style_meuble( id_style_meuble, id_materiau ) VALUES ( 3, 6);
INSERT INTO materiau_possible_style_meuble( id_style_meuble, id_materiau ) VALUES ( 3, 9);
INSERT INTO materiau_possible_style_meuble( id_style_meuble, id_materiau ) VALUES ( 3, 12);
INSERT INTO materiau_possible_style_meuble( id_style_meuble, id_materiau ) VALUES ( 3, 15);
INSERT INTO materiau_possible_style_meuble( id_style_meuble, id_materiau ) VALUES ( 3, 18);

INSERT INTO meuble(nom, id_style_meuble, id_categorie_meuble, description ) VALUES ('Table en Chene', 1, 1, 'Belle table en bois de chene pour la salle a manger');
INSERT INTO meuble(nom, id_style_meuble, id_categorie_meuble, description ) VALUES ('Table Basse Scandinave', 2, 1, 'Table basse au style scandinave pour le salon');
INSERT INTO meuble(nom, id_style_meuble, id_categorie_meuble, description ) VALUES ('Table de Travail Contemporaine', 3, 1, 'Table de travail moderne pour le bureau');
INSERT INTO meuble(nom, id_style_meuble, id_categorie_meuble, description ) VALUES ('Chaise en Pin', 2, 2, 'Chaise legere en pin pour la salle a manger');
INSERT INTO meuble(nom, id_style_meuble, id_categorie_meuble, description ) VALUES ('Chaise Bois et Metal', 3, 2, 'Chaise contemporaine en bois et metal pour differents usages');
INSERT INTO meuble(nom, id_style_meuble, id_categorie_meuble, description ) VALUES ('Canape Boheme', 1, 3, 'Canape confortable au style boheme pour le salon');
INSERT INTO meuble(nom, id_style_meuble, id_categorie_meuble, description ) VALUES ('Canape Scandinave Convertible', 2, 3, 'Canape convertible au design scandinave pour un usage multifonctionnel');
INSERT INTO meuble(nom, id_style_meuble, id_categorie_meuble, description ) VALUES ('Armoire en erable', 3, 4, 'Armoire spacieuse en erable pour la chambre a coucher');
INSERT INTO meuble(nom, id_style_meuble, id_categorie_meuble, description ) VALUES ('Armoire Contemporaine avec Miroir', 2, 4, 'Armoire moderne avec miroir pour ranger vos vetements');
INSERT INTO meuble(nom, id_style_meuble, id_categorie_meuble, description ) VALUES ( 'Coffre en Peuplier', 1, 5, 'Coffre en bois de peuplier pour stocker differents objets');
INSERT INTO meuble(nom, id_style_meuble, id_categorie_meuble, description ) VALUES ( 'Coiffeuse Scandinave', 2, 6, 'Coiffeuse au style scandinave avec miroir pour la chambre');
INSERT INTO meuble(nom, id_style_meuble, id_categorie_meuble, description ) VALUES ( 'Bibliotheque Contemporaine', 3, 7, 'Bibliotheque moderne pour exposer vos livres et objets decoratifs');
INSERT INTO meuble(nom, id_style_meuble, id_categorie_meuble, description ) VALUES ( 'Vitrine en Verre Trempe', 2, 8, 'Vitrine elegante en verre trempe pour exposer vos collections');

INSERT INTO montee_niveau_employe(id_poste, id_niveau_depart, id_niveau_arrive, duree, date_debut, date_fin ) VALUES (1, 1, 2, 2.0, '2020-01-01 00:00', '9999-12-31 23:59');
INSERT INTO montee_niveau_employe(id_poste, id_niveau_depart, id_niveau_arrive, duree, date_debut, date_fin ) VALUES (1, 2, 3, 3.0, '2020-01-01 00:00', '9999-12-31 23:59');
INSERT INTO montee_niveau_employe(id_poste, id_niveau_depart, id_niveau_arrive, duree, date_debut, date_fin ) VALUES (2, 1, 2, 2.0, '2020-01-01 00:00', '9999-12-31 23:59');
INSERT INTO montee_niveau_employe(id_poste, id_niveau_depart, id_niveau_arrive, duree, date_debut, date_fin ) VALUES (2, 2, 3, 3.0, '2020-01-01 00:00', '9999-12-31 23:59');
INSERT INTO montee_niveau_employe(id_poste, id_niveau_depart, id_niveau_arrive, duree, date_debut, date_fin ) VALUES (3, 1, 2, 2.0, '2020-01-01 00:00', '9999-12-31 23:59');
INSERT INTO montee_niveau_employe(id_poste, id_niveau_depart, id_niveau_arrive, duree, date_debut, date_fin ) VALUES (3, 2, 3, 3.0, '2020-01-01 00:00', '9999-12-31 23:59');
INSERT INTO montee_niveau_employe(id_poste, id_niveau_depart, id_niveau_arrive, duree, date_debut, date_fin ) VALUES (4, 1, 2, 2.0, '2020-01-01 00:00', '9999-12-31 23:59');
INSERT INTO montee_niveau_employe(id_poste, id_niveau_depart, id_niveau_arrive, duree, date_debut, date_fin ) VALUES (4, 2, 3, 3.0, '2020-01-01 00:00', '9999-12-31 23:59');
INSERT INTO montee_niveau_employe(id_poste, id_niveau_depart, id_niveau_arrive, duree, date_debut, date_fin ) VALUES (5, 1, 2, 2.0, '2020-01-01 00:00', '9999-12-31 23:59');
INSERT INTO montee_niveau_employe(id_poste, id_niveau_depart, id_niveau_arrive, duree, date_debut, date_fin ) VALUES (5, 2, 3, 3.0, '2020-01-01 00:00', '9999-12-31 23:59');

INSERT INTO multiplication_salarial_employe(id_poste, id_niveau_depart, id_niveau_arrive, multipliant, date_debut, date_fin ) VALUES (1, 1, 2, 2.0, '2020-01-01 00:00', '9999-12-31 23:59');
INSERT INTO multiplication_salarial_employe(id_poste, id_niveau_depart, id_niveau_arrive, multipliant, date_debut, date_fin ) VALUES (1, 2, 3, 1.5, '2020-01-01 00:00', '9999-12-31 23:59');
INSERT INTO multiplication_salarial_employe(id_poste, id_niveau_depart, id_niveau_arrive, multipliant, date_debut, date_fin ) VALUES (2, 1, 2, 2.0, '2020-01-01 00:00', '9999-12-31 23:59');
INSERT INTO multiplication_salarial_employe(id_poste, id_niveau_depart, id_niveau_arrive, multipliant, date_debut, date_fin ) VALUES (2, 2, 3, 1.5, '2020-01-01 00:00', '9999-12-31 23:59');
INSERT INTO multiplication_salarial_employe(id_poste, id_niveau_depart, id_niveau_arrive, multipliant, date_debut, date_fin ) VALUES (3, 1, 2, 2.0, '2020-01-01 00:00', '9999-12-31 23:59');
INSERT INTO multiplication_salarial_employe(id_poste, id_niveau_depart, id_niveau_arrive, multipliant, date_debut, date_fin ) VALUES (3, 2, 3, 1.5, '2020-01-01 00:00', '9999-12-31 23:59');
INSERT INTO multiplication_salarial_employe(id_poste, id_niveau_depart, id_niveau_arrive, multipliant, date_debut, date_fin ) VALUES (4, 1, 2, 2.0, '2020-01-01 00:00', '9999-12-31 23:59');
INSERT INTO multiplication_salarial_employe(id_poste, id_niveau_depart, id_niveau_arrive, multipliant, date_debut, date_fin ) VALUES (4, 2, 3, 1.5, '2020-01-01 00:00', '9999-12-31 23:59');
INSERT INTO multiplication_salarial_employe(id_poste, id_niveau_depart, id_niveau_arrive, multipliant, date_debut, date_fin ) VALUES (5, 1, 2, 2.0, '2020-01-01 00:00', '9999-12-31 23:59');
INSERT INTO multiplication_salarial_employe(id_poste, id_niveau_depart, id_niveau_arrive, multipliant, date_debut, date_fin ) VALUES (5, 2, 3, 1.5, '2020-01-01 00:00', '9999-12-31 23:59');

INSERT INTO role_employe(id_employe, id_poste, id_niveau, date_debut, date_fin, taux_horaire ) VALUES (1, 1, 1, '2019-01-01 00:00', '9999-12-31 23:59', 10.0);
INSERT INTO role_employe(id_employe, id_poste, id_niveau, date_debut, date_fin, taux_horaire ) VALUES (2, 1, 1, '2021-01-01 00:00', '9999-12-31 23:59', 10.0);
INSERT INTO role_employe(id_employe, id_poste, id_niveau, date_debut, date_fin, taux_horaire ) VALUES (3, 3, 1, '2023-01-01 00:00', '9999-12-31 23:59', 5.0);
INSERT INTO role_employe(id_employe, id_poste, id_niveau, date_debut, date_fin, taux_horaire ) VALUES (4, 5, 1, '2010-01-01 00:00', '9999-12-31 23:59', 10.0);

INSERT INTO formule_meuble(id_meuble, id_taille_meuble ) VALUES (1, 1);
INSERT INTO formule_meuble(id_meuble, id_taille_meuble ) VALUES (2, 1);
INSERT INTO formule_meuble(id_meuble, id_taille_meuble ) VALUES (3, 1);
INSERT INTO formule_meuble(id_meuble, id_taille_meuble ) VALUES (4, 1);
INSERT INTO formule_meuble(id_meuble, id_taille_meuble ) VALUES (5, 1);
INSERT INTO formule_meuble(id_meuble, id_taille_meuble ) VALUES (6, 1);
INSERT INTO formule_meuble(id_meuble, id_taille_meuble ) VALUES (7, 1);
INSERT INTO formule_meuble(id_meuble, id_taille_meuble ) VALUES (8, 1);
INSERT INTO formule_meuble(id_meuble, id_taille_meuble ) VALUES (9, 1);
INSERT INTO formule_meuble(id_meuble, id_taille_meuble ) VALUES (10, 1);
INSERT INTO formule_meuble(id_meuble, id_taille_meuble ) VALUES (11, 1);
INSERT INTO formule_meuble(id_meuble, id_taille_meuble ) VALUES (12, 1);
INSERT INTO formule_meuble(id_meuble, id_taille_meuble ) VALUES (13, 1);

INSERT INTO prix_de_vente_meuble( id_formule_meuble, date_debut, date_fin, valeur ) VALUES (1, '2020-01-01 00:00', '9999-12-31 23:59', 200000.0);
INSERT INTO prix_de_vente_meuble( id_formule_meuble, date_debut, date_fin, valeur ) VALUES (2, '2020-01-01 00:00', '9999-12-31 23:59', 300000.0);
INSERT INTO prix_de_vente_meuble( id_formule_meuble, date_debut, date_fin, valeur ) VALUES (3, '2020-01-01 00:00', '9999-12-31 23:59', 1000000.0);
INSERT INTO prix_de_vente_meuble( id_formule_meuble, date_debut, date_fin, valeur ) VALUES (4, '2020-01-01 00:00', '9999-12-31 23:59', 150000.0);
INSERT INTO prix_de_vente_meuble( id_formule_meuble, date_debut, date_fin, valeur ) VALUES (5, '2020-01-01 00:00', '9999-12-31 23:59', 130000.0);
INSERT INTO prix_de_vente_meuble( id_formule_meuble, date_debut, date_fin, valeur ) VALUES (6, '2020-01-01 00:00', '9999-12-31 23:59', 2000000.0);
INSERT INTO prix_de_vente_meuble( id_formule_meuble, date_debut, date_fin, valeur ) VALUES (7, '2020-01-01 00:00', '9999-12-31 23:59', 3.5E7);
INSERT INTO prix_de_vente_meuble( id_formule_meuble, date_debut, date_fin, valeur ) VALUES (8, '2020-01-01 00:00', '9999-12-31 23:59', 3000000.0);
INSERT INTO prix_de_vente_meuble( id_formule_meuble, date_debut, date_fin, valeur ) VALUES (9, '2020-01-01 00:00', '9999-12-31 23:59', 450000.0);
INSERT INTO prix_de_vente_meuble( id_formule_meuble, date_debut, date_fin, valeur ) VALUES (10, '2020-01-01 00:00', '9999-12-31 23:59', 230000.0);
INSERT INTO prix_de_vente_meuble( id_formule_meuble, date_debut, date_fin, valeur ) VALUES (11, '2020-01-01 00:00', '9999-12-31 23:59', 670000.0);
INSERT INTO prix_de_vente_meuble( id_formule_meuble, date_debut, date_fin, valeur ) VALUES (12, '2020-01-01 00:00', '9999-12-31 23:59', 5000000.0);
INSERT INTO prix_de_vente_meuble( id_formule_meuble, date_debut, date_fin, valeur ) VALUES (13, '2020-01-01 00:00', '2020-01-02 12:00:00 am', 900000.0);
INSERT INTO prix_de_vente_meuble( id_formule_meuble, date_debut, date_fin, valeur ) VALUES (14, '2020-01-02 12:00:00 am', '9999-12-31 23:59', 1000000.0);

INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (1, 1, 1, 2, 20.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (1, 3, 3, 1, 15.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (1, 4, 2, 1, 15.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (2, 1, 1, 2, 30.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (2, 2, 2, 1, 15.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (2, 4, 3, 1, 10.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (3, 1, 3, 1, 40.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (3, 2, 1, 1, 15.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (3, 3, 2, 1, 10.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (4, 1, 1, 1, 10.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (4, 2, 1, 1, 10.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (4, 4, 1, 1, 15.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (4, 5, 2, 2, 10.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (5, 1, 3, 2, 10.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (5, 3, 2, 1, 10.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (5, 5, 1, 2, 20.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (6, 1, 3, 1, 10.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (6, 3, 2, 1, 10.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (6, 3, 3, 1, 20.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (6, 4, 1, 2, 10.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (7, 1, 3, 1, 20.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (7, 2, 1, 2, 10.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (7, 3, 2, 1, 10.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (8, 1, 3, 2, 30.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (8, 2, 2, 1, 30.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (8, 4, 1, 3, 10.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (9, 1, 2, 2, 10.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (9, 2, 1, 1, 10.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (9, 3, 2, 1, 20.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (10, 1, 2, 2, 20.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (10, 3, 1, 1, 10.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (10, 4, 2, 1, 10.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (11, 1, 2, 1, 10.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (11, 3, 1, 1, 30.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (11, 4, 3, 1, 20.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (12, 1, 2, 2, 40.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (12, 2, 2, 2, 30.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (12, 3, 3, 1, 15.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (12, 5, 2, 1, 10.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (13, 1, 2, 1, 10.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (13, 2, 2, 1, 10.0);
INSERT INTO detail_employe_meuble(id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES (13, 3, 2, 1, 10.0);

INSERT INTO detail_formule_meuble(id_formule_meuble, id_materiau, quantite ) VALUES (1, 1, 2.0);
INSERT INTO detail_formule_meuble(id_formule_meuble, id_materiau, quantite ) VALUES (1, 16, 1.0);
INSERT INTO detail_formule_meuble(id_formule_meuble, id_materiau, quantite ) VALUES (2, 2, 2.0);
INSERT INTO detail_formule_meuble(id_formule_meuble, id_materiau, quantite ) VALUES (2, 5, 2.0);
INSERT INTO detail_formule_meuble(id_formule_meuble, id_materiau, quantite ) VALUES (2, 14, 1.0);
INSERT INTO detail_formule_meuble(id_formule_meuble, id_materiau, quantite ) VALUES (3, 3, 2.0);
INSERT INTO detail_formule_meuble(id_formule_meuble, id_materiau, quantite ) VALUES (3, 6, 1.0);
INSERT INTO detail_formule_meuble(id_formule_meuble, id_materiau, quantite ) VALUES (3, 12, 2.0);
INSERT INTO detail_formule_meuble(id_formule_meuble, id_materiau, quantite ) VALUES (4, 2, 1.0);
INSERT INTO detail_formule_meuble(id_formule_meuble, id_materiau, quantite ) VALUES (4, 8, 1.0);
INSERT INTO detail_formule_meuble(id_formule_meuble, id_materiau, quantite ) VALUES (4, 17, 1.0);
INSERT INTO detail_formule_meuble(id_formule_meuble, id_materiau, quantite ) VALUES (5, 6, 4.0);
INSERT INTO detail_formule_meuble(id_formule_meuble, id_materiau, quantite ) VALUES (5, 18, 2.0);
INSERT INTO detail_formule_meuble(id_formule_meuble, id_materiau, quantite ) VALUES (6, 1, 1.0);
INSERT INTO detail_formule_meuble(id_formule_meuble, id_materiau, quantite ) VALUES (6, 7, 2.0);
INSERT INTO detail_formule_meuble(id_formule_meuble, id_materiau, quantite ) VALUES (6, 13, 1.0);
INSERT INTO detail_formule_meuble(id_formule_meuble, id_materiau, quantite ) VALUES (7, 2, 3.0);
INSERT INTO detail_formule_meuble(id_formule_meuble, id_materiau, quantite ) VALUES (7, 5, 2.0);
INSERT INTO detail_formule_meuble(id_formule_meuble, id_materiau, quantite ) VALUES (7, 11, 1.0);
INSERT INTO detail_formule_meuble(id_formule_meuble, id_materiau, quantite ) VALUES (8, 3, 10.0);
INSERT INTO detail_formule_meuble(id_formule_meuble, id_materiau, quantite ) VALUES (8, 15, 5.0);
INSERT INTO detail_formule_meuble(id_formule_meuble, id_materiau, quantite ) VALUES (9, 2, 2.0);
INSERT INTO detail_formule_meuble(id_formule_meuble, id_materiau, quantite ) VALUES (9, 11, 4.0);
INSERT INTO detail_formule_meuble(id_formule_meuble, id_materiau, quantite ) VALUES (9, 8, 1.0);
INSERT INTO detail_formule_meuble(id_formule_meuble, id_materiau, quantite ) VALUES (10, 1, 3.0);
INSERT INTO detail_formule_meuble(id_formule_meuble, id_materiau, quantite ) VALUES (10, 4, 3.0);
INSERT INTO detail_formule_meuble(id_formule_meuble, id_materiau, quantite ) VALUES (10, 13, 1.0);
INSERT INTO detail_formule_meuble(id_formule_meuble, id_materiau, quantite ) VALUES (11, 2, 2.0);
INSERT INTO detail_formule_meuble(id_formule_meuble, id_materiau, quantite ) VALUES (11, 8, 1.0);
INSERT INTO detail_formule_meuble(id_formule_meuble, id_materiau, quantite ) VALUES (12, 3, 20.0);
INSERT INTO detail_formule_meuble(id_formule_meuble, id_materiau, quantite ) VALUES (12, 6, 10.0);
INSERT INTO detail_formule_meuble(id_formule_meuble, id_materiau, quantite ) VALUES (12, 9, 2.0);
INSERT INTO detail_formule_meuble(id_formule_meuble, id_materiau, quantite ) VALUES (12, 15, 1.0);
INSERT INTO detail_formule_meuble(id_formule_meuble, id_materiau, quantite ) VALUES (13, 2, 2.0);
INSERT INTO detail_formule_meuble(id_formule_meuble, id_materiau, quantite ) VALUES (13, 11, 1.0);
