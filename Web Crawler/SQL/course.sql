show databases;
use yimuyang;
show tables;
describe course3;
drop table course3;

CREATE TABLE course3 (
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

select * from course3;
select * from course3 where imageURL="N/A" and videoURL="N/A";
select * from course3 where courseActualURL="N/A";
select * from course3 where price NOT LIKE 'FREE%';
select * from course3 where provider="Coursera";
select * from course3 where provider="Udacity";
select * from course3 where provider="Udemy";
select * from course3 where provider="Treehouse";
select * from course3 where provider="edX";
select * from course3 where courseName="JavaScript Basics";
select * from course3 where courseTalkURL="https://www.coursetalk.com/providers/edx/courses/customer-protection-rule-15c3-3";

select count(*) from course3;


