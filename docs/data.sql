create table categorie_meuble(
    id serial primary key,
    nom varchar(200)
);

create table style_meuble(
    id serial primary key,
    nom varchar(200)
);

create table type_meuble(
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

create table type_possible_meuble(
    id serial primary key,
    id_meuble integer,
    id_type_meuble integer,
    foreign key(id_meuble) references meuble(id),
    foreign key(id_type_meuble) references type_meuble(id)
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
    dimension varchar(200)  
);

create table dimension_possible_materiau(
    id serial primary key,
    id_materiau integer,
    id_dimension_materiau integer,
    foreign key(id_materiau) references materiau(id),
    foreign key(id_dimension_materiau) references dimension_materiau(id)
);

create table materiau_possible_style_meuble(
    id serial primary key,
    id_style_meuble integer,
    id_materiau integer,
    foreign key(id_style_meuble) references style_meuble(id),
    foreign key(id_materiau) references materiau(id)
);



create table stockage_materiau(
    id serial primary key,
    id_materiau integer,
    id_dimension_materiau integer,
    quantite_stockage numeric,
    date_stockage date,
    prix_unitaire numeric,
    prix_total numeric,
    foreign key(id_materiau) references materiau(id),
    foreign key(id_dimension_materiau) references dimension_materiau(id)
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


create table quantite_dimension_materiau_meuble(
    id serial primary key,
    id_materiau integer,
    id_dimension_materiau integer,
    quantite numeric,
    id_meuble integer,
    foreign key(id_materiau) references materiau(id),
    foreign key(id_dimension_materiau) references dimension_materiau(id),
    foreign key(id_meuble) references meuble(id)
);

create table details_fabrication_meuble(
    id serial primary key,
    id_fabrication_meuble integer,
    id_quantite_dimension_materiau_meuble integer,
    quantite numeric,
    foreign key(id_fabrication_meuble) references fabrication_meuble(id),
    foreign key(id_quantite_dimension_materiau_meuble) references quantite_dimension_materiau_meuble(id)
);

create table destockage_materiau(
    id serial primary key,
    id_details_fabrication_meuble integer,
    id_stockage_materiau integer,
    quantite numeric,
    date_destockage date,
    foreign key(id_details_fabrication_meuble) references details_fabrication_meuble(id),
    foreign key(id_stockage_materiau) references stockage_materiau(id)
);

