/*Se crea la base de datos del proyecto*/
CREATE SCHEMA foodorder;

/*Se crea el usuario del proyecto*/
CREATE USER 'usuario_foodorder'@'localhost'
IDENTIFIED BY 'Foodorder_Clave1.';

/*Se asignan los permisos al usuario*/
GRANT ALL PRIVILEGES ON foodorder.*
TO 'usuario_foodorder'@'localhost';

/*Se utiliza la base de datos del proyecto*/
USE foodorder;

/*Se crea la tabla de usuarios*/
CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    rol VARCHAR(20) NOT NULL
);

/*Se crea la tabla de categorías*/
CREATE TABLE categoria (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(255)
);

/*Se crea la tabla de productos*/
CREATE TABLE producto (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255),
    precio DECIMAL(10,2) NOT NULL,
    imagen VARCHAR(1024),
    estado BOOLEAN NOT NULL DEFAULT TRUE,
    id_categoria INT NOT NULL,
    FOREIGN KEY (id_categoria)
        REFERENCES categoria(id_categoria)
);

/*Se crea la tabla de pedidos*/
CREATE TABLE pedido (
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10,2) NOT NULL,
    estado VARCHAR(30) NOT NULL,
    id_usuario INT NOT NULL,
    FOREIGN KEY (id_usuario)
        REFERENCES usuario(id_usuario)
);

/*Se crea la tabla del detalle de los pedidos*/
CREATE TABLE detalle_pedido (
    id_detalle INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_pedido)
        REFERENCES pedido(id_pedido),
    FOREIGN KEY (id_producto)
        REFERENCES producto(id_producto)
);