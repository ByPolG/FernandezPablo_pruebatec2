-- 1. Creamos la base de datos si no existe
CREATE DATABASE IF NOT EXISTS turnos_db;

-- 2. La seleccionamos para usarla
USE turnos_db;

-- 3. Creamos la tabla "ciudadano"
CREATE TABLE IF NOT EXISTS ciudadano (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    dni VARCHAR(20) NOT NULL DEFAULT 'DNI por defecto' UNIQUE  -- Valor por defecto
);

-- 4. Creamos la tabla "turno"
CREATE TABLE IF NOT EXISTS turno (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero INT NOT NULL,
    fecha DATE NOT NULL,
    descripcion VARCHAR(255) NOT NULL DEFAULT 'Descripción por defecto', -- Valor por defecto
    estado ENUM('EN_ESPERA', 'YA_ATENDIDO') NOT NULL,
    ciudadano_id BIGINT,
    FOREIGN KEY (ciudadano_id) REFERENCES ciudadano(id) -- Referencia a la tabla "ciudadano"
);

-- 5. Creamos un índice en las columnas "fecha" y "estado" de la tabla "turno"
CREATE INDEX idx_fecha_estado ON turno(fecha, estado);

-- 6. Insertamos unos datos de ejemplo en la tabla "ciudadano"
INSERT INTO ciudadano (nombre, apellido, dni) VALUES
('Pablo', 'Fernandez Barredo', '12345678'),
('Carlos', 'Gonzalez', '11223344');

-- Aquí insertamos el valor por defecto del dni a un ciudadano creado
INSERT INTO ciudadano (nombre, apellido) VALUES
('Pedro', 'Rodriguez');


-- 7. Insertamos unos datos de ejemplo en la tabla "turno", "descripcion" usará el valor por defecto
INSERT INTO turno (numero, fecha, estado, ciudadano_id) VALUES
(1, '2025-01-21', 'EN_ESPERA', 1),
(2, '2025-01-21', 'YA_ATENDIDO', 2),
(3, '2025-01-22', 'EN_ESPERA', 3),
(4, '2025-01-23', 'EN_ESPERA', 1);
