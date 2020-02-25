show databases;
use eLearningDB;
show tables;
describe course;
describe user;

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

select * from course;
select * from course where coursetalk_url='N/A';
select * from course where course_redirect_url='N/A';
select * from course where course_actual_url='N/A';
select * from course where image_url='N/A' and video_url='N/A';
select * from course where price NOT LIKE 'FREE%';
select * from course where course_name='Business Communication';
select count(*) from course2;