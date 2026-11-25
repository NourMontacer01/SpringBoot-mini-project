# 🎬 Filmothèque — Spring Boot Mini Project

[![CI](https://github.com/NourMontacer01/SpringBoot-mini-project/actions/workflows/ci.yml/badge.svg)](https://github.com/NourMontacer01/SpringBoot-mini-project/actions/workflows/ci.yml)
![Java](https://img.shields.io/badge/Java-21-ED8B00?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2-6DB33F?logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8-4479A1?logo=mysql&logoColor=white)

A small film library built with **Spring Boot**: manage films and their categories through
**server-rendered pages (Thymeleaf)** and a **REST API** documented with **Swagger / OpenAPI**.

## ✨ Features

- List, add, edit and delete films
- Search films by **title** or filter by **category**
- REST API with proper `404 Film not found` errors
- Interactive API docs with Swagger UI
- Layered architecture: controller → service (interface + implementation) → Spring Data JPA repository

## 🧱 Stack

Java 21 · Spring Boot 3.2 (Web, Data JPA, Thymeleaf) · MySQL · Lombok · springdoc-openapi · JUnit 5, MockMvc and H2 for tests

## 🗂 Project structure

```
projet_filomtheque/src/main/java/com/ala/projet_filomtheque/
├── entities/      # Film, Categorie (JPA entities, one category -> many films)
├── repository/    # Spring Data repositories, title search and ordered query
├── service/       # IServiceFilm / ServiceFilm, IServiceCategorie / ServiceCategorie
├── controller/    # FilmController (Thymeleaf pages), RestFilmController (REST API)
└── exception/     # FilmNotFoundException + @ControllerAdvice returning 404
```

## 🚀 Run it

Requirements: **JDK 21** and a **MySQL** server with a `db_film_app` database
(tables are created automatically).

```bash
cd projet_filomtheque
./mvnw spring-boot:run          # Windows: mvnw.cmd spring-boot:run
```

The MySQL host defaults to `localhost` and can be changed with the `MYSQL_HOST` environment variable
(user `root`, empty password — see `src/main/resources/application.yml`).

| URL | What |
| --- | --- |
| <http://localhost:8080/film/all> | Film list (web UI) |
| <http://localhost:8080/film/new> | Add a film |
| <http://localhost:8080/swagger-ui.html> | Swagger UI |

## 🔌 REST API

| Method | Endpoint | Description |
| --- | --- | --- |
| `GET` | `/api/films` | All films |
| `GET` | `/api/films/{id}` | One film, `404` if it does not exist |
| `POST` | `/api/films/add` | Create a film (`201 Created`) |
| `PUT` | `/api/films/update` | Update a film (body contains its `id`) |
| `DELETE` | `/api/films/{id}` | Delete a film |

```bash
curl -X POST http://localhost:8080/api/films/add \
  -H "Content-Type: application/json" \
  -d '{"titre": "Dune", "description": "Sci-fi", "anneeparution": 2021}'
```

## 🧪 Tests

```bash
cd projet_filomtheque
./mvnw test
```

Tests run on an **in-memory H2 database**, so no MySQL server is needed. They cover every REST
endpoint (including the 404 cases) and the custom repository queries. GitHub Actions runs them on every push.
