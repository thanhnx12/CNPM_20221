-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: quan_ly_so_ho_khau
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `nhan_khau`
--

DROP TABLE IF EXISTS `nhan_khau`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhan_khau` (
  `ID` int NOT NULL,
  `soCMT_CCCD` int DEFAULT NULL,
  `hoTen` varchar(100) NOT NULL,
  `ngaySinh` date DEFAULT NULL,
  `gioiTinh` varchar(100) DEFAULT NULL,
  `noiSinh` varchar(100) DEFAULT '',
  `nguyenQuan` varchar(100) DEFAULT NULL,
  `danToc` varchar(100) DEFAULT NULL,
  `quocTich` varchar(100) DEFAULT NULL,
  `soHoChieu` varchar(100) DEFAULT NULL,
  `noiThuongTru` varchar(100) DEFAULT NULL,
  `diaChiHienTai` varchar(100) DEFAULT NULL,
  `tonGiao` varchar(100) DEFAULT NULL,
  `ghiChu` varchar(100) DEFAULT NULL,
  `ngayTao` date DEFAULT NULL,
  `idNguoiTao` int DEFAULT NULL,
  `ngayXoa` date DEFAULT NULL,
  `idNguoiXoa` int DEFAULT NULL,
  `lyDoXoa` varchar(100) DEFAULT NULL,
  `dienThoai` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `idNguoiTao` (`idNguoiTao`,`idNguoiXoa`),
  KEY `idNguoiXoa` (`idNguoiXoa`),
  CONSTRAINT `nhan_khau_ibfk_1` FOREIGN KEY (`idNguoiTao`) REFERENCES `users` (`id`),
  CONSTRAINT `nhan_khau_ibfk_2` FOREIGN KEY (`idNguoiXoa`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhan_khau`
--

LOCK TABLES `nhan_khau` WRITE;
/*!40000 ALTER TABLE `nhan_khau` DISABLE KEYS */;
INSERT INTO `nhan_khau` VALUES (1,12345,'Ngoc','2002-09-12','Nam','BacGiang','BacGiang','Kinh','Viet Nam','123','hoho','haha','Khong','hohooh','2002-09-12',1,'2002-09-12',1,'asdf',NULL),(2,12345,'Thanh','2002-09-12','Nam','BacGiang','BacGiang','Kinh','Viet Nam','123','hoho','haha','Khong','hohooh','2002-09-12',1,'2002-09-12',1,'asdf',NULL),(3,12345,'Nam','2002-09-12','Nam','BacGiang','BacGiang','Kinh','Viet Nam','123','hoho','haha','Khong','hohooh','2002-09-12',1,'2002-09-12',1,'asdf',NULL),(4,12345,'Hai','2002-09-12','Nam','BacGiang','BacGiang','Kinh','Viet Nam','123','hoho','haha','Khong','hohooh','2002-09-12',1,'2002-09-12',1,'asdf',NULL),(5,12345,'Duc','2002-09-12','Nam','BacGiang','BacGiang','Kinh','Viet Nam','123','hoho','haha','Khong','hohooh','2002-09-12',1,'2002-09-12',1,'asdf',NULL),(6,12345,'Doremon','2002-09-12','Nam','BacGiang','BacGiang','Kinh','Viet Nam','123','hoho','haha','Khong','hohooh','2002-09-12',1,'2002-09-12',1,'asdf','088888'),(7,12345,'asdfawe','2002-09-12','Nam','BacGiang','BacGiang','Kinh','Viet Nam','123','hoho','haha','Khong','hohooh','2002-09-12',1,'2002-09-12',1,'asdf','09999'),(8,12345,'asdfawe','2002-09-12','Nam','BacGiang','BacGiang','Kinh','Viet Nam','123','hoho','haha','Khong','hohooh','2002-09-12',1,'2002-09-12',1,'asdf','09999'),(9,0,'mlmlmlmlml',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'popo',NULL,'Tạm trú',NULL,1,NULL,1,NULL,'0999999'),(10,0,'LUU',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'adsgfadg',NULL,'Tạm trú',NULL,1,NULL,1,NULL,'099999'),(11,0,'Luu',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'hljlgl',NULL,'Tạm trú',NULL,1,NULL,1,NULL,'01651'),(12,0,'asdf',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'zsdf',NULL,'Tạm trú',NULL,1,NULL,1,NULL,'123');
/*!40000 ALTER TABLE `nhan_khau` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-02 11:49:15
