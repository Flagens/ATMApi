-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 09 Sty 2023, 08:42
-- Wersja serwera: 10.4.21-MariaDB
-- Wersja PHP: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `db`
--
CREATE DATABASE IF NOT EXISTS `db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `db`;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `account`
--

CREATE TABLE `account` (
  `id` int(56) NOT NULL,
  `name` varchar(56) NOT NULL,
  `surname` varchar(56) NOT NULL,
  `money` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `account`
--

INSERT INTO `account` (`id`, `name`, `surname`, `money`) VALUES
(1, 'Mateusz', 'Nowakowski', '5.00'),
(2, 'Piotr', 'Kwiecien', '3051.55'),
(3, 'Antoni', 'Cichy', '403.15');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `blik`
--

CREATE TABLE `blik` (
  `code` varchar(56) NOT NULL,
  `expiration_date` date NOT NULL,
  `creation_date` date NOT NULL,
  `account_id` int(56) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `blik`
--

INSERT INTO `blik` (`code`, `expiration_date`, `creation_date`, `account_id`) VALUES
('123456', '2022-12-07', '2022-12-28', 3),
('135434', '2022-12-09', '2022-12-10', 1),
('198765', '2022-12-30', '2022-12-08', 1),
('234567', '2022-12-13', '2022-12-10', 2);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `blik_transaction`
--

CREATE TABLE `blik_transaction` (
  `id` int(11) NOT NULL,
  `blik_code` varchar(56) NOT NULL,
  `verified` tinyint(1) NOT NULL,
  `executed` tinyint(1) NOT NULL,
  `amount` decimal(10,0) NOT NULL,
  `date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `blik_transaction`
--

INSERT INTO `blik_transaction` (`id`, `blik_code`, `verified`, `executed`, `amount`, `date`) VALUES
(1953229942, '234567', 0, 0, '12', '2022-12-11 11:03:43');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`);

--
-- Indeksy dla tabeli `blik`
--
ALTER TABLE `blik`
  ADD PRIMARY KEY (`code`),
  ADD KEY `account_id` (`account_id`);

--
-- Indeksy dla tabeli `blik_transaction`
--
ALTER TABLE `blik_transaction`
  ADD PRIMARY KEY (`id`),
  ADD KEY `blik_code` (`blik_code`);

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `blik`
--
ALTER TABLE `blik`
  ADD CONSTRAINT `blik_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `blik_transaction`
--
ALTER TABLE `blik_transaction`
  ADD CONSTRAINT `blik_transaction_ibfk_1` FOREIGN KEY (`blik_code`) REFERENCES `blik` (`code`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
