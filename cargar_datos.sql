-- CARGAR DATOS

Use sofa_bbdd;

-- DEPARTAMENTOS
INSERT INTO departamentos (nombre) VALUES
('carpinteria'),
('costura'),
('tapizado'),
('enfundado');

-- PERFILES
INSERT INTO perfiles (rol)VALUES
('administrador'),
('comercial'),
('operario');

INSERT INTO empleados (nombre, apellidos, password, id_depto, id_perfil, fecha_ingreso, fecha_baja, estado, salario) 
VALUES 
('Juan', 'Pérez', 'juan', 1, 1, '2023-05-10', NULL, 1 , 30000),
('María', 'García', 'maria',  2, 2, '2022-08-20', NULL, 1 , 35000),
('Pedro', 'Martínez', 'pedro', 3, 3, '2024-01-15', NULL, 0 , 28000),
('Ana', 'López', 'ana', 1, 2, '2023-11-03', NULL, 1, 32000),
('Carlos', 'Sánchez', 'carlos', 2, 1, '2021-10-05', NULL, 1, 38000),
('Laura', 'Gómez','laura', 3, 3, '2024-02-28', NULL, 1, 27000),
('Luis', 'Hernández','luis', 1, 3, '2022-04-12', NULL, 1, 31000),
('Sofía', 'Díaz', 'sofia', 2, 2, '2023-09-08', NULL, 1, 33000),
('Javier', 'Ruiz','javier', 3, 1, '2021-12-20', NULL, 1, 36000);

-- CLIENTES
INSERT INTO clientes (nombre, apellidos, direccion, email, telefono) VALUES
('Juan', 'Pérez', 'Calle 123', 'juan@example.com', '123-456-7890'),
('María', 'Gómez', 'Avenida 456', 'maria@example.com', '987-654-3210'),
('Carlos', 'López', 'Carrera 789', 'carlos@example.com', '456-789-0123'),
('Laura', 'Martínez', 'Plaza 789', 'laura@example.com', '789-012-3456'),
('Pedro', 'Sánchez', 'Calle 456', 'pedro@example.com', '012-345-6789');

-- PROVEEDORES
INSERT INTO proveedores (nombre, telefono, descripcion) VALUES
('ACME', '682659477', 'Telas importadas he hilos'),
('Suministros Vanguardia', '926548733', 'Tornilleria y ferreteria'),
('Innova', '957487611', 'Patas'),
('Avanza Global', '978574776', 'Cinchas'),
('Excelencia', '678479523', 'Guata y cojines'),
('Maderas Moral', '759845221', 'Madera');

-- MATERIALES
INSERT INTO materiales (nombre, descripcion, cantidad, id_proveedor, ref_material_prov, categoria, unidad_medida)VALUES
('Tornillos', 'Tornillos madera 7cm', 3, 2, 456, 'ferreteria', 'caja'),
('Guata', 'Guata blanca 50cm ancho', 300, 5, 357, 'textil', ''),
('Hilo', 'Hilo costura', 25, 1, 003, 'textil', 'bobina'),
('Tela', 'Rivera beige', 30, 1, 757, 'textil', 'm2'),
('Tela', 'Rivera negro', 30, 1, 048, 'textil', 'm2'),
('Madera', 'Madera pino esqueleto', 50, 6, 112, 'madera', 'ml'),
('Cojines', 'Cojines sofas', 35, 5, 654, 'textil', 'unidad'),
('Cinchas', 'Cinchas duras', 10, 4, 1795, 'ferreteria', 'ml'),
('Cinchas', 'Cinchas blandas', 7, 4, 548, 'ferreteria', 'ml'),
('Patas', 'Patas de madera negras básicas', 24, 3, 468, 'patas', 'unidad'),
('Patas', 'Patas acero inox rectas', 12, 3, 489, 'patas', 'unidad');

-- SOFAS
INSERT INTO sofas (nombre, descripcion, patas, medida_cojin, precio) 
VALUES
    ('Aithara', 'Sofá de estilo clásico con tapicería de cuero', 4, 50.00, 500.00),
    ('Lucia', 'Chaise longue izquierdo', 4, 45.00, 700.00),
    ('Luna', 'Chaise longue derecho', 4, 48.00, 600.00),
    ('Sevilla', 'Simple 2 y 3 plazas', 6, 55.00, 800.00),
    ('Iris', 'Respaldo reclinable en 2 y 3 plazas', 4, 60.00, 900.00);
    
INSERT INTO estados(nombre) VALUES
('Pendiente'),
('Procesando'),
('Finalizado');

-- Sofá Aithara
INSERT INTO sofa_materiales (id_sofa, id_material, cantidad_utilizada)
VALUES
    (1, 1, 20), -- Tornillos
    (1, 2, 5),  -- Guata
    (1, 3, 2),  -- Hilo
     (1, 4, 14), -- Tela 
    (1, 6, 5),-- Madera
    (1, 7, 5),  -- Cojines
    (1, 8, 5),-- Cinchas
    (1, 11, 4); -- Patas

-- Sofá Lucía
INSERT INTO sofa_materiales (id_sofa, id_material, cantidad_utilizada)
VALUES
    (2, 1, 25), -- Tornillos
    (2, 2, 8),  -- Guata
    (2, 3, 3),  -- Hilo
     (2, 5, 14), -- Tela 
    (2, 6, 6),-- Madera
    (2, 7, 6),  -- Cojines
    (2, 8, 12),-- Cinchas
    (2, 11, 4); -- Patas

-- Sofá Luna
INSERT INTO sofa_materiales (id_sofa, id_material, cantidad_utilizada)
VALUES
    (3, 1, 22), -- Tornillos
    (3, 2, 7),  -- Guata
    (3, 3, 2),  -- Hilo
     (3, 5, 14), -- Tela 
    (3, 6, 5),-- Madera
    (3, 7, 6),  -- Cojines
    (3, 8, 11),-- Cinchas
    (3, 11, 4); -- Patas

-- Sofá Sevilla
INSERT INTO sofa_materiales (id_sofa, id_material, cantidad_utilizada)
VALUES
    (4, 1, 28), -- Tornillos
    (4, 2, 10), -- Guata
    (4, 3, 3),  -- Hilo
    (4, 4, 14), -- Tela 
    (4, 6, 7),-- Madera
    (4, 7, 7),  -- Cojines
    (4, 8, 6),-- Cinchas
    (4, 11, 6); -- Patas

-- Sofá Iris
INSERT INTO sofa_materiales (id_sofa, id_material, cantidad_utilizada)
VALUES
    (5, 1, 30), -- Tornillos
    (5, 2, 12), -- Guata
    (5, 3, 4),  -- Hilo
    (5, 4, 16), -- Tela 
    (5, 6, 8),-- Madera
    (5, 7, 8),  -- Cojines
    (5, 8, 9),-- Cinchas
    (5, 11, 4); -- Patas
