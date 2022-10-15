-- 创建数据库
CREATE DATABASE IF NOT EXISTS `labelclocksystem`;

USE `labelclocksystem`;

-- 用户列表
CREATE TABLE  IF NOT EXISTS `user`
(
    `Id`         int primary key auto_increment,
    `Name`       varchar(10) NOT NULL,
    `StuNum`     char(12) UNIQUE,
    `StuCardNum` char(10) UNIQUE,
    `Grade`      char(4)     NOT NULL,
    `Major`      varchar(20)
);

-- 用户状态表
CREATE TABLE IF NOT EXISTS `userstatus`
(
    `UserId`    int,
    `IsAlive`   boolean NOT NULL,
    `StartTime` datetime,
    CONSTRAINT `UserStatusUserId` FOREIGN KEY (`UserId`) REFERENCES `user` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
);

-- 打卡记录表
CREATE TABLE IF NOT EXISTS `record`
(
    `UserId`    int,
    `StartTime` datetime,
    `EndTime`   datetime,
    `Time`      double(4, 2) ,
    CONSTRAINT `RecordUserId` FOREIGN KEY(`UserId`) REFERENCES `user`(`Id`) ON DELETE CASCADE ON UPDATE CASCADE
);