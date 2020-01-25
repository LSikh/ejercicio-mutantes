# Mutantes - Lautaro Sikh

### Tecnologías usadas

* Java 1.8
* Springboot 2.1.2
* Hibernate
* Gradle
* PostgreSQL
* Heroku
* Flyway 
* JUnit

### Ejecución

La API está desplegada en Heroku. Se pueden enviar peticiones a la URL

```sh
https://ejercicio-mutantes.herokuapp.com/
```
Se implementaron 2 servicios según las especificaciones del enunciado.

```sh
POST https://ejercicio-mutantes.herokuapp.com/mutant
GET https://ejercicio-mutantes.herokuapp.com/stats
```

Para el caso del primer servicio, envie un una petición POST con el siguiente payload en el body:
```sh
{"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]}
```
La respuesta fue `HTTP 200 OK`. La misma quedó persistida en la base de datos.

Luego otra petición POST con el siguiente body
```sh
{"dna":["ATGCGA","CAGTAC","TTATGT","AGATGG","CCCCTA","TCACTG"]}
```
La respuesta fue `HTTP 403 FORBIDDEN`. La misma quedó persistida en la base de datos.

Luego, una petición GET es enviada al segundo servicio y se obtienen el siguiente JSON de respuesta
```sh
{
    "count_human_dna": 1.0,
    "count_mutant_dna": 1.0,
    "ratio": 1.0
}
```
### Base de Datos
La base de datos usada es una instancia de PostgreSQL. Dentro de la carpeta `src/java/resources/db/migrations` se encuentra el migration utilizado para crear la tabla correspondiente al Nivel 3 del enunciado. El mismo se ejecutó con flyway.

### Testing y Coverage

Se implementaron 13 tests unitarios con JUnit y Mockito. Todos fueron correctos y el coverage de los archivos ubicados en `src/java/main` es del 97%, según el plugin de Jacoco.

### Repositorio

```sh
https://github.com/LSikh/ejercicio-mutantes
```

### Nota

Las aplicaciones de Heroku, suelen desactivarse luego de 30 o 40 minutos de inactividad. Es posible que el primer request que se envíe, tarde en volver la respuesta debido a que se está inicializando la aplicacion.