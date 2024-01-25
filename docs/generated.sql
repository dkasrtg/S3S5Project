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
INSERT INTO employe( id, nom ) VALUES ( 1, 'Menuisier');
INSERT INTO employe( id, nom ) VALUES ( 2, 'Assembleur');
INSERT INTO employe( id, nom ) VALUES ( 3, 'Operateur de machine');
INSERT INTO employe( id, nom ) VALUES ( 4, 'Technicien de bois');
INSERT INTO employe( id, nom ) VALUES ( 5, 'Technicien en finition');
INSERT INTO option_reference( id, "option" ) VALUES ( 1, 'Option 1');
INSERT INTO option_reference( id, "option" ) VALUES ( 2, 'Option 2');
INSERT INTO reference( id, string, date_simple, heure_simple, date_heure, entier, pas_entier, id_option_reference, id_radio_reference ) VALUES ( 1, 'string', '2022-02-02', '05:00:00', '2022-02-02 05:00:00 am', 1, 12243.334234, 1, 2);
INSERT INTO reference( id, string, date_simple, heure_simple, date_heure, entier, pas_entier, id_option_reference, id_radio_reference ) VALUES ( 2, 'string', '2022-02-02', '05:00:00', '2022-02-02 05:00:00 am', 1, 12243.334234, 2, 1);
INSERT INTO salaire_employe( id, id_employe, date_debut, date_fin, valeur ) VALUES ( 1, 1, '2023-01-01 12:00:00 am', '9999-12-12 11:59:00 pm', 30000);
INSERT INTO salaire_employe( id, id_employe, date_debut, date_fin, valeur ) VALUES ( 2, 2, '2023-01-01 12:00:00 am', '9999-12-12 11:59:00 pm', 10000);
INSERT INTO salaire_employe( id, id_employe, date_debut, date_fin, valeur ) VALUES ( 3, 3, '2023-01-01 12:00:00 am', '9999-12-12 11:59:00 pm', 20000);
INSERT INTO salaire_employe( id, id_employe, date_debut, date_fin, valeur ) VALUES ( 4, 4, '2023-01-01 12:00:00 am', '9999-12-12 11:59:00 pm', 40000);
INSERT INTO salaire_employe( id, id_employe, date_debut, date_fin, valeur ) VALUES ( 5, 5, '2023-01-01 12:00:00 am', '9999-12-12 11:59:00 pm', 50000);
INSERT INTO style_meuble( id, nom ) VALUES ( 1, 'Boheme');
INSERT INTO style_meuble( id, nom ) VALUES ( 2, 'Scandinave');
INSERT INTO style_meuble( id, nom ) VALUES ( 3, 'Contemporaine');
INSERT INTO taille_meuble( id, nom ) VALUES ( 1, 'petit');
INSERT INTO taille_meuble( id, nom ) VALUES ( 2, 'moyen');
INSERT INTO taille_meuble( id, nom ) VALUES ( 3, 'grand');
INSERT INTO type_materiau( id, nom ) VALUES ( 1, 'bois');
INSERT INTO type_materiau( id, nom ) VALUES ( 2, 'metal');
INSERT INTO type_materiau( id, nom ) VALUES ( 3, 'plastique');
INSERT INTO type_materiau( id, nom ) VALUES ( 4, 'verre');
INSERT INTO type_materiau( id, nom ) VALUES ( 5, 'tissus');
INSERT INTO type_materiau( id, nom ) VALUES ( 6, 'contreplaque');
INSERT INTO checkbox_reference( id, id_reference, id_checkbox ) VALUES ( 1, 1, 1);
INSERT INTO checkbox_reference( id, id_reference, id_checkbox ) VALUES ( 2, 1, 2);
INSERT INTO checkbox_reference( id, id_reference, id_checkbox ) VALUES ( 3, 2, 2);
INSERT INTO checkbox_reference( id, id_reference, id_checkbox ) VALUES ( 4, 2, 3);
INSERT INTO details_reference( id, id_reference, details, note ) VALUES ( 1, 1, 'avgvwFVvWVwvWVWvev', 10.5);
INSERT INTO details_reference( id, id_reference, details, note ) VALUES ( 2, 1, 'avgvwFVvWVwvWVWvev', 10.5);
INSERT INTO details_reference( id, id_reference, details, note ) VALUES ( 3, 2, 'avgvwFVvWVwvWVWvev', 10.5);
INSERT INTO details_reference( id, id_reference, details, note ) VALUES ( 4, 2, 'avgvwFVvWVwvWVWvev', 10.5);
INSERT INTO materiau( id, nom, id_type_materiau, description ) VALUES ( 1, 'Chene', 1, 'Materiau durable et resistant');
INSERT INTO materiau( id, nom, id_type_materiau, description ) VALUES ( 2, 'Pin', 1, 'Bois leger utilise pour differents usages');
INSERT INTO materiau( id, nom, id_type_materiau, description ) VALUES ( 3, 'Erable', 1, 'Bois dur souvent utilise dans la fabrication de meubles');
INSERT INTO materiau( id, nom, id_type_materiau, description ) VALUES ( 4, 'Acier inoxydable', 2, 'Resistant a la corrosion et polyvalent');
INSERT INTO materiau( id, nom, id_type_materiau, description ) VALUES ( 5, 'Aluminium', 2, 'Leger et malleable, utilise dans diverses industries');
INSERT INTO materiau( id, nom, id_type_materiau, description ) VALUES ( 6, 'Cuivre', 2, 'Bonne conductivite thermique et electrique');
INSERT INTO materiau( id, nom, id_type_materiau, description ) VALUES ( 7, 'Polyethylene', 3, 'Resistant et flexible, utilise dans les emballages et les produits chimiques');
INSERT INTO materiau( id, nom, id_type_materiau, description ) VALUES ( 8, 'Polycarbonate', 3, 'Transparent et resistant aux chocs, utilise dans les lunettes et les vitres de protection');
INSERT INTO materiau( id, nom, id_type_materiau, description ) VALUES ( 9, 'Polypropylene', 3, 'Resistant a la chaleur, utilise dans les tapis et les conteneurs');
INSERT INTO materiau( id, nom, id_type_materiau, description ) VALUES ( 10, 'Verre borosilicate', 4, 'Resistant aux temperatures elevees, utilise dans les ustensiles de cuisine');
INSERT INTO materiau( id, nom, id_type_materiau, description ) VALUES ( 11, 'Verre trempe', 4, 'Renforce pour plus de resistance, utilise dans les smartphones et les pare-brise de voiture');
INSERT INTO materiau( id, nom, id_type_materiau, description ) VALUES ( 12, 'Verre cristallin', 4, 'Transparent et brillant, utilise dans la verrerie fine');
INSERT INTO materiau( id, nom, id_type_materiau, description ) VALUES ( 13, 'Coton', 5, 'Respirant et confortable, utilise dans les vetements');
INSERT INTO materiau( id, nom, id_type_materiau, description ) VALUES ( 14, 'Laine', 5, 'Isolant thermique naturel, utilise dans les tapis et les vetements d hiver');
INSERT INTO materiau( id, nom, id_type_materiau, description ) VALUES ( 15, 'Soie', 5, 'Legere et brillante, utilisee dans la mode haut de gamme');
INSERT INTO materiau( id, nom, id_type_materiau, description ) VALUES ( 16, 'Peuplier', 6, 'Leger et resistant, utilise dans la construction legere et le mobilier');
INSERT INTO materiau( id, nom, id_type_materiau, description ) VALUES ( 17, 'Epinette', 6, 'Stable et peu co–teux, utilise dans la fabrication de meubles');
INSERT INTO materiau( id, nom, id_type_materiau, description ) VALUES ( 18, 'Eucalyptus', 6, 'Durabilite et resistance a l humidite, utilise dans les revetements de sol');
INSERT INTO materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 1, 1, 1);
INSERT INTO materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 2, 1, 4);
INSERT INTO materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 3, 1, 7);
INSERT INTO materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 4, 1, 10);
INSERT INTO materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 5, 1, 13);
INSERT INTO materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 6, 1, 16);
INSERT INTO materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 7, 2, 2);
INSERT INTO materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 8, 2, 5);
INSERT INTO materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 9, 2, 8);
INSERT INTO materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 10, 2, 11);
INSERT INTO materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 11, 2, 14);
INSERT INTO materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 12, 2, 17);
INSERT INTO materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 13, 3, 3);
INSERT INTO materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 14, 3, 6);
INSERT INTO materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 15, 3, 9);
INSERT INTO materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 16, 3, 12);
INSERT INTO materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 17, 3, 15);
INSERT INTO materiau_possible_style_meuble( id, id_style_meuble, id_materiau ) VALUES ( 18, 3, 18);
INSERT INTO meuble( id, nom, id_style_meuble, id_categorie_meuble, description ) VALUES ( 1, 'Table en Chene', 1, 1, 'Belle table en bois de chene pour la salle a manger');
INSERT INTO meuble( id, nom, id_style_meuble, id_categorie_meuble, description ) VALUES ( 2, 'Table Basse Scandinave', 2, 1, 'Table basse au style scandinave pour le salon');
INSERT INTO meuble( id, nom, id_style_meuble, id_categorie_meuble, description ) VALUES ( 3, 'Table de Travail Contemporaine', 3, 1, 'Table de travail moderne pour le bureau');
INSERT INTO meuble( id, nom, id_style_meuble, id_categorie_meuble, description ) VALUES ( 4, 'Chaise en Pin', 2, 2, 'Chaise legere en pin pour la salle a manger');
INSERT INTO meuble( id, nom, id_style_meuble, id_categorie_meuble, description ) VALUES ( 5, 'Chaise Bois et Metal', 3, 2, 'Chaise contemporaine en bois et metal pour differents usages');
INSERT INTO meuble( id, nom, id_style_meuble, id_categorie_meuble, description ) VALUES ( 6, 'Canape Boheme', 1, 3, 'Canape confortable au style boheme pour le salon');
INSERT INTO meuble( id, nom, id_style_meuble, id_categorie_meuble, description ) VALUES ( 7, 'Canape Scandinave Convertible', 2, 3, 'Canape convertible au design scandinave pour un usage multifonctionnel');
INSERT INTO meuble( id, nom, id_style_meuble, id_categorie_meuble, description ) VALUES ( 8, 'Armoire en erable', 3, 4, 'Armoire spacieuse en erable pour la chambre a coucher');
INSERT INTO meuble( id, nom, id_style_meuble, id_categorie_meuble, description ) VALUES ( 9, 'Armoire Contemporaine avec Miroir', 2, 4, 'Armoire moderne avec miroir pour ranger vos vetements');
INSERT INTO meuble( id, nom, id_style_meuble, id_categorie_meuble, description ) VALUES ( 10, 'Coffre en Peuplier', 1, 5, 'Coffre en bois de peuplier pour stocker differents objets');
INSERT INTO meuble( id, nom, id_style_meuble, id_categorie_meuble, description ) VALUES ( 11, 'Coiffeuse Scandinave', 2, 6, 'Coiffeuse au style scandinave avec miroir pour la chambre');
INSERT INTO meuble( id, nom, id_style_meuble, id_categorie_meuble, description ) VALUES ( 12, 'Bibliotheque Contemporaine', 3, 7, 'Bibliotheque moderne pour exposer vos livres et objets decoratifs');
INSERT INTO meuble( id, nom, id_style_meuble, id_categorie_meuble, description ) VALUES ( 13, 'Vitrine en Verre Trempe', 2, 8, 'Vitrine elegante en verre trempe pour exposer vos collections');
INSERT INTO mouvement_materiau( id, date_mouvement, id_materiau, quantite, prix_unitaire, type_mouvement, id_mouvement_mere, description, id_mouvement_meuble ) VALUES ( 1, '2024-01-01 12:00:00 am', 1, 100, 1000, 1, -1, '', -1);
INSERT INTO mouvement_materiau( id, date_mouvement, id_materiau, quantite, prix_unitaire, type_mouvement, id_mouvement_mere, description, id_mouvement_meuble ) VALUES ( 2, '2024-01-01 12:00:00 am', 2, 100, 3000, 1, -1, '', -1);
INSERT INTO mouvement_materiau( id, date_mouvement, id_materiau, quantite, prix_unitaire, type_mouvement, id_mouvement_mere, description, id_mouvement_meuble ) VALUES ( 3, '2024-01-01 12:00:00 am', 5, 100, 2500, 1, -1, '', -1);
INSERT INTO mouvement_materiau( id, date_mouvement, id_materiau, quantite, prix_unitaire, type_mouvement, id_mouvement_mere, description, id_mouvement_meuble ) VALUES ( 4, '2024-01-01 12:00:00 am', 16, 100, 1500, 1, -1, '', -1);
INSERT INTO mouvement_materiau( id, date_mouvement, id_materiau, quantite, prix_unitaire, type_mouvement, id_mouvement_mere, description, id_mouvement_meuble ) VALUES ( 5, '2024-01-01 01:00:00 am', 1, 20, 1000, -1, 1, 'Test', -1);
INSERT INTO mouvement_materiau( id, date_mouvement, id_materiau, quantite, prix_unitaire, type_mouvement, id_mouvement_mere, description, id_mouvement_meuble ) VALUES ( 6, '2024-01-01 01:00:00 am', 1, 100, 12000, 1, -1, '', -1);
INSERT INTO mouvement_materiau( id, date_mouvement, id_materiau, quantite, prix_unitaire, type_mouvement, id_mouvement_mere, description, id_mouvement_meuble ) VALUES ( 7, '2024-01-01 02:00:00 am', 1, 80, 1000, -1, 1, '', -1);
INSERT INTO mouvement_materiau( id, date_mouvement, id_materiau, quantite, prix_unitaire, type_mouvement, id_mouvement_mere, description, id_mouvement_meuble ) VALUES ( 8, '2024-01-01 02:00:00 am', 1, 1, 12000, -1, 6, '', -1);
INSERT INTO formule_meuble( id, id_meuble, id_taille_meuble ) VALUES ( 1, 1, 1);
INSERT INTO formule_meuble( id, id_meuble, id_taille_meuble ) VALUES ( 3, 2, 1);
INSERT INTO formule_meuble( id, id_meuble, id_taille_meuble ) VALUES ( 4, 1, 3);
INSERT INTO prix_de_vente_meuble( id, id_formule_meuble, date_debut, date_fin, valeur ) VALUES ( 1, 1, '2024-01-01 12:00:00 am', '9999-12-12 11:59:00 pm', 1000000);
INSERT INTO prix_de_vente_meuble( id, id_formule_meuble, date_debut, date_fin, valeur ) VALUES ( 2, 3, '2024-01-01 12:00:00 am', '9999-12-12 11:59:00 pm', 1500000);
INSERT INTO prix_de_vente_meuble( id, id_formule_meuble, date_debut, date_fin, valeur ) VALUES ( 3, 4, '2024-01-01 12:00:00 am', '9999-12-12 11:59:00 pm', 10000000);
INSERT INTO detail_employe_meuble( id, id_formule_meuble, id_employe, nombre, duree ) VALUES ( 1, 1, 1, 1, 10);
INSERT INTO detail_employe_meuble( id, id_formule_meuble, id_employe, nombre, duree ) VALUES ( 2, 1, 2, 1, 5);
INSERT INTO detail_employe_meuble( id, id_formule_meuble, id_employe, nombre, duree ) VALUES ( 3, 1, 5, 1, 5);
INSERT INTO detail_employe_meuble( id, id_formule_meuble, id_employe, nombre, duree ) VALUES ( 4, 3, 1, 1, 10);
INSERT INTO detail_employe_meuble( id, id_formule_meuble, id_employe, nombre, duree ) VALUES ( 5, 3, 3, 1, 5);
INSERT INTO detail_employe_meuble( id, id_formule_meuble, id_employe, nombre, duree ) VALUES ( 6, 3, 5, 1, 5);
INSERT INTO detail_employe_meuble( id, id_formule_meuble, id_employe, nombre, duree ) VALUES ( 7, 4, 1, 2, 30);
INSERT INTO detail_employe_meuble( id, id_formule_meuble, id_employe, nombre, duree ) VALUES ( 8, 4, 2, 1, 15);
INSERT INTO detail_employe_meuble( id, id_formule_meuble, id_employe, nombre, duree ) VALUES ( 9, 4, 5, 1, 15);
INSERT INTO detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 1, 1, 1, 1);
INSERT INTO detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 2, 1, 16, 1);
INSERT INTO detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 3, 3, 2, 1);
INSERT INTO detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 4, 3, 5, 1);
INSERT INTO detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 5, 4, 1, 5);
INSERT INTO detail_formule_meuble( id, id_formule_meuble, id_materiau, quantite ) VALUES ( 6, 4, 16, 2);
