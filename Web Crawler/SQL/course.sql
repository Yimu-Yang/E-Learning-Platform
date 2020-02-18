show databases;
use eLearningDB;
show tables;
describe course;

select * from course;
select * from course where coursetalk_url='N/A';
select * from course where course_redirect_url='N/A';
select * from course where course_actual_url='N/A';
select * from course where image_url='N/A' and video_url='N/A';
select * from course where price NOT LIKE 'FREE%';
select * from course where course_name='Business Communication ';
select count(*) from course;
