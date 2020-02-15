# CourseTalk Web Crawler

## How to Use
- configure the config.xml file located in the project root folder.
- if the file crawlerData_courseTalkIndividualCoursePageUrl.txt located in ./src/resources/crawlerData_courseTalkIndividualCoursePageUrl.txt exists, then just run CourseTalkCourseInfoCrawlerDriver.java.
- if the file crawlerData_courseTalkIndividualCoursePageUrl.txt located in ./src/resources/crawlerData_courseTalkIndividualCoursePageUrl.txt does not exist, run CourseTalkCoursePageCrawlerDriver first, then run CourseTalkCourseInfoCrawlerDriver.java.

## bash command
- cd [project root folder]
- mvn package
- cd target
- java -jar [executable jar file]