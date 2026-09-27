# API Películas

## 1. Nombre del proyecto

**API Películas**

API REST desarrollada con **Java y Spring Boot** para gestionar información de películas mediante operaciones CRUD, utilizando MySQL como base de datos e integrando la API externa TVmaze para consultar información de series.

---

## 2. Descripción del proyecto

El proyecto consiste en una API REST que permite crear, consultar, actualizar y eliminar películas almacenadas en una base de datos MySQL.

Además, la aplicación tiene una integración con **TVmaze**, que permite buscar información de series mediante un servicio externo.

También se implementaron elementos de observabilidad utilizando **Spring Boot Actuator**, métricas personalizadas, logs y trazabilidad mediante un identificador de solicitud.

---

## 3. Tecnologías utilizadas

* Java 17
* Spring Boot 4.1.1
* Spring Web
* Spring Data JPA
* MySQL
* Maven
* Spring Boot Actuator
* Micrometer
* Prometheus
* TVmaze API
* Git y GitHub
* Visual Studio Code

---

## 4. Estructura principal del proyecto

El proyecto se encuentra organizado de la siguiente manera:

```text
src/
└── main/
    ├── java/
    │   └── com.example.api_peliculas/
    │       ├── controller/
    │       ├── filter/
    │       ├── model/
    │       ├── repository/
    │       └── service/
    │
    └── resources/
        └── application.properties
```

### Principales paquetes

**model:** contiene las entidades utilizadas por la aplicación.

**repository:** contiene los repositorios para acceder a la base de datos mediante JPA.

**controller:** contiene los endpoints de la API REST.

**service:** contiene la lógica relacionada con la comunicación con servicios externos.

**filter:** contiene el filtro utilizado para generar el identificador de cada solicitud.

---

## 5. Entidades

### Pelicula

La entidad `Pelicula` representa las películas almacenadas en la base de datos.

Sus principales atributos son:

* `id`
* `titulo`
* `genero`
* `anio`

### Director

La entidad `Director` representa los directores asociados a las películas.

La relación entre las entidades se maneja mediante JPA.

---

## 6. Base de datos MySQL

La aplicación utiliza **MySQL** como sistema de gestión de base de datos.

La base de datos utilizada es:

```text
peliculasdb
```

La conexión se configura mediante el archivo:

```text
src/main/resources/application.properties
```

Ejemplo de configuración:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/peliculasdb
spring.datasource.username=root
spring.datasource.password=TU_CONTRASEÑA
```

> **Importante:** no se debe publicar en GitHub la contraseña real de MySQL ni ningún otro dato sensible.

Cada usuario debe configurar sus propias credenciales localmente.

---

## 7. Ejecución del proyecto

### Requisitos

Antes de ejecutar el proyecto se necesita tener instalado:

* Java 17
* MySQL
* Git

### Paso 1. Crear la base de datos

En MySQL:

```sql
CREATE DATABASE peliculasdb;
```

### Paso 2. Configurar MySQL

Modificar el archivo:

```text
src/main/resources/application.properties
```

con las credenciales correspondientes a la instalación local de MySQL.

### Paso 3. Ejecutar la aplicación

En Windows se puede utilizar el Maven Wrapper incluido en el proyecto:

```powershell
.\mvnw.cmd spring-boot:run
```

La aplicación se ejecutará en:

```text
http://localhost:8080
```

---

## 8. Principales endpoints

### Películas

Consultar todas las películas:

```http
GET /peliculas
```

Consultar una película por ID:

```http
GET /peliculas/{id}
```

Crear una película:

```http
POST /peliculas
```

Actualizar una película:

```http
PUT /peliculas/{id}
```

Eliminar una película:

```http
DELETE /peliculas/{id}
```

Buscar películas por género:

```http
GET /peliculas/buscar?genero=Drama
```

---

## 9. Integración con TVmaze

La aplicación utiliza **TVmaze** como API externa para consultar información de series.

Endpoint utilizado:

```http
GET /series/buscar?nombre={nombre}
```

Ejemplo:

```text
http://localhost:8080/series/buscar?nombre=Breaking%20Bad
```

La aplicación realiza la solicitud a TVmaze y devuelve la información obtenida.

También se implementó manejo de errores cuando la serie no es encontrada.

Ejemplo:

```text
http://localhost:8080/series/buscar?nombre=EstaSerieNoExiste999999
```

En este caso la aplicación devuelve un mensaje indicando que no se encontró la serie o que ocurrió un error al consultar TVmaze.

---

## 10. Observabilidad

La aplicación utiliza **Spring Boot Actuator** para consultar información sobre su funcionamiento.

### Estado de la aplicación

```http
GET /actuator/health
```

Permite comprobar si la aplicación se encuentra funcionando correctamente.

### Métricas generales

```http
GET /actuator/metrics
```

Permite consultar las métricas disponibles.

### Métrica personalizada

Se creó la métrica:

```text
tvmaze_busquedas_total
```

Puede consultarse mediante:

```http
GET /actuator/metrics/tvmaze_busquedas_total
```

Esta métrica registra la cantidad de búsquedas realizadas en TVmaze.

### Prometheus

Las métricas se exponen en un formato compatible con Prometheus mediante:

```http
GET /actuator/prometheus
```

No es necesario instalar un servidor completo de Prometheus para utilizar este endpoint en esta actividad.

---

## 11. Logs

La aplicación utiliza logs para registrar diferentes situaciones durante su funcionamiento.

Se utilizan mensajes de tipo:

* `INFO`: para registrar las búsquedas realizadas y las series encontradas.
* `WARN`: cuando una serie no es encontrada.
* `ERROR`: cuando ocurre otro problema durante la consulta.

Estos mensajes pueden observarse en la consola donde se ejecuta la aplicación.

---

## 12. Trazabilidad de solicitudes

Se implementó un filtro denominado `RequestTracingFilter`.

Este filtro genera un identificador único para cada solicitud y lo agrega a la respuesta mediante el encabezado:

```text
X-Request-ID
```

Ejemplo:

```text
X-Request-ID: 62c003bd-f12a-4c05-8576-19552df34b72
```

Esto permite identificar y hacer seguimiento de una solicitud específica.

---

## 13. API externa utilizada

**TVmaze API**

TVmaze proporciona información sobre series de televisión y es utilizada en este proyecto para realizar búsquedas externas.

La aplicación consulta el servicio de TVmaze desde el componente `TvMazeService`.

---

## 14. Archivos principales

Entre los archivos importantes del proyecto se encuentran:

```text
pom.xml
src/main/resources/application.properties
```

Además de las clases Java ubicadas en los paquetes:

```text
controller
filter
model
repository
service
```

---

## 15. Seguridad de la configuración

Las contraseñas, tokens y demás credenciales sensibles **no deben publicarse en GitHub**.

Antes de subir el proyecto al repositorio se debe revisar especialmente:

```text
application.properties
```

para evitar publicar la contraseña real de MySQL.

---

## 16. Autor

**Wilton Bonilla Cano**

Proyecto académico – Ingeniería de Sistemas.
