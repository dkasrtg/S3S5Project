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

CREATE TABLE reference(
    id serial primary key,
    string varchar(20),
    date_simple date,
    heure_simple time,
    date_heure timestamp,
    entier integer,
    pas_entier numeric,
    id_option_reference integer,
    foreign key(id_option_reference) references option_reference(id)
);

insert into reference(string,date_simple,heure_simple,date_heure,entier,pas_entier,id_option_reference) values('string','2022-02-02','05:00:00','2022-02-02 05:00:00',1,12243.334234,1);
insert into reference(string,date_simple,heure_simple,date_heure,entier,pas_entier,id_option_reference) values('string','2022-02-02','05:00:00','2022-02-02 05:00:00',1,12243.334234,2);

CREATE OR REPLACE VIEW v_reference as 
select r.*,o.option from reference r join option_reference o on o.id=r.id_option_reference;

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


