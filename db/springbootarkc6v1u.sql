-- MySQL dump 10.13  Distrib 5.7.31, for Linux (x86_64)
--
-- Host: localhost    Database: springbootarkc6v1u
-- ------------------------------------------------------
-- Server version	5.7.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `springbootarkc6v1u`
--

/*!40000 DROP DATABASE IF EXISTS `springbootarkc6v1u`*/;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `springbootarkc6v1u` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `springbootarkc6v1u`;

--
-- Table structure for table `ceshifenxi`
--

DROP TABLE IF EXISTS `ceshifenxi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ceshifenxi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `xiangmubianhao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目编号',
  `renwumingcheng` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '任务名称',
  `xiangmumingcheng` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '项目名称',
  `xiangmuleixing` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目类型',
  `ceshiyongli` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '测试用例',
  `zhixingshunxu` longtext COLLATE utf8mb4_unicode_ci COMMENT '执行顺序',
  `zhixingtiaojian` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '执行条件',
  `jinglizhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '经理账号',
  `jinglixingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '经理姓名',
  `tuanduimingcheng` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '团队名称',
  `gongchengshihao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '工程师号',
  `gongchengshiming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '工程师名',
  `quexianxiufu` longtext COLLATE utf8mb4_unicode_ci COMMENT '缺陷修复',
  `daimagaijin` longtext COLLATE utf8mb4_unicode_ci COMMENT '代码改进',
  `kaifazhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '开发账号',
  `kaifaxingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '开发姓名',
  `fenxishijian` datetime DEFAULT NULL COMMENT '分析时间',
  `ceshijieguo` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '测试结果',
  `gaijinjianyi` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '改进建议',
  `fenxishihao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分析师号',
  `fenxishiming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分析师名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='测试分析';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ceshifenxi`
--

LOCK TABLES `ceshifenxi` WRITE;
/*!40000 ALTER TABLE `ceshifenxi` DISABLE KEYS */;
INSERT INTO `ceshifenxi` VALUES (1,'2025-03-03 14:28:33','项目编号1','任务名称1','项目名称1','项目类型1','测试用例1','执行顺序1','执行条件1','经理账号1','经理姓名1','团队名称1','工程师号1','工程师名1','缺陷修复1','代码改进1','开发账号1','开发姓名1','2025-03-03 22:28:33','优','改进建议1','分析师号1','分析师名1'),(2,'2025-03-03 14:28:33','项目编号2','任务名称2','项目名称2','项目类型2','测试用例2','执行顺序2','执行条件2','经理账号2','经理姓名2','团队名称2','工程师号2','工程师名2','缺陷修复2','代码改进2','开发账号2','开发姓名2','2025-03-03 22:28:33','优','改进建议2','分析师号2','分析师名2'),(3,'2025-03-03 14:28:33','项目编号3','任务名称3','项目名称3','项目类型3','测试用例3','执行顺序3','执行条件3','经理账号3','经理姓名3','团队名称3','工程师号3','工程师名3','缺陷修复3','代码改进3','开发账号3','开发姓名3','2025-03-03 22:28:33','优','改进建议3','分析师号3','分析师名3'),(4,'2025-03-03 14:28:33','项目编号4','任务名称4','项目名称4','项目类型4','测试用例4','执行顺序4','执行条件4','经理账号4','经理姓名4','团队名称4','工程师号4','工程师名4','缺陷修复4','代码改进4','开发账号4','开发姓名4','2025-03-03 22:28:33','优','改进建议4','分析师号4','分析师名4'),(5,'2025-03-03 14:28:33','项目编号5','任务名称5','项目名称5','项目类型5','测试用例5','执行顺序5','执行条件5','经理账号5','经理姓名5','团队名称5','工程师号5','工程师名5','缺陷修复5','代码改进5','开发账号5','开发姓名5','2025-03-03 22:28:33','优','改进建议5','分析师号5','分析师名5'),(6,'2025-03-03 14:28:33','项目编号6','任务名称6','项目名称6','项目类型6','测试用例6','执行顺序6','执行条件6','经理账号6','经理姓名6','团队名称6','工程师号6','工程师名6','缺陷修复6','代码改进6','开发账号6','开发姓名6','2025-03-03 22:28:33','优','改进建议6','分析师号6','分析师名6'),(7,'2025-03-03 14:28:33','项目编号7','任务名称7','项目名称7','项目类型7','测试用例7','执行顺序7','执行条件7','经理账号7','经理姓名7','团队名称7','工程师号7','工程师名7','缺陷修复7','代码改进7','开发账号7','开发姓名7','2025-03-03 22:28:33','优','改进建议7','分析师号7','分析师名7'),(8,'2025-03-03 14:28:33','项目编号8','任务名称8','项目名称8','项目类型8','测试用例8','执行顺序8','执行条件8','经理账号8','经理姓名8','团队名称8','工程师号8','工程师名8','缺陷修复8','代码改进8','开发账号8','开发姓名8','2025-03-03 22:28:33','优','改进建议8','分析师号8','分析师名8');
/*!40000 ALTER TABLE `ceshifenxi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ceshigongchengshi`
--

DROP TABLE IF EXISTS `ceshigongchengshi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ceshigongchengshi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gongchengshihao` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '工程师号',
  `gongchengshiming` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '工程师名',
  `mima` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `xingbie` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '性别',
  `shouji` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机',
  `tuanduimingcheng` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '团队名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `gongchengshihao` (`gongchengshihao`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='测试工程师';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ceshigongchengshi`
--

LOCK TABLES `ceshigongchengshi` WRITE;
/*!40000 ALTER TABLE `ceshigongchengshi` DISABLE KEYS */;
INSERT INTO `ceshigongchengshi` VALUES (31,'2025-03-03 14:28:32','工程师号1','工程师名1','123456','男','13823888881','团队名称1'),(32,'2025-03-03 14:28:32','工程师号2','工程师名2','123456','男','13823888882','团队名称2'),(33,'2025-03-03 14:28:32','工程师号3','工程师名3','123456','男','13823888883','团队名称3'),(34,'2025-03-03 14:28:32','工程师号4','工程师名4','123456','男','13823888884','团队名称4'),(35,'2025-03-03 14:28:32','工程师号5','工程师名5','123456','男','13823888885','团队名称5'),(36,'2025-03-03 14:28:32','工程师号6','工程师名6','123456','男','13823888886','团队名称6'),(37,'2025-03-03 14:28:32','工程师号7','工程师名7','123456','男','13823888887','团队名称7'),(38,'2025-03-03 14:28:32','工程师号8','工程师名8','123456','男','13823888888','团队名称8');
/*!40000 ALTER TABLE `ceshigongchengshi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ceshihuodong`
--

DROP TABLE IF EXISTS `ceshihuodong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ceshihuodong` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `xiangmubianhao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目编号',
  `xiangmumingcheng` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目名称',
  `xiangmuleixing` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目类型',
  `huodongmingcheng` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '活动名称',
  `huodongshijian` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '活动时间',
  `huodongjieshao` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '活动介绍',
  `jinglizhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '经理账号',
  `jinglixingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '经理姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='测试活动';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ceshihuodong`
--

LOCK TABLES `ceshihuodong` WRITE;
/*!40000 ALTER TABLE `ceshihuodong` DISABLE KEYS */;
INSERT INTO `ceshihuodong` VALUES (1,'2025-03-03 14:28:33','项目编号1','项目名称1','项目类型1','活动名称1','活动时间1','活动介绍1','经理账号1','经理姓名1'),(2,'2025-03-03 14:28:33','项目编号2','项目名称2','项目类型2','活动名称2','活动时间2','活动介绍2','经理账号2','经理姓名2'),(3,'2025-03-03 14:28:33','项目编号3','项目名称3','项目类型3','活动名称3','活动时间3','活动介绍3','经理账号3','经理姓名3'),(4,'2025-03-03 14:28:33','项目编号4','项目名称4','项目类型4','活动名称4','活动时间4','活动介绍4','经理账号4','经理姓名4'),(5,'2025-03-03 14:28:33','项目编号5','项目名称5','项目类型5','活动名称5','活动时间5','活动介绍5','经理账号5','经理姓名5'),(6,'2025-03-03 14:28:33','项目编号6','项目名称6','项目类型6','活动名称6','活动时间6','活动介绍6','经理账号6','经理姓名6'),(7,'2025-03-03 14:28:33','项目编号7','项目名称7','项目类型7','活动名称7','活动时间7','活动介绍7','经理账号7','经理姓名7'),(8,'2025-03-03 14:28:33','项目编号8','项目名称8','项目类型8','活动名称8','活动时间8','活动介绍8','经理账号8','经理姓名8');
/*!40000 ALTER TABLE `ceshihuodong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ceshijihua`
--

DROP TABLE IF EXISTS `ceshijihua`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ceshijihua` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `xiangmubianhao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目编号',
  `xiangmumingcheng` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '项目名称',
  `xiangmuleixing` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目类型',
  `jihuafujian` longtext COLLATE utf8mb4_unicode_ci COMMENT '计划附件',
  `ceshicelve` longtext COLLATE utf8mb4_unicode_ci COMMENT '测试策略',
  `jihuaxiangqing` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '计划详情',
  `jihuashijian` datetime DEFAULT NULL COMMENT '计划时间',
  `jinglizhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '经理账号',
  `jinglixingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '经理姓名',
  `tuanduimingcheng` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '团队名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='测试计划';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ceshijihua`
--

LOCK TABLES `ceshijihua` WRITE;
/*!40000 ALTER TABLE `ceshijihua` DISABLE KEYS */;
INSERT INTO `ceshijihua` VALUES (1,'2025-03-03 14:28:32','项目编号1','项目名称1','项目类型1','','测试策略1','计划详情1','2025-03-03 22:28:32','经理账号1','经理姓名1','团队名称1'),(2,'2025-03-03 14:28:32','项目编号2','项目名称2','项目类型2','','测试策略2','计划详情2','2025-03-03 22:28:32','经理账号2','经理姓名2','团队名称2'),(3,'2025-03-03 14:28:32','项目编号3','项目名称3','项目类型3','','测试策略3','计划详情3','2025-03-03 22:28:32','经理账号3','经理姓名3','团队名称3'),(4,'2025-03-03 14:28:32','项目编号4','项目名称4','项目类型4','','测试策略4','计划详情4','2025-03-03 22:28:32','经理账号4','经理姓名4','团队名称4'),(5,'2025-03-03 14:28:32','项目编号5','项目名称5','项目类型5','','测试策略5','计划详情5','2025-03-03 22:28:32','经理账号5','经理姓名5','团队名称5'),(6,'2025-03-03 14:28:32','项目编号6','项目名称6','项目类型6','','测试策略6','计划详情6','2025-03-03 22:28:32','经理账号6','经理姓名6','团队名称6'),(7,'2025-03-03 14:28:32','项目编号7','项目名称7','项目类型7','','测试策略7','计划详情7','2025-03-03 22:28:32','经理账号7','经理姓名7','团队名称7'),(8,'2025-03-03 14:28:32','项目编号8','项目名称8','项目类型8','','测试策略8','计划详情8','2025-03-03 22:28:32','经理账号8','经理姓名8','团队名称8');
/*!40000 ALTER TABLE `ceshijihua` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ceshijingli`
--

DROP TABLE IF EXISTS `ceshijingli`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ceshijingli` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `jinglizhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '经理账号',
  `mima` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `jinglixingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '经理姓名',
  `touxiang` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  `xingbie` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '性别',
  `lianxidianhua` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系电话',
  PRIMARY KEY (`id`),
  UNIQUE KEY `jinglizhanghao` (`jinglizhanghao`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='测试经理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ceshijingli`
--

LOCK TABLES `ceshijingli` WRITE;
/*!40000 ALTER TABLE `ceshijingli` DISABLE KEYS */;
INSERT INTO `ceshijingli` VALUES (21,'2025-03-03 14:28:32','经理账号1','123456','经理姓名1','upload/ceshijingli_touxiang1.jpg','男','13823888881'),(22,'2025-03-03 14:28:32','经理账号2','123456','经理姓名2','upload/ceshijingli_touxiang2.jpg','男','13823888882'),(23,'2025-03-03 14:28:32','经理账号3','123456','经理姓名3','upload/ceshijingli_touxiang3.jpg','男','13823888883'),(24,'2025-03-03 14:28:32','经理账号4','123456','经理姓名4','upload/ceshijingli_touxiang4.jpg','男','13823888884'),(25,'2025-03-03 14:28:32','经理账号5','123456','经理姓名5','upload/ceshijingli_touxiang5.jpg','男','13823888885'),(26,'2025-03-03 14:28:32','经理账号6','123456','经理姓名6','upload/ceshijingli_touxiang6.jpg','男','13823888886'),(27,'2025-03-03 14:28:32','经理账号7','123456','经理姓名7','upload/ceshijingli_touxiang7.jpg','男','13823888887'),(28,'2025-03-03 14:28:32','经理账号8','123456','经理姓名8','upload/ceshijingli_touxiang8.jpg','男','13823888888');
/*!40000 ALTER TABLE `ceshijingli` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ceshirenwu`
--

DROP TABLE IF EXISTS `ceshirenwu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ceshirenwu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `xiangmubianhao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目编号',
  `renwumingcheng` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '任务名称',
  `xiangmumingcheng` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '项目名称',
  `xiangmuleixing` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目类型',
  `renwuwenjian` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '任务文件',
  `renwuneirong` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '任务内容',
  `xiafashijian` datetime DEFAULT NULL COMMENT '下发时间',
  `jinglizhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '经理账号',
  `jinglixingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '经理姓名',
  `gongchengshihao` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '工程师号',
  `gongchengshiming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '工程师名',
  `tuanduimingcheng` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '团队名称',
  `renwuzhuangtai` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '任务状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='测试任务';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ceshirenwu`
--

LOCK TABLES `ceshirenwu` WRITE;
/*!40000 ALTER TABLE `ceshirenwu` DISABLE KEYS */;
INSERT INTO `ceshirenwu` VALUES (1,'2025-03-03 14:28:33','项目编号1','任务名称1','项目名称1','项目类型1','','任务内容1','2025-03-03 22:28:33','经理账号1','经理姓名1','工程师号1','工程师名1','团队名称1','已完成'),(2,'2025-03-03 14:28:33','项目编号2','任务名称2','项目名称2','项目类型2','','任务内容2','2025-03-03 22:28:33','经理账号2','经理姓名2','工程师号2','工程师名2','团队名称2','已完成'),(3,'2025-03-03 14:28:33','项目编号3','任务名称3','项目名称3','项目类型3','','任务内容3','2025-03-03 22:28:33','经理账号3','经理姓名3','工程师号3','工程师名3','团队名称3','已完成'),(4,'2025-03-03 14:28:33','项目编号4','任务名称4','项目名称4','项目类型4','','任务内容4','2025-03-03 22:28:33','经理账号4','经理姓名4','工程师号4','工程师名4','团队名称4','已完成'),(5,'2025-03-03 14:28:33','项目编号5','任务名称5','项目名称5','项目类型5','','任务内容5','2025-03-03 22:28:33','经理账号5','经理姓名5','工程师号5','工程师名5','团队名称5','已完成'),(6,'2025-03-03 14:28:33','项目编号6','任务名称6','项目名称6','项目类型6','','任务内容6','2025-03-03 22:28:33','经理账号6','经理姓名6','工程师号6','工程师名6','团队名称6','已完成'),(7,'2025-03-03 14:28:33','项目编号7','任务名称7','项目名称7','项目类型7','','任务内容7','2025-03-03 22:28:33','经理账号7','经理姓名7','工程师号7','工程师名7','团队名称7','已完成'),(8,'2025-03-03 14:28:33','项目编号8','任务名称8','项目名称8','项目类型8','','任务内容8','2025-03-03 22:28:33','经理账号8','经理姓名8','工程师号8','工程师名8','团队名称8','已完成'),(9,'2025-03-03 14:35:46','项目编号1','阿斯蒂芬','项目名称1','项目类型1','upload/1741012540283.zip','<p>阿斯蒂芬</p>','2025-03-03 22:35:29','经理账号1','经理姓名1','工程师号1','工程师名1','团队名称1','待完成');
/*!40000 ALTER TABLE `ceshirenwu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ceshituandui`
--

DROP TABLE IF EXISTS `ceshituandui`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ceshituandui` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `tuanduimingcheng` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '团队名称',
  `lianxifangshi` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系方式',
  `chenglishijian` date DEFAULT NULL COMMENT '成立时间',
  `tuanduichengyuan` longtext COLLATE utf8mb4_unicode_ci COMMENT '团队成员',
  `tuanduijieshao` longtext COLLATE utf8mb4_unicode_ci COMMENT '团队介绍',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='测试团队';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ceshituandui`
--

LOCK TABLES `ceshituandui` WRITE;
/*!40000 ALTER TABLE `ceshituandui` DISABLE KEYS */;
INSERT INTO `ceshituandui` VALUES (1,'2025-03-03 14:28:32','团队名称1','13823888881','2025-03-03','团队成员1','团队介绍1'),(2,'2025-03-03 14:28:32','团队名称2','13823888882','2025-03-03','团队成员2','团队介绍2'),(3,'2025-03-03 14:28:32','团队名称3','13823888883','2025-03-03','团队成员3','团队介绍3'),(4,'2025-03-03 14:28:32','团队名称4','13823888884','2025-03-03','团队成员4','团队介绍4'),(5,'2025-03-03 14:28:33','团队名称5','13823888885','2025-03-03','团队成员5','团队介绍5'),(6,'2025-03-03 14:28:33','团队名称6','13823888886','2025-03-03','团队成员6','团队介绍6'),(7,'2025-03-03 14:28:33','团队名称7','13823888887','2025-03-03','团队成员7','团队介绍7'),(8,'2025-03-03 14:28:33','团队名称8','13823888888','2025-03-03','团队成员8','团队介绍8');
/*!40000 ALTER TABLE `ceshituandui` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ceshiyongli`
--

DROP TABLE IF EXISTS `ceshiyongli`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ceshiyongli` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `yonglimingcheng` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用例名称',
  `yongliwenjian` longtext COLLATE utf8mb4_unicode_ci COMMENT '用例文件',
  `gongnengyaoqiu` longtext COLLATE utf8mb4_unicode_ci COMMENT '功能要求',
  `cunzaiwenti` longtext COLLATE utf8mb4_unicode_ci COMMENT '存在问题',
  `jiejuexuqiu` longtext COLLATE utf8mb4_unicode_ci COMMENT '解决需求',
  `xiangqingxinxi` longtext COLLATE utf8mb4_unicode_ci COMMENT '详情信息',
  `jinglizhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '经理账号',
  `dengjishijian` datetime DEFAULT NULL COMMENT '登记时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='测试用例';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ceshiyongli`
--

LOCK TABLES `ceshiyongli` WRITE;
/*!40000 ALTER TABLE `ceshiyongli` DISABLE KEYS */;
INSERT INTO `ceshiyongli` VALUES (1,'2025-03-03 14:28:33','用例名称1','','功能要求1','存在问题1','解决需求1','详情信息1','经理账号1','2025-03-03 22:28:33'),(2,'2025-03-03 14:28:33','用例名称2','','功能要求2','存在问题2','解决需求2','详情信息2','经理账号2','2025-03-03 22:28:33'),(3,'2025-03-03 14:28:33','用例名称3','','功能要求3','存在问题3','解决需求3','详情信息3','经理账号3','2025-03-03 22:28:33'),(4,'2025-03-03 14:28:33','用例名称4','','功能要求4','存在问题4','解决需求4','详情信息4','经理账号4','2025-03-03 22:28:33'),(5,'2025-03-03 14:28:33','用例名称5','','功能要求5','存在问题5','解决需求5','详情信息5','经理账号5','2025-03-03 22:28:33'),(6,'2025-03-03 14:28:33','用例名称6','','功能要求6','存在问题6','解决需求6','详情信息6','经理账号6','2025-03-03 22:28:33'),(7,'2025-03-03 14:28:33','用例名称7','','功能要求7','存在问题7','解决需求7','详情信息7','经理账号7','2025-03-03 22:28:33'),(8,'2025-03-03 14:28:33','用例名称8','','功能要求8','存在问题8','解决需求8','详情信息8','经理账号8','2025-03-03 22:28:33');
/*!40000 ALTER TABLE `ceshiyongli` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config`
--

DROP TABLE IF EXISTS `config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '配置参数名称',
  `value` varchar(100) DEFAULT NULL COMMENT '配置参数值',
  `url` varchar(500) DEFAULT NULL COMMENT 'url',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='配置文件';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config`
--

LOCK TABLES `config` WRITE;
/*!40000 ALTER TABLE `config` DISABLE KEYS */;
INSERT INTO `config` VALUES (1,'picture1','upload/picture1.jpg',NULL),(2,'picture2','upload/picture2.jpg',NULL),(3,'picture3','upload/picture3.jpg',NULL);
/*!40000 ALTER TABLE `config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fenxishi`
--

DROP TABLE IF EXISTS `fenxishi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fenxishi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `fenxishihao` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '分析师号',
  `fenxishiming` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '分析师名',
  `mima` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `xingbie` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '性别',
  `dianhuahaoma` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '电话号码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `fenxishihao` (`fenxishihao`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='QA分析师';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fenxishi`
--

LOCK TABLES `fenxishi` WRITE;
/*!40000 ALTER TABLE `fenxishi` DISABLE KEYS */;
INSERT INTO `fenxishi` VALUES (51,'2025-03-03 14:28:32','分析师号1','分析师名1','123456','男','13823888881'),(52,'2025-03-03 14:28:32','分析师号2','分析师名2','123456','男','13823888882'),(53,'2025-03-03 14:28:32','分析师号3','分析师名3','123456','男','13823888883'),(54,'2025-03-03 14:28:32','分析师号4','分析师名4','123456','男','13823888884'),(55,'2025-03-03 14:28:32','分析师号5','分析师名5','123456','男','13823888885'),(56,'2025-03-03 14:28:32','分析师号6','分析师名6','123456','男','13823888886'),(57,'2025-03-03 14:28:32','分析师号7','分析师名7','123456','男','13823888887'),(58,'2025-03-03 14:28:32','分析师号8','分析师名8','123456','男','13823888888');
/*!40000 ALTER TABLE `fenxishi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kaifarenyuan`
--

DROP TABLE IF EXISTS `kaifarenyuan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kaifarenyuan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `kaifazhanghao` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '开发账号',
  `kaifaxingming` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '开发姓名',
  `mima` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `xingbie` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '性别',
  `kaifadianhua` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '开发电话',
  `tuanduimingcheng` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '团队名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `kaifazhanghao` (`kaifazhanghao`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='开发人员';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kaifarenyuan`
--

LOCK TABLES `kaifarenyuan` WRITE;
/*!40000 ALTER TABLE `kaifarenyuan` DISABLE KEYS */;
INSERT INTO `kaifarenyuan` VALUES (41,'2025-03-03 14:28:32','开发账号1','开发姓名1','123456','男','13823888881','团队名称1'),(42,'2025-03-03 14:28:32','开发账号2','开发姓名2','123456','男','13823888882','团队名称2'),(43,'2025-03-03 14:28:32','开发账号3','开发姓名3','123456','男','13823888883','团队名称3'),(44,'2025-03-03 14:28:32','开发账号4','开发姓名4','123456','男','13823888884','团队名称4'),(45,'2025-03-03 14:28:32','开发账号5','开发姓名5','123456','男','13823888885','团队名称5'),(46,'2025-03-03 14:28:32','开发账号6','开发姓名6','123456','男','13823888886','团队名称6'),(47,'2025-03-03 14:28:32','开发账号7','开发姓名7','123456','男','13823888887','团队名称7'),(48,'2025-03-03 14:28:32','开发账号8','开发姓名8','123456','男','13823888888','团队名称8');
/*!40000 ALTER TABLE `kaifarenyuan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `menujson` longtext COLLATE utf8mb4_unicode_ci COMMENT '菜单',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='菜单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,'2025-03-03 14:28:33','[{\"backMenu\":[{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-explore\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"menu\":\"项目经理\",\"menuJump\":\"列表\",\"tableName\":\"xiangmujingli\"}],\"menu\":\"项目经理管理\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-goods\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"menu\":\"测试经理\",\"menuJump\":\"列表\",\"tableName\":\"ceshijingli\"}],\"menu\":\"测试经理管理\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-copy\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"menu\":\"测试工程师\",\"menuJump\":\"列表\",\"tableName\":\"ceshigongchengshi\"}],\"menu\":\"测试工程师管理\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-qrcode\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"menu\":\"开发人员\",\"menuJump\":\"列表\",\"tableName\":\"kaifarenyuan\"}],\"menu\":\"开发人员管理\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-form\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"menu\":\"QA分析师\",\"menuJump\":\"列表\",\"tableName\":\"fenxishi\"}],\"menu\":\"QA分析师管理\"},{\"child\":[{\"allButtons\":[\"查看\",\"修改\"],\"appFrontIcon\":\"cuIcon-keyboard\",\"buttons\":[\"查看\",\"修改\"],\"menu\":\"系统公告\",\"tableName\":\"systemnotice\"},{\"allButtons\":[\"查看\",\"编辑名称\",\"编辑父级\",\"编辑权限\",\"删除\"],\"appFrontIcon\":\"cuIcon-discover\",\"buttons\":[\"查看\",\"编辑名称\",\"编辑父级\",\"编辑权限\",\"删除\"],\"menu\":\"菜单列表\",\"tableName\":\"menu\"}],\"menu\":\"系统管理\"}],\"frontMenu\":[],\"hasBackLogin\":\"是\",\"hasBackRegister\":\"否\",\"hasFrontLogin\":\"否\",\"hasFrontRegister\":\"否\",\"roleName\":\"管理员\",\"tableName\":\"users\"},{\"backMenu\":[{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"进度登记\",\"测试计划\",\"资源分配\"],\"appFrontIcon\":\"cuIcon-skin\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"进度登记\",\"资源分配\"],\"menu\":\"项目信息\",\"menuJump\":\"列表\",\"tableName\":\"xiangmuxinxi\"}],\"menu\":\"项目信息管理\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-goodsnew\",\"buttons\":[\"查看\",\"删除\",\"修改\"],\"menu\":\"项目进度\",\"menuJump\":\"列表\",\"tableName\":\"xiangmujindu\"}],\"menu\":\"项目进度管理\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-rank\",\"buttons\":[\"查看\",\"修改\",\"删除\"],\"menu\":\"资源分配\",\"menuJump\":\"列表\",\"tableName\":\"ziyuanfenpei\"}],\"menu\":\"资源分配管理\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"测试任务\"],\"appFrontIcon\":\"cuIcon-flashlightopen\",\"buttons\":[\"查看\"],\"menu\":\"测试计划\",\"menuJump\":\"列表\",\"tableName\":\"ceshijihua\"}],\"menu\":\"测试计划管理\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-time\",\"buttons\":[\"查看\"],\"menu\":\"测试团队\",\"menuJump\":\"列表\",\"tableName\":\"ceshituandui\"}],\"menu\":\"测试团队管理\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-goods\",\"buttons\":[\"查看\"],\"menu\":\"测试活动\",\"menuJump\":\"列表\",\"tableName\":\"ceshihuodong\"}],\"menu\":\"测试活动管理\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"执行记录\"],\"appFrontIcon\":\"cuIcon-rank\",\"buttons\":[\"查看\"],\"menu\":\"测试任务\",\"menuJump\":\"列表\",\"tableName\":\"ceshirenwu\"}],\"menu\":\"测试任务管理\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"修复改进\"],\"appFrontIcon\":\"cuIcon-explore\",\"buttons\":[\"查看\"],\"menu\":\"执行任务\",\"menuJump\":\"列表\",\"tableName\":\"zhixingrenwu\"}],\"menu\":\"执行任务管理\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"测试分析\"],\"appFrontIcon\":\"cuIcon-discover\",\"buttons\":[\"查看\"],\"menu\":\"修复改进\",\"menuJump\":\"列表\",\"tableName\":\"xiufugaijin\"}],\"menu\":\"修复改进管理\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"测试结果情况\",\"首页总数\",\"首页统计\"],\"appFrontIcon\":\"cuIcon-news\",\"buttons\":[\"查看\"],\"menu\":\"测试分析\",\"menuJump\":\"列表\",\"tableName\":\"ceshifenxi\"}],\"menu\":\"测试分析管理\"}],\"frontMenu\":[],\"hasBackLogin\":\"是\",\"hasBackRegister\":\"否\",\"hasFrontLogin\":\"否\",\"hasFrontRegister\":\"否\",\"roleName\":\"项目经理\",\"tableName\":\"xiangmujingli\"},{\"backMenu\":[{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"进度登记\",\"测试计划\",\"资源分配\"],\"appFrontIcon\":\"cuIcon-skin\",\"buttons\":[\"查看\",\"测试计划\"],\"menu\":\"项目信息\",\"menuJump\":\"列表\",\"tableName\":\"xiangmuxinxi\"}],\"menu\":\"项目信息管理\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-rank\",\"buttons\":[\"查看\"],\"menu\":\"资源分配\",\"menuJump\":\"列表\",\"tableName\":\"ziyuanfenpei\"}],\"menu\":\"资源分配管理\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"测试任务\"],\"appFrontIcon\":\"cuIcon-flashlightopen\",\"buttons\":[\"查看\",\"删除\",\"测试任务\",\"修改\"],\"menu\":\"测试计划\",\"menuJump\":\"列表\",\"tableName\":\"ceshijihua\"}],\"menu\":\"测试计划管理\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-time\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"menu\":\"测试团队\",\"menuJump\":\"列表\",\"tableName\":\"ceshituandui\"}],\"menu\":\"测试团队管理\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-goods\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"menu\":\"测试活动\",\"menuJump\":\"列表\",\"tableName\":\"ceshihuodong\"}],\"menu\":\"测试活动管理\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"执行记录\"],\"appFrontIcon\":\"cuIcon-rank\",\"buttons\":[\"查看\",\"删除\",\"修改\"],\"menu\":\"测试任务\",\"menuJump\":\"列表\",\"tableName\":\"ceshirenwu\"}],\"menu\":\"测试任务管理\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"修复改进\"],\"appFrontIcon\":\"cuIcon-explore\",\"buttons\":[\"查看\"],\"menu\":\"执行任务\",\"menuJump\":\"列表\",\"tableName\":\"zhixingrenwu\"}],\"menu\":\"执行任务管理\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"测试分析\"],\"appFrontIcon\":\"cuIcon-discover\",\"buttons\":[\"查看\"],\"menu\":\"修复改进\",\"menuJump\":\"列表\",\"tableName\":\"xiufugaijin\"}],\"menu\":\"修复改进管理\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"测试结果情况\",\"首页总数\",\"首页统计\"],\"appFrontIcon\":\"cuIcon-news\",\"buttons\":[\"查看\"],\"menu\":\"测试分析\",\"menuJump\":\"列表\",\"tableName\":\"ceshifenxi\"}],\"menu\":\"测试分析管理\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-album\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"menu\":\"测试用例\",\"menuJump\":\"列表\",\"tableName\":\"ceshiyongli\"}],\"menu\":\"测试用例管理\"}],\"frontMenu\":[],\"hasBackLogin\":\"是\",\"hasBackRegister\":\"否\",\"hasFrontLogin\":\"否\",\"hasFrontRegister\":\"否\",\"roleName\":\"测试经理\",\"tableName\":\"ceshijingli\"},{\"backMenu\":[{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-rank\",\"buttons\":[\"查看\"],\"menu\":\"资源分配\",\"menuJump\":\"列表\",\"tableName\":\"ziyuanfenpei\"}],\"menu\":\"资源分配管理\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"测试任务\"],\"appFrontIcon\":\"cuIcon-flashlightopen\",\"buttons\":[\"查看\"],\"menu\":\"测试计划\",\"menuJump\":\"列表\",\"tableName\":\"ceshijihua\"}],\"menu\":\"测试计划管理\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-time\",\"buttons\":[\"查看\"],\"menu\":\"测试团队\",\"menuJump\":\"列表\",\"tableName\":\"ceshituandui\"}],\"menu\":\"测试团队管理\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-goods\",\"buttons\":[\"查看\"],\"menu\":\"测试活动\",\"menuJump\":\"列表\",\"tableName\":\"ceshihuodong\"}],\"menu\":\"测试活动管理\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"执行记录\"],\"appFrontIcon\":\"cuIcon-rank\",\"buttons\":[\"查看\",\"执行记录\"],\"menu\":\"测试任务\",\"menuJump\":\"列表\",\"tableName\":\"ceshirenwu\"}],\"menu\":\"测试任务管理\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"修复改进\"],\"appFrontIcon\":\"cuIcon-explore\",\"buttons\":[\"查看\",\"删除\"],\"menu\":\"执行任务\",\"menuJump\":\"列表\",\"tableName\":\"zhixingrenwu\"}],\"menu\":\"执行任务管理\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-album\",\"buttons\":[\"查看\"],\"menu\":\"测试用例\",\"menuJump\":\"列表\",\"tableName\":\"ceshiyongli\"}],\"menu\":\"测试用例管理\"}],\"frontMenu\":[],\"hasBackLogin\":\"是\",\"hasBackRegister\":\"否\",\"hasFrontLogin\":\"否\",\"hasFrontRegister\":\"否\",\"roleName\":\"测试工程师\",\"tableName\":\"ceshigongchengshi\"},{\"backMenu\":[{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-rank\",\"buttons\":[\"查看\"],\"menu\":\"资源分配\",\"menuJump\":\"列表\",\"tableName\":\"ziyuanfenpei\"}],\"menu\":\"资源分配管理\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-goods\",\"buttons\":[\"查看\"],\"menu\":\"测试活动\",\"menuJump\":\"列表\",\"tableName\":\"ceshihuodong\"}],\"menu\":\"测试活动管理\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"修复改进\"],\"appFrontIcon\":\"cuIcon-explore\",\"buttons\":[\"查看\",\"修复改进\"],\"menu\":\"执行任务\",\"menuJump\":\"列表\",\"tableName\":\"zhixingrenwu\"}],\"menu\":\"执行任务管理\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"测试分析\"],\"appFrontIcon\":\"cuIcon-discover\",\"buttons\":[\"查看\",\"删除\",\"修改\"],\"menu\":\"修复改进\",\"menuJump\":\"列表\",\"tableName\":\"xiufugaijin\"}],\"menu\":\"修复改进管理\"}],\"frontMenu\":[],\"hasBackLogin\":\"是\",\"hasBackRegister\":\"否\",\"hasFrontLogin\":\"否\",\"hasFrontRegister\":\"否\",\"roleName\":\"开发人员\",\"tableName\":\"kaifarenyuan\"},{\"backMenu\":[{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-rank\",\"buttons\":[\"查看\"],\"menu\":\"资源分配\",\"menuJump\":\"列表\",\"tableName\":\"ziyuanfenpei\"}],\"menu\":\"资源分配管理\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-goods\",\"buttons\":[\"查看\"],\"menu\":\"测试活动\",\"menuJump\":\"列表\",\"tableName\":\"ceshihuodong\"}],\"menu\":\"测试活动管理\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"测试分析\"],\"appFrontIcon\":\"cuIcon-discover\",\"buttons\":[\"查看\",\"测试分析\"],\"menu\":\"修复改进\",\"menuJump\":\"列表\",\"tableName\":\"xiufugaijin\"}],\"menu\":\"修复改进管理\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"测试结果情况\",\"首页总数\",\"首页统计\"],\"appFrontIcon\":\"cuIcon-news\",\"buttons\":[\"查看\",\"删除\",\"首页总数\",\"首页统计\"],\"menu\":\"测试分析\",\"menuJump\":\"列表\",\"tableName\":\"ceshifenxi\"}],\"menu\":\"测试分析管理\"}],\"frontMenu\":[],\"hasBackLogin\":\"是\",\"hasBackRegister\":\"否\",\"hasFrontLogin\":\"否\",\"hasFrontRegister\":\"否\",\"roleName\":\"QA分析师\",\"tableName\":\"fenxishi\"}]');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `systemnotice`
--

DROP TABLE IF EXISTS `systemnotice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `systemnotice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统公告';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `systemnotice`
--

LOCK TABLES `systemnotice` WRITE;
/*!40000 ALTER TABLE `systemnotice` DISABLE KEYS */;
INSERT INTO `systemnotice` VALUES (1,'2025-03-03 14:28:33','这是系统公告');
/*!40000 ALTER TABLE `systemnotice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `tablename` varchar(100) DEFAULT NULL COMMENT '表名',
  `role` varchar(100) DEFAULT NULL COMMENT '角色',
  `token` varchar(200) NOT NULL COMMENT '密码',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `expiratedtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='token表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES (1,1,'admin','users','管理员','fnte7oqe9pn94nnugncqjv0ys52l8o38','2025-03-03 14:30:50','2025-03-03 15:34:01'),(2,38,'工程师号8','ceshigongchengshi','测试工程师','vr8g24kuqwk8jf2kv1cvr99gbyem8xxh','2025-03-03 14:31:20','2025-03-03 15:31:21'),(3,31,'工程师号1','ceshigongchengshi','测试工程师','mctavnxb90hy59u5z9tumuj3qvu4zcrm','2025-03-03 14:32:17','2025-03-03 15:36:02'),(4,21,'经理账号1','ceshijingli','测试经理','iavm9apqusq7qpoxqbshcez4g7jhk3al','2025-03-03 14:32:55','2025-03-03 15:35:22'),(5,18,'经理工号8','xiangmujingli','项目经理','su18bi56s8l7o6invncvzgfecdxu9cb2','2025-03-03 14:34:13','2025-03-03 15:34:14');
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `image` varchar(200) DEFAULT NULL COMMENT '头像',
  `role` varchar(100) DEFAULT '管理员' COMMENT '角色',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='管理员表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','admin','upload/image1.jpg','管理员','2025-03-03 14:28:33');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xiangmujindu`
--

DROP TABLE IF EXISTS `xiangmujindu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xiangmujindu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `xiangmubianhao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目编号',
  `xiangmumingcheng` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目名称',
  `xiangmuleixing` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目类型',
  `xiangmujindu` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '项目进度',
  `jinduqingkuang` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '进度情况',
  `jindubaogao` longtext COLLATE utf8mb4_unicode_ci COMMENT '进度报告',
  `shangchuanshijian` datetime DEFAULT NULL COMMENT '上传时间',
  `jinglimingcheng` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '经理名称',
  `jingligonghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '经理工号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='项目进度';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xiangmujindu`
--

LOCK TABLES `xiangmujindu` WRITE;
/*!40000 ALTER TABLE `xiangmujindu` DISABLE KEYS */;
INSERT INTO `xiangmujindu` VALUES (1,'2025-03-03 14:28:32','项目编号1','项目名称1','项目类型1','10%','进度情况1','','2025-03-03 22:28:32','经理名称1','经理工号1'),(2,'2025-03-03 14:28:32','项目编号2','项目名称2','项目类型2','10%','进度情况2','','2025-03-03 22:28:32','经理名称2','经理工号2'),(3,'2025-03-03 14:28:32','项目编号3','项目名称3','项目类型3','10%','进度情况3','','2025-03-03 22:28:32','经理名称3','经理工号3'),(4,'2025-03-03 14:28:32','项目编号4','项目名称4','项目类型4','10%','进度情况4','','2025-03-03 22:28:32','经理名称4','经理工号4'),(5,'2025-03-03 14:28:32','项目编号5','项目名称5','项目类型5','10%','进度情况5','','2025-03-03 22:28:32','经理名称5','经理工号5'),(6,'2025-03-03 14:28:32','项目编号6','项目名称6','项目类型6','10%','进度情况6','','2025-03-03 22:28:32','经理名称6','经理工号6'),(7,'2025-03-03 14:28:32','项目编号7','项目名称7','项目类型7','10%','进度情况7','','2025-03-03 22:28:32','经理名称7','经理工号7'),(8,'2025-03-03 14:28:32','项目编号8','项目名称8','项目类型8','10%','进度情况8','','2025-03-03 22:28:32','经理名称8','经理工号8');
/*!40000 ALTER TABLE `xiangmujindu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xiangmujingli`
--

DROP TABLE IF EXISTS `xiangmujingli`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xiangmujingli` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `jingligonghao` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '经理工号',
  `mima` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `jinglimingcheng` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '经理名称',
  `xingbie` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '性别',
  `lianxifangshi` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '联系方式',
  `touxiang` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  PRIMARY KEY (`id`),
  UNIQUE KEY `jingligonghao` (`jingligonghao`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='项目经理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xiangmujingli`
--

LOCK TABLES `xiangmujingli` WRITE;
/*!40000 ALTER TABLE `xiangmujingli` DISABLE KEYS */;
INSERT INTO `xiangmujingli` VALUES (11,'2025-03-03 14:28:32','经理工号1','123456','经理名称1','男','13823888881','upload/xiangmujingli_touxiang1.jpg'),(12,'2025-03-03 14:28:32','经理工号2','123456','经理名称2','男','13823888882','upload/xiangmujingli_touxiang2.jpg'),(13,'2025-03-03 14:28:32','经理工号3','123456','经理名称3','男','13823888883','upload/xiangmujingli_touxiang3.jpg'),(14,'2025-03-03 14:28:32','经理工号4','123456','经理名称4','男','13823888884','upload/xiangmujingli_touxiang4.jpg'),(15,'2025-03-03 14:28:32','经理工号5','123456','经理名称5','男','13823888885','upload/xiangmujingli_touxiang5.jpg'),(16,'2025-03-03 14:28:32','经理工号6','123456','经理名称6','男','13823888886','upload/xiangmujingli_touxiang6.jpg'),(17,'2025-03-03 14:28:32','经理工号7','123456','经理名称7','男','13823888887','upload/xiangmujingli_touxiang7.jpg'),(18,'2025-03-03 14:28:32','经理工号8','123456','经理名称8','男','13823888888','upload/xiangmujingli_touxiang8.jpg');
/*!40000 ALTER TABLE `xiangmujingli` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xiangmuxinxi`
--

DROP TABLE IF EXISTS `xiangmuxinxi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xiangmuxinxi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `xiangmubianhao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目编号',
  `xiangmumingcheng` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '项目名称',
  `xiangmuleixing` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '项目类型',
  `xiangmuxiangqing` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '项目详情',
  `wendang` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文档',
  `beizhu` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '备注',
  `dengjishijian` date DEFAULT NULL COMMENT '登记时间',
  `jingligonghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '经理工号',
  `jinglimingcheng` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '经理名称',
  `xiangmuguihua` longtext COLLATE utf8mb4_unicode_ci COMMENT '项目规划',
  PRIMARY KEY (`id`),
  UNIQUE KEY `xiangmubianhao` (`xiangmubianhao`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='项目信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xiangmuxinxi`
--

LOCK TABLES `xiangmuxinxi` WRITE;
/*!40000 ALTER TABLE `xiangmuxinxi` DISABLE KEYS */;
INSERT INTO `xiangmuxinxi` VALUES (1,'2025-03-03 14:28:32','1111111111','项目名称1','项目类型1','项目详情1','','备注1','2025-03-03','经理工号1','经理名称1','项目规划1'),(2,'2025-03-03 14:28:32','2222222222','项目名称2','项目类型2','项目详情2','','备注2','2025-03-03','经理工号2','经理名称2','项目规划2'),(3,'2025-03-03 14:28:32','3333333333','项目名称3','项目类型3','项目详情3','','备注3','2025-03-03','经理工号3','经理名称3','项目规划3'),(4,'2025-03-03 14:28:32','4444444444','项目名称4','项目类型4','项目详情4','','备注4','2025-03-03','经理工号4','经理名称4','项目规划4'),(5,'2025-03-03 14:28:32','5555555555','项目名称5','项目类型5','项目详情5','','备注5','2025-03-03','经理工号5','经理名称5','项目规划5'),(6,'2025-03-03 14:28:32','6666666666','项目名称6','项目类型6','项目详情6','','备注6','2025-03-03','经理工号6','经理名称6','项目规划6'),(7,'2025-03-03 14:28:32','7777777777','项目名称7','项目类型7','项目详情7','','备注7','2025-03-03','经理工号7','经理名称7','项目规划7'),(8,'2025-03-03 14:28:32','8888888888','项目名称8','项目类型8','项目详情8','','备注8','2025-03-03','经理工号8','经理名称8','项目规划8');
/*!40000 ALTER TABLE `xiangmuxinxi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xiufugaijin`
--

DROP TABLE IF EXISTS `xiufugaijin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xiufugaijin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `xiangmubianhao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目编号',
  `renwumingcheng` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '任务名称',
  `xiangmumingcheng` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '项目名称',
  `xiangmuleixing` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目类型',
  `ceshiyongli` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '测试用例',
  `zhixingshunxu` longtext COLLATE utf8mb4_unicode_ci COMMENT '执行顺序',
  `zhixingtiaojian` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '执行条件',
  `jinglizhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '经理账号',
  `jinglixingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '经理姓名',
  `tuanduimingcheng` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '团队名称',
  `gongchengshihao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '工程师号',
  `gongchengshiming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '工程师名',
  `quexianxiufu` longtext COLLATE utf8mb4_unicode_ci COMMENT '缺陷修复',
  `daimagaijin` longtext COLLATE utf8mb4_unicode_ci COMMENT '代码改进',
  `kaifazhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '开发账号',
  `kaifaxingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '开发姓名',
  `gaijinshijian` datetime DEFAULT NULL COMMENT '改进时间',
  `ceshizhuangtai` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '测试状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='修复改进';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xiufugaijin`
--

LOCK TABLES `xiufugaijin` WRITE;
/*!40000 ALTER TABLE `xiufugaijin` DISABLE KEYS */;
INSERT INTO `xiufugaijin` VALUES (1,'2025-03-03 14:28:33','项目编号1','任务名称1','项目名称1','项目类型1','测试用例1','执行顺序1','执行条件1','经理账号1','经理姓名1','团队名称1','工程师号1','工程师名1','缺陷修复1','代码改进1','开发账号1','开发姓名1','2025-03-03 22:28:33','已测试'),(2,'2025-03-03 14:28:33','项目编号2','任务名称2','项目名称2','项目类型2','测试用例2','执行顺序2','执行条件2','经理账号2','经理姓名2','团队名称2','工程师号2','工程师名2','缺陷修复2','代码改进2','开发账号2','开发姓名2','2025-03-03 22:28:33','已测试'),(3,'2025-03-03 14:28:33','项目编号3','任务名称3','项目名称3','项目类型3','测试用例3','执行顺序3','执行条件3','经理账号3','经理姓名3','团队名称3','工程师号3','工程师名3','缺陷修复3','代码改进3','开发账号3','开发姓名3','2025-03-03 22:28:33','已测试'),(4,'2025-03-03 14:28:33','项目编号4','任务名称4','项目名称4','项目类型4','测试用例4','执行顺序4','执行条件4','经理账号4','经理姓名4','团队名称4','工程师号4','工程师名4','缺陷修复4','代码改进4','开发账号4','开发姓名4','2025-03-03 22:28:33','已测试'),(5,'2025-03-03 14:28:33','项目编号5','任务名称5','项目名称5','项目类型5','测试用例5','执行顺序5','执行条件5','经理账号5','经理姓名5','团队名称5','工程师号5','工程师名5','缺陷修复5','代码改进5','开发账号5','开发姓名5','2025-03-03 22:28:33','已测试'),(6,'2025-03-03 14:28:33','项目编号6','任务名称6','项目名称6','项目类型6','测试用例6','执行顺序6','执行条件6','经理账号6','经理姓名6','团队名称6','工程师号6','工程师名6','缺陷修复6','代码改进6','开发账号6','开发姓名6','2025-03-03 22:28:33','已测试'),(7,'2025-03-03 14:28:33','项目编号7','任务名称7','项目名称7','项目类型7','测试用例7','执行顺序7','执行条件7','经理账号7','经理姓名7','团队名称7','工程师号7','工程师名7','缺陷修复7','代码改进7','开发账号7','开发姓名7','2025-03-03 22:28:33','已测试'),(8,'2025-03-03 14:28:33','项目编号8','任务名称8','项目名称8','项目类型8','测试用例8','执行顺序8','执行条件8','经理账号8','经理姓名8','团队名称8','工程师号8','工程师名8','缺陷修复8','代码改进8','开发账号8','开发姓名8','2025-03-03 22:28:33','已测试');
/*!40000 ALTER TABLE `xiufugaijin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zhixingrenwu`
--

DROP TABLE IF EXISTS `zhixingrenwu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zhixingrenwu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `xiangmubianhao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目编号',
  `renwumingcheng` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '任务名称',
  `xiangmumingcheng` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '项目名称',
  `xiangmuleixing` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目类型',
  `ceshiyongli` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '测试用例',
  `zhixingshunxu` longtext COLLATE utf8mb4_unicode_ci COMMENT '执行顺序',
  `zhixingtiaojian` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '执行条件',
  `zhixingshijian` datetime DEFAULT NULL COMMENT '执行时间',
  `jinglizhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '经理账号',
  `jinglixingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '经理姓名',
  `tuanduimingcheng` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '团队名称',
  `gongchengshihao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '工程师号',
  `gongchengshiming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '工程师名',
  `ceshijieguo` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '测试结果',
  `baogaoquexian` longtext COLLATE utf8mb4_unicode_ci COMMENT '报告缺陷',
  `zhuangtai` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='执行任务';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zhixingrenwu`
--

LOCK TABLES `zhixingrenwu` WRITE;
/*!40000 ALTER TABLE `zhixingrenwu` DISABLE KEYS */;
INSERT INTO `zhixingrenwu` VALUES (1,'2025-03-03 14:28:33','项目编号1','任务名称1','项目名称1','项目类型1','测试用例1','执行顺序1','执行条件1','2025-03-03 22:28:33','经理账号1','经理姓名1','团队名称1','工程师号1','工程师名1','','','已修复'),(2,'2025-03-03 14:28:33','项目编号2','任务名称2','项目名称2','项目类型2','测试用例2','执行顺序2','执行条件2','2025-03-03 22:28:33','经理账号2','经理姓名2','团队名称2','工程师号2','工程师名2','','','已修复'),(3,'2025-03-03 14:28:33','项目编号3','任务名称3','项目名称3','项目类型3','测试用例3','执行顺序3','执行条件3','2025-03-03 22:28:33','经理账号3','经理姓名3','团队名称3','工程师号3','工程师名3','','','已修复'),(4,'2025-03-03 14:28:33','项目编号4','任务名称4','项目名称4','项目类型4','测试用例4','执行顺序4','执行条件4','2025-03-03 22:28:33','经理账号4','经理姓名4','团队名称4','工程师号4','工程师名4','','','已修复'),(5,'2025-03-03 14:28:33','项目编号5','任务名称5','项目名称5','项目类型5','测试用例5','执行顺序5','执行条件5','2025-03-03 22:28:33','经理账号5','经理姓名5','团队名称5','工程师号5','工程师名5','','','已修复'),(6,'2025-03-03 14:28:33','项目编号6','任务名称6','项目名称6','项目类型6','测试用例6','执行顺序6','执行条件6','2025-03-03 22:28:33','经理账号6','经理姓名6','团队名称6','工程师号6','工程师名6','','','已修复'),(7,'2025-03-03 14:28:33','项目编号7','任务名称7','项目名称7','项目类型7','测试用例7','执行顺序7','执行条件7','2025-03-03 22:28:33','经理账号7','经理姓名7','团队名称7','工程师号7','工程师名7','','','已修复'),(8,'2025-03-03 14:28:33','项目编号8','任务名称8','项目名称8','项目类型8','测试用例8','执行顺序8','执行条件8','2025-03-03 22:28:33','经理账号8','经理姓名8','团队名称8','工程师号8','工程师名8','','','已修复');
/*!40000 ALTER TABLE `zhixingrenwu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ziyuanfenpei`
--

DROP TABLE IF EXISTS `ziyuanfenpei`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ziyuanfenpei` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `xiangmubianhao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目编号',
  `xiangmumingcheng` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目名称',
  `xiangmuleixing` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目类型',
  `ziyuanmingcheng` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源名称',
  `fenpeishuliang` int(11) DEFAULT NULL COMMENT '分配数量',
  `beizhu` longtext COLLATE utf8mb4_unicode_ci COMMENT '备注',
  `zijin` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资金',
  `fenpeirenyuan` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分配人员',
  `fenpeishijian` datetime DEFAULT NULL COMMENT '分配时间',
  `jingligonghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '经理工号',
  `jinglimingcheng` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '经理名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='资源分配';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ziyuanfenpei`
--

LOCK TABLES `ziyuanfenpei` WRITE;
/*!40000 ALTER TABLE `ziyuanfenpei` DISABLE KEYS */;
INSERT INTO `ziyuanfenpei` VALUES (1,'2025-03-03 14:28:32','项目编号1','项目名称1','项目类型1','资源名称1',1,'备注1','资金1','分配人员1','2025-03-03 22:28:32','经理工号1','经理名称1'),(2,'2025-03-03 14:28:32','项目编号2','项目名称2','项目类型2','资源名称2',2,'备注2','资金2','分配人员2','2025-03-03 22:28:32','经理工号2','经理名称2'),(3,'2025-03-03 14:28:32','项目编号3','项目名称3','项目类型3','资源名称3',3,'备注3','资金3','分配人员3','2025-03-03 22:28:32','经理工号3','经理名称3'),(4,'2025-03-03 14:28:32','项目编号4','项目名称4','项目类型4','资源名称4',4,'备注4','资金4','分配人员4','2025-03-03 22:28:32','经理工号4','经理名称4'),(5,'2025-03-03 14:28:32','项目编号5','项目名称5','项目类型5','资源名称5',5,'备注5','资金5','分配人员5','2025-03-03 22:28:32','经理工号5','经理名称5'),(6,'2025-03-03 14:28:32','项目编号6','项目名称6','项目类型6','资源名称6',6,'备注6','资金6','分配人员6','2025-03-03 22:28:32','经理工号6','经理名称6'),(7,'2025-03-03 14:28:32','项目编号7','项目名称7','项目类型7','资源名称7',7,'备注7','资金7','分配人员7','2025-03-03 22:28:32','经理工号7','经理名称7'),(8,'2025-03-03 14:28:32','项目编号8','项目名称8','项目类型8','资源名称8',8,'备注8','资金8','分配人员8','2025-03-03 22:28:32','经理工号8','经理名称8');
/*!40000 ALTER TABLE `ziyuanfenpei` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-04 15:18:47
