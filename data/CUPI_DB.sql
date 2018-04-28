-- ****************** SqlDBM: MySQL ******************;
-- ***************************************************;

DROP TABLE `Vacation`;


DROP TABLE `Category`;



-- ************************************** `Category`

CREATE TABLE `Category`
(
 `CategoryID`     INT NOT NULL ,
 `CategoryParent` INT NOT NULL ,
 `CategoryName`   VARCHAR(45) NOT NULL ,

PRIMARY KEY (`CategoryID`)
);





-- ************************************** `Vacation`

CREATE TABLE `Vacation`
(
 `VacationID` INT NOT NULL ,
 `Name`       VARCHAR(255) NOT NULL ,
 `Address`    TEXT NOT NULL ,
 `Coordinate` VARCHAR(45) NOT NULL ,
 `Price`      DOUBLE NOT NULL ,
 `Note`       LONGTEXT NOT NULL ,
 `OpenHour`   TIME NOT NULL ,
 `CloseHour`  TIME NOT NULL ,
 `Photo`      VARCHAR(255) NOT NULL ,
 `CategoryID` INT NOT NULL ,

PRIMARY KEY (`VacationID`),
KEY `fkIdx_32` (`CategoryID`),
CONSTRAINT `FK_32` FOREIGN KEY `fkIdx_32` (`CategoryID`) REFERENCES `Category` (`CategoryID`)
);




