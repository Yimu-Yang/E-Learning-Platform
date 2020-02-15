show databases;
use yimuyang;
drop table course2;
show tables;

CREATE TABLE course2 (
    courseName varchar(255) NOT NULL PRIMARY KEY,
    provider varchar(255),
    price varchar(255),
	rating varchar(255),
    courseDescription VARCHAR(10000),
    imageURL varchar(255),
    videoURL varchar(255),
	courseTalkURL varchar(255),
    courseRedirectURL varchar(255),
    courseActualURL varchar(255)
);

describe course2;
select * from course2;
select * from course2 where imageURL="N/A" and videoURL="N/A";
select count(*) from course2;

