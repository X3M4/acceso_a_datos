-- Crear la base de datos "gotbd"
CREATE DATABASE IF NOT EXISTS gotbd;

-- Usar la base de datos "gotbd"
USE gotbd;

-- Crear la tabla "characters" para almacenar los personajes
CREATE TABLE IF NOT EXISTS characters (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    gender VARCHAR(255),
	culture VARCHAR(255),
	born VARCHAR(255),
	died VARCHAR(255),
	alive BOOLEAN,
	father VARCHAR(255),
	mother VARCHAR(255),
	spouse VARCHAR(255)
);

-- Crear la tabla "titles" para almacenar información de titulos de los caracteres
CREATE TABLE IF NOT EXISTS titles (
    id INT AUTO_INCREMENT PRIMARY KEY,
	title VARCHAR(255) NOT NULL,
	id_char INT NOT NULL,
    FOREIGN KEY(id_char) REFERENCES characters(id)
);


-- Crear la tabla "aliases" para almacenar información de alias
CREATE TABLE IF NOT EXISTS aliases (
    id INT AUTO_INCREMENT PRIMARY KEY,
    alias VARCHAR(255) NOT NULL,
	id_char INT NOT NULL,
    FOREIGN KEY(id_char) REFERENCES characters(id)
);

-- Crear la tabla "allegiances" para almacenar información de las lealtades
CREATE TABLE IF NOT EXISTS allegiances (
    id INT AUTO_INCREMENT PRIMARY KEY,
    allegiance VARCHAR(255) NOT NULL
);

-- Crear la tabla intermedia "alleg_char" para almacenar información de caracteres y sus lealtades
CREATE TABLE IF NOT EXISTS alleg_char (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_alleg INT NOT NULL,
	id_char INT NOT NULL,
	FOREIGN KEY(id_alleg) REFERENCES allegiances(id),
	FOREIGN KEY(id_char) REFERENCES characters(id)
);

-- Crear la tabla "books" para almacenar información de libros
CREATE TABLE IF NOT EXISTS books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    book VARCHAR(255) NOT NULL
);

-- Crear la tabla intermedia "book_char" para almacenar información de caracteres que aparecen en libros
CREATE TABLE IF NOT EXISTS book_char (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_book INT NOT NULL,
	id_char INT NOT NULL,
	FOREIGN KEY(id_book) REFERENCES books(id),
	FOREIGN KEY(id_char) REFERENCES characters(id)
);


-- Crear la tabla "tvseries" para almacenar información de la serie
CREATE TABLE IF NOT EXISTS tvseries (
    id INT AUTO_INCREMENT PRIMARY KEY,
    season VARCHAR(255) NOT NULL
);

-- Crear la tabla intermedia "tvserie_char" para almacenar información de caracteres que aparecen en la serie
CREATE TABLE IF NOT EXISTS tvserie_char (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_tvs INT NOT NULL,
	id_char INT NOT NULL,
	FOREIGN KEY(id_tvs) REFERENCES tvseries(id),
	FOREIGN KEY(id_char) REFERENCES characters(id)
);