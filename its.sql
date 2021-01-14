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
INSERT INTO `sys_menu` VALUES ('012914d9-f821-4fc5-8931-9cd20d2defde','部门新增',2,'#','b76b655d-ae5e-40d4-8bc2-204258e413f4','system:dept:add','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('0554e0d0-3bb9-4faf-b23c-f11e3bc74369','学院新增',2,'#','d78b56ab-3730-4cad-83e6-8e63ddb4969f','system:college:add','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-13 18:30:33','2021-01-13 18:30:33'),('06e31db6-562f-48b9-8023-ee475ff3d878','字典新增',2,'#','79ba0663-46ba-4287-bc3c-1496d8e15b7a','system:dict:add','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('106a997b-015b-4348-8d38-81770a8ab8b9','数据监控',3,'/monitor/data','b18effaa-ff27-4666-a8e0-b24543aadb4e','monitor:data:view','','C','0','1','fa fa-bug','数据监控菜单','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('10f1706d-3b86-4897-8dbd-3440015b3b57','详细信息',3,'#','895aac0e-13a2-4fc3-be43-c331b31d984e','monitor:operlog:detail','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('17cbbbf5-c7c8-43db-b152-94816e63ec9a','用户删除',4,'#','33926c89-e38e-45ee-846d-81f564a8ea38','system:user:remove','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('17ef33ad-80d8-4630-b8a8-37ef8e035147','服务监控',4,'/monitor/server','b18effaa-ff27-4666-a8e0-b24543aadb4e','monitor:server:view','','C','0','1','fa fa-server','服务监控菜单','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('1850297a-c644-49f9-b0da-27d5b20fa4d5','岗位新增',2,'#','c19cc116-57d1-4d95-bb8b-71f457141243','system:post:add','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('1ebf0f90-c3d0-4238-9509-8af4551f2f03','参数新增',2,'#','b0bfd3e5-bc09-4e1a-807a-6247e4d06200','system:config:add','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('298a3927-93a1-4352-bb06-ebf113876fe5','用户导出',5,'#','33926c89-e38e-45ee-846d-81f564a8ea38','system:user:export','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('2e9237e5-6144-4531-a068-9cb96c03b3ed','部门删除',4,'#','b76b655d-ae5e-40d4-8bc2-204258e413f4','system:dept:remove','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('2f9b2b60-6104-4b1a-a3fe-cfbc1eecb4dd','岗位修改',3,'#','c19cc116-57d1-4d95-bb8b-71f457141243','system:post:edit','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('3116680f-4bcb-4eee-bd66-d0c90ed223ed','岗位查询',1,'#','c19cc116-57d1-4d95-bb8b-71f457141243','system:post:list','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('33926c89-e38e-45ee-846d-81f564a8ea38','用户管理',1,'/system/user','eeaaceb9-f8db-4506-81d7-986e4b82fae0','system:user:view','','C','0','1','fa fa-user-o','用户管理菜单','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('345b4c26-3414-40c7-aea3-6c6f9514da34','字典导出',5,'#','79ba0663-46ba-4287-bc3c-1496d8e15b7a','system:dict:export','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('3e4fbcf7-3a8a-4138-a8fb-028fbfb0e6ef','生成代码',5,'#','efc211aa-57cd-46ef-9497-f5825b995967','tool:gen:code','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('42744d14-9fa7-4d23-804a-cfa24e83b800','参数修改',3,'#','b0bfd3e5-bc09-4e1a-807a-6247e4d06200','system:config:edit','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('48d3c830-c362-46b4-a69e-6fc1fbc786de','菜单查询',1,'#','51977a2b-5ff2-41ea-bae4-4bab175af56f','system:menu:search','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('4b1b3613-1921-45aa-94d4-600a27c8e286','公告删除',4,'#','5dcf5a6a-b053-40bf-bb1e-d3139f95b285','system:notice:remove','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('4c61a0a9-815d-4dfd-9d92-91415d3177f3','学院修改',3,'#','d78b56ab-3730-4cad-83e6-8e63ddb4969f','system:college:edit','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-13 18:31:11','2021-01-13 18:31:11'),('4faa3eb9-188d-4328-a88b-24b2e779149f','菜单新增',2,'#','51977a2b-5ff2-41ea-bae4-4bab175af56f','system:menu:add','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('51977a2b-5ff2-41ea-bae4-4bab175af56f','菜单管理',3,'/system/menu','eeaaceb9-f8db-4506-81d7-986e4b82fae0','system:menu:view','','C','0','1','fa fa-th-list','菜单管理菜单','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('53278f2d-4782-4514-8454-a6442ca10da6','日志导出',3,'#','76b96d01-187b-44e5-a98f-c610f49b4797','monitor:logininfor:export','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('53e21a05-8bb3-49ac-9c75-6a769e1b5257','在线查询',1,'#','f5179957-15fe-41a0-9a36-7173481eda5f','monitor:online:list','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('5467570c-c3e9-431b-b1e3-ad9cf9b76549','生成删除',3,'#','efc211aa-57cd-46ef-9497-f5825b995967','tool:gen:remove','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('54bc50a2-a46b-4e4e-b4c6-6d5ecc9d9e69','定时任务',2,'/monitor/job','b18effaa-ff27-4666-a8e0-b24543aadb4e','monitor:job:view','','C','0','1','fa fa-tasks','定时任务菜单','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('584e4737-6597-4f59-98a0-f87c2aab2668','账户解锁',4,'#','76b96d01-187b-44e5-a98f-c610f49b4797','monitor:logininfor:unlock','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('58dd9ff6-55d9-46d4-93ca-3dc9bcdd9ee6','部门修改',3,'#','b76b655d-ae5e-40d4-8bc2-204258e413f4','system:dept:edit','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('59972056-5280-4eb0-b468-6f729c8f7a19','角色管理',2,'/system/role','eeaaceb9-f8db-4506-81d7-986e4b82fae0','system:role:view','menuItem','C','0','1','fa fa-user-secret','角色管理菜单','1b3c1438-beb2-4bab-af86-b6b8dfb91114','d5053e06-fcb2-4855-b9d0-ac5861ac4594','2021-01-06 11:28:07','2021-01-13 15:22:30'),('59a6f5f9-69a3-4066-b584-3ea30e920649','状态修改',5,'#','54bc50a2-a46b-4e4e-b4c6-6d5ecc9d9e69','monitor:job:changeStatus','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('5a4fa9a4-e27d-43d6-978d-1b8509820b0f','字典修改',3,'#','79ba0663-46ba-4287-bc3c-1496d8e15b7a','system:dict:edit','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('5d9e1634-b0f9-4ed6-9a22-d780c1bf0d6b','参数查询',1,'#','b0bfd3e5-bc09-4e1a-807a-6247e4d06200','system:config:list','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('5dcf5a6a-b053-40bf-bb1e-d3139f95b285','通知公告',8,'/system/notice','eeaaceb9-f8db-4506-81d7-986e4b82fae0','system:notice:view','','C','0','1','fa fa-bullhorn','通知公告菜单','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('5f463f1e-4377-4dd7-a0ad-8ee80d9cd2e5','部门查询',1,'#','b76b655d-ae5e-40d4-8bc2-204258e413f4','system:dept:list','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('62d3cbc4-831b-44ce-8139-aa6260b7d99f','岗位导出',5,'#','c19cc116-57d1-4d95-bb8b-71f457141243','system:post:export','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('64f89b00-ec11-4c5a-9e92-29bf34b4a8cc','登录查询',1,'#','76b96d01-187b-44e5-a98f-c610f49b4797','monitor:logininfor:list','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('7332b0dc-4d2a-4c64-9806-8fd1f43bf20c','用户查询',1,'#','33926c89-e38e-45ee-846d-81f564a8ea38','system:user:search','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('76b96d01-187b-44e5-a98f-c610f49b4797','登录日志',2,'/monitor/logininfor','c407a7c7-66ba-4f24-b3aa-1c9c71cee241','monitor:logininfor:view','','C','0','1','fa fa-file-image-o','登录日志菜单','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('79ba0663-46ba-4287-bc3c-1496d8e15b7a','字典管理',6,'/system/dict','eeaaceb9-f8db-4506-81d7-986e4b82fae0','system:dict:view','','C','0','1','fa fa-bookmark-o','字典管理菜单','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('7bb7b8cc-db0b-430e-8f8d-2b0bf665d3db','系统接口',3,'/tool/swagger','ddd9901b-f1ce-4f48-9b28-06ff734a02f1','tool:swagger:view','','C','0','1','fa fa-gg','系统接口菜单','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('7c8ad545-b9fb-4762-91cf-af97090698cf','公告修改',3,'#','5dcf5a6a-b053-40bf-bb1e-d3139f95b285','system:notice:edit','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('7f2e78a1-41ef-4fa0-9367-88797e6b338e','菜单删除',4,'#','51977a2b-5ff2-41ea-bae4-4bab175af56f','system:menu:remove','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('83bf1e8a-a008-4681-9878-91e1a5305330','批量强退',2,'#','f5179957-15fe-41a0-9a36-7173481eda5f','monitor:online:batchForceLogout','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('848fcbaf-a27d-4401-ac34-292622e54f28','参数删除',4,'#','b0bfd3e5-bc09-4e1a-807a-6247e4d06200','system:config:remove','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('895aac0e-13a2-4fc3-be43-c331b31d984e','操作日志',1,'/monitor/operlog','c407a7c7-66ba-4f24-b3aa-1c9c71cee241','monitor:operlog:view','','C','0','1','fa fa-address-book','操作日志菜单','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('8ef0d06c-1eaf-4d47-b5f3-af64d9077fe2','学院删除',4,'#','d78b56ab-3730-4cad-83e6-8e63ddb4969f','system:college:remove','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-13 18:31:44','2021-01-13 18:31:44'),('92589525-79c3-4f76-a29a-26824a000d46','角色修改',3,'#','59972056-5280-4eb0-b468-6f729c8f7a19','system:role:edit','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('9278d780-b47c-493b-9d2d-afe06f6764d8','任务查询',1,'#','54bc50a2-a46b-4e4e-b4c6-6d5ecc9d9e69','monitor:job:list','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('93d5dfe4-3381-4eae-bfc7-527cc2015f3d','角色导出',5,'#','59972056-5280-4eb0-b468-6f729c8f7a19','system:role:export','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('94a8302f-ad2d-49f0-aaa8-7989e65e246e','角色删除',4,'#','59972056-5280-4eb0-b468-6f729c8f7a19','system:role:remove','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('950cea35-83ed-44b3-9dee-df9538234e49','单条强退',3,'#','f5179957-15fe-41a0-9a36-7173481eda5f','monitor:online:forceLogout','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('96334cf4-95e3-47d9-b170-9903b849fa81','字典删除',4,'#','79ba0663-46ba-4287-bc3c-1496d8e15b7a','system:dict:remove','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('9a96204f-c50c-494b-a7cb-584eb3acf6bd','表单构建',1,'/tool/build','ddd9901b-f1ce-4f48-9b28-06ff734a02f1','tool:build:view','','C','0','1','fa fa-wpforms','表单构建菜单','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('9e63296a-6c31-48ef-8123-82139034656a','公告新增',2,'#','5dcf5a6a-b053-40bf-bb1e-d3139f95b285','system:notice:add','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('a610be1a-7670-49b3-9054-d7c6166edd4f','生成修改',2,'#','efc211aa-57cd-46ef-9497-f5825b995967','tool:gen:edit','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('b0bfd3e5-bc09-4e1a-807a-6247e4d06200','参数设置',7,'/system/config','eeaaceb9-f8db-4506-81d7-986e4b82fae0','system:config:view','','C','0','1','fa fa-sun-o','参数设置菜单','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('b18effaa-ff27-4666-a8e0-b24543aadb4e','系统监控',2,'#',NULL,'','','M','0','1','fa fa-video-camera','系统监控目录','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('b34824ba-5df6-4be8-b190-502e423c24bd','任务详细',6,'#','54bc50a2-a46b-4e4e-b4c6-6d5ecc9d9e69','monitor:job:detail','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('b4ff6ff5-62ea-4734-aa3c-bfd7391a90d7','任务修改',3,'#','54bc50a2-a46b-4e4e-b4c6-6d5ecc9d9e69','monitor:job:edit','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('b5e4ac60-75c0-4e48-8665-a6a8dcf632e6','用户新增',2,'#','33926c89-e38e-45ee-846d-81f564a8ea38','system:user:add','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('b6857d09-24e5-44ed-b915-ed649e5457a4','操作删除',2,'#','895aac0e-13a2-4fc3-be43-c331b31d984e','monitor:operlog:remove','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('b76b655d-ae5e-40d4-8bc2-204258e413f4','部门管理',4,'/system/dept','eeaaceb9-f8db-4506-81d7-986e4b82fae0','system:dept:view','','C','0','1','fa fa-outdent','部门管理菜单','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('b7b97205-0fc7-4402-9527-671378d6c99c','预览代码',4,'#','efc211aa-57cd-46ef-9497-f5825b995967','tool:gen:preview','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('ba050e2e-2f49-4168-af4a-674da78263cb','重置密码',7,'#','33926c89-e38e-45ee-846d-81f564a8ea38','system:user:resetPwd','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('bfe24b4b-d89b-42ea-b043-d3d3c92e390e','日志导出',4,'#','895aac0e-13a2-4fc3-be43-c331b31d984e','monitor:operlog:export','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('c19cc116-57d1-4d95-bb8b-71f457141243','岗位管理',5,'/system/post','eeaaceb9-f8db-4506-81d7-986e4b82fae0','system:post:view','','C','0','1','fa fa-address-card-o','岗位管理菜单','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('c407a7c7-66ba-4f24-b3aa-1c9c71cee241','日志管理',9,'#','eeaaceb9-f8db-4506-81d7-986e4b82fae0','','','M','0','1','fa fa-pencil-square-o','日志管理菜单','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('c77aaecf-ee8c-4f63-8314-58f305e408f4','公告查询',1,'#','5dcf5a6a-b053-40bf-bb1e-d3139f95b285','system:notice:list','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('cc1e3a72-4b68-49fe-ac60-9ffee7e03f40','任务新增',2,'#','54bc50a2-a46b-4e4e-b4c6-6d5ecc9d9e69','monitor:job:add','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('cc3a32cb-f6fe-44fe-b0a3-6a5780d3e65b','字典查询',1,'#','79ba0663-46ba-4287-bc3c-1496d8e15b7a','system:dict:list','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('cc636cfe-8ecb-4fed-af3e-4b9bf0570ab6','任务导出',7,'#','54bc50a2-a46b-4e4e-b4c6-6d5ecc9d9e69','monitor:job:export','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('d2c653c8-d21f-4927-843d-c54e08ab2fd3','岗位删除',4,'#','c19cc116-57d1-4d95-bb8b-71f457141243','system:post:remove','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('d3318d63-9dfa-43dc-b292-d715da994364','操作查询',1,'#','895aac0e-13a2-4fc3-be43-c331b31d984e','monitor:operlog:list','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('d3692db5-b118-44bb-b09a-c0e3d8b70346','角色查询',1,'#','59972056-5280-4eb0-b468-6f729c8f7a19','system:role:search','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('d78b56ab-3730-4cad-83e6-8e63ddb4969f','学院管理',4,'/system/college','eeaaceb9-f8db-4506-81d7-986e4b82fae0','system:college:view','menuItem','C',NULL,NULL,'fa fa-graduation-cap',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-13 18:28:46','2021-01-13 18:28:46'),('dbb0e1d1-75de-4a4f-8fae-31259543883f','菜单修改',3,'#','51977a2b-5ff2-41ea-bae4-4bab175af56f','system:menu:edit','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('ddd9901b-f1ce-4f48-9b28-06ff734a02f1','系统工具',3,'#',NULL,'','','M','0','1','fa fa-bars','系统工具目录','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('e1925f9c-960b-45fc-981d-e62ebfb4e143','参数导出',5,'#','b0bfd3e5-bc09-4e1a-807a-6247e4d06200','system:config:export','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('e3af6408-1161-4167-8411-5b7e2df576b5','用户导入',6,'#','33926c89-e38e-45ee-846d-81f564a8ea38','system:user:import','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('e47b4b05-a648-48c0-b183-1c2b787a147b','生成查询',1,'#','efc211aa-57cd-46ef-9497-f5825b995967','tool:gen:list','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('e8701b76-4773-4785-8261-0d33f25ac401','角色新增',2,'#','59972056-5280-4eb0-b468-6f729c8f7a19','system:role:add','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('ebe31c6c-3d23-46b6-a49d-c9beb157d73a','任务删除',4,'#','54bc50a2-a46b-4e4e-b4c6-6d5ecc9d9e69','monitor:job:remove','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('eeaaceb9-f8db-4506-81d7-986e4b82fae0','系统管理',1,'#',NULL,'','','M','0','1','fa fa-gear','系统管理目录','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('efc211aa-57cd-46ef-9497-f5825b995967','代码生成',2,'/tool/gen','ddd9901b-f1ce-4f48-9b28-06ff734a02f1','tool:gen:view','','C','0','1','fa fa-code','代码生成菜单','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('f5179957-15fe-41a0-9a36-7173481eda5f','在线用户',1,'/monitor/online','b18effaa-ff27-4666-a8e0-b24543aadb4e','monitor:online:view','','C','0','1','fa fa-user-circle','在线用户菜单','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('f7d415c3-6425-431b-be19-540aa7a0b3d4','用户修改',3,'#','33926c89-e38e-45ee-846d-81f564a8ea38','system:user:edit','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('fad86ae3-7c93-418d-b14f-87125259800c','缓存监控',5,'/monitor/cache','b18effaa-ff27-4666-a8e0-b24543aadb4e','monitor:cache:view','','C','0','1','fa fa-cube','缓存监控菜单','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('fe0082ae-af19-4bc8-9284-c346ba0c080a','学院查询',1,'#','d78b56ab-3730-4cad-83e6-8e63ddb4969f','system:college:search','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-13 18:29:56','2021-01-13 18:29:56'),('feb0ea06-67c3-4c7c-bb68-4e0489270b77','登录删除',2,'#','76b96d01-187b-44e5-a98f-c610f49b4797','monitor:logininfor:remove','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
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
INSERT INTO `sys_role` VALUES ('90b7f916-9ea0-46c7-b2a7-81ca885e5850','2021-01-14 10:58:50.397386','2021-01-14 10:58:50.397386','普通角色','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114',''),('a2a84058-8660-4393-a89d-6d1064f9b43a','2021-01-12 15:36:14.615792','2021-01-13 18:32:00.115048','超级管理员','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','超级管理员,具有最大权限的角色');
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
INSERT INTO `sys_role_menu` VALUES ('a2a84058-8660-4393-a89d-6d1064f9b43a','eeaaceb9-f8db-4506-81d7-986e4b82fae0'),('a2a84058-8660-4393-a89d-6d1064f9b43a','33926c89-e38e-45ee-846d-81f564a8ea38'),('a2a84058-8660-4393-a89d-6d1064f9b43a','17cbbbf5-c7c8-43db-b152-94816e63ec9a'),('a2a84058-8660-4393-a89d-6d1064f9b43a','298a3927-93a1-4352-bb06-ebf113876fe5'),('a2a84058-8660-4393-a89d-6d1064f9b43a','7332b0dc-4d2a-4c64-9806-8fd1f43bf20c'),('a2a84058-8660-4393-a89d-6d1064f9b43a','b5e4ac60-75c0-4e48-8665-a6a8dcf632e6'),('a2a84058-8660-4393-a89d-6d1064f9b43a','ba050e2e-2f49-4168-af4a-674da78263cb'),('a2a84058-8660-4393-a89d-6d1064f9b43a','e3af6408-1161-4167-8411-5b7e2df576b5'),('a2a84058-8660-4393-a89d-6d1064f9b43a','f7d415c3-6425-431b-be19-540aa7a0b3d4'),('a2a84058-8660-4393-a89d-6d1064f9b43a','51977a2b-5ff2-41ea-bae4-4bab175af56f'),('a2a84058-8660-4393-a89d-6d1064f9b43a','48d3c830-c362-46b4-a69e-6fc1fbc786de'),('a2a84058-8660-4393-a89d-6d1064f9b43a','4faa3eb9-188d-4328-a88b-24b2e779149f'),('a2a84058-8660-4393-a89d-6d1064f9b43a','7f2e78a1-41ef-4fa0-9367-88797e6b338e'),('a2a84058-8660-4393-a89d-6d1064f9b43a','dbb0e1d1-75de-4a4f-8fae-31259543883f'),('a2a84058-8660-4393-a89d-6d1064f9b43a','59972056-5280-4eb0-b468-6f729c8f7a19'),('a2a84058-8660-4393-a89d-6d1064f9b43a','92589525-79c3-4f76-a29a-26824a000d46'),('a2a84058-8660-4393-a89d-6d1064f9b43a','93d5dfe4-3381-4eae-bfc7-527cc2015f3d'),('a2a84058-8660-4393-a89d-6d1064f9b43a','94a8302f-ad2d-49f0-aaa8-7989e65e246e'),('a2a84058-8660-4393-a89d-6d1064f9b43a','d3692db5-b118-44bb-b09a-c0e3d8b70346'),('a2a84058-8660-4393-a89d-6d1064f9b43a','e8701b76-4773-4785-8261-0d33f25ac401'),('a2a84058-8660-4393-a89d-6d1064f9b43a','d78b56ab-3730-4cad-83e6-8e63ddb4969f'),('a2a84058-8660-4393-a89d-6d1064f9b43a','0554e0d0-3bb9-4faf-b23c-f11e3bc74369'),('a2a84058-8660-4393-a89d-6d1064f9b43a','4c61a0a9-815d-4dfd-9d92-91415d3177f3'),('a2a84058-8660-4393-a89d-6d1064f9b43a','8ef0d06c-1eaf-4d47-b5f3-af64d9077fe2'),('a2a84058-8660-4393-a89d-6d1064f9b43a','fe0082ae-af19-4bc8-9284-c346ba0c080a'),('90b7f916-9ea0-46c7-b2a7-81ca885e5850','eeaaceb9-f8db-4506-81d7-986e4b82fae0'),('90b7f916-9ea0-46c7-b2a7-81ca885e5850','d78b56ab-3730-4cad-83e6-8e63ddb4969f'),('90b7f916-9ea0-46c7-b2a7-81ca885e5850','0554e0d0-3bb9-4faf-b23c-f11e3bc74369'),('90b7f916-9ea0-46c7-b2a7-81ca885e5850','4c61a0a9-815d-4dfd-9d92-91415d3177f3'),('90b7f916-9ea0-46c7-b2a7-81ca885e5850','8ef0d06c-1eaf-4d47-b5f3-af64d9077fe2'),('90b7f916-9ea0-46c7-b2a7-81ca885e5850','fe0082ae-af19-4bc8-9284-c346ba0c080a');
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
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
  `phone_number` char(11) NOT NULL,
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
INSERT INTO `sys_user` VALUES ('1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 16:04:50.000000','2021-01-14 10:53:42.291287','超级管理员','$2a$10$b3ezmRiBwb0nJjIRk3JWVOeTmcS7mPDOD5T887jLWfG2/GDdgZptK','admin','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-14 10:34:55.128949','男','zhizhufan@foxmail.com','13384614120','','73f1218d-9249-42a2-9e78-a5c7963fbec1'),('d5053e06-fcb2-4855-b9d0-ac5861ac4594','2021-01-12 15:39:51.697828','2021-01-14 10:53:13.022440','刘凯','$2a$10$b3ezmRiBwb0nJjIRk3JWVOeTmcS7mPDOD5T887jLWfG2/GDdgZptK','liukai','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-13 15:20:16.633081','男','zhizhufan@foxmail.com','13384614120','','c76098f8-efbb-4957-a2ab-9b332a09d52d');
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
INSERT INTO `sys_user_role` VALUES ('1b3c1438-beb2-4bab-af86-b6b8dfb91114','a2a84058-8660-4393-a89d-6d1064f9b43a'),('d5053e06-fcb2-4855-b9d0-ac5861ac4594','a2a84058-8660-4393-a89d-6d1064f9b43a');
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-14 11:21:11