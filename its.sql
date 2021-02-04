-- MySQL dump 10.13  Distrib 5.7.30, for macos10.14 (x86_64)
--
-- Host: localhost    Database: its
-- ------------------------------------------------------
-- Server version	5.7.30

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
-- Current Database: `its`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `its` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `its`;

--
-- Table structure for table `eve_event`
--

DROP TABLE IF EXISTS `eve_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eve_event` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `place` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `camera_id` varchar(255) DEFAULT NULL,
  `vehicle_id` varchar(255) DEFAULT NULL,
  `violation_id` varchar(255) DEFAULT NULL,
  `vehicle_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs6ftkjagbnlrgkuw4cjn3sehc` (`camera_id`),
  KEY `FKkkwmjljmrrx45ng53lw3annwo` (`vehicle_id`),
  KEY `FKfs1jwhcxqlbujxpc1eip4n4hq` (`violation_id`),
  CONSTRAINT `FKfs1jwhcxqlbujxpc1eip4n4hq` FOREIGN KEY (`violation_id`) REFERENCES `sys_violation` (`id`),
  CONSTRAINT `FKkkwmjljmrrx45ng53lw3annwo` FOREIGN KEY (`vehicle_id`) REFERENCES `sys_vehicle` (`id`),
  CONSTRAINT `FKs6ftkjagbnlrgkuw4cjn3sehc` FOREIGN KEY (`camera_id`) REFERENCES `sys_camera` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eve_event`
--

LOCK TABLES `eve_event` WRITE;
/*!40000 ALTER TABLE `eve_event` DISABLE KEYS */;
INSERT INTO `eve_event` VALUES ('395cde7c-433d-4e50-86d1-778f2623a4d9',NULL,'哈工大保卫处',0,NULL,NULL,NULL,'青B.00000'),('fe0d1b9e-e923-46c9-9e1f-7c33339924a7',NULL,'哈工大保卫处',0,NULL,NULL,NULL,'青B.00000');
/*!40000 ALTER TABLE `eve_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eve_record`
--

DROP TABLE IF EXISTS `eve_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eve_record` (
  `id` varchar(255) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime(6) NOT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime(6) NOT NULL,
  `record` int(11) DEFAULT NULL,
  `college_id` varchar(255) DEFAULT NULL,
  `event_id` varchar(255) DEFAULT NULL,
  `score_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt4gs77cya4yvb7u5xnhe7kulk` (`college_id`),
  KEY `FKgbh9o426n2fwfsfifqtyfom8j` (`event_id`),
  KEY `FKoaies9yh8fbrxtskkoixvmg1r` (`score_id`),
  CONSTRAINT `FKgbh9o426n2fwfsfifqtyfom8j` FOREIGN KEY (`event_id`) REFERENCES `eve_event` (`id`),
  CONSTRAINT `FKoaies9yh8fbrxtskkoixvmg1r` FOREIGN KEY (`score_id`) REFERENCES `sys_score` (`id`),
  CONSTRAINT `FKt4gs77cya4yvb7u5xnhe7kulk` FOREIGN KEY (`college_id`) REFERENCES `sys_college` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eve_record`
--

LOCK TABLES `eve_record` WRITE;
/*!40000 ALTER TABLE `eve_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `eve_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `par_park_lot`
--

DROP TABLE IF EXISTS `par_park_lot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `par_park_lot` (
  `id` varchar(255) NOT NULL,
  `create_time` datetime(6) NOT NULL,
  `update_time` datetime(6) NOT NULL,
  `count` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdggnfx8ubeefwq5c8y6oajbtw` (`create_by`),
  KEY `FK4pmqg8175jw780tgapef1ek64` (`update_by`),
  CONSTRAINT `FK4pmqg8175jw780tgapef1ek64` FOREIGN KEY (`update_by`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FKdggnfx8ubeefwq5c8y6oajbtw` FOREIGN KEY (`create_by`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `par_park_lot`
--

LOCK TABLES `par_park_lot` WRITE;
/*!40000 ALTER TABLE `par_park_lot` DISABLE KEYS */;
INSERT INTO `par_park_lot` VALUES ('24443b28-a611-40eb-b6aa-9f4426cf799c','2021-01-21 11:36:40.798658','2021-01-21 11:36:40.798658',30,'停车场3','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114'),('8cdd7104-7625-4f5f-9bfc-6c63ae9cef9d','2021-01-21 11:36:29.377397','2021-01-21 11:36:29.377397',20,'停车场2','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114'),('ab839f0b-a532-4722-9316-84525ed3565a','2021-01-21 11:34:53.207313','2021-01-21 11:39:30.885447',10,'停车场1','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114');
/*!40000 ALTER TABLE `par_park_lot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_camera`
--

DROP TABLE IF EXISTS `sys_camera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_camera` (
  `id` varchar(255) NOT NULL,
  `create_time` datetime(6) NOT NULL,
  `update_time` datetime(6) NOT NULL,
  `factory` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `region_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6xruvswvux376gkk3wt6h06tc` (`create_by`),
  KEY `FKef96kal50w9ylrdgjc8gkbc9j` (`update_by`),
  KEY `FK65votwhc893dr3pq4xo4vp6jt` (`region_id`),
  CONSTRAINT `FK65votwhc893dr3pq4xo4vp6jt` FOREIGN KEY (`region_id`) REFERENCES `sys_region` (`id`),
  CONSTRAINT `FK6xruvswvux376gkk3wt6h06tc` FOREIGN KEY (`create_by`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FKef96kal50w9ylrdgjc8gkbc9j` FOREIGN KEY (`update_by`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_camera`
--

LOCK TABLES `sys_camera` WRITE;
/*!40000 ALTER TABLE `sys_camera` DISABLE KEYS */;
INSERT INTO `sys_camera` VALUES ('580d15d3-d433-47a0-8d19-7e5ff7f0319d','2021-01-20 10:26:47.829923','2021-01-20 11:58:41.884328','厂家2','192.168.3.85','摄像头2',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','779d6d9b-fa7f-4eac-8d22-3c7020c1c5c0'),('cf139bd8-2188-4bd6-94dd-b588ae4660b9','2021-01-20 10:21:42.231205','2021-01-26 16:38:52.957634','厂家1222','127.0.0.0','摄像头名称1',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','aa8908a9-d2ad-40c7-ba3a-4630e08d9d1f');
/*!40000 ALTER TABLE `sys_camera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_college`
--

DROP TABLE IF EXISTS `sys_college`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_college` (
  `id` varchar(255) NOT NULL,
  `create_time` datetime(6) NOT NULL,
  `update_time` datetime(6) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlivjm5nuqf1tvrvmsdsta6oe1` (`create_by`),
  KEY `FK1op0tlni76jj2krhl0roc6rx` (`update_by`),
  KEY `FK2gcg73krc9bi6e816wq6dhqlb` (`parent_id`),
  CONSTRAINT `FK1op0tlni76jj2krhl0roc6rx` FOREIGN KEY (`update_by`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FK2gcg73krc9bi6e816wq6dhqlb` FOREIGN KEY (`parent_id`) REFERENCES `sys_college` (`id`),
  CONSTRAINT `FKlivjm5nuqf1tvrvmsdsta6oe1` FOREIGN KEY (`create_by`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_college`
--

LOCK TABLES `sys_college` WRITE;
/*!40000 ALTER TABLE `sys_college` DISABLE KEYS */;
INSERT INTO `sys_college` VALUES ('676cf193-fbc8-48c1-8a16-f1984c9071b9','2021-01-14 10:15:02.470661','2021-01-14 10:15:02.470661','学院3','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','73f1218d-9249-42a2-9e78-a5c7963fbec1',NULL),('73f1218d-9249-42a2-9e78-a5c7963fbec1','2021-01-14 10:13:29.449121','2021-01-14 10:13:29.449121','主学院','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114',NULL,NULL),('c76098f8-efbb-4957-a2ab-9b332a09d52d','2021-01-14 10:14:37.056202','2021-01-14 10:14:37.056202','学院2','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','73f1218d-9249-42a2-9e78-a5c7963fbec1',NULL),('fc2b34c0-bfe8-4262-9dbb-8f0d026608bc','2021-01-14 10:14:28.020722','2021-01-14 10:14:28.020722','学院1','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','73f1218d-9249-42a2-9e78-a5c7963fbec1',NULL);
/*!40000 ALTER TABLE `sys_college` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_config`
--

DROP TABLE IF EXISTS `sys_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_config` (
  `id` varchar(255) NOT NULL,
  `config_name` varchar(100) DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `FKs07yhp773o8mn9cor8smgv7b4` (`create_by`),
  KEY `FKs3v61guxk6k8t0acfsidvji3i` (`update_by`),
  CONSTRAINT `FKs07yhp773o8mn9cor8smgv7b4` FOREIGN KEY (`create_by`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FKs3v61guxk6k8t0acfsidvji3i` FOREIGN KEY (`update_by`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='参数配置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_config`
--

LOCK TABLES `sys_config` WRITE;
/*!40000 ALTER TABLE `sys_config` DISABLE KEYS */;
INSERT INTO `sys_config` VALUES ('064b2740-c4d1-4891-bf1c-5e1c19bcbe20','用户管理-账号初始密码','sys.user.initPassword','123qwe!@#','Y','初始化密码 123456','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-09 11:09:57','2021-01-09 11:09:57'),('0e1b871c-03d2-4bbc-ba18-013089765bcf','主框架页-是否开启页脚','sys.index.ignoreFooter','true','Y','是否开启底部页脚显示（true显示，false隐藏）','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-09 11:09:57','2021-01-09 11:09:57'),('3a642c2e-d6e0-4838-a71d-b6a0d9869a7a','用户管理-密码字符范围','sys.account.chrtype','0','Y','默认任意字符范围，0任意（密码可以输入任意字符），1数字（密码只能为0-9数字），2英文字母（密码只能为a-z和A-Z字母），3字母和数字（密码必须包含字母，数字）,4字母数字和特殊字符（目前支持的特殊字符包括：~!@#$%^&*()-=_+）','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-09 11:09:57','2021-01-09 11:09:57'),('5f653eba-8557-4a26-9ad1-38d4a59b2c40','主框架页-菜单导航显示风格','sys.index.menuStyle','default','Y','菜单导航显示风格（default为左侧导航菜单，topnav为顶部导航菜单）','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-09 11:09:57','2021-01-09 11:09:57'),('78a58201-8b1c-4a6e-8976-23ec1ac7496f','用户管理-账号密码更新周期','sys.account.passwordValidateDays','0','Y','密码更新周期（填写数字，数据初始化值为0不限制，若修改必须为大于0小于365的正整数），如果超过这个周期登录系统时，则在登录时就会提醒修改密码对话框','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-09 11:09:57','2021-01-09 11:09:57'),('902af647-367c-4116-a74e-b911355c1ff0','账号自助-是否开启用户注册功能','sys.account.registerUser','false','Y','是否开启注册用户功能（true开启，false关闭）','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-09 11:09:57','2021-01-09 11:09:57'),('9788edf0-0488-40d3-8e35-9c38fcb554aa','用户管理-初始密码修改策略','sys.account.initPasswordModify','0','Y','0：初始密码修改策略关闭，没有任何提示，1：提醒用户，如果未修改初始密码，则在登录时就会提醒修改密码对话框','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-09 11:09:57','2021-01-09 11:09:57'),('b9fe2d5a-bfe3-4262-9866-8e066a9348b9','主框架页-默认皮肤样式名称','sys.index.skinName','skin-blue','Y','蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-09 11:09:57','2021-01-09 11:09:57'),('c1b7524a-37da-4e27-b590-fd26a6e6e7ec','主框架页-侧边栏主题','sys.index.sideTheme','theme-blue','Y','深黑主题theme-dark，浅色主题theme-light，深蓝主题theme-blue','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-09 11:09:57','2021-01-09 11:09:57');
/*!40000 ALTER TABLE `sys_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_login_log`
--

DROP TABLE IF EXISTS `sys_login_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_login_log` (
  `id` varchar(255) NOT NULL,
  `browser` varchar(255) DEFAULT NULL,
  `ip` varchar(255) NOT NULL,
  `login_time` datetime(6) NOT NULL,
  `message` tinyint(4) DEFAULT NULL,
  `os` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_login_log`
--

LOCK TABLES `sys_login_log` WRITE;
/*!40000 ALTER TABLE `sys_login_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_login_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sort` int(4) DEFAULT '0' COMMENT '显示顺序',
  `url` varchar(200) DEFAULT '#' COMMENT '请求地址',
  `parent_id` varchar(255) DEFAULT NULL,
  `authority` varchar(255) DEFAULT NULL,
  `target` varchar(20) DEFAULT '' COMMENT '打开方式（menuItem页签 menuBlank新窗口）',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `is_refresh` char(1) DEFAULT '1' COMMENT '是否刷新（0刷新 1不刷新）',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  `create_by` varchar(255) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_menu_id_uindex` (`id`),
  KEY `FKoxg2hi96yr9pf2m0stjomr3we` (`create_by`),
  KEY `FKsiko0qcr8ddamvrxf1tk4opgc` (`update_by`),
  KEY `FK2jrf4gb0gjqi8882gxytpxnhe` (`parent_id`),
  CONSTRAINT `FK2jrf4gb0gjqi8882gxytpxnhe` FOREIGN KEY (`parent_id`) REFERENCES `sys_menu` (`id`),
  CONSTRAINT `FKoxg2hi96yr9pf2m0stjomr3we` FOREIGN KEY (`create_by`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FKsiko0qcr8ddamvrxf1tk4opgc` FOREIGN KEY (`update_by`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES ('0554e0d0-3bb9-4faf-b23c-f11e3bc74369','学院新增',2,'#','d78b56ab-3730-4cad-83e6-8e63ddb4969f','system:college:add','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-13 18:30:33','2021-01-13 18:30:33'),('06e31db6-562f-48b9-8023-ee475ff3d878','字典新增',2,'#','79ba0663-46ba-4287-bc3c-1496d8e15b7a','system:dict:add','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('0b5c6672-b034-40ed-b167-5ecc5e8cf9bf','摄像头修改',3,'#','0c9dfc80-a979-4dff-ba61-08e43f0c52b8','system:camera:edit','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-18 14:24:25','2021-01-18 14:24:25'),('0c9dfc80-a979-4dff-ba61-08e43f0c52b8','摄像头管理',7,'/system/camera','eeaaceb9-f8db-4506-81d7-986e4b82fae0','system:camera:view','menuItem','C',NULL,NULL,'fa fa-video-camera',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-18 14:22:49','2021-01-18 14:22:49'),('106a997b-015b-4348-8d38-81770a8ab8b9','数据监控',3,'/monitor/data','b18effaa-ff27-4666-a8e0-b24543aadb4e','monitor:data:view','','C','0','1','fa fa-bug','数据监控菜单','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('1461f63f-13bd-40ac-a4d2-3d5706e20102','车辆新增',2,'#','62288907-eb9b-41aa-8f22-8d53c0f2d357','system:vehicle:add','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-14 15:08:37','2021-01-14 15:08:37'),('17cbbbf5-c7c8-43db-b152-94816e63ec9a','用户删除',4,'#','33926c89-e38e-45ee-846d-81f564a8ea38','system:user:remove','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('17ef33ad-80d8-4630-b8a8-37ef8e035147','服务监控',4,'/monitor/server','b18effaa-ff27-4666-a8e0-b24543aadb4e','monitor:server:view','','C','0','1','fa fa-server','服务监控菜单','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('18c26b44-2328-4605-aa8b-43c8bbc9a625','违规处置平台',3,'#',NULL,'','menuItem','M',NULL,NULL,'fa fa-bar-chart',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-21 13:18:20','2021-02-02 10:44:27'),('1a47f9a2-0a9a-43dc-81f6-916cc94e4fca','区域管理',6,'/system/region','eeaaceb9-f8db-4506-81d7-986e4b82fae0','system:region:view','menuItem','C',NULL,NULL,'fa fa-adjust',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-18 12:42:22','2021-01-18 12:43:40'),('1ebf0f90-c3d0-4238-9509-8af4551f2f03','参数新增',2,'#','b0bfd3e5-bc09-4e1a-807a-6247e4d06200','system:config:add','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('209e47a9-5cc1-4730-9ea8-18980c25b2cd','审批',3,'#','b57bdf94-0845-4cc7-b504-e399198b144d','event:approval:approval','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-29 18:15:48','2021-01-29 18:15:48'),('298a3927-93a1-4352-bb06-ebf113876fe5','用户导出',5,'#','33926c89-e38e-45ee-846d-81f564a8ea38','system:user:export','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('2fd34018-b88e-4c9e-a438-f95959801a81','违规分类管理',10,'/system/violation','eeaaceb9-f8db-4506-81d7-986e4b82fae0','system:violation:view','menuItem','C',NULL,NULL,'fa fa-certificate',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-18 17:05:12','2021-01-18 17:06:20'),('304794fa-f08f-4750-9541-3e663fbca4c7','扣分修改',3,'#','7f2fa746-b85b-419a-8acd-4dd985c4e6e7','system:score:edit','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-20 13:32:17','2021-01-20 13:32:17'),('33926c89-e38e-45ee-846d-81f564a8ea38','用户管理',1,'/system/user','eeaaceb9-f8db-4506-81d7-986e4b82fae0','system:user:view','','C','0','1','fa fa-user-o','用户管理菜单','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('345b4c26-3414-40c7-aea3-6c6f9514da34','字典导出',5,'#','79ba0663-46ba-4287-bc3c-1496d8e15b7a','system:dict:export','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('38a620d2-44f8-4ca7-81c7-6eabbd42914c','摄像头新增',2,'#','0c9dfc80-a979-4dff-ba61-08e43f0c52b8','system:camera:add','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-18 14:23:57','2021-01-18 14:23:57'),('3d8907c6-d118-4e2b-bd32-c75fc7f1e9c6','停车场新增',2,'#','e98bc573-3ba7-4354-885b-c81d3df5715f','park:parklot:add','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-21 11:11:41','2021-01-21 11:11:41'),('42744d14-9fa7-4d23-804a-cfa24e83b800','参数修改',3,'#','b0bfd3e5-bc09-4e1a-807a-6247e4d06200','system:config:edit','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('485073aa-8bab-45d3-b34d-300278db4f43','审批查询',1,'#','b57bdf94-0845-4cc7-b504-e399198b144d','event:approval:search','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-22 13:57:18','2021-01-22 13:57:18'),('48d3c830-c362-46b4-a69e-6fc1fbc786de','菜单查询',1,'#','51977a2b-5ff2-41ea-bae4-4bab175af56f','system:menu:search','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('49e538c1-c68d-4fa3-8d41-9a06329d4135','扣分查询',1,'#','7f2fa746-b85b-419a-8acd-4dd985c4e6e7','system:score:search','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-20 12:37:16','2021-01-20 14:07:39'),('4c61a0a9-815d-4dfd-9d92-91415d3177f3','学院修改',3,'#','d78b56ab-3730-4cad-83e6-8e63ddb4969f','system:college:edit','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-13 18:31:11','2021-01-13 18:31:11'),('4faa3eb9-188d-4328-a88b-24b2e779149f','菜单新增',2,'#','51977a2b-5ff2-41ea-bae4-4bab175af56f','system:menu:add','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('51977a2b-5ff2-41ea-bae4-4bab175af56f','菜单管理',3,'/system/menu','eeaaceb9-f8db-4506-81d7-986e4b82fae0','system:menu:view','','C','0','1','fa fa-th-list','菜单管理菜单','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('53e21a05-8bb3-49ac-9c75-6a769e1b5257','在线查询',1,'#','f5179957-15fe-41a0-9a36-7173481eda5f','monitor:online:list','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('54855816-ace4-4878-a000-b148f80f4c7c','摄像头查询',1,'#','0c9dfc80-a979-4dff-ba61-08e43f0c52b8','system:camera:search','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-18 14:23:33','2021-01-18 14:23:33'),('5494e9bf-05e1-440d-b6a8-fed43026c7a4','停车场删除',4,'#','e98bc573-3ba7-4354-885b-c81d3df5715f','park:parklot:remove','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-21 11:12:32','2021-01-21 11:12:32'),('54bc50a2-a46b-4e4e-b4c6-6d5ecc9d9e69','定时任务',2,'/monitor/job','b18effaa-ff27-4666-a8e0-b24543aadb4e','monitor:job:view','','C','0','1','fa fa-tasks','定时任务菜单','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('59972056-5280-4eb0-b468-6f729c8f7a19','角色管理',2,'/system/role','eeaaceb9-f8db-4506-81d7-986e4b82fae0','system:role:view','menuItem','C','0','1','fa fa-user-secret','角色管理菜单','1b3c1438-beb2-4bab-af86-b6b8dfb91114','d5053e06-fcb2-4855-b9d0-ac5861ac4594','2021-01-06 11:28:07','2021-01-13 15:22:30'),('59a6f5f9-69a3-4066-b584-3ea30e920649','状态修改',5,'#','54bc50a2-a46b-4e4e-b4c6-6d5ecc9d9e69','monitor:job:changeStatus','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('5a4fa9a4-e27d-43d6-978d-1b8509820b0f','字典修改',3,'#','79ba0663-46ba-4287-bc3c-1496d8e15b7a','system:dict:edit','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('5d9e1634-b0f9-4ed6-9a22-d780c1bf0d6b','参数查询',1,'#','b0bfd3e5-bc09-4e1a-807a-6247e4d06200','system:config:list','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('62288907-eb9b-41aa-8f22-8d53c0f2d357','车辆管理',5,'/system/vehicle','eeaaceb9-f8db-4506-81d7-986e4b82fae0','system:vehicle:view','menuItem','C',NULL,NULL,'fa fa-automobile',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-14 15:05:21','2021-01-14 15:05:21'),('687123d6-41e7-4767-b114-077ef66da5d3','扣分新增',2,'#','7f2fa746-b85b-419a-8acd-4dd985c4e6e7','system:score:add','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-20 13:31:51','2021-01-20 13:31:51'),('6dfb338e-976a-44f9-ab96-cd567297b043','扣分删除',4,'#','7f2fa746-b85b-419a-8acd-4dd985c4e6e7','system:score:remove','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-20 13:32:40','2021-01-20 13:32:40'),('7332b0dc-4d2a-4c64-9806-8fd1f43bf20c','用户查询',1,'#','33926c89-e38e-45ee-846d-81f564a8ea38','system:user:search','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('79ba0663-46ba-4287-bc3c-1496d8e15b7a','字典管理',6,'/system/dict','eeaaceb9-f8db-4506-81d7-986e4b82fae0','system:dict:view','','C','0','1','fa fa-bookmark-o','字典管理菜单','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('7f2e78a1-41ef-4fa0-9367-88797e6b338e','菜单删除',4,'#','51977a2b-5ff2-41ea-bae4-4bab175af56f','system:menu:remove','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('7f2fa746-b85b-419a-8acd-4dd985c4e6e7','扣分设置',11,'/system/score','eeaaceb9-f8db-4506-81d7-986e4b82fae0','system:score:view','menuItem','C',NULL,NULL,'fa fa-bar-chart',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-20 12:36:25','2021-01-20 14:03:54'),('83bf1e8a-a008-4681-9878-91e1a5305330','批量强退',2,'#','f5179957-15fe-41a0-9a36-7173481eda5f','monitor:online:batchForceLogout','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('848fcbaf-a27d-4401-ac34-292622e54f28','参数删除',4,'#','b0bfd3e5-bc09-4e1a-807a-6247e4d06200','system:config:remove','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('8bc56609-6694-4699-b5ba-d62f7ed59ede','违规数据统计',2,'/event/statistics','18c26b44-2328-4605-aa8b-43c8bbc9a625','event:statistics:view','menuItem','C',NULL,NULL,'fa fa-address-book-o',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-21 13:20:14','2021-01-30 13:32:04'),('8ef0d06c-1eaf-4d47-b5f3-af64d9077fe2','学院删除',4,'#','d78b56ab-3730-4cad-83e6-8e63ddb4969f','system:college:remove','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-13 18:31:44','2021-01-13 18:31:44'),('8fe828aa-dce2-4e39-abc7-9f82fcd84aeb','违规查询',1,'#','2fd34018-b88e-4c9e-a438-f95959801a81','system:violation:search','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-18 17:07:23','2021-01-18 17:07:23'),('92589525-79c3-4f76-a29a-26824a000d46','角色修改',3,'#','59972056-5280-4eb0-b468-6f729c8f7a19','system:role:edit','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('9278d780-b47c-493b-9d2d-afe06f6764d8','任务查询',1,'#','54bc50a2-a46b-4e4e-b4c6-6d5ecc9d9e69','monitor:job:list','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('93d5dfe4-3381-4eae-bfc7-527cc2015f3d','角色导出',5,'#','59972056-5280-4eb0-b468-6f729c8f7a19','system:role:export','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('94a8302f-ad2d-49f0-aaa8-7989e65e246e','角色删除',4,'#','59972056-5280-4eb0-b468-6f729c8f7a19','system:role:remove','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('950cea35-83ed-44b3-9dee-df9538234e49','单条强退',3,'#','f5179957-15fe-41a0-9a36-7173481eda5f','monitor:online:forceLogout','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('96334cf4-95e3-47d9-b170-9903b849fa81','字典删除',4,'#','79ba0663-46ba-4287-bc3c-1496d8e15b7a','system:dict:remove','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('a47104f8-db0c-4f30-8c3f-ac8f74b532b9','区域修改',3,'#','1a47f9a2-0a9a-43dc-81f6-916cc94e4fca','system:region:edit','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-18 12:46:05','2021-01-18 12:46:05'),('aa98c270-dcb4-48f0-a3de-dd515a7f72a9','违规修改',3,'#','2fd34018-b88e-4c9e-a438-f95959801a81','system:violation:edit','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-18 17:08:05','2021-01-18 17:08:05'),('ac26ee13-2fe8-4351-93d6-3919abe36ce9','违规删除',4,'#','2fd34018-b88e-4c9e-a438-f95959801a81','system:violation:remove','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-18 17:08:32','2021-01-18 17:08:32'),('ae798006-edd4-478f-b5b4-f70233f6f619','区域删除',4,'#','1a47f9a2-0a9a-43dc-81f6-916cc94e4fca','system:region:remove','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-18 12:47:04','2021-01-18 12:47:04'),('b0bfd3e5-bc09-4e1a-807a-6247e4d06200','参数设置',7,'/system/config','eeaaceb9-f8db-4506-81d7-986e4b82fae0','system:config:view','','C','0','1','fa fa-sun-o','参数设置菜单','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('b18effaa-ff27-4666-a8e0-b24543aadb4e','系统监控',4,'#',NULL,'','menuItem','M','0','1','fa fa-video-camera','系统监控目录','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-21 13:18:29'),('b34824ba-5df6-4be8-b190-502e423c24bd','任务详细',6,'#','54bc50a2-a46b-4e4e-b4c6-6d5ecc9d9e69','monitor:job:detail','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('b4ff6ff5-62ea-4734-aa3c-bfd7391a90d7','任务修改',3,'#','54bc50a2-a46b-4e4e-b4c6-6d5ecc9d9e69','monitor:job:edit','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('b57bdf94-0845-4cc7-b504-e399198b144d','违规审批',1,'/event/approval','18c26b44-2328-4605-aa8b-43c8bbc9a625','event:approval:view','menuItem','C',NULL,NULL,'fa fa-address-book',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-21 13:19:42','2021-01-22 13:40:51'),('b5e4ac60-75c0-4e48-8665-a6a8dcf632e6','用户新增',2,'#','33926c89-e38e-45ee-846d-81f564a8ea38','system:user:add','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('b86f4d57-0ff2-4509-baad-2207477e7ae2','违规新增',2,'#','2fd34018-b88e-4c9e-a438-f95959801a81','system:violation:add','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-18 17:07:48','2021-01-18 17:07:48'),('ba050e2e-2f49-4168-af4a-674da78263cb','重置密码',7,'#','33926c89-e38e-45ee-846d-81f564a8ea38','system:user:resetPwd','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('bca635af-5bac-449b-adba-7947aacb2694','摄像头删除',4,'#','0c9dfc80-a979-4dff-ba61-08e43f0c52b8','system:camera:remove','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-18 14:24:58','2021-01-18 14:24:58'),('c06868ad-256a-4ac4-a7fa-db4f9c82e021','区域查询',1,'#','1a47f9a2-0a9a-43dc-81f6-916cc94e4fca','system:region:search','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-18 12:44:13','2021-01-18 12:44:28'),('c193c493-6f42-4df7-9015-0b4a4dc91ac5','作废',4,'#','b57bdf94-0845-4cc7-b504-e399198b144d','event:approval:cancel','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-29 18:16:40','2021-01-29 18:16:40'),('c194d646-5d99-4041-9046-9b8ac3b8c479','停车场修改',3,'#','e98bc573-3ba7-4354-885b-c81d3df5715f','park:parklot:edit','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-21 11:12:09','2021-01-21 11:12:09'),('cc1e3a72-4b68-49fe-ac60-9ffee7e03f40','任务新增',2,'#','54bc50a2-a46b-4e4e-b4c6-6d5ecc9d9e69','monitor:job:add','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('cc3a32cb-f6fe-44fe-b0a3-6a5780d3e65b','字典查询',1,'#','79ba0663-46ba-4287-bc3c-1496d8e15b7a','system:dict:list','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('cc4a5e04-2be1-42a4-a079-835d1242398d','停车场查询',1,'#','e98bc573-3ba7-4354-885b-c81d3df5715f','park:parklot:search','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-21 11:10:55','2021-01-21 11:10:55'),('cc636cfe-8ecb-4fed-af3e-4b9bf0570ab6','任务导出',7,'#','54bc50a2-a46b-4e4e-b4c6-6d5ecc9d9e69','monitor:job:export','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('d3692db5-b118-44bb-b09a-c0e3d8b70346','角色查询',1,'#','59972056-5280-4eb0-b468-6f729c8f7a19','system:role:search','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('d78b56ab-3730-4cad-83e6-8e63ddb4969f','学院管理',4,'/system/college','eeaaceb9-f8db-4506-81d7-986e4b82fae0','system:college:view','menuItem','C',NULL,NULL,'fa fa-graduation-cap',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-13 18:28:46','2021-01-13 18:28:46'),('dbb0e1d1-75de-4a4f-8fae-31259543883f','菜单修改',3,'#','51977a2b-5ff2-41ea-bae4-4bab175af56f','system:menu:edit','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('ddd1c450-840c-409e-89de-ba7bbb59babe','车辆修改',3,'#','62288907-eb9b-41aa-8f22-8d53c0f2d357','system:vehicle:edit','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-14 15:09:16','2021-01-14 15:09:16'),('e1925f9c-960b-45fc-981d-e62ebfb4e143','参数导出',5,'#','b0bfd3e5-bc09-4e1a-807a-6247e4d06200','system:config:export','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('e3af6408-1161-4167-8411-5b7e2df576b5','用户导入',6,'#','33926c89-e38e-45ee-846d-81f564a8ea38','system:user:import','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('e47dce29-47c4-4522-8c0d-7eba51a39c16','违规数据统计查询',1,'#','8bc56609-6694-4699-b5ba-d62f7ed59ede','system:statistics:search','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-30 16:16:32','2021-01-30 16:16:32'),('e598271c-db31-4e7e-af05-92cc92780b85','查看详情',2,'#','b57bdf94-0845-4cc7-b504-e399198b144d','event:approval:detail','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-22 15:51:35','2021-01-22 15:51:35'),('e8701b76-4773-4785-8261-0d33f25ac401','角色新增',2,'#','59972056-5280-4eb0-b468-6f729c8f7a19','system:role:add','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('e98bc573-3ba7-4354-885b-c81d3df5715f','停车场管理',16,'/park/parklot','eeaaceb9-f8db-4506-81d7-986e4b82fae0','park:parklot:view','menuItem','C',NULL,NULL,'fa fa-tasks',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-21 11:10:13','2021-02-02 10:41:21'),('ebe31c6c-3d23-46b6-a49d-c9beb157d73a','任务删除',4,'#','54bc50a2-a46b-4e4e-b4c6-6d5ecc9d9e69','monitor:job:remove','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('eeaaceb9-f8db-4506-81d7-986e4b82fae0','系统管理',1,'#',NULL,'','menuItem','M','0','1','fa fa-gear','系统管理目录','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-02-02 10:36:34'),('f5179957-15fe-41a0-9a36-7173481eda5f','在线用户',1,'/monitor/online','b18effaa-ff27-4666-a8e0-b24543aadb4e','monitor:online:view','','C','0','1','fa fa-user-circle','在线用户菜单','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('f7d415c3-6425-431b-be19-540aa7a0b3d4','用户修改',3,'#','33926c89-e38e-45ee-846d-81f564a8ea38','system:user:edit','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('fad86ae3-7c93-418d-b14f-87125259800c','缓存监控',5,'/monitor/cache','b18effaa-ff27-4666-a8e0-b24543aadb4e','monitor:cache:view','','C','0','1','fa fa-cube','缓存监控菜单','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('fc0b4075-b562-4f5d-8e51-59946ace5eb9','车辆查询',1,'#','62288907-eb9b-41aa-8f22-8d53c0f2d357','system:vehicle:search','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-14 15:06:39','2021-01-14 15:06:39'),('fd79d156-98c0-4997-9e8f-9eccd3d24596','区域新增',2,'#','1a47f9a2-0a9a-43dc-81f6-916cc94e4fca','system:region:add','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-18 12:45:07','2021-01-18 12:45:07'),('fe0082ae-af19-4bc8-9284-c346ba0c080a','学院查询',1,'#','d78b56ab-3730-4cad-83e6-8e63ddb4969f','system:college:search','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-13 18:29:56','2021-01-13 18:29:56'),('ff641349-a11c-4575-947b-ce2f0df42694','车辆删除',4,'#','62288907-eb9b-41aa-8f22-8d53c0f2d357','system:vehicle:remove','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-14 15:10:12','2021-01-14 15:10:12');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_region`
--

DROP TABLE IF EXISTS `sys_region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_region` (
  `id` varchar(255) NOT NULL,
  `create_time` datetime(6) NOT NULL,
  `update_time` datetime(6) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfjvwepskshfe26it868bmic5m` (`create_by`),
  KEY `FK2006ahwbsbk3bgxx9cw0dfm0p` (`update_by`),
  CONSTRAINT `FK2006ahwbsbk3bgxx9cw0dfm0p` FOREIGN KEY (`update_by`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FKfjvwepskshfe26it868bmic5m` FOREIGN KEY (`create_by`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_region`
--

LOCK TABLES `sys_region` WRITE;
/*!40000 ALTER TABLE `sys_region` DISABLE KEYS */;
INSERT INTO `sys_region` VALUES ('779d6d9b-fa7f-4eac-8d22-3c7020c1c5c0','2021-01-20 11:58:20.325064','2021-01-20 11:58:20.325064','区域2','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114'),('a299e8e7-9053-4c0c-8a80-7c7eeeca6cdf','2021-01-20 11:58:33.670494','2021-01-20 11:58:33.670494','区域4','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114'),('aa8908a9-d2ad-40c7-ba3a-4630e08d9d1f','2021-01-20 11:58:27.241132','2021-01-20 11:58:27.241132','区域3','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114'),('cfb2f9e4-9c45-4c81-80c9-71aabe1d3d5a','2021-01-20 11:58:14.947207','2021-01-20 11:58:14.947207','区域1','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114');
/*!40000 ALTER TABLE `sys_region` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `id` varchar(255) NOT NULL,
  `create_time` datetime(6) NOT NULL,
  `update_time` datetime(6) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdkwvv0rm6j3d5l6hwsy2dplol` (`create_by`),
  KEY `FKrouqqi3f2bgc5o83wdstlh6q4` (`update_by`),
  CONSTRAINT `FKdkwvv0rm6j3d5l6hwsy2dplol` FOREIGN KEY (`create_by`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FKrouqqi3f2bgc5o83wdstlh6q4` FOREIGN KEY (`update_by`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES ('90b7f916-9ea0-46c7-b2a7-81ca885e5850','2021-01-14 10:58:50.397386','2021-01-14 10:58:50.397386','普通角色','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114',''),('a2a84058-8660-4393-a89d-6d1064f9b43a','2021-01-12 15:36:14.615792','2021-02-02 10:43:31.571139','超级管理员','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','超级管理员,具有最大权限的角色');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_menu` (
  `role_id` varchar(255) NOT NULL,
  `menu_id` varchar(255) NOT NULL,
  KEY `FKkeitxsgxwayackgqllio4ohn5` (`role_id`),
  KEY `FKf3mud4qoc7ayew8nml4plkevo` (`menu_id`),
  CONSTRAINT `FKf3mud4qoc7ayew8nml4plkevo` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu` (`id`),
  CONSTRAINT `FKkeitxsgxwayackgqllio4ohn5` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` VALUES ('90b7f916-9ea0-46c7-b2a7-81ca885e5850','eeaaceb9-f8db-4506-81d7-986e4b82fae0'),('90b7f916-9ea0-46c7-b2a7-81ca885e5850','d78b56ab-3730-4cad-83e6-8e63ddb4969f'),('90b7f916-9ea0-46c7-b2a7-81ca885e5850','0554e0d0-3bb9-4faf-b23c-f11e3bc74369'),('90b7f916-9ea0-46c7-b2a7-81ca885e5850','4c61a0a9-815d-4dfd-9d92-91415d3177f3'),('90b7f916-9ea0-46c7-b2a7-81ca885e5850','8ef0d06c-1eaf-4d47-b5f3-af64d9077fe2'),('90b7f916-9ea0-46c7-b2a7-81ca885e5850','fe0082ae-af19-4bc8-9284-c346ba0c080a'),('a2a84058-8660-4393-a89d-6d1064f9b43a','18c26b44-2328-4605-aa8b-43c8bbc9a625'),('a2a84058-8660-4393-a89d-6d1064f9b43a','8bc56609-6694-4699-b5ba-d62f7ed59ede'),('a2a84058-8660-4393-a89d-6d1064f9b43a','e47dce29-47c4-4522-8c0d-7eba51a39c16'),('a2a84058-8660-4393-a89d-6d1064f9b43a','b57bdf94-0845-4cc7-b504-e399198b144d'),('a2a84058-8660-4393-a89d-6d1064f9b43a','209e47a9-5cc1-4730-9ea8-18980c25b2cd'),('a2a84058-8660-4393-a89d-6d1064f9b43a','485073aa-8bab-45d3-b34d-300278db4f43'),('a2a84058-8660-4393-a89d-6d1064f9b43a','c193c493-6f42-4df7-9015-0b4a4dc91ac5'),('a2a84058-8660-4393-a89d-6d1064f9b43a','e598271c-db31-4e7e-af05-92cc92780b85'),('a2a84058-8660-4393-a89d-6d1064f9b43a','eeaaceb9-f8db-4506-81d7-986e4b82fae0'),('a2a84058-8660-4393-a89d-6d1064f9b43a','0c9dfc80-a979-4dff-ba61-08e43f0c52b8'),('a2a84058-8660-4393-a89d-6d1064f9b43a','0b5c6672-b034-40ed-b167-5ecc5e8cf9bf'),('a2a84058-8660-4393-a89d-6d1064f9b43a','38a620d2-44f8-4ca7-81c7-6eabbd42914c'),('a2a84058-8660-4393-a89d-6d1064f9b43a','54855816-ace4-4878-a000-b148f80f4c7c'),('a2a84058-8660-4393-a89d-6d1064f9b43a','bca635af-5bac-449b-adba-7947aacb2694'),('a2a84058-8660-4393-a89d-6d1064f9b43a','1a47f9a2-0a9a-43dc-81f6-916cc94e4fca'),('a2a84058-8660-4393-a89d-6d1064f9b43a','a47104f8-db0c-4f30-8c3f-ac8f74b532b9'),('a2a84058-8660-4393-a89d-6d1064f9b43a','ae798006-edd4-478f-b5b4-f70233f6f619'),('a2a84058-8660-4393-a89d-6d1064f9b43a','c06868ad-256a-4ac4-a7fa-db4f9c82e021'),('a2a84058-8660-4393-a89d-6d1064f9b43a','fd79d156-98c0-4997-9e8f-9eccd3d24596'),('a2a84058-8660-4393-a89d-6d1064f9b43a','2fd34018-b88e-4c9e-a438-f95959801a81'),('a2a84058-8660-4393-a89d-6d1064f9b43a','8fe828aa-dce2-4e39-abc7-9f82fcd84aeb'),('a2a84058-8660-4393-a89d-6d1064f9b43a','aa98c270-dcb4-48f0-a3de-dd515a7f72a9'),('a2a84058-8660-4393-a89d-6d1064f9b43a','ac26ee13-2fe8-4351-93d6-3919abe36ce9'),('a2a84058-8660-4393-a89d-6d1064f9b43a','b86f4d57-0ff2-4509-baad-2207477e7ae2'),('a2a84058-8660-4393-a89d-6d1064f9b43a','33926c89-e38e-45ee-846d-81f564a8ea38'),('a2a84058-8660-4393-a89d-6d1064f9b43a','17cbbbf5-c7c8-43db-b152-94816e63ec9a'),('a2a84058-8660-4393-a89d-6d1064f9b43a','298a3927-93a1-4352-bb06-ebf113876fe5'),('a2a84058-8660-4393-a89d-6d1064f9b43a','7332b0dc-4d2a-4c64-9806-8fd1f43bf20c'),('a2a84058-8660-4393-a89d-6d1064f9b43a','b5e4ac60-75c0-4e48-8665-a6a8dcf632e6'),('a2a84058-8660-4393-a89d-6d1064f9b43a','ba050e2e-2f49-4168-af4a-674da78263cb'),('a2a84058-8660-4393-a89d-6d1064f9b43a','e3af6408-1161-4167-8411-5b7e2df576b5'),('a2a84058-8660-4393-a89d-6d1064f9b43a','f7d415c3-6425-431b-be19-540aa7a0b3d4'),('a2a84058-8660-4393-a89d-6d1064f9b43a','51977a2b-5ff2-41ea-bae4-4bab175af56f'),('a2a84058-8660-4393-a89d-6d1064f9b43a','48d3c830-c362-46b4-a69e-6fc1fbc786de'),('a2a84058-8660-4393-a89d-6d1064f9b43a','4faa3eb9-188d-4328-a88b-24b2e779149f'),('a2a84058-8660-4393-a89d-6d1064f9b43a','7f2e78a1-41ef-4fa0-9367-88797e6b338e'),('a2a84058-8660-4393-a89d-6d1064f9b43a','dbb0e1d1-75de-4a4f-8fae-31259543883f'),('a2a84058-8660-4393-a89d-6d1064f9b43a','59972056-5280-4eb0-b468-6f729c8f7a19'),('a2a84058-8660-4393-a89d-6d1064f9b43a','92589525-79c3-4f76-a29a-26824a000d46'),('a2a84058-8660-4393-a89d-6d1064f9b43a','93d5dfe4-3381-4eae-bfc7-527cc2015f3d'),('a2a84058-8660-4393-a89d-6d1064f9b43a','94a8302f-ad2d-49f0-aaa8-7989e65e246e'),('a2a84058-8660-4393-a89d-6d1064f9b43a','d3692db5-b118-44bb-b09a-c0e3d8b70346'),('a2a84058-8660-4393-a89d-6d1064f9b43a','e8701b76-4773-4785-8261-0d33f25ac401'),('a2a84058-8660-4393-a89d-6d1064f9b43a','62288907-eb9b-41aa-8f22-8d53c0f2d357'),('a2a84058-8660-4393-a89d-6d1064f9b43a','1461f63f-13bd-40ac-a4d2-3d5706e20102'),('a2a84058-8660-4393-a89d-6d1064f9b43a','ddd1c450-840c-409e-89de-ba7bbb59babe'),('a2a84058-8660-4393-a89d-6d1064f9b43a','fc0b4075-b562-4f5d-8e51-59946ace5eb9'),('a2a84058-8660-4393-a89d-6d1064f9b43a','ff641349-a11c-4575-947b-ce2f0df42694'),('a2a84058-8660-4393-a89d-6d1064f9b43a','7f2fa746-b85b-419a-8acd-4dd985c4e6e7'),('a2a84058-8660-4393-a89d-6d1064f9b43a','304794fa-f08f-4750-9541-3e663fbca4c7'),('a2a84058-8660-4393-a89d-6d1064f9b43a','49e538c1-c68d-4fa3-8d41-9a06329d4135'),('a2a84058-8660-4393-a89d-6d1064f9b43a','687123d6-41e7-4767-b114-077ef66da5d3'),('a2a84058-8660-4393-a89d-6d1064f9b43a','6dfb338e-976a-44f9-ab96-cd567297b043'),('a2a84058-8660-4393-a89d-6d1064f9b43a','d78b56ab-3730-4cad-83e6-8e63ddb4969f'),('a2a84058-8660-4393-a89d-6d1064f9b43a','0554e0d0-3bb9-4faf-b23c-f11e3bc74369'),('a2a84058-8660-4393-a89d-6d1064f9b43a','4c61a0a9-815d-4dfd-9d92-91415d3177f3'),('a2a84058-8660-4393-a89d-6d1064f9b43a','8ef0d06c-1eaf-4d47-b5f3-af64d9077fe2'),('a2a84058-8660-4393-a89d-6d1064f9b43a','fe0082ae-af19-4bc8-9284-c346ba0c080a'),('a2a84058-8660-4393-a89d-6d1064f9b43a','e98bc573-3ba7-4354-885b-c81d3df5715f'),('a2a84058-8660-4393-a89d-6d1064f9b43a','3d8907c6-d118-4e2b-bd32-c75fc7f1e9c6'),('a2a84058-8660-4393-a89d-6d1064f9b43a','5494e9bf-05e1-440d-b6a8-fed43026c7a4'),('a2a84058-8660-4393-a89d-6d1064f9b43a','c194d646-5d99-4041-9046-9b8ac3b8c479'),('a2a84058-8660-4393-a89d-6d1064f9b43a','cc4a5e04-2be1-42a4-a079-835d1242398d');
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_score`
--

DROP TABLE IF EXISTS `sys_score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_score` (
  `id` varchar(255) NOT NULL,
  `create_time` datetime(6) NOT NULL,
  `update_time` datetime(6) NOT NULL,
  `number` int(11) DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `region_id` varchar(255) DEFAULT NULL,
  `violation_id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKiy8vamfkl0juuu5funm584jym` (`create_by`),
  KEY `FK6xt59x3we1wgcyxj04d057b7l` (`update_by`),
  KEY `FK9v0yp258eb3dcxjcf5v9puius` (`region_id`),
  KEY `FKr7jbe1y3eqkrd5qmy0ml2hpkv` (`violation_id`),
  CONSTRAINT `FK6xt59x3we1wgcyxj04d057b7l` FOREIGN KEY (`update_by`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FK9v0yp258eb3dcxjcf5v9puius` FOREIGN KEY (`region_id`) REFERENCES `sys_region` (`id`),
  CONSTRAINT `FKiy8vamfkl0juuu5funm584jym` FOREIGN KEY (`create_by`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FKr7jbe1y3eqkrd5qmy0ml2hpkv` FOREIGN KEY (`violation_id`) REFERENCES `sys_violation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_score`
--

LOCK TABLES `sys_score` WRITE;
/*!40000 ALTER TABLE `sys_score` DISABLE KEYS */;
INSERT INTO `sys_score` VALUES ('fde57d8e-c9ca-4a91-b77d-850b6a43d262','2021-01-30 13:41:55.846882','2021-01-30 13:41:55.846882',10,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','aa8908a9-d2ad-40c7-ba3a-4630e08d9d1f','a2095ee8-a8a7-4221-beaa-42a2ae6b416d','违章停车10小时');
/*!40000 ALTER TABLE `sys_score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `id` varchar(255) NOT NULL,
  `create_time` datetime(6) NOT NULL,
  `update_time` datetime(6) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` char(60) NOT NULL,
  `username` varchar(20) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `last_login_time` datetime(6) DEFAULT NULL,
  `sex` char(1) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone_number` char(32) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `college_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6uj38tbvf8jyy6je7oghdx8oe` (`create_by`),
  KEY `FKgnogw8c0llt9oyonwwgfhyync` (`update_by`),
  KEY `FKnxfsjcmww39m3i0e17u9nc186` (`college_id`),
  CONSTRAINT `FK6uj38tbvf8jyy6je7oghdx8oe` FOREIGN KEY (`create_by`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FKgnogw8c0llt9oyonwwgfhyync` FOREIGN KEY (`update_by`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FKnxfsjcmww39m3i0e17u9nc186` FOREIGN KEY (`college_id`) REFERENCES `sys_college` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES ('1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 16:04:50.000000','2021-01-22 17:07:36.097519','超级管理员','$2a$10$b3ezmRiBwb0nJjIRk3JWVOeTmcS7mPDOD5T887jLWfG2/GDdgZptK','admin','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-02-04 13:49:08.575819','男','zhizhufan@foxmail.com','043ca3fc919e4db0dfc37cecf8490f1d','','73f1218d-9249-42a2-9e78-a5c7963fbec1'),('5d9c5075-47dc-4e6c-a177-4f366f2e4ea3','2021-01-26 15:37:40.153917','2021-01-26 15:37:40.153917','王硕','$2a$10$.eKtIPjsyYGg9d24.jASoerDxLsoPmnrzUgF.VMq3t37dRT9Xs6li','wangshuo','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-26 15:38:33.931903','男','','043ca3fc919e4db0dfc37cecf8490f1d','','c76098f8-efbb-4957-a2ab-9b332a09d52d'),('d5053e06-fcb2-4855-b9d0-ac5861ac4594','2021-01-12 15:39:51.697828','2021-01-22 17:07:13.993072','刘凯','$2a$10$b3ezmRiBwb0nJjIRk3JWVOeTmcS7mPDOD5T887jLWfG2/GDdgZptK','liukai','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-13 15:20:16.633081','男','zhizhufan@foxmail.com','043ca3fc919e4db0dfc37cecf8490f1d','','fc2b34c0-bfe8-4262-9dbb-8f0d026608bc');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_role` (
  `user_id` varchar(255) NOT NULL,
  `role_id` varchar(255) NOT NULL,
  KEY `FKhh52n8vd4ny9ff4x9fb8v65qx` (`role_id`),
  KEY `FKb40xxfch70f5qnyfw8yme1n1s` (`user_id`),
  CONSTRAINT `FKb40xxfch70f5qnyfw8yme1n1s` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FKhh52n8vd4ny9ff4x9fb8v65qx` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES ('d5053e06-fcb2-4855-b9d0-ac5861ac4594','a2a84058-8660-4393-a89d-6d1064f9b43a'),('1b3c1438-beb2-4bab-af86-b6b8dfb91114','a2a84058-8660-4393-a89d-6d1064f9b43a'),('5d9c5075-47dc-4e6c-a177-4f366f2e4ea3','a2a84058-8660-4393-a89d-6d1064f9b43a');
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_vehicle`
--

DROP TABLE IF EXISTS `sys_vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_vehicle` (
  `id` varchar(255) NOT NULL,
  `create_time` datetime(6) NOT NULL,
  `update_time` datetime(6) NOT NULL,
  `number` varchar(255) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `college_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_iik8klcph48940klfc8xv48hb` (`number`),
  KEY `FKjwiu4tppuxd6yi0tv5ixwuert` (`create_by`),
  KEY `FKb16f6dhwax0cvugpvubf2h3y1` (`update_by`),
  KEY `FKtprj36fx1c1rr7skrml2hmswb` (`college_id`),
  CONSTRAINT `FKb16f6dhwax0cvugpvubf2h3y1` FOREIGN KEY (`update_by`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FKjwiu4tppuxd6yi0tv5ixwuert` FOREIGN KEY (`create_by`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FKtprj36fx1c1rr7skrml2hmswb` FOREIGN KEY (`college_id`) REFERENCES `sys_college` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_vehicle`
--

LOCK TABLES `sys_vehicle` WRITE;
/*!40000 ALTER TABLE `sys_vehicle` DISABLE KEYS */;
INSERT INTO `sys_vehicle` VALUES ('3be4717d-da13-43eb-bc8b-be3ada5d52f6','2021-01-15 15:33:38.848561','2021-01-15 15:33:38.848561','fffffff',1,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','676cf193-fbc8-48c1-8a16-f1984c9071b9'),('a38de747-039e-427c-b033-dfd195ff90de','2021-01-15 15:33:26.349946','2021-02-04 12:04:32.841859','青A.00000',0,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','676cf193-fbc8-48c1-8a16-f1984c9071b9');
/*!40000 ALTER TABLE `sys_vehicle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_violation`
--

DROP TABLE IF EXISTS `sys_violation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_violation` (
  `id` varchar(255) NOT NULL,
  `create_time` datetime(6) NOT NULL,
  `update_time` datetime(6) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK35y2uh373ghoin8xicdpmhtcq` (`create_by`),
  KEY `FKjkfwx10cbhh80057efgprt971` (`update_by`),
  CONSTRAINT `FK35y2uh373ghoin8xicdpmhtcq` FOREIGN KEY (`create_by`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FKjkfwx10cbhh80057efgprt971` FOREIGN KEY (`update_by`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_violation`
--

LOCK TABLES `sys_violation` WRITE;
/*!40000 ALTER TABLE `sys_violation` DISABLE KEYS */;
INSERT INTO `sys_violation` VALUES ('a2095ee8-a8a7-4221-beaa-42a2ae6b416d','2021-01-20 12:08:23.188216','2021-01-20 12:08:23.188216','违规分类2','code002','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114'),('f0571407-fa10-4467-b260-75243ba6ab2e','2021-01-20 12:08:11.767313','2021-01-20 12:08:11.767313','违规分类1','code001','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114');
/*!40000 ALTER TABLE `sys_violation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-04 14:02:07
