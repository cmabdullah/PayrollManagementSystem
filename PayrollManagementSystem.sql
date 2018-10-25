-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: PayrollManagementSystem
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `attendence`
--

DROP TABLE IF EXISTS `attendence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attendence` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `logintime` datetime DEFAULT NULL,
  `logouttime` datetime DEFAULT NULL,
  `userinfo_id` int(11) NOT NULL,
  `ipaddress` varchar(45) DEFAULT NULL,
  `workinghours` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_attendence_userinfo1_idx` (`userinfo_id`),
  CONSTRAINT `fk_attendence_userinfo1` FOREIGN KEY (`userinfo_id`) REFERENCES `userinfo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendence`
--

LOCK TABLES `attendence` WRITE;
/*!40000 ALTER TABLE `attendence` DISABLE KEYS */;
INSERT INTO `attendence` VALUES (24,'2018-10-25 05:57:01',NULL,2020,'0:0:0:0:0:0:0:1',0),(25,'2018-10-25 06:09:45',NULL,2020,'0:0:0:0:0:0:0:1',0),(26,'2018-10-25 06:56:52',NULL,2020,'0:0:0:0:0:0:0:1',0),(27,'2018-10-25 07:09:21',NULL,2020,'0:0:0:0:0:0:0:1',0);
/*!40000 ALTER TABLE `attendence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grade`
--

DROP TABLE IF EXISTS `grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grade` (
  `id` int(11) NOT NULL,
  `basic` int(11) DEFAULT NULL,
  `medicalallowence` int(11) DEFAULT NULL,
  `houserent` int(11) DEFAULT NULL,
  `transport` int(11) DEFAULT NULL,
  `lunch` int(11) DEFAULT NULL,
  `userinfo_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_grade_userinfo1_idx` (`userinfo_id`),
  CONSTRAINT `fk_grade_userinfo1` FOREIGN KEY (`userinfo_id`) REFERENCES `userinfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grade`
--

LOCK TABLES `grade` WRITE;
/*!40000 ALTER TABLE `grade` DISABLE KEYS */;
/*!40000 ALTER TABLE `grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leave`
--

DROP TABLE IF EXISTS `leave`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `leave` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reasone` varchar(45) DEFAULT NULL,
  `from` datetime DEFAULT NULL,
  `to` datetime DEFAULT NULL,
  `userinfo_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_leave_userinfo1_idx` (`userinfo_id`),
  CONSTRAINT `fk_leave_userinfo1` FOREIGN KEY (`userinfo_id`) REFERENCES `userinfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leave`
--

LOCK TABLES `leave` WRITE;
/*!40000 ALTER TABLE `leave` DISABLE KEYS */;
/*!40000 ALTER TABLE `leave` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loan`
--

DROP TABLE IF EXISTS `loan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `placedate` datetime NOT NULL,
  `approvedate` datetime DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `userinfo_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_loan_userinfo1_idx` (`userinfo_id`),
  CONSTRAINT `fk_loan_userinfo1` FOREIGN KEY (`userinfo_id`) REFERENCES `userinfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loan`
--

LOCK TABLES `loan` WRITE;
/*!40000 ALTER TABLE `loan` DISABLE KEYS */;
/*!40000 ALTER TABLE `loan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salary`
--

DROP TABLE IF EXISTS `salary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datemonthyear` datetime DEFAULT NULL,
  `Leave_id` int(11) NOT NULL,
  `Loan_id` int(11) NOT NULL,
  `grade_id` int(11) NOT NULL,
  `userinfo_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_salary_Leave1_idx` (`Leave_id`),
  KEY `fk_salary_Loan1_idx` (`Loan_id`),
  KEY `fk_salary_grade1_idx` (`grade_id`),
  KEY `fk_salary_userinfo1_idx` (`userinfo_id`),
  CONSTRAINT `fk_salary_Leave1` FOREIGN KEY (`Leave_id`) REFERENCES `leave` (`id`),
  CONSTRAINT `fk_salary_Loan1` FOREIGN KEY (`Loan_id`) REFERENCES `loan` (`id`),
  CONSTRAINT `fk_salary_grade1` FOREIGN KEY (`grade_id`) REFERENCES `grade` (`id`),
  CONSTRAINT `fk_salary_userinfo1` FOREIGN KEY (`userinfo_id`) REFERENCES `userinfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salary`
--

LOCK TABLES `salary` WRITE;
/*!40000 ALTER TABLE `salary` DISABLE KEYS */;
/*!40000 ALTER TABLE `salary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userinfo`
--

DROP TABLE IF EXISTS `userinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `fullname` varchar(45) DEFAULT NULL,
  `address` varchar(250) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `phone` int(14) NOT NULL,
  `password` varchar(100) NOT NULL,
  `authority` varchar(45) DEFAULT NULL,
  `enabled` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2027 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userinfo`
--

LOCK TABLES `userinfo` WRITE;
/*!40000 ALTER TABLE `userinfo` DISABLE KEYS */;
INSERT INTO `userinfo` VALUES (2020,'cmaa','C M Abdullah Khan','Bonosree','cmabdullah21@gmail.com',1717243357,'2783baf271251333bb18a8532305bf5761ee1c4ad636d352b73efc0dc6bf154ffb0fa0ed7face9c8','ROLE_ADMIN',1),(2026,'zxcv','C. M. Abdullah Khan','House#10, Road#5,Block#B, Banasree,Rampura,Dhaka-1219, House#10, Road#5,Block#B, Banasree,Rampura,Dhaka-1219','a.kium.khan@gmail.com',1717243357,'9611efdad194cffdcb05ecf4f671515ef2cf357be63ad7615086b17e99013ef956b46e56b5a29b00','ROLE_ADMIN',1);
/*!40000 ALTER TABLE `userinfo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-25 13:29:44
