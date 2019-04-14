
create  database if not exists `drink_safe`;
use `drink_safe`;

CREATE TABLE `user` (
  `username` varchar(100) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `height` int(4) DEFAULT NULL,
  `weight` int(5) DEFAULT NULL,
  `gender` int(1) DEFAULT NULL,
  `guest_status` int(1) DEFAULT NULL,
  `bac` float(10) DEFAULT NULL,
  PRIMARY KEY (`username`));
 
 
CREATE TABLE `friend`(
   `sentfrom` varchar (100) NOT NULL,
   `sentto` varchar (100) NOT NULL,
   CONSTRAINT `sentfrom` FOREIGN KEY (`sentfrom`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
   CONSTRAINT `sentto` FOREIGN KEY (`sentto`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE);
 
CREATE TABLE `time` (
  `tid` int(10) NOT NULL,
  `time_start` bigint DEFAULT NULL,
  `time_finish` bigint DEFAULT NULL,
  PRIMARY KEY (`tid`));
  
CREATE TABLE `Drink` (
  `did` int(10) NOT NULL,
  `drinkid` varchar(100) NOT NULL,
  `alcpercent` int(20) DEFAULT NULL,
  `volume` int(20) DEFAULT NULL,
  `fkuser` varchar(100) NOT NULL,
  PRIMARY KEY (`did`),
  CONSTRAINT `fkuser` FOREIGN KEY (`fkuser`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE);
  
  
CREATE TABLE `drink_time` (
  `tid` int(20) NOT NULL,
  `dtusername` varchar(100) NOT NULL,
  CONSTRAINT `tid` FOREIGN KEY (`tid`) REFERENCES `time` (`tid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `dtusername` FOREIGN KEY (`dtusername`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE);
  

