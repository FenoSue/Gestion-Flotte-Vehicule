/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  USER
 * Created: 17 oct. 2023
 */

create database Vehicule;
alter database Vehicule owner to projet;

create table Admin (
    id serial primary key,
    login varchar not null,
    pwd varchar not null
);
insert into Admin (login,pwd) values
('Solo','solo'),
('Maria','1234');

create table Vehicule (
    id serial primary key,
    matricule varchar unique not null,
    marque varchar not null,
    modele varchar not null
);

create table Kilometrage (
    id serial primary key,
    idVehicule integer not null references Vehicule (id),
    dateKilometrage date default now(),
    debutKilometrage integer default 1,
    finkilometrage integer default 1
);

create view KilometrageVehicule as
select vehicule.id as idVehicule, vehicule.matricule as matricule, vehicule.marque as marque, vehicule.modele as modele, 
kilometrage.datekilometrage as dateKilometrage, kilometrage.debutkilometrage as debut,kilometrage.finkilometrage as fin 
from vehicule join kilometrage on vehicule.id=kilometrage.idvehicule;
