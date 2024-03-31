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

--
-- Table structure for table `cliente_empleado`
--

DROP TABLE IF EXISTS `cliente_empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente_empleado` (
  `id_clemp` int NOT NULL AUTO_INCREMENT,
  `id_empleado` int DEFAULT NULL,
  `id_cliente` int DEFAULT NULL,
  PRIMARY KEY (`id_clemp`),
  KEY `id_empleado8` (`id_empleado`),
  KEY `id_cliente8` (`id_cliente`),
  CONSTRAINT `id_cliente8` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`),
  CONSTRAINT `id_empleado8` FOREIGN KEY (`id_empleado`) REFERENCES `empleados` (`id_empleado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente_empleado`
--

LOCK TABLES `cliente_empleado` WRITE;
/*!40000 ALTER TABLE `cliente_empleado` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente_empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `id_cliente` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `apellidos` varchar(150) DEFAULT NULL,
  `direccion` varchar(300) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'Juan','Pérez','Calle 123','juan@example.com','123-456-7890'),(2,'María','Gómez','Avenida 456','maria@example.com','987-654-3210'),(3,'Carlos','López','Carrera 789','carlos@example.com','456-789-0123'),(4,'Laura','Martínez','Plaza 789','laura@example.com','789-012-3456'),(5,'Pedro','Sánchez','Calle 456','pedro@example.com','012-345-6789');
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
  `nombre` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_depto`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `id_estado` int DEFAULT NULL,
  PRIMARY KEY (`id_deped`),
  KEY `id_sofa9` (`id_sofa`),
  KEY `id_pedido9` (`id_pedido`),
  KEY `id_estado` (`id_estado`),
  CONSTRAINT `id_estado` FOREIGN KEY (`id_estado`) REFERENCES `estados` (`id_estado`),
  CONSTRAINT `id_pedido9` FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id_pedido`),
  CONSTRAINT `id_sofa9` FOREIGN KEY (`id_sofa`) REFERENCES `sofas` (`id_sofa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_pedido`
--

LOCK TABLES `detalle_pedido` WRITE;
/*!40000 ALTER TABLE `detalle_pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado_departamento`
--

DROP TABLE IF EXISTS `empleado_departamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleado_departamento` (
  `id_ed` int NOT NULL AUTO_INCREMENT,
  `id_empleado` int DEFAULT NULL,
  `id_depto` int DEFAULT NULL,
  PRIMARY KEY (`id_ed`),
  KEY `id_empleado6` (`id_empleado`),
  KEY `id_depto6` (`id_depto`),
  CONSTRAINT `id_depto6` FOREIGN KEY (`id_depto`) REFERENCES `departamentos` (`id_depto`),
  CONSTRAINT `id_empleado6` FOREIGN KEY (`id_empleado`) REFERENCES `empleados` (`id_empleado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado_departamento`
--

LOCK TABLES `empleado_departamento` WRITE;
/*!40000 ALTER TABLE `empleado_departamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `empleado_departamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado_perfil`
--

DROP TABLE IF EXISTS `empleado_perfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleado_perfil` (
  `id_emper` int NOT NULL AUTO_INCREMENT,
  `id_empleado` int DEFAULT NULL,
  `id_perfil` int DEFAULT NULL,
  PRIMARY KEY (`id_emper`),
  KEY `id_empleado7` (`id_empleado`),
  KEY `id_perfil7` (`id_perfil`),
  CONSTRAINT `id_empleado7` FOREIGN KEY (`id_empleado`) REFERENCES `empleados` (`id_empleado`),
  CONSTRAINT `id_perfil7` FOREIGN KEY (`id_perfil`) REFERENCES `perfiles` (`id_perfil`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado_perfil`
--

LOCK TABLES `empleado_perfil` WRITE;
/*!40000 ALTER TABLE `empleado_perfil` DISABLE KEYS */;
/*!40000 ALTER TABLE `empleado_perfil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleados`
--

DROP TABLE IF EXISTS `empleados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleados` (
  `id_empleado` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `apellidos` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `id_depto` int DEFAULT NULL,
  `id_perfil` int DEFAULT NULL,
  `fecha_ingreso` date DEFAULT NULL,
  `fecha_baja` date DEFAULT NULL,
  `estado` int NOT NULL,
  `salario` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_empleado`),
  KEY `id_perfil` (`id_perfil`),
  CONSTRAINT `empleados_ibfk_2` FOREIGN KEY (`id_perfil`) REFERENCES `perfiles` (`id_perfil`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleados`
--

LOCK TABLES `empleados` WRITE;
/*!40000 ALTER TABLE `empleados` DISABLE KEYS */;
INSERT INTO `empleados` VALUES (1,'Juan','Pérez','juan',1,1,'2023-05-10',NULL,1,30000.00),(2,'María','García','maria',2,2,'2022-08-20',NULL,1,35000.00),(3,'Pedro','Martínez','pedro',3,3,'2024-01-15',NULL,0,28000.00),(4,'Ana','López','ana',1,2,'2023-11-03',NULL,1,32000.00),(5,'Carlos','Sánchez','carlos',2,1,'2021-10-05',NULL,1,38000.00),(6,'Laura','Gómez','laura',3,3,'2024-02-28',NULL,1,27000.00),(7,'Luis','Hernández','luis',1,3,'2022-04-12',NULL,1,31000.00),(8,'Sofía','Díaz','sofia',2,2,'2023-09-08',NULL,1,33000.00),(9,'Javier','Ruiz','javier',3,1,'2021-12-20',NULL,1,36000.00);
/*!40000 ALTER TABLE `empleados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado_departamento`
--

DROP TABLE IF EXISTS `estado_departamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estado_departamento` (
  `id_edep` int NOT NULL AUTO_INCREMENT,
  `id_estado` int DEFAULT NULL,
  `id_depto` int DEFAULT NULL,
  PRIMARY KEY (`id_edep`),
  KEY `id_estado9` (`id_estado`),
  KEY `id_depto9` (`id_depto`),
  CONSTRAINT `id_depto9` FOREIGN KEY (`id_depto`) REFERENCES `departamentos` (`id_depto`),
  CONSTRAINT `id_estado9` FOREIGN KEY (`id_estado`) REFERENCES `estados` (`id_estado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_departamento`
--

LOCK TABLES `estado_departamento` WRITE;
/*!40000 ALTER TABLE `estado_departamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `estado_departamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado_pedido`
--

DROP TABLE IF EXISTS `estado_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estado_pedido` (
  `id_ep` int NOT NULL AUTO_INCREMENT,
  `id_pedido` int DEFAULT NULL,
  `id_estado` int DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`id_ep`),
  KEY `id_pedido` (`id_pedido`),
  KEY `id:estado` (`id_estado`),
  CONSTRAINT `id:estado` FOREIGN KEY (`id_estado`) REFERENCES `estados` (`id_estado`),
  CONSTRAINT `id_pedido` FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id_pedido`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_pedido`
--

LOCK TABLES `estado_pedido` WRITE;
/*!40000 ALTER TABLE `estado_pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `estado_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estados`
--

DROP TABLE IF EXISTS `estados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estados` (
  `id_estado` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_estado`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estados`
--

LOCK TABLES `estados` WRITE;
/*!40000 ALTER TABLE `estados` DISABLE KEYS */;
INSERT INTO `estados` VALUES (1,'Pendiente'),(2,'Procesando'),(3,'Finalizado');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `nombre` varchar(100) DEFAULT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `cantidad` decimal(10,2) DEFAULT NULL,
  `id_proveedor` int DEFAULT NULL,
  `ref_material_prov` int DEFAULT NULL,
  `categoria` varchar(100) DEFAULT NULL,
  `unidad_medida` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_material`),
  KEY `id_proveedor` (`id_proveedor`),
  CONSTRAINT `id_proveedor` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedores` (`id_proveedor`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materiales`
--

LOCK TABLES `materiales` WRITE;
/*!40000 ALTER TABLE `materiales` DISABLE KEYS */;
INSERT INTO `materiales` VALUES (1,'Tornillos','Tornillos madera 7cm',3.00,2,456,'ferreteria','caja'),(2,'Guata','Guata blanca 50cm ancho',300.00,5,357,'textil',''),(3,'Hilo','Hilo costura',25.00,1,3,'textil','bobina'),(4,'Tela','Rivera beige',30.00,1,757,'textil','m2'),(5,'Tela','Rivera negro',30.00,1,48,'textil','m2'),(6,'Madera','Madera pino esqueleto',50.00,6,112,'madera','ml'),(7,'Cojines','Cojines sofas',35.00,5,654,'textil','unidad'),(8,'Cinchas','Cinchas duras',10.00,4,1795,'ferreteria','ml'),(9,'Cinchas','Cinchas blandas',7.00,4,548,'ferreteria','ml'),(10,'Patas','Patas de madera negras básicas',24.00,3,468,'patas','unidad'),(11,'Patas','Patas acero inox rectas',12.00,3,489,'patas','unidad');
/*!40000 ALTER TABLE `materiales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido_departamento`
--

DROP TABLE IF EXISTS `pedido_departamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido_departamento` (
  `id_pd` int NOT NULL AUTO_INCREMENT,
  `id_pedido` int DEFAULT NULL,
  `id_depto` int DEFAULT NULL,
  PRIMARY KEY (`id_pd`),
  KEY `id_pedido2` (`id_pedido`),
  KEY `id_depto1` (`id_depto`),
  CONSTRAINT `id_depto1` FOREIGN KEY (`id_depto`) REFERENCES `departamentos` (`id_depto`),
  CONSTRAINT `id_pedido2` FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id_pedido`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido_departamento`
--

LOCK TABLES `pedido_departamento` WRITE;
/*!40000 ALTER TABLE `pedido_departamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedido_departamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido_material`
--

DROP TABLE IF EXISTS `pedido_material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido_material` (
  `id_pm` int NOT NULL AUTO_INCREMENT,
  `id_pedido` int DEFAULT NULL,
  `id_material` int DEFAULT NULL,
  PRIMARY KEY (`id_pm`),
  KEY `id_pedido1` (`id_pedido`),
  KEY `id_material2` (`id_material`),
  CONSTRAINT `id_material2` FOREIGN KEY (`id_material`) REFERENCES `materiales` (`id_material`),
  CONSTRAINT `id_pedido1` FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id_pedido`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido_material`
--

LOCK TABLES `pedido_material` WRITE;
/*!40000 ALTER TABLE `pedido_material` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedido_material` ENABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
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
  `rol` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_perfil`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `nombre` varchar(100) DEFAULT NULL,
  `telefono` int DEFAULT NULL,
  `descripcion` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id_proveedor`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedores`
--

LOCK TABLES `proveedores` WRITE;
/*!40000 ALTER TABLE `proveedores` DISABLE KEYS */;
INSERT INTO `proveedores` VALUES (1,'ACME',682659477,'Telas importadas he hilos'),(2,'Suministros Vanguardia',926548733,'Tornilleria y ferreteria'),(3,'Innova',957487611,'Patas'),(4,'Avanza Global',978574776,'Cinchas'),(5,'Excelencia',678479523,'Guata y cojines'),(6,'Maderas Moral',759845221,'Madera');
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
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `nombre` varchar(100) DEFAULT NULL,
  `descripcion` varchar(300) DEFAULT NULL,
  `patas` int DEFAULT NULL,
  `medida_cojin` int DEFAULT NULL,
  `precio` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_sofa`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sofas`
--

LOCK TABLES `sofas` WRITE;
/*!40000 ALTER TABLE `sofas` DISABLE KEYS */;
INSERT INTO `sofas` VALUES (1,'Aithara','Sofá de estilo clásico con tapicería de cuero',4,50,500.00),(2,'Lucia','Chaise longue izquierdo',4,45,700.00),(3,'Luna','Chaise longue derecho',4,48,600.00),(4,'Sevilla','Simple 2 y 3 plazas',6,55,800.00),(5,'Iris','Respaldo reclinable en 2 y 3 plazas',4,60,900.00);
/*!40000 ALTER TABLE `sofas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarea_departamento`
--

DROP TABLE IF EXISTS `tarea_departamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tarea_departamento` (
  `id_td` int NOT NULL AUTO_INCREMENT,
  `id_depto` int DEFAULT NULL,
  `id_tarea` int DEFAULT NULL,
  PRIMARY KEY (`id_td`),
  KEY `id_depto4` (`id_depto`),
  KEY `id_tarea4` (`id_tarea`),
  CONSTRAINT `id_depto4` FOREIGN KEY (`id_depto`) REFERENCES `departamentos` (`id_depto`),
  CONSTRAINT `id_tarea4` FOREIGN KEY (`id_tarea`) REFERENCES `tareas` (`id_tarea`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarea_departamento`
--

LOCK TABLES `tarea_departamento` WRITE;
/*!40000 ALTER TABLE `tarea_departamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `tarea_departamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarea_empleado`
--

DROP TABLE IF EXISTS `tarea_empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tarea_empleado` (
  `id_te` int NOT NULL AUTO_INCREMENT,
  `id_empleado` int DEFAULT NULL,
  `id_tarea` int DEFAULT NULL,
  PRIMARY KEY (`id_te`),
  KEY `id_empleado5` (`id_empleado`),
  KEY `id_tarea5` (`id_tarea`),
  CONSTRAINT `id_empleado5` FOREIGN KEY (`id_empleado`) REFERENCES `empleados` (`id_empleado`),
  CONSTRAINT `id_tarea5` FOREIGN KEY (`id_tarea`) REFERENCES `tareas` (`id_tarea`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarea_empleado`
--

LOCK TABLES `tarea_empleado` WRITE;
/*!40000 ALTER TABLE `tarea_empleado` DISABLE KEYS */;
/*!40000 ALTER TABLE `tarea_empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tareas`
--

DROP TABLE IF EXISTS `tareas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tareas` (
  `id_tarea` int NOT NULL AUTO_INCREMENT,
  `id_pedido` int DEFAULT NULL,
  `id_empleado` int DEFAULT NULL,
  `id_depto` int DEFAULT NULL,
  `id_estado` int DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`id_tarea`),
  KEY `id_pedido3` (`id_pedido`),
  KEY `id_empleado2` (`id_empleado`),
  KEY `id_depto3` (`id_depto`),
  KEY `id_estado2` (`id_estado`),
  CONSTRAINT `id_depto3` FOREIGN KEY (`id_depto`) REFERENCES `departamentos` (`id_depto`),
  CONSTRAINT `id_empleado2` FOREIGN KEY (`id_empleado`) REFERENCES `empleados` (`id_empleado`),
  CONSTRAINT `id_estado2` FOREIGN KEY (`id_estado`) REFERENCES `estados` (`id_estado`),
  CONSTRAINT `id_pedido3` FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id_pedido`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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

-- Dump completed on 2024-03-31 17:34:37
