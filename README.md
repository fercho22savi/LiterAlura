LiterAlura - Challenge Backend
Estado del Proyecto: 🚀 En Desarrollo / Finalizado
📝 Descripción del Proyecto
LiterAlura es un catálogo de libros interactivo desarrollado en Java utilizando el framework Spring Boot. El objetivo del desafío es realizar búsquedas de libros a través de una API externa (Gutendex), procesar los datos y persistirlos en una base de datos relacional para su posterior consulta y gestión (CRUD).

🛠️ Tecnologías Utilizadas
Java JDK: 17 o superior.
Spring Boot: 3.x.x.
Spring Data JPA: Para el mapeo objeto-relacional.
PostgreSQL: Motor de base de datos.
Jackson: Para el parseo de JSON.
Maven: Gestor de dependencias.

📂 Estructura de Carpetas
(Aquí insertaré la estructura que me pases en el siguiente mensaje)
text
[Esperando tu estructura...]
Use code with caution.

🚀 Funcionalidades
Búsqueda de libros por título: Consulta a la API de Gutendex.
Persistencia: Almacenamiento de libros y autores en PostgreSQL.
Listar libros registrados: Consulta a la base de datos local.
Listar autores registrados: Visualización de escritores almacenados.
Filtros específicos: Búsqueda de autores vivos en un año determinado o libros por idioma.

⚙️ Configuración del Entorno
Para ejecutar este proyecto, deberás configurar tu archivo application.properties:
properties

spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
