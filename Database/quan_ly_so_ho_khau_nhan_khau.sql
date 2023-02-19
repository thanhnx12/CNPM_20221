-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: quan_ly_so_ho_khau
-- ------------------------------------------------------
-- Server version	8.0.31

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
INSERT INTO `nhan_khau` VALUES (1,132123,'Khanh','2022-11-15','Nữ','plplplpl','hahaah','ahahah','ádf','1231','àdadsf','ádf','ádf','Tam vang','2022-11-09',1,NULL,1,NULL,'06515'),(2,0,'gà',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ababab',NULL,'Da Mat',NULL,1,NULL,1,NULL,'029595'),(3,123123,'Thanh','2022-11-15','awef','awfeawef','awefawef','awefawef','awef','123','afwefawe','awef','awef','Da Mat','2022-11-02',1,NULL,1,NULL,'891'),(4,0,'Hai',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'asdfasdf',NULL,'Tam tru',NULL,1,NULL,1,NULL,'123123'),(5,1234,'Lưu Tiến Ngọc','2013-02-13','Nam','Bắc Giang','Hà Nội','Kinh','Việt Nam','4444','Hanoi','Hanoi','Không','Da Mat','2023-02-13',1,NULL,1,NULL,'019032123'),(6,0,'123',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123',NULL,'Tam tru',NULL,1,NULL,1,NULL,'132'),(7,122019231,'Nguyễn Xuân Thành','2023-01-29','Nam','Bắc Giang','Bắc Giang','Kinh','Việt Nam','12','Bắc Giang','Bắc Giang','Không','Da Mat','2023-02-14',1,NULL,1,NULL,'0123567457'),(8,190827365,'Nguyễn Ngọc Nam','2023-02-15','Nam','Thanh Hóa','Thanh Hóa','Kinh','Việt Nam','123435634','Thanh Hóa','Thanh Hóa','Không','','2023-02-14',1,NULL,1,NULL,'01928347'),(9,120983567,'Nguyễn Văn Đức','2023-02-08','Nam','Bắc Giang','Bắc Giang','Kinh','Việt Nam','279835','Bắc Giang','Bắc Giang','Không','','2023-02-14',1,NULL,1,NULL,'01928375456'),(10,1235623423,'Nguyễn Trung Hải','2023-02-08','Nam','Hải Phòng','Hải Phòng','Kinh','Việt Nam','293856234','Hải Phòng','Hải PHòng','Không','','2023-02-14',1,NULL,1,NULL,'012938756'),(11,234523562,'Nguyễn Văn A','2023-02-09','Nam','Hà Nội','Hà Nội','Kinh','Việt Nam','0934576','Hà Nội','Hà Nội','Không','Tam vang','2023-02-14',1,NULL,1,NULL,'0129487612'),(12,239475345,'Nguyễn Văn B','2023-02-21','Nam','Hà Nội','Hà Nội','Kinh','Việt Nam','23892356','Hà Nội','Hà Nội','Không','Da Mat','2023-02-14',1,NULL,1,NULL,'089293652'),(13,234566,'Nguyễn Văn C','2023-02-15','asdf','sadf','oijsdf','aiosudhjf','asdf','2134','sdfas','asdf','asdf','','2023-02-14',1,NULL,1,NULL,'2345'),(14,1236677,'Nguyễn Văn D','2023-02-08','asdf','asdf','asdf','asdf','aasdf','235','asdf','asd','asdf','','2023-02-14',1,NULL,1,NULL,'456787'),(15,0,'sdffgadsfg',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'9387463',NULL,'Tam tru',NULL,1,NULL,1,NULL,'34506846'),(16,0,'9846709812',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'3457475578',NULL,'Da Mat',NULL,1,NULL,1,NULL,'56795603441'),(17,0,'Nguyen Van E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'hahaha',NULL,'Tam tru',NULL,1,NULL,1,NULL,'6457834'),(18,1234,'THanh','2023-02-08','ádf','ádf','ádf','ádf','ádf','2345','ádf','ádf','ádf','ádf','2023-02-14',1,NULL,1,NULL,'678678');
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

-- Dump completed on 2023-02-19 20:46:43
