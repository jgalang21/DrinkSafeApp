
create  database if not exists `drink_safe`;
use `drink_safe`;

CREATE TABLE `user` (
  `username` varchar(100) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  `height` int(4) DEFAULT NULL,
  `weight` int(5) DEFAULT NULL,
  `gender` int(1) DEFAULT NULL,
  `guest_status` int(1) DEFAULT NULL,
  PRIMARY KEY (`username`));
 
CREATE TABLE `friend`(
   `sentfrom` varchar (100) NOT NULL,
   `sentto` varchar (100) NOT NULL,
   CONSTRAINT `sentfrom` FOREIGN KEY (`sentfrom`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
   CONSTRAINT `sentto` FOREIGN KEY (`sentto`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE);
 
 
CREATE TABLE `time` (
  `tid` int(10) NOT NULL,
  `time_start` int(20) DEFAULT NULL,
  `time_finish` int(20) DEFAULT NULL,
  PRIMARY KEY (`tid`));
  
CREATE TABLE `Drink` (
  `drinkId` varchar(100) NOT NULL,
  `alcPercent` int(20) DEFAULT NULL,
  `volume` int(20) DEFAULT NULL,
  `fkuser` varchar(100) NOT NULL,
  PRIMARY KEY (`drinkId`),
  CONSTRAINT `fkuser` FOREIGN KEY (`fkuser`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE `drink_time` (
  `tid` int(20) NOT NULL,
  `dtusername` varchar(100) NOT NULL,
  CONSTRAINT `tid` FOREIGN KEY (`tid`) REFERENCES `time` (`tid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `dtusername` FOREIGN KEY (`dtusername`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE);
  
  
