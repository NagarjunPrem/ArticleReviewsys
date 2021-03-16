clone this project from : https://github.com/NagarjunPrem/ArticleReviewsys


To start the project-

Use Maven: mvn clean package

creates target file : at /target ArticleRevSys-1.0-SNAPSHOT.war

Listed dependencies: primefaces 5.0 javaee-web-api 7.0 BootStrap4.0 JSF2.3

GET Home page: 
ArticleRevSys/faces/login.xhtml 
ArticleRevSys/faces/loginSuccess.xhtml
To list reviews: ArticleRevSys/faces/reviews.xhtml
To list ratings of the Review: ArticleRevSys/faces/ratings.xhtml
To add Rating of the Review: ArticleRevSys/faces/addRating.xhtml
To add Review: ArticleRevSys/faces/addreview.xhtml

Base url: http://localhost:8080


Test Credentials;
username:arjun password:arjun
username:ajay password:ajay 

For Simplicity purpose kindly start the reviewID in 'AddReview' page from 10.

Database statements:

CREATE DATABASE articleReviewSys;
USE articleReviewSys;

create table users(
   uid integer,
    uname varchar(15),
    upassword varchar(15),
    primary key (uid)
);

show tables;

select * from users;

create table reviews(
   uid integer,
   rid integer,
   rtitle varchar(100),
   pub_title varchar(100),
   pub_url varchar(100),
   summary varchar(999),
   post_negt varchar(999),
   maj_points varchar(999),
   min_points varchar(999),
   recommendation varchar(999),
   rev_name varchar(15),
   primary key (rid),
   foreign key (uid) references users (uid)
);

select * from reviews;

select * from ratings;

select * from ratings where rid=1;

create table ratings(
   uid integer,
   rid integer,
   rating integer,
   comment varchar(500),
   foreign key (uid) references users (uid),
   foreign key (rid) references reviews (rid)
);

select * from ratings;

INSERT INTO ratings VALUES(1,1,10,'Good Review');
INSERT INTO ratings VALUES(1,2,0,'Bad Review');
INSERT INTO ratings VALUES(2,3,10,'Good job');
INSERT INTO ratings VALUES(2,7,8,'Good job');
INSERT INTO ratings VALUES(2,7,4,'Good job');





