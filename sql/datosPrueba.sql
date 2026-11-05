/*Se ejecuta una sola vez, despues de creaTabla.sql*/
USE foodorder;

/*Usuarios ficticios de prueba, uno por rol. Claves en el README*/
INSERT INTO usuario (nombre, correo, contrasena, rol) VALUES
('Cliente Prueba', 'cliente@foodorder.test', '$2a$10$/p5uqZKw7vV5nrevu3egIeHbOgvjNWxcgI.PxrqQsHrTstXBaX4Y2', 'CLIENTE'),
('Administrador Prueba', 'admin@foodorder.test', '$2a$10$QYL4IV4EVOhPdqjmWwQ26..jbd9UBJ8A0Icz6o8SafKBaGNwU/kAS', 'ADMINISTRADOR'),
('Cocina Prueba', 'cocina@foodorder.test', '$2a$10$vk.PyTEK/rP7VA2nl3XlHuJeL1u.Orjf/tTOIhA6jYeFfwFu0ixhi', 'COCINA');

/*Categorias del menu*/
INSERT INTO categoria (nombre, descripcion) VALUES
('Platos fuertes', 'Comidas principales del restaurante'),
('Bebidas', 'Refrescos naturales y bebidas calientes'),
('Postres', 'Opciones dulces para cerrar la comida');

/*Productos del menu*/
INSERT INTO producto (nombre, descripcion, precio, imagen, estado, id_categoria) VALUES
('Casado de pollo', 'Arroz, frijoles, ensalada, platano maduro y pollo a la plancha', 4500.00, NULL, TRUE, 1),
('Arroz con camarones', 'Arroz salteado con camarones y vegetales', 6200.00, NULL, TRUE, 1),
('Refresco de cas', 'Refresco natural en agua', 1500.00, NULL, TRUE, 2),
('Cafe chorreado', 'Cafe costarricense recien chorreado', 1200.00, NULL, TRUE, 2),
('Tres leches', 'Porcion de queque tres leches', 2300.00, NULL, TRUE, 3);
