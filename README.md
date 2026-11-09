# Salon Booking

Backend application for managing salon bookings, built with Java and
Spring Boot.

## Tech Stack

-   Java 25
-   Spring Boot 4.1.2
-   Spring Data JPA
-   PostgreSQL 17
-   Flyway
-   Docker Compose
-   Testcontainers
-   Maven Wrapper
-   GitHub Actions

## Prerequisites

Install the following tools:

-   JDK 25
-   Docker Desktop
-   Git

Docker must be running when starting PostgreSQL locally or running
integration tests with Testcontainers.

## Getting Started

### 1. Clone the repository

``` bash
git clone https://github.com/dilshat-dev/salon-booking.git
cd salon-booking
```

### 2. Configure environment variables

Create your local environment file:

``` bash
cp .env.example .env
```

Review `.env` and set values appropriate for your local environment. Do
not commit `.env`; it is excluded by `.gitignore`.

The application and Docker Compose use these variables:

Variable        Purpose
  --------------- -----------------------------------------
`DB_NAME`       PostgreSQL database name
`DB_USERNAME`   PostgreSQL username
`DB_PASSWORD`   Local database password
`DB_PORT`       Host port mapped to PostgreSQL
`DB_URL`        JDBC connection URL used by Spring Boot

### 3. Start PostgreSQL

``` bash
docker compose up -d
```

Check the container status:

``` bash
docker compose ps
```

Wait until PostgreSQL reports as healthy.

### 4. Run the application

Export the variables from `.env` into the current shell and start Spring
Boot with the `local` profile:

``` bash
set -a
source .env
set +a
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

The application uses Flyway to apply database migrations. Hibernate
validates the database schema with `ddl-auto: validate`.

By default, the application is available at:

``` text
http://localhost:8080
```

### 5. Check application health

Open:

``` text
http://localhost:8080/actuator/health
```

A healthy application returns a response with `"status": "UP"`.

Application information is available at:

``` text
http://localhost:8080/actuator/info
```

Only the `health` and `info` Actuator endpoints are exposed over the
web.

## Running Tests

Make sure Docker Desktop is running, then execute:

``` bash
./mvnw verify
```

The integration tests use Testcontainers to start PostgreSQL 17. Spring
Boot connects to the test database through `@ServiceConnection`.

To run the tests without the full Maven verification lifecycle:

``` bash
./mvnw test
```

## Database Migrations

Flyway migration scripts are stored in:

``` text
src/main/resources/db/migration/
```

Use versioned migration files (for example, `V2__add_salon_fields.sql`)
for schema changes. Do not edit a migration that has already been
applied to a database; create a new migration instead.

## Continuous Integration

GitHub Actions runs the Maven verification build on pushes and pull
requests.

Workflow file:

``` text
.github/workflows/ci.yml
```

The workflow checks out the repository, configures JDK 25 (Temurin),
caches Maven dependencies, and runs:

``` bash
./mvnw verify
```
