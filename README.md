## Java Könyvkatalógus program használata:


A program helyes működéséhez, szükséges az alábbi adatbázis:

```
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

CREATE TABLE `books` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `authors` text DEFAULT NULL,
  `publication_year` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('ADMIN','USER','GUEST') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

ALTER TABLE `books`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

ALTER TABLE `books`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;
```

Az adatbázis elkészítése után szúrjunk be egy felhasználót, hogy tudjuk kezelni a programot.

A program elindításakor, a felhasználónév és jelszó megadása után elérhetővé válik a felhasználónak a lenti konzolos menü:

1. Könyv hozzáadása
2. Könyv törlése
3. Könyvek listázása
4. Könyv keresése
5. Mentés fájlba
6. Betöltés fájlból
7. Mentés adatbázisba
8. Kilépés
