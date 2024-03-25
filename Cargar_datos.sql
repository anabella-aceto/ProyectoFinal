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
(1),
(2),
(3);

-- EMPLEADOS
INSERT INTO empleados (nombre, apellidos, id_depto, id_perfil, fecha_ingreso, salario) VALUES
('Juan', 'García', 1, 1, '2024-03-24', 2500.00),
('María', 'López', 2, 2, '2024-03-25', 2800.00),
('Carlos', 'Martínez', 1, 2, '2024-03-26', 3000.00),
('Laura', 'Pérez', 3, 3, '2024-03-27', 2700.00),
('Pedro', 'Sánchez', 2, 1, '2024-03-28', 3200.00);

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
INSERT INTO materiales (nombre, descripcion, cantidad, id_proveedor, ref_material_prov)VALUES
('Tornillos', 'Tornillos madera 7cm', 3, 2, 456),
('Guata', 'Guata blanca 50cm ancho', 300, 5, 357),
('Hilo', 'Hilo costura', 25, 1, 003),
('Tela', 'Rivera beige', 30, 1, 757),
('Tela', 'Rivera negro', 30, 1, 048),
('Madera', 'Madera pino esqueleto', 50, 6, 112),
('Cojines', 'Cojines sofas', 35, 5, 654),
('Cinchas', 'Cinchas duras', 10, 4, 1795),
('Cinchas', 'Cinchas blandas', 7, 4, 548),
('Patas', 'Patas de madera negras básicas', 24, 3, 468),
('Patas', 'Patas acero inox rectas', 12, 3, 489);

-- SOFAS
INSERT INTO sofas (nombre, descripcion, patas, medida_cojin, precio) 
VALUES
    ('Aithara', 'Sofá de estilo clásico con tapicería de cuero', 4, 50.00, 500.00),
    ('Lucia', 'Chaise longue izquierdo', 4, 45.00, 700.00),
    ('Luna', 'Chaise longue derecho', 4, 48.00, 600.00),
    ('Sevilla', 'Simple 2 y 3 plazas', 6, 55.00, 800.00),
    ('Iris', 'Respaldo reclinable en 2 y 3 plazas', 4, 60.00, 900.00);

-- ESTADO GLOBAL PEDIDO
INSERT INTO estado_pedido(nombre)VALUES
('Pendiente'),
('Procesando'),
('Finalizado');

