start transaction;

create database `Acme-HackerRank`;

use `Acme-HackerRank`;

create user 'acme-user'@'%' 
	identified by password '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577';

create user 'acme-manager'@'%' 
	identified by password '*FDB8CD304EB2317D10C95D797A4BD7492560F55F';

grant select, insert, update, delete 
	on `Acme-HackerRank`.* to 'acme-user'@'%';

grant select, insert, update, delete, create, drop, references, index, alter, 
        create temporary tables, lock tables, create view, create routine, 
        alter routine, execute, trigger, show view
    on `Acme-HackerRank`.* to 'acme-manager'@'%';

-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: Acme-HackerRank
-- ------------------------------------------------------
-- Server version	5.5.29

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
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `vatnumber` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `cvv_code` int(11) NOT NULL,
  `expiration_month` varchar(255) DEFAULT NULL,
  `expiration_year` varchar(255) DEFAULT NULL,
  `holder` varchar(255) DEFAULT NULL,
  `make` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_spammer` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_7ohwsa2usmvu0yxb44je2lge` (`user_account`),
  UNIQUE KEY `UK_jj3mmcc0vjobqibj67dvuwihk` (`email`),
  CONSTRAINT `FK_7ohwsa2usmvu0yxb44je2lge` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (4201,0,65,'Calle Admin 1',724,'08','21','holder1','VISA','38353348140483','admin1@gmail.com',NULL,'Admin1','63018754','http://www.littlehearts.ind.in/wp-content/themes/lhs/Birthday/images/adm.png','Ruiz',4187),(4202,0,97,'Calle Admin 2',587,'10','22','holder2','VISA','4716895067094219','admin2@gmail.com',NULL,'System','63015521','https://image.freepik.com/free-photo/linux-kubuntu-operating-logo-system-options_121-97849.jpg','Reina',4188);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `code_link` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` VALUES (4203,0,'https://www.code.com','Answer1, Application1'),(4204,0,'https://www.code.com','Answer2, Application2'),(4205,0,'https://www.code.com','Answer4, Application4'),(4206,0,'https://www.code.com','Answer5, Application5'),(4207,0,'https://www.code.com','Answer6, Application6'),(4208,0,'https://www.code.com','Answer7, Application7'),(4209,0,'https://www.code.com','Answer9, Application9'),(4210,0,'https://www.code.com','Answer10, Application10'),(4211,0,'https://www.code.com','Answer11, Application11'),(4212,0,'https://www.code.com','Answer12, Application12'),(4213,0,'https://www.code.com','Answer14, Application14');
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `application_moment` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `submitted_moment` datetime DEFAULT NULL,
  `answer` int(11) DEFAULT NULL,
  `curriculum` int(11) NOT NULL,
  `hacker` int(11) NOT NULL,
  `position` int(11) NOT NULL,
  `problem` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sjing1smhndaor3vyjfk4hsn2` (`curriculum`),
  UNIQUE KEY `UK_cwv8t86bugyjmxjf6maq5mxey` (`hacker`,`position`),
  KEY `UK_m5p5f217d0wseigmrjgpk99k4` (`hacker`,`status`),
  KEY `UK_m10tgfnkn56kq5i6mrhpahm0g` (`position`,`status`),
  KEY `UK_ak4fyog5wv0nthlp0u0ns0u69` (`problem`,`hacker`),
  KEY `FK_lvkbgejfw0xfg2m80h1bjjmau` (`answer`),
  CONSTRAINT `FK_7gn6fojv7rim6rxyglc6n9mt2` FOREIGN KEY (`problem`) REFERENCES `problem` (`id`),
  CONSTRAINT `FK_3hgwemcpn15ns7bi2upsq613y` FOREIGN KEY (`hacker`) REFERENCES `hacker` (`id`),
  CONSTRAINT `FK_cqpb54jon3yjef814oj6g6o4n` FOREIGN KEY (`position`) REFERENCES `position` (`id`),
  CONSTRAINT `FK_lvkbgejfw0xfg2m80h1bjjmau` FOREIGN KEY (`answer`) REFERENCES `answer` (`id`),
  CONSTRAINT `FK_sjing1smhndaor3vyjfk4hsn2` FOREIGN KEY (`curriculum`) REFERENCES `curriculum` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
INSERT INTO `application` VALUES (4384,0,'2019-01-01 10:10:00','ACCEPTED','2019-02-01 10:10:00',4203,4352,4231,4305,4333),(4385,0,'2019-01-01 10:10:00','ACCEPTED','2019-02-01 10:10:00',4204,4355,4232,4305,4333),(4386,0,'2019-01-01 10:10:00','PENDING',NULL,NULL,4359,4233,4305,4334),(4387,0,'2019-01-01 10:10:00','SUBMITTED','2019-02-01 10:10:00',4205,4369,4236,4306,4333),(4388,0,'2019-01-01 10:10:00','REJECTED','2019-02-01 10:10:00',4206,4353,4231,4306,4335),(4389,0,'2019-01-01 10:10:00','REJECTED','2019-02-01 10:10:00',4207,4362,4234,4310,4337),(4390,0,'2019-01-01 10:10:00','SUBMITTED','2019-02-01 10:10:00',4208,4366,4235,4310,4341),(4391,0,'2019-01-01 10:10:00','PENDING',NULL,NULL,4356,4232,4311,4338),(4392,0,'2019-01-01 10:10:00','REJECTED','2019-02-01 10:10:00',4209,4367,4235,4311,4339),(4393,0,'2019-01-01 10:10:00','ACCEPTED','2019-02-01 10:10:00',4210,4371,4237,4311,4340),(4394,0,'2019-01-01 10:10:00','REJECTED','2019-02-01 10:10:00',4211,4357,4232,4312,4344),(4395,0,'2019-01-01 10:10:00','SUBMITTED','2019-02-01 10:10:00',4212,4363,4234,4312,4345),(4396,0,'2019-01-01 10:10:00','PENDING',NULL,NULL,4364,4234,4313,4345),(4397,0,'2019-01-01 10:10:00','ACCEPTED','2019-02-01 10:10:00',4213,4360,4233,4313,4345);
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `vatnumber` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `cvv_code` int(11) NOT NULL,
  `expiration_month` varchar(255) DEFAULT NULL,
  `expiration_year` varchar(255) DEFAULT NULL,
  `holder` varchar(255) DEFAULT NULL,
  `make` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_spammer` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) NOT NULL,
  `commercial_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pno7oguspp7fxv0y2twgplt0s` (`user_account`),
  UNIQUE KEY `UK_bma9lv19ba3yjwf12a34xord3` (`email`),
  UNIQUE KEY `UK_4askypslrvhwn9noojdiojojc` (`commercial_name`),
  CONSTRAINT `FK_pno7oguspp7fxv0y2twgplt0s` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (4214,0,97,'Calle Company 1',587,'03','22','holder3','VISA','4389142361978458','company1@gmail.com',NULL,'Company1','63015521','http://webfeb.in/wp-content/uploads/2016/11/logo-design-for-it-company.jpg','Garcia',4189,'One Commercial Name'),(4215,0,97,'Calle Company 2',147,'02','21','holder4','VISA','4916210851536995','company2@gmail.com',NULL,'Company2','63015521','https://www.freelogodesign.org/Content/img/logo-ex-6.png','Lobato',4190,'Two Commercial Name'),(4216,0,97,'Calle Company 3',258,'01','20','holder5','MCARD','4539294619605521','company3@gmail.com',NULL,'Company3','63015521','https://www.freelogodesign.org/Content/img/logo-ex-5.png','Perez',4191,'Three Commercial Name');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curriculum`
--

DROP TABLE IF EXISTS `curriculum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curriculum` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `is_original` bit(1) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `hacker` int(11) NOT NULL,
  `personal_data` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_14ypx9rpvo8410mt1jmkx1wyw` (`personal_data`),
  KEY `UK_h56gsmadrk648f6pa53hcybul` (`is_original`,`hacker`),
  KEY `FK_njodnr2nr4mxaudsmvh5irkdi` (`hacker`),
  CONSTRAINT `FK_14ypx9rpvo8410mt1jmkx1wyw` FOREIGN KEY (`personal_data`) REFERENCES `personal_data` (`id`),
  CONSTRAINT `FK_njodnr2nr4mxaudsmvh5irkdi` FOREIGN KEY (`hacker`) REFERENCES `hacker` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curriculum`
--

LOCK TABLES `curriculum` WRITE;
/*!40000 ALTER TABLE `curriculum` DISABLE KEYS */;
INSERT INTO `curriculum` VALUES (4351,0,'','Curriculum del Hacker 1',4231,4283),(4352,0,'\0','Copia de Curriculum del Hacker 1',4231,4284),(4353,0,'\0','Copia de Curriculum del Hacker 1',4231,4285),(4354,0,'','Curriculum del Hacker 2',4232,4286),(4355,0,'\0','Copia de Curriculum del Hacker 2',4232,4287),(4356,0,'\0','Copia de Curriculum del Hacker 2',4232,4291),(4357,0,'\0','Copia de Curriculum del Hacker 2',4236,4299),(4358,0,'','Curriculum del Hacker 3',4233,4288),(4359,0,'\0','Copia de Curriculum del Hacker 3',4233,4289),(4360,0,'\0','Copia de Curriculum del Hacker 3',4233,4290),(4361,0,'','Curriculum del Hacker 4',4234,4292),(4362,0,'\0','Copia de Curriculum del Hacker 4',4234,4293),(4363,0,'\0','Copia de Curriculum del Hacker 4',4234,4294),(4364,0,'\0','Copia de Curriculum del Hacker 4',4237,4302),(4365,0,'','Curriculum del Hacker 5',4235,4295),(4366,0,'\0','Copia de Curriculum del Hacker 5',4235,4296),(4367,0,'\0','Copia de Curriculum del Hacker 5',4237,4303),(4368,0,'','Curriculum del Hacker 6',4236,4297),(4369,0,'\0','Copia de Curriculum del Hacker 6',4236,4298),(4370,0,'','Curriculum del Hacker 7',4237,4300),(4371,0,'\0','Copia de Curriculum del Hacker 7',4237,4301),(4372,0,'','Curriculum del Hacker 8',4238,4304);
/*!40000 ALTER TABLE `curriculum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curriculum_education_datas`
--

DROP TABLE IF EXISTS `curriculum_education_datas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curriculum_education_datas` (
  `curriculum` int(11) NOT NULL,
  `education_datas` int(11) NOT NULL,
  UNIQUE KEY `UK_h03suk6hhcmp350vmwcs9m7k3` (`education_datas`),
  KEY `FK_o783xr31yi8vovi9q0fxe3pm2` (`curriculum`),
  CONSTRAINT `FK_o783xr31yi8vovi9q0fxe3pm2` FOREIGN KEY (`curriculum`) REFERENCES `curriculum` (`id`),
  CONSTRAINT `FK_h03suk6hhcmp350vmwcs9m7k3` FOREIGN KEY (`education_datas`) REFERENCES `education_data` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curriculum_education_datas`
--

LOCK TABLES `curriculum_education_datas` WRITE;
/*!40000 ALTER TABLE `curriculum_education_datas` DISABLE KEYS */;
INSERT INTO `curriculum_education_datas` VALUES (4351,4218),(4352,4219),(4352,4220),(4353,4221),(4353,4222),(4361,4223),(4362,4224),(4363,4225),(4365,4226),(4365,4227),(4366,4228),(4366,4229),(4372,4230);
/*!40000 ALTER TABLE `curriculum_education_datas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curriculum_miscellaneous_datas`
--

DROP TABLE IF EXISTS `curriculum_miscellaneous_datas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curriculum_miscellaneous_datas` (
  `curriculum` int(11) NOT NULL,
  `miscellaneous_datas` int(11) NOT NULL,
  UNIQUE KEY `UK_pn9k3t3bcipwqxkyraju77p47` (`miscellaneous_datas`),
  KEY `FK_asudhgax0bfxmqjshst691ml` (`curriculum`),
  CONSTRAINT `FK_asudhgax0bfxmqjshst691ml` FOREIGN KEY (`curriculum`) REFERENCES `curriculum` (`id`),
  CONSTRAINT `FK_pn9k3t3bcipwqxkyraju77p47` FOREIGN KEY (`miscellaneous_datas`) REFERENCES `miscellaneous_data` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curriculum_miscellaneous_datas`
--

LOCK TABLES `curriculum_miscellaneous_datas` WRITE;
/*!40000 ALTER TABLE `curriculum_miscellaneous_datas` DISABLE KEYS */;
INSERT INTO `curriculum_miscellaneous_datas` VALUES (4351,4246),(4351,4247),(4352,4248),(4353,4249),(4354,4250),(4354,4251),(4355,4252),(4355,4253),(4356,4257),(4357,4264),(4357,4265),(4358,4254),(4359,4255),(4360,4256),(4364,4268),(4365,4258),(4366,4259),(4367,4269),(4368,4260),(4368,4261),(4369,4262),(4369,4263),(4370,4266),(4371,4267);
/*!40000 ALTER TABLE `curriculum_miscellaneous_datas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curriculum_position_datas`
--

DROP TABLE IF EXISTS `curriculum_position_datas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curriculum_position_datas` (
  `curriculum` int(11) NOT NULL,
  `position_datas` int(11) NOT NULL,
  UNIQUE KEY `UK_imvuypdut7j9qj727srfqihuh` (`position_datas`),
  KEY `FK_9m5vouqexttm1x4o5r5o1u0f8` (`curriculum`),
  CONSTRAINT `FK_9m5vouqexttm1x4o5r5o1u0f8` FOREIGN KEY (`curriculum`) REFERENCES `curriculum` (`id`),
  CONSTRAINT `FK_imvuypdut7j9qj727srfqihuh` FOREIGN KEY (`position_datas`) REFERENCES `position_data` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curriculum_position_datas`
--

LOCK TABLES `curriculum_position_datas` WRITE;
/*!40000 ALTER TABLE `curriculum_position_datas` DISABLE KEYS */;
INSERT INTO `curriculum_position_datas` VALUES (4351,4314),(4351,4315),(4354,4316),(4355,4317),(4356,4321),(4357,4327),(4358,4318),(4359,4319),(4360,4320),(4361,4322),(4362,4323),(4363,4324),(4364,4330),(4367,4331),(4368,4325),(4369,4326),(4370,4328),(4371,4329),(4372,4332);
/*!40000 ALTER TABLE `curriculum_position_datas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customisation`
--

DROP TABLE IF EXISTS `customisation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customisation` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `banner` varchar(255) DEFAULT NULL,
  `country_code` varchar(255) DEFAULT NULL,
  `max_number_results` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `spam_words` varchar(255) DEFAULT NULL,
  `time_cached_results` int(11) NOT NULL,
  `welcome_message_en` varchar(255) DEFAULT NULL,
  `welcome_message_es` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customisation`
--

LOCK TABLES `customisation` WRITE;
/*!40000 ALTER TABLE `customisation` DISABLE KEYS */;
INSERT INTO `customisation` VALUES (4217,0,'https://i.imgur.com/7b8lu4b.png','+34',10,'Acme Hacker Rank','sex,viagra,cialis,one million,you\'ve been selected,Nigeria,sexo,un millon,ha sido seleccionado',1,'Welcome to Acme Hacker Rank! We\'re IT hacker\'s favourite job marketplace!','¡Bienvenidos a Acme Hacker Rank! ¡Somos el mercado de trabajo favorito de los profesionales de las TICs!');
/*!40000 ALTER TABLE `customisation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `education_data`
--

DROP TABLE IF EXISTS `education_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `education_data` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `degree` varchar(255) DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `institution` varchar(255) DEFAULT NULL,
  `mark` double NOT NULL,
  `start_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `education_data`
--

LOCK TABLES `education_data` WRITE;
/*!40000 ALTER TABLE `education_data` DISABLE KEYS */;
INSERT INTO `education_data` VALUES (4218,0,'Ingeniería Informática del Software',NULL,'Universidad de Sevilla',6.2,'2014-10-20'),(4219,0,'Ingeniería Informática del Software','2016-09-25','Universidad de Málaga',8.6,'2010-10-11'),(4220,0,'Ingeniería Informática','2018-05-20','Universidad de Sevilla',7.5,'2014-10-12'),(4221,0,'Ingeniería Informática','2018-05-20','Universidad de Sevilla',7.5,'2014-10-12'),(4222,0,'Ingeniería Informática','2018-05-20','Universidad de Sevilla',7.5,'2014-10-12'),(4223,0,'Ingeniería Informática Tecnologías','2014-07-24','Universidad de Sevilla',5.6,'2010-10-20'),(4224,0,'Ingeniería Informática Tecnologías','2015-07-01','Universidad de Sevilla',8.6,'2011-10-02'),(4225,0,'Ingeniería Informática Tecnologías','2015-07-01','Universidad de Sevilla',8.6,'2011-10-02'),(4226,0,'Ingeniería Informática Tecnologías','2014-07-24','Universidad de Sevilla',7.6,'2010-10-20'),(4227,0,'Ingeniería Informática Tecnologías','2017-07-01','Universidad de Sevilla',9.6,'2012-10-02'),(4228,0,'Ingeniería Informática Tecnologías','2014-07-24','Universidad de Sevilla',7.6,'2010-10-20'),(4229,0,'Ingeniería Informática Tecnologías','2017-07-01','Universidad de Sevilla',9.6,'2012-10-02'),(4230,0,'Ingeniería Informática Tecnologías','2017-07-01','Universidad de Sevilla',6.3,'2012-10-02');
/*!40000 ALTER TABLE `education_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `finder`
--

DROP TABLE IF EXISTS `finder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `finder` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `deadline` date DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `maximum_deadline` date DEFAULT NULL,
  `minimum_salary` double DEFAULT NULL,
  `updated_moment` datetime DEFAULT NULL,
  `hacker` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_41crxbc805xo41bt5dr6nxtdp` (`hacker`),
  KEY `UK_n08k0vrypppbsqf2gwmtk7y8o` (`keyword`,`deadline`,`minimum_salary`,`maximum_deadline`),
  CONSTRAINT `FK_41crxbc805xo41bt5dr6nxtdp` FOREIGN KEY (`hacker`) REFERENCES `hacker` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finder`
--

LOCK TABLES `finder` WRITE;
/*!40000 ALTER TABLE `finder` DISABLE KEYS */;
INSERT INTO `finder` VALUES (4373,0,'2020-10-05','estoEsUnaPruebaParaQueNoMeDevuelvaNada','2022-10-05',965.24,'2019-01-01 10:10:00',4231),(4374,0,NULL,'',NULL,NULL,'2019-01-01 10:10:00',4232),(4375,0,'2020-10-05','','2022-10-05',7965.24,'2019-01-01 10:10:00',4233),(4376,0,NULL,'position4',NULL,NULL,'2019-01-01 10:10:00',4234),(4377,0,NULL,'position3',NULL,NULL,'2019-01-01 10:10:00',4235),(4378,0,NULL,'company2',NULL,NULL,'2019-01-01 10:10:00',4236),(4379,0,'2020-10-05','NoQuieroNada','2022-10-05',965.24,'2019-01-01 10:10:00',4237),(4380,0,NULL,'Company',NULL,NULL,'2019-01-01 10:10:00',4238),(4381,0,NULL,'',NULL,NULL,'2019-01-01 10:15:00',4239);
/*!40000 ALTER TABLE `finder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `finder_positions`
--

DROP TABLE IF EXISTS `finder_positions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `finder_positions` (
  `finder` int(11) NOT NULL,
  `positions` int(11) NOT NULL,
  KEY `FK_3d46gil0nks2dhgg7cyhv2m39` (`positions`),
  KEY `FK_l0b3qg4nly59oeqhe8ig5yssc` (`finder`),
  CONSTRAINT `FK_l0b3qg4nly59oeqhe8ig5yssc` FOREIGN KEY (`finder`) REFERENCES `finder` (`id`),
  CONSTRAINT `FK_3d46gil0nks2dhgg7cyhv2m39` FOREIGN KEY (`positions`) REFERENCES `position` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finder_positions`
--

LOCK TABLES `finder_positions` WRITE;
/*!40000 ALTER TABLE `finder_positions` DISABLE KEYS */;
INSERT INTO `finder_positions` VALUES (4374,4305),(4374,4306),(4374,4308),(4376,4308),(4378,4306),(4380,4305),(4380,4306),(4380,4308),(4381,4305),(4381,4306),(4381,4308);
/*!40000 ALTER TABLE `finder_positions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hacker`
--

DROP TABLE IF EXISTS `hacker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hacker` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `vatnumber` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `cvv_code` int(11) NOT NULL,
  `expiration_month` varchar(255) DEFAULT NULL,
  `expiration_year` varchar(255) DEFAULT NULL,
  `holder` varchar(255) DEFAULT NULL,
  `make` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_spammer` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pechhr6iex4b7l2rymmx1xi38` (`user_account`),
  UNIQUE KEY `UK_ovpmggeh69q4p2qhdo4tw6asy` (`email`),
  CONSTRAINT `FK_pechhr6iex4b7l2rymmx1xi38` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hacker`
--

LOCK TABLES `hacker` WRITE;
/*!40000 ALTER TABLE `hacker` DISABLE KEYS */;
INSERT INTO `hacker` VALUES (4231,0,97,'Calle Hacker 1',258,'02','22','holder6','MCARD','4024007150182987','hacker1@gmail.com',NULL,'Hacker1','63015521','https://www.madmen-onlinemarketing.de/wp-content/uploads/jan-madmen-onlinemarketing-e1525337078609.jpg','Alvarez',4192),(4232,0,97,'Calle Hacker 2',254,'02','21','holder7','MCARD','4556001181801737','hacker2@gmail.com',NULL,'Hacker2','63015521','https://www.madmen-onlinemarketing.de/wp-content/uploads/jan-madmen-onlinemarketing-e1525337078609.jpg','Munoz',4193),(4233,0,97,'Calle Hacker 3',218,'03','22','holder8','MCARD','4485923119929700','hacker3@gmail.com',NULL,'Hacker3','63015521','https://www.madmen-onlinemarketing.de/wp-content/uploads/jan-madmen-onlinemarketing-e1525337078609.jpg','Esteban',4194),(4234,0,97,'Calle Hacker 4',250,'04','21','holder9','MCARD','5265265673659825','hacker4@gmail.com',NULL,'Hacker4','63015521','https://www.madmen-onlinemarketing.de/wp-content/uploads/jan-madmen-onlinemarketing-e1525337078609.jpg','Rodriguez',4195),(4235,0,97,'Calle Hacker 5',118,'03','22','holder10','MCARD','5466799096597995','hacker5@gmail.com',NULL,'Hacker5','63015521','https://www.madmen-onlinemarketing.de/wp-content/uploads/jan-madmen-onlinemarketing-e1525337078609.jpg','Lopez',4196),(4236,0,97,'Calle Hacker 6',950,'04','21','holder11','MCARD','5386393115853022','hacker6@gmail.com',NULL,'Hacker6','63015521','https://www.madmen-onlinemarketing.de/wp-content/uploads/jan-madmen-onlinemarketing-e1525337078609.jpg','Barrera',4197),(4237,0,97,'Calle Hacker 7',124,'03','22','holder12','MCARD','5283115543844354','hacker7@gmail.com',NULL,'Hacker7','63015521','https://www.madmen-onlinemarketing.de/wp-content/uploads/jan-madmen-onlinemarketing-e1525337078609.jpg','Navarro',4198),(4238,0,97,'Calle Hacker 8',180,'04','21','holder13','MCARD','5311247326144102','hacker8@gmail.com',NULL,'Hacker8','63015521','https://www.madmen-onlinemarketing.de/wp-content/uploads/jan-madmen-onlinemarketing-e1525337078609.jpg','Moreno',4199),(4239,0,32,'Calle Hacker 9',613,'07','24','holder14','MCARD','5363673506446743','hacker9@gmail.com',NULL,'Hacker9','632321239','https://www.madmen-onlinemarketing.de/wp-content/uploads/jan-madmen-onlinemarketing-e1525337078609.jpg','Rubio',4200);
/*!40000 ALTER TABLE `hacker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('domain_entity',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` longtext,
  `is_spam` bit(1) NOT NULL,
  `sent_moment` datetime DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `tags` varchar(255) DEFAULT NULL,
  `sender` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_iwf26op1bvdfl6ubuvhbbp49p` (`sender`,`is_spam`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (4240,0,'Thanks for your reply','\0','2017-05-09 10:17:00','Thank you','notification',4231),(4241,0,'Thanks for your reply','\0','2017-05-09 10:17:00','Thank you','problem',4201),(4242,0,'Thanks for your reply','\0','2017-05-09 10:17:00','Thank you','position',4202),(4243,0,'Thanks for your reply','\0','2017-05-09 10:17:00','Thank you','answer',4238),(4244,0,'Thanks for your reply','\0','2017-05-09 10:17:00','Thank you','curriculum',4214),(4245,0,'Thanks for your reply','\0','2017-05-09 10:17:00','Thank you','position',4202);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message_recipients`
--

DROP TABLE IF EXISTS `message_recipients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message_recipients` (
  `message` int(11) NOT NULL,
  `recipients` int(11) NOT NULL,
  KEY `FK_1odmg2n3n487tvhuxx5oyyya2` (`message`),
  CONSTRAINT `FK_1odmg2n3n487tvhuxx5oyyya2` FOREIGN KEY (`message`) REFERENCES `message` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_recipients`
--

LOCK TABLES `message_recipients` WRITE;
/*!40000 ALTER TABLE `message_recipients` DISABLE KEYS */;
INSERT INTO `message_recipients` VALUES (4240,4232),(4241,4231),(4241,4232),(4241,4233),(4241,4234),(4241,4235),(4241,4236),(4241,4238),(4242,4214),(4242,4215),(4242,4216),(4243,4233),(4244,4231),(4244,4232),(4244,4233),(4244,4234),(4244,4235),(4244,4236),(4244,4238),(4245,4214),(4245,4215),(4245,4216),(4245,4231),(4245,4232),(4245,4233),(4245,4234);
/*!40000 ALTER TABLE `message_recipients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `miscellaneous_data`
--

DROP TABLE IF EXISTS `miscellaneous_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `miscellaneous_data` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `attachments` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `miscellaneous_data`
--

LOCK TABLES `miscellaneous_data` WRITE;
/*!40000 ALTER TABLE `miscellaneous_data` DISABLE KEYS */;
INSERT INTO `miscellaneous_data` VALUES (4246,0,'http://www.attachment.com/data1','text miscelleneous data1 del hacker 1'),(4247,0,'http://www.attachment.com/data2','text miscelleneous data2 del hacker 1'),(4248,0,'http://www.attachment.com/data3','text miscelleneous data3 del hacker 1'),(4249,0,'http://www.attachment.com/data3','text miscelleneous data4 del hacker 1'),(4250,0,'http://www.attachment.com/data4','text miscelleneous data1 del hacker 2'),(4251,0,'http://www.attachment.com/data5','text miscelleneous data2 del hacker 2'),(4252,0,'http://www.attachment.com/data4','text miscelleneous data3 del hacker 2'),(4253,0,'http://www.attachment.com/data5','text miscelleneous data4 del hacker 2'),(4254,0,'http://www.attachment.com/data6','text miscelleneous data1 del hacker 3'),(4255,0,'http://www.attachment.com/data6','text miscelleneous data2 del hacker 3'),(4256,0,'http://www.attachment.com/data6','text miscelleneous data3 del hacker 3'),(4257,0,'http://www.attachment.com/data6','text miscelleneous'),(4258,0,'http://www.attachment.com/data6','text miscelleneous data1 del hacker 5'),(4259,0,'http://www.attachment.com/data6','text miscelleneous data2 del hacker 5'),(4260,0,'http://www.attachment.com/data6','text miscelleneous data1 del hacker 6'),(4261,0,'http://www.attachment.com/data6','text miscelleneous data2 del hacker 6'),(4262,0,'http://www.attachment.com/data6','text miscelleneous data3 del hacker 6'),(4263,0,'http://www.attachment.com/data6','text miscelleneous data4 del hacker 6'),(4264,0,'http://www.attachment.com/data6','text miscelleneous'),(4265,0,'http://www.attachment.com/data6','text miscelleneous'),(4266,0,'http://www.attachment.com/data6','text miscelleneous data1 del hacker 7'),(4267,0,'http://www.attachment.com/data6','text miscelleneous data2 del hacker 7'),(4268,0,'http://www.attachment.com/data6','text miscelleneous'),(4269,0,'http://www.attachment.com/data6','text miscelleneous');
/*!40000 ALTER TABLE `miscellaneous_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personal_data`
--

DROP TABLE IF EXISTS `personal_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personal_data` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `github_profile` varchar(255) DEFAULT NULL,
  `linked_in_profile` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `statement` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal_data`
--

LOCK TABLES `personal_data` WRITE;
/*!40000 ALTER TABLE `personal_data` DISABLE KEYS */;
INSERT INTO `personal_data` VALUES (4283,0,'Hacker1 Alvarez','https://github.com/personal1','https://www.linkedin.com/personal1','631047853','statement personal data 1 del hacker 1'),(4284,0,'Hacker1 Alvarez','https://github.com/personal1','https://www.linkedin.com/personal1','631047853','statement personal data 2 del hacker 1'),(4285,0,'Hacker1 Alvarez','https://github.com/personal1','https://www.linkedin.com/personal1','631047853','statement personal data 2 del hacker 1'),(4286,0,'Hacker2 Munoz','https://github.com/personal3','https://www.linkedin.com/personal3','632587412','statement personal data 1 del hacker 2'),(4287,0,'Hacker2 Munoz','https://github.com/personal3','https://www.linkedin.com/personal3','632587412','statement personal data 2 del hacker 2'),(4288,0,'Hacker3 Esteban','https://github.com/personal4','https://www.linkedin.com/personal4','632587412','statement personal data 1 del hacker 3'),(4289,0,'Hacker3 Esteban','https://github.com/personal4','https://www.linkedin.com/personal4','632587412','statement personal data 1 del hacker 3'),(4290,0,'Hacker3 Esteban','https://github.com/personal4','https://www.linkedin.com/personal4','632587412','statement personal data 1 del hacker 3'),(4291,0,'Hacker3 Esteban','https://github.com/personal4','https://www.linkedin.com/personal4','632587412','statement personal data 1 del hacker 3'),(4292,0,'Hacker4 Rodriguez','https://github.com/personal5','https://www.linkedin.com/personal5','632587412','statement personal data 1 del hacker 4'),(4293,0,'Hacker4 Rodriguez','https://github.com/personal5','https://www.linkedin.com/personal5','632587412','statement personal data 5'),(4294,0,'Hacker4 Rodriguez','https://github.com/personal5','https://www.linkedin.com/personal5','632587412','statement personal data 5'),(4295,0,'Hacker5 Lopez','https://github.com/personal5','https://www.linkedin.com/personal5','632587412','statement personal data 5'),(4296,0,'Hacker5 Lopez','https://github.com/personal5','https://www.linkedin.com/personal5','632587412','statement personal data 5'),(4297,0,'Hacker6 Barrera','https://github.com/personal5','https://www.linkedin.com/personal5','632587412','statement personal data 5'),(4298,0,'Hacker6 Barrera','https://github.com/personal5','https://www.linkedin.com/personal5','632587412','statement personal data 5'),(4299,0,'Hacker6 Barrera','https://github.com/personal5','https://www.linkedin.com/personal5','632587412','statement personal data'),(4300,0,'Hacker7 Navarro','https://github.com/personal5','https://www.linkedin.com/personal5','632587412','statement personal data 5'),(4301,0,'Hacker7 Navarro','https://github.com/personal5','https://www.linkedin.com/personal5','632587412','statement personal data 5'),(4302,0,'Hacker7 Navarro','https://github.com/personal5','https://www.linkedin.com/personal5','632587412','statement personal data'),(4303,0,'Hacker7 Navarro','https://github.com/personal5','https://www.linkedin.com/personal5','632587412','statement personal data'),(4304,0,'Hacker8 Moreno','https://github.com/personal5','https://www.linkedin.com/personal5','632587412','statement personal data 5');
/*!40000 ALTER TABLE `personal_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position`
--

DROP TABLE IF EXISTS `position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `deadline` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_cancelled` bit(1) NOT NULL,
  `is_final_mode` bit(1) NOT NULL,
  `profile` varchar(255) DEFAULT NULL,
  `salary` double NOT NULL,
  `skills` varchar(255) DEFAULT NULL,
  `technologies` varchar(255) DEFAULT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `company` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_15390c4j2aeju6ha0iwvi6mc5` (`ticker`),
  KEY `UK_7mmmvedajgdrjcq4940r6leib` (`salary`),
  KEY `UK_sbvjiwr8lnatlindwii8ijw3h` (`company`,`is_final_mode`,`is_cancelled`),
  KEY `UK_hdrydnvokbdh3vfme3as797ig` (`is_final_mode`,`is_cancelled`),
  KEY `UK_te8wiw9qh3ahbbh1tw7ypds42` (`company`,`is_final_mode`),
  KEY `UK_p1dvtfa0heqwte443u9lms0kh` (`is_final_mode`,`is_cancelled`,`title`,`description`,`profile`,`skills`,`technologies`,`company`),
  KEY `UK_l01961ptql97qten0obe0f5qs` (`is_final_mode`,`is_cancelled`,`ticker`,`title`,`description`,`profile`,`skills`,`technologies`,`deadline`,`salary`),
  CONSTRAINT `FK_7qlfn4nye234rrm4w83nms1g8` FOREIGN KEY (`company`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` VALUES (4305,0,'2019-12-05','Esta es position1 del company1','\0','','Economy',1586.23,'Number','Android','ONEC-4562','Position 1 - Company 1',4214),(4306,0,'2019-12-05','Esta es position2 del company1','\0','','Math',3586.23,'Number','R','ONEC-4752','Position 2 - Company 1',4214),(4307,0,'2019-12-05','Esta es position3 del company1','\0','\0','Math',3586.23,'Number','R','ONEC-4772','Position 3 - Company 1',4214),(4308,0,'2019-12-05','Esta es position4 del company1','','','Math',3586.23,'Number','R','ONEC-4122','Position 4 - Company 1',4214),(4309,0,'2019-11-05','Esta es position5 del company2','\0','\0','Computing',2686.23,'Pyhton','Eclipse','TWOC-4556','Position 5 - Company 2',4215),(4310,0,'2019-11-05','Esta es position6 del company2','\0','','Junior',2686.23,'Pyhton','Eclipse','TWOC-4146','Position 6 - Company 2',4215),(4311,0,'2019-11-05','Esta es position7 del company2','\0','','Senior',2686.23,'Pyhton','Eclipse','TWOC-4786','Position 7 - Company 2',4215),(4312,0,'2019-11-08','Esta es position8 del company3','\0','','App',2886.23,'Android','Java','FOUR-2556','Position 8 - Company 3',4216),(4313,0,'2019-11-08','Esta es position9 del company3','\0','','App',2886.23,'Android','Java','THRE-2556','Position 9 - Company 3',4216);
/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position_data`
--

DROP TABLE IF EXISTS `position_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position_data` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position_data`
--

LOCK TABLES `position_data` WRITE;
/*!40000 ALTER TABLE `position_data` DISABLE KEYS */;
INSERT INTO `position_data` VALUES (4314,0,'description','2014-10-20','2014-08-20','Position data 1 del hacker 1'),(4315,0,'description','2016-01-03','2015-09-02','Position data 2 del hacker 1'),(4316,0,'description',NULL,'2018-12-22','Position data 1 del hacker 2'),(4317,0,'description',NULL,'2018-12-22','Position data 2 del hacker 2'),(4318,0,'description','2018-01-03','2017-08-31','Position data 1 del hacker 3'),(4319,0,'description','2018-01-03','2017-08-31','Position data 2 del hacker 3'),(4320,0,'description','2018-01-03','2017-08-31','Position data 3 del hacker 3'),(4321,0,'description','2018-01-03','2017-08-31','Position'),(4322,0,'description','2013-01-13','2013-01-12','Position data 1 del hacker 4'),(4323,0,'description',NULL,'2018-08-20','Position data 2 del hacker 4'),(4324,0,'description',NULL,'2018-08-20','Position data 3 del hacker 4'),(4325,0,'description',NULL,'2016-08-20','Position data 1 del hacker 6'),(4326,0,'description',NULL,'2016-08-20','Position data 2 del hacker 6'),(4327,0,'description',NULL,'2016-08-20','Position data'),(4328,0,'description',NULL,'2018-08-20','Position data 1 del hacker 7'),(4329,0,'description',NULL,'2018-08-20','Position data 2 del hacker 7'),(4330,0,'description',NULL,'2018-08-20','Position data'),(4331,0,'description',NULL,'2018-08-20','Position data'),(4332,0,'description',NULL,'2016-08-20','Position data 1 del hacker 8');
/*!40000 ALTER TABLE `position_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position_problems`
--

DROP TABLE IF EXISTS `position_problems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position_problems` (
  `position` int(11) NOT NULL,
  `problems` int(11) NOT NULL,
  KEY `FK_7pe330ganri24wsftsajlm4l9` (`problems`),
  KEY `FK_iji6l3ytrjgytbgo6oi061elj` (`position`),
  CONSTRAINT `FK_iji6l3ytrjgytbgo6oi061elj` FOREIGN KEY (`position`) REFERENCES `position` (`id`),
  CONSTRAINT `FK_7pe330ganri24wsftsajlm4l9` FOREIGN KEY (`problems`) REFERENCES `problem` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position_problems`
--

LOCK TABLES `position_problems` WRITE;
/*!40000 ALTER TABLE `position_problems` DISABLE KEYS */;
INSERT INTO `position_problems` VALUES (4305,4333),(4305,4334),(4306,4333),(4306,4335),(4310,4337),(4310,4339),(4310,4341),(4311,4338),(4311,4339),(4311,4340),(4312,4344),(4312,4345),(4313,4345);
/*!40000 ALTER TABLE `position_problems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `problem`
--

DROP TABLE IF EXISTS `problem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `problem` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `attachments` varchar(255) DEFAULT NULL,
  `hint` varchar(255) DEFAULT NULL,
  `is_final_mode` bit(1) NOT NULL,
  `statement` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `company` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_is3c456yjdg242skxcgn8exgb` (`company`,`is_final_mode`),
  CONSTRAINT `FK_1dla8eoii1nn6emoo4yv86pgb` FOREIGN KEY (`company`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `problem`
--

LOCK TABLES `problem` WRITE;
/*!40000 ALTER TABLE `problem` DISABLE KEYS */;
INSERT INTO `problem` VALUES (4333,0,'','Propongo que el hacker esté cualificado','','Statement','Problem 1 - Company 1',4214),(4334,0,'','Que el hacker viva en Sevilla','','Statement','Problem 2 - Company 1',4214),(4335,0,'','Que el hacker viva en Sevilla','','Statement','Problem 3 - Company 1',4214),(4336,0,'','Que el hacker viva en Sevilla','\0','Statement','Problem 4 - Company 1',4214),(4337,0,'','Necesito que trabaje los fines de semana','','Statement','Problem 5 - Company 2',4215),(4338,0,'','Necesito que trabaje los fines de semana','','Statement','Problem 6 - Company 2',4215),(4339,0,'','Necesito que trabaje los fines de semana','','Statement','Problem 7 - Company 2',4215),(4340,0,'','Necesito que trabaje los fines de semana','','Statement','Problem 8 - Company 2',4215),(4341,0,'','Necesito que trabaje los fines de semana','','Statement','Problem 9 - Company 2',4215),(4342,0,'','Necesito que trabaje los fines de semana','\0','Statement','Problem 10 - Company 3',4216),(4343,0,'','Necesito que trabaje los fines de semana','','Statement','Problem 11 - Company 3',4216),(4344,0,'','Necesito que trabaje los fines de semana','','Statement','Problem 12 - Company 3',4216),(4345,0,'','Necesito que trabaje los fines de semana','','Statement','Problem 13 - Company 3',4216),(4346,0,'','Necesito que trabaje los fines de semana','','Statement','Problem 14 - Company 1',4214),(4347,0,'','Necesito que trabaje los fines de semana','','Statement','Problem 15 - Company 2',4215),(4348,0,'','Necesito que trabaje los fines de semana','','Statement','Problem 16 - Company 3',4216),(4349,0,'','Necesito que trabaje los fines de semana','\0','Statement','Problem 17 - Company 1',4214),(4350,0,'','Necesito que trabaje los fines de semana','\0','Statement','Problem 18 - Company 2',4215);
/*!40000 ALTER TABLE `problem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `social_profile`
--

DROP TABLE IF EXISTS `social_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `social_profile` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `link_profile` varchar(255) DEFAULT NULL,
  `nick` varchar(255) DEFAULT NULL,
  `social_network` varchar(255) DEFAULT NULL,
  `actor` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_nedqor7tomp44srq0vbui1h6b` (`link_profile`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `social_profile`
--

LOCK TABLES `social_profile` WRITE;
/*!40000 ALTER TABLE `social_profile` DISABLE KEYS */;
INSERT INTO `social_profile` VALUES (4270,0,'http://www.twitter1.com','nick1','Twitter',4201),(4271,0,'http://www.instagram1.com','nick2','Instagram',4231),(4272,0,'http://www.facebook1.com','nick3','Facebook',4231),(4273,0,'http://www.facebook2.com','nick4','Facebook',4232),(4274,0,'http://www.facebook3.com','nick5','Facebook',4232),(4275,0,'https://www.flickr1.com','nick6','Flickr',4233),(4276,0,'http://www.tinder1.com','nick7','Tinder',4214),(4277,0,'https://www.tuenti1.es','nick8','Tuenti',4214),(4278,0,'https://www.messenger1.es','nick9','Messenger',4214),(4279,0,'https://www.twitter2.com','nick10','Twitter',4215),(4280,0,'http://www.tinder2.com','nick11','Tinder',4215),(4281,0,'https://www.tuenti2.es','nick12','Tuenti',4216),(4282,0,'https://www.messenger2.es','nick13','Messenger',4216);
/*!40000 ALTER TABLE `social_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_tag`
--

DROP TABLE IF EXISTS `system_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system_tag` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `text` varchar(255) DEFAULT NULL,
  `actor` int(11) NOT NULL,
  `message` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_rp2knd2grlj4ao3ek3p8a9pmh` (`actor`,`message`,`text`),
  KEY `UK_iru8dbu07gaqhsc8c8xvjijsq` (`message`,`text`),
  CONSTRAINT `FK_k3jdfke3ocmep23m2gcc5y7kd` FOREIGN KEY (`message`) REFERENCES `message` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_tag`
--

LOCK TABLES `system_tag` WRITE;
/*!40000 ALTER TABLE `system_tag` DISABLE KEYS */;
INSERT INTO `system_tag` VALUES (4382,0,'HARDDELETED',4232,4240),(4383,0,'DELETED',4232,4241);
/*!40000 ALTER TABLE `system_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `is_banned` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_castjbvpeeus0r8lbpehiu0e4` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (4187,0,'\0','e00cf25ad42683b3df678c61f42c6bda','admin1'),(4188,0,'\0','54b53072540eeeb8f8e9343e71f28176','system'),(4189,0,'\0','df655f976f7c9d3263815bd981225cd9','company1'),(4190,0,'\0','d196a28097115067fefd73d25b0c0be8','company2'),(4191,0,'\0','e828ae3339b8d80b3902c1564578804e','company3'),(4192,0,'\0','2ba2a8ac968a7a2b0a7baa7f2fef18d2','hacker1'),(4193,0,'\0','91af68b69a50a98dbc0800942540342c','hacker2'),(4194,0,'\0','c6ae6edca1ad45f42e619ec91a32b636','hacker3'),(4195,0,'\0','a0ba178a7585a3b3575613351aa76ef7','hacker4'),(4196,0,'\0','c788cf2e81a407a2d33ee178ed79de3e','hacker5'),(4197,0,'\0','b8f9087a445d02d19b5154dbadbc7efd','hacker6'),(4198,0,'\0','dc072385108964493854966465072c77','hacker7'),(4199,0,'\0','917a85f5524799a575bc06e45f7f5e1b','hacker8'),(4200,0,'\0','8d9faf0956e7eb5f9dc7583a803ec4d2','hacker9');
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account_authorities`
--

DROP TABLE IF EXISTS `user_account_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account_authorities` (
  `user_account` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_pao8cwh93fpccb0bx6ilq6gsl` (`user_account`),
  CONSTRAINT `FK_pao8cwh93fpccb0bx6ilq6gsl` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account_authorities`
--

LOCK TABLES `user_account_authorities` WRITE;
/*!40000 ALTER TABLE `user_account_authorities` DISABLE KEYS */;
INSERT INTO `user_account_authorities` VALUES (4187,'ADMIN'),(4188,'ADMIN'),(4189,'COMPANY'),(4190,'COMPANY'),(4191,'COMPANY'),(4192,'HACKER'),(4193,'HACKER'),(4194,'HACKER'),(4195,'HACKER'),(4196,'HACKER'),(4197,'HACKER'),(4198,'HACKER'),(4199,'HACKER'),(4200,'HACKER');
/*!40000 ALTER TABLE `user_account_authorities` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-12 16:49:09

commit;