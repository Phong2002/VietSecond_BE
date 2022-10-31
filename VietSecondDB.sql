DROP DATABASE IF EXISTS VietSecond;
CREATE DATABASE IF NOT EXISTS VietSecond;

USE VietSecond;

CREATE TABLE `User`(
userId INT PRIMARY KEY AUTO_INCREMENT,
firstName VARCHAR(50) NOT NULL,
lastName VARCHAR(50) NOT NULL,
gender ENUM('MALE','FEMALE','UNKNOW'),
address VARCHAR(255) NOT NULL,
dateOfBirth DATE NOT NULL, 
phoneNumber VARCHAR(15) NOT NULL,
accountBalance LONGBLOB,
email  VARCHAR(100) NOT NULL,
userName VARCHAR(50) NOT NULL,
`password` VARCHAR(255) NOT NULL,
role ENUM('USER','ADMIN'),
isActive bit
);

INSERT INTO `User` 	(firstName  ,lastName  ,gender  ,address  ,dateOfBirth  ,phoneNumber  ,accountBalance   ,email   ,userName   ,`password`  ,`role`,isActive)
VALUES				('Ngô Bá'	,'Khá','MALE','Bắc Ninh',"1993-11-27", "0369425986","0","khabanhdzai@gmail.com",'khabanhpaylak','123456','ADMIN',1),
					('Nguyễn Xuân','Đường ','MALE','Thái Bình',"1971-06-15","0658351258","0","duongnhue71@gmail.com",'duongnhuetb','123456','EMPLOYEE',1),
					('Lê Văn'	,'Phú','MALE','Yên Bái',"1980-8-13","0258635415","0","chicaphule@gmail.com",'chicaphu','123456','EMPLOYEE',1),
					('Nguyễn Văn','Dũng','MALE','Hà Nội',"1992-06-15","0369425586","0","dungkhongtrochd@gmail.com",'dungtrochadong','123456','ADMIN',1),
					('Nguyễn Thành','Long','MALE','Hải Phòng',"1988-8-10","0369845686","0","tienbibip@gmail.com",'tienkhongbip','123456','SUPER_ADMIN',1),
					('Nguyễn Văn','Hợi','MALE','Hải Phòng',"1992-5-30","0369425986","0","khabanhdzai@gmail.com",'khanhbautroi','123456','EMPLOYEE',1),
					('Nguyễn Thùy','Chi','FEMALE','Hà Nội',"1993-06-14","0699856478","0","chipunek@gmail.com",'chipuooo','123456','EMPLOYEE',1),
					('Mai Phương','Thúy','FEMALE','Đà Nẵng',"1995-8-30","0586264586","0","maithuy@gmail.com",'maithuy12','123456','EMPLOYEE',1),
					('Phạm Văn','Thoại','UNKNOWN','Hải Phòng',"1996-06-16","0685136586","0","thoainorin123@gmail.com",'meophonorin','123456','EMPLOYEE',1),
					('Trần Đức','Bo','UNKNOWN','Đà Nẵng',"1999-10-13","0968596358","0","conmeongungok@gmail.com",'conmeongungok','123456','EMPLOYEE',0);