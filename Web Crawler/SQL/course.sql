show databases;
use yimuyang;
drop table course3;
show tables;

CREATE TABLE course (
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

describe course;
select * from course;
select * from course where imageURL="N/A" and videoURL="N/A";
select count(*) from course;

