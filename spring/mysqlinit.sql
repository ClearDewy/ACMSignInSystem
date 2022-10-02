-- 创建数据库
CREATE DATABASE IF NOT EXISTS labelclocksystem;
USE labelclocksystem;

-- 用户列表
DROP TABLE IF EXISTS user;
CREATE TABLE user(
    Id int primary key auto_increment,
    Name varchar(10) NOT NULL,
    StuNum char(12) UNIQUE,
    StuCardNum char(10) UNIQUE,
    Grade char(4) NOT NULL,
    Major varchar(20)
);

-- 用户状态表
DROP TABLE IF EXISTS userstatus;
CREATE TABLE userstatus(
    UserId int,
    IsAlive boolean NOT NULL,
    StartTime datetime,
    CONSTRAINT UserStatusUserId FOREIGN KEY(UserId) REFERENCES user(Id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- 打卡记录表
DROP TABLE IF EXISTS record;
CREATE TABLE record(
    UserId int,
    StartTime datetime,
    EndTime datetime,
    Time double(4,2),
    CONSTRAINT RecordUserId FOREIGN KEY(UserId) REFERENCES user(Id) ON DELETE CASCADE ON UPDATE CASCADE
);


-- test
SELECT * FROM user;
SELECT Id FROM user WHERE "1234567891" IN (StuNum,Name,StuCardNum) ;

-- 外键级联删除更新
INSERT INTO Record(UserId,StartTime,EndTime,Time)
VALUES
    (1,now(),now(),(UNIX_TIMESTAMP()-UNIX_TIMESTAMP())/3600);

INSERT INTO User(Name,StuNum,StuCardNum,Grade,Major)
VALUES
    ("张三","202121091111","1111111111","2021","计算机"),
    ("李四","202121092222","2222222222","2021","计算机"),
    ("王五","202121093333","3333333333","2021","计算机");

-- 删除外键
ALTER TABLE UserStatus DROP FOREIGN KEY UserStatusUserId;
ALTER TABLE Record DROP FOREIGN KEY RecordUserId;

-- 新建外键,级联删除更新
ALTER TABLE UserStatus ADD CONSTRAINT UserStatusUserId FOREIGN KEY(UserId) REFERENCES User(Id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE Record ADD CONSTRAINT RecordUserId FOREIGN KEY(UserId) REFERENCES User(Id) ON DELETE CASCADE ON UPDATE CASCADE;

SELECT UserId,StuNum,SUM(Time) AS Time
FROM user,record
WHERE UserId=User.Id
GROUP BY Record.UserId  ORDER BY Time DESC ;

show variables like '%time_zone%';