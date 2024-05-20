-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2024-05-19 20:17:13.793

CREATE DATABASE sofa_bbdd;
use sofa_bbdd;

-- tables
-- Table: clientes
CREATE TABLE clientes (
    id_cliente int  NOT NULL AUTO_INCREMENT,
    nombre varchar(100)  CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT null,
    apellidos varchar(150)  CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT null,
    direccion varchar(300)  CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT null,
    email varchar(100)  CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT null,
    telefono varchar(20)  CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT null,
    CONSTRAINT clientes_pk PRIMARY KEY (id_cliente)
) AUTO_INCREMENT 8 ENGINE InnoDB CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

-- Table: departamentos
CREATE TABLE departamentos (
    id_depto int  NOT NULL AUTO_INCREMENT,
    nombre varchar(50)  CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT null,
    CONSTRAINT departamentos_pk PRIMARY KEY (id_depto)
) AUTO_INCREMENT 5 ENGINE InnoDB CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

-- Table: detalle_pedido
CREATE TABLE detalle_pedido (
    id_deped int  NOT NULL AUTO_INCREMENT,
    id_pedido int  NULL DEFAULT null,
    id_sofa int  NULL DEFAULT null,
    cantidad int  NULL DEFAULT null,
    plazas int  NULL DEFAULT null,
    dens_cojin decimal(10,2)  NULL DEFAULT null,
    fecha date  NULL DEFAULT null,
    precio decimal(10,2)  NULL DEFAULT null,
    CONSTRAINT detalle_pedido_pk PRIMARY KEY (id_deped)
) AUTO_INCREMENT 6 ENGINE InnoDB CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

CREATE INDEX id_sofa9 ON detalle_pedido (id_sofa);

CREATE INDEX id_pedido9 ON detalle_pedido (id_pedido);

-- Table: empleado_departamento
CREATE TABLE empleado_departamento (
    id_ed int  NOT NULL AUTO_INCREMENT,
    id_empleado int  NULL DEFAULT null,
    id_depto int  NULL DEFAULT null,
    CONSTRAINT empleado_departamento_pk PRIMARY KEY (id_ed)
) ENGINE InnoDB CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

CREATE INDEX id_empleado6 ON empleado_departamento (id_empleado);

CREATE INDEX id_depto6 ON empleado_departamento (id_depto);

-- Table: empleados
CREATE TABLE empleados (
    id_empleado int  NOT NULL AUTO_INCREMENT,
    nombre varchar(100)  CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT null,
    apellidos varchar(100)  CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT null,
    password varchar(100)  CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT null,
    id_depto int  NULL DEFAULT null,
    id_perfil int  NULL DEFAULT null,
    fecha_ingreso date  NULL DEFAULT null,
    fecha_baja date  NULL DEFAULT null,
    estado varchar(15)  NULL DEFAULT null,
    salario decimal(10,2)  NULL DEFAULT null,
    CONSTRAINT empleados_pk PRIMARY KEY (id_empleado)
) AUTO_INCREMENT 11 ENGINE InnoDB CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

CREATE INDEX id_perfil ON empleados (id_perfil);

-- Table: estados
CREATE TABLE estados (
    id_estado int  NOT NULL AUTO_INCREMENT,
    nombre varchar(50)  CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT null,
    CONSTRAINT estados_pk PRIMARY KEY (id_estado)
) AUTO_INCREMENT 5 ENGINE InnoDB CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

-- Table: material_proveedor
CREATE TABLE material_proveedor (
    id_mat_prov int  NOT NULL AUTO_INCREMENT,
    id_proveedor int  NULL DEFAULT null,
    id_material int  NULL DEFAULT null,
    CONSTRAINT material_proveedor_pk PRIMARY KEY (id_mat_prov)
) ENGINE InnoDB CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

CREATE INDEX id_proveedor1 ON material_proveedor (id_proveedor);

CREATE INDEX id_material3 ON material_proveedor (id_material);

-- Table: materiales
CREATE TABLE materiales (
    id_material int  NOT NULL AUTO_INCREMENT,
    nombre varchar(100)  CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT null,
    descripcion varchar(100)  CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT null,
    cantidad decimal(10,2)  NULL DEFAULT null,
    id_proveedor int  NULL DEFAULT null,
    ref_material_prov int  NULL DEFAULT null,
    categoria varchar(100)  CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT null,
    unidad_medida varchar(100)  CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT null,
    CONSTRAINT materiales_pk PRIMARY KEY (id_material)
) AUTO_INCREMENT 12 ENGINE InnoDB CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

-- Table: pedidos
CREATE TABLE pedidos (
    id_pedido int  NOT NULL AUTO_INCREMENT,
    id_cliente int  NULL DEFAULT null,
    fecha date  NULL DEFAULT null,
    vendedor int  NULL DEFAULT null,
    CONSTRAINT pedidos_pk PRIMARY KEY (id_pedido)
) AUTO_INCREMENT 15 ENGINE InnoDB CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

CREATE INDEX id_cliente ON pedidos (id_cliente);

CREATE INDEX id_empleado ON pedidos (vendedor);

-- Table: perfiles
CREATE TABLE perfiles (
    id_perfil int  NOT NULL AUTO_INCREMENT,
    rol varchar(50)  CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT null,
    CONSTRAINT perfiles_pk PRIMARY KEY (id_perfil)
) AUTO_INCREMENT 4 ENGINE InnoDB CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

-- Table: proveedores
CREATE TABLE proveedores (
    id_proveedor int  NOT NULL AUTO_INCREMENT,
    nombre varchar(100)  CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT null,
    telefono int  NULL DEFAULT null,
    descripcion varchar(300)  CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT null,
    CONSTRAINT proveedores_pk PRIMARY KEY (id_proveedor)
) AUTO_INCREMENT 9 ENGINE InnoDB CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

-- Table: sofa_materiales
CREATE TABLE sofa_materiales (
    id_sm int  NOT NULL AUTO_INCREMENT,
    id_sofa int  NULL DEFAULT null,
    id_material int  NULL DEFAULT null,
    cantidad_utilizada int  NULL DEFAULT null,
    CONSTRAINT sofa_materiales_pk PRIMARY KEY (id_sm)
) AUTO_INCREMENT 41 ENGINE InnoDB CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

CREATE INDEX id_sofa1 ON sofa_materiales (id_sofa);

CREATE INDEX id_material1 ON sofa_materiales (id_material);

-- Table: sofas
CREATE TABLE sofas (
    id_sofa int  NOT NULL AUTO_INCREMENT,
    nombre varchar(100)  CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT null,
    descripcion varchar(300)  CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT null,
    patas int  NULL DEFAULT null,
    medida_cojin varchar(100)  CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT null,
    precio decimal(10,2)  NULL DEFAULT null,
    CONSTRAINT sofas_pk PRIMARY KEY (id_sofa)
) AUTO_INCREMENT 7 ENGINE InnoDB CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

-- Table: tareas
CREATE TABLE tareas (
    id_tarea int  NOT NULL AUTO_INCREMENT,
    id_empleado int  NULL DEFAULT null,
    id_depto int  NULL DEFAULT null,
    id_estado int  NULL DEFAULT null,
    fecha date  NULL DEFAULT null,
    id_deped int  NOT NULL,
    CONSTRAINT tareas_pk PRIMARY KEY (id_tarea)
) AUTO_INCREMENT 3 ENGINE InnoDB CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

CREATE INDEX id_empleado2 ON tareas (id_empleado);

CREATE INDEX id_depto3 ON tareas (id_depto);

CREATE INDEX id_estado2 ON tareas (id_estado);

CREATE INDEX id_deped2 ON tareas (id_deped);

-- foreign keys
-- Reference: empleados_ibfk_2 (table: empleados)
ALTER TABLE empleados ADD CONSTRAINT empleados_ibfk_2 FOREIGN KEY empleados_ibfk_2 (id_perfil)
    REFERENCES perfiles (id_perfil);

-- Reference: id_cliente (table: pedidos)
ALTER TABLE pedidos ADD CONSTRAINT id_cliente FOREIGN KEY id_cliente (id_cliente)
    REFERENCES clientes (id_cliente);

-- Reference: id_depto3 (table: tareas)
ALTER TABLE tareas ADD CONSTRAINT id_depto3 FOREIGN KEY id_depto3 (id_depto)
    REFERENCES departamentos (id_depto);

-- Reference: id_depto6 (table: empleado_departamento)
ALTER TABLE empleado_departamento ADD CONSTRAINT id_depto6 FOREIGN KEY id_depto6 (id_depto)
    REFERENCES departamentos (id_depto);

-- Reference: id_empleado (table: pedidos)
ALTER TABLE pedidos ADD CONSTRAINT id_empleado FOREIGN KEY id_empleado (vendedor)
    REFERENCES empleados (id_empleado);

-- Reference: id_empleado2 (table: tareas)
ALTER TABLE tareas ADD CONSTRAINT id_empleado2 FOREIGN KEY id_empleado2 (id_empleado)
    REFERENCES empleados (id_empleado);

-- Reference: id_empleado6 (table: empleado_departamento)
ALTER TABLE empleado_departamento ADD CONSTRAINT id_empleado6 FOREIGN KEY id_empleado6 (id_empleado)
    REFERENCES empleados (id_empleado);

-- Reference: id_estado2 (table: tareas)
ALTER TABLE tareas ADD CONSTRAINT id_estado2 FOREIGN KEY id_estado2 (id_estado)
    REFERENCES estados (id_estado);

-- Reference: id_material1 (table: sofa_materiales)
ALTER TABLE sofa_materiales ADD CONSTRAINT id_material1 FOREIGN KEY id_material1 (id_material)
    REFERENCES materiales (id_material);

-- Reference: id_material3 (table: material_proveedor)
ALTER TABLE material_proveedor ADD CONSTRAINT id_material3 FOREIGN KEY id_material3 (id_material)
    REFERENCES materiales (id_material);

-- Reference: id_pedido9 (table: detalle_pedido)
ALTER TABLE detalle_pedido ADD CONSTRAINT id_pedido9 FOREIGN KEY id_pedido9 (id_pedido)
    REFERENCES pedidos (id_pedido);

-- Reference: id_proveedor1 (table: material_proveedor)
ALTER TABLE material_proveedor ADD CONSTRAINT id_proveedor1 FOREIGN KEY id_proveedor1 (id_proveedor)
    REFERENCES proveedores (id_proveedor);

-- Reference: id_sofa1 (table: sofa_materiales)
ALTER TABLE sofa_materiales ADD CONSTRAINT id_sofa1 FOREIGN KEY id_sofa1 (id_sofa)
    REFERENCES sofas (id_sofa);

-- Reference: id_sofa9 (table: detalle_pedido)
ALTER TABLE detalle_pedido ADD CONSTRAINT id_sofa9 FOREIGN KEY id_sofa9 (id_sofa)
    REFERENCES sofas (id_sofa);

-- Reference: tareas_detalle_pedido (table: tareas)
ALTER TABLE tareas ADD CONSTRAINT tareas_detalle_pedido FOREIGN KEY tareas_detalle_pedido (id_deped)
    REFERENCES detalle_pedido (id_deped);

-- Poblamos la tabla de datos

INSERT INTO `proveedores` VALUES (1,'ACME',682659477,'Telas importadas he hilos'),(2,'Suministros Vanguardia',926548733,'Tornilleria y ferreteria'),(3,'Innova',957487611,'Patas'),(4,'Avanza Global',978574776,'Cinchas'),(5,'Excelencia',678479523,'Guata y cojines'),(6,'Maderas Moral',759845221,'Madera'),(8,'Tapitel',987654321,'Hilos negros importados');

INSERT INTO `perfiles` VALUES (1,'administrador'),(2,'comercial'),(3,'operario');

INSERT INTO `clientes` VALUES (1,'Juan','Pérez','Calle 123','juan@example.com','123-456-7890'),(2,'María','Gómez','Avenida 456','maria@example.com','987-654-3210'),(3,'Carlos','López','Carrera 789','carlos@example.com','456-789-0123'),(4,'Laura','Martínez','Plaza 789','laura@example.com','789-012-3456'),(5,'Pedro','Sánchez','Calle 456','pedro@example.com','012-345-6789'),(7,'Gloria','Torres','Madrid 1254','gtorres@mail.com','661665664');

INSERT INTO `departamentos` VALUES (1,'carpinteria'),(2,'costura'),(3,'tapizado'),(4,'enfundado');

INSERT INTO `empleados` VALUES (1,'Juan','Pérez','juan',1,1,'2023-05-10',NULL,'activo',30000.00),(2,'María','García','maria',2,2,'2022-08-20',NULL,'activo',35000.00),(3,'Pedro','Martínez','pedro',3,3,'2024-01-15','2024-04-30','inactivo',28000.00),(4,'Ana','López','ana',1,2,'2023-11-03',NULL,'activo',32000.00),(5,'Carlos','Sánchez','carlos',2,1,'2021-10-05',NULL,'activo',38000.00),(6,'Laura','Gómez','laura',3,3,'2024-02-28',NULL,'activo',27000.00),(7,'Luis','Hernández','luis',1,3,'2022-04-12',NULL,'activo',31000.00),(8,'Sofía','Díaz','sofia',2,2,'2023-09-08',NULL,'activo',33000.00),(9,'Javier','Ruiz','javier',3,1,'2021-12-20',NULL,'activo',36000.00),(10,'Paco','Hidalgo Flores',NULL,NULL,3,'2024-05-17',NULL,'activo',300000.00);

INSERT INTO `materiales` VALUES (1,'Tornillos','Tornillos madera 7cm',6.00,2,456,'ferreteria','caja'),(2,'Guata','Guata blanca 50cm ancho',276.00,5,357,'textil',''),(3,'Hilo','Hilo costura',18.00,1,100,'textil','bobina'),(4,'Tela','Rivera beige',16.00,1,757,'textil','m2'),(5,'Tela','Rivera negro',2.00,1,48,'textil','m2'),(6,'Madera','Madera pino esqueleto',33.00,6,112,'madera','ml'),(7,'Cojines','Cojines sofas',16.00,5,654,'textil','unidad'),(8,'Cinchas','Cinchas duras',4.00,4,1795,'ferreteria','ml'),(9,'Cinchas','Cinchas blandas',7.00,4,548,'ferreteria','ml'),(10,'Patas','Patas de madera negras básicas',24.00,3,468,'patas','unidad'),(11,'Patas','Patas acero inox rectas',6.00,3,489,'patas','unidad');

INSERT INTO `pedidos` VALUES (1,1,'2024-04-01',1),(2,5,'2024-03-24',5),(3,2,'2024-03-24',2),(4,2,'2024-03-27',2),(5,3,'2024-04-01',3),(6,2,'2024-04-01',1);

INSERT INTO `sofas` VALUES (1,'Aithara','Sofá de estilo clásico con tapicería de cuero',4,'50.00',500.00),(2,'Lucia','Chaise longue izquierdo',4,'45.00',700.00),(3,'Luna','Chaise longue derecho',4,'48.00',600.00),(4,'Sevilla','Simple 2 y 3 plazas',6,'55.00',800.00),(5,'Iris','Respaldo reclinable en 2 y 3 plazas',4,'60.00',900.00),(6,'Benedetta','sofá Chesterfield',4,'30',1200.00);

INSERT INTO `sofa_materiales` VALUES (1,1,1,20),(2,1,2,5),(3,1,3,2),(4,1,4,14),(5,1,6,5),(6,1,7,5),(7,1,8,5),(8,1,11,4),(9,2,1,25),(10,2,2,8),(11,2,3,3),(12,2,5,14),(13,2,6,6),(14,2,7,6),(15,2,8,12),(16,2,11,4),(17,3,1,22),(18,3,2,7),(19,3,3,2),(20,3,5,14),(21,3,6,5),(22,3,7,6),(23,3,8,11),(24,3,11,4),(25,4,1,28),(26,4,2,10),(27,4,3,3),(28,4,4,14),(29,4,6,7),(30,4,7,7),(31,4,8,6),(32,4,11,6),(33,5,1,30),(34,5,2,12),(35,5,3,4),(36,5,4,16),(37,5,6,8),(38,5,7,8),(39,5,8,9),(40,5,11,4);

INSERT INTO `estados` VALUES (0,'Sin asignar'),(1,'Pendiente'),(2,'Procesando'),(3,'Finalizado'),(4,'Cancelado');

INSERT INTO `detalle_pedido` VALUES (1,3,1,1,3,20.00,'2024-04-05',1200.00),(2,4,3,1,3,20.00,'2024-03-23',1200.00),(3,5,2,1,2,15.00,'2024-03-23',1800.00),(4,6,5,1,3,25.00,'2024-03-31',1500.00),(5,4,4,1,3,50.00,'2024-03-23',0.00);

INSERT INTO empleado_departamento (id_empleado, id_depto) VALUES 
(1, 1),(2, 2),(3, 3),(4, 1),(5, 2),(6, 3),(7, 1),(8, 2),(9, 3),(10, 1);

INSERT INTO tareas (id_empleado, id_depto, id_estado, fecha, id_deped) VALUES 
(1, 1, 1, '2024-01-01', 1),(2, 2, 2, '2024-02-01', 2),(3, 3, 3, '2024-03-01', 3),(4, 1, 4, '2024-04-01', 4),(5, 2, 1, '2024-05-01', 5),(6, 3, 2, '2024-06-01', 1),(7, 1, 3, '2024-07-01', 2),(8, 2, 4, '2024-08-01', 3),(9, 3, 1, '2024-09-01', 4),(10, 1, 2, '2024-10-01', 5);

INSERT INTO `sofa_bbdd`.`material_proveedor` (`id_mat_prov`, `id_proveedor`, `id_material`) VALUES ('1', '1', '1');
INSERT INTO `sofa_bbdd`.`material_proveedor` (`id_mat_prov`, `id_proveedor`, `id_material`) VALUES ('2', '2', '2');
INSERT INTO `sofa_bbdd`.`material_proveedor` (`id_mat_prov`, `id_proveedor`, `id_material`) VALUES ('3', '3', '3');
INSERT INTO `sofa_bbdd`.`material_proveedor` (`id_mat_prov`, `id_proveedor`, `id_material`) VALUES ('4', '4', '4');
INSERT INTO `sofa_bbdd`.`material_proveedor` (`id_mat_prov`, `id_proveedor`, `id_material`) VALUES ('5', '5', '5');
INSERT INTO `sofa_bbdd`.`material_proveedor` (`id_mat_prov`, `id_proveedor`, `id_material`) VALUES ('6', '6', '6');


-- End of file.

