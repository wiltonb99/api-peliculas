# API Películas

## Descripción

Este proyecto consiste en una API REST desarrollada con Java y Spring Boot para gestionar información de películas.

La aplicación permite realizar operaciones CRUD (crear, consultar, actualizar y eliminar películas) y realizar una consulta personalizada para buscar películas por género.

## Contexto seleccionado

El contexto seleccionado es la gestión de películas.

Cada película contiene:

* Título.
* Género.
* Año de lanzamiento.

## Integrante

* Wilton Bonilla Cano

## Tecnologías utilizadas

* Java 17
* Spring Boot
* Maven
* Spring Web
* Spring Data JPA
* API REST
* JSON
* DTO mediante `record`
* Git y GitHub

## Requisitos

Para ejecutar el proyecto se necesita:

* Java 17 o superior.
* Maven.
* Visual Studio Code u otro IDE compatible.

## Ejecución del proyecto

1. Abrir el proyecto en Visual Studio Code.

2. Abrir una terminal dentro de la carpeta del proyecto.

3. Ejecutar el siguiente comando en Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

4. La API estará disponible en:

```text
http://localhost:8080
```

## Principales endpoints

| Método | Endpoint                       | Descripción                 |
| ------ | ------------------------------ | --------------------------- |
| GET    | `/peliculas`                   | Obtener todas las películas |
| GET    | `/peliculas/{id}`              | Obtener una película por ID |
| GET    | `/peliculas/buscar?genero=...` | Buscar películas por género |
| POST   | `/peliculas`                   | Crear una película          |
| PUT    | `/peliculas/{id}`              | Actualizar una película     |
| DELETE | `/peliculas/{id}`              | Eliminar una película       |

## Consulta personalizada

La API permite buscar películas por género mediante:

```text
GET /peliculas/buscar?genero=Ciencia%20ficci%C3%B3n
```

Ejemplo de respuesta:

```json
[
  {
    "id": 1,
    "titulo": "Interestelar Actualizada",
    "genero": "Ciencia ficción",
    "anio": 2014
  }
]
```

## Ejemplo de solicitud POST

Para registrar una nueva película:

```json
{
  "titulo": "Gladiador",
  "genero": "Acción",
  "anio": 2000
}
```

Cuando la película se crea correctamente, la API responde con:

```text
201 Created
```

## Respuestas HTTP

La API utiliza códigos HTTP coherentes con las operaciones realizadas:

* `200 OK`: operación realizada correctamente.
* `201 Created`: película creada correctamente.
* `204 No Content`: película eliminada correctamente.
* `404 Not Found`: película no encontrada.
