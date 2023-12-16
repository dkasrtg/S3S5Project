CREATE SCHEMA IF NOT EXISTS "public";

CREATE SEQUENCE checkbox_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE checkbox_reference_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE details_reference_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE dimension_materiau_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE dimension_unite_possible_materiau_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE materiau_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE option_reference_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE reference_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE stockage_materiau_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE type_materiau_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE unite_materiau_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE  TABLE checkbox ( 
	id                   serial DEFAULT nextval('checkbox_id_seq'::regclass) NOT NULL  ,
	nom                  varchar(20)    ,
	CONSTRAINT checkbox_pkey PRIMARY KEY ( id )
 );

CREATE  TABLE dimension_materiau ( 
	id                   serial DEFAULT nextval('dimension_materiau_id_seq'::regclass) NOT NULL  ,
	longueur             numeric    ,
	largeur              numeric    ,
	hauteur              numeric    ,
	CONSTRAINT dimension_materiau_pkey PRIMARY KEY ( id )
 );

CREATE  TABLE option_reference ( 
	id                   serial DEFAULT nextval('option_reference_id_seq'::regclass) NOT NULL  ,
	"option"             varchar(20)    ,
	CONSTRAINT option_reference_pkey PRIMARY KEY ( id )
 );

CREATE  TABLE reference ( 
	id                   serial DEFAULT nextval('reference_id_seq'::regclass) NOT NULL  ,
	string               varchar(20)    ,
	date_simple          date    ,
	heure_simple         time    ,
	date_heure           timestamp    ,
	entier               integer    ,
	pas_entier           numeric    ,
	id_option_reference  integer    ,
	id_radio_reference   integer    ,
	CONSTRAINT reference_pkey PRIMARY KEY ( id ),
	CONSTRAINT reference_id_option_reference_fkey FOREIGN KEY ( id_option_reference ) REFERENCES option_reference( id )   ,
	CONSTRAINT reference_id_radio_reference_fkey FOREIGN KEY ( id_radio_reference ) REFERENCES option_reference( id )   
 );

CREATE  TABLE type_materiau ( 
	id                   serial DEFAULT nextval('type_materiau_id_seq'::regclass) NOT NULL  ,
	nom                  varchar(200)    ,
	CONSTRAINT type_materiau_pkey PRIMARY KEY ( id )
 );

CREATE  TABLE unite_materiau ( 
	id                   serial DEFAULT nextval('unite_materiau_id_seq'::regclass) NOT NULL  ,
	nom                  varchar(200)    ,
	CONSTRAINT unite_materiau_pkey PRIMARY KEY ( id )
 );

CREATE  TABLE checkbox_reference ( 
	id                   serial DEFAULT nextval('checkbox_reference_id_seq'::regclass) NOT NULL  ,
	id_reference         integer    ,
	id_checkbox          integer    ,
	CONSTRAINT checkbox_reference_pkey PRIMARY KEY ( id ),
	CONSTRAINT checkbox_reference_id_reference_fkey FOREIGN KEY ( id_reference ) REFERENCES reference( id ) ON DELETE CASCADE ON UPDATE CASCADE ,
	CONSTRAINT checkbox_reference_id_checkbox_fkey FOREIGN KEY ( id_checkbox ) REFERENCES checkbox( id )   
 );

CREATE  TABLE details_reference ( 
	id                   serial DEFAULT nextval('details_reference_id_seq'::regclass) NOT NULL  ,
	id_reference         integer    ,
	details              text    ,
	note                 numeric    ,
	CONSTRAINT details_reference_pkey PRIMARY KEY ( id ),
	CONSTRAINT details_reference_id_reference_fkey FOREIGN KEY ( id_reference ) REFERENCES reference( id ) ON DELETE CASCADE ON UPDATE CASCADE 
 );

CREATE  TABLE materiau ( 
	id                   serial DEFAULT nextval('materiau_id_seq'::regclass) NOT NULL  ,
	nom                  varchar(200)    ,
	id_type_materiau     integer    ,
	description          text    ,
	CONSTRAINT materiau_pkey PRIMARY KEY ( id ),
	CONSTRAINT materiau_id_type_materiau_fkey FOREIGN KEY ( id_type_materiau ) REFERENCES type_materiau( id )   
 );

CREATE  TABLE stockage_materiau ( 
	id                   serial DEFAULT nextval('stockage_materiau_id_seq'::regclass) NOT NULL  ,
	id_materiau          integer    ,
	id_dimension_materiau integer    ,
	id_unite_materiau    integer    ,
	quantite_stockage    numeric    ,
	date_stockage        date    ,
	prix_unitaire        numeric    ,
	prix_total           numeric    ,
	CONSTRAINT stockage_materiau_pkey PRIMARY KEY ( id ),
	CONSTRAINT stockage_materiau_id_materiau_fkey FOREIGN KEY ( id_materiau ) REFERENCES materiau( id )   ,
	CONSTRAINT stockage_materiau_id_dimension_materiau_fkey FOREIGN KEY ( id_dimension_materiau ) REFERENCES dimension_materiau( id )   ,
	CONSTRAINT stockage_materiau_id_unite_materiau_fkey FOREIGN KEY ( id_unite_materiau ) REFERENCES unite_materiau( id )   
 );

CREATE  TABLE dimension_unite_possible_materiau ( 
	id                   serial DEFAULT nextval('dimension_unite_possible_materiau_id_seq'::regclass) NOT NULL  ,
	id_materiau          integer    ,
	id_dimension_materiau integer    ,
	id_unite_materiau    integer    ,
	CONSTRAINT dimension_unite_possible_materiau_pkey PRIMARY KEY ( id ),
	CONSTRAINT dimension_unite_possible_materiau_id_materiau_fkey FOREIGN KEY ( id_materiau ) REFERENCES materiau( id )   ,
	CONSTRAINT dimension_unite_possible_materiau_id_dimension_materiau_fkey FOREIGN KEY ( id_dimension_materiau ) REFERENCES dimension_materiau( id )   ,
	CONSTRAINT dimension_unite_possible_materiau_id_unite_materiau_fkey FOREIGN KEY ( id_unite_materiau ) REFERENCES unite_materiau( id )   
 );

CREATE OR REPLACE VIEW v_checkbox_reference AS SELECT cr.id,     cr.id_reference,     cr.id_checkbox,     c.nom    FROM (checkbox_reference cr      JOIN checkbox c ON ((c.id = cr.id_checkbox)))
 SELECT cr.id,
    cr.id_reference,
    cr.id_checkbox,
    c.nom
   FROM (checkbox_reference cr
     JOIN checkbox c ON ((c.id = cr.id_checkbox)));

CREATE OR REPLACE VIEW v_dimension_possible_materiau AS SELECT dupm.id,     dupm.id_materiau,     dupm.id_dimension_materiau,     dm.longueur,     dm.largeur,     dm.hauteur    FROM (dimension_unite_possible_materiau dupm      JOIN dimension_materiau dm ON ((dm.id = dupm.id_dimension_materiau)))
 SELECT dupm.id,
    dupm.id_materiau,
    dupm.id_dimension_materiau,
    dm.longueur,
    dm.largeur,
    dm.hauteur
   FROM (dimension_unite_possible_materiau dupm
     JOIN dimension_materiau dm ON ((dm.id = dupm.id_dimension_materiau)));

CREATE OR REPLACE VIEW v_materiau AS SELECT m.id,     m.nom,     m.id_type_materiau,     m.description,     tm.nom AS nom_type_materiau    FROM (materiau m      JOIN type_materiau tm ON ((m.id_type_materiau = tm.id)))
 SELECT m.id,
    m.nom,
    m.id_type_materiau,
    m.description,
    tm.nom AS nom_type_materiau
   FROM (materiau m
     JOIN type_materiau tm ON ((m.id_type_materiau = tm.id)));

CREATE OR REPLACE VIEW v_reference AS SELECT r.id,     r.string,     r.date_simple,     r.heure_simple,     r.date_heure,     r.entier,     r.pas_entier,     r.id_option_reference,     r.id_radio_reference,     o1.option,     o2.option AS radio    FROM ((reference r      JOIN option_reference o1 ON ((o1.id = r.id_option_reference)))      JOIN option_reference o2 ON ((o2.id = r.id_radio_reference)))
 SELECT r.id,
    r.string,
    r.date_simple,
    r.heure_simple,
    r.date_heure,
    r.entier,
    r.pas_entier,
    r.id_option_reference,
    r.id_radio_reference,
    o1.option,
    o2.option AS radio
   FROM ((reference r
     JOIN option_reference o1 ON ((o1.id = r.id_option_reference)))
     JOIN option_reference o2 ON ((o2.id = r.id_radio_reference)));

CREATE OR REPLACE VIEW v_stockage_materiau AS SELECT sm.id,     sm.id_materiau,     sm.id_dimension_materiau,     sm.id_unite_materiau,     sm.quantite_stockage,     sm.date_stockage,     sm.prix_unitaire,     sm.prix_total,     m.nom AS nom_materiau,     m.id_type_materiau,     tm.nom AS nom_type_materiau,     dm.longueur,     dm.largeur,     dm.hauteur,     um.nom AS nom_unite_materiau    FROM ((((stockage_materiau sm      JOIN materiau m ON ((m.id = sm.id_materiau)))      JOIN dimension_materiau dm ON ((dm.id = sm.id_dimension_materiau)))      JOIN unite_materiau um ON ((um.id = sm.id_unite_materiau)))      JOIN type_materiau tm ON ((tm.id = m.id_type_materiau)))
 SELECT sm.id,
    sm.id_materiau,
    sm.id_dimension_materiau,
    sm.id_unite_materiau,
    sm.quantite_stockage,
    sm.date_stockage,
    sm.prix_unitaire,
    sm.prix_total,
    m.nom AS nom_materiau,
    m.id_type_materiau,
    tm.nom AS nom_type_materiau,
    dm.longueur,
    dm.largeur,
    dm.hauteur,
    um.nom AS nom_unite_materiau
   FROM ((((stockage_materiau sm
     JOIN materiau m ON ((m.id = sm.id_materiau)))
     JOIN dimension_materiau dm ON ((dm.id = sm.id_dimension_materiau)))
     JOIN unite_materiau um ON ((um.id = sm.id_unite_materiau)))
     JOIN type_materiau tm ON ((tm.id = m.id_type_materiau)));

CREATE OR REPLACE VIEW v_unite_possible_materiau AS SELECT DISTINCT dupm.id_materiau,     dupm.id_unite_materiau,     um.nom AS nom_unite_materiau    FROM (dimension_unite_possible_materiau dupm      JOIN unite_materiau um ON ((um.id = dupm.id_unite_materiau)))
 SELECT DISTINCT dupm.id_materiau,
    dupm.id_unite_materiau,
    um.nom AS nom_unite_materiau
   FROM (dimension_unite_possible_materiau dupm
     JOIN unite_materiau um ON ((um.id = dupm.id_unite_materiau)));

INSERT INTO checkbox( id, nom ) VALUES ( 1, 'HTML');
INSERT INTO checkbox( id, nom ) VALUES ( 2, 'CSS');
INSERT INTO checkbox( id, nom ) VALUES ( 3, 'JS');
INSERT INTO dimension_materiau( id, longueur, largeur, hauteur ) VALUES ( 1, 1, 1, 1);
INSERT INTO dimension_materiau( id, longueur, largeur, hauteur ) VALUES ( 2, 20, 2, 1);
INSERT INTO option_reference( id, "option" ) VALUES ( 1, 'Option 1');
INSERT INTO option_reference( id, "option" ) VALUES ( 2, 'Option 2');
INSERT INTO reference( id, string, date_simple, heure_simple, date_heure, entier, pas_entier, id_option_reference, id_radio_reference ) VALUES ( 1, 'string', '2022-02-02', '05:00:00', '2022-02-02 05:00:00 am', 1, 12243.334234, 1, 2);
INSERT INTO reference( id, string, date_simple, heure_simple, date_heure, entier, pas_entier, id_option_reference, id_radio_reference ) VALUES ( 2, 'string', '2022-02-02', '05:00:00', '2022-02-02 05:00:00 am', 1, 12243.334234, 2, 1);
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
INSERT INTO checkbox_reference( id, id_reference, id_checkbox ) VALUES ( 1, 1, 1);
INSERT INTO checkbox_reference( id, id_reference, id_checkbox ) VALUES ( 2, 1, 2);
INSERT INTO checkbox_reference( id, id_reference, id_checkbox ) VALUES ( 3, 2, 2);
INSERT INTO checkbox_reference( id, id_reference, id_checkbox ) VALUES ( 4, 2, 3);
INSERT INTO details_reference( id, id_reference, details, note ) VALUES ( 1, 1, 'avgvwFVvWVwvWVWvev', 10.5);
INSERT INTO details_reference( id, id_reference, details, note ) VALUES ( 2, 1, 'avgvwFVvWVwvWVWvev', 10.5);
INSERT INTO details_reference( id, id_reference, details, note ) VALUES ( 3, 2, 'avgvwFVvWVwvWVWvev', 10.5);
INSERT INTO details_reference( id, id_reference, details, note ) VALUES ( 4, 2, 'avgvwFVvWVwvWVWvev', 10.5);
INSERT INTO materiau( id, nom, id_type_materiau, description ) VALUES ( 1, 'Palissandre', 1, 'Bois cool non nul');
INSERT INTO stockage_materiau( id, id_materiau, id_dimension_materiau, id_unite_materiau, quantite_stockage, date_stockage, prix_unitaire, prix_total ) VALUES ( 1, 1, 1, 1, 10, '2023-01-01', 100, 1000);
INSERT INTO dimension_unite_possible_materiau( id, id_materiau, id_dimension_materiau, id_unite_materiau ) VALUES ( 1, 1, 1, 1);
INSERT INTO dimension_unite_possible_materiau( id, id_materiau, id_dimension_materiau, id_unite_materiau ) VALUES ( 2, 1, 2, 1);
