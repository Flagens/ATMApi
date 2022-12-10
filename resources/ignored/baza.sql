-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 10, 2022 at 02:31 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db`
--
CREATE DATABASE IF NOT EXISTS `db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `db`;

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
                           `id` int(56) NOT NULL,
                           `name` varchar(56) NOT NULL,
                           `surname` varchar(56) NOT NULL,
                           `money` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Truncate table before insert `account`
--

TRUNCATE TABLE `account`;
--
-- Dumping data for table `account`
--

INSERT INTO `account` (`id`, `name`, `surname`, `money`) VALUES
                                                             (1, 'Mateusz', 'Nowakowski', '5.00'),
                                                             (2, 'Piotr', 'Kwiecien', '3051.55'),
                                                             (3, 'Antoni', 'Cichy', '403.15');

-- --------------------------------------------------------

--
-- Table structure for table `blik`
--

CREATE TABLE `blik` (
                        `code` varchar(56) NOT NULL,
                        `expiration_date` date NOT NULL,
                        `creation_date` date NOT NULL,
                        `account_id` int(56) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Truncate table before insert `blik`
--

TRUNCATE TABLE `blik`;
--
-- Dumping data for table `blik`
--

INSERT INTO `blik` (`code`, `expiration_date`, `creation_date`, `account_id`) VALUES
                                                                                  ('123456', '2022-12-07', '2022-12-28', 3),
                                                                                  ('135434', '2022-12-09', '2022-12-10', 1),
                                                                                  ('198765', '2022-12-30', '2022-12-08', 1),
                                                                                  ('234567', '2022-12-13', '2022-12-10', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
    ADD PRIMARY KEY (`id`),
    ADD KEY `id` (`id`);

--
-- Indexes for table `blik`
--
ALTER TABLE `blik`
    ADD PRIMARY KEY (`code`),
    ADD KEY `account_id` (`account_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `blik`
--
ALTER TABLE `blik`
    ADD CONSTRAINT `blik_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
