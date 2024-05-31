-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sofa_bbdd
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Database: sofa_bbdd
CREATE DATABASE IF NOT EXISTS sofa_bbdd;
use sofa_bbdd;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `id_cliente` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `apellidos` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `direccion` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `telefono` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'Juan','Pérez','Calle 123','juan@example.com','123-456-7890'),(2,'María','Gómez','Avenida 456','maria@example.com','987-654-3210'),(3,'Carlos','López','Carrera 789','carlos@example.com','456-789-0123'),(4,'Laura','Martínez','Plaza 789','laura@example.com','789-012-3456'),(5,'Pedro','Sánchez','Calle 456','pedro@example.com','012-345-6789'),(7,'Gloria','Torres','Madrid 1254','gtorres@mail.com','661665664');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departamentos`
--

DROP TABLE IF EXISTS `departamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `departamentos` (
  `id_depto` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id_depto`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departamentos`
--

LOCK TABLES `departamentos` WRITE;
/*!40000 ALTER TABLE `departamentos` DISABLE KEYS */;
INSERT INTO `departamentos` VALUES (1,'carpinteria'),(2,'costura'),(3,'tapizado'),(4,'enfundado');
/*!40000 ALTER TABLE `departamentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_pedido`
--

DROP TABLE IF EXISTS `detalle_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_pedido` (
  `id_deped` int NOT NULL AUTO_INCREMENT,
  `id_pedido` int DEFAULT NULL,
  `id_sofa` int DEFAULT NULL,
  `cantidad` int DEFAULT NULL,
  `plazas` int DEFAULT NULL,
  `dens_cojin` decimal(10,2) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `precio` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_deped`),
  KEY `id_sofa9` (`id_sofa`),
  KEY `id_pedido9` (`id_pedido`),
  CONSTRAINT `id_pedido9` FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id_pedido`),
  CONSTRAINT `id_sofa9` FOREIGN KEY (`id_sofa`) REFERENCES `sofas` (`id_sofa`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_pedido`
--

LOCK TABLES `detalle_pedido` WRITE;
/*!40000 ALTER TABLE `detalle_pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleados`
--

DROP TABLE IF EXISTS `empleados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleados` (
  `id_empleado` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `apellidos` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `id_depto` int DEFAULT NULL,
  `id_perfil` int DEFAULT NULL,
  `fecha_ingreso` date DEFAULT NULL,
  `fecha_baja` date DEFAULT NULL,
  `estado` varchar(15) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `salario` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_empleado`),
  KEY `id_perfil` (`id_perfil`),
  CONSTRAINT `empleados_ibfk_2` FOREIGN KEY (`id_perfil`) REFERENCES `perfiles` (`id_perfil`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleados`
--

LOCK TABLES `empleados` WRITE;
/*!40000 ALTER TABLE `empleados` DISABLE KEYS */;
INSERT INTO `empleados` VALUES (1,'Juan','Pérez','juan',1,1,'2023-05-10',NULL,'activo',30000.00),(2,'María','García','maria',2,2,'2022-08-20',NULL,'activo',35000.00),(3,'Pedro','Martínez','pedro',3,3,'2024-01-15','2024-04-30','inactivo',28000.00),(4,'Ana','López','ana',1,2,'2023-11-03',NULL,'activo',32000.00),(5,'Carlos','Sánchez','carlos',2,1,'2021-10-05',NULL,'activo',38000.00),(6,'Laura','Gómez','laura',3,3,'2024-02-28',NULL,'activo',27000.00),(7,'Luis','Hernández','luis',1,3,'2022-04-12',NULL,'activo',31000.00),(8,'Sofía','Díaz','sofia',2,2,'2023-09-08',NULL,'activo',33000.00),(9,'Javier','Ruiz','javier',3,1,'2021-12-20',NULL,'activo',36000.00),(10,'Paco','Hidalgo Flores',NULL,NULL,3,'2024-05-17',NULL,'activo',300000.00),(11,'Miguel','Núñez','miguel',4,3,'2024-05-19',NULL,'activo',29000.00),(12,'Lucía','Pérez','lucia',4,3,'2024-05-20',NULL,'activo',31000.00);
/*!40000 ALTER TABLE `empleados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estados`
--

DROP TABLE IF EXISTS `estados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estados` (
  `id_estado` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id_estado`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estados`
--

LOCK TABLES `estados` WRITE;
/*!40000 ALTER TABLE `estados` DISABLE KEYS */;
INSERT INTO `estados` VALUES (1,'Pendiente'),(2,'Procesando'),(3,'Finalizado'),(4,'Cancelado'),(5,'Sin asignar');
/*!40000 ALTER TABLE `estados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material_proveedor`
--

DROP TABLE IF EXISTS `material_proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material_proveedor` (
  `id_mat_prov` int NOT NULL AUTO_INCREMENT,
  `id_proveedor` int DEFAULT NULL,
  `id_material` int DEFAULT NULL,
  PRIMARY KEY (`id_mat_prov`),
  KEY `id_proveedor1` (`id_proveedor`),
  KEY `id_material3` (`id_material`),
  CONSTRAINT `id_material3` FOREIGN KEY (`id_material`) REFERENCES `materiales` (`id_material`),
  CONSTRAINT `id_proveedor1` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedores` (`id_proveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material_proveedor`
--

LOCK TABLES `material_proveedor` WRITE;
/*!40000 ALTER TABLE `material_proveedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `material_proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materiales`
--

DROP TABLE IF EXISTS `materiales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materiales` (
  `id_material` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `descripcion` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `cantidad` decimal(10,2) DEFAULT NULL,
  `id_proveedor` int DEFAULT NULL,
  `ref_material_prov` int DEFAULT NULL,
  `categoria` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `unidad_medida` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id_material`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materiales`
--

LOCK TABLES `materiales` WRITE;
/*!40000 ALTER TABLE `materiales` DISABLE KEYS */;
INSERT INTO `materiales` VALUES (1,'Tornillos','Tornillos madera 7cm',6.00,2,456,'ferreteria','caja'),(2,'Guata','Guata blanca 50cm ancho',276.00,5,357,'textil',''),(3,'Hilo','Hilo costura',18.00,1,100,'textil','bobina'),(4,'Tela','Rivera beige',16.00,1,757,'textil','m2'),(5,'Tela','Rivera negro',2.00,1,48,'textil','m2'),(6,'Madera','Madera pino esqueleto',33.00,6,112,'madera','ml'),(7,'Cojines','Cojines sofas',16.00,5,654,'textil','unidad'),(8,'Cinchas','Cinchas duras',4.00,4,1795,'ferreteria','ml'),(9,'Cinchas','Cinchas blandas',7.00,4,548,'ferreteria','ml'),(10,'Patas','Patas de madera negras básicas',24.00,3,468,'patas','unidad'),(11,'Patas','Patas acero inox rectas',6.00,3,489,'patas','unidad');
/*!40000 ALTER TABLE `materiales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedidos` (
  `id_pedido` int NOT NULL AUTO_INCREMENT,
  `id_cliente` int DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `vendedor` int DEFAULT NULL,
  PRIMARY KEY (`id_pedido`),
  KEY `id_cliente` (`id_cliente`),
  KEY `id_empleado` (`vendedor`),
  CONSTRAINT `id_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`),
  CONSTRAINT `id_empleado` FOREIGN KEY (`vendedor`) REFERENCES `empleados` (`id_empleado`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
INSERT INTO `pedidos` VALUES (15,1,'2024-04-01',1),(16,5,'2024-03-24',1),(17,2,'2024-03-24',1),(18,2,'2024-03-27',1),(19,3,'2024-04-01',1),(20,1,'2024-04-01',1);
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfiles`
--

DROP TABLE IF EXISTS `perfiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `perfiles` (
  `id_perfil` int NOT NULL AUTO_INCREMENT,
  `rol` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id_perfil`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfiles`
--

LOCK TABLES `perfiles` WRITE;
/*!40000 ALTER TABLE `perfiles` DISABLE KEYS */;
INSERT INTO `perfiles` VALUES (1,'administrador'),(2,'comercial'),(3,'operario');
/*!40000 ALTER TABLE `perfiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedores`
--

DROP TABLE IF EXISTS `proveedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proveedores` (
  `id_proveedor` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `telefono` int DEFAULT NULL,
  `descripcion` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id_proveedor`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedores`
--

LOCK TABLES `proveedores` WRITE;
/*!40000 ALTER TABLE `proveedores` DISABLE KEYS */;
INSERT INTO `proveedores` VALUES (1,'ACME',682659477,'Telas importadas he hilos'),(2,'Suministros Vanguardia',926548733,'Tornilleria y ferreteria'),(3,'Innova',957487611,'Patas'),(4,'Avanza Global',978574776,'Cinchas'),(5,'Excelencia',678479523,'Guata y cojines'),(6,'Maderas Moral',759845221,'Madera'),(8,'Tapitel',987654321,'Hilos negros importados');
/*!40000 ALTER TABLE `proveedores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sofa_materiales`
--

DROP TABLE IF EXISTS `sofa_materiales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sofa_materiales` (
  `id_sm` int NOT NULL AUTO_INCREMENT,
  `id_sofa` int DEFAULT NULL,
  `id_material` int DEFAULT NULL,
  `cantidad_utilizada` int DEFAULT NULL,
  PRIMARY KEY (`id_sm`),
  KEY `id_sofa1` (`id_sofa`),
  KEY `id_material1` (`id_material`),
  CONSTRAINT `id_material1` FOREIGN KEY (`id_material`) REFERENCES `materiales` (`id_material`),
  CONSTRAINT `id_sofa1` FOREIGN KEY (`id_sofa`) REFERENCES `sofas` (`id_sofa`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sofa_materiales`
--

LOCK TABLES `sofa_materiales` WRITE;
/*!40000 ALTER TABLE `sofa_materiales` DISABLE KEYS */;
INSERT INTO `sofa_materiales` VALUES (1,1,1,20),(2,1,2,5),(3,1,3,2),(4,1,4,14),(5,1,6,5),(6,1,7,5),(7,1,8,5),(8,1,11,4),(9,2,1,25),(10,2,2,8),(11,2,3,3),(12,2,5,14),(13,2,6,6),(14,2,7,6),(15,2,8,12),(16,2,11,4),(17,3,1,22),(18,3,2,7),(19,3,3,2),(20,3,5,14),(21,3,6,5),(22,3,7,6),(23,3,8,11),(24,3,11,4),(25,4,1,28),(26,4,2,10),(27,4,3,3),(28,4,4,14),(29,4,6,7),(30,4,7,7),(31,4,8,6),(32,4,11,6),(33,5,1,30),(34,5,2,12),(35,5,3,4),(36,5,4,16),(37,5,6,8),(38,5,7,8),(39,5,8,9),(40,5,11,4);
/*!40000 ALTER TABLE `sofa_materiales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sofas`
--

DROP TABLE IF EXISTS `sofas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sofas` (
  `id_sofa` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `descripcion` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `patas` int DEFAULT NULL,
  `medida_cojin` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `precio` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_sofa`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sofas`
--

LOCK TABLES `sofas` WRITE;
/*!40000 ALTER TABLE `sofas` DISABLE KEYS */;
INSERT INTO `sofas` VALUES (1,'Aithara','Sofá de estilo clásico con tapicería de cuero',4,'50.00',500.00),(2,'Lucia','Chaise longue izquierdo',4,'45.00',700.00),(3,'Luna','Chaise longue derecho',4,'48.00',600.00),(4,'Sevilla','Simple 2 y 3 plazas',6,'55.00',800.00),(5,'Iris','Respaldo reclinable en 2 y 3 plazas',4,'60.00',900.00),(6,'Benedetta','sofá Chesterfield',4,'30',1200.00);
/*!40000 ALTER TABLE `sofas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tareas`
--

DROP TABLE IF EXISTS `tareas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tareas` (
  `id_tarea` int NOT NULL AUTO_INCREMENT,
  `id_empleado` int DEFAULT NULL,
  `id_depto` int DEFAULT NULL,
  `id_estado` int DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `id_deped` int NOT NULL,
  PRIMARY KEY (`id_tarea`),
  KEY `id_empleado2` (`id_empleado`),
  KEY `id_depto3` (`id_depto`),
  KEY `id_estado2` (`id_estado`),
  KEY `id_deped2` (`id_deped`),
  CONSTRAINT `id_depto3` FOREIGN KEY (`id_depto`) REFERENCES `departamentos` (`id_depto`),
  CONSTRAINT `id_empleado2` FOREIGN KEY (`id_empleado`) REFERENCES `empleados` (`id_empleado`),
  CONSTRAINT `id_estado2` FOREIGN KEY (`id_estado`) REFERENCES `estados` (`id_estado`),
  CONSTRAINT `tareas_detalle_pedido` FOREIGN KEY (`id_deped`) REFERENCES `detalle_pedido` (`id_deped`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tareas`
--

LOCK TABLES `tareas` WRITE;
/*!40000 ALTER TABLE `tareas` DISABLE KEYS */;
/*!40000 ALTER TABLE `tareas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-27 16:02:52
