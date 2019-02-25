create  database if not exists `drink_safe`;


CREATE TABLE `drink_time` (
  `tid` int(20) NOT NULL,
  `dtusername` varchar(20) NOT NULL,
  CONSTRAINT `tid` FOREIGN KEY (`tid`) REFERENCES `time` (`tid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `dtusername` FOREIGN KEY (`dtusername`) REFERENCES `User` (`username`) ON DELETE CASCADE ON UPDATE CASCADE);
  
  
