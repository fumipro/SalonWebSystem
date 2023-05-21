CREATE TABLE contact
(
   id INT PRIMARY KEY AUTO_INCREMENT,
   name VARCHAR(255),
   email VARCHAR(255),
   message VARCHAR(1000)
);

CREATE TABLE reservation
(
   id INT PRIMARY KEY AUTO_INCREMENT,
   yyear VARCHAR(4),
   mmonth VARCHAR(2),
   dday VARCHAR(2),
   hhour VARCHAR(2),
   mminutes VARCHAR(2),
   name VARCHAR(255),
   gender INT,
   tel VARCHAR(255),
   message VARCHAR(1000)
);