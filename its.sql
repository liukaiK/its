-- MySQL dump 10.13  Distrib 5.7.30, for macos10.14 (x86_64)
--
-- Host: localhost    Database: its
-- ------------------------------------------------------
-- Server version	5.7.30-log

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
  `place` varchar(255) DEFAULT NULL COMMENT '违规地点',
  `status` tinyint(4) DEFAULT NULL,
  `camera_id` varchar(255) DEFAULT NULL,
  `vehicle_id` varchar(255) DEFAULT NULL,
  `violation_id` varchar(255) DEFAULT NULL,
  `violation_time` datetime(6) DEFAULT NULL,
  `license_plate_number` varchar(255) DEFAULT NULL COMMENT '违规车辆车牌号',
  `lane_number` varchar(255) DEFAULT NULL COMMENT '车道号',
  `vehicle_color` varchar(255) DEFAULT NULL COMMENT '违规车辆颜色',
  `time` datetime(6) DEFAULT NULL COMMENT '违规时间',
  `approval_time` datetime(6) DEFAULT NULL COMMENT '审批时间或作废时间',
  `vehicle_size` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `speed` tinyint(4) DEFAULT NULL COMMENT '车速',
  `score_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs6ftkjagbnlrgkuw4cjn3sehc` (`camera_id`),
  KEY `FKkkwmjljmrrx45ng53lw3annwo` (`vehicle_id`),
  KEY `FKfs1jwhcxqlbujxpc1eip4n4hq` (`violation_id`),
  KEY `eve_event_sys_score_id_fk` (`score_id`),
  KEY `eve_event_license_plate_number_index` (`license_plate_number`),
  CONSTRAINT `FKfs1jwhcxqlbujxpc1eip4n4hq` FOREIGN KEY (`violation_id`) REFERENCES `sys_violation_type` (`id`),
  CONSTRAINT `FKkkwmjljmrrx45ng53lw3annwo` FOREIGN KEY (`vehicle_id`) REFERENCES `sys_vehicle` (`id`),
  CONSTRAINT `FKs6ftkjagbnlrgkuw4cjn3sehc` FOREIGN KEY (`camera_id`) REFERENCES `sys_camera` (`id`),
  CONSTRAINT `eve_event_sys_score_id_fk` FOREIGN KEY (`score_id`) REFERENCES `sys_score` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eve_event`
--

LOCK TABLES `eve_event` WRITE;
/*!40000 ALTER TABLE `eve_event` DISABLE KEYS */;
INSERT INTO `eve_event` VALUES ('0300ee6d-56a5-413a-8bc7-64a9f88d3051',NULL,'校外街拍二公寓方向',1,'ee7d1f7d-a86c-4e5d-b4d4-91f66cf90f80',NULL,NULL,NULL,'黑A9R798','1','Black','2021-02-19 09:24:45.000000','2021-02-24 11:19:18.040000','小型车','/group1/M00/00/B3/CqMUC2AvE16AR21_AA-3zDrhs3Q931.png',20,'14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d'),('2988b7a1-a33a-4070-81a5-956870c19a7f',NULL,'校外街拍二公寓方向',1,'ee7d1f7d-a86c-4e5d-b4d4-91f66cf90f80',NULL,NULL,NULL,'黑A57A76','1','Silver','2021-02-19 09:27:24.000000','2021-02-24 11:38:12.675000','小型车','/group1/M00/00/B4/CqMUC2AvE_2ALEgJAA9mUhtw4Fg464.png',26,'14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d'),('313ac8fd-2832-4bbe-9c76-68b7513626db',NULL,'校外街拍二公寓方向',2,'ee7d1f7d-a86c-4e5d-b4d4-91f66cf90f80',NULL,NULL,NULL,'黑A10ZG9','1','Gray','2021-02-19 09:27:40.000000','2021-02-24 11:25:33.378000','小型车','/group1/M00/00/B4/CqMUC2AvFA6AbSFcAA_0tsPTJlU544.png',33,'14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d'),('39f0a961-2419-4659-9c45-a8bd4677960f',NULL,'校外街拍二公寓方向',0,'ee7d1f7d-a86c-4e5d-b4d4-91f66cf90f80',NULL,NULL,NULL,'黑AB163L','1','Black','2021-02-19 09:24:22.000000','2021-02-20 15:57:28.473000','小型车','/group1/M00/00/B3/CqMUC2AvE0mAbFV_AA8yYg9vF3U169.png',33,'14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d'),('464e51f8-24a7-4efe-b99b-46b0e59ef0fe',NULL,'航天路拍保卫科方向',0,'bfcf913f-568d-4afb-93ff-206c805fd793',NULL,NULL,NULL,'黑A4JU66','1','White','2021-02-19 09:29:44.000000','2021-02-20 15:57:31.055000','小型车','/group1/M00/00/B4/CqMUC2AvE52AIfsAABAJsjV1rZQ337.png',36,'14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d'),('4f8cc217-c89f-4db1-ba0d-e5f0d317850e',NULL,'校外街拍游泳馆方向',1,'cf139bd8-2188-4bd6-94dd-b588ae4660b9',NULL,NULL,NULL,'黑AQ213D','1','Blue','2021-02-19 09:27:41.000000','2021-02-24 11:25:42.606000','小型车','/group1/M00/00/B4/CqMUC2AvE6WAWCUTABCblI6MxMA167.png',25,'14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d'),('5223e036-30cc-41f5-9bfd-2a921c240c3f',NULL,'校外街拍二公寓方向',0,'ee7d1f7d-a86c-4e5d-b4d4-91f66cf90f80',NULL,NULL,NULL,'黑A967TL','1','White','2021-02-19 09:26:09.000000','2021-02-20 15:57:35.777000','小型车','/group1/M00/00/B4/CqMUC2AvE7OALVldAA3imJkqHrg814.png',35,'14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d'),('55d8ab1b-2ead-4bdb-8dbe-5c9b2e9c3ef4',NULL,'校外街拍二公寓方向',0,'ee7d1f7d-a86c-4e5d-b4d4-91f66cf90f80',NULL,NULL,NULL,'黑A967TL','1','White','2021-02-19 09:26:09.000000','2021-02-20 15:57:38.269000','小型车','/group1/M00/00/B4/CqMUC2AvE7KAOM20AA4sV2RjsHQ685.png',35,'14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d'),('6e986603-8b36-4af1-a013-137f10bd713f',NULL,'校外街拍二公寓方向',0,'ee7d1f7d-a86c-4e5d-b4d4-91f66cf90f80',NULL,NULL,NULL,'黑AQ2478','1','Black','2021-02-19 09:28:02.000000','2021-02-20 15:57:40.482000','小型车','/group1/M00/00/B4/CqMUC2AvFCOANRcgABBsZC172VA225.png',34,'14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d'),('71d6e3d5-edb0-4f96-9c35-33c1ece6aade',NULL,'校外街拍二公寓方向',0,'ee7d1f7d-a86c-4e5d-b4d4-91f66cf90f80',NULL,NULL,NULL,'黑A4JU66','1','White','2021-02-19 09:23:06.000000','2021-02-20 15:57:43.067000','小型车','/group1/M00/00/B3/CqMUC2AvEvyAA0DyAA-rd-vpXEU831.png',28,'14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d'),('7aaf10cc-213b-4e25-864c-80164193239e',NULL,'校外街拍二公寓方向',0,'ee7d1f7d-a86c-4e5d-b4d4-91f66cf90f80',NULL,NULL,NULL,'黑A967TL','1','White','2021-02-19 09:26:09.000000','2021-02-20 15:57:45.633000','小型车','/group1/M00/00/B4/CqMUC2AvE7OAa8qOAA6makyLoyc154.png',35,'14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d'),('812acee1-a06a-4af8-b9d9-2f78763714b2',NULL,'航天路拍保卫科方向',0,'bfcf913f-568d-4afb-93ff-206c805fd793',NULL,NULL,NULL,'黑A4JU66','1','White','2021-02-19 09:29:44.000000','2021-02-20 15:57:48.141000','小型车','/group1/M00/00/B4/CqMUC2AvE52AR5wUAA98SxGQuK8203.png',36,'14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d'),('8c56209f-0f74-427c-9a63-8a37c79055dd',NULL,'教化西街',0,'62cd8466-deb1-4d80-87e6-f3e4285a507b',NULL,NULL,NULL,'黑A8M6Z6','1','Black','2021-02-19 09:17:46.000000','2021-02-20 15:57:50.558000','小型车','/group1/M00/00/B3/CqMUC2AvE16ALc0tABALEddeXTY964.png',39,'14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d'),('8ebf6b61-c6a1-4c79-875c-b8f37e096e79',NULL,'航天路拍保卫科方向',0,'bfcf913f-568d-4afb-93ff-206c805fd793',NULL,NULL,NULL,'黑AB163L','1','Black','2021-02-19 09:30:47.000000','2021-02-20 15:57:53.033000','小型车','/group1/M00/00/B4/CqMUC2AvE92AW0ELABAoOG9WhVg971.png',44,'14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d'),('a0e1c8e2-4c38-4f36-bb8b-38a693a2c453',NULL,'校外街拍二公寓方向',0,'ee7d1f7d-a86c-4e5d-b4d4-91f66cf90f80',NULL,NULL,NULL,'黑AQ2478','1','Black','2021-02-19 09:28:02.000000','2021-02-20 15:57:55.547000','小型车','/group1/M00/00/B4/CqMUC2AvFCOASpbzABBtCLcIpos973.png',34,'14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d'),('a5e62ba3-371d-4ae6-b0e7-8239761b5174',NULL,'校外街拍二公寓方向',0,'ee7d1f7d-a86c-4e5d-b4d4-91f66cf90f80',NULL,NULL,NULL,'黑A1GM20','1','Black','2021-02-19 09:24:30.000000','2021-02-20 15:57:58.347000','小型车','/group1/M00/00/B3/CqMUC2AvE0-AfcwpAA-aKwOuJ9s756.png',29,'14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d'),('a61df901-1b29-4a88-9a6f-a5cc6b6752fe',NULL,'校外街拍二公寓方向',0,'ee7d1f7d-a86c-4e5d-b4d4-91f66cf90f80',NULL,NULL,NULL,'黑A51M53','1','Silver','2021-02-19 09:28:54.000000','2021-02-24 11:08:59.507000','小型车','/group1/M00/00/B4/CqMUC2AvFFiAIkimAA-CVM_e3_w010.png',43,'14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d'),('a8079716-6e38-4774-9b8e-718cc7fd1ebb',NULL,'校外街拍游泳馆方向',0,'cf139bd8-2188-4bd6-94dd-b588ae4660b9',NULL,NULL,NULL,'黑A6068F','1','Black','2021-02-19 09:25:22.000000','2021-02-24 11:09:01.862000','小型车','/group1/M00/00/B3/CqMUC2AvExqAOxBGABDuddalaY8976.png',35,'14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d'),('b4ee2477-f914-4aa0-b924-ceaf1624b4ac',NULL,'校外街拍游泳馆方向',0,'cf139bd8-2188-4bd6-94dd-b588ae4660b9',NULL,NULL,NULL,'黑A058LH','1','White','2021-02-19 09:29:50.000000','2021-02-24 11:09:04.304000','小型车','/group1/M00/00/B4/CqMUC2AvFCeATrykABFHEfK24M4578.png',42,'14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d'),('b9101549-190b-4dac-b502-6d6f8f1241f4',NULL,'校外街拍二公寓方向',0,'ee7d1f7d-a86c-4e5d-b4d4-91f66cf90f80',NULL,NULL,NULL,'黑AB163L','1','Black','2021-02-19 09:24:22.000000','2021-02-24 11:06:22.656000','小型车','/group1/M00/00/B3/CqMUC2AvE0eAW7dlAA8xRJ6jsSk363.png',33,'14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d'),('bc2ee503-3ece-4f3d-b2b7-0d65a03dfe22',NULL,'校外街拍游泳馆方向',0,'cf139bd8-2188-4bd6-94dd-b588ae4660b9',NULL,NULL,NULL,'黑A058LH','1','White','2021-02-19 09:29:50.000000','2021-02-24 11:09:06.608000','小型车','/group1/M00/00/B4/CqMUC2AvFCeAHVGdABBFWYr5vns308.png',42,'14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d'),('bf01f60d-828a-4d9a-8fc9-68e67d8c74a2',NULL,'航天路拍保卫科方向',0,'bfcf913f-568d-4afb-93ff-206c805fd793',NULL,NULL,NULL,'黑A4JU66','1','White','2021-02-19 09:29:44.000000','2021-02-24 11:06:05.705000','小型车','/group1/M00/00/B4/CqMUC2AvE52ADEyPABAJsqEoJnk722.png',36,'14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d'),('c113fc0d-daa7-4ea6-8945-49d611754088',NULL,'校外街拍游泳馆方向',0,'cf139bd8-2188-4bd6-94dd-b588ae4660b9',NULL,NULL,NULL,'黑E558BW','1','Blue','2021-02-19 09:27:33.000000','2021-02-24 11:09:09.272000','小型车','/group1/M00/00/B4/CqMUC2AvE52AYHvrABAZQI1F2Po327.png',34,'14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d'),('c1243b51-a102-4f22-80be-97fc0b3e45de',NULL,'校外街拍二公寓方向',0,'ee7d1f7d-a86c-4e5d-b4d4-91f66cf90f80',NULL,NULL,NULL,'黑A10ZG9','1','Gray','2021-02-19 09:27:40.000000','2021-02-24 11:06:20.002000','小型车','/group1/M00/00/B4/CqMUC2AvFA6APVrMABAH22KRwyU653.png',33,'14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d'),('c7f0dc7a-bd68-4676-83de-80b9da05fbb4',NULL,'航天路拍保卫科方向',0,'bfcf913f-568d-4afb-93ff-206c805fd793',NULL,NULL,NULL,'黑AB163L','1','Black','2021-02-19 09:30:47.000000','2021-02-24 11:09:11.615000','小型车','/group1/M00/00/B4/CqMUC2AvE92Ac9qZABAoOINV7ec736.png',44,'14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d'),('c9c204ef-0eda-4ae8-bb47-4a4676d2d244',NULL,'校外街拍游泳馆方向',0,'cf139bd8-2188-4bd6-94dd-b588ae4660b9',NULL,NULL,NULL,'黑A058LH','1','White','2021-02-19 09:29:50.000000','2021-02-24 11:09:13.930000','小型车','/group1/M00/00/B4/CqMUC2AvFCeAVm_5ABFGd14H-c8714.png',42,'14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d'),('caebecff-7c46-454c-80ea-4803e23ff90d',NULL,'校外街拍二公寓方向',0,'ee7d1f7d-a86c-4e5d-b4d4-91f66cf90f80',NULL,NULL,NULL,'黑AQ2478','1','Black','2021-02-19 09:28:02.000000','2021-02-24 11:06:25.120000','小型车','/group1/M00/00/B4/CqMUC2AvFCOALTyuABAcnGCotSA694.png',34,'14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d'),('db79b654-df4e-401a-8190-9b3197582835',NULL,'校外街拍二公寓方向',0,'ee7d1f7d-a86c-4e5d-b4d4-91f66cf90f80',NULL,NULL,NULL,'黑AB163L','1','Black','2021-02-19 09:24:22.000000','2021-02-24 11:09:16.513000','小型车','/group1/M00/00/B3/CqMUC2AvE0mAeCtdAA9GzP0Rfy0598.png',33,'14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d'),('df85845a-59b0-4c4f-b628-a06d32643dcc',NULL,'校外街拍二公寓方向',0,'ee7d1f7d-a86c-4e5d-b4d4-91f66cf90f80',NULL,NULL,NULL,'黑A88ZL6','1','Silver','2021-02-19 09:27:30.000000','2021-02-24 11:09:19.082000','小型车','/group1/M00/00/B4/CqMUC2AvFAKAPKNLABC071jsxG4853.png',25,'14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d'),('e23bddf0-b0e0-4bce-a129-8fbf70af3852',NULL,'校外街拍二公寓方向',0,'ee7d1f7d-a86c-4e5d-b4d4-91f66cf90f80',NULL,NULL,NULL,'黑A10ZG9','1','Gray','2021-02-19 09:27:40.000000','2021-02-24 11:06:27.496000','小型车','/group1/M00/00/B4/CqMUC2AvFA6AaoO5ABAH23YviZo696.png',33,'14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d'),('e78efc7a-dea7-45ec-9364-8fbcc02580ed',NULL,'校外街拍游泳馆方向',0,'cf139bd8-2188-4bd6-94dd-b588ae4660b9',NULL,NULL,NULL,'黑E558BW','1','Blue','2021-02-19 09:27:33.000000','2021-02-24 11:09:21.458000','小型车','/group1/M00/00/B4/CqMUC2AvE52AH_SQABCzdMsQ9P8357.png',34,'14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d'),('ecc699f8-8d36-4192-80bc-4c2db521e825',NULL,'校外街拍游泳馆方向',0,'cf139bd8-2188-4bd6-94dd-b588ae4660b9',NULL,NULL,NULL,'黑A6068F','1','Black','2021-02-19 09:25:22.000000','2021-02-24 11:08:55.452000','小型车','/group1/M00/00/B3/CqMUC2AvExqAN4HKABDuLwUg2bY380.png',35,'14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d'),('f495aca2-56a4-4cd3-9e77-3b6a2929eae9',NULL,'校外街拍二公寓方向',0,'ee7d1f7d-a86c-4e5d-b4d4-91f66cf90f80',NULL,NULL,NULL,'黑A51M53','1','Silver','2021-02-19 09:28:54.000000','2021-02-24 11:08:42.478000','小型车','/group1/M00/00/B4/CqMUC2AvFFmAcbeNAA_ahin5EXM336.png',43,'14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d'),('f5ec9a72-53ff-49a9-a39d-14701fc60751',NULL,'校外街拍游泳馆方向',0,'cf139bd8-2188-4bd6-94dd-b588ae4660b9',NULL,NULL,NULL,'黑A6T8N8','1','Blue','2021-02-19 09:27:37.000000','2021-02-24 11:08:40.173000','小型车','/group1/M00/00/B4/CqMUC2AvE6KAXG3fABC64BKv3TY824.png',30,'14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d'),('f96413f0-5462-4cbd-9e17-ae950c53dce3',NULL,'校外街拍游泳馆方向',0,'cf139bd8-2188-4bd6-94dd-b588ae4660b9',NULL,NULL,NULL,'黑E558BW','1','Blue','2021-02-19 09:27:33.000000','2021-02-24 11:08:37.751000','小型车','/group1/M00/00/B3/CqMUC2AvE52ABgNVABCx9aCo3Yw675.png',34,'14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d'),('fbc05775-d67a-4df6-8d40-55cb8ae767ed',NULL,'航天路拍保卫科方向',0,'bfcf913f-568d-4afb-93ff-206c805fd793',NULL,NULL,NULL,'黑AB163L','1','Black','2021-02-19 09:30:47.000000','2021-02-24 11:08:35.594000','小型车','/group1/M00/00/B4/CqMUC2AvE92AGJTfAA-fgCvJfXI726.png',44,'14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d'),('fdfaeb4f-55c7-4f37-9ede-8d9753365179',NULL,'校外街拍游泳馆方向',0,'cf139bd8-2188-4bd6-94dd-b588ae4660b9',NULL,NULL,NULL,'黑A6068F','1','Black','2021-02-19 09:25:22.000000','2021-02-24 11:08:33.198000','小型车','/group1/M00/00/B3/CqMUC2AvExqAEgAcABArCwp6Wbo615.png',35,'14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d'),('ffebc68f-9a01-458b-a99b-6557ffb8c10e',NULL,'校外街拍二公寓方向',0,'ee7d1f7d-a86c-4e5d-b4d4-91f66cf90f80',NULL,NULL,NULL,'黑A51M53','1','Silver','2021-02-19 09:28:55.000000','2021-02-24 11:08:30.722000','小型车','/group1/M00/00/B4/CqMUC2AvFFiAS9VhAA7MouZylB8008.png',43,'14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d');
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
  `score_id` varchar(255) DEFAULT NULL,
  `license_plate_number` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `region_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt4gs77cya4yvb7u5xnhe7kulk` (`college_id`),
  KEY `FKoaies9yh8fbrxtskkoixvmg1r` (`score_id`),
  CONSTRAINT `FKoaies9yh8fbrxtskkoixvmg1r` FOREIGN KEY (`score_id`) REFERENCES `sys_score` (`id`),
  CONSTRAINT `FKt4gs77cya4yvb7u5xnhe7kulk` FOREIGN KEY (`college_id`) REFERENCES `sys_college` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eve_record`
--

LOCK TABLES `eve_record` WRITE;
/*!40000 ALTER TABLE `eve_record` DISABLE KEYS */;
INSERT INTO `eve_record` VALUES ('07408755-cfb6-4f07-af6c-6c456cf37485',NULL,'2021-02-24 11:25:42.636000',NULL,'2021-02-24 11:25:42.636000',20,NULL,NULL,'黑AQ213D','2021-02-19 09:27:41','限速30公里道路'),('19cc4651-6c60-44a3-b657-1ac3bfb12634',NULL,'2021-02-24 11:19:18.050000',NULL,'2021-02-24 11:19:18.050000',20,NULL,NULL,'黑A9R798','2021-02-19 09:24:45','限速30公里道路'),('90587101-6e4c-484b-8850-024624d819e0',NULL,'2021-02-24 11:38:12.688000',NULL,'2021-02-24 11:38:12.688000',20,NULL,NULL,'黑A57A76','2021-02-19 09:27:24','限速30公里道路');
/*!40000 ALTER TABLE `eve_record` ENABLE KEYS */;
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
  CONSTRAINT `FK65votwhc893dr3pq4xo4vp6jt` FOREIGN KEY (`region_id`) REFERENCES `sys_region` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_camera`
--

LOCK TABLES `sys_camera` WRITE;
/*!40000 ALTER TABLE `sys_camera` DISABLE KEYS */;
INSERT INTO `sys_camera` VALUES ('580d15d3-d433-47a0-8d19-7e5ff7f0319d','2021-01-20 10:26:47.829923','2021-02-18 13:48:26.429531','厂家2','192.168.3.85','摄像头2','航天路拍2H栋方向','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','779d6d9b-fa7f-4eac-8d22-3c7020c1c5c0'),('62cd8466-deb1-4d80-87e6-f3e4285a507b','2021-02-18 13:50:13.051099','2021-02-18 16:45:56.479528','','192.168.3.85','摄像头3','教化西街',NULL,NULL,'779d6d9b-fa7f-4eac-8d22-3c7020c1c5c0'),('bfcf913f-568d-4afb-93ff-206c805fd793','2021-02-18 13:52:12.761284','2021-02-18 16:45:48.425832','','192.168.3.85','摄像头4','航天路拍保卫科方向',NULL,NULL,'779d6d9b-fa7f-4eac-8d22-3c7020c1c5c0'),('cf139bd8-2188-4bd6-94dd-b588ae4660b9','2021-01-20 10:21:42.231205','2021-02-18 16:45:17.819465','厂家1222','127.0.0.1','摄像头1','校外街拍游泳馆方向','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','779d6d9b-fa7f-4eac-8d22-3c7020c1c5c0'),('cf54f3ab-98b5-4ddd-93e5-5642dc0a2086','2021-02-18 13:52:57.266745','2021-02-18 16:45:04.205148','','192.168.3.85','摄像头5','教化东街',NULL,NULL,'779d6d9b-fa7f-4eac-8d22-3c7020c1c5c0'),('ee7d1f7d-a86c-4e5d-b4d4-91f66cf90f80','2021-02-18 13:53:34.483517','2021-02-18 13:53:34.483517','','192.168.3.85','摄像头6','校外街拍二公寓方向',NULL,NULL,'779d6d9b-fa7f-4eac-8d22-3c7020c1c5c0');
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
  CONSTRAINT `FK2gcg73krc9bi6e816wq6dhqlb` FOREIGN KEY (`parent_id`) REFERENCES `sys_college` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_college`
--

LOCK TABLES `sys_college` WRITE;
/*!40000 ALTER TABLE `sys_college` DISABLE KEYS */;
INSERT INTO `sys_college` VALUES ('0009d9b9-19f0-49ed-bbf2-931f8eab55ac','2021-02-09 10:18:39.226934','2021-02-09 10:18:39.226934','哈尔滨工业大学',NULL,NULL,'4e7054f9-b5e8-4ee9-96db-9295614ae313',NULL),('034803f0-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','图书馆',NULL,NULL,'dec9e4c3-3f59-447a-b4a4-a18523554948',NULL),('034f5268-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','档案馆',NULL,NULL,'dec9e4c3-3f59-447a-b4a4-a18523554948',NULL),('035c9842-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','网络与信息中心',NULL,NULL,'dec9e4c3-3f59-447a-b4a4-a18523554948',NULL),('0362ce56-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','校友工作办公室/教育发展基金会秘书处',NULL,NULL,'dec9e4c3-3f59-447a-b4a4-a18523554948',NULL),('036e77c4-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','《哈尔滨工业大学学报》编辑部',NULL,NULL,'dec9e4c3-3f59-447a-b4a4-a18523554948',NULL),('037453e2-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','继续教育学院',NULL,NULL,'dec9e4c3-3f59-447a-b4a4-a18523554948',NULL),('037f6d4a-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','分析测试与计算中心',NULL,NULL,'dec9e4c3-3f59-447a-b4a4-a18523554948',NULL),('038c763e-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','后勤集团',NULL,NULL,'dec9e4c3-3f59-447a-b4a4-a18523554948',NULL),('0392d812-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','校医院',NULL,NULL,'dec9e4c3-3f59-447a-b4a4-a18523554948',NULL),('039d6b7e-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','建筑设计研究院',NULL,NULL,'dec9e4c3-3f59-447a-b4a4-a18523554948',NULL),('03a88086-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','哈工大资产经营有限公司/哈工大技术转移中心',NULL,NULL,'dec9e4c3-3f59-447a-b4a4-a18523554948',NULL),('03b60436-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','哈工大出版社有限公司',NULL,NULL,'dec9e4c3-3f59-447a-b4a4-a18523554948',NULL),('135a3f02-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','航天学院党委',NULL,NULL,'c76098f8-efbb-4957-a2ab-9b332a09d52d',NULL),('138217de-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','电子与信息工程学院党委',NULL,NULL,'c76098f8-efbb-4957-a2ab-9b332a09d52d',NULL),('1387e54c-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','机电工程学院党委',NULL,NULL,'c76098f8-efbb-4957-a2ab-9b332a09d52d',NULL),('13959f0c-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','材料科学与工程学院党委',NULL,NULL,'c76098f8-efbb-4957-a2ab-9b332a09d52d',NULL),('13a18704-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','能源科学与工程学院党委',NULL,NULL,'c76098f8-efbb-4957-a2ab-9b332a09d52d',NULL),('13a84184-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','电气工程及自动化学院党委',NULL,NULL,'c76098f8-efbb-4957-a2ab-9b332a09d52d',NULL),('13b47788-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','仪器科学与工程学院党委',NULL,NULL,'c76098f8-efbb-4957-a2ab-9b332a09d52d',NULL),('13c58cbc-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','数学学院党委',NULL,NULL,'c76098f8-efbb-4957-a2ab-9b332a09d52d',NULL),('13cc5114-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','物理学院党委',NULL,NULL,'c76098f8-efbb-4957-a2ab-9b332a09d52d',NULL),('13d988e8-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','生命科学与技术学院党委',NULL,NULL,'c76098f8-efbb-4957-a2ab-9b332a09d52d',NULL),('13e4a46c-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','经济与管理学院党委',NULL,NULL,'c76098f8-efbb-4957-a2ab-9b332a09d52d',NULL),('13f06022-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','人文社科与法学学院党委',NULL,NULL,'c76098f8-efbb-4957-a2ab-9b332a09d52d',NULL),('13fb60ee-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','马克思主义学院党委',NULL,NULL,'c76098f8-efbb-4957-a2ab-9b332a09d52d',NULL),('140139c4-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','土木工程学院党委',NULL,NULL,'c76098f8-efbb-4957-a2ab-9b332a09d52d',NULL),('140c8da6-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','环境学院党委',NULL,NULL,'c76098f8-efbb-4957-a2ab-9b332a09d52d',NULL),('1417eb88-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','建筑学院党委',NULL,NULL,'c76098f8-efbb-4957-a2ab-9b332a09d52d',NULL),('1425b150-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','交通科学与工程学院党委',NULL,NULL,'c76098f8-efbb-4957-a2ab-9b332a09d52d',NULL),('14317828-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','计算机学部党委',NULL,NULL,'c76098f8-efbb-4957-a2ab-9b332a09d52d',NULL),('1437e8ca-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','外国语学院党委',NULL,NULL,'c76098f8-efbb-4957-a2ab-9b332a09d52d',NULL),('14448d96-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','体育部党委',NULL,NULL,'c76098f8-efbb-4957-a2ab-9b332a09d52d',NULL),('144fb950-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','化工与化学学院党委',NULL,NULL,'c76098f8-efbb-4957-a2ab-9b332a09d52d',NULL),('145a54e6-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','空间环境与物质科学研究院党委',NULL,NULL,'c76098f8-efbb-4957-a2ab-9b332a09d52d',NULL),('1466804a-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','基础学部党委',NULL,NULL,'c76098f8-efbb-4957-a2ab-9b332a09d52d',NULL),('147160dc-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','机关党委',NULL,NULL,'c76098f8-efbb-4957-a2ab-9b332a09d52d',NULL),('147b82a6-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','离休干部党委',NULL,NULL,'c76098f8-efbb-4957-a2ab-9b332a09d52d',NULL),('1481cb66-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','后勤党委',NULL,NULL,'c76098f8-efbb-4957-a2ab-9b332a09d52d',NULL),('148fd724-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','校医院党委',NULL,NULL,'c76098f8-efbb-4957-a2ab-9b332a09d52d',NULL),('149b6620-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','资产经营有限公司党委',NULL,NULL,'c76098f8-efbb-4957-a2ab-9b332a09d52d',NULL),('3bfc0908-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','航天学院',NULL,NULL,'0009d9b9-19f0-49ed-bbf2-931f8eab55ac',NULL),('3c1ed0f0-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','电子与信息工程学院',NULL,NULL,'0009d9b9-19f0-49ed-bbf2-931f8eab55ac',NULL),('3c24288e-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','机电工程学院',NULL,NULL,'0009d9b9-19f0-49ed-bbf2-931f8eab55ac',NULL),('3c2f5fa6-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','材料科学与工程学院',NULL,NULL,'0009d9b9-19f0-49ed-bbf2-931f8eab55ac',NULL),('3c3ba504-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','能源科学与工程学院',NULL,NULL,'0009d9b9-19f0-49ed-bbf2-931f8eab55ac',NULL),('3c3eda76-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','电气工程及自动化学院',NULL,NULL,'0009d9b9-19f0-49ed-bbf2-931f8eab55ac',NULL),('3c4d9c78-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','仪器科学与工程学院',NULL,NULL,'0009d9b9-19f0-49ed-bbf2-931f8eab55ac',NULL),('3c5dadde-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','数学学院',NULL,NULL,'0009d9b9-19f0-49ed-bbf2-931f8eab55ac',NULL),('3c68fd60-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','物理学院',NULL,NULL,'0009d9b9-19f0-49ed-bbf2-931f8eab55ac',NULL),('3c744576-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','经济与管理学院',NULL,NULL,'0009d9b9-19f0-49ed-bbf2-931f8eab55ac',NULL),('3c7bde9e-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','人文社科与法学学院',NULL,NULL,'0009d9b9-19f0-49ed-bbf2-931f8eab55ac',NULL),('3c86153a-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','马克思主义学院',NULL,NULL,'0009d9b9-19f0-49ed-bbf2-931f8eab55ac',NULL),('3c8c3f78-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','土木工程学院',NULL,NULL,'0009d9b9-19f0-49ed-bbf2-931f8eab55ac',NULL),('3c992c2e-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','环境学院',NULL,NULL,'0009d9b9-19f0-49ed-bbf2-931f8eab55ac',NULL),('3ca4abe4-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','建筑学院',NULL,NULL,'0009d9b9-19f0-49ed-bbf2-931f8eab55ac',NULL),('3cb1a3d0-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','交通科学与工程学院',NULL,NULL,'0009d9b9-19f0-49ed-bbf2-931f8eab55ac',NULL),('3cbc1c70-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','计算学部计算机科学与技术学院国家示范性软件学院网络空间安全学院',NULL,NULL,'0009d9b9-19f0-49ed-bbf2-931f8eab55ac',NULL),('3cc33dac-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','化工与化学学院',NULL,NULL,'0009d9b9-19f0-49ed-bbf2-931f8eab55ac',NULL),('3ccd6304-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','外国语学院',NULL,NULL,'0009d9b9-19f0-49ed-bbf2-931f8eab55ac',NULL),('3cd77e3e-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','生命科学与技术学院',NULL,NULL,'0009d9b9-19f0-49ed-bbf2-931f8eab55ac',NULL),('3ce21470-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','医学与健康学院',NULL,NULL,'0009d9b9-19f0-49ed-bbf2-931f8eab55ac',NULL),('3ceedade-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','体育部',NULL,NULL,'0009d9b9-19f0-49ed-bbf2-931f8eab55ac',NULL),('3cf42eee-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','空间环境与物质科学研究院（国家大科学工程）',NULL,NULL,'0009d9b9-19f0-49ed-bbf2-931f8eab55ac',NULL),('3cfec282-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','数学研究院',NULL,NULL,'0009d9b9-19f0-49ed-bbf2-931f8eab55ac',NULL),('3d053a90-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','生命信息技术研究院',NULL,NULL,'0009d9b9-19f0-49ed-bbf2-931f8eab55ac',NULL),('3d0af944-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','生命科学中心',NULL,NULL,'0009d9b9-19f0-49ed-bbf2-931f8eab55ac',NULL),('3d166ffe-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','军民融合创新研究院',NULL,NULL,'0009d9b9-19f0-49ed-bbf2-931f8eab55ac',NULL),('3d25c080-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','航空研究院',NULL,NULL,'0009d9b9-19f0-49ed-bbf2-931f8eab55ac',NULL),('3d327f96-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','人工智能研究院',NULL,NULL,'0009d9b9-19f0-49ed-bbf2-931f8eab55ac',NULL),('3d3f4e7e-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','哈尔滨工业大学（威海）',NULL,NULL,'0009d9b9-19f0-49ed-bbf2-931f8eab55ac',NULL),('3d4a7542-6a7d-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','哈尔滨工业大学（深圳）',NULL,NULL,'0009d9b9-19f0-49ed-bbf2-931f8eab55ac',NULL),('4e7054f9-b5e8-4ee9-96db-9295614ae313','2021-02-09 10:18:25.721570','2021-02-09 10:18:25.721570','教学与科研机构',NULL,NULL,NULL,NULL),('676cf193-fbc8-48c1-8a16-f1984c9071b9','2021-01-14 10:15:02.470661','2021-02-09 09:46:14.727764','党群职能部门','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','73f1218d-9249-42a2-9e78-a5c7963fbec1',NULL),('73248ec3-06f2-4ad0-9e3f-e211a6d96186','2021-02-09 10:12:33.281780','2021-02-09 10:12:33.281780','管理与服务机构',NULL,NULL,NULL,NULL),('73f1218d-9249-42a2-9e78-a5c7963fbec1','2021-01-14 10:13:29.449121','2021-02-09 09:46:01.852432','党群机构','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114',NULL,NULL),('8338f170-6a7b-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','学校办公室',NULL,NULL,'676cf193-fbc8-48c1-8a16-f1984c9071b9',NULL),('833efafc-6a7b-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','组织部',NULL,NULL,'676cf193-fbc8-48c1-8a16-f1984c9071b9',NULL),('834fe59c-6a7b-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','宣传部/教师工作部',NULL,NULL,'676cf193-fbc8-48c1-8a16-f1984c9071b9',NULL),('83563d34-6a7b-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','统战部',NULL,NULL,'676cf193-fbc8-48c1-8a16-f1984c9071b9',NULL),('836801fe-6a7b-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','纪委办公室',NULL,NULL,'676cf193-fbc8-48c1-8a16-f1984c9071b9',NULL),('836f2b1e-6a7b-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','学生工作部/团委',NULL,NULL,'676cf193-fbc8-48c1-8a16-f1984c9071b9',NULL),('83800a4c-6a7b-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','研究生工作部',NULL,NULL,'676cf193-fbc8-48c1-8a16-f1984c9071b9',NULL),('838da184-6a7b-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','保卫部',NULL,NULL,'676cf193-fbc8-48c1-8a16-f1984c9071b9',NULL),('839c21c8-6a7b-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','工会',NULL,NULL,'676cf193-fbc8-48c1-8a16-f1984c9071b9',NULL),('c76098f8-efbb-4957-a2ab-9b332a09d52d','2021-01-14 10:14:37.056202','2021-02-09 09:46:31.305882','基层党组织','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','73f1218d-9249-42a2-9e78-a5c7963fbec1',NULL),('dafb150e-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','学校办公室',NULL,NULL,'ef9584a2-8869-48b0-8c63-a17a6953059b',NULL),('db0168d2-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','本科生院本科生招生办公室',NULL,NULL,'ef9584a2-8869-48b0-8c63-a17a6953059b',NULL),('db0c98ec-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','研究生院学科建设办公室',NULL,NULL,'ef9584a2-8869-48b0-8c63-a17a6953059b',NULL),('db143156-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','科学与工业技术研究院',NULL,NULL,'ef9584a2-8869-48b0-8c63-a17a6953059b',NULL),('db1eecd6-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','学生工作处',NULL,NULL,'ef9584a2-8869-48b0-8c63-a17a6953059b',NULL),('db264da0-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','人事处',NULL,NULL,'ef9584a2-8869-48b0-8c63-a17a6953059b',NULL),('db330680-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','国际合作部',NULL,NULL,'ef9584a2-8869-48b0-8c63-a17a6953059b',NULL),('db3e1d54-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','计划财务处',NULL,NULL,'ef9584a2-8869-48b0-8c63-a17a6953059b',NULL),('db493018-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','监察处/行政效能投诉中心',NULL,NULL,'ef9584a2-8869-48b0-8c63-a17a6953059b',NULL),('db5114c2-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','审计处',NULL,NULL,'ef9584a2-8869-48b0-8c63-a17a6953059b',NULL),('db5b6468-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','国有资产管理处',NULL,NULL,'ef9584a2-8869-48b0-8c63-a17a6953059b',NULL),('db6734c8-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','总务处',NULL,NULL,'ef9584a2-8869-48b0-8c63-a17a6953059b',NULL),('db7272a2-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','基建处',NULL,NULL,'ef9584a2-8869-48b0-8c63-a17a6953059b',NULL),('db7b186c-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','保卫处',NULL,NULL,'ef9584a2-8869-48b0-8c63-a17a6953059b',NULL),('db868b2a-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','保密处',NULL,NULL,'ef9584a2-8869-48b0-8c63-a17a6953059b',NULL),('db90cd92-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','离退休工作处',NULL,NULL,'ef9584a2-8869-48b0-8c63-a17a6953059b',NULL),('db9b17ac-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','网络安全和信息化办公室',NULL,NULL,'ef9584a2-8869-48b0-8c63-a17a6953059b',NULL),('dba5d624-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','实验室与设备管理处',NULL,NULL,'ef9584a2-8869-48b0-8c63-a17a6953059b',NULL),('dbabbcb0-6a7c-11eb-a15a-1f40e3b3d410','2021-02-09 10:18:39.226934','2021-02-09 09:46:01.852432','发展改革和规划处',NULL,NULL,'ef9584a2-8869-48b0-8c63-a17a6953059b',NULL),('dec9e4c3-3f59-447a-b4a4-a18523554948','2021-02-09 10:15:00.594716','2021-02-09 10:15:00.594716','直属及附属单位',NULL,NULL,'73248ec3-06f2-4ad0-9e3f-e211a6d96186',NULL),('ef9584a2-8869-48b0-8c63-a17a6953059b','2021-02-09 10:12:53.448509','2021-02-09 10:12:53.448509','职能部处',NULL,NULL,'73248ec3-06f2-4ad0-9e3f-e211a6d96186',NULL);
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
INSERT INTO `sys_config` VALUES ('064b2740-c4d1-4891-bf1c-5e1c19bcbe20','用户管理-账号初始密码','sys.user.initPassword','pswJfZ*V$MN8xzx','Y','初始化密码 pswJfZ*V$MN8xzx','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-09 11:09:57','2021-01-09 11:09:57'),('0e1b871c-03d2-4bbc-ba18-013089765bcf','主框架页-是否开启页脚','sys.index.ignoreFooter','true','Y','是否开启底部页脚显示（true显示，false隐藏）','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-09 11:09:57','2021-01-09 11:09:57'),('3a642c2e-d6e0-4838-a71d-b6a0d9869a7a','用户管理-密码字符范围','sys.account.chrtype','0','Y','默认任意字符范围，0任意（密码可以输入任意字符），1数字（密码只能为0-9数字），2英文字母（密码只能为a-z和A-Z字母），3字母和数字（密码必须包含字母，数字）,4字母数字和特殊字符（目前支持的特殊字符包括：~!@#$%^&*()-=_+）','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-09 11:09:57','2021-01-09 11:09:57'),('5f653eba-8557-4a26-9ad1-38d4a59b2c40','主框架页-菜单导航显示风格','sys.index.menuStyle','default','Y','菜单导航显示风格（default为左侧导航菜单，topnav为顶部导航菜单）','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-09 11:09:57','2021-01-09 11:09:57'),('78a58201-8b1c-4a6e-8976-23ec1ac7496f','用户管理-账号密码更新周期','sys.account.passwordValidateDays','0','Y','密码更新周期（填写数字，数据初始化值为0不限制，若修改必须为大于0小于365的正整数），如果超过这个周期登录系统时，则在登录时就会提醒修改密码对话框','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-09 11:09:57','2021-01-09 11:09:57'),('902af647-367c-4116-a74e-b911355c1ff0','账号自助-是否开启用户注册功能','sys.account.registerUser','false','Y','是否开启注册用户功能（true开启，false关闭）','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-09 11:09:57','2021-01-09 11:09:57'),('9788edf0-0488-40d3-8e35-9c38fcb554aa','用户管理-初始密码修改策略','sys.account.initPasswordModify','0','Y','0：初始密码修改策略关闭，没有任何提示，1：提醒用户，如果未修改初始密码，则在登录时就会提醒修改密码对话框','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-09 11:09:57','2021-01-09 11:09:57'),('b9fe2d5a-bfe3-4262-9866-8e066a9348b9','主框架页-默认皮肤样式名称','sys.index.skinName','skin-blue','Y','蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-09 11:09:57','2021-01-09 11:09:57'),('c1b7524a-37da-4e27-b590-fd26a6e6e7ec','主框架页-侧边栏主题','sys.index.sideTheme','theme-blue','Y','深黑主题theme-dark，浅色主题theme-light，深蓝主题theme-blue','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-09 11:09:57','2021-01-09 11:09:57');
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
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `sort` int(11) DEFAULT '0' COMMENT '显示顺序',
  `url` varchar(200) DEFAULT '#' COMMENT '请求地址',
  `parent_id` varchar(255) DEFAULT NULL COMMENT '父id',
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
  CONSTRAINT `FK2jrf4gb0gjqi8882gxytpxnhe` FOREIGN KEY (`parent_id`) REFERENCES `sys_menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES ('0554e0d0-3bb9-4faf-b23c-f11e3bc74369','学院新增',2,'#','d78b56ab-3730-4cad-83e6-8e63ddb4969f','system:college:add','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-13 18:30:33','2021-01-13 18:30:33'),('06e31db6-562f-48b9-8023-ee475ff3d878','字典新增',2,'#','79ba0663-46ba-4287-bc3c-1496d8e15b7a','system:dict:add','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('0b5c6672-b034-40ed-b167-5ecc5e8cf9bf','摄像头修改',3,'#','0c9dfc80-a979-4dff-ba61-08e43f0c52b8','system:camera:edit','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-18 14:24:25','2021-01-18 14:24:25'),('0c9dfc80-a979-4dff-ba61-08e43f0c52b8','摄像头管理',7,'/system/camera','eeaaceb9-f8db-4506-81d7-986e4b82fae0','system:camera:view','menuItem','C',NULL,NULL,'fa fa-video-camera',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-18 14:22:49','2021-01-18 14:22:49'),('1461f63f-13bd-40ac-a4d2-3d5706e20102','车辆新增',2,'#','62288907-eb9b-41aa-8f22-8d53c0f2d357','system:vehicle:add','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-14 15:08:37','2021-01-14 15:08:37'),('17cbbbf5-c7c8-43db-b152-94816e63ec9a','用户删除',4,'#','33926c89-e38e-45ee-846d-81f564a8ea38','system:user:remove','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('18c26b44-2328-4605-aa8b-43c8bbc9a625','违规处置平台',3,'#',NULL,'','menuItem','M',NULL,NULL,'fa fa-bar-chart',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-21 13:18:20','2021-02-02 10:44:27'),('1a47f9a2-0a9a-43dc-81f6-916cc94e4fca','区域管理',6,'/system/region','eeaaceb9-f8db-4506-81d7-986e4b82fae0','system:region:view','menuItem','C',NULL,NULL,'fa fa-adjust',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-18 12:42:22','2021-01-18 12:43:40'),('209e47a9-5cc1-4730-9ea8-18980c25b2cd','审批',3,'#','b57bdf94-0845-4cc7-b504-e399198b144d','event:approval:approval','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-29 18:15:48','2021-01-29 18:15:48'),('298a3927-93a1-4352-bb06-ebf113876fe5','用户导出',5,'#','33926c89-e38e-45ee-846d-81f564a8ea38','system:user:export','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('2fd34018-b88e-4c9e-a438-f95959801a81','违规分类管理',10,'/system/violation','eeaaceb9-f8db-4506-81d7-986e4b82fae0','system:violation:view','menuItem','C',NULL,NULL,'fa fa-certificate',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-18 17:05:12','2021-01-18 17:06:20'),('304794fa-f08f-4750-9541-3e663fbca4c7','扣分修改',3,'#','7f2fa746-b85b-419a-8acd-4dd985c4e6e7','system:score:edit','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-20 13:32:17','2021-01-20 13:32:17'),('33926c89-e38e-45ee-846d-81f564a8ea38','用户管理',1,'/system/user','eeaaceb9-f8db-4506-81d7-986e4b82fae0','system:user:view','','C','0','1','fa fa-user-o','用户管理菜单','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('345b4c26-3414-40c7-aea3-6c6f9514da34','字典导出',5,'#','79ba0663-46ba-4287-bc3c-1496d8e15b7a','system:dict:export','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('38a620d2-44f8-4ca7-81c7-6eabbd42914c','摄像头新增',2,'#','0c9dfc80-a979-4dff-ba61-08e43f0c52b8','system:camera:add','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-18 14:23:57','2021-01-18 14:23:57'),('3d8907c6-d118-4e2b-bd32-c75fc7f1e9c6','停车场新增',2,'#','e98bc573-3ba7-4354-885b-c81d3df5715f','park:parklot:add','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-21 11:11:41','2021-01-21 11:11:41'),('40c45575-396e-41ab-bd09-76d73df50bbb','车辆管理平台',30,'http://192.168.59.108:9080/CarManagerHIT/userLogin.do',NULL,'','menuBlank','C',NULL,NULL,'fa fa-automobile',NULL,NULL,NULL,'2021-02-18 09:07:16','2021-02-18 09:07:16'),('485073aa-8bab-45d3-b34d-300278db4f43','审批查询',1,'#','b57bdf94-0845-4cc7-b504-e399198b144d','event:approval:search','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-22 13:57:18','2021-01-22 13:57:18'),('48d3c830-c362-46b4-a69e-6fc1fbc786de','菜单查询',1,'#','51977a2b-5ff2-41ea-bae4-4bab175af56f','system:menu:search','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('49e538c1-c68d-4fa3-8d41-9a06329d4135','扣分查询',1,'#','7f2fa746-b85b-419a-8acd-4dd985c4e6e7','system:score:search','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-20 12:37:16','2021-01-20 14:07:39'),('4c61a0a9-815d-4dfd-9d92-91415d3177f3','学院修改',3,'#','d78b56ab-3730-4cad-83e6-8e63ddb4969f','system:college:edit','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-13 18:31:11','2021-01-13 18:31:11'),('4faa3eb9-188d-4328-a88b-24b2e779149f','菜单新增',2,'#','51977a2b-5ff2-41ea-bae4-4bab175af56f','system:menu:add','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('51747d48-9f49-44c2-a88f-72aab5c46183','车辆来访预约',40,'http://192.168.59.108:9080/CarManagerHIT/login.do',NULL,'','menuBlank','C',NULL,NULL,'fa fa-calendar',NULL,NULL,NULL,'2021-02-18 09:08:04','2021-02-18 09:08:04'),('51977a2b-5ff2-41ea-bae4-4bab175af56f','菜单管理',3,'/system/menu','eeaaceb9-f8db-4506-81d7-986e4b82fae0','system:menu:view','','C','0','1','fa fa-th-list','菜单管理菜单','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('54855816-ace4-4878-a000-b148f80f4c7c','摄像头查询',1,'#','0c9dfc80-a979-4dff-ba61-08e43f0c52b8','system:camera:search','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-18 14:23:33','2021-01-18 14:23:33'),('5494e9bf-05e1-440d-b6a8-fed43026c7a4','停车场删除',4,'#','e98bc573-3ba7-4354-885b-c81d3df5715f','park:parklot:remove','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-21 11:12:32','2021-01-21 11:12:32'),('59972056-5280-4eb0-b468-6f729c8f7a19','角色管理',2,'/system/role','eeaaceb9-f8db-4506-81d7-986e4b82fae0','system:role:view','menuItem','C','0','1','fa fa-user-secret','角色管理菜单','1b3c1438-beb2-4bab-af86-b6b8dfb91114','d5053e06-fcb2-4855-b9d0-ac5861ac4594','2021-01-06 11:28:07','2021-01-13 15:22:30'),('5a4fa9a4-e27d-43d6-978d-1b8509820b0f','字典修改',3,'#','79ba0663-46ba-4287-bc3c-1496d8e15b7a','system:dict:edit','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('62288907-eb9b-41aa-8f22-8d53c0f2d357','车辆管理',5,'/system/vehicle','eeaaceb9-f8db-4506-81d7-986e4b82fae0','system:vehicle:view','menuItem','C',NULL,NULL,'fa fa-automobile',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-14 15:05:21','2021-01-14 15:05:21'),('687123d6-41e7-4767-b114-077ef66da5d3','扣分新增',2,'#','7f2fa746-b85b-419a-8acd-4dd985c4e6e7','system:score:add','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-20 13:31:51','2021-01-20 13:31:51'),('6dfb338e-976a-44f9-ab96-cd567297b043','扣分删除',4,'#','7f2fa746-b85b-419a-8acd-4dd985c4e6e7','system:score:remove','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-20 13:32:40','2021-01-20 13:32:40'),('7332b0dc-4d2a-4c64-9806-8fd1f43bf20c','用户查询',1,'#','33926c89-e38e-45ee-846d-81f564a8ea38','system:user:search','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('79ba0663-46ba-4287-bc3c-1496d8e15b7a','字典管理',6,'/system/dict','eeaaceb9-f8db-4506-81d7-986e4b82fae0','system:dict:view','','C','0','1','fa fa-bookmark-o','字典管理菜单','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('7f2e78a1-41ef-4fa0-9367-88797e6b338e','菜单删除',4,'#','51977a2b-5ff2-41ea-bae4-4bab175af56f','system:menu:remove','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('7f2fa746-b85b-419a-8acd-4dd985c4e6e7','扣分设置',11,'/system/score','eeaaceb9-f8db-4506-81d7-986e4b82fae0','system:score:view','menuItem','C',NULL,NULL,'fa fa-bar-chart',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-20 12:36:25','2021-01-20 14:03:54'),('8bc56609-6694-4699-b5ba-d62f7ed59ede','违规数据统计',2,'/event/statistics','18c26b44-2328-4605-aa8b-43c8bbc9a625','event:statistics:view','menuItem','C',NULL,NULL,'fa fa-address-book-o',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-21 13:20:14','2021-01-30 13:32:04'),('8ef0d06c-1eaf-4d47-b5f3-af64d9077fe2','学院删除',4,'#','d78b56ab-3730-4cad-83e6-8e63ddb4969f','system:college:remove','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-13 18:31:44','2021-01-13 18:31:44'),('8fe828aa-dce2-4e39-abc7-9f82fcd84aeb','违规查询',1,'#','2fd34018-b88e-4c9e-a438-f95959801a81','system:violation:search','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-18 17:07:23','2021-01-18 17:07:23'),('92589525-79c3-4f76-a29a-26824a000d46','角色修改',3,'#','59972056-5280-4eb0-b468-6f729c8f7a19','system:role:edit','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('93d5dfe4-3381-4eae-bfc7-527cc2015f3d','角色导出',5,'#','59972056-5280-4eb0-b468-6f729c8f7a19','system:role:export','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('94a8302f-ad2d-49f0-aaa8-7989e65e246e','角色删除',4,'#','59972056-5280-4eb0-b468-6f729c8f7a19','system:role:remove','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('96334cf4-95e3-47d9-b170-9903b849fa81','字典删除',4,'#','79ba0663-46ba-4287-bc3c-1496d8e15b7a','system:dict:remove','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('a47104f8-db0c-4f30-8c3f-ac8f74b532b9','区域修改',3,'#','1a47f9a2-0a9a-43dc-81f6-916cc94e4fca','system:region:edit','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-18 12:46:05','2021-01-18 12:46:05'),('aa98c270-dcb4-48f0-a3de-dd515a7f72a9','违规修改',3,'#','2fd34018-b88e-4c9e-a438-f95959801a81','system:violation:edit','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-18 17:08:05','2021-01-18 17:08:05'),('ac26ee13-2fe8-4351-93d6-3919abe36ce9','违规删除',4,'#','2fd34018-b88e-4c9e-a438-f95959801a81','system:violation:remove','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-18 17:08:32','2021-01-18 17:08:32'),('ae798006-edd4-478f-b5b4-f70233f6f619','区域删除',4,'#','1a47f9a2-0a9a-43dc-81f6-916cc94e4fca','system:region:remove','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-18 12:47:04','2021-01-18 12:47:04'),('b57bdf94-0845-4cc7-b504-e399198b144d','违规审批',1,'/event/approval','18c26b44-2328-4605-aa8b-43c8bbc9a625','event:approval:view','menuItem','C',NULL,NULL,'fa fa-address-book',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-21 13:19:42','2021-01-22 13:40:51'),('b5e4ac60-75c0-4e48-8665-a6a8dcf632e6','用户新增',2,'#','33926c89-e38e-45ee-846d-81f564a8ea38','system:user:add','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('b86f4d57-0ff2-4509-baad-2207477e7ae2','违规新增',2,'#','2fd34018-b88e-4c9e-a438-f95959801a81','system:violation:add','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-18 17:07:48','2021-01-18 17:07:48'),('ba050e2e-2f49-4168-af4a-674da78263cb','重置密码',7,'#','33926c89-e38e-45ee-846d-81f564a8ea38','system:user:resetPassword','menuItem','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-02-09 14:42:19'),('bca635af-5bac-449b-adba-7947aacb2694','摄像头删除',4,'#','0c9dfc80-a979-4dff-ba61-08e43f0c52b8','system:camera:remove','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-18 14:24:58','2021-01-18 14:24:58'),('c06868ad-256a-4ac4-a7fa-db4f9c82e021','区域查询',1,'#','1a47f9a2-0a9a-43dc-81f6-916cc94e4fca','system:region:search','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-18 12:44:13','2021-01-18 12:44:28'),('c193c493-6f42-4df7-9015-0b4a4dc91ac5','作废',4,'#','b57bdf94-0845-4cc7-b504-e399198b144d','event:approval:cancel','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-29 18:16:40','2021-01-29 18:16:40'),('c194d646-5d99-4041-9046-9b8ac3b8c479','停车场修改',3,'#','e98bc573-3ba7-4354-885b-c81d3df5715f','park:parklot:edit','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-21 11:12:09','2021-01-21 11:12:09'),('cc3a32cb-f6fe-44fe-b0a3-6a5780d3e65b','字典查询',1,'#','79ba0663-46ba-4287-bc3c-1496d8e15b7a','system:dict:list','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('cc4a5e04-2be1-42a4-a079-835d1242398d','停车场查询',1,'#','e98bc573-3ba7-4354-885b-c81d3df5715f','park:parklot:search','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-21 11:10:55','2021-01-21 11:10:55'),('d3692db5-b118-44bb-b09a-c0e3d8b70346','角色查询',1,'#','59972056-5280-4eb0-b468-6f729c8f7a19','system:role:search','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('d49c0360-e404-4016-b54b-5b8b0b855c8a','导出',5,'#','b57bdf94-0845-4cc7-b504-e399198b144d','event:approval:export','menuItem','F',NULL,NULL,'',NULL,NULL,NULL,'2021-02-19 14:07:54','2021-02-19 14:07:54'),('d78b56ab-3730-4cad-83e6-8e63ddb4969f','学院管理',4,'/system/college','eeaaceb9-f8db-4506-81d7-986e4b82fae0','system:college:view','menuItem','C',NULL,NULL,'fa fa-graduation-cap',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-13 18:28:46','2021-01-13 18:28:46'),('dbb0e1d1-75de-4a4f-8fae-31259543883f','菜单修改',3,'#','51977a2b-5ff2-41ea-bae4-4bab175af56f','system:menu:edit','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:08','2021-01-06 11:28:08'),('ddd1c450-840c-409e-89de-ba7bbb59babe','车辆修改',3,'#','62288907-eb9b-41aa-8f22-8d53c0f2d357','system:vehicle:edit','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-14 15:09:16','2021-01-14 15:09:16'),('e3af6408-1161-4167-8411-5b7e2df576b5','用户导入',6,'#','33926c89-e38e-45ee-846d-81f564a8ea38','system:user:import','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('e47dce29-47c4-4522-8c0d-7eba51a39c16','违规数据统计查询',1,'#','8bc56609-6694-4699-b5ba-d62f7ed59ede','system:statistics:search','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-30 16:16:32','2021-01-30 16:16:32'),('e598271c-db31-4e7e-af05-92cc92780b85','查看详情',2,'#','b57bdf94-0845-4cc7-b504-e399198b144d','event:approval:detail','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-22 15:51:35','2021-01-22 15:51:35'),('e8701b76-4773-4785-8261-0d33f25ac401','角色新增',2,'#','59972056-5280-4eb0-b468-6f729c8f7a19','system:role:add','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('e98bc573-3ba7-4354-885b-c81d3df5715f','停车场管理',16,'/park/parklot','eeaaceb9-f8db-4506-81d7-986e4b82fae0','park:parklot:view','menuItem','C',NULL,NULL,'fa fa-tasks',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-21 11:10:13','2021-02-02 10:41:21'),('eeaaceb9-f8db-4506-81d7-986e4b82fae0','系统管理',1,'#',NULL,'','menuItem','M','0','1','fa fa-gear','系统管理目录','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-02-02 10:36:34'),('f7d415c3-6425-431b-be19-540aa7a0b3d4','用户修改',3,'#','33926c89-e38e-45ee-846d-81f564a8ea38','system:user:edit','','F','0','1','#','','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 11:28:07','2021-01-06 11:28:07'),('fc0b4075-b562-4f5d-8e51-59946ace5eb9','车辆查询',1,'#','62288907-eb9b-41aa-8f22-8d53c0f2d357','system:vehicle:search','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-14 15:06:39','2021-01-14 15:06:39'),('fd79d156-98c0-4997-9e8f-9eccd3d24596','区域新增',2,'#','1a47f9a2-0a9a-43dc-81f6-916cc94e4fca','system:region:add','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-18 12:45:07','2021-01-18 12:45:07'),('fe0082ae-af19-4bc8-9284-c346ba0c080a','学院查询',1,'#','d78b56ab-3730-4cad-83e6-8e63ddb4969f','system:college:search','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-13 18:29:56','2021-01-13 18:29:56'),('ff641349-a11c-4575-947b-ce2f0df42694','车辆删除',4,'#','62288907-eb9b-41aa-8f22-8d53c0f2d357','system:vehicle:remove','menuItem','F',NULL,NULL,'',NULL,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-14 15:10:12','2021-01-14 15:10:12');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_park_lot`
--

DROP TABLE IF EXISTS `sys_park_lot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_park_lot` (
  `id` varchar(255) NOT NULL,
  `create_time` datetime(6) NOT NULL,
  `update_time` datetime(6) NOT NULL,
  `count` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdggnfx8ubeefwq5c8y6oajbtw` (`create_by`),
  KEY `FK4pmqg8175jw780tgapef1ek64` (`update_by`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_park_lot`
--

LOCK TABLES `sys_park_lot` WRITE;
/*!40000 ALTER TABLE `sys_park_lot` DISABLE KEYS */;
INSERT INTO `sys_park_lot` VALUES ('24443b28-a611-40eb-b6aa-9f4426cf799c','2021-01-21 11:36:40.798658','2021-01-21 11:36:40.798658',30,'停车场3','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114'),('8cdd7104-7625-4f5f-9bfc-6c63ae9cef9d','2021-01-21 11:36:29.377397','2021-01-21 11:36:29.377397',20,'停车场2','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114'),('ab839f0b-a532-4722-9316-84525ed3565a','2021-01-21 11:34:53.207313','2021-01-21 11:39:30.885447',10,'停车场1','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114');
/*!40000 ALTER TABLE `sys_park_lot` ENABLE KEYS */;
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
  KEY `FK2006ahwbsbk3bgxx9cw0dfm0p` (`update_by`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_region`
--

LOCK TABLES `sys_region` WRITE;
/*!40000 ALTER TABLE `sys_region` DISABLE KEYS */;
INSERT INTO `sys_region` VALUES ('779d6d9b-fa7f-4eac-8d22-3c7020c1c5c0','2021-01-20 11:58:20.325064','2021-02-18 16:44:32.399470','限速30公里道路','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114'),('cfb2f9e4-9c45-4c81-80c9-71aabe1d3d5a','2021-01-20 11:58:14.947207','2021-02-18 16:44:08.667860','限速20公里道路','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114');
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
  KEY `FKrouqqi3f2bgc5o83wdstlh6q4` (`update_by`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES ('134f32fd-e488-4a6c-a300-5af4b8c467d6','2021-02-20 10:32:35.828000','2021-02-20 10:32:35.828000','普通角色',NULL,NULL,''),('a2a84058-8660-4393-a89d-6d1064f9b43a','2021-01-12 15:36:14.615792','2021-02-19 14:08:05.372809','超级管理员','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','超级管理员,具有最大权限的角色');
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
INSERT INTO `sys_role_menu` VALUES ('a2a84058-8660-4393-a89d-6d1064f9b43a','18c26b44-2328-4605-aa8b-43c8bbc9a625'),('a2a84058-8660-4393-a89d-6d1064f9b43a','8bc56609-6694-4699-b5ba-d62f7ed59ede'),('a2a84058-8660-4393-a89d-6d1064f9b43a','e47dce29-47c4-4522-8c0d-7eba51a39c16'),('a2a84058-8660-4393-a89d-6d1064f9b43a','b57bdf94-0845-4cc7-b504-e399198b144d'),('a2a84058-8660-4393-a89d-6d1064f9b43a','209e47a9-5cc1-4730-9ea8-18980c25b2cd'),('a2a84058-8660-4393-a89d-6d1064f9b43a','485073aa-8bab-45d3-b34d-300278db4f43'),('a2a84058-8660-4393-a89d-6d1064f9b43a','c193c493-6f42-4df7-9015-0b4a4dc91ac5'),('a2a84058-8660-4393-a89d-6d1064f9b43a','d49c0360-e404-4016-b54b-5b8b0b855c8a'),('a2a84058-8660-4393-a89d-6d1064f9b43a','e598271c-db31-4e7e-af05-92cc92780b85'),('a2a84058-8660-4393-a89d-6d1064f9b43a','40c45575-396e-41ab-bd09-76d73df50bbb'),('a2a84058-8660-4393-a89d-6d1064f9b43a','51747d48-9f49-44c2-a88f-72aab5c46183'),('a2a84058-8660-4393-a89d-6d1064f9b43a','eeaaceb9-f8db-4506-81d7-986e4b82fae0'),('a2a84058-8660-4393-a89d-6d1064f9b43a','0c9dfc80-a979-4dff-ba61-08e43f0c52b8'),('a2a84058-8660-4393-a89d-6d1064f9b43a','0b5c6672-b034-40ed-b167-5ecc5e8cf9bf'),('a2a84058-8660-4393-a89d-6d1064f9b43a','38a620d2-44f8-4ca7-81c7-6eabbd42914c'),('a2a84058-8660-4393-a89d-6d1064f9b43a','54855816-ace4-4878-a000-b148f80f4c7c'),('a2a84058-8660-4393-a89d-6d1064f9b43a','bca635af-5bac-449b-adba-7947aacb2694'),('a2a84058-8660-4393-a89d-6d1064f9b43a','1a47f9a2-0a9a-43dc-81f6-916cc94e4fca'),('a2a84058-8660-4393-a89d-6d1064f9b43a','a47104f8-db0c-4f30-8c3f-ac8f74b532b9'),('a2a84058-8660-4393-a89d-6d1064f9b43a','ae798006-edd4-478f-b5b4-f70233f6f619'),('a2a84058-8660-4393-a89d-6d1064f9b43a','c06868ad-256a-4ac4-a7fa-db4f9c82e021'),('a2a84058-8660-4393-a89d-6d1064f9b43a','fd79d156-98c0-4997-9e8f-9eccd3d24596'),('a2a84058-8660-4393-a89d-6d1064f9b43a','2fd34018-b88e-4c9e-a438-f95959801a81'),('a2a84058-8660-4393-a89d-6d1064f9b43a','8fe828aa-dce2-4e39-abc7-9f82fcd84aeb'),('a2a84058-8660-4393-a89d-6d1064f9b43a','aa98c270-dcb4-48f0-a3de-dd515a7f72a9'),('a2a84058-8660-4393-a89d-6d1064f9b43a','ac26ee13-2fe8-4351-93d6-3919abe36ce9'),('a2a84058-8660-4393-a89d-6d1064f9b43a','b86f4d57-0ff2-4509-baad-2207477e7ae2'),('a2a84058-8660-4393-a89d-6d1064f9b43a','33926c89-e38e-45ee-846d-81f564a8ea38'),('a2a84058-8660-4393-a89d-6d1064f9b43a','17cbbbf5-c7c8-43db-b152-94816e63ec9a'),('a2a84058-8660-4393-a89d-6d1064f9b43a','298a3927-93a1-4352-bb06-ebf113876fe5'),('a2a84058-8660-4393-a89d-6d1064f9b43a','7332b0dc-4d2a-4c64-9806-8fd1f43bf20c'),('a2a84058-8660-4393-a89d-6d1064f9b43a','b5e4ac60-75c0-4e48-8665-a6a8dcf632e6'),('a2a84058-8660-4393-a89d-6d1064f9b43a','ba050e2e-2f49-4168-af4a-674da78263cb'),('a2a84058-8660-4393-a89d-6d1064f9b43a','e3af6408-1161-4167-8411-5b7e2df576b5'),('a2a84058-8660-4393-a89d-6d1064f9b43a','f7d415c3-6425-431b-be19-540aa7a0b3d4'),('a2a84058-8660-4393-a89d-6d1064f9b43a','51977a2b-5ff2-41ea-bae4-4bab175af56f'),('a2a84058-8660-4393-a89d-6d1064f9b43a','48d3c830-c362-46b4-a69e-6fc1fbc786de'),('a2a84058-8660-4393-a89d-6d1064f9b43a','4faa3eb9-188d-4328-a88b-24b2e779149f'),('a2a84058-8660-4393-a89d-6d1064f9b43a','7f2e78a1-41ef-4fa0-9367-88797e6b338e'),('a2a84058-8660-4393-a89d-6d1064f9b43a','dbb0e1d1-75de-4a4f-8fae-31259543883f'),('a2a84058-8660-4393-a89d-6d1064f9b43a','59972056-5280-4eb0-b468-6f729c8f7a19'),('a2a84058-8660-4393-a89d-6d1064f9b43a','92589525-79c3-4f76-a29a-26824a000d46'),('a2a84058-8660-4393-a89d-6d1064f9b43a','93d5dfe4-3381-4eae-bfc7-527cc2015f3d'),('a2a84058-8660-4393-a89d-6d1064f9b43a','94a8302f-ad2d-49f0-aaa8-7989e65e246e'),('a2a84058-8660-4393-a89d-6d1064f9b43a','d3692db5-b118-44bb-b09a-c0e3d8b70346'),('a2a84058-8660-4393-a89d-6d1064f9b43a','e8701b76-4773-4785-8261-0d33f25ac401'),('a2a84058-8660-4393-a89d-6d1064f9b43a','62288907-eb9b-41aa-8f22-8d53c0f2d357'),('a2a84058-8660-4393-a89d-6d1064f9b43a','1461f63f-13bd-40ac-a4d2-3d5706e20102'),('a2a84058-8660-4393-a89d-6d1064f9b43a','ddd1c450-840c-409e-89de-ba7bbb59babe'),('a2a84058-8660-4393-a89d-6d1064f9b43a','fc0b4075-b562-4f5d-8e51-59946ace5eb9'),('a2a84058-8660-4393-a89d-6d1064f9b43a','ff641349-a11c-4575-947b-ce2f0df42694'),('a2a84058-8660-4393-a89d-6d1064f9b43a','7f2fa746-b85b-419a-8acd-4dd985c4e6e7'),('a2a84058-8660-4393-a89d-6d1064f9b43a','304794fa-f08f-4750-9541-3e663fbca4c7'),('a2a84058-8660-4393-a89d-6d1064f9b43a','49e538c1-c68d-4fa3-8d41-9a06329d4135'),('a2a84058-8660-4393-a89d-6d1064f9b43a','687123d6-41e7-4767-b114-077ef66da5d3'),('a2a84058-8660-4393-a89d-6d1064f9b43a','6dfb338e-976a-44f9-ab96-cd567297b043'),('a2a84058-8660-4393-a89d-6d1064f9b43a','d78b56ab-3730-4cad-83e6-8e63ddb4969f'),('a2a84058-8660-4393-a89d-6d1064f9b43a','0554e0d0-3bb9-4faf-b23c-f11e3bc74369'),('a2a84058-8660-4393-a89d-6d1064f9b43a','4c61a0a9-815d-4dfd-9d92-91415d3177f3'),('a2a84058-8660-4393-a89d-6d1064f9b43a','8ef0d06c-1eaf-4d47-b5f3-af64d9077fe2'),('a2a84058-8660-4393-a89d-6d1064f9b43a','fe0082ae-af19-4bc8-9284-c346ba0c080a'),('a2a84058-8660-4393-a89d-6d1064f9b43a','e98bc573-3ba7-4354-885b-c81d3df5715f'),('a2a84058-8660-4393-a89d-6d1064f9b43a','3d8907c6-d118-4e2b-bd32-c75fc7f1e9c6'),('a2a84058-8660-4393-a89d-6d1064f9b43a','5494e9bf-05e1-440d-b6a8-fed43026c7a4'),('a2a84058-8660-4393-a89d-6d1064f9b43a','c194d646-5d99-4041-9046-9b8ac3b8c479'),('a2a84058-8660-4393-a89d-6d1064f9b43a','cc4a5e04-2be1-42a4-a079-835d1242398d'),('134f32fd-e488-4a6c-a300-5af4b8c467d6','40c45575-396e-41ab-bd09-76d73df50bbb'),('134f32fd-e488-4a6c-a300-5af4b8c467d6','51747d48-9f49-44c2-a88f-72aab5c46183');
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
  `remark` varchar(255) DEFAULT NULL,
  `min_range` tinyint(4) DEFAULT NULL COMMENT '分值最小值',
  `max_range` tinyint(4) DEFAULT NULL COMMENT '分值最大值',
  PRIMARY KEY (`id`),
  KEY `FKiy8vamfkl0juuu5funm584jym` (`create_by`),
  KEY `FK6xt59x3we1wgcyxj04d057b7l` (`update_by`),
  KEY `FK9v0yp258eb3dcxjcf5v9puius` (`region_id`),
  KEY `FKr7jbe1y3eqkrd5qmy0ml2hpkv` (`violation_id`),
  CONSTRAINT `FK9v0yp258eb3dcxjcf5v9puius` FOREIGN KEY (`region_id`) REFERENCES `sys_region` (`id`),
  CONSTRAINT `FKr7jbe1y3eqkrd5qmy0ml2hpkv` FOREIGN KEY (`violation_id`) REFERENCES `sys_violation_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_score`
--

LOCK TABLES `sys_score` WRITE;
/*!40000 ALTER TABLE `sys_score` DISABLE KEYS */;
INSERT INTO `sys_score` VALUES ('126e87e3-42e3-4e53-8128-005843a65173','2021-02-10 14:04:12.875050','2021-02-19 10:50:10.719268',10,NULL,NULL,'cfb2f9e4-9c45-4c81-80c9-71aabe1d3d5a','a2095ee8-a8a7-4221-beaa-42a2ae6b416d','速度25-30','',25,30),('14731d00-3e20-4b7f-aaf0-b4b4a7d8b29d','2021-02-10 14:05:12.406107','2021-02-19 10:49:59.317136',20,NULL,NULL,'cfb2f9e4-9c45-4c81-80c9-71aabe1d3d5a','a2095ee8-a8a7-4221-beaa-42a2ae6b416d','40以上','',40,NULL),('35552577-cfd6-480e-8586-f7568bb943b4','2021-02-10 14:02:38.127426','2021-02-19 10:50:27.747798',20,NULL,NULL,'779d6d9b-fa7f-4eac-8d22-3c7020c1c5c0','f0571407-fa10-4467-b260-75243ba6ab2e','速度60以上','',60,NULL),('e26ee558-26bc-44a6-9767-909fe9accf09','2021-02-10 14:02:08.052231','2021-02-19 10:50:21.041583',15,NULL,NULL,'779d6d9b-fa7f-4eac-8d22-3c7020c1c5c0','f0571407-fa10-4467-b260-75243ba6ab2e','速度40-60','',40,60),('f676d255-ef2c-4ccf-bd12-8eebb67dc0c8','2021-02-20 10:29:53.289000','2021-02-20 10:29:53.289000',2,NULL,NULL,'779d6d9b-fa7f-4eac-8d22-3c7020c1c5c0','a2095ee8-a8a7-4221-beaa-42a2ae6b416d','10','',NULL,NULL),('f6a3a200-9c0d-4606-a218-554fb637c908','2021-02-10 14:04:51.317107','2021-02-19 09:06:53.268140',15,NULL,NULL,'cfb2f9e4-9c45-4c81-80c9-71aabe1d3d5a','a2095ee8-a8a7-4221-beaa-42a2ae6b416d','速度30-40','',30,40),('fee71ddd-9116-4952-b8a1-13639b3032d3','2021-02-07 10:38:59.119139','2021-02-19 10:50:50.520871',10,NULL,NULL,'779d6d9b-fa7f-4eac-8d22-3c7020c1c5c0','f0571407-fa10-4467-b260-75243ba6ab2e','速度35-40','',35,40);
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
  `create_time` datetime(6) NOT NULL COMMENT '创建时间',
  `update_time` datetime(6) NOT NULL COMMENT '更新时间',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `password` char(60) NOT NULL COMMENT '密码',
  `username` varchar(20) NOT NULL COMMENT '账号',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(255) DEFAULT NULL COMMENT '修改人',
  `last_login_time` datetime(6) DEFAULT NULL COMMENT '最后一次登录时间',
  `sex` char(1) NOT NULL COMMENT '性别',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `phone_number` char(32) DEFAULT NULL COMMENT '手机号码',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `college_id` varchar(255) DEFAULT NULL COMMENT '学院id',
  PRIMARY KEY (`id`),
  KEY `FK6uj38tbvf8jyy6je7oghdx8oe` (`create_by`),
  KEY `FKgnogw8c0llt9oyonwwgfhyync` (`update_by`),
  KEY `FKnxfsjcmww39m3i0e17u9nc186` (`college_id`),
  CONSTRAINT `FKnxfsjcmww39m3i0e17u9nc186` FOREIGN KEY (`college_id`) REFERENCES `sys_college` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES ('05c71d58-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-02-20 10:32:44.352000','于力军','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19840239',NULL,NULL,'2021-02-20 14:26:44.084000','男','','6bb6132a5f264ba5f7ffa20cb55f89e4','','db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('05ce9e3e-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','曲福忠','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19930204',NULL,NULL,NULL,'男',NULL,'436c043c42a17a07dcdec349669f62c1',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('05e4f800-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','刘玉会','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19930203',NULL,NULL,NULL,'男',NULL,'780c89f36a4be25aa6b92e9cb49fc2fe',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('05f060b4-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','庄红伟','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19950166',NULL,NULL,NULL,'男',NULL,'ea8de86a6fa405b41f2afc87dae00d83',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('05f5c91e-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','赵晓东','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19840240',NULL,NULL,NULL,'男',NULL,'691f027f26bc12ca44af68740cbf5396',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('05fb272e-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','汪树斌','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19860132',NULL,NULL,NULL,'男',NULL,'9db6dbfe7e8a276ada2f3007da4659c9',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('06096fe6-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','王冰','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19870215',NULL,NULL,NULL,'男',NULL,'cf2e45b54ea3e4d56a6374085495aeba',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('0614b586-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','苗悦','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20060157',NULL,NULL,NULL,'女',NULL,'7eb31565515ce1c4f1db12b9f5f98541',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('06213a54-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','陈鑫','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20070213',NULL,NULL,NULL,'男',NULL,'3ab41f743b9a83bc0dfd51c503f393ea',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('062ba192-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','王继刚','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20050097',NULL,NULL,NULL,'男',NULL,'c14f22c2613806c371ec202d7d76e0eb',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('0631522c-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','王爽','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19950168',NULL,NULL,NULL,'男',NULL,'6089b2fe4a6abc5996d5d24688085ec3',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('063e3474-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','孟繁浩','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20140171',NULL,NULL,NULL,'男',NULL,'5461e0906b952ed744bc79117208eca9',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('064960b0-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','栾斯洋','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19990140',NULL,NULL,NULL,'男',NULL,'b370e1ce44aa69fec564b9facac82810',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('065809d0-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','江秀峰','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20180057L',NULL,NULL,NULL,'男',NULL,'d5aca9a7d174e43d6753ccd1e263c703',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('06633ecc-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','高莉媛','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20180056L',NULL,NULL,NULL,'女',NULL,'de9701eb6b56f3862dab2670d1b5209c',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('0668b9ec-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','韩基刚','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19950167',NULL,NULL,NULL,'男',NULL,'c041d9bf32f5177bb252db504a958936',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('0675937e-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','刘颖矛','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19810125',NULL,NULL,NULL,'男',NULL,'9e8054bddfc6fc23dcfb542b5a0f0099',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('068015f6-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','高大全','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20060025',NULL,NULL,NULL,'男',NULL,'77998db09abb1f1ae50f00095e91932b',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('068b5bc8-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','王东','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20070150',NULL,NULL,NULL,'男',NULL,'1732d1df8a677994dd7d92e6c11f95b5',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('06a0dba6-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','刘宁','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20060190',NULL,NULL,NULL,'男',NULL,'fe32a5e0893e7439b15611be727ec59a',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('06a75990-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','郭四海','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19800122',NULL,NULL,NULL,'男',NULL,'8a7514376f4083bec8bc935b3066d060',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('06b25dcc-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','汤巨兴','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20070208',NULL,NULL,NULL,'男',NULL,'66abcfc5efb6018751bf0ea6f71f14f4',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('06b7a76e-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','李贞年','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20040207',NULL,NULL,NULL,'男',NULL,'65df772ecb38fcfc3c23076caa35f595',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('06c33b24-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','宫国军','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19860031',NULL,NULL,NULL,'男',NULL,'cb1ab3e244e2212cf61ae803c78c02fd',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('06ceb83c-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','栾石','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20040258',NULL,NULL,NULL,'男',NULL,'3e4e99377cd0c1d40dc28599a8cb01fc',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('06da302c-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','李朝伟','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19840246',NULL,NULL,NULL,'男',NULL,'06305017bff96ea5186b743be236c367',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('06dff9f8-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','杨少骞','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20040260',NULL,NULL,NULL,'男',NULL,'eed0309ef1705b18eca7740c0b23ca0c',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('06eba2b2-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','蒋子先','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20010101',NULL,NULL,NULL,'男',NULL,'61c64a760cea21289c23afd0b8df6642',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('06f6cc28-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','高升','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20201310L',NULL,NULL,NULL,'男',NULL,'4555b9ea02b5a7a6933405b122689466',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('0701a6de-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','李睿','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20040256',NULL,NULL,NULL,'男',NULL,'045f9a16fa15ac13c28ca1bf6bbc4e56',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('070c370c-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','国庆强','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20070209',NULL,NULL,NULL,'男',NULL,'062f1c477275169172fdc4882699c523',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('0711a1ce-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','潘博','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20070211',NULL,NULL,NULL,'男',NULL,'c406d84015b36221484df2a0dd86e83f',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('071f10ac-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','韩忠源','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20070201',NULL,NULL,NULL,'男',NULL,'1c820da076ec18cca9927a913e4a615e',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('07296214-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','赵丛日','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20070016',NULL,NULL,NULL,'男',NULL,'8362a5709ed83cd4fa98dbdd18eeab45',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('07342ece-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','杜秉峰','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19820252',NULL,NULL,NULL,'男',NULL,'97d3ea567f2bc11a94679a9a9081e0e8',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('074097a4-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','张立刚','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19930206',NULL,NULL,NULL,'男',NULL,'ed91e4b615f557b185585d8d39724ff9',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('074b1102-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','刘晓汉','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19790016',NULL,NULL,NULL,'男',NULL,'8e8816e2c02fd4c54e47ab128b6d4201',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('075576f6-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','杨岁军','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19850038',NULL,NULL,NULL,'男',NULL,'0fc079ee6dd14cd891eac16b391a1042',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('075fae64-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','王立君','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19860035',NULL,NULL,NULL,'男',NULL,'23ab4d4de55745978f6b82cc867872a0',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('076a5d46-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','孙权','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19790012',NULL,NULL,NULL,'男',NULL,'4a7b6fec8292660918ae672a87fdc435',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('076fef04-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','于春祥','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19830029',NULL,NULL,NULL,'男',NULL,'1aa56204ce6bdda366d06958d43dfbdb',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('077dd9d4-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','王彦民','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19810012',NULL,NULL,NULL,'男',NULL,'91f23d755b74886453de525f61d8d3be',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('078350bc-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','王刚','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19990138',NULL,NULL,NULL,'男',NULL,'b6406af6c8a331f3193408b31fc4be74',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('078d3eec-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','李岩','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19860232',NULL,NULL,NULL,'男',NULL,'17f862cacbf51eeb3c67730afc82b0b9',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-01-06 16:04:50.000000','2021-02-09 15:29:24.424723','超级管理员','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','admin','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','2021-02-24 11:25:23.928000','男','zhizhufan@foxmail.com','043ca3fc919e4db0dfc37cecf8490f1d','','73f1218d-9249-42a2-9e78-a5c7963fbec1'),('8059a5f4-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','郭军','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20040250',NULL,NULL,NULL,'男',NULL,'4500a3364c4cc58b9c3d9e1426fcabee',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('807226a6-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','关璐','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20000127',NULL,NULL,NULL,'男',NULL,'1b42e431de2230db3fb00593e7e28ffd',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('807776d8-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','沈方亮','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20201315L',NULL,NULL,NULL,'男',NULL,'677fa081dc02f75cade8d05b29ae47ea',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('8081db1e-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','国清江','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19840250',NULL,NULL,NULL,'男',NULL,'5a9ceb548a5e679714bb7d20349051cd',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('808bf590-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','岳忠罡','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20040249',NULL,NULL,NULL,'男',NULL,'f0c941c36639ef6ff16d1d22d4f0edc8',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('8092aaca-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','徐杰','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20201307L',NULL,NULL,NULL,'男',NULL,'26cfb9b553ca7716201b1e5cbb3eab04',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('8098d332-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','田间','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19810010',NULL,NULL,NULL,'男',NULL,'30a992c8da6cbf16b0281b93a41df33e',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('80a4ead2-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','丛洋','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20040262',NULL,NULL,NULL,'男',NULL,'63ed01567119a5cf77ae9a580baee5e5',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('80aa74b6-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','张勇立','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20070203',NULL,NULL,NULL,'男',NULL,'71168d5a847fc2cd2169d9bed81ffa81',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('80afee6e-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','牛宇','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19990139',NULL,NULL,NULL,'男',NULL,'150de35a392a122f9f35fe63c7e75d6b',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('80bb73ec-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','滕健卫','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19880232',NULL,NULL,NULL,'男',NULL,'2f79783dea64bb67e7013d16b98e5382',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('80c0b4d8-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','孙远达','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20070206',NULL,NULL,NULL,'男',NULL,'4a86d11b40aa050a8f028e3b4140d1f8',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('80cd0616-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','关兵','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20201319L',NULL,NULL,NULL,'男',NULL,'09e416ef791b20ccc93406061bba4168',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('80d8528c-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','卢鸣琰','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20201314L',NULL,NULL,NULL,'男',NULL,'edff390a88e260adc18b755369e08003',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('80e333aa-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','张振权','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20070205',NULL,NULL,NULL,'男',NULL,'af7c600f71f2bf2da7c0b7e7f329f6ec',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('80e86cbc-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','田源','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20201299L',NULL,NULL,NULL,'男',NULL,'be8141744f60097b237430eacff6189e',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('80f5db54-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','杜元冰','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19820245',NULL,NULL,NULL,'男',NULL,'4cb73bfde2018d618aea61677f0e434a',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('810018a8-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','栗延东','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19820239',NULL,NULL,NULL,'男',NULL,'2d6307f2a7360158c2520af53e271397',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('810ad234-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','马吉武','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19810127',NULL,NULL,NULL,'男',NULL,'27bfe9c7cdd98a79f8e3081a6658b05e',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('81172e26-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','王占滨','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19820250',NULL,NULL,NULL,'男',NULL,'8bab9aec6c1ba789607c988c664d0f6c',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('81229e8c-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','李敬武','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19800106',NULL,NULL,NULL,'男',NULL,'9c60a0eab4f2ccaaecc2c710c83310b1',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('812d382e-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','纪邵宏','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19820251',NULL,NULL,NULL,'男',NULL,'18472d8e6d18a8cfaa350ec3d0edd381',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('8138ea7a-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','桑滨生','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19840242',NULL,NULL,NULL,'男',NULL,'9fc2cde90bc9049ef5f07c6abb4b44ba',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('813e8e8a-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','张洪国','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19820243',NULL,NULL,NULL,'男',NULL,'bb963501511ab484385bbb6462940ad9',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('814c3134-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','刘保国','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19850226',NULL,NULL,NULL,'男',NULL,'da7694df184f3fa8e6eb7835f2673674',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('815688e6-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','国清海','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19820069',NULL,NULL,NULL,'男',NULL,'71487e7fc38c08f97290c718d480047e',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('8160aa9c-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','姜斌','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19850070',NULL,NULL,NULL,'男',NULL,'266b80cb73f7fdb04447a82970c7ac3c',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('817662b0-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','薛海峰','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19930076',NULL,NULL,NULL,'男',NULL,'1faa40580f0ef66b2f0b8cf7d6834a9d',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('817c5616-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','尚志强','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19790064',NULL,NULL,NULL,'男',NULL,'23ebe53161d6815c91e8b5364b7c2331',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('8182140c-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','王怀清','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19850068',NULL,NULL,NULL,'男',NULL,'3761e160261bdde1d003ee10ebef4d98',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('81876394-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','赵丽杰','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19830063',NULL,NULL,NULL,'男',NULL,'9bedf28139e4ebd8aab4853a53626e0a',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('818d1866-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','刘辉','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19790065',NULL,NULL,NULL,'男',NULL,'06715b75cbd38ff9a0fc88032d644a43',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('8196956c-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','李刚','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19860092',NULL,NULL,NULL,'男',NULL,'be9ba53f2e80dfc49ca5858caac30d89',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('819d2670-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','王卫东','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19790116',NULL,NULL,NULL,'男',NULL,'1296936f9d11f6376b8331af66317407',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('81a3fa22-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','安宏志','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19800040',NULL,NULL,NULL,'男',NULL,'28cebb3b81980fbb9a4d66ab45118cc5',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('81a91868-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','张文剑','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20201309L',NULL,NULL,NULL,'男',NULL,'faa1f6bcc3387ee8da13b49d6888940b',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('81b2e1d6-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','吴迪','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19970176',NULL,NULL,NULL,'男',NULL,'3155fc3ea69326c43790bc10d8509265',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('81be0d04-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','秦晖','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19860090',NULL,NULL,NULL,'男',NULL,'28b1bdf86797f4e0f36a125ce7a3d5f6',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('81c91ff0-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','冯建忠','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20040246',NULL,NULL,NULL,'男',NULL,'db06a07bbcb35b2ba0b469459203fb38',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('81d6ac92-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','王宏明','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19820253',NULL,NULL,NULL,'男',NULL,'c15e92a4f975fda99012c1c49c068f16',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('81e1dbd0-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','王保武','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19860082',NULL,NULL,NULL,'男',NULL,'e72617f55b41de2705508da27e1c2a91',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('81ec1848-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','马玉纯','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19800113',NULL,NULL,NULL,'男',NULL,'a2773f80b8cf2ee345f1732a7127a9ae',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('81f73f84-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','李博','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20201320L',NULL,NULL,NULL,'男',NULL,'f03554b3996eeb1acc0df9a1c5a07f21',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('8201c0bc-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','王迪','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20201607L',NULL,NULL,NULL,'男',NULL,'9f98c2d5b0ce731eb679771676c81349',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('820cb62a-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','张海涛','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20070212',NULL,NULL,NULL,'男',NULL,'2440a1e0dc2cbfb76cbbbe476bf6d14a',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('82174c16-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','魏鑫','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20201317L',NULL,NULL,NULL,'男',NULL,'cbe86b8bed79ba03e1e83f34f97b1939',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('82220b4c-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','翟伟庆','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20201311L',NULL,NULL,NULL,'男',NULL,'e75e0f9b43fb1c1c3e444a65c069423b',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('822ad84e-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','王彦臣','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19790015',NULL,NULL,NULL,'男',NULL,'3bc7e13fbfaefc00fd52acd6e20303d7',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('8237aba0-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','吴文波','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19820080',NULL,NULL,NULL,'男',NULL,'ce72ce56bcf84e4c652669fabe36fa3f',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('823d43da-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','孙守权','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19800019',NULL,NULL,NULL,'男',NULL,'76012ad7cb61fbeab9db511361099e57',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('e699e090-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','黄志厢','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19800015',NULL,NULL,NULL,'男',NULL,'8fd3c1ebed2dccec3acc3d030f8ba068',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('e6a03968-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','王志禹','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19810011',NULL,NULL,NULL,'男',NULL,'ce5af4f1cfaca60d952e03bf652f3762',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('e6adb160-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','张子伟','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19800118',NULL,NULL,NULL,'男',NULL,'1af3d68160ec65040a528d6d2a3d4ded',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('e6b52cc4-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','赵艳伟','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20160143',NULL,NULL,NULL,'男',NULL,'23f5fd3386dd4299be61da626fed4be9',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('e6c3b01e-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','闫跃权','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20040203',NULL,NULL,NULL,'男',NULL,'291eea409e583319eef4d580e3a40c5e',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('e6c97896-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','邵佳','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20050127',NULL,NULL,NULL,'男',NULL,'ca3af9b328687f10d4307c6a75ee3284',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('e6d81bf8-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','张立祥','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20060191',NULL,NULL,NULL,'男',NULL,'2168f8cfbeba37814319a9c2cdf4d474',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('e6e283fe-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','韩立文','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19850040',NULL,NULL,NULL,'男',NULL,'7719d6e674e28910d039770ccff57199',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('e6eeb1f6-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','张利国','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19790013',NULL,NULL,NULL,'男',NULL,'06b707b9e458b4e46b4967562282d0fe',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('e6f4a660-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','田振东','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','20100164',NULL,NULL,NULL,'男',NULL,'997eff7a4acb12ce5f9a2ea3601a9497',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('e6fa4e26-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','张玉江','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19800116',NULL,NULL,NULL,'男',NULL,'dfbb44cca7cf08281ffc84a3ce26ef39',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410'),('e6ffc5e0-7191-11eb-a327-786593b2c1b2','2021-01-06 16:04:50.000000','2021-01-06 16:04:50.000000','吴红旗','$2a$10$vl6lRZAqvHZ52bu6kVfPLu5fpd3wRmHmdAf.mUtr/G8WQltIGhl9y','19800114',NULL,NULL,NULL,'男',NULL,'7416846c1e467c7da1d9c23658a41f1c',NULL,'db7b186c-6a7c-11eb-a15a-1f40e3b3d410');
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
INSERT INTO `sys_user_role` VALUES ('1b3c1438-beb2-4bab-af86-b6b8dfb91114','a2a84058-8660-4393-a89d-6d1064f9b43a'),('05c71d58-7191-11eb-a327-786593b2c1b2','134f32fd-e488-4a6c-a300-5af4b8c467d6');
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
  `license_plate_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_iik8klcph48940klfc8xv48hb` (`number`),
  KEY `FKjwiu4tppuxd6yi0tv5ixwuert` (`create_by`),
  KEY `FKb16f6dhwax0cvugpvubf2h3y1` (`update_by`),
  KEY `FKtprj36fx1c1rr7skrml2hmswb` (`college_id`),
  CONSTRAINT `FKtprj36fx1c1rr7skrml2hmswb` FOREIGN KEY (`college_id`) REFERENCES `sys_college` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_vehicle`
--

LOCK TABLES `sys_vehicle` WRITE;
/*!40000 ALTER TABLE `sys_vehicle` DISABLE KEYS */;
INSERT INTO `sys_vehicle` VALUES ('3be4717d-da13-43eb-bc8b-be3ada5d52f6','2021-01-15 15:33:38.848561','2021-02-07 11:54:31.099380','fffffff',1,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','676cf193-fbc8-48c1-8a16-f1984c9071b9','青A.00000'),('a38de747-039e-427c-b033-dfd195ff90de','2021-01-15 15:33:26.349946','2021-02-07 10:59:15.793897','青A.00000',0,'1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','676cf193-fbc8-48c1-8a16-f1984c9071b9','8888888');
/*!40000 ALTER TABLE `sys_vehicle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_violation_type`
--

DROP TABLE IF EXISTS `sys_violation_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_violation_type` (
  `id` varchar(255) NOT NULL,
  `create_time` datetime(6) NOT NULL,
  `update_time` datetime(6) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK35y2uh373ghoin8xicdpmhtcq` (`create_by`),
  KEY `FKjkfwx10cbhh80057efgprt971` (`update_by`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_violation_type`
--

LOCK TABLES `sys_violation_type` WRITE;
/*!40000 ALTER TABLE `sys_violation_type` DISABLE KEYS */;
INSERT INTO `sys_violation_type` VALUES ('0a1f20c3-929d-4eaf-b1da-26f0b37a0168','2021-02-10 14:10:19.085317','2021-02-20 10:27:08.042000','违停','code003',NULL,NULL,'在专用停车场违规停放的,在校园内不按位停放的'),('1961895e-b30b-4433-804c-3120b991fa6b','2021-02-10 14:08:52.426687','2021-02-20 10:27:00.430000','违停','code004',NULL,NULL,'在校园内禁停道路,网格线内违规停放的,或步行区内及入口违规停放的'),('a2095ee8-a8a7-4221-beaa-42a2ae6b416d','2021-01-20 12:08:23.188216','2021-02-10 14:03:46.102731','超速2','code002','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','校园车内限速20公里道路'),('f0571407-fa10-4467-b260-75243ba6ab2e','2021-01-20 12:08:11.767313','2021-02-10 13:51:09.456851','超速','code001','1b3c1438-beb2-4bab-af86-b6b8dfb91114','1b3c1438-beb2-4bab-af86-b6b8dfb91114','校园车内限速30公里道路');
/*!40000 ALTER TABLE `sys_violation_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-24 13:05:53
