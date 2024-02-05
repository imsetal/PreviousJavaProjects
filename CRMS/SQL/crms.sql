-- MySQL dump 10.13  Distrib 5.5.22, for Win32 (x86)
--
-- Host: localhost    Database: crms
-- ------------------------------------------------------
-- Server version	5.5.22

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
-- Table structure for table `management`
--
CREATE schema crms;
use crms;

DROP TABLE IF EXISTS `management`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `management` (
  `pc_id` int(11) DEFAULT NULL,
  `room` varchar(32) DEFAULT NULL,
  KEY `management_pc_id_fk` (`pc_id`),
  KEY `management_room_room_fk` (`room`),
  CONSTRAINT `management_pc_id_fk` FOREIGN KEY (`pc_id`) REFERENCES `pc` (`id`),
  CONSTRAINT `management_room_room_fk` FOREIGN KEY (`room`) REFERENCES `room` (`room`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `management`
--

LOCK TABLES `management` WRITE;
/*!40000 ALTER TABLE `management` DISABLE KEYS */;
INSERT INTO `management` VALUES (2,'101'),(3,'101'),(4,'101'),(1,'101'),(5,'103');
/*!40000 ALTER TABLE `management` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pc`
--

DROP TABLE IF EXISTS `pc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `CPU` varchar(128) DEFAULT NULL,
  `GPU` varchar(128) DEFAULT NULL,
  `RAM` varchar(128) DEFAULT NULL,
  `StorageDevice` varchar(128) DEFAULT NULL,
  `MotherBoard` varchar(128) DEFAULT NULL,
  `state` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `pc_pc_state_state_fk` (`state`),
  CONSTRAINT `pc_pc_state_state_fk` FOREIGN KEY (`state`) REFERENCES `pc_state` (`state`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pc`
--

LOCK TABLES `pc` WRITE;
/*!40000 ALTER TABLE `pc` DISABLE KEYS */;
INSERT INTO `pc` VALUES (1,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','8GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','使用中'),(2,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','使用中'),(3,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','使用中'),(4,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','使用中'),(5,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','使用中'),(6,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(7,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(8,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(9,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(10,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(11,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(12,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(13,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(14,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(15,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(16,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(17,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(18,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(19,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(20,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(21,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(22,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(23,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(24,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(25,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(26,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(27,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(28,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(29,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(30,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(31,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(32,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(33,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(34,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(35,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(36,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(37,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(38,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(39,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(40,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(41,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(42,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(43,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(44,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(45,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(46,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(47,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(48,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(49,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(50,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(51,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(52,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(53,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(54,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(55,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(56,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(57,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(58,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(59,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(60,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(61,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(62,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(63,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(64,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(65,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(66,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(67,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(68,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(69,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(70,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(71,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(72,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(73,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(74,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(75,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(76,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(77,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(78,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(79,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(80,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(81,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(82,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(83,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(84,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(85,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(86,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(87,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(88,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(89,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(90,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(91,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(92,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(93,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(94,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(95,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(96,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(97,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(98,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中'),(99,'Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）','Intel UHD Graphics 630','16GB DDR4 3200MHz','256GB SSD + 1TB 7200RPM SATA硬盘','华硕（ASUS）PRIME B560M-A','闲置中');
/*!40000 ALTER TABLE `pc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pc_state`
--

DROP TABLE IF EXISTS `pc_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pc_state` (
  `state` varchar(32) NOT NULL,
  PRIMARY KEY (`state`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pc_state`
--

LOCK TABLES `pc_state` WRITE;
/*!40000 ALTER TABLE `pc_state` DISABLE KEYS */;
INSERT INTO `pc_state` VALUES ('使用中'),('闲置中'),('需要维护');
/*!40000 ALTER TABLE `pc_state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room` (
  `room` varchar(32) NOT NULL,
  `seat_num` int(11) NOT NULL,
  PRIMARY KEY (`room`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES ('101',50),('102',50),('103',50),('104',50),('105',51),('106',50),('107',50),('108',50),('109',50),('110',50),('111',50),('112',50),('201',1);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user` varchar(32) NOT NULL DEFAULT '',
  `password` varchar(32) NOT NULL,
  `user_group` varchar(32) NOT NULL,
  PRIMARY KEY (`user`),
  KEY `user_user_group_name_fk` (`user_group`),
  CONSTRAINT `user_user_group_name_fk` FOREIGN KEY (`user_group`) REFERENCES `user_group` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('root','root','root'),('test0','123456','user'),('yj','123456','user');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_group`
--

DROP TABLE IF EXISTS `user_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_group` (
  `name` varchar(32) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_group`
--

LOCK TABLES `user_group` WRITE;
/*!40000 ALTER TABLE `user_group` DISABLE KEYS */;
INSERT INTO `user_group` VALUES ('root'),('user');
/*!40000 ALTER TABLE `user_group` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-12 19:53:41
