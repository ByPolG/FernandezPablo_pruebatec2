CREATE DATABASE db_turnero;

USE db_turnero;

-- Creamos la tabla Ciudadano

CREATE TABLE ciudadano (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    documento VARCHAR(20) UNIQUE NOT NULL
);

-- Creamos la tabla Turno

CREATE TABLE turno (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero INT NOT NULL,
    descripcion VARCHAR(200) NOT NULL,
    estado ENUM('En espera', 'Ya atendido') NOT NULL,
    fecha DATE NOT NULL,
    ciudadano_id BIGINT NOT NULL,
    FOREIGN KEY (ciudadano_id) REFERENCES ciudadano(id)
);

-- Insertamos algunos datos en las tablas

INSERT INTO ciudadano (nombre, apellido, documento) VALUES
('Pablo', 'Fernandez', '12345678'),
('Brian', 'Gomez', '87654321'),
('Pedro', 'Garcia', '45678912');

INSERT INTO turno (numero, descripcion, estado, fecha, ciudadano_id) VALUES
(1, 'Renovación de DNI', 'En espera', '2025-01-20', 1),
(2, 'Trámite de DNI', 'Ya atendido', '2025-01-18', 2),
(3, 'Cambio de domicilio', 'En espera', '2025-01-19', 3);
