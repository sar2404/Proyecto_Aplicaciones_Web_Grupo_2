# FoodOrder
Proyecto del curso de desarrollo de aplicaciones web.
## Descripción

FoodOrder plantea como propuesta la realización de un sistema web orientado a la gestión y realización de pedidos para un restaurante.
El proyecto busca digitalizar el proceso de consulta del menú, selección de productos, generación de pedidos y seguimiento de las órdenes mediante una plataforma web.
El sistema estará dirigido principalmente a tres tipos de usuarios: clientes, administradores y encargados de cocina.

## Objetivo

Diseñar y desarrollar FoodOrder, un sistema web de pedidos para restaurante que permita gestionar el menú, registrar pedidos y consultar su estado mediante una plataforma centralizada. 

## Integrantes

- Santiago Alfaro Rodríguez
- Sofía Camacho Farfán
- Jennifer Calderón Cordero
- Esteban Bernardo Padilla Monge

## Tecnologías previstas

De acuerdo con los requerimientos del proyecto y los contenidos desarrollados en el curso, se espera utilizar:

- Java
- Spring Boot
- HTML5
- CSS
- Thymeleaf
- Bootstrap
- Hibernate / JPA
- MySQL
- Git
- GitHub

Durante el desarrollo podrán incorporarse otras tecnologías trabajadas en el curso según las necesidades del proyecto.

## Estado actual

El proyecto cuenta con una base ejecutable en Spring Boot.

Se ha implementado:

- Configuración de Maven con Java 21.
- Conexión a MySQL.
- Script de creación de la base de datos, usuario y tablas.
- Entidades Categoria, Producto, Usuario, Pedido y DetallePedido.
- Relaciones entre las entidades mediante JPA.
- Repositorios para las cinco entidades.
- Controlador de la página inicial.
- Plantilla con fragmentos de encabezado, pie de página y Bootstrap.
- Archivo messages.properties para los textos de pantalla.

- Servicios, controladores y vistas Thymeleaf con Bootstrap para productos, categorias, usuarios, carrito y pedidos.
- Inicio y cierre de sesion mediante HttpSession, con contrasenas cifradas.
- Script sql/datosPrueba.sql con usuarios, categorias y productos de prueba.

Se verificó la compilación, el arranque, la conexión a MySQL y la visualización de la página inicial.

### Historias de usuario

- Implementadas: HU01, HU02, HU03, HU04, HU07, HU09, HU10, HU11, HU12, HU13, HU15, HU16, HU17, HU18, HU19 y HU20.
- Pendientes: HU05, HU06 y HU08.
- Parcial: HU14. El menu cambia segun el rol, pero la restriccion de rutas por rol queda pendiente.

Las imagenes de los productos se registran por URL. La fecha del pedido se genera automaticamente en MySQL.

## Ejecución

### Requisitos

- JDK 21.
- MySQL Server 8 y MySQL Workbench.
- Apache NetBeans con soporte para proyectos Maven.
- Conexión a internet para descargar las dependencias durante la primera compilación.

### Configuración de la base de datos

1. Iniciar MySQL Server.
2. Abrir MySQL Workbench con una conexión administradora.
3. Abrir el archivo sql/creaTabla.sql ubicado en la raíz del repositorio.
4. Ejecutar el script en una instalación donde todavía no existan la base foodorder ni el usuario usuario_foodorder.

El script crea la base de datos foodorder, el usuario usuario_foodorder y las cinco tablas del proyecto.

Si la base y el usuario ya existen, no ejecutar nuevamente todo el script.

Despues, ejecutar una sola vez el archivo sql/datosPrueba.sql para cargar los datos de prueba.

### Usuarios de prueba

Son usuarios ficticios creados por sql/datosPrueba.sql:

- Cliente: cliente@foodorder.test / Cliente_Prueba1
- Administrador: admin@foodorder.test / Admin_Prueba1
- Cocina: cocina@foodorder.test / Cocina_Prueba1

### Configuración de la aplicación

El archivo foodorder/src/main/resources/application.properties contiene la conexión local:

- Servidor: localhost.
- Puerto de MySQL: 3306.
- Base de datos: foodorder.
- Usuario: usuario_foodorder.
- Contraseña: la definida en sql/creaTabla.sql.

### Arranque desde NetBeans

1. Abrir el proyecto ubicado en la carpeta foodorder.
2. Seleccionar JDK 21 como plataforma Java.
3. Mantener MySQL Server encendido.
4. Ejecutar Clean and Build y comprobar que termine con BUILD SUCCESS.
5. Ejecutar Run.
6. Esperar el mensaje Started FoodorderApplication.
7. Abrir http://localhost/ en el navegador.

La aplicación utiliza el puerto 80, que debe estar disponible.

La pagina inicial muestra el menu con los productos disponibles.

## Acuerdo preliminar de trabajo por ramas

Para mantener un desarrollo organizado y permitir identificar los aportes realizados por cada integrante, se establece inicialmente el siguiente flujo de trabajo:

- `main`: será la rama principal y contendrá las versiones estables e integradas del proyecto.
- `feature/nombre-funcionalidad`: se utilizará para desarrollar nuevas funcionalidades.
- `fix/nombre-correccion`: se utilizará para realizar correcciones específicas.

Ejemplos de nombres de ramas que podrían utilizarse durante el desarrollo:

- `feature/autenticacion-roles`
- `feature/menu-productos`
- `feature/carrito-compras`

El objetivo de esta sección es que cada integrante trabajará en una rama asociada a la funcionalidad o tarea que tenga asignada.
Cuando una funcionalidad esté lista, se realizará un Pull Request hacia `main`. Antes de integrar los cambios, al menos otro integrante del equipo deberá revisarlos.
Los conflictos u observaciones encontrados durante la revisión deberán resolverse antes de realizar la integración a la rama principal.
Los commits deberán ser descriptivos y representar cambios concretos realizados durante el desarrollo.
Este acuerdo es preliminar y podrá ajustarse conforme avance el proyecto.