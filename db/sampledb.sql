DROP DATABASE sampledb;

CREATE DATABASE sampledb;

USE sampledb;

CREATE TABLE `user` (
  `username` varchar(16) NOT NULL,
  `usertype` varchar(255) DEFAULT NULL,
  `password` varchar(32) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
);

INSERT INTO `user` VALUES ('admin','ADMIN','admin',1),('user','USER','user',2),('corriere','CORRIERE','corriere',3);

CREATE TABLE Magazzino(
id integer primary key auto_increment,
id_oggetto integer,
capienza integer,
otp varchar(5),
cancellato tinyint(1),
UNIQUE (id, id_oggetto)
);

CREATE TABLE Codice(
id integer primary key auto_increment,
otp varchar(5),
cancellato tinyint(1),
UNIQUE (otp)
);

CREATE TABLE Oggetto(
id integer primary key auto_increment,
nome varchar(20) not null,
dimensione integer,
cancellato tinyint(1)
);
