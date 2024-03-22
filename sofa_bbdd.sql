create database sofa_BBDD;
use sofa_BBDD;


CREATE TABLE Perfiles (
    id_perfil INT AUTO_INCREMENT PRIMARY KEY,
    rol VARCHAR(50)
);

CREATE TABLE Departamentos(
id_depto INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(50)
);

CREATE TABLE Empleados (
    id_empleado INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    apellidos VARCHAR(100),
    id_depto INT,
    id_perfil INT,
    fecha_ingreso DATE,
    salario DECIMAL(10,2),
    FOREIGN KEY (id_depto) REFERENCES Departamentos(id_depto),
    FOREIGN KEY (id_perfil) REFERENCES Perfiles(id_perfil)
);

CREATE TABLE Sofas (
    id_sofa INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
	descripción VARCHAR(300),
	plazas INT,
	patas INT,
	medida_cojin DECIMAL(10,2),	
	precio DECIMAL(10,2)  
	);

CREATE TABLE Materiales (
    id_material INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    descripcion VARCHAR(100),
    cantidad DECIMAL(10,2),
    id_fabricante INT
);

CREATE TABLE Sofa_materiales (
    id_sofa_material INT AUTO_INCREMENT PRIMARY KEY,
    id_sofa INT,
    id_material INT,
    Cantidad_utilizada INT,
    FOREIGN KEY (id_sofa) REFERENCES Sofas(id_sofa),
    FOREIGN KEY (id_material) REFERENCES Materiales(id_material)
);

CREATE TABLE Sofas_depto (
    id_sofa INT,
    id_depto INT,    
    FOREIGN KEY (id_sofa) REFERENCES Sofas(id_sofa),
    FOREIGN KEY (id_depto) REFERENCES Departamentos(id_depto)
);



CREATE TABLE Proveedores(
id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(100),
telefono INT,
descripción VARCHAR(300)
);
CREATE TABLE Clientes(
	id_cliente INT AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR (100),
	apellidos VARCHAR (150),
	dirección VARCHAR(300),
	email VARCHAR (100),
	teléfono VARCHAR (20)
);

CREATE TABLE Pedidos (
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    cliente INT,
    sofa INT,
    cantidad INT,  
    plazas INT,  
  dens_cojin DECIMAL(10,2),
  fecha DATE,
  precio DECIMAL(10,2),
  vendedor INT,
    FOREIGN KEY (sofa) REFERENCES Sofas(id_sofa),
    FOREIGN KEY (cliente) REFERENCES Clientes(id_cliente),
   FOREIGN KEY (vendedor) REFERENCES Empleados(id_empleado)
);

CREATE TABLE Estado_pedido (
	id_estado INT AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(100)
);

CREATE TABLE Costura (
    id_pedido INT,
    id_estado INT,
    fecha DATE,
    FOREIGN KEY (id_pedido) REFERENCES Pedidos(id_pedido),
    FOREIGN KEY (id_estado) REFERENCES Estado_pedido(id_estado)
);

CREATE TABLE Tapizado (
    id_pedido INT,
    id_estado INT,
    fecha DATE,
	FOREIGN KEY (id_pedido) REFERENCES Pedidos(id_pedido),
    FOREIGN KEY (id_estado) REFERENCES Estado_pedido(id_estado)
);

CREATE TABLE Enfundado (
Id_pedido INT,
    Id_estado INT,
   fecha DATE,
    FOREIGN KEY (id_pedido) REFERENCES Pedidos(id_pedido),
    FOREIGN KEY (id_estado) REFERENCES Estado_pedido(id_estado)
);

CREATE TABLE Carpinteria (
    Id_pedido INT,
    id_estado INT,
    fecha DATE,
    FOREIGN KEY (id_pedido) REFERENCES Pedidos(id_pedido),
    FOREIGN KEY (id_estado) REFERENCES Estado_pedido(id_estado)
);pedidos