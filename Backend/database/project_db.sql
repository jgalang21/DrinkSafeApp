create  database if not exists `drink_safe`;

CREATE TABLE `User` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20),
  `height` int(4) DEFAULT NULL,
  `weight` int(5) DEFAULT NULL,
  `gender` int(1) DEFAULT NULL,
  `guest_status` int(1) DEFAULT NULL,
  PRIMARY KEY (`username`));
  
  
CREATE TABLE `time` (
  `tid` int(10) NOT NULL,
  `time_start` int(20) DEFAULT NULL,
  `time_finish` int(20) DEFAULT NULL,
  PRIMARY KEY (`tid`));
  
CREATE TABLE `drink` (
  `did` int(10) NOT NULL,
  `alc_percent` int(20) DEFAULT NULL,
  `volume` int(20) DEFAULT NULL,
  PRIMARY KEY (`did`));
  
CREATE TABLE `consume` (
  `total_num` int(20) DEFAULT NULL,
  `did` int(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  CONSTRAINT `did` FOREIGN KEY (`did`) REFERENCES `drink` (`did`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `username` FOREIGN KEY (`username`) REFERENCES `User` (`username`) ON DELETE CASCADE ON UPDATE CASCADE);
  
CREATE TABLE `drink_time` (
  `tid` int(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  CONSTRAINT `tid` FOREIGN KEY (`tid`) REFERENCES `time` (`tid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `username` FOREIGN KEY (`username`) REFERENCES `User` (`username`) ON DELETE CASCADE ON UPDATE CASCADE);
  
  
