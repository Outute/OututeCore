-- MySQL dump 10.10
--
-- Host: localhost    Database: core
-- ------------------------------------------------------
-- Server version	5.0.24-community

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
-- Table structure for table `app_user`
--

DROP TABLE IF EXISTS `app_user`;
CREATE TABLE `app_user` (
  `id` bigint(20) NOT NULL auto_increment,
  `account_expired` bit(1) NOT NULL,
  `account_locked` bit(1) NOT NULL,
  `address` varchar(150) default NULL,
  `city` varchar(50) default NULL,
  `country` varchar(100) default NULL,
  `postal_code` varchar(15) default NULL,
  `province` varchar(100) default NULL,
  `credentials_expired` bit(1) NOT NULL,
  `email` varchar(255) NOT NULL,
  `account_enabled` bit(1) default NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `password_hint` varchar(255) default NULL,
  `phone_number` varchar(255) default NULL,
  `username` varchar(50) NOT NULL,
  `version` int(11) default NULL,
  `website` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `app_user`
--


/*!40000 ALTER TABLE `app_user` DISABLE KEYS */;
LOCK TABLES `app_user` WRITE;
INSERT INTO `app_user` VALUES (-2,'\0','\0','','Denver','US','80210','CO','\0','outute@163.com','\1','Matt','Raible','d033e22ae348aeb5660fc2140aec35850c4da997','Not a female kitty.','','admin',1,'http://raibledesigns.com'),(-1,'\0','\0','','Denver','US','80210','CO','\0','iffiff1@163.com','\1','Tomcat','User','12dea96fec20593566ab75692c9949596833adc9','A male kitty.','','user',1,'http://tomcat.apache.org');
UNLOCK TABLES;
/*!40000 ALTER TABLE `app_user` ENABLE KEYS */;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL auto_increment,
  `description` varchar(64) default NULL,
  `name` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `role`
--


/*!40000 ALTER TABLE `role` DISABLE KEYS */;
LOCK TABLES `role` WRITE;
INSERT INTO `role` VALUES (-4,'Default role for all Students','ROLE_STUDENT'),(-3,'Default role for all Tutors','ROLE_TUTOR'),(-2,'Default role for all Users','ROLE_USER'),(-1,'Administrator role (can edit Users)','ROLE_ADMIN');
UNLOCK TABLES;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

--
-- Table structure for table `system_configure`
--

DROP TABLE IF EXISTS `system_configure`;
CREATE TABLE `system_configure` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `system_configure`
--


/*!40000 ALTER TABLE `system_configure` DISABLE KEYS */;
LOCK TABLES `system_configure` WRITE;
INSERT INTO `system_configure` VALUES (1,'NOTIFICATION_DUETIME_MINUTE','15');
UNLOCK TABLES;
/*!40000 ALTER TABLE `system_configure` ENABLE KEYS */;

--
-- Table structure for table `tutorial`
--

DROP TABLE IF EXISTS `tutorial`;
CREATE TABLE `tutorial` (
  `id` bigint(20) NOT NULL auto_increment,
  `category` int(11) NOT NULL,
  `cost` int(11) NOT NULL,
  `createTime` datetime NOT NULL,
  `description` varchar(256) default NULL,
  `enabled` bit(1) NOT NULL,
  `lengthInMins` int(11) NOT NULL,
  `maxParticipate` int(11) NOT NULL,
  `method` int(11) NOT NULL,
  `modifyTime` datetime NOT NULL,
  `name` varchar(50) NOT NULL,
  `openDays` int(11) NOT NULL,
  `schedule` datetime NOT NULL,
  `tutorialExpired` bit(1) default NULL,
  `tutorialLocked` bit(1) default NULL,
  `type` int(11) NOT NULL,
  `version` int(11) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tutorial`
--


/*!40000 ALTER TABLE `tutorial` DISABLE KEYS */;
LOCK TABLES `tutorial` WRITE;
INSERT INTO `tutorial` VALUES (1,1,20,'2010-05-23 13:30:00','a test tutorial','\1',30,0,1,'2010-06-23 13:30:00','test tutorial',7,'2010-05-23 13:30:00','\1','\0',1,0);
UNLOCK TABLES;
/*!40000 ALTER TABLE `tutorial` ENABLE KEYS */;

--
-- Table structure for table `tutorial_schedule`
--

DROP TABLE IF EXISTS `tutorial_schedule`;
CREATE TABLE `tutorial_schedule` (
  `id` bigint(20) NOT NULL auto_increment,
  `cost` int(11) default NULL,
  `create_time` datetime default NULL,
  `duration_type` int(11) NOT NULL,
  `end_date` date NOT NULL,
  `ends_occurrence` int(11) NOT NULL,
  `from_time` datetime NOT NULL,
  `max_participate` int(11) default NULL,
  `modify_time` datetime default NULL,
  `start_date` date NOT NULL,
  `to_time` datetime NOT NULL,
  `version` int(11) default NULL,
  `tutorial_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FKFEDE825814280904` (`tutorial_id`),
  CONSTRAINT `FKFEDE825814280904` FOREIGN KEY (`tutorial_id`) REFERENCES `tutorial` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tutorial_schedule`
--


/*!40000 ALTER TABLE `tutorial_schedule` DISABLE KEYS */;
LOCK TABLES `tutorial_schedule` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `tutorial_schedule` ENABLE KEYS */;

--
-- Table structure for table `tutorial_schedule_student`
--

DROP TABLE IF EXISTS `tutorial_schedule_student`;
CREATE TABLE `tutorial_schedule_student` (
  `lecture_date` date NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `tutorial_schedule_id` bigint(20) NOT NULL,
  `flag` int(11) NOT NULL,
  `last_update_time` datetime default NULL,
  `lecture_time` datetime NOT NULL,
  PRIMARY KEY  (`lecture_date`,`user_id`,`tutorial_schedule_id`),
  KEY `FK1550D3545FEFA681` (`tutorial_schedule_id`),
  KEY `FK1550D3542D852AE4` (`user_id`),
  CONSTRAINT `FK1550D3542D852AE4` FOREIGN KEY (`user_id`) REFERENCES `app_user` (`id`),
  CONSTRAINT `FK1550D3545FEFA681` FOREIGN KEY (`tutorial_schedule_id`) REFERENCES `tutorial_schedule` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tutorial_schedule_student`
--


/*!40000 ALTER TABLE `tutorial_schedule_student` DISABLE KEYS */;
LOCK TABLES `tutorial_schedule_student` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `tutorial_schedule_student` ENABLE KEYS */;

--
-- Table structure for table `tutorial_student`
--

DROP TABLE IF EXISTS `tutorial_student`;
CREATE TABLE `tutorial_student` (
  `tutorial_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`tutorial_id`,`user_id`),
  KEY `FKC2D9165A2D852AE4` (`user_id`),
  KEY `FKC2D9165A14280904` (`tutorial_id`),
  CONSTRAINT `FKC2D9165A14280904` FOREIGN KEY (`tutorial_id`) REFERENCES `tutorial` (`id`),
  CONSTRAINT `FKC2D9165A2D852AE4` FOREIGN KEY (`user_id`) REFERENCES `app_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tutorial_student`
--


/*!40000 ALTER TABLE `tutorial_student` DISABLE KEYS */;
LOCK TABLES `tutorial_student` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `tutorial_student` ENABLE KEYS */;

--
-- Table structure for table `tutorial_tutor`
--

DROP TABLE IF EXISTS `tutorial_tutor`;
CREATE TABLE `tutorial_tutor` (
  `tutorial_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`tutorial_id`,`user_id`),
  KEY `FK54F8A7152D852AE4` (`user_id`),
  KEY `FK54F8A71514280904` (`tutorial_id`),
  CONSTRAINT `FK54F8A71514280904` FOREIGN KEY (`tutorial_id`) REFERENCES `tutorial` (`id`),
  CONSTRAINT `FK54F8A7152D852AE4` FOREIGN KEY (`user_id`) REFERENCES `app_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tutorial_tutor`
--


/*!40000 ALTER TABLE `tutorial_tutor` DISABLE KEYS */;
LOCK TABLES `tutorial_tutor` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `tutorial_tutor` ENABLE KEYS */;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`user_id`,`role_id`),
  KEY `FK143BF46A885A6704` (`role_id`),
  KEY `FK143BF46A2D852AE4` (`user_id`),
  CONSTRAINT `FK143BF46A2D852AE4` FOREIGN KEY (`user_id`) REFERENCES `app_user` (`id`),
  CONSTRAINT `FK143BF46A885A6704` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_role`
--


/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
LOCK TABLES `user_role` WRITE;
INSERT INTO `user_role` VALUES (-1,-2),(-2,-1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

