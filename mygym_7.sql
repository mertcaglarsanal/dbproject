-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: Jan 08, 2024 at 09:43 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mygym`
--

-- --------------------------------------------------------

--
-- Stand-in structure for view `age_of_20s`
-- (See below for the actual view)
--
CREATE TABLE `age_of_20s` (
`firstname` varchar(20)
,`surname` varchar(20)
,`birthdate` date
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `gold_members`
-- (See below for the actual view)
--
CREATE TABLE `gold_members` (
`CustomerID` int(11)
,`PaymentID` int(11)
);

-- --------------------------------------------------------

--
-- Table structure for table `gymemployee`
--

CREATE TABLE `gymemployee` (
  `FirstName` varchar(50) DEFAULT NULL,
  `SurName` varchar(50) DEFAULT NULL,
  `EmployeeId` int(11) NOT NULL,
  `email` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `gymemployee`
--

INSERT INTO `gymemployee` (`FirstName`, `SurName`, `EmployeeId`, `email`, `password`) VALUES
('admin', 'xd', 123, 'admin', '123'),
('Merve', 'Yalın', 1234, 'merve@gmail.com', '123'),
('Umut', 'Arduç', 1235, 'Umut.Arduc@gmail.com', '123'),
('Mert', 'Koç', 1236, 'Mert.Koc@gmail.com', '123'),
('Selenay', 'Suna', 1237, 'Selenay@gmail.com', '123'),
('Elif', 'Erdem', 1238, 'elif.erdem@gmail.com', '123'),
('Demir', 'Yılmaz', 1239, 'demir@gmail.com', '123'),
('Zeynep', 'Akçayır', 1240, 'zeynep@gmail.com', '123'),
('Mert', 'Dursun', 1241, 'mert@gmail.com', '123'),
('Sıla', 'Ermiş', 1242, 'sıla.ermis@gmail.com', '123'),
('Çağın', 'Alp', 1243, 'cagın.alp@gmail.com', '123');

-- --------------------------------------------------------

--
-- Table structure for table `gymmanager`
--

CREATE TABLE `gymmanager` (
  `FirstName` varchar(50) DEFAULT NULL,
  `Surname` varchar(50) DEFAULT NULL,
  `Password` varchar(20) DEFAULT NULL,
  `EmployeeID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `gymmembership`
--

CREATE TABLE `gymmembership` (
  `CustomerID` int(11) NOT NULL,
  `FirstName` varchar(20) NOT NULL,
  `Surname` varchar(20) NOT NULL,
  `Birthdate` date NOT NULL,
  `PhoneNumber` int(11) NOT NULL,
  `Gender` varchar(1) DEFAULT NULL,
  `JoiningDate` date DEFAULT NULL,
  `ExpireDate` date DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `gymmembership`
--

INSERT INTO `gymmembership` (`CustomerID`, `FirstName`, `Surname`, `Birthdate`, `PhoneNumber`, `Gender`, `JoiningDate`, `ExpireDate`, `email`, `password`) VALUES
(9883, 'eqwe', 'qw', '2002-11-11', 12312312, 'f', '2022-11-11', '2024-11-11', 'emofkw@gmail.com', '123'),
(525451, 'hakan', 'sanal', '1980-10-02', 5434352, 'm', '2024-01-01', '2024-04-01', 'akskasa@gmail.com', '123'),
(16948654, 'Altan', 'Can', '2001-02-10', 85389323, 'm', '2023-08-20', '2024-08-20', 'altan@gmail.com', '123'),
(18443464, 'Doğa', 'Ata', '1999-11-25', 45327323, 'f', '2023-03-18', '2024-03-18', 'doga@gmail.com', '123'),
(23408214, 'Burkay', 'Gürsel', '2002-10-26', 25284050, 'm', '2023-04-15', '2025-04-15', 'burkay@gmail.com', '123123'),
(32440964, 'Buğra', 'sel', '2003-12-23', 45389323, 'm', '2023-01-15', '2024-01-15', 'buğra@gmail.com', '123'),
(32447983, 'Tolga', 'Çevik', '1970-06-12', 55389323, 'm', '2023-03-09', '2024-03-09', 'tolga@gmail.com', '123'),
(32486446, 'Ada', 'Talı', '2004-10-24', 67389323, 'f', '2023-05-15', '2024-05-15', 'ada@gmail.com', '123'),
(42346764, 'Kübra', 'dur', '2004-10-21', 43383323, 'f', '2023-02-11', '2024-02-11', 'kubra@gmail.com', '123'),
(52909764, 'Seda', 'Koca', '2000-03-10', 47689323, 'f', '2023-05-15', '2024-05-15', 'seda@gmail.com', '123'),
(74902313, 'Selen', 'Erdem', '2004-08-20', 39384391, 'f', '2020-12-19', '2024-12-19', 'selen@gmail.com', '123123'),
(78547764, 'Bora', 'Taş', '2002-08-14', 89323566, 'm', '2023-04-13', '2024-05-13', 'bora@gmail.com', '123');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `Amount` int(11) DEFAULT NULL,
  `PaymentID` int(11) NOT NULL,
  `CustomerID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`Amount`, `PaymentID`, `CustomerID`) VALUES
(30000, 1, 23408214),
(30000, 2, 74902313),
(25000, 3, 32440964),
(25000, 4, 42346764),
(30000, 5, 32447983),
(30000, 6, 52909764),
(20000, 7, 18443464),
(20000, 8, 16948654),
(20000, 9, 32486446),
(20000, 10, 78547764);

-- --------------------------------------------------------

--
-- Table structure for table `workoutclass`
--

CREATE TABLE `workoutclass` (
  `ClassId` int(11) NOT NULL,
  `ClassName` varchar(50) DEFAULT NULL,
  `ClassDate` varchar(10) DEFAULT NULL,
  `InstructorID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `workoutclass`
--

INSERT INTO `workoutclass` (`ClassId`, `ClassName`, `ClassDate`, `InstructorID`) VALUES
(1, 'Yoga', 'Pazartesi', 1234),
(2, 'Pilates', 'Salı', 1235),
(3, 'Cardio', 'Çarşamba', 1236),
(4, 'Yüzme', 'Perşembe', 1237),
(5, 'Bisiklet', 'Cuma', 1238),
(6, 'Zumba', 'Pazartesi', 1239),
(7, 'Samba', 'Cumartesi', 1240),
(8, 'Basketbol', 'Cuma', 1241),
(9, 'Voleybol', 'Salı', 1242),
(10, 'Boks', 'Cumartesi', 1243);

-- --------------------------------------------------------

--
-- Structure for view `age_of_20s`
--
DROP TABLE IF EXISTS `age_of_20s`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `age_of_20s`  AS SELECT `gymmembership`.`FirstName` AS `firstname`, `gymmembership`.`Surname` AS `surname`, `gymmembership`.`Birthdate` AS `birthdate` FROM `gymmembership` WHERE `gymmembership`.`Birthdate` > '1994-01-01' ORDER BY `gymmembership`.`FirstName` ASC, `gymmembership`.`Surname` ASC ;

-- --------------------------------------------------------

--
-- Structure for view `gold_members`
--
DROP TABLE IF EXISTS `gold_members`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `gold_members`  AS SELECT `payment`.`CustomerID` AS `CustomerID`, `payment`.`PaymentID` AS `PaymentID` FROM `payment` WHERE `payment`.`Amount` >= 30000 ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `gymemployee`
--
ALTER TABLE `gymemployee`
  ADD PRIMARY KEY (`EmployeeId`);

--
-- Indexes for table `gymmanager`
--
ALTER TABLE `gymmanager`
  ADD KEY `EmployeeID` (`EmployeeID`);

--
-- Indexes for table `gymmembership`
--
ALTER TABLE `gymmembership`
  ADD PRIMARY KEY (`CustomerID`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`PaymentID`),
  ADD KEY `CustomerID` (`CustomerID`);

--
-- Indexes for table `workoutclass`
--
ALTER TABLE `workoutclass`
  ADD PRIMARY KEY (`ClassId`),
  ADD KEY `InstructorID` (`InstructorID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `PaymentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `workoutclass`
--
ALTER TABLE `workoutclass`
  MODIFY `ClassId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `gymmanager`
--
ALTER TABLE `gymmanager`
  ADD CONSTRAINT `gymmanager_ibfk_1` FOREIGN KEY (`EmployeeID`) REFERENCES `gymemployee` (`EmployeeId`);

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `gymmembership` (`CustomerID`);

--
-- Constraints for table `workoutclass`
--
ALTER TABLE `workoutclass`
  ADD CONSTRAINT `workoutclass_ibfk_1` FOREIGN KEY (`InstructorID`) REFERENCES `gymemployee` (`EmployeeId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
