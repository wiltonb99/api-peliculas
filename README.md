# API Películas

## Descripción

Este proyecto es una API REST desarrollada con Java y Spring Boot para gestionar información de películas.

La aplicación permite consultar películas, buscar películas por género y registrar nuevas películas mediante solicitudes HTTP.

Los datos se almacenan temporalmente en memoria mediante una lista, por lo que no se utiliza MySQL ni una base de datos.

## Tecnologías utilizadas

- Java 17
- Spring Boot 4.1.1
- Maven
- Spring Web
- DTO mediante `record`

## Requisitos

Para ejecutar el proyecto se necesita:

- Java 17 o superior
- Maven
- Visual Studio Code u otro IDE compatible

## Ejecución del proyecto

1. Abrir el proyecto en Visual Studio Code.
2. Abrir una terminal dentro de la carpeta del proyecto.
3. Ejecutar:

```powershell
.\mvnw.cmd spring-boot:run
```