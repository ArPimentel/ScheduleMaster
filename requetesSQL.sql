CREATE DATABASE mesTodos;

CREATE TABLE liste(
    id SERIAL PRIMARY KEY,
    nom VARCHAR
);

CREATE TABLE todo(
    id SERIAL PRIMARY KEY,
    nom VARCHAR,
    description VARCHAR,
    dateDeFin TIMESTAMP,
    estTermine BOOLEAN,
    idListe INTEGER REFERENCES liste
);