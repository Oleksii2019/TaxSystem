-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: susers_db
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `replacement_request`
--

DROP TABLE IF EXISTS `replacement_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `replacement_request` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creation_time` datetime NOT NULL,
  `taxofficer` bigint(20) NOT NULL,
  `taxpayer` bigint(20) NOT NULL,
  `taxplayer` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `payer_idx` (`taxpayer`),
  KEY `payer_rep_idx` (`taxpayer`),
  KEY `officer_rep_idx` (`taxofficer`),
  CONSTRAINT `officer_rep` FOREIGN KEY (`taxofficer`) REFERENCES `taxofficers` (`id`),
  CONSTRAINT `payer_rep` FOREIGN KEY (`taxpayer`) REFERENCES `taxpayers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `replacement_request`
--

LOCK TABLES `replacement_request` WRITE;
/*!40000 ALTER TABLE `replacement_request` DISABLE KEYS */;
INSERT INTO `replacement_request` VALUES (77,'2019-07-17 13:16:27',1,3,0),(80,'2019-07-30 11:21:31',2,4,0);
/*!40000 ALTER TABLE `replacement_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report_alteration`
--

DROP TABLE IF EXISTS `report_alteration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `report_alteration` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `accept_time` datetime DEFAULT NULL,
  `accepted` bit(1) DEFAULT b'0',
  `creation_time` datetime NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `report` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `report_idx` (`report`),
  CONSTRAINT `report_alt` FOREIGN KEY (`report`) REFERENCES `reports` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_alteration`
--

LOCK TABLES `report_alteration` WRITE;
/*!40000 ALTER TABLE `report_alteration` DISABLE KEYS */;
INSERT INTO `report_alteration` VALUES (5,'2019-07-17 13:27:25',_binary '','2019-07-17 13:27:01','Зауваження',4),(6,NULL,_binary '\0','2019-07-17 13:27:25','',4),(10,'2019-07-17 13:28:47',_binary '','2019-07-17 13:28:28','Зауваження 2',9),(11,NULL,_binary '\0','2019-07-17 13:28:47','',9),(74,'2019-07-17 11:22:25',_binary '','2019-07-17 11:22:12','bbnnnmm123',73),(75,NULL,_binary '\0','2019-07-17 11:22:25','',73),(78,'2019-07-28 11:49:04',_binary '','2019-07-28 10:47:26','Доповнити пункт 2',86),(79,'2019-07-28 11:52:31',_binary '','2019-07-28 11:51:43','Доповнити пункт 3',86),(80,'2019-07-28 11:55:24',_binary '','2019-07-28 11:55:11','Доповнити пункт 4',86),(81,NULL,_binary '\0','2019-07-28 11:55:51','Доповнити пункт 5',86);
/*!40000 ALTER TABLE `report_alteration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reports`
--

DROP TABLE IF EXISTS `reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `reports` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `accept_time` datetime DEFAULT NULL,
  `accepted` bit(1) DEFAULT b'0',
  `assessed` bit(1) DEFAULT b'0',
  `creation_time` datetime NOT NULL,
  `taxpayer` bigint(20) NOT NULL,
  `taxofficer` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `taxofficer_idx` (`taxofficer`),
  KEY `officer_idx` (`taxofficer`),
  KEY `payer_idx` (`taxpayer`),
  CONSTRAINT `officer` FOREIGN KEY (`taxofficer`) REFERENCES `taxofficers` (`id`) ON DELETE SET NULL,
  CONSTRAINT `payer` FOREIGN KEY (`taxpayer`) REFERENCES `taxpayers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reports`
--

LOCK TABLES `reports` WRITE;
/*!40000 ALTER TABLE `reports` DISABLE KEYS */;
INSERT INTO `reports` VALUES (4,NULL,_binary '\0',_binary '\0','2019-07-17 13:26:06',3,1),(9,NULL,_binary '\0',_binary '\0','2019-07-17 11:21:22',3,1),(12,NULL,_binary '\0',_binary '\0','2019-07-27 22:09:44',4,2),(13,'2019-07-27 22:10:18',_binary '',_binary '','2019-07-28 00:21:03',4,2),(14,NULL,_binary '\0',_binary '\0','2019-07-15 21:19:32',4,2),(17,'2019-07-17 05:43:49',_binary '',_binary '','2019-07-14 18:51:54',3,1),(73,NULL,_binary '\0',_binary '\0','2019-07-14 18:54:09',3,1),(86,NULL,_binary '\0',_binary '','2019-07-28 09:22:33',4,2),(107,NULL,_binary '\0',_binary '\0','2019-08-02 08:40:20',3,1),(108,NULL,_binary '\0',_binary '\0','2019-08-02 08:40:28',4,2);
/*!40000 ALTER TABLE `reports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taxofficers`
--

DROP TABLE IF EXISTS `taxofficers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `taxofficers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(20) NOT NULL,
  `name` varchar(150) NOT NULL,
  `password` varchar(20) NOT NULL,
  `role` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taxofficers`
--

LOCK TABLES `taxofficers` WRITE;
/*!40000 ALTER TABLE `taxofficers` DISABLE KEYS */;
INSERT INTO `taxofficers` VALUES (1,'Mike1','Олег Бондар','11',0),(2,'Mike2','Петро Писарчук','11',0),(3,'Mike3','Тарас Кравець','11',0);
/*!40000 ALTER TABLE `taxofficers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taxpayers`
--

DROP TABLE IF EXISTS `taxpayers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `taxpayers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(20) NOT NULL,
  `name` varchar(150) NOT NULL,
  `password` varchar(20) NOT NULL,
  `role` int(11) NOT NULL,
  `taxofficer` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `taxofficer_idx` (`taxofficer`),
  CONSTRAINT `taxofficer` FOREIGN KEY (`taxofficer`) REFERENCES `taxofficers` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taxpayers`
--

LOCK TABLES `taxpayers` WRITE;
/*!40000 ALTER TABLE `taxpayers` DISABLE KEYS */;
INSERT INTO `taxpayers` VALUES (3,'Nike11','Василь Скляр','22',1,1),(4,'Nike22','Остап Швець','22',1,2),(5,'Nike21','ДП \"Сервис П\" ','22',1,2),(6,'Nike31','ПП \"Фотон +\"','22',1,3),(7,'Nike12','КП \"Зоряний Шлях\"','22',1,1),(8,'Nike32','ПП \"Зразкове\"','22',1,3);
/*!40000 ALTER TABLE `taxpayers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-07 16:43:24
