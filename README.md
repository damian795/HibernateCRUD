# Projekt CRUD Gier

Ten projekt to prosta aplikacja CRUD używająca Hibernate i MySQL do zarządzania informacjami o grach w bazie danych. Projekt demonstruje podstawowe operacje takie jak tworzenie, odczyt, aktualizacja i usuwanie rekordów z bazy danych.

## Funkcjonalności

- Dodawanie nowych gier do bazy danych
- Pobieranie listy gier
- Aktualizowanie szczegółów istniejących gier
- Usuwanie gier z bazy danych

## Użyte technologie

- Java 22
- Hibernate ORM 5.6.0
- MySQL
- XAMPP (do obsługi bazy MySQL)
- IntelliJ IDEA

## Instrukcja instalacji

### Wymagania

Przed uruchomieniem projektu upewnij się, że masz zainstalowane:

- Java Development Kit (JDK) 22
- MySQL (z XAMPP lub standalone MySQL server)
- IntelliJ IDEA (lub inny edytor IDE)
- Maven (do zarządzania zależnościami)

### Konfiguracja bazy danych

1. Uruchom MySQL za pomocą XAMPP lub innego serwera MySQL.
2. Stwórz nową bazę danych o nazwie `gamesdb`:
   ```sql
   CREATE DATABASE gamesdb;
