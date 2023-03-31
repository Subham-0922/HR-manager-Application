-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: cwproject
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `Deptid` int NOT NULL AUTO_INCREMENT,
  `Dname` varchar(15) NOT NULL,
  `isDeleted` tinyint DEFAULT '0',
  PRIMARY KEY (`Deptid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'Accounts',0),(2,'R&D',0),(3,'Marketing',0),(4,'R&D',0);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleave`
--

DROP TABLE IF EXISTS `empleave`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleave` (
  `LID` int NOT NULL AUTO_INCREMENT,
  `EID` int NOT NULL,
  `Dname` varchar(15) NOT NULL,
  `days_of_leave` int NOT NULL,
  `type` tinyint NOT NULL,
  `reason` varchar(80) NOT NULL,
  `status` varchar(13) DEFAULT 'pending',
  `remark` varchar(70) DEFAULT 'pending',
  `isRemoved` tinyint DEFAULT '0',
  `Date_of_leave` date NOT NULL,
  PRIMARY KEY (`LID`),
  KEY `EID` (`EID`),
  CONSTRAINT `empleave_ibfk_1` FOREIGN KEY (`EID`) REFERENCES `employee` (`EID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleave`
--

LOCK TABLES `empleave` WRITE;
/*!40000 ALTER TABLE `empleave` DISABLE KEYS */;
INSERT INTO `empleave` VALUES (1,1,'Account',4,2,'Having bad Headache','pending','pending',0,'2023-03-30'),(2,2,'Accounts',4,3,'My Marriage','Accepted','Ok ',1,'2023-03-30');
/*!40000 ALTER TABLE `empleave` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `EID` int NOT NULL AUTO_INCREMENT,
  `Ename` varchar(25) NOT NULL,
  `E_Address` varchar(25) NOT NULL,
  `edeptId` int DEFAULT NULL,
  `username` varchar(15) NOT NULL,
  `password` varchar(8) DEFAULT '123456',
  `date_of_joining` date DEFAULT NULL,
  `isRemoved` tinyint DEFAULT '0',
  `Salary_Per_Month` double NOT NULL,
  `available_Com_Leave` int DEFAULT '18',
  `available_sick_leave` int DEFAULT '12',
  `leave_taken` int DEFAULT '0',
  `dname` varchar(15) NOT NULL,
  PRIMARY KEY (`EID`),
  UNIQUE KEY `username` (`username`),
  KEY `edeptId` (`edeptId`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`edeptId`) REFERENCES `department` (`Deptid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Lalla','Durgapur',1,'ksubham','321','2023-03-29',1,99999,18,12,0,'Account'),(2,'RiyaJi','Asansol',1,'riya7','1234','2023-03-29',0,89000,18,12,1,'Accounts'),(3,'Shila','Kajora',3,'shila1','123456','2023-03-30',0,79000,18,12,0,'Marketing');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-31 22:31:32
