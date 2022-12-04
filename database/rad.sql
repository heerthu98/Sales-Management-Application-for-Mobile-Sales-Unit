-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Aug 01, 2021 at 06:25 PM
-- Server version: 8.0.21
-- PHP Version: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sms2`
--

-- --------------------------------------------------------

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
CREATE TABLE IF NOT EXISTS `manager` (
  `username` varchar(10) NOT NULL,
  `password` varchar(16) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `manager`
--

INSERT INTO `manager` (`username`, `password`) VALUES
('Manager', '123');

-- --------------------------------------------------------

--
-- Table structure for table `personalstock`
--

DROP TABLE IF EXISTS `personalstock`;
CREATE TABLE IF NOT EXISTS `personalstock` (
  `serialNo` int NOT NULL AUTO_INCREMENT,
  `srID` int NOT NULL,
  `productID` varchar(10) NOT NULL,
  `count` int NOT NULL,
  `stockStatus` enum('in','out') NOT NULL,
  `date` date NOT NULL,
  `subTotal` float NOT NULL,
  PRIMARY KEY (`serialNo`),
  KEY `salesRep` (`srID`),
  KEY `product` (`productID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `personalstock`
--

INSERT INTO `personalstock` (`serialNo`, `srID`, `productID`, `count`, `stockStatus`, `date`, `subTotal`) VALUES
(1, 1234, 'ZMG001', 10, 'out', '2021-08-09', 0),
(3, 1003, 'ZMG003', 6, 'in', '2021-08-09', 0),
(4, 2345, 'ZMG004', 6, 'out', '2021-08-18', 0),
(9, 1234, 'ZMG004', 6, 'in', '2021-08-17', 4),
(10, 2345, 'ZMG003', 6, 'in', '2021-08-17', 4),
(12, 1234, 'zmg002', 3, 'in', '2021-08-17', 600),
(13, 2345, 'zmg006', 3, 'in', '2021-08-24', 4),
(14, 1234, 'zmg002', 3, 'in', '2021-08-17', 7),
(15, 1234, 'zmg001', 3, 'in', '2021-08-03', 0);

-- --------------------------------------------------------

--
-- Table structure for table `productdetails`
--

DROP TABLE IF EXISTS `productdetails`;
CREATE TABLE IF NOT EXISTS `productdetails` (
  `productID` varchar(10) NOT NULL,
  `proName` varchar(20) NOT NULL,
  `proModel` varchar(10) NOT NULL,
  `proColor` varchar(10) NOT NULL,
  `proPrice` float NOT NULL,
  PRIMARY KEY (`productID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `productdetails`
--

INSERT INTO `productdetails` (`productID`, `proName`, `proModel`, `proColor`, `proPrice`) VALUES
('ZMG001', 'Phone', 'Samsung', 'Black', 12000),
('ZMG002', 'Head Phone', 'iPhone', 'White', 5000),
('ZMG003', 'Phone', 'iPhone 6s', 'Blue', 12000),
('ZMG004', 'Charger', 'Huawei', 'Pink', 1500),
('ZMG005', 'Charger', 'iPhone 7pl', 'Ping', 3000),
('zmg006', 'Charger', 'Samsung', 'Blue', 400),
('ZMG007', 'Router', 'Dialog', 'White', 7000),
('ZMG008', 'Router', 'SLT', 'Black', 7500),
('ZMG009', 'Head Phone', 'iPhone', 'Pink', 2000),
('ZMG010', 'Phone', 'Huawei', 'Black', 50000);

-- --------------------------------------------------------

--
-- Table structure for table `salesrep`
--

DROP TABLE IF EXISTS `salesrep`;
CREATE TABLE IF NOT EXISTS `salesrep` (
  `srID` int NOT NULL,
  `srName` varchar(20) NOT NULL,
  `srMobile` varchar(10) NOT NULL,
  `srAddress` varchar(50) NOT NULL,
  `salesArea` varchar(20) NOT NULL,
  `srPassword` varchar(16) NOT NULL,
  PRIMARY KEY (`srID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `salesrep`
--

INSERT INTO `salesrep` (`srID`, `srName`, `srMobile`, `srAddress`, `salesArea`, `srPassword`) VALUES
(1002, 'Abiram', '0775432457', 'Jaffna', 'Pointpetro', 'Abiram98'),
(1003, 'Abdhul', '0761234567', 'Jaffna', 'Chunnakam', 'Abdhul12'),
(1234, 'Thanushan', '0112345678', 'Jaffna', 'Manipay', 'THanu123'),
(2345, 'Harsha', '0776544532', 'Jaffna', 'Manipay', 'Harsha34');

-- --------------------------------------------------------

--
-- Table structure for table `totalstock`
--

DROP TABLE IF EXISTS `totalstock`;
CREATE TABLE IF NOT EXISTS `totalstock` (
  `serialNo` int NOT NULL AUTO_INCREMENT,
  `productID` varchar(10) NOT NULL,
  `count` int NOT NULL,
  `stockStatus` enum('in','out') NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`serialNo`),
  KEY `productID` (`productID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `totalstock`
--

INSERT INTO `totalstock` (`serialNo`, `productID`, `count`, `stockStatus`, `date`) VALUES
(1, 'ZMG007', 56, 'out', '2021-08-23'),
(2, 'ZMG007', 87, '', '2021-08-10'),
(3, 'ZMG007', 56, 'out', '2021-08-23'),
(4, 'ZMG007', 87, '', '2021-08-10'),
(5, 'ZMG009', 87, 'out', '2021-08-18'),
(6, 'zmg006', 78, '', '2021-08-12'),
(7, 'ZMG009', 87, 'out', '2021-08-18'),
(8, 'zmg006', 78, '', '2021-08-12');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `personalstock`
--
ALTER TABLE `personalstock`
  ADD CONSTRAINT `product` FOREIGN KEY (`productID`) REFERENCES `productdetails` (`productID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `salesRep` FOREIGN KEY (`srID`) REFERENCES `salesrep` (`srID`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `totalstock`
--
ALTER TABLE `totalstock`
  ADD CONSTRAINT `productID` FOREIGN KEY (`productID`) REFERENCES `productdetails` (`productID`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
