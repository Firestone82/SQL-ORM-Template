# SQL-ORM-Template

> **VŠB-TUO** — Teaching template · Database Systems

![Java](https://img.shields.io/badge/Java-17%2B-orange) ![Maven](https://img.shields.io/badge/Build-Maven-blue)

## About

An example ORM project for an auction system, originally authored by Ing. Jan Kožusznik, Ph.D. at VŠB-TUO as a teaching template. Demonstrates object-relational mapping patterns in enterprise Java with support for Microsoft SQL Server and Oracle. Used as a starting point for assignments in database-focused courses.

## Requirements

- Java 17+
- Maven 3.8.2+
- Microsoft SQL Server or Oracle database

## Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/Firestone82/SQL-ORM-Template.git
   cd SQL-ORM-Template
   ```

2. Configure your database connection in `src/main/resources/` (host, port, credentials, and dialect).

3. Build:
   ```bash
   mvn clean package
   ```

4. Run:
   ```bash
   java -jar target/*.jar
   ```

## License

Original template by VŠB-TUO / Ing. Jan Kožusznik, Ph.D.
