/*
SQLyog Community v12.4.3 (64 bit)
MySQL - 5.7.14 : Database - league
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`league` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `league`;

/*Table structure for table `club` */

DROP TABLE IF EXISTS `club`;

CREATE TABLE `club` (
  `ClubID` int(11) NOT NULL,
  `Name` varchar(30) DEFAULT NULL,
  `Description` varchar(300) DEFAULT NULL,
  `Arena` varchar(30) DEFAULT NULL,
  `Image` varchar(100) DEFAULT NULL,
  `CountryID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ClubID`),
  KEY `CountryID` (`CountryID`),
  CONSTRAINT `club_ibfk_1` FOREIGN KEY (`CountryID`) REFERENCES `country` (`CountryID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `club` */

insert  into `club`(`ClubID`,`Name`,`Description`,`Arena`,`Image`,`CountryID`) values 
(1,'Crvena Zvezda','Crvena Zvezda je najbolji klub na svetu','Pionir','Zvezda.png',1),
(2,'Partizan','klasdnslakdn','alskdnalksdn','Partizan.png',1),
(3,'Cedevita','oasidjlkj`l','lkjlkj','Cedevita.png',1),
(4,'Mega','lkjhjkh','kjhkljhkj','Mega.png',1);

/*Table structure for table `country` */

DROP TABLE IF EXISTS `country`;

CREATE TABLE `country` (
  `CountryID` int(11) NOT NULL,
  `Name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`CountryID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `country` */

insert  into `country`(`CountryID`,`Name`) values 
(1,'Srbija');

/*Table structure for table `game` */

DROP TABLE IF EXISTS `game`;

CREATE TABLE `game` (
  `GameID` int(11) NOT NULL,
  `HomeTeamPoints` int(11) DEFAULT NULL,
  `AwayTeamPoints` int(11) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `HomeTeamID` int(11) DEFAULT NULL,
  `AwayTeamID` int(11) DEFAULT NULL,
  `RoundID` int(11) DEFAULT NULL,
  PRIMARY KEY (`GameID`),
  KEY `HomeTeamID` (`HomeTeamID`),
  KEY `AwayTeamID` (`AwayTeamID`),
  KEY `RoundID` (`RoundID`),
  CONSTRAINT `game_ibfk_1` FOREIGN KEY (`HomeTeamID`) REFERENCES `club` (`ClubID`),
  CONSTRAINT `game_ibfk_2` FOREIGN KEY (`AwayTeamID`) REFERENCES `club` (`ClubID`),
  CONSTRAINT `game_ibfk_3` FOREIGN KEY (`RoundID`) REFERENCES `round` (`RoundID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `game` */

insert  into `game`(`GameID`,`HomeTeamPoints`,`AwayTeamPoints`,`Date`,`HomeTeamID`,`AwayTeamID`,`RoundID`) values 
(1,NULL,NULL,NULL,NULL,NULL,1),
(2,NULL,NULL,NULL,NULL,NULL,1),
(3,NULL,NULL,NULL,NULL,NULL,2),
(4,NULL,NULL,NULL,NULL,NULL,2),
(5,NULL,NULL,NULL,NULL,NULL,3),
(6,NULL,NULL,NULL,NULL,NULL,3),
(7,NULL,NULL,NULL,NULL,NULL,4),
(8,NULL,NULL,NULL,NULL,NULL,4),
(9,NULL,NULL,NULL,NULL,NULL,5),
(10,NULL,NULL,NULL,NULL,NULL,5),
(11,NULL,NULL,NULL,NULL,NULL,6),
(12,NULL,NULL,NULL,NULL,NULL,6);

/*Table structure for table `round` */

DROP TABLE IF EXISTS `round`;

CREATE TABLE `round` (
  `RoundID` int(11) NOT NULL,
  `Number` int(11) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `SeasonID` int(11) DEFAULT NULL,
  PRIMARY KEY (`RoundID`),
  KEY `SeasonID` (`SeasonID`),
  CONSTRAINT `round_ibfk_1` FOREIGN KEY (`SeasonID`) REFERENCES `season` (`SeasonID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `round` */

insert  into `round`(`RoundID`,`Number`,`Date`,`SeasonID`) values 
(1,1,NULL,1),
(2,2,NULL,1),
(3,3,NULL,1),
(4,1,NULL,2),
(5,2,NULL,2),
(6,3,NULL,2);

/*Table structure for table `season` */

DROP TABLE IF EXISTS `season`;

CREATE TABLE `season` (
  `SeasonID` int(11) NOT NULL,
  `NumberOfClubs` int(11) DEFAULT NULL,
  `RoundSystem` int(11) DEFAULT NULL,
  `StartDate` date DEFAULT NULL,
  `EndDate` date DEFAULT NULL,
  `LeagueName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`SeasonID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `season` */

insert  into `season`(`SeasonID`,`NumberOfClubs`,`RoundSystem`,`StartDate`,`EndDate`,`LeagueName`) values 
(1,4,1,'2017-09-09','2018-05-09','ABA liga'),
(2,4,1,'2017-09-09','2018-05-09','NBA league');

/*Table structure for table `season2club` */

DROP TABLE IF EXISTS `season2club`;

CREATE TABLE `season2club` (
  `SeasonID` int(11) NOT NULL,
  `ClubID` int(11) NOT NULL,
  PRIMARY KEY (`SeasonID`,`ClubID`),
  KEY `ClubID` (`ClubID`),
  CONSTRAINT `season2club_ibfk_3` FOREIGN KEY (`SeasonID`) REFERENCES `season` (`SeasonID`),
  CONSTRAINT `season2club_ibfk_4` FOREIGN KEY (`ClubID`) REFERENCES `club` (`ClubID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `season2club` */

insert  into `season2club`(`SeasonID`,`ClubID`) values 
(1,1),
(2,1),
(1,2),
(2,2),
(1,3),
(2,3),
(1,4),
(2,4);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
