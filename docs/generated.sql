CREATE SCHEMA IF NOT EXISTS "public";

CREATE SEQUENCE "public".base_taux_horaire_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".categorie_meuble_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".changement_niveau_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".client_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".detail_employe_meuble_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".detail_formule_meuble_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".detail_vente_meuble_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".employe_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".formule_meuble_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".genre_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".materiau_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".materiau_possible_style_meuble_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".meuble_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".montee_niveau_employe_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".mouvement_materiau_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".mouvement_meuble_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".multiplication_salarial_employe_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".multiplicite_taux_horaire_niveau_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".niveau_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".personnel_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".personnel_poste_niveau_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".poste_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".prix_de_vente_meuble_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".role_employe_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".salaire_employe_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".style_meuble_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".taille_meuble_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".taux_horaire_poste_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".type_materiau_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".utilisation_employe_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".vente_meuble_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE TYPE "public".tablefunc_crosstab_2 AS (row_name text, category_1 text, category_2 text);

CREATE TYPE "public".tablefunc_crosstab_3 AS (row_name text, category_1 text, category_2 text, category_3 text);

CREATE TYPE "public".tablefunc_crosstab_4 AS (row_name text, category_1 text, category_2 text, category_3 text, category_4 text);

CREATE  TABLE "public".categorie_meuble ( 
	id                   serial DEFAULT nextval('categorie_meuble_id_seq'::regclass) NOT NULL  ,
	nom                  varchar(200)    ,
	CONSTRAINT categorie_meuble_pkey PRIMARY KEY ( id )
 );

CREATE  TABLE "public".genre ( 
	id                   serial DEFAULT nextval('genre_id_seq'::regclass) NOT NULL  ,
	nom                  varchar(200)    ,
	CONSTRAINT genre_pkey PRIMARY KEY ( id )
 );

CREATE  TABLE "public".niveau ( 
	id                   serial DEFAULT nextval('niveau_id_seq'::regclass) NOT NULL  ,
	nom                  varchar(200)    ,
	ordre                integer    ,
	CONSTRAINT niveau_pkey PRIMARY KEY ( id )
 );

CREATE  TABLE "public".poste ( 
	id                   serial DEFAULT nextval('poste_id_seq'::regclass) NOT NULL  ,
	nom                  varchar(200)    ,
	CONSTRAINT poste_pkey PRIMARY KEY ( id )
 );

CREATE  TABLE "public".style_meuble ( 
	id                   serial DEFAULT nextval('style_meuble_id_seq'::regclass) NOT NULL  ,
	nom                  varchar(200)    ,
	CONSTRAINT style_meuble_pkey PRIMARY KEY ( id )
 );

CREATE  TABLE "public".taille_meuble ( 
	id                   serial DEFAULT nextval('taille_meuble_id_seq'::regclass) NOT NULL  ,
	nom                  varchar(100)    ,
	CONSTRAINT taille_meuble_pkey PRIMARY KEY ( id )
 );

CREATE  TABLE "public".taux_horaire_poste ( 
	id                   serial DEFAULT nextval('taux_horaire_poste_id_seq'::regclass) NOT NULL  ,
	id_poste             integer    ,
	date_debut           timestamp    ,
	date_fin             timestamp    ,
	valeur               double precision    ,
	CONSTRAINT taux_horaire_poste_pkey PRIMARY KEY ( id ),
	CONSTRAINT taux_horaire_poste_id_poste_fkey FOREIGN KEY ( id_poste ) REFERENCES "public".poste( id )   
 );

CREATE  TABLE "public".type_materiau ( 
	id                   serial DEFAULT nextval('type_materiau_id_seq'::regclass) NOT NULL  ,
	nom                  varchar(200)    ,
	CONSTRAINT type_materiau_pkey PRIMARY KEY ( id )
 );

CREATE  TABLE "public".base_taux_horaire ( 
	id                   serial DEFAULT nextval('base_taux_horaire_id_seq'::regclass) NOT NULL  ,
	id_poste             integer    ,
	date_debut           timestamp    ,
	date_fin             timestamp    ,
	valeur               double precision    ,
	CONSTRAINT base_taux_horaire_pkey PRIMARY KEY ( id ),
	CONSTRAINT base_taux_horaire_id_poste_fkey FOREIGN KEY ( id_poste ) REFERENCES "public".poste( id )   
 );

CREATE  TABLE "public".client ( 
	id                   serial DEFAULT nextval('client_id_seq'::regclass) NOT NULL  ,
	nom                  varchar(200)    ,
	prenom               varchar(200)    ,
	telephone            varchar(30)    ,
	id_genre             integer    ,
	date_entree          timestamp    ,
	CONSTRAINT client_pkey PRIMARY KEY ( id ),
	CONSTRAINT client_id_genre_fkey FOREIGN KEY ( id_genre ) REFERENCES "public".genre( id )   
 );

CREATE  TABLE "public".employe ( 
	id                   serial DEFAULT nextval('employe_id_seq'::regclass) NOT NULL  ,
	nom                  varchar(200)    ,
	prenom               varchar(200)    ,
	date_naissance       date    ,
	id_genre             integer    ,
	date_entree          timestamp    ,
	CONSTRAINT employe_pkey PRIMARY KEY ( id ),
	CONSTRAINT employe_id_genre_fkey FOREIGN KEY ( id_genre ) REFERENCES "public".genre( id )   
 );

CREATE  TABLE "public".materiau ( 
	id                   serial DEFAULT nextval('materiau_id_seq'::regclass) NOT NULL  ,
	nom                  varchar(200)    ,
	id_type_materiau     integer    ,
	description          text    ,
	CONSTRAINT materiau_pkey PRIMARY KEY ( id ),
	CONSTRAINT materiau_id_type_materiau_fkey FOREIGN KEY ( id_type_materiau ) REFERENCES "public".type_materiau( id )   
 );

CREATE  TABLE "public".materiau_possible_style_meuble ( 
	id                   serial DEFAULT nextval('materiau_possible_style_meuble_id_seq'::regclass) NOT NULL  ,
	id_style_meuble      integer    ,
	id_materiau          integer    ,
	CONSTRAINT materiau_possible_style_meuble_pkey PRIMARY KEY ( id ),
	CONSTRAINT materiau_possible_style_meuble_id_materiau_fkey FOREIGN KEY ( id_materiau ) REFERENCES "public".materiau( id )   ,
	CONSTRAINT materiau_possible_style_meuble_id_style_meuble_fkey FOREIGN KEY ( id_style_meuble ) REFERENCES "public".style_meuble( id )   
 );

CREATE  TABLE "public".meuble ( 
	id                   serial DEFAULT nextval('meuble_id_seq'::regclass) NOT NULL  ,
	nom                  varchar(200)    ,
	id_style_meuble      integer    ,
	id_categorie_meuble  integer    ,
	description          text    ,
	CONSTRAINT meuble_pkey PRIMARY KEY ( id ),
	CONSTRAINT meuble_id_categorie_meuble_fkey FOREIGN KEY ( id_categorie_meuble ) REFERENCES "public".categorie_meuble( id )   ,
	CONSTRAINT meuble_id_style_meuble_fkey FOREIGN KEY ( id_style_meuble ) REFERENCES "public".style_meuble( id )   
 );

CREATE  TABLE "public".montee_niveau_employe ( 
	id                   serial DEFAULT nextval('montee_niveau_employe_id_seq'::regclass) NOT NULL  ,
	id_poste             integer    ,
	id_niveau_depart     integer    ,
	id_niveau_arrive     integer    ,
	duree                double precision    ,
	date_debut           timestamp    ,
	date_fin             timestamp    ,
	CONSTRAINT montee_niveau_employe_pkey PRIMARY KEY ( id ),
	CONSTRAINT montee_niveau_employe_id_niveau_arrive_fkey FOREIGN KEY ( id_niveau_arrive ) REFERENCES "public".niveau( id )   ,
	CONSTRAINT montee_niveau_employe_id_niveau_depart_fkey FOREIGN KEY ( id_niveau_depart ) REFERENCES "public".niveau( id )   ,
	CONSTRAINT montee_niveau_employe_id_poste_fkey FOREIGN KEY ( id_poste ) REFERENCES "public".poste( id )   
 );

CREATE  TABLE "public".mouvement_materiau ( 
	id                   serial DEFAULT nextval('mouvement_materiau_id_seq'::regclass) NOT NULL  ,
	date_mouvement       timestamp    ,
	id_materiau          integer    ,
	quantite             double precision    ,
	prix_unitaire        double precision    ,
	type_mouvement       integer    ,
	id_mouvement_mere    integer    ,
	description          varchar(200)    ,
	id_mouvement_meuble  integer    ,
	CONSTRAINT mouvement_materiau_pkey PRIMARY KEY ( id ),
	CONSTRAINT mouvement_materiau_id_materiau_fkey FOREIGN KEY ( id_materiau ) REFERENCES "public".materiau( id )   
 );

CREATE  TABLE "public".multiplication_salarial_employe ( 
	id                   serial DEFAULT nextval('multiplication_salarial_employe_id_seq'::regclass) NOT NULL  ,
	id_poste             integer    ,
	id_niveau_depart     integer    ,
	id_niveau_arrive     integer    ,
	multipliant          double precision    ,
	date_debut           timestamp    ,
	date_fin             timestamp    ,
	CONSTRAINT multiplication_salarial_employe_pkey PRIMARY KEY ( id ),
	CONSTRAINT multiplication_salarial_employe_id_niveau_arrive_fkey FOREIGN KEY ( id_niveau_arrive ) REFERENCES "public".niveau( id )   ,
	CONSTRAINT multiplication_salarial_employe_id_niveau_depart_fkey FOREIGN KEY ( id_niveau_depart ) REFERENCES "public".niveau( id )   ,
	CONSTRAINT multiplication_salarial_employe_id_poste_fkey FOREIGN KEY ( id_poste ) REFERENCES "public".poste( id )   
 );

CREATE  TABLE "public".role_employe ( 
	id                   serial DEFAULT nextval('role_employe_id_seq'::regclass) NOT NULL  ,
	id_employe           integer    ,
	id_poste             integer    ,
	id_niveau            integer    ,
	date_debut           timestamp    ,
	date_fin             timestamp    ,
	taux_horaire         double precision    ,
	CONSTRAINT role_employe_pkey PRIMARY KEY ( id ),
	CONSTRAINT role_employe_id_niveau_fkey FOREIGN KEY ( id_niveau ) REFERENCES "public".niveau( id )   ,
	CONSTRAINT role_employe_id_poste_fkey FOREIGN KEY ( id_poste ) REFERENCES "public".poste( id )   ,
	CONSTRAINT role_employe_id_employe_fkey FOREIGN KEY ( id_employe ) REFERENCES "public".employe( id )   
 );

CREATE  TABLE "public".utilisation_employe ( 
	id                   serial DEFAULT nextval('utilisation_employe_id_seq'::regclass) NOT NULL  ,
	id_mouvement_meuble  integer    ,
	date_debut           timestamp    ,
	date_fin             timestamp    ,
	id_role_employe      integer    ,
	duree_utilisation    double precision    ,
	salaire_total        double precision    ,
	description          varchar(200)    ,
	CONSTRAINT utilisation_employe_pkey PRIMARY KEY ( id ),
	CONSTRAINT utilisation_employe_id_role_employe_fkey FOREIGN KEY ( id_role_employe ) REFERENCES "public".role_employe( id )   
 );

CREATE  TABLE "public".vente_meuble ( 
	id                   serial DEFAULT nextval('vente_meuble_id_seq'::regclass) NOT NULL  ,
	date_vente           timestamp    ,
	id_client            integer    ,
	prix_total           double precision    ,
	CONSTRAINT vente_meuble_pkey PRIMARY KEY ( id ),
	CONSTRAINT fk_vente_meuble_client FOREIGN KEY ( id_client ) REFERENCES "public".client( id )   
 );

CREATE  TABLE "public".formule_meuble ( 
	id                   serial DEFAULT nextval('formule_meuble_id_seq'::regclass) NOT NULL  ,
	id_meuble            integer    ,
	id_taille_meuble     integer    ,
	CONSTRAINT formule_meuble_pkey PRIMARY KEY ( id ),
	CONSTRAINT formule_meuble_id_taille_meuble_fkey FOREIGN KEY ( id_taille_meuble ) REFERENCES "public".taille_meuble( id )   ,
	CONSTRAINT formule_meuble_id_meuble_fkey FOREIGN KEY ( id_meuble ) REFERENCES "public".meuble( id )   
 );

CREATE  TABLE "public".mouvement_meuble ( 
	id                   serial DEFAULT nextval('mouvement_meuble_id_seq'::regclass) NOT NULL  ,
	date_mouvement       timestamp    ,
	id_formule_meuble    integer    ,
	quantite             double precision    ,
	type_mouvement       integer    ,
	id_mouvement_mere    integer    ,
	total_materiaux      double precision    ,
	total_salaires       double precision    ,
	prix_total           double precision    ,
	prix_unitaire        double precision    ,
	id_detail_vente_meuble integer    ,
	description          varchar(200)    ,
	CONSTRAINT mouvement_meuble_pkey PRIMARY KEY ( id ),
	CONSTRAINT mouvement_meuble_id_formule_meuble_fkey FOREIGN KEY ( id_formule_meuble ) REFERENCES "public".formule_meuble( id )   
 );

CREATE  TABLE "public".prix_de_vente_meuble ( 
	id                   serial DEFAULT nextval('prix_de_vente_meuble_id_seq'::regclass) NOT NULL  ,
	id_formule_meuble    integer    ,
	date_debut           timestamp    ,
	date_fin             timestamp    ,
	valeur               double precision    ,
	CONSTRAINT prix_de_vente_meuble_pkey PRIMARY KEY ( id ),
	CONSTRAINT prix_de_vente_meuble_id_formule_meuble_fkey FOREIGN KEY ( id_formule_meuble ) REFERENCES "public".formule_meuble( id )   
 );

CREATE  TABLE "public".detail_employe_meuble ( 
	id                   serial DEFAULT nextval('detail_employe_meuble_id_seq'::regclass) NOT NULL  ,
	id_formule_meuble    integer    ,
	id_poste             integer    ,
	id_niveau            integer    ,
	nombre               integer    ,
	duree                double precision    ,
	CONSTRAINT detail_employe_meuble_pkey PRIMARY KEY ( id ),
	CONSTRAINT detail_employe_meuble_id_niveau_fkey FOREIGN KEY ( id_niveau ) REFERENCES "public".niveau( id )   ,
	CONSTRAINT detail_employe_meuble_id_poste_fkey FOREIGN KEY ( id_poste ) REFERENCES "public".poste( id )   ,
	CONSTRAINT detail_employe_meuble_id_formule_meuble_fkey FOREIGN KEY ( id_formule_meuble ) REFERENCES "public".formule_meuble( id )   
 );

CREATE  TABLE "public".detail_formule_meuble ( 
	id                   serial DEFAULT nextval('detail_formule_meuble_id_seq'::regclass) NOT NULL  ,
	id_formule_meuble    integer    ,
	id_materiau          integer    ,
	quantite             double precision    ,
	CONSTRAINT detail_formule_meuble_pkey PRIMARY KEY ( id ),
	CONSTRAINT detail_formule_meuble_id_materiau_fkey FOREIGN KEY ( id_materiau ) REFERENCES "public".materiau( id )   ,
	CONSTRAINT detail_formule_meuble_id_formule_meuble_fkey FOREIGN KEY ( id_formule_meuble ) REFERENCES "public".formule_meuble( id )   
 );

CREATE  TABLE "public".detail_vente_meuble ( 
	id                   serial DEFAULT nextval('detail_vente_meuble_id_seq'::regclass) NOT NULL  ,
	id_vente_meuble      integer    ,
	id_formule_meuble    integer    ,
	quantite             double precision    ,
	prix_unitaire        double precision    ,
	prix_total           double precision    ,
	CONSTRAINT detail_vente_meuble_pkey PRIMARY KEY ( id ),
	CONSTRAINT detail_vente_meuble_id_formule_meuble_fkey FOREIGN KEY ( id_formule_meuble ) REFERENCES "public".formule_meuble( id )   
 );

CREATE OR REPLACE FUNCTION public.connectby(text, text, text, text, integer, text)
 RETURNS SETOF record
 LANGUAGE c
 STABLE STRICT
AS '$libdir/tablefunc', $function$connectby_text$function$
;

CREATE OR REPLACE FUNCTION public.connectby(text, text, text, text, integer)
 RETURNS SETOF record
 LANGUAGE c
 STABLE STRICT
AS '$libdir/tablefunc', $function$connectby_text$function$
;

CREATE OR REPLACE FUNCTION public.connectby(text, text, text, text, text, integer, text)
 RETURNS SETOF record
 LANGUAGE c
 STABLE STRICT
AS '$libdir/tablefunc', $function$connectby_text_serial$function$
;

CREATE OR REPLACE FUNCTION public.connectby(text, text, text, text, text, integer)
 RETURNS SETOF record
 LANGUAGE c
 STABLE STRICT
AS '$libdir/tablefunc', $function$connectby_text_serial$function$
;

CREATE OR REPLACE FUNCTION public.crosstab(text)
 RETURNS SETOF record
 LANGUAGE c
 STABLE STRICT
AS '$libdir/tablefunc', $function$crosstab$function$
;

CREATE OR REPLACE FUNCTION public.crosstab(text, integer)
 RETURNS SETOF record
 LANGUAGE c
 STABLE STRICT
AS '$libdir/tablefunc', $function$crosstab$function$
;

CREATE OR REPLACE FUNCTION public.crosstab(text, text)
 RETURNS SETOF record
 LANGUAGE c
 STABLE STRICT
AS '$libdir/tablefunc', $function$crosstab_hash$function$
;

CREATE OR REPLACE FUNCTION public.crosstab2(text)
 RETURNS SETOF tablefunc_crosstab_2
 LANGUAGE c
 STABLE STRICT
AS '$libdir/tablefunc', $function$crosstab$function$
;

CREATE OR REPLACE FUNCTION public.crosstab3(text)
 RETURNS SETOF tablefunc_crosstab_3
 LANGUAGE c
 STABLE STRICT
AS '$libdir/tablefunc', $function$crosstab$function$
;

CREATE OR REPLACE FUNCTION public.crosstab4(text)
 RETURNS SETOF tablefunc_crosstab_4
 LANGUAGE c
 STABLE STRICT
AS '$libdir/tablefunc', $function$crosstab$function$
;

CREATE OR REPLACE FUNCTION public.dynamic_level_query()
 RETURNS TABLE(id_poste integer, nom_poste character varying, date_debut timestamp without time zone, valeur integer, level_name character varying, level_value integer)
 LANGUAGE plpgsql
AS $function$
DECLARE
    level_cursor CURSOR FOR SELECT id, nom, ordre FROM niveau;
    level_record RECORD;
BEGIN
    FOR level_record IN level_cursor
    LOOP
        RETURN QUERY
        EXECUTE
        'SELECT
            v.id_poste,
            v.nom_poste,
            v.date_debut,
            v.valeur,
            $1 AS level_name,
            $2 AS level_value
        FROM
            v_base_taux_horaire v
        WHERE
            v.date_fin = ''9999-12-31 23:59''
            AND v.valeur = $2'
        USING level_record.nom, level_record.ordre;
    END LOOP;
END;
$function$
;

CREATE OR REPLACE FUNCTION public.normal_rand(integer, double precision, double precision)
 RETURNS SETOF double precision
 LANGUAGE c
 STRICT
AS '$libdir/tablefunc', $function$normal_rand$function$
;

CREATE OR REPLACE VIEW "public".v_base_taux_horaire AS SELECT bth.id,     bth.id_poste,     bth.date_debut,     bth.date_fin,     bth.valeur,     p.nom AS nom_poste    FROM (base_taux_horaire bth      JOIN poste p ON ((p.id = bth.id_poste)))
 SELECT bth.id,
    bth.id_poste,
    bth.date_debut,
    bth.date_fin,
    bth.valeur,
    p.nom AS nom_poste
   FROM (base_taux_horaire bth
     JOIN poste p ON ((p.id = bth.id_poste)));

CREATE OR REPLACE VIEW "public".v_benefice_meuble AS SELECT q3.id_formule_meuble,     q3.prix_total_materiau,     q4.valeur AS prix_de_vente,     fm.id_meuble,     fm.id_taille_meuble,     m.nom AS nom_meuble,     tm.nom AS nom_taille_meuble    FROM ((((( SELECT q2.id_formule_meuble,             sum((q2.quantite * q2.prix_unitaire_moyen)) AS prix_total_materiau            FROM ( SELECT dfm.id_formule_meuble,                     dfm.id_materiau,                     dfm.quantite,                     q1.prix_unitaire_moyen                    FROM (detail_formule_meuble dfm                      JOIN ( SELECT v_materiau_restant.id_materiau,                             avg(v_materiau_restant.prix_unitaire) AS prix_unitaire_moyen                            FROM v_materiau_restant                           GROUP BY v_materiau_restant.id_materiau) q1 ON ((q1.id_materiau = dfm.id_materiau)))) q2           GROUP BY q2.id_formule_meuble) q3      JOIN ( SELECT prix_de_vente_meuble.id_formule_meuble,             prix_de_vente_meuble.valeur            FROM prix_de_vente_meuble           WHERE (prix_de_vente_meuble.date_fin = '9999-12-31 23:59:00'::timestamp without time zone)) q4 ON ((q4.id_formule_meuble = q3.id_formule_meuble)))      JOIN formule_meuble fm ON ((fm.id = q3.id_formule_meuble)))      JOIN meuble m ON ((m.id = fm.id_meuble)))      JOIN taille_meuble tm ON ((tm.id = fm.id_taille_meuble)))
 SELECT q3.id_formule_meuble,
    q3.prix_total_materiau,
    q4.valeur AS prix_de_vente,
    fm.id_meuble,
    fm.id_taille_meuble,
    m.nom AS nom_meuble,
    tm.nom AS nom_taille_meuble
   FROM ((((( SELECT q2.id_formule_meuble,
            sum((q2.quantite * q2.prix_unitaire_moyen)) AS prix_total_materiau
           FROM ( SELECT dfm.id_formule_meuble,
                    dfm.id_materiau,
                    dfm.quantite,
                    q1.prix_unitaire_moyen
                   FROM (detail_formule_meuble dfm
                     JOIN ( SELECT v_materiau_restant.id_materiau,
                            avg(v_materiau_restant.prix_unitaire) AS prix_unitaire_moyen
                           FROM v_materiau_restant
                          GROUP BY v_materiau_restant.id_materiau) q1 ON ((q1.id_materiau = dfm.id_materiau)))) q2
          GROUP BY q2.id_formule_meuble) q3
     JOIN ( SELECT prix_de_vente_meuble.id_formule_meuble,
            prix_de_vente_meuble.valeur
           FROM prix_de_vente_meuble
          WHERE (prix_de_vente_meuble.date_fin = '9999-12-31 23:59:00'::timestamp without time zone)) q4 ON ((q4.id_formule_meuble = q3.id_formule_meuble)))
     JOIN formule_meuble fm ON ((fm.id = q3.id_formule_meuble)))
     JOIN meuble m ON ((m.id = fm.id_meuble)))
     JOIN taille_meuble tm ON ((tm.id = fm.id_taille_meuble)));

CREATE OR REPLACE VIEW "public".v_client AS SELECT c.id,     c.nom,     c.prenom,     c.telephone,     c.id_genre,     c.date_entree,     g.nom AS genre    FROM (client c      JOIN genre g ON ((g.id = c.id_genre)))
 SELECT c.id,
    c.nom,
    c.prenom,
    c.telephone,
    c.id_genre,
    c.date_entree,
    g.nom AS genre
   FROM (client c
     JOIN genre g ON ((g.id = c.id_genre)));

CREATE OR REPLACE VIEW "public".v_detail_employe_meuble AS SELECT dem.id,     dem.id_formule_meuble,     dem.id_poste,     dem.id_niveau,     dem.nombre,     dem.duree,     p.nom AS nom_poste,     n.nom AS nom_niveau,     n.ordre AS ordre_niveau    FROM ((detail_employe_meuble dem      JOIN poste p ON ((p.id = dem.id_poste)))      JOIN niveau n ON ((n.id = dem.id_niveau)))
 SELECT dem.id,
    dem.id_formule_meuble,
    dem.id_poste,
    dem.id_niveau,
    dem.nombre,
    dem.duree,
    p.nom AS nom_poste,
    n.nom AS nom_niveau,
    n.ordre AS ordre_niveau
   FROM ((detail_employe_meuble dem
     JOIN poste p ON ((p.id = dem.id_poste)))
     JOIN niveau n ON ((n.id = dem.id_niveau)));

CREATE OR REPLACE VIEW "public".v_detail_formule_meuble AS SELECT dfm.id,     dfm.id_formule_meuble,     dfm.id_materiau,     dfm.quantite,     m.nom AS nom_materiau    FROM (detail_formule_meuble dfm      JOIN materiau m ON ((m.id = dfm.id_materiau)))
 SELECT dfm.id,
    dfm.id_formule_meuble,
    dfm.id_materiau,
    dfm.quantite,
    m.nom AS nom_materiau
   FROM (detail_formule_meuble dfm
     JOIN materiau m ON ((m.id = dfm.id_materiau)));

CREATE OR REPLACE VIEW "public".v_detail_vente_meuble AS SELECT dvm.id,     dvm.id_vente_meuble,     dvm.id_formule_meuble,     dvm.quantite,     dvm.prix_unitaire,     dvm.prix_total,     vfm.id_meuble,     vfm.id_taille_meuble,     vfm.nom_meuble,     vfm.nom_taille_meuble    FROM (detail_vente_meuble dvm      JOIN v_formule_meuble vfm ON ((vfm.id = dvm.id_formule_meuble)))
 SELECT dvm.id,
    dvm.id_vente_meuble,
    dvm.id_formule_meuble,
    dvm.quantite,
    dvm.prix_unitaire,
    dvm.prix_total,
    vfm.id_meuble,
    vfm.id_taille_meuble,
    vfm.nom_meuble,
    vfm.nom_taille_meuble
   FROM (detail_vente_meuble dvm
     JOIN v_formule_meuble vfm ON ((vfm.id = dvm.id_formule_meuble)));

CREATE OR REPLACE VIEW "public".v_employe AS SELECT e.id,     e.nom,     e.prenom,     e.date_naissance,     e.id_genre,     e.date_entree,     g.nom AS genre    FROM (employe e      JOIN genre g ON ((g.id = e.id_genre)))
 SELECT e.id,
    e.nom,
    e.prenom,
    e.date_naissance,
    e.id_genre,
    e.date_entree,
    g.nom AS genre
   FROM (employe e
     JOIN genre g ON ((g.id = e.id_genre)));

CREATE OR REPLACE VIEW "public".v_formule_meuble AS SELECT fm.id,     fm.id_meuble,     fm.id_taille_meuble,     tm.nom AS nom_taille_meuble,     m.nom AS nom_meuble    FROM ((formule_meuble fm      JOIN taille_meuble tm ON ((tm.id = fm.id_taille_meuble)))      JOIN meuble m ON ((m.id = fm.id_meuble)))
 SELECT fm.id,
    fm.id_meuble,
    fm.id_taille_meuble,
    tm.nom AS nom_taille_meuble,
    m.nom AS nom_meuble
   FROM ((formule_meuble fm
     JOIN taille_meuble tm ON ((tm.id = fm.id_taille_meuble)))
     JOIN meuble m ON ((m.id = fm.id_meuble)));

CREATE OR REPLACE VIEW "public".v_map_genre AS SELECT fm.id AS id_formule_meuble,     g.id AS id_genre,     g.nom AS nom_genre    FROM (formule_meuble fm      CROSS JOIN genre g)
 SELECT fm.id AS id_formule_meuble,
    g.id AS id_genre,
    g.nom AS nom_genre
   FROM (formule_meuble fm
     CROSS JOIN genre g);

CREATE OR REPLACE VIEW "public".v_materiau AS SELECT m.id,     m.nom,     m.id_type_materiau,     m.description,     tm.nom AS nom_type_materiau    FROM (materiau m      JOIN type_materiau tm ON ((m.id_type_materiau = tm.id)))
 SELECT m.id,
    m.nom,
    m.id_type_materiau,
    m.description,
    tm.nom AS nom_type_materiau
   FROM (materiau m
     JOIN type_materiau tm ON ((m.id_type_materiau = tm.id)));

CREATE OR REPLACE VIEW "public".v_materiau_possible_style_meuble AS SELECT mpsm.id,     mpsm.id_style_meuble,     mpsm.id_materiau,     m.nom AS nom_materiau,     m.id_type_materiau,     tm.nom AS nom_type_materiau    FROM ((materiau_possible_style_meuble mpsm      JOIN materiau m ON ((m.id = mpsm.id_materiau)))      JOIN type_materiau tm ON ((tm.id = m.id_type_materiau)))
 SELECT mpsm.id,
    mpsm.id_style_meuble,
    mpsm.id_materiau,
    m.nom AS nom_materiau,
    m.id_type_materiau,
    tm.nom AS nom_type_materiau
   FROM ((materiau_possible_style_meuble mpsm
     JOIN materiau m ON ((m.id = mpsm.id_materiau)))
     JOIN type_materiau tm ON ((tm.id = m.id_type_materiau)));

CREATE OR REPLACE VIEW "public".v_materiau_restant AS SELECT q3.id,     q3.date_mouvement,     q3.id_materiau,     q3.quantite,     q3.prix_unitaire    FROM ( SELECT q2.id,             q2.date_mouvement,             q2.id_materiau,             (q2.q_e - q2.t_q_s) AS quantite,             q2.p_e AS prix_unitaire            FROM ( SELECT q1.id,                     q1.date_mouvement,                     q1.id_materiau,                     q1.q_e,                     q1.p_e,                     sum(q1.q_s) AS t_q_s                    FROM ( SELECT mm_e.id,                             mm_e.date_mouvement,                             mm_e.id_materiau,                             mm_e.quantite AS q_e,                             mm_e.prix_unitaire AS p_e,                             COALESCE(mm_s.quantite, (0)::double precision) AS q_s                            FROM (( SELECT mouvement_materiau.id,                                     mouvement_materiau.date_mouvement,                                     mouvement_materiau.id_materiau,                                     mouvement_materiau.quantite,                                     mouvement_materiau.prix_unitaire                                    FROM mouvement_materiau                                   WHERE (mouvement_materiau.type_mouvement = 1)) mm_e                              LEFT JOIN ( SELECT mouvement_materiau.id_mouvement_mere,                                     mouvement_materiau.quantite                                    FROM mouvement_materiau                                   WHERE (mouvement_materiau.type_mouvement = '-1'::integer)) mm_s ON ((mm_e.id = mm_s.id_mouvement_mere)))) q1                   GROUP BY q1.id, q1.date_mouvement, q1.id_materiau, q1.q_e, q1.p_e) q2           ORDER BY q2.date_mouvement) q3   WHERE (q3.quantite > (0)::double precision)
 SELECT q3.id,
    q3.date_mouvement,
    q3.id_materiau,
    q3.quantite,
    q3.prix_unitaire
   FROM ( SELECT q2.id,
            q2.date_mouvement,
            q2.id_materiau,
            (q2.q_e - q2.t_q_s) AS quantite,
            q2.p_e AS prix_unitaire
           FROM ( SELECT q1.id,
                    q1.date_mouvement,
                    q1.id_materiau,
                    q1.q_e,
                    q1.p_e,
                    sum(q1.q_s) AS t_q_s
                   FROM ( SELECT mm_e.id,
                            mm_e.date_mouvement,
                            mm_e.id_materiau,
                            mm_e.quantite AS q_e,
                            mm_e.prix_unitaire AS p_e,
                            COALESCE(mm_s.quantite, (0)::double precision) AS q_s
                           FROM (( SELECT mouvement_materiau.id,
                                    mouvement_materiau.date_mouvement,
                                    mouvement_materiau.id_materiau,
                                    mouvement_materiau.quantite,
                                    mouvement_materiau.prix_unitaire
                                   FROM mouvement_materiau
                                  WHERE (mouvement_materiau.type_mouvement = 1)) mm_e
                             LEFT JOIN ( SELECT mouvement_materiau.id_mouvement_mere,
                                    mouvement_materiau.quantite
                                   FROM mouvement_materiau
                                  WHERE (mouvement_materiau.type_mouvement = '-1'::integer)) mm_s ON ((mm_e.id = mm_s.id_mouvement_mere)))) q1
                  GROUP BY q1.id, q1.date_mouvement, q1.id_materiau, q1.q_e, q1.p_e) q2
          ORDER BY q2.date_mouvement) q3
  WHERE (q3.quantite > (0)::double precision);

CREATE OR REPLACE VIEW "public".v_meuble AS SELECT m.id,     m.nom,     m.id_style_meuble,     m.id_categorie_meuble,     m.description,     sm.nom AS nom_style_meuble,     cm.nom AS nom_categorie_meuble    FROM ((meuble m      JOIN style_meuble sm ON ((sm.id = m.id_style_meuble)))      JOIN categorie_meuble cm ON ((cm.id = m.id_categorie_meuble)))
 SELECT m.id,
    m.nom,
    m.id_style_meuble,
    m.id_categorie_meuble,
    m.description,
    sm.nom AS nom_style_meuble,
    cm.nom AS nom_categorie_meuble
   FROM ((meuble m
     JOIN style_meuble sm ON ((sm.id = m.id_style_meuble)))
     JOIN categorie_meuble cm ON ((cm.id = m.id_categorie_meuble)));

CREATE OR REPLACE VIEW "public".v_meuble_contenant_materiau AS SELECT fm.id,     fm.id_meuble,     fm.id_taille_meuble,     vm.nom AS nom_meuble,     vm.id_style_meuble,     vm.id_categorie_meuble,     vm.nom_style_meuble,     vm.nom_categorie_meuble,     dfm.quantite,     dfm.id_materiau,     tm.nom AS nom_taille_meuble    FROM (((detail_formule_meuble dfm      JOIN formule_meuble fm ON ((fm.id = dfm.id_formule_meuble)))      JOIN v_meuble vm ON ((vm.id = fm.id_meuble)))      JOIN taille_meuble tm ON ((tm.id = fm.id_taille_meuble)))
 SELECT fm.id,
    fm.id_meuble,
    fm.id_taille_meuble,
    vm.nom AS nom_meuble,
    vm.id_style_meuble,
    vm.id_categorie_meuble,
    vm.nom_style_meuble,
    vm.nom_categorie_meuble,
    dfm.quantite,
    dfm.id_materiau,
    tm.nom AS nom_taille_meuble
   FROM (((detail_formule_meuble dfm
     JOIN formule_meuble fm ON ((fm.id = dfm.id_formule_meuble)))
     JOIN v_meuble vm ON ((vm.id = fm.id_meuble)))
     JOIN taille_meuble tm ON ((tm.id = fm.id_taille_meuble)));

CREATE OR REPLACE VIEW "public".v_meuble_restant AS SELECT q3.id,     q3.date_mouvement,     q3.id_formule_meuble,     q3.prix_unitaire,     q3.quantite    FROM ( SELECT q2.id,             q2.date_mouvement,             q2.id_formule_meuble,             q2.prix_unitaire,             (q2.q_e - q2.tqs) AS quantite            FROM ( SELECT q1.id,                     q1.date_mouvement,                     q1.id_formule_meuble,                     q1.prix_unitaire,                     q1.q_e,                     sum(q1.q_s) AS tqs                    FROM ( SELECT mme.id,                             mme.date_mouvement,                             mme.id_formule_meuble,                             mme.q_e,                             mme.prix_unitaire,                             COALESCE(mms.q_s, (0)::double precision) AS q_s                            FROM (( SELECT mouvement_meuble.id,                                     mouvement_meuble.date_mouvement,                                     mouvement_meuble.id_formule_meuble,                                     mouvement_meuble.quantite AS q_e,                                     mouvement_meuble.prix_unitaire                                    FROM mouvement_meuble                                   WHERE (mouvement_meuble.type_mouvement = 1)) mme                              LEFT JOIN ( SELECT mouvement_meuble.quantite AS q_s,                                     mouvement_meuble.id_mouvement_mere                                    FROM mouvement_meuble                                   WHERE (mouvement_meuble.type_mouvement = '-1'::integer)) mms ON ((mme.id = mms.id_mouvement_mere)))) q1                   GROUP BY q1.id, q1.date_mouvement, q1.id_formule_meuble, q1.prix_unitaire, q1.q_e                   ORDER BY q1.date_mouvement) q2) q3   WHERE (q3.quantite > (0)::double precision)
 SELECT q3.id,
    q3.date_mouvement,
    q3.id_formule_meuble,
    q3.prix_unitaire,
    q3.quantite
   FROM ( SELECT q2.id,
            q2.date_mouvement,
            q2.id_formule_meuble,
            q2.prix_unitaire,
            (q2.q_e - q2.tqs) AS quantite
           FROM ( SELECT q1.id,
                    q1.date_mouvement,
                    q1.id_formule_meuble,
                    q1.prix_unitaire,
                    q1.q_e,
                    sum(q1.q_s) AS tqs
                   FROM ( SELECT mme.id,
                            mme.date_mouvement,
                            mme.id_formule_meuble,
                            mme.q_e,
                            mme.prix_unitaire,
                            COALESCE(mms.q_s, (0)::double precision) AS q_s
                           FROM (( SELECT mouvement_meuble.id,
                                    mouvement_meuble.date_mouvement,
                                    mouvement_meuble.id_formule_meuble,
                                    mouvement_meuble.quantite AS q_e,
                                    mouvement_meuble.prix_unitaire
                                   FROM mouvement_meuble
                                  WHERE (mouvement_meuble.type_mouvement = 1)) mme
                             LEFT JOIN ( SELECT mouvement_meuble.quantite AS q_s,
                                    mouvement_meuble.id_mouvement_mere
                                   FROM mouvement_meuble
                                  WHERE (mouvement_meuble.type_mouvement = '-1'::integer)) mms ON ((mme.id = mms.id_mouvement_mere)))) q1
                  GROUP BY q1.id, q1.date_mouvement, q1.id_formule_meuble, q1.prix_unitaire, q1.q_e
                  ORDER BY q1.date_mouvement) q2) q3
  WHERE (q3.quantite > (0)::double precision);

CREATE OR REPLACE VIEW "public".v_montee_niveau_employe AS SELECT mne.id,     mne.id_poste,     mne.id_niveau_depart,     mne.id_niveau_arrive,     mne.duree,     mne.date_debut,     mne.date_fin,     p.nom AS nom_poste,     n1.nom AS nom_niveau_depart,     n2.nom AS nom_niveau_arrive    FROM (((montee_niveau_employe mne      JOIN poste p ON ((p.id = mne.id_poste)))      JOIN niveau n1 ON ((n1.id = mne.id_niveau_depart)))      JOIN niveau n2 ON ((n2.id = mne.id_niveau_arrive)))
 SELECT mne.id,
    mne.id_poste,
    mne.id_niveau_depart,
    mne.id_niveau_arrive,
    mne.duree,
    mne.date_debut,
    mne.date_fin,
    p.nom AS nom_poste,
    n1.nom AS nom_niveau_depart,
    n2.nom AS nom_niveau_arrive
   FROM (((montee_niveau_employe mne
     JOIN poste p ON ((p.id = mne.id_poste)))
     JOIN niveau n1 ON ((n1.id = mne.id_niveau_depart)))
     JOIN niveau n2 ON ((n2.id = mne.id_niveau_arrive)));

CREATE OR REPLACE VIEW "public".v_mouvement_materiau AS SELECT mm.id,     mm.date_mouvement,     mm.id_materiau,     mm.quantite,     mm.prix_unitaire,     mm.type_mouvement,     mm.id_mouvement_mere,     mm.description,     mm.id_mouvement_meuble,     m.id_type_materiau,     m.nom AS nom_materiau,     tm.nom AS nom_type_materiau,     (mm.prix_unitaire * mm.quantite) AS prix_total    FROM ((mouvement_materiau mm      JOIN materiau m ON ((m.id = mm.id_materiau)))      JOIN type_materiau tm ON ((tm.id = m.id_type_materiau)))   ORDER BY mm.date_mouvement DESC
 SELECT mm.id,
    mm.date_mouvement,
    mm.id_materiau,
    mm.quantite,
    mm.prix_unitaire,
    mm.type_mouvement,
    mm.id_mouvement_mere,
    mm.description,
    mm.id_mouvement_meuble,
    m.id_type_materiau,
    m.nom AS nom_materiau,
    tm.nom AS nom_type_materiau,
    (mm.prix_unitaire * mm.quantite) AS prix_total
   FROM ((mouvement_materiau mm
     JOIN materiau m ON ((m.id = mm.id_materiau)))
     JOIN type_materiau tm ON ((tm.id = m.id_type_materiau)))
  ORDER BY mm.date_mouvement DESC;

CREATE OR REPLACE VIEW "public".v_mouvement_meuble AS SELECT mm.id,     mm.date_mouvement,     mm.id_formule_meuble,     mm.quantite,     mm.type_mouvement,     mm.id_mouvement_mere,     mm.total_materiaux,     mm.total_salaires,     mm.prix_total,     mm.prix_unitaire,     mm.id_detail_vente_meuble,     mm.description,     fm.id_taille_meuble,     fm.id_meuble,     tm.nom AS nom_taille_meuble,     vm.nom AS nom_meuble,     vm.id_style_meuble,     vm.id_categorie_meuble,     vm.nom_style_meuble,     vm.nom_categorie_meuble    FROM (((mouvement_meuble mm      JOIN formule_meuble fm ON ((mm.id_formule_meuble = fm.id)))      JOIN v_meuble vm ON ((vm.id = fm.id_meuble)))      JOIN taille_meuble tm ON ((tm.id = fm.id_taille_meuble)))   ORDER BY mm.date_mouvement DESC
 SELECT mm.id,
    mm.date_mouvement,
    mm.id_formule_meuble,
    mm.quantite,
    mm.type_mouvement,
    mm.id_mouvement_mere,
    mm.total_materiaux,
    mm.total_salaires,
    mm.prix_total,
    mm.prix_unitaire,
    mm.id_detail_vente_meuble,
    mm.description,
    fm.id_taille_meuble,
    fm.id_meuble,
    tm.nom AS nom_taille_meuble,
    vm.nom AS nom_meuble,
    vm.id_style_meuble,
    vm.id_categorie_meuble,
    vm.nom_style_meuble,
    vm.nom_categorie_meuble
   FROM (((mouvement_meuble mm
     JOIN formule_meuble fm ON ((mm.id_formule_meuble = fm.id)))
     JOIN v_meuble vm ON ((vm.id = fm.id_meuble)))
     JOIN taille_meuble tm ON ((tm.id = fm.id_taille_meuble)))
  ORDER BY mm.date_mouvement DESC;

CREATE OR REPLACE VIEW "public".v_multiplication_salarial_employe AS SELECT mne.id,     mne.id_poste,     mne.id_niveau_depart,     mne.id_niveau_arrive,     mne.multipliant,     mne.date_debut,     mne.date_fin,     p.nom AS nom_poste,     n1.nom AS nom_niveau_depart,     n2.nom AS nom_niveau_arrive    FROM (((multiplication_salarial_employe mne      JOIN poste p ON ((p.id = mne.id_poste)))      JOIN niveau n1 ON ((n1.id = mne.id_niveau_depart)))      JOIN niveau n2 ON ((n2.id = mne.id_niveau_arrive)))
 SELECT mne.id,
    mne.id_poste,
    mne.id_niveau_depart,
    mne.id_niveau_arrive,
    mne.multipliant,
    mne.date_debut,
    mne.date_fin,
    p.nom AS nom_poste,
    n1.nom AS nom_niveau_depart,
    n2.nom AS nom_niveau_arrive
   FROM (((multiplication_salarial_employe mne
     JOIN poste p ON ((p.id = mne.id_poste)))
     JOIN niveau n1 ON ((n1.id = mne.id_niveau_depart)))
     JOIN niveau n2 ON ((n2.id = mne.id_niveau_arrive)));

CREATE OR REPLACE VIEW "public".v_prix_de_vente_meuble AS SELECT pdvm.id,     pdvm.date_debut,     pdvm.date_fin,     pdvm.valeur,     fm.id AS id_formule_meuble,     fm.id_meuble,     fm.id_taille_meuble,     m.nom AS nom_meuble,     tm.nom AS nom_taille_meuble    FROM (((prix_de_vente_meuble pdvm      JOIN formule_meuble fm ON ((fm.id = pdvm.id_formule_meuble)))      JOIN meuble m ON ((m.id = fm.id_meuble)))      JOIN taille_meuble tm ON ((tm.id = fm.id_taille_meuble)))
 SELECT pdvm.id,
    pdvm.date_debut,
    pdvm.date_fin,
    pdvm.valeur,
    fm.id AS id_formule_meuble,
    fm.id_meuble,
    fm.id_taille_meuble,
    m.nom AS nom_meuble,
    tm.nom AS nom_taille_meuble
   FROM (((prix_de_vente_meuble pdvm
     JOIN formule_meuble fm ON ((fm.id = pdvm.id_formule_meuble)))
     JOIN meuble m ON ((m.id = fm.id_meuble)))
     JOIN taille_meuble tm ON ((tm.id = fm.id_taille_meuble)));

CREATE OR REPLACE VIEW "public".v_prix_fabrication_meuble AS SELECT q2.id_formule_meuble,     q2.id_meuble,     q2.id_taille_meuble,     q2.prix_fabrication,     m.nom AS nom_meuble,     tm.nom AS nom_taille_meuble    FROM ((( SELECT q1.id_formule_meuble,             fm.id_meuble,             fm.id_taille_meuble,             q1.prix_fabrication            FROM (formule_meuble fm              JOIN ( SELECT dfm.id_formule_meuble,                     sum((dfm.quantite * p.prix_unitaire)) AS prix_fabrication                    FROM (detail_formule_meuble dfm                      JOIN ( SELECT v_materiau_restant.id_materiau,                             avg(v_materiau_restant.prix_unitaire) AS prix_unitaire                            FROM v_materiau_restant                           GROUP BY v_materiau_restant.id_materiau) p ON ((p.id_materiau = dfm.id_materiau)))                   GROUP BY dfm.id_formule_meuble) q1 ON ((q1.id_formule_meuble = fm.id)))) q2      JOIN meuble m ON ((m.id = q2.id_meuble)))      JOIN taille_meuble tm ON ((tm.id = q2.id_taille_meuble)))
 SELECT q2.id_formule_meuble,
    q2.id_meuble,
    q2.id_taille_meuble,
    q2.prix_fabrication,
    m.nom AS nom_meuble,
    tm.nom AS nom_taille_meuble
   FROM ((( SELECT q1.id_formule_meuble,
            fm.id_meuble,
            fm.id_taille_meuble,
            q1.prix_fabrication
           FROM (formule_meuble fm
             JOIN ( SELECT dfm.id_formule_meuble,
                    sum((dfm.quantite * p.prix_unitaire)) AS prix_fabrication
                   FROM (detail_formule_meuble dfm
                     JOIN ( SELECT v_materiau_restant.id_materiau,
                            avg(v_materiau_restant.prix_unitaire) AS prix_unitaire
                           FROM v_materiau_restant
                          GROUP BY v_materiau_restant.id_materiau) p ON ((p.id_materiau = dfm.id_materiau)))
                  GROUP BY dfm.id_formule_meuble) q1 ON ((q1.id_formule_meuble = fm.id)))) q2
     JOIN meuble m ON ((m.id = q2.id_meuble)))
     JOIN taille_meuble tm ON ((tm.id = q2.id_taille_meuble)));

CREATE OR REPLACE VIEW "public".v_role_employe AS SELECT re.id,     re.id_employe,     re.id_poste,     re.id_niveau,     re.date_debut,     re.date_fin,     re.taux_horaire,     ve.nom AS nom_employe,     ve.prenom AS prenom_employe,     ve.date_naissance AS date_naissance_employe,     ve.id_genre AS id_genre_employe,     ve.date_entree AS date_entree_employe,     ve.genre AS nom_genre_employe,     p.nom AS nom_poste,     n.nom AS nom_niveau,     n.ordre AS ordre_niveau    FROM (((role_employe re      JOIN v_employe ve ON ((ve.id = re.id_employe)))      JOIN poste p ON ((p.id = re.id_poste)))      JOIN niveau n ON ((n.id = re.id_niveau)))
 SELECT re.id,
    re.id_employe,
    re.id_poste,
    re.id_niveau,
    re.date_debut,
    re.date_fin,
    re.taux_horaire,
    ve.nom AS nom_employe,
    ve.prenom AS prenom_employe,
    ve.date_naissance AS date_naissance_employe,
    ve.id_genre AS id_genre_employe,
    ve.date_entree AS date_entree_employe,
    ve.genre AS nom_genre_employe,
    p.nom AS nom_poste,
    n.nom AS nom_niveau,
    n.ordre AS ordre_niveau
   FROM (((role_employe re
     JOIN v_employe ve ON ((ve.id = re.id_employe)))
     JOIN poste p ON ((p.id = re.id_poste)))
     JOIN niveau n ON ((n.id = re.id_niveau)));

CREATE OR REPLACE VIEW "public".v_utilisation_employe AS SELECT ue.id,     ue.id_mouvement_meuble,     ue.date_debut,     ue.date_fin,     ue.id_role_employe,     ue.duree_utilisation,     ue.salaire_total,     ue.description,     re.id_employe,     re.id_poste,     re.id_niveau,     re.taux_horaire,     e.nom AS nom_employe,     e.prenom AS prenom_employe,     e.genre AS nom_genre_employe,     e.id_genre AS id_genre_employe,     p.nom AS nom_poste,     n.nom AS nom_niveau    FROM ((((utilisation_employe ue      JOIN role_employe re ON ((re.id = ue.id_role_employe)))      JOIN v_employe e ON ((e.id = re.id_employe)))      JOIN poste p ON ((p.id = re.id_poste)))      JOIN niveau n ON ((n.id = re.id_niveau)))
 SELECT ue.id,
    ue.id_mouvement_meuble,
    ue.date_debut,
    ue.date_fin,
    ue.id_role_employe,
    ue.duree_utilisation,
    ue.salaire_total,
    ue.description,
    re.id_employe,
    re.id_poste,
    re.id_niveau,
    re.taux_horaire,
    e.nom AS nom_employe,
    e.prenom AS prenom_employe,
    e.genre AS nom_genre_employe,
    e.id_genre AS id_genre_employe,
    p.nom AS nom_poste,
    n.nom AS nom_niveau
   FROM ((((utilisation_employe ue
     JOIN role_employe re ON ((re.id = ue.id_role_employe)))
     JOIN v_employe e ON ((e.id = re.id_employe)))
     JOIN poste p ON ((p.id = re.id_poste)))
     JOIN niveau n ON ((n.id = re.id_niveau)));

CREATE OR REPLACE VIEW "public".v_vente_enregistre AS SELECT dvm.id_formule_meuble,     sum(dvm.quantite) AS quantite,     vc.id_genre    FROM ((detail_vente_meuble dvm      JOIN vente_meuble vm ON ((vm.id = dvm.id_vente_meuble)))      JOIN v_client vc ON ((vc.id = vm.id_client)))   GROUP BY dvm.id_formule_meuble, vc.id_genre
 SELECT dvm.id_formule_meuble,
    sum(dvm.quantite) AS quantite,
    vc.id_genre
   FROM ((detail_vente_meuble dvm
     JOIN vente_meuble vm ON ((vm.id = dvm.id_vente_meuble)))
     JOIN v_client vc ON ((vc.id = vm.id_client)))
  GROUP BY dvm.id_formule_meuble, vc.id_genre;

CREATE OR REPLACE VIEW "public".v_vente_global_genre AS SELECT q2.id_genre,     q2.quantite,     g.nom AS genre    FROM (( SELECT q1.id_genre,             sum(q1.quantite) AS quantite            FROM ( SELECT dvm.id_formule_meuble,                     dvm.quantite,                     vc.id_genre                    FROM ((detail_vente_meuble dvm                      JOIN vente_meuble vm ON ((vm.id = dvm.id_vente_meuble)))                      JOIN v_client vc ON ((vc.id = vm.id_client)))) q1           GROUP BY q1.id_genre) q2      JOIN genre g ON ((g.id = q2.id_genre)))
 SELECT q2.id_genre,
    q2.quantite,
    g.nom AS genre
   FROM (( SELECT q1.id_genre,
            sum(q1.quantite) AS quantite
           FROM ( SELECT dvm.id_formule_meuble,
                    dvm.quantite,
                    vc.id_genre
                   FROM ((detail_vente_meuble dvm
                     JOIN vente_meuble vm ON ((vm.id = dvm.id_vente_meuble)))
                     JOIN v_client vc ON ((vc.id = vm.id_client)))) q1
          GROUP BY q1.id_genre) q2
     JOIN genre g ON ((g.id = q2.id_genre)));

CREATE OR REPLACE VIEW "public".v_vente_meuble AS SELECT vm.id,     vm.date_vente,     vm.id_client,     vm.prix_total,     c.nom AS nom_client,     c.prenom AS prenom_client    FROM (vente_meuble vm      JOIN client c ON ((c.id = vm.id_client)))
 SELECT vm.id,
    vm.date_vente,
    vm.id_client,
    vm.prix_total,
    c.nom AS nom_client,
    c.prenom AS prenom_client
   FROM (vente_meuble vm
     JOIN client c ON ((c.id = vm.id_client)));

INSERT INTO "public".categorie_meuble( id, nom ) VALUES ( 1, 'Table');
INSERT INTO "public".categorie_meuble( id, nom ) VALUES ( 2, 'Chaise');
INSERT INTO "public".categorie_meuble( id, nom ) VALUES ( 3, 'Canape');
INSERT INTO "public".categorie_meuble( id, nom ) VALUES ( 4, 'Armoire');
INSERT INTO "public".categorie_meuble( id, nom ) VALUES ( 5, 'Coffre');
INSERT INTO "public".categorie_meuble( id, nom ) VALUES ( 6, 'Coiffeuse');
INSERT INTO "public".categorie_meuble( id, nom ) VALUES ( 7, 'Biblotheque');
INSERT INTO "public".categorie_meuble( id, nom ) VALUES ( 8, 'Vitrine');
INSERT INTO "public".categorie_meuble( id, nom ) VALUES ( 9, 'Bakarto');
INSERT INTO "public".genre( id, nom ) VALUES ( 1, 'Homme');
INSERT INTO "public".genre( id, nom ) VALUES ( 2, 'Femme');
INSERT INTO "public".niveau( id, nom, ordre ) VALUES ( 1, 'Debutant', 10);
INSERT INTO "public".niveau( id, nom, ordre ) VALUES ( 2, 'Senior', 20);
INSERT INTO "public".niveau( id, nom, ordre ) VALUES ( 3, 'Expert', 30);
INSERT INTO "public".poste( id, nom ) VALUES ( 1, 'Menuisier');
INSERT INTO "public".poste( id, nom ) VALUES ( 2, 'Assembleur');
INSERT INTO "public".poste( id, nom ) VALUES ( 3, 'Technicien en finition');
INSERT INTO "public".poste( id, nom ) VALUES ( 4, 'Technicien de bois');
INSERT INTO "public".poste( id, nom ) VALUES ( 5, 'Operateur de machine');
INSERT INTO "public".style_meuble( id, nom ) VALUES ( 1, 'Boheme');
INSERT INTO "public".style_meuble( id, nom ) VALUES ( 2, 'Scandinave');
INSERT INTO "public".style_meuble( id, nom ) VALUES ( 3, 'Contemporaine');
INSERT INTO "public".style_meuble( id, nom ) VALUES ( 4, 'GasyGasy');
INSERT INTO "public".taille_meuble( id, nom ) VALUES ( 1, 'petit');
INSERT INTO "public".taille_meuble( id, nom ) VALUES ( 2, 'moyen');
INSERT INTO "public".taille_meuble( id, nom ) VALUES ( 3, 'grand');
INSERT INTO "public".taux_horaire_poste( id, id_poste, date_debut, date_fin, valeur ) VALUES ( 1, 1, '2024-01-01 12:00:00 am', '9999-12-31 11:59:00 pm', 1000.0);
INSERT INTO "public".taux_horaire_poste( id, id_poste, date_debut, date_fin, valeur ) VALUES ( 2, 2, '2024-01-01 12:00:00 am', '9999-12-31 11:59:00 pm', 1000.0);
INSERT INTO "public".taux_horaire_poste( id, id_poste, date_debut, date_fin, valeur ) VALUES ( 3, 3, '2024-01-01 12:00:00 am', '9999-12-31 11:59:00 pm', 1000.0);
INSERT INTO "public".taux_horaire_poste( id, id_poste, date_debut, date_fin, valeur ) VALUES ( 4, 4, '2024-01-01 12:00:00 am', '9999-12-31 11:59:00 pm', 1000.0);
INSERT INTO "public".taux_horaire_poste( id, id_poste, date_debut, date_fin, valeur ) VALUES ( 5, 5, '2024-01-01 12:00:00 am', '9999-12-31 11:59:00 pm', 1000.0);
INSERT INTO "public".type_materiau( id, nom ) VALUES ( 1, 'bois');
INSERT INTO "public".type_materiau( id, nom ) VALUES ( 2, 'metal');
INSERT INTO "public".type_materiau( id, nom ) VALUES ( 3, 'plastique');
INSERT INTO "public".type_materiau( id, nom ) VALUES ( 4, 'verre');
INSERT INTO "public".type_materiau( id, nom ) VALUES ( 5, 'tissus');
INSERT INTO "public".type_materiau( id, nom ) VALUES ( 6, 'contreplaque');
INSERT INTO "public".type_materiau( id, nom ) VALUES ( 7, 'typo');
INSERT INTO "public".base_taux_horaire( id, id_poste, date_debut, date_fin, valeur ) VALUES ( 1, 1, '2022-01-01 12:00:00 am', '9999-12-31 11:59:00 pm', 10.0);
INSERT INTO "public".base_taux_horaire( id, id_poste, date_debut, date_fin, valeur ) VALUES ( 2, 2, '2022-01-01 12:00:00 am', '9999-12-31 11:59:00 pm', 10.0);
INSERT INTO "public".base_taux_horaire( id, id_poste, date_debut, date_fin, valeur ) VALUES ( 3, 3, '2022-01-01 12:00:00 am', '9999-12-31 11:59:00 pm', 10.0);
INSERT INTO "public".base_taux_horaire( id, id_poste, date_debut, date_fin, valeur ) VALUES ( 4, 4, '2022-01-01 12:00:00 am', '9999-12-31 11:59:00 pm', 10.0);
INSERT INTO "public".base_taux_horaire( id, id_poste, date_debut, date_fin, valeur ) VALUES ( 5, 5, '2022-01-01 12:00:00 am', '9999-12-31 11:59:00 pm', 10.0);
INSERT INTO "public".client( id, nom, prenom, telephone, id_genre, date_entree ) VALUES ( 2, 'Rakoto', 'Koto', '0321289400', 1, '2024-01-01 12:00:00 am');
INSERT INTO "public".client( id, nom, prenom, telephone, id_genre, date_entree ) VALUES ( 3, 'Ravao', 'Vao', '0332744350', 2, '2024-01-01 12:00:00 am');
INSERT INTO "public".client( id, nom, prenom, telephone, id_genre, date_entree ) VALUES ( 4, 'Rabe', 'Be', '0330885002', 1, '2024-01-01 12:00:00 am');
INSERT INTO "public".employe( id, nom, prenom, date_naissance, id_genre, date_entree ) VALUES ( 3, 'Rajoelina', 'Andry', '2000-01-01', 1, '2019-01-01 12:00:00 am');
INSERT INTO "public".employe( id, nom, prenom, date_naissance, id_genre, date_entree ) VALUES ( 4, 'Randriasoloniako', 'Siteny', '2000-01-01', 1, '2021-01-01 12:00:00 am');
INSERT INTO "public".employe( id, nom, prenom, date_naissance, id_genre, date_entree ) VALUES ( 5, 'Tom', 'Sawyer', '2000-01-01', 1, '2023-01-01 12:00:00 am');
INSERT INTO "public".employe( id, nom, prenom, date_naissance, id_genre, date_entree ) VALUES ( 6, 'Do', 'Do', '2000-01-01', 1, '2010-01-01 12:00:00 am');
INSERT INTO "public".materiau( id, nom, id_type_materiau, description ) VALUES ( 1, 'Chene', 1, 'Materiau durable et resistant');
INSERT INTO "public".materiau( id, nom, id_type_materiau, description ) VALUES ( 2, 'Pin', 1, 'Bois leger utilise pour differents usages');
INSERT INTO "public".materiau( id, nom, id_type_materiau, description ) VALUES ( 3, 'Erable', 1, 'Bois dur souvent utilise dans la fabrication de meubles');
INSERT INTO "public".materiau( id, nom, id_type_materiau, description ) VALUES ( 4, 'Acier inoxydable', 2, 'Resistant a la corrosion et polyvalent');
INSERT INTO "public".materiau( id, nom, id_type_materiau, description ) VALUES ( 5, 'Aluminium', 2, 'Leger et malleable, utilise dans diverses industries');
INSERT INTO "public".materiau( id, nom, id_type_materiau, description ) VALUES ( 6, 'Cuivre', 2, 'Bonne conductivite thermique et electrique');
INSERT INTO "public".materiau( id, nom, id_type_materiau, description ) VALUES ( 7, 'Polyethylene', 3, 'Resistant et flexible, utilise dans les emballages et les produits chimiques');
INSERT INTO "public".materiau( id, nom, id_type_materiau, description ) VALUES ( 8, 'Polycarbonate', 3, 'Transparent et resistant aux chocs, utilise dans les lunettes et les vitres de protection');
INSERT INTO "public".materiau( id, nom, id_type_materiau, description ) VALUES ( 9, 'Polypropylene', 3, 'Resistant a la chaleur, utilise dans les tapis et les conteneurs');
INSERT INTO "public".materiau( id, nom, id_type_materiau, description ) VALUES ( 10, 'Verre borosilicate', 4, 'Resistant aux temperatures elevees, utilise dans les ustensiles de cuisine');
INSERT INTO "public".materiau( id, nom, id_type_materiau, description ) VALUES ( 11, 'Verre trempe', 4, 'Renforce pour plus de resistance, utilise dans les smartphones et les pare-brise de voiture');
INSERT INTO "public".materiau( id, nom, id_type_materiau, description ) VALUES ( 12, 'Verre cristallin', 4, 'Transparent et brillant, utilise dans la verrerie fine');
INSERT INTO "public".materiau( id, nom, id_type_materiau, description ) VALUES ( 13, 'Coton', 5, 'Respirant et confortable, utilise dans les vetements');
INSERT INTO "public".materiau( id, nom, id_type_materiau, description ) VALUES ( 14, 'Laine', 5, 'Isolant thermique naturel, utilise dans les tapis et les vetements d hiver');
INSERT INTO "public".materiau( id, nom, id_type_materiau, description ) VALUES ( 15, 'Soie', 5, 'Legere et brillante, utilisee dans la mode haut de gamme');
INSERT INTO "public".materiau( id, nom, id_type_materiau, description ) VALUES ( 16, 'Peuplier', 6, 'Leger et resistant, utilise dans la construction legere et le mobilier');
INSERT INTO "public".materiau( id, nom, id_type_materiau, description ) VALUES ( 17, 'Epinette', 6, 'Stable et peu co–teux, utilise dans la fabrication de meubles');
INSERT INTO "public".materiau( id, nom, id_type_materiau, description ) VALUES ( 18, 'Eucalyptus', 6, 'Durabilite et resistance a l humidite, utilise dans les revetements de sol');
INSERT INTO "public".materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 1, 1, 1);
INSERT INTO "public".materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 2, 1, 4);
INSERT INTO "public".materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 3, 1, 7);
INSERT INTO "public".materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 4, 1, 10);
INSERT INTO "public".materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 5, 1, 13);
INSERT INTO "public".materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 6, 1, 16);
INSERT INTO "public".materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 7, 2, 2);
INSERT INTO "public".materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 8, 2, 5);
INSERT INTO "public".materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 9, 2, 8);
INSERT INTO "public".materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 10, 2, 11);
INSERT INTO "public".materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 11, 2, 14);
INSERT INTO "public".materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 12, 2, 17);
INSERT INTO "public".materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 13, 3, 3);
INSERT INTO "public".materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 14, 3, 6);
INSERT INTO "public".materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 15, 3, 9);
INSERT INTO "public".materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 16, 3, 12);
INSERT INTO "public".materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 17, 3, 15);
INSERT INTO "public".materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 18, 3, 18);
INSERT INTO "public".meuble( id, nom, id_style_meuble, id_categorie_meuble, description ) VALUES ( 1, 'Table en Chene', 1, 1, 'Belle table en bois de chene pour la salle a manger');
INSERT INTO "public".meuble( id, nom, id_style_meuble, id_categorie_meuble, description ) VALUES ( 2, 'Table Basse Scandinave', 2, 1, 'Table basse au style scandinave pour le salon');
INSERT INTO "public".meuble( id, nom, id_style_meuble, id_categorie_meuble, description ) VALUES ( 3, 'Table de Travail Contemporaine', 3, 1, 'Table de travail moderne pour le bureau');
INSERT INTO "public".meuble( id, nom, id_style_meuble, id_categorie_meuble, description ) VALUES ( 4, 'Chaise en Pin', 2, 2, 'Chaise legere en pin pour la salle a manger');
INSERT INTO "public".meuble( id, nom, id_style_meuble, id_categorie_meuble, description ) VALUES ( 5, 'Chaise Bois et Metal', 3, 2, 'Chaise contemporaine en bois et metal pour differents usages');
INSERT INTO "public".meuble( id, nom, id_style_meuble, id_categorie_meuble, description ) VALUES ( 6, 'Canape Boheme', 1, 3, 'Canape confortable au style boheme pour le salon');
INSERT INTO "public".meuble( id, nom, id_style_meuble, id_categorie_meuble, description ) VALUES ( 7, 'Canape Scandinave Convertible', 2, 3, 'Canape convertible au design scandinave pour un usage multifonctionnel');
INSERT INTO "public".meuble( id, nom, id_style_meuble, id_categorie_meuble, description ) VALUES ( 8, 'Armoire en erable', 3, 4, 'Armoire spacieuse en erable pour la chambre a coucher');
INSERT INTO "public".meuble( id, nom, id_style_meuble, id_categorie_meuble, description ) VALUES ( 9, 'Armoire Contemporaine avec Miroir', 2, 4, 'Armoire moderne avec miroir pour ranger vos vetements');
INSERT INTO "public".meuble( id, nom, id_style_meuble, id_categorie_meuble, description ) VALUES ( 10, 'Coffre en Peuplier', 1, 5, 'Coffre en bois de peuplier pour stocker differents objets');
INSERT INTO "public".meuble( id, nom, id_style_meuble, id_categorie_meuble, description ) VALUES ( 11, 'Coiffeuse Scandinave', 2, 6, 'Coiffeuse au style scandinave avec miroir pour la chambre');
INSERT INTO "public".meuble( id, nom, id_style_meuble, id_categorie_meuble, description ) VALUES ( 12, 'Bibliotheque Contemporaine', 3, 7, 'Bibliotheque moderne pour exposer vos livres et objets decoratifs');
INSERT INTO "public".meuble( id, nom, id_style_meuble, id_categorie_meuble, description ) VALUES ( 13, 'Vitrine en Verre Trempe', 2, 8, 'Vitrine elegante en verre trempe pour exposer vos collections');
INSERT INTO "public".montee_niveau_employe( id, id_poste, id_niveau_depart, id_niveau_arrive, duree, date_debut, date_fin ) VALUES ( 4, 1, 1, 2, 2.0, '2020-01-01 12:00:00 am', '9999-12-31 11:59:00 pm');
INSERT INTO "public".montee_niveau_employe( id, id_poste, id_niveau_depart, id_niveau_arrive, duree, date_debut, date_fin ) VALUES ( 5, 1, 2, 3, 3.0, '2020-01-01 12:00:00 am', '9999-12-31 11:59:00 pm');
INSERT INTO "public".montee_niveau_employe( id, id_poste, id_niveau_depart, id_niveau_arrive, duree, date_debut, date_fin ) VALUES ( 6, 2, 1, 2, 2.0, '2020-01-01 12:00:00 am', '9999-12-31 11:59:00 pm');
INSERT INTO "public".montee_niveau_employe( id, id_poste, id_niveau_depart, id_niveau_arrive, duree, date_debut, date_fin ) VALUES ( 7, 2, 2, 3, 3.0, '2020-01-01 12:00:00 am', '9999-12-31 11:59:00 pm');
INSERT INTO "public".montee_niveau_employe( id, id_poste, id_niveau_depart, id_niveau_arrive, duree, date_debut, date_fin ) VALUES ( 8, 3, 1, 2, 2.0, '2020-01-01 12:00:00 am', '9999-12-31 11:59:00 pm');
INSERT INTO "public".montee_niveau_employe( id, id_poste, id_niveau_depart, id_niveau_arrive, duree, date_debut, date_fin ) VALUES ( 9, 3, 2, 3, 3.0, '2020-01-01 12:00:00 am', '9999-12-31 11:59:00 pm');
INSERT INTO "public".montee_niveau_employe( id, id_poste, id_niveau_depart, id_niveau_arrive, duree, date_debut, date_fin ) VALUES ( 10, 4, 1, 2, 2.0, '2020-01-01 12:00:00 am', '9999-12-31 11:59:00 pm');
INSERT INTO "public".montee_niveau_employe( id, id_poste, id_niveau_depart, id_niveau_arrive, duree, date_debut, date_fin ) VALUES ( 11, 4, 2, 3, 3.0, '2020-01-01 12:00:00 am', '9999-12-31 11:59:00 pm');
INSERT INTO "public".montee_niveau_employe( id, id_poste, id_niveau_depart, id_niveau_arrive, duree, date_debut, date_fin ) VALUES ( 12, 5, 1, 2, 2.0, '2020-01-01 08:00:00 pm', '9999-12-31 11:59:00 pm');
INSERT INTO "public".montee_niveau_employe( id, id_poste, id_niveau_depart, id_niveau_arrive, duree, date_debut, date_fin ) VALUES ( 13, 5, 2, 3, 3.0, '2020-01-01 12:00:00 am', '2020-01-01 12:00:00 am');
INSERT INTO "public".montee_niveau_employe( id, id_poste, id_niveau_depart, id_niveau_arrive, duree, date_debut, date_fin ) VALUES ( 14, 5, 2, 3, 4.0, '2020-01-01 12:00:00 am', '9999-12-31 11:59:00 pm');
INSERT INTO "public".mouvement_materiau( id, date_mouvement, id_materiau, quantite, prix_unitaire, type_mouvement, id_mouvement_mere, description, id_mouvement_meuble ) VALUES ( 7, '2024-01-01 12:00:00 am', 1, 100.0, 100.0, 1, -1, '', -1);
INSERT INTO "public".mouvement_materiau( id, date_mouvement, id_materiau, quantite, prix_unitaire, type_mouvement, id_mouvement_mere, description, id_mouvement_meuble ) VALUES ( 8, '2024-01-01 12:00:00 am', 2, 100.0, 100.0, 1, -1, '', -1);
INSERT INTO "public".mouvement_materiau( id, date_mouvement, id_materiau, quantite, prix_unitaire, type_mouvement, id_mouvement_mere, description, id_mouvement_meuble ) VALUES ( 9, '2024-01-01 12:00:00 am', 3, 100.0, 100.0, 1, -1, '', -1);
INSERT INTO "public".mouvement_materiau( id, date_mouvement, id_materiau, quantite, prix_unitaire, type_mouvement, id_mouvement_mere, description, id_mouvement_meuble ) VALUES ( 10, '2024-01-01 12:00:00 am', 4, 100.0, 100.0, 1, -1, '', -1);
INSERT INTO "public".mouvement_materiau( id, date_mouvement, id_materiau, quantite, prix_unitaire, type_mouvement, id_mouvement_mere, description, id_mouvement_meuble ) VALUES ( 11, '2024-01-01 12:00:00 am', 5, 100.0, 100.0, 1, -1, '', -1);
INSERT INTO "public".mouvement_materiau( id, date_mouvement, id_materiau, quantite, prix_unitaire, type_mouvement, id_mouvement_mere, description, id_mouvement_meuble ) VALUES ( 12, '2024-01-01 12:00:00 am', 6, 100.0, 100.0, 1, -1, '', -1);
INSERT INTO "public".mouvement_materiau( id, date_mouvement, id_materiau, quantite, prix_unitaire, type_mouvement, id_mouvement_mere, description, id_mouvement_meuble ) VALUES ( 13, '2024-01-01 12:00:00 am', 7, 100.0, 100.0, 1, -1, '', -1);
INSERT INTO "public".mouvement_materiau( id, date_mouvement, id_materiau, quantite, prix_unitaire, type_mouvement, id_mouvement_mere, description, id_mouvement_meuble ) VALUES ( 14, '2024-01-01 12:00:00 am', 8, 100.0, 100.0, 1, -1, '', -1);
INSERT INTO "public".mouvement_materiau( id, date_mouvement, id_materiau, quantite, prix_unitaire, type_mouvement, id_mouvement_mere, description, id_mouvement_meuble ) VALUES ( 15, '2024-01-01 12:00:00 am', 9, 100.0, 100.0, 1, -1, '', -1);
INSERT INTO "public".mouvement_materiau( id, date_mouvement, id_materiau, quantite, prix_unitaire, type_mouvement, id_mouvement_mere, description, id_mouvement_meuble ) VALUES ( 16, '2024-01-01 12:00:00 am', 10, 100.0, 100.0, 1, -1, '', -1);
INSERT INTO "public".mouvement_materiau( id, date_mouvement, id_materiau, quantite, prix_unitaire, type_mouvement, id_mouvement_mere, description, id_mouvement_meuble ) VALUES ( 17, '2024-01-01 12:00:00 am', 11, 100.0, 100.0, 1, -1, '', -1);
INSERT INTO "public".mouvement_materiau( id, date_mouvement, id_materiau, quantite, prix_unitaire, type_mouvement, id_mouvement_mere, description, id_mouvement_meuble ) VALUES ( 19, '2024-01-01 12:00:00 am', 12, 100.0, 100.0, 1, -1, '', -1);
INSERT INTO "public".mouvement_materiau( id, date_mouvement, id_materiau, quantite, prix_unitaire, type_mouvement, id_mouvement_mere, description, id_mouvement_meuble ) VALUES ( 20, '2024-01-01 12:00:00 am', 13, 100.0, 100.0, 1, -1, '', -1);
INSERT INTO "public".mouvement_materiau( id, date_mouvement, id_materiau, quantite, prix_unitaire, type_mouvement, id_mouvement_mere, description, id_mouvement_meuble ) VALUES ( 21, '2024-01-01 12:00:00 am', 14, 100.0, 100.0, 1, -1, '', -1);
INSERT INTO "public".mouvement_materiau( id, date_mouvement, id_materiau, quantite, prix_unitaire, type_mouvement, id_mouvement_mere, description, id_mouvement_meuble ) VALUES ( 22, '2024-01-01 12:00:00 am', 15, 100.0, 100.0, 1, -1, '', -1);
INSERT INTO "public".mouvement_materiau( id, date_mouvement, id_materiau, quantite, prix_unitaire, type_mouvement, id_mouvement_mere, description, id_mouvement_meuble ) VALUES ( 23, '2024-01-01 12:00:00 am', 16, 100.0, 100.0, 1, -1, '', -1);
INSERT INTO "public".mouvement_materiau( id, date_mouvement, id_materiau, quantite, prix_unitaire, type_mouvement, id_mouvement_mere, description, id_mouvement_meuble ) VALUES ( 24, '2024-01-01 12:00:00 am', 17, 100.0, 100.0, 1, -1, '', -1);
INSERT INTO "public".mouvement_materiau( id, date_mouvement, id_materiau, quantite, prix_unitaire, type_mouvement, id_mouvement_mere, description, id_mouvement_meuble ) VALUES ( 25, '2024-01-01 12:00:00 am', 18, 100.0, 100.0, 1, -1, '', -1);
INSERT INTO "public".mouvement_materiau( id, date_mouvement, id_materiau, quantite, prix_unitaire, type_mouvement, id_mouvement_mere, description, id_mouvement_meuble ) VALUES ( 38, '2024-01-01 01:00:00 am', 1, 20.0, 100.0, -1, 7, 'Fabrication de meuble', 22);
INSERT INTO "public".mouvement_materiau( id, date_mouvement, id_materiau, quantite, prix_unitaire, type_mouvement, id_mouvement_mere, description, id_mouvement_meuble ) VALUES ( 39, '2024-01-01 01:00:00 am', 16, 10.0, 100.0, -1, 23, 'Fabrication de meuble', 22);
INSERT INTO "public".multiplication_salarial_employe( id, id_poste, id_niveau_depart, id_niveau_arrive, multipliant, date_debut, date_fin ) VALUES ( 4, 1, 1, 2, 2.0, '2020-01-01 12:00:00 am', '9999-12-31 11:59:00 pm');
INSERT INTO "public".multiplication_salarial_employe( id, id_poste, id_niveau_depart, id_niveau_arrive, multipliant, date_debut, date_fin ) VALUES ( 5, 1, 2, 3, 1.5, '2020-01-01 12:00:00 am', '9999-12-31 11:59:00 pm');
INSERT INTO "public".multiplication_salarial_employe( id, id_poste, id_niveau_depart, id_niveau_arrive, multipliant, date_debut, date_fin ) VALUES ( 6, 2, 1, 2, 2.0, '2020-01-01 12:00:00 am', '9999-12-31 11:59:00 pm');
INSERT INTO "public".multiplication_salarial_employe( id, id_poste, id_niveau_depart, id_niveau_arrive, multipliant, date_debut, date_fin ) VALUES ( 7, 2, 2, 3, 1.5, '2020-01-01 12:00:00 am', '9999-12-31 11:59:00 pm');
INSERT INTO "public".multiplication_salarial_employe( id, id_poste, id_niveau_depart, id_niveau_arrive, multipliant, date_debut, date_fin ) VALUES ( 8, 3, 1, 2, 2.0, '2020-01-01 12:00:00 am', '9999-12-31 11:59:00 pm');
INSERT INTO "public".multiplication_salarial_employe( id, id_poste, id_niveau_depart, id_niveau_arrive, multipliant, date_debut, date_fin ) VALUES ( 9, 3, 2, 3, 1.5, '2020-01-01 12:00:00 am', '9999-12-31 11:59:00 pm');
INSERT INTO "public".multiplication_salarial_employe( id, id_poste, id_niveau_depart, id_niveau_arrive, multipliant, date_debut, date_fin ) VALUES ( 10, 4, 1, 2, 2.0, '2020-01-01 12:00:00 am', '9999-12-31 11:59:00 pm');
INSERT INTO "public".multiplication_salarial_employe( id, id_poste, id_niveau_depart, id_niveau_arrive, multipliant, date_debut, date_fin ) VALUES ( 11, 4, 2, 3, 1.5, '2020-01-01 12:00:00 am', '9999-12-31 11:59:00 pm');
INSERT INTO "public".multiplication_salarial_employe( id, id_poste, id_niveau_depart, id_niveau_arrive, multipliant, date_debut, date_fin ) VALUES ( 12, 5, 1, 2, 2.0, '2020-01-01 12:00:00 am', '9999-12-31 11:59:00 pm');
INSERT INTO "public".multiplication_salarial_employe( id, id_poste, id_niveau_depart, id_niveau_arrive, multipliant, date_debut, date_fin ) VALUES ( 13, 5, 2, 3, 1.5, '2020-01-01 12:00:00 am', '2020-01-02 12:00:00 am');
INSERT INTO "public".multiplication_salarial_employe( id, id_poste, id_niveau_depart, id_niveau_arrive, multipliant, date_debut, date_fin ) VALUES ( 14, 5, 2, 3, 2.0, '2020-01-02 12:00:00 am', '9999-12-31 11:59:00 pm');
INSERT INTO "public".role_employe( id, id_employe, id_poste, id_niveau, date_debut, date_fin, taux_horaire ) VALUES ( 4, 3, 1, 1, '2019-01-01 12:00:00 am', '9999-12-31 11:59:00 pm', 10.0);
INSERT INTO "public".role_employe( id, id_employe, id_poste, id_niveau, date_debut, date_fin, taux_horaire ) VALUES ( 5, 4, 1, 1, '2021-01-01 12:00:00 am', '9999-12-31 11:59:00 pm', 10.0);
INSERT INTO "public".role_employe( id, id_employe, id_poste, id_niveau, date_debut, date_fin, taux_horaire ) VALUES ( 6, 5, 3, 1, '2023-01-01 12:00:00 am', '9999-12-31 11:59:00 pm', 5.0);
INSERT INTO "public".role_employe( id, id_employe, id_poste, id_niveau, date_debut, date_fin, taux_horaire ) VALUES ( 7, 6, 5, 1, '2010-01-01 12:01:00 am', '9999-12-31 11:59:00 pm', 10.0);
INSERT INTO "public".utilisation_employe( id, id_mouvement_meuble, date_debut, date_fin, id_role_employe, duree_utilisation, salaire_total, description ) VALUES ( 1, -1, '2024-01-01 12:00:00 am', '2024-01-10 12:00:00 am', 6, 60.0, 300.0, 'dfbvdfb');
INSERT INTO "public".utilisation_employe( id, id_mouvement_meuble, date_debut, date_fin, id_role_employe, duree_utilisation, salaire_total, description ) VALUES ( 2, 3, '2024-01-01 12:00:00 am', '2024-01-01 06:00:00 pm', 4, 8.0, 80.0, 'Fabrication de meuble');
INSERT INTO "public".utilisation_employe( id, id_mouvement_meuble, date_debut, date_fin, id_role_employe, duree_utilisation, salaire_total, description ) VALUES ( 3, 14, '2024-01-01 01:00:00 am', '2024-01-01 08:00:00 pm', 4, 6.0, 60.0, 'Fabrication de meuble');
INSERT INTO "public".utilisation_employe( id, id_mouvement_meuble, date_debut, date_fin, id_role_employe, duree_utilisation, salaire_total, description ) VALUES ( 4, 14, '2024-01-01 02:00:00 am', '2024-01-01 01:00:00 pm', 5, 4.0, 40.0, 'Fabrication de meuble');
INSERT INTO "public".vente_meuble( id, date_vente, id_client, prix_total ) VALUES ( 12, '2024-01-01 03:00:00 am', 2, 200000.0);
INSERT INTO "public".vente_meuble( id, date_vente, id_client, prix_total ) VALUES ( 13, '2024-01-01 03:05:00 am', 3, 400000.0);
INSERT INTO "public".formule_meuble( id, id_meuble, id_taille_meuble ) VALUES ( 6, 1, 1);
INSERT INTO "public".formule_meuble( id, id_meuble, id_taille_meuble ) VALUES ( 7, 2, 1);
INSERT INTO "public".formule_meuble( id, id_meuble, id_taille_meuble ) VALUES ( 8, 3, 1);
INSERT INTO "public".formule_meuble( id, id_meuble, id_taille_meuble ) VALUES ( 9, 4, 1);
INSERT INTO "public".formule_meuble( id, id_meuble, id_taille_meuble ) VALUES ( 10, 5, 1);
INSERT INTO "public".formule_meuble( id, id_meuble, id_taille_meuble ) VALUES ( 11, 6, 1);
INSERT INTO "public".formule_meuble( id, id_meuble, id_taille_meuble ) VALUES ( 12, 7, 1);
INSERT INTO "public".formule_meuble( id, id_meuble, id_taille_meuble ) VALUES ( 13, 8, 1);
INSERT INTO "public".formule_meuble( id, id_meuble, id_taille_meuble ) VALUES ( 14, 9, 1);
INSERT INTO "public".formule_meuble( id, id_meuble, id_taille_meuble ) VALUES ( 15, 10, 1);
INSERT INTO "public".formule_meuble( id, id_meuble, id_taille_meuble ) VALUES ( 16, 11, 1);
INSERT INTO "public".formule_meuble( id, id_meuble, id_taille_meuble ) VALUES ( 17, 12, 1);
INSERT INTO "public".formule_meuble( id, id_meuble, id_taille_meuble ) VALUES ( 18, 13, 1);
INSERT INTO "public".mouvement_meuble( id, date_mouvement, id_formule_meuble, quantite, type_mouvement, id_mouvement_mere, total_materiaux, total_salaires, prix_total, prix_unitaire, id_detail_vente_meuble, description ) VALUES ( 22, '2024-01-01 01:00:00 am', 6, 10.0, 1, -1, 3000.0, 0.0, 3000.0, 300.0, -1, '');
INSERT INTO "public".mouvement_meuble( id, date_mouvement, id_formule_meuble, quantite, type_mouvement, id_mouvement_mere, total_materiaux, total_salaires, prix_total, prix_unitaire, id_detail_vente_meuble, description ) VALUES ( 23, '2024-01-01 03:00:00 am', 6, 1.0, -1, 22, -1.0, -1.0, 300.0, 300.0, -1, 'Vente de meuble');
INSERT INTO "public".mouvement_meuble( id, date_mouvement, id_formule_meuble, quantite, type_mouvement, id_mouvement_mere, total_materiaux, total_salaires, prix_total, prix_unitaire, id_detail_vente_meuble, description ) VALUES ( 24, '2024-01-01 03:05:00 am', 6, 2.0, -1, 22, -1.0, -1.0, 600.0, 300.0, -1, 'Vente de meuble');
INSERT INTO "public".prix_de_vente_meuble( id, id_formule_meuble, date_debut, date_fin, valeur ) VALUES ( 1, 6, '2020-01-01 12:00:00 am', '9999-12-31 11:59:00 pm', 200000.0);
INSERT INTO "public".prix_de_vente_meuble( id, id_formule_meuble, date_debut, date_fin, valeur ) VALUES ( 2, 7, '2020-01-01 12:00:00 am', '9999-12-31 11:59:00 pm', 300000.0);
INSERT INTO "public".prix_de_vente_meuble( id, id_formule_meuble, date_debut, date_fin, valeur ) VALUES ( 3, 8, '2020-01-01 12:00:00 am', '9999-12-31 11:59:00 pm', 1000000.0);
INSERT INTO "public".prix_de_vente_meuble( id, id_formule_meuble, date_debut, date_fin, valeur ) VALUES ( 4, 9, '2020-01-01 12:00:00 am', '9999-12-31 11:59:00 pm', 150000.0);
INSERT INTO "public".prix_de_vente_meuble( id, id_formule_meuble, date_debut, date_fin, valeur ) VALUES ( 5, 10, '2020-01-01 12:00:00 am', '9999-12-31 11:59:00 pm', 130000.0);
INSERT INTO "public".prix_de_vente_meuble( id, id_formule_meuble, date_debut, date_fin, valeur ) VALUES ( 6, 11, '2020-01-01 12:00:00 am', '9999-12-31 11:59:00 pm', 2000000.0);
INSERT INTO "public".prix_de_vente_meuble( id, id_formule_meuble, date_debut, date_fin, valeur ) VALUES ( 7, 12, '2020-01-01 12:00:00 am', '9999-12-31 11:59:00 pm', 3.5E7);
INSERT INTO "public".prix_de_vente_meuble( id, id_formule_meuble, date_debut, date_fin, valeur ) VALUES ( 8, 13, '2020-01-01 12:00:00 am', '9999-12-31 11:59:00 pm', 3000000.0);
INSERT INTO "public".prix_de_vente_meuble( id, id_formule_meuble, date_debut, date_fin, valeur ) VALUES ( 9, 14, '2020-01-01 12:00:00 am', '9999-12-31 11:59:00 pm', 450000.0);
INSERT INTO "public".prix_de_vente_meuble( id, id_formule_meuble, date_debut, date_fin, valeur ) VALUES ( 10, 15, '2020-01-01 12:00:00 am', '9999-12-31 11:59:00 pm', 230000.0);
INSERT INTO "public".prix_de_vente_meuble( id, id_formule_meuble, date_debut, date_fin, valeur ) VALUES ( 11, 16, '2020-01-01 12:00:00 am', '9999-12-31 11:59:00 pm', 670000.0);
INSERT INTO "public".prix_de_vente_meuble( id, id_formule_meuble, date_debut, date_fin, valeur ) VALUES ( 12, 17, '2020-01-01 12:00:00 am', '9999-12-31 11:59:00 pm', 5000000.0);
INSERT INTO "public".prix_de_vente_meuble( id, id_formule_meuble, date_debut, date_fin, valeur ) VALUES ( 13, 18, '2020-01-01 12:00:00 am', '2020-01-02 12:00:00 am', 900000.0);
INSERT INTO "public".prix_de_vente_meuble( id, id_formule_meuble, date_debut, date_fin, valeur ) VALUES ( 14, 18, '2020-01-02 12:00:00 am', '9999-12-31 11:59:00 pm', 1000000.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 1, 6, 1, 1, 2, 20.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 2, 6, 3, 3, 1, 15.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 3, 6, 4, 2, 1, 15.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 4, 7, 1, 1, 2, 30.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 5, 7, 2, 2, 1, 15.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 6, 7, 4, 3, 1, 10.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 7, 8, 1, 3, 1, 40.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 8, 8, 2, 1, 1, 15.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 9, 8, 3, 2, 1, 10.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 10, 9, 1, 1, 1, 10.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 11, 9, 2, 1, 1, 10.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 12, 9, 4, 1, 1, 15.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 13, 9, 5, 2, 2, 10.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 14, 10, 1, 3, 2, 10.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 15, 10, 3, 2, 1, 10.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 16, 10, 5, 1, 2, 20.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 17, 11, 1, 3, 1, 10.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 18, 11, 3, 2, 1, 10.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 19, 11, 3, 3, 1, 20.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 20, 11, 4, 1, 2, 10.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 21, 12, 1, 3, 1, 20.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 22, 12, 2, 1, 2, 10.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 23, 12, 3, 2, 1, 10.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 24, 13, 1, 3, 2, 30.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 25, 13, 2, 2, 1, 30.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 26, 13, 4, 1, 3, 10.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 27, 14, 1, 2, 2, 10.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 28, 14, 2, 1, 1, 10.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 29, 14, 3, 2, 1, 20.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 30, 15, 1, 2, 2, 20.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 31, 15, 3, 1, 1, 10.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 32, 15, 4, 2, 1, 10.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 33, 16, 1, 2, 1, 10.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 34, 16, 3, 1, 1, 30.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 35, 16, 4, 3, 1, 20.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 36, 17, 1, 2, 2, 40.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 37, 17, 2, 2, 2, 30.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 38, 17, 3, 3, 1, 15.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 39, 17, 5, 2, 1, 10.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 40, 18, 1, 2, 1, 10.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 41, 18, 2, 2, 1, 10.0);
INSERT INTO "public".detail_employe_meuble( id, id_formule_meuble, id_poste, id_niveau, nombre, duree ) VALUES ( 42, 18, 3, 2, 1, 10.0);
INSERT INTO "public".detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 1, 6, 1, 2.0);
INSERT INTO "public".detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 2, 6, 16, 1.0);
INSERT INTO "public".detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 3, 7, 2, 2.0);
INSERT INTO "public".detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 4, 7, 5, 2.0);
INSERT INTO "public".detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 5, 7, 14, 1.0);
INSERT INTO "public".detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 6, 8, 3, 2.0);
INSERT INTO "public".detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 7, 8, 6, 1.0);
INSERT INTO "public".detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 8, 8, 12, 2.0);
INSERT INTO "public".detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 9, 9, 2, 1.0);
INSERT INTO "public".detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 10, 9, 8, 1.0);
INSERT INTO "public".detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 11, 9, 17, 1.0);
INSERT INTO "public".detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 12, 10, 6, 4.0);
INSERT INTO "public".detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 13, 10, 18, 2.0);
INSERT INTO "public".detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 14, 11, 1, 1.0);
INSERT INTO "public".detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 15, 11, 7, 2.0);
INSERT INTO "public".detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 16, 11, 13, 1.0);
INSERT INTO "public".detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 17, 12, 2, 3.0);
INSERT INTO "public".detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 18, 12, 5, 2.0);
INSERT INTO "public".detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 19, 12, 11, 1.0);
INSERT INTO "public".detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 20, 13, 3, 10.0);
INSERT INTO "public".detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 21, 13, 15, 5.0);
INSERT INTO "public".detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 22, 14, 2, 2.0);
INSERT INTO "public".detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 23, 14, 11, 4.0);
INSERT INTO "public".detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 24, 14, 8, 1.0);
INSERT INTO "public".detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 25, 15, 1, 3.0);
INSERT INTO "public".detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 26, 15, 4, 3.0);
INSERT INTO "public".detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 27, 15, 13, 1.0);
INSERT INTO "public".detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 28, 16, 2, 2.0);
INSERT INTO "public".detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 29, 16, 8, 1.0);
INSERT INTO "public".detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 30, 17, 3, 20.0);
INSERT INTO "public".detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 31, 17, 6, 10.0);
INSERT INTO "public".detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 32, 17, 9, 2.0);
INSERT INTO "public".detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 33, 17, 15, 1.0);
INSERT INTO "public".detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 34, 18, 2, 2.0);
INSERT INTO "public".detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 35, 18, 11, 1.0);
INSERT INTO "public".detail_vente_meuble( id, id_vente_meuble, id_formule_meuble, quantite, prix_unitaire, prix_total ) VALUES ( 8, 12, 6, 1.0, 200000.0, 200000.0);
INSERT INTO "public".detail_vente_meuble( id, id_vente_meuble, id_formule_meuble, quantite, prix_unitaire, prix_total ) VALUES ( 9, 13, 6, 2.0, 200000.0, 400000.0);
