# Conflict Tracker

## Descripción del proyecto

Conflict Tracker es una aplicación desarrollada con Spring Boot para la asignatura de FULLSTACK.
La aplicación es una aplicación para gestionar conflictos y mostrarlos.

## Instrucciones para compilar y ejecutar la aplicación

Para poder ejecutar la aplicación es necesario tener instalado Java 21 o superior, Maven 3.9 o superior y PostgreSQL 16 o superior, además de disponer del puerto 8080 libre.

En primer lugar, se debe crear la base de datos en PostgreSQL ejecutando el siguiente comando SQL:

CREATE DATABASE conflict_tracker;

A continuación, se debe configurar la conexión a la base de datos editando el archivo src/main/resources/application.yml con las credenciales correspondientes.

Es importante asegurarse de que el servidor de PostgreSQL esté en ejecución antes de iniciar la aplicación si no no funcionara.

Una vez configurado todo, desde la raíz del proyecto donde se encuentra el archivo pom.xml se debe ejecutar el comando:

mvn clean package

Esto generará el archivo .jar dentro de la carpeta target/.

Posteriormente, la aplicación puede ejecutarse con:

mvn spring-boot:run (tambien se puede dentro del ide dandole al play)

O bien ejecutando directamente el JAR generado mediante:

java -jar target/conflict-tracker-0.0.1-SNAPSHOT.jar

Una vez iniciada correctamente, la aplicación estará disponible en:

http://localhost:8080


## Ejemplos de uso de los endpoints (con curl)

Los endpoints principales de la aplicación permiten realizar operaciones CRUD completas sobre conflictos, así como operaciones similares sobre Faction y Event.

Para obtener la lista de todos los conflictos:

curl -X GET http://localhost:8080/conflicts

Para obtener los detalles de un conflicto específico por su identificador:

curl -X GET http://localhost:8080/conflicts/1

Para crear un nuevo conflicto:

curl -X POST http://localhost:8080/conflicts \
-H "Content-Type: application/json" \
-d '{"name":"Conflicto de prueba","startDate":"2025-01-01","status":"ACTIVE","description":"Descripción del conflicto"}'

Para actualizar un conflicto existente:

curl -X PUT http://localhost:8080/conflicts/1 \
-H "Content-Type: application/json" \
-d '{"name":"Conflicto actualizado","startDate":"2025-01-01","status":"FROZEN","description":"Descripción modificada"}'

Para eliminar un conflicto:

curl -X DELETE http://localhost:8080/conflicts/1

Para filtrar conflictos por estado:

curl -X GET "http://localhost:8080/conflicts?status=ACTIVE"

Para obtener todos los conflictos en los que participa un país identificado por su código:

curl -X GET http://localhost:8080/countries/ESP/conflicts

La aplicación también implementa operaciones CRUD similares para las entidades Faction y Event, siguiendo la misma estructura de endpoints:

GET /factions  
GET /factions/{id}  
POST /factions  
PUT /factions/{id}  
DELETE /factions/{id}

GET /events  
GET /events/{id}  
POST /events  
PUT /events/{id}  
DELETE /events/{id}

