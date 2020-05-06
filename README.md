# E-Learning Platform

## Introduction
The amount of educational resources spread over the Internet is vast and diverse. A lot of websites such as Coursera, Udacity, EdX are very popular. There are millions of courses out there that sometimes may confuse users who are seeking suitable courses for themselves. In addition, some of the online learning resources are lack of user experience and interaction in engagement with other users. Communication and discussion is also a key in online study, so the learner can absorb the knowledge better by communicating with other learners just like what we did in an actual in-person class. Our application is combining the virtual learning benefit with traditional on-campus learning experience together with on purpose to build a web application target to the user who is seeking a set of relevant skills to achieve their study or career goal. The application will be using data from some existing online course web resources to help users to select the course they need utilizing the page ranking algorithm. The main feature of our application is providing an integrated learning platform for users to search, set up the study plan, and communicate with other users about the current course.

## How to run
1. 
	cd tfidf <br/>
	make install3 <br/>
	make run3

2. 
	cd eLearning <br/>
	mvn package <br/>
	mvn exec:java

3. 
	cd iStudy-Client <br/>
	npm install <br/>
	npm run socket <br/>
	npm run start