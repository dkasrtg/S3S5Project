CREATE SCHEMA IF NOT EXISTS "public";

CREATE SEQUENCE categorie_meuble_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE checkbox_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE checkbox_reference_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE composant_meuble_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE detail_fabrication_meuble_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE details_reference_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE dimension_materiau_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE dimension_unite_possible_materiau_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE fabrication_meuble_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE lieu_meuble_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE lieu_possible_meuble_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE materiau_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE materiau_possible_style_meuble_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE meuble_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE option_reference_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE reference_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE stockage_materiau_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE style_meuble_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE type_materiau_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE unite_materiau_id_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE  TABLE categorie_meuble ( 
	id                   serial DEFAULT nextval('categorie_meuble_id_seq'::regclass) NOT NULL  ,
	nom                  varchar(200)    ,
	CONSTRAINT categorie_meuble_pkey PRIMARY KEY ( id )
 );

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

CREATE  TABLE lieu_meuble ( 
	id                   serial DEFAULT nextval('lieu_meuble_id_seq'::regclass) NOT NULL  ,
	nom                  varchar(200)    ,
	CONSTRAINT lieu_meuble_pkey PRIMARY KEY ( id )
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
	CONSTRAINT reference_id_radio_reference_fkey FOREIGN KEY ( id_radio_reference ) REFERENCES option_reference( id )   ,
	CONSTRAINT reference_id_option_reference_fkey FOREIGN KEY ( id_option_reference ) REFERENCES option_reference( id )   
 );

CREATE  TABLE style_meuble ( 
	id                   serial DEFAULT nextval('style_meuble_id_seq'::regclass) NOT NULL  ,
	nom                  varchar(200)    ,
	CONSTRAINT style_meuble_pkey PRIMARY KEY ( id )
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
	CONSTRAINT checkbox_reference_id_checkbox_fkey FOREIGN KEY ( id_checkbox ) REFERENCES checkbox( id )   ,
	CONSTRAINT checkbox_reference_id_reference_fkey FOREIGN KEY ( id_reference ) REFERENCES reference( id ) ON DELETE CASCADE ON UPDATE CASCADE 
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

CREATE  TABLE materiau_possible_style_meuble ( 
	id                   serial DEFAULT nextval('materiau_possible_style_meuble_id_seq'::regclass) NOT NULL  ,
	id_style_meuble      integer    ,
	id_materiau          integer    ,
	CONSTRAINT materiau_possible_style_meuble_pkey PRIMARY KEY ( id ),
	CONSTRAINT materiau_possible_style_meuble_id_materiau_fkey FOREIGN KEY ( id_materiau ) REFERENCES materiau( id )   ,
	CONSTRAINT materiau_possible_style_meuble_id_style_meuble_fkey FOREIGN KEY ( id_style_meuble ) REFERENCES style_meuble( id )   
 );

CREATE  TABLE meuble ( 
	id                   serial DEFAULT nextval('meuble_id_seq'::regclass) NOT NULL  ,
	nom                  varchar(200)    ,
	id_style_meuble      integer    ,
	id_categorie_meuble  integer    ,
	longueur             numeric    ,
	largeur              numeric    ,
	hauteur              numeric    ,
	volume               numeric    ,
	volume_materiau      numeric    ,
	description          text    ,
	CONSTRAINT meuble_pkey PRIMARY KEY ( id ),
	CONSTRAINT meuble_id_categorie_meuble_fkey FOREIGN KEY ( id_categorie_meuble ) REFERENCES categorie_meuble( id )   ,
	CONSTRAINT meuble_id_style_meuble_fkey FOREIGN KEY ( id_style_meuble ) REFERENCES style_meuble( id )   
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
	CONSTRAINT stockage_materiau_id_unite_materiau_fkey FOREIGN KEY ( id_unite_materiau ) REFERENCES unite_materiau( id )   ,
	CONSTRAINT stockage_materiau_id_dimension_materiau_fkey FOREIGN KEY ( id_dimension_materiau ) REFERENCES dimension_materiau( id )   ,
	CONSTRAINT stockage_materiau_id_materiau_fkey FOREIGN KEY ( id_materiau ) REFERENCES materiau( id )   
 );

CREATE  TABLE composant_meuble ( 
	id                   serial DEFAULT nextval('composant_meuble_id_seq'::regclass) NOT NULL  ,
	nom                  varchar(200)    ,
	id_meuble            integer    ,
	id_type_materiau     integer    ,
	volume               numeric    ,
	CONSTRAINT composant_meuble_pkey PRIMARY KEY ( id ),
	CONSTRAINT composant_meuble_id_type_materiau_fkey FOREIGN KEY ( id_type_materiau ) REFERENCES type_materiau( id )   ,
	CONSTRAINT composant_meuble_id_meuble_fkey FOREIGN KEY ( id_meuble ) REFERENCES meuble( id )   
 );

CREATE  TABLE dimension_unite_possible_materiau ( 
	id                   serial DEFAULT nextval('dimension_unite_possible_materiau_id_seq'::regclass) NOT NULL  ,
	id_materiau          integer    ,
	id_dimension_materiau integer    ,
	id_unite_materiau    integer    ,
	CONSTRAINT dimension_unite_possible_materiau_pkey PRIMARY KEY ( id ),
	CONSTRAINT dimension_unite_possible_materiau_id_unite_materiau_fkey FOREIGN KEY ( id_unite_materiau ) REFERENCES unite_materiau( id )   ,
	CONSTRAINT dimension_unite_possible_materiau_id_dimension_materiau_fkey FOREIGN KEY ( id_dimension_materiau ) REFERENCES dimension_materiau( id )   ,
	CONSTRAINT dimension_unite_possible_materiau_id_materiau_fkey FOREIGN KEY ( id_materiau ) REFERENCES materiau( id )   
 );

CREATE  TABLE fabrication_meuble ( 
	id                   serial DEFAULT nextval('fabrication_meuble_id_seq'::regclass) NOT NULL  ,
	id_meuble            integer    ,
	date_fabrication     date    ,
	quantite             integer    ,
	cout_unitaire_fabrication numeric    ,
	cout_total_fabrication numeric    ,
	marge_beneficiaire   numeric    ,
	prix_unitaire_de_vente numeric    ,
	CONSTRAINT fabrication_meuble_pkey PRIMARY KEY ( id ),
	CONSTRAINT fabrication_meuble_id_meuble_fkey FOREIGN KEY ( id_meuble ) REFERENCES meuble( id )   
 );

CREATE  TABLE lieu_possible_meuble ( 
	id                   serial DEFAULT nextval('lieu_possible_meuble_id_seq'::regclass) NOT NULL  ,
	id_meuble            integer    ,
	id_lieu_meuble       integer    ,
	CONSTRAINT lieu_possible_meuble_pkey PRIMARY KEY ( id ),
	CONSTRAINT lieu_possible_meuble_id_lieu_meuble_fkey FOREIGN KEY ( id_lieu_meuble ) REFERENCES lieu_meuble( id )   ,
	CONSTRAINT lieu_possible_meuble_id_meuble_fkey FOREIGN KEY ( id_meuble ) REFERENCES meuble( id )   
 );

CREATE  TABLE detail_fabrication_meuble ( 
	id                   serial DEFAULT nextval('detail_fabrication_meuble_id_seq'::regclass) NOT NULL  ,
	id_fabrication_meuble integer    ,
	id_composant_meuble  integer    ,
	id_stockage_materiau integer    ,
	quantite             numeric    ,
	cout_unitaire        numeric    ,
	cout_total           numeric    ,
	CONSTRAINT detail_fabrication_meuble_pkey PRIMARY KEY ( id ),
	CONSTRAINT detail_fabrication_meuble_id_stockage_materiau_fkey FOREIGN KEY ( id_stockage_materiau ) REFERENCES stockage_materiau( id )   ,
	CONSTRAINT detail_fabrication_meuble_id_composant_meuble_fkey FOREIGN KEY ( id_composant_meuble ) REFERENCES composant_meuble( id )   ,
	CONSTRAINT detail_fabrication_meuble_id_fabrication_meuble_fkey FOREIGN KEY ( id_fabrication_meuble ) REFERENCES fabrication_meuble( id )   
 );

CREATE OR REPLACE VIEW v_checkbox_reference AS SELECT cr.id,     cr.id_reference,     cr.id_checkbox,     c.nom    FROM (checkbox_reference cr      JOIN checkbox c ON ((c.id = cr.id_checkbox)))
 SELECT cr.id,
    cr.id_reference,
    cr.id_checkbox,
    c.nom
   FROM (checkbox_reference cr
     JOIN checkbox c ON ((c.id = cr.id_checkbox)));

CREATE OR REPLACE VIEW v_composant_meuble AS SELECT cm.id,     cm.nom,     cm.id_meuble,     cm.id_type_materiau,     cm.volume,     tm.nom AS nom_type_materiau    FROM (composant_meuble cm      JOIN type_materiau tm ON ((tm.id = cm.id_type_materiau)))
 SELECT cm.id,
    cm.nom,
    cm.id_meuble,
    cm.id_type_materiau,
    cm.volume,
    tm.nom AS nom_type_materiau
   FROM (composant_meuble cm
     JOIN type_materiau tm ON ((tm.id = cm.id_type_materiau)));

CREATE OR REPLACE VIEW v_dimension_possible_materiau AS SELECT dupm.id,     dupm.id_materiau,     dupm.id_dimension_materiau,     dm.longueur,     dm.largeur,     dm.hauteur    FROM (dimension_unite_possible_materiau dupm      JOIN dimension_materiau dm ON ((dm.id = dupm.id_dimension_materiau)))
 SELECT dupm.id,
    dupm.id_materiau,
    dupm.id_dimension_materiau,
    dm.longueur,
    dm.largeur,
    dm.hauteur
   FROM (dimension_unite_possible_materiau dupm
     JOIN dimension_materiau dm ON ((dm.id = dupm.id_dimension_materiau)));

CREATE OR REPLACE VIEW v_lieu_possible_meuble AS SELECT lpm.id,     lpm.id_meuble,     lpm.id_lieu_meuble,     lm.nom    FROM (lieu_possible_meuble lpm      JOIN lieu_meuble lm ON ((lpm.id_lieu_meuble = lm.id)))
 SELECT lpm.id,
    lpm.id_meuble,
    lpm.id_lieu_meuble,
    lm.nom
   FROM (lieu_possible_meuble lpm
     JOIN lieu_meuble lm ON ((lpm.id_lieu_meuble = lm.id)));

CREATE OR REPLACE VIEW v_materiau AS SELECT m.id,     m.nom,     m.id_type_materiau,     m.description,     tm.nom AS nom_type_materiau    FROM (materiau m      JOIN type_materiau tm ON ((m.id_type_materiau = tm.id)))
 SELECT m.id,
    m.nom,
    m.id_type_materiau,
    m.description,
    tm.nom AS nom_type_materiau
   FROM (materiau m
     JOIN type_materiau tm ON ((m.id_type_materiau = tm.id)));

CREATE OR REPLACE VIEW v_materiau_possible_style_meuble AS SELECT mpsm.id,     mpsm.id_style_meuble,     mpsm.id_materiau,     m.nom AS nom_materiau,     m.id_type_materiau,     tm.nom AS nom_type_materiau    FROM ((materiau_possible_style_meuble mpsm      JOIN materiau m ON ((m.id = mpsm.id_materiau)))      JOIN type_materiau tm ON ((tm.id = m.id_type_materiau)))
 SELECT mpsm.id,
    mpsm.id_style_meuble,
    mpsm.id_materiau,
    m.nom AS nom_materiau,
    m.id_type_materiau,
    tm.nom AS nom_type_materiau
   FROM ((materiau_possible_style_meuble mpsm
     JOIN materiau m ON ((m.id = mpsm.id_materiau)))
     JOIN type_materiau tm ON ((tm.id = m.id_type_materiau)));

CREATE OR REPLACE VIEW v_meuble AS SELECT m.id,     m.nom,     m.id_style_meuble,     m.id_categorie_meuble,     m.longueur,     m.largeur,     m.hauteur,     m.volume,     m.volume_materiau,     m.description,     sm.nom AS nom_style_meuble,     cm.nom AS nom_categorie_meuble    FROM ((meuble m      JOIN style_meuble sm ON ((sm.id = m.id_style_meuble)))      JOIN categorie_meuble cm ON ((cm.id = m.id_categorie_meuble)))
 SELECT m.id,
    m.nom,
    m.id_style_meuble,
    m.id_categorie_meuble,
    m.longueur,
    m.largeur,
    m.hauteur,
    m.volume,
    m.volume_materiau,
    m.description,
    sm.nom AS nom_style_meuble,
    cm.nom AS nom_categorie_meuble
   FROM ((meuble m
     JOIN style_meuble sm ON ((sm.id = m.id_style_meuble)))
     JOIN categorie_meuble cm ON ((cm.id = m.id_categorie_meuble)));

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

INSERT INTO categorie_meuble( id, nom ) VALUES ( 1, 'Table');
INSERT INTO categorie_meuble( id, nom ) VALUES ( 2, 'Chaise');
INSERT INTO categorie_meuble( id, nom ) VALUES ( 3, 'Canape');
INSERT INTO categorie_meuble( id, nom ) VALUES ( 4, 'Armoire');
INSERT INTO categorie_meuble( id, nom ) VALUES ( 5, 'Coffre');
INSERT INTO categorie_meuble( id, nom ) VALUES ( 6, 'Coiffeuse');
INSERT INTO categorie_meuble( id, nom ) VALUES ( 7, 'Biblotheque');
INSERT INTO categorie_meuble( id, nom ) VALUES ( 8, 'Vitrine');
INSERT INTO checkbox( id, nom ) VALUES ( 1, 'HTML');
INSERT INTO checkbox( id, nom ) VALUES ( 2, 'CSS');
INSERT INTO checkbox( id, nom ) VALUES ( 3, 'JS');
INSERT INTO dimension_materiau( id, longueur, largeur, hauteur ) VALUES ( 1, 1, 1, 1);
INSERT INTO dimension_materiau( id, longueur, largeur, hauteur ) VALUES ( 2, 20, 2, 1);
INSERT INTO lieu_meuble( id, nom ) VALUES ( 1, 'Cuisine');
INSERT INTO lieu_meuble( id, nom ) VALUES ( 2, 'Salle a manger');
INSERT INTO lieu_meuble( id, nom ) VALUES ( 3, 'Salon');
INSERT INTO lieu_meuble( id, nom ) VALUES ( 4, 'Chambre a coucher');
INSERT INTO option_reference( id, "option" ) VALUES ( 1, 'Option 1');
INSERT INTO option_reference( id, "option" ) VALUES ( 2, 'Option 2');
INSERT INTO reference( id, string, date_simple, heure_simple, date_heure, entier, pas_entier, id_option_reference, id_radio_reference ) VALUES ( 1, 'string', '2022-02-02', '05:00:00', '2022-02-02 05:00:00 am', 1, 12243.334234, 1, 2);
INSERT INTO reference( id, string, date_simple, heure_simple, date_heure, entier, pas_entier, id_option_reference, id_radio_reference ) VALUES ( 2, 'string', '2022-02-02', '05:00:00', '2022-02-02 05:00:00 am', 1, 12243.334234, 2, 1);
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
INSERT INTO checkbox_reference( id, id_reference, id_checkbox ) VALUES ( 1, 1, 1);
INSERT INTO checkbox_reference( id, id_reference, id_checkbox ) VALUES ( 2, 1, 2);
INSERT INTO checkbox_reference( id, id_reference, id_checkbox ) VALUES ( 3, 2, 2);
INSERT INTO checkbox_reference( id, id_reference, id_checkbox ) VALUES ( 4, 2, 3);
INSERT INTO details_reference( id, id_reference, details, note ) VALUES ( 1, 1, 'avgvwFVvWVwvWVWvev', 10.5);
INSERT INTO details_reference( id, id_reference, details, note ) VALUES ( 2, 1, 'avgvwFVvWVwvWVWvev', 10.5);
INSERT INTO details_reference( id, id_reference, details, note ) VALUES ( 3, 2, 'avgvwFVvWVwvWVWvev', 10.5);
INSERT INTO details_reference( id, id_reference, details, note ) VALUES ( 4, 2, 'avgvwFVvWVwvWVWvev', 10.5);
INSERT INTO materiau( id, nom, id_type_materiau, description ) VALUES ( 1, 'Palissandre', 1, 'Bois cool non nul');
INSERT INTO materiau( id, nom, id_type_materiau, description ) VALUES ( 2, 'Ebene', 1, 'Bois mlay');
INSERT INTO materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 1, 2, 1);
INSERT INTO materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 2, 3, 2);
INSERT INTO meuble( id, nom, id_style_meuble, id_categorie_meuble, longueur, largeur, hauteur, volume, volume_materiau, description ) VALUES ( 3, 'Tabouret Tabou', 1, 1, 10, 10, 10, 1000, 100, 'wer');
INSERT INTO stockage_materiau( id, id_materiau, id_dimension_materiau, id_unite_materiau, quantite_stockage, date_stockage, prix_unitaire, prix_total ) VALUES ( 1, 1, 1, 1, 10, '2023-01-01', 100, 1000);
INSERT INTO composant_meuble( id, nom, id_meuble, id_type_materiau, volume ) VALUES ( 1, 'Structure', 3, 1, 100);
INSERT INTO dimension_unite_possible_materiau( id, id_materiau, id_dimension_materiau, id_unite_materiau ) VALUES ( 1, 1, 1, 1);
INSERT INTO dimension_unite_possible_materiau( id, id_materiau, id_dimension_materiau, id_unite_materiau ) VALUES ( 2, 1, 2, 1);
INSERT INTO dimension_unite_possible_materiau( id, id_materiau, id_dimension_materiau, id_unite_materiau ) VALUES ( 3, 2, 1, 1);
INSERT INTO lieu_possible_meuble( id, id_meuble, id_lieu_meuble ) VALUES ( 1, 3, 1);
