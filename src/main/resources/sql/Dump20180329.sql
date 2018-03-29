CREATE DATABASE  IF NOT EXISTS `css` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `css`;
-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: css
-- ------------------------------------------------------
-- Server version	5.7.20

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
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '购物车主键id',
  `buyer_id` int(11) NOT NULL COMMENT '购物车对应的买家id',
  `good_id` int(11) NOT NULL COMMENT '购物车中商品id',
  `good_num` int(11) NOT NULL DEFAULT '0' COMMENT '商品个数',
  `good_title` varchar(255) NOT NULL,
  `good_cost` int(11) NOT NULL DEFAULT '0' COMMENT '该类商品总价',
  `order_time` int(11) NOT NULL COMMENT '该类商品加入购物车的时间戳',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '该类商品是否已从购物车中移除，1为已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (7,4,27,4,'aa',9,3000,1),(8,4,27,4,'aa',13,3000,1),(9,4,29,3,'aa',34,3000,1),(10,4,30,3,'rrr',12,3000,1),(11,4,31,6,'ss',13,1522242786,1),(12,4,33,5,'aaa',12,1522246285,1),(13,4,34,3,'aa',1,1522333025,1),(14,4,34,4,'aa',1,1522333900,1),(15,4,35,6,'ty',34,1522334510,1),(16,4,37,7,'45',89,1522334895,1);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `good`
--

DROP TABLE IF EXISTS `good`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `good` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品主键id',
  `seller_id` int(11) NOT NULL COMMENT '提交该商品的卖家id',
  `buyer_id` int(11) NOT NULL COMMENT '购买该商品的买家id，若无人购买过，则为-1',
  `title` varchar(255) NOT NULL DEFAULT '' COMMENT '商品标题',
  `intro` varchar(255) NOT NULL DEFAULT '' COMMENT '商品摘要',
  `content` varchar(1000) NOT NULL DEFAULT '' COMMENT '商品内容',
  `photo` varchar(255) NOT NULL COMMENT '商品照片url',
  `cost` int(11) NOT NULL DEFAULT '0' COMMENT '商品单价',
  `create_time` int(11) NOT NULL DEFAULT '0' COMMENT '商品提交的时间戳',
  `sold_num` int(11) NOT NULL DEFAULT '0' COMMENT '商品已卖出的个数，若未卖出，则为0',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '商品是否已经被删除，1为已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `good`
--

LOCK TABLES `good` WRITE;
/*!40000 ALTER TABLE `good` DISABLE KEYS */;
INSERT INTO `good` VALUES (5,3,-1,'aaaa','aaaa','aaaa','aaaa',12,1522061485,0,1),(12,3,-1,'testTitle11','testIntro','testContent','https://ss1.baidu.com/-4o3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=8d3a9ea62c7f9e2f6f351b082f31e962/500fd9f9d72a6059099ccd5a2334349b023bbae5.jpg',100,1521210593,0,1),(24,3,-1,'testTitle11','testIntro','testContent','https://ss1.baidu.com/-4o3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=8d3a9ea62c7f9e2f6f351b082f31e962/500fd9f9d72a6059099ccd5a2334349b023bbae5.jpg',100,1521210593,0,1),(25,3,-1,'testTitle11','testIntro','testContent','https://ss1.baidu.com/-4o3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=8d3a9ea62c7f9e2f6f351b082f31e962/500fd9f9d72a6059099ccd5a2334349b023bbae5.jpg',100,1521210593,0,1),(26,3,-1,'testTitle4','testIntro4','edit4','https://ss1.baidu.com/-4o3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=8d3a9ea62c7f9e2f6f351b082f31e962/500fd9f9d72a6059099ccd5a2334349b023bbae5.jpg',9,1521210593,1,0),(27,3,-1,'aa','aa','aa','../images/1522332672208.jpg',24,1522143525,4,0),(29,3,-1,'aa','bb','aa','https://ss1.baidu.com/-4o3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=8d3a9ea62c7f9e2f6f351b082f31e962/500fd9f9d72a6059099ccd5a2334349b023bbae5.jpg',34,1522237668,3,0),(30,3,-1,'rrr','rrr','rrr','https://ss1.baidu.com/-4o3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=8d3a9ea62c7f9e2f6f351b082f31e962/500fd9f9d72a6059099ccd5a2334349b023bbae5.jpg',12,1522238344,3,0),(31,3,-1,'ss','ss','aa','https://ss1.baidu.com/-4o3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=8d3a9ea62c7f9e2f6f351b082f31e962/500fd9f9d72a6059099ccd5a2334349b023bbae5.jpg',13,1522242771,6,0),(32,3,-1,'aaa','aaa','aaa','https://ss1.baidu.com/-4o3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=8d3a9ea62c7f9e2f6f351b082f31e962/500fd9f9d72a6059099ccd5a2334349b023bbae5.jpg',12,1522246246,0,1),(33,3,-1,'aaa','aaa','rrr','https://ss1.baidu.com/-4o3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=8d3a9ea62c7f9e2f6f351b082f31e962/500fd9f9d72a6059099ccd5a2334349b023bbae5.jpg',12,1522246261,5,0),(34,3,-1,'aa','aa','aqq','../images/1522331913950.jpg',1,1522331918,4,0),(35,3,-1,'ty','aa','aaa','../images/1522334472649.jpg',34,1522334485,6,0),(36,3,-1,'+++','aaa','aaaa','../images/1522334817500.png',34,1522334823,0,1),(37,3,-1,'45','45','rt','../images/1522334864260.jpg',89,1522334850,7,0);
/*!40000 ALTER TABLE `good` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '购买记录主键',
  `buyer_id` int(11) NOT NULL COMMENT '买家id',
  `transaction_id` bigint(20) NOT NULL COMMENT '订单号',
  `good_id` int(11) NOT NULL COMMENT '商品id',
  `good_title` varchar(255) NOT NULL,
  `good_photo` varchar(255) NOT NULL,
  `good_num` int(11) NOT NULL DEFAULT '0',
  `good_cost` int(11) NOT NULL DEFAULT '0' COMMENT '该类商品总价',
  `purchase_time` int(11) NOT NULL COMMENT '购买时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
INSERT INTO `purchase` VALUES (1,4,1228189162,26,'testTitle4','https://ss1.baidu.com/-4o3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=8d3a9ea62c7f9e2f6f351b082f31e962/500fd9f9d72a6059099ccd5a2334349b023bbae5.jpg',1,9,1522234932),(2,4,1273129163,27,'aa','https://ss1.baidu.com/-4o3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=8d3a9ea62c7f9e2f6f351b082f31e962/500fd9f9d72a6059099ccd5a2334349b023bbae5.jpg',4,13,1522237626),(3,4,1274329165,29,'aa','https://ss1.baidu.com/-4o3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=8d3a9ea62c7f9e2f6f351b082f31e962/500fd9f9d72a6059099ccd5a2334349b023bbae5.jpg',3,34,1522237706),(4,4,1285329166,30,'rrr','https://ss1.baidu.com/-4o3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=8d3a9ea62c7f9e2f6f351b082f31e962/500fd9f9d72a6059099ccd5a2334349b023bbae5.jpg',3,12,1522238366),(5,4,1461569167,31,'ss','https://ss1.baidu.com/-4o3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=8d3a9ea62c7f9e2f6f351b082f31e962/500fd9f9d72a6059099ccd5a2334349b023bbae5.jpg',6,13,1522244150),(6,4,1803282211300433,33,'aaa','https://ss1.baidu.com/-4o3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=8d3a9ea62c7f9e2f6f351b082f31e962/500fd9f9d72a6059099ccd5a2334349b023bbae5.jpg',5,12,1522246290),(7,4,1803292233300434,34,'aa','../images/1522331913950.jpg',3,1,1522334010),(8,4,1803292233300434,34,'aa','../images/1522331913950.jpg',4,1,1522334010),(9,4,1803292241570435,35,'ty','../images/1522334472649.jpg',6,34,1522334517),(10,4,1803292248330437,37,'45','../images/1522334864260.jpg',7,89,1522334913);
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商家id',
  `username` varchar(45) NOT NULL COMMENT '商家用户名',
  `nickname` varchar(45) DEFAULT NULL COMMENT '商家昵称',
  `password` varchar(45) NOT NULL COMMENT '商家md5加密后的密码',
  `type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '用户类型，0为卖家，1为买家，默认为0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `account_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (3,'seller','卖家','981c57a5cfb0f868e064904b8745766f',0),(4,'buyer','买家','37254660e226ea65ce6f1efd54233424',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-29 23:58:07
