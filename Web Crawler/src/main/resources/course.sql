show databases;
use eLearningDB;
show tables;
describe course;
describe user;

CREATE TABLE course2 (
    id bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    course_name varchar(255) NOT NULL,
    provider varchar(255) NOT NULL,
    price varchar(255),
	rating varchar(255),
    course_description longtext,
    image_url varchar(255),
    video_url varchar(255),
	coursetalk_url varchar(255),
    course_redirect_url varchar(255),
    course_actual_url varchar(255)
);

select * from course;
select * from course where coursetalk_url='N/A';
select * from course where course_redirect_url='N/A';
select * from course where course_actual_url='N/A';
select * from course where image_url='N/A' and video_url='N/A';
select * from course where price NOT LIKE 'FREE%';
select * from course where course_name='Business Communication';
select count(*) from course;
select * from user;

