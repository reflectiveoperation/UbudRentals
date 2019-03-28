-- MySQL dump 10.13  Distrib 8.0.14, for macos10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: ubud_videostore
-- ------------------------------------------------------
-- Server version	8.0.14

--
-- Table structure for table customers
--

CREATE TABLE customers (
  c_id int(11) NOT NULL AUTO_INCREMENT,
  c_first_name varchar(255) NOT NULL,
  c_last_name varchar(255) NOT NULL,
  c_address varchar(255) DEFAULT NULL);

--
-- Dumping data for table customers
--


INSERT INTO customers (c_id, c_first_name, c_last_name, c_address) VALUES (1,'John','Smith','123 Main St');
INSERT INTO customers (c_id, c_first_name, c_last_name, c_address) VALUES (2,'Mario','Gomez','52 Tyre');
INSERT INTO customers (c_id, c_first_name, c_last_name, c_address) VALUES (3,'Roi','Falcon','100 Forest Key');
INSERT INTO customers (c_id, c_first_name, c_last_name, c_address) VALUES (4,'Alp','Fast','299 Alo Ave');
INSERT INTO customers (c_id, c_first_name, c_last_name, c_address) VALUES (5,'Molly','Craig','91 Hawaii Cir.');
INSERT INTO customers (c_id, c_first_name, c_last_name, c_address) VALUES (6,'Sabine','Morales','57 Warren Ave.');
INSERT INTO customers (c_id, c_first_name, c_last_name, c_address) VALUES (7,'Grace','Falls','1212 McTyre Ave.');
INSERT INTO customers (c_id, c_first_name, c_last_name, c_address) VALUES (8,'Cristian','Perez','162 York Mills Rd.');

--
-- Table structure for table movie_types
--

CREATE TABLE movie_types (
  m_t_id int(11) NOT NULL AUTO_INCREMENT,
  m_t_type int(11) DEFAULT NULL,
  m_t_cost decimal(5,2) DEFAULT NULL);

--
-- Dumping data for table movie_types
--


INSERT INTO movie_types (m_t_id, m_t_type, m_t_cost) VALUES (1,1,2.80);
INSERT INTO movie_types (m_t_id, m_t_type, m_t_cost) VALUES (2,2,3.50);
INSERT INTO movie_types (m_t_id, m_t_type, m_t_cost) VALUES (3,3,4.30);

--
-- Table structure for table movies
--

CREATE TABLE movies (
  m_id int(11) NOT NULL AUTO_INCREMENT,
  m_title varchar(255) DEFAULT NULL,
  m_main_actor varchar(255) DEFAULT NULL,
  m_year int(11) DEFAULT NULL,
  m_genre varchar(255) DEFAULT NULL,
  m_m_t_id int(11) DEFAULT NULL);

--
-- Dumping data for table movies
--

INSERT INTO movies (m_id, m_title, m_main_actor, m_year, m_genre, m_m_t_id) VALUES (1,'Inglorious Bastards','Robbie Williams',2012,'action',1);
INSERT INTO movies (m_id, m_title, m_main_actor, m_year, m_genre, m_m_t_id) VALUES (2,'Batman','John Lennon',2010,'action',2);
INSERT INTO movies (m_id, m_title, m_main_actor, m_year, m_genre, m_m_t_id) VALUES (3,'Pulp Fiction','Keanu Reeves',1994,'drama',3);
INSERT INTO movies (m_id, m_title, m_main_actor, m_year, m_genre, m_m_t_id) VALUES (4,'The Godfather','Nero Morales',1992,'drama',2);
INSERT INTO movies (m_id, m_title, m_main_actor, m_year, m_genre, m_m_t_id) VALUES (5,'Citizen Kane','Mario Rui',1934,'action',2);
INSERT INTO movies (m_id, m_title, m_main_actor, m_year, m_genre, m_m_t_id) VALUES (6,'The Wizard Of Oz','Alexandro Moore',1947,'adventure',1);
INSERT INTO movies (m_id, m_title, m_main_actor, m_year, m_genre, m_m_t_id) VALUES (7,'Battle Of The Worlds','Fulano Fulanito',2001,'documentary',2);
INSERT INTO movies (m_id, m_title, m_main_actor, m_year, m_genre, m_m_t_id) VALUES (8,'The Big Short','Frank Sinatra',2013,'drama',3);

--
-- Table structure for table rentals
--

CREATE TABLE rentals (
  r_id int(11) NOT NULL AUTO_INCREMENT,
  r_m_id int(11) DEFAULT NULL,
  r_c_id int(11) DEFAULT NULL,
  r_date_start date DEFAULT NULL,
  r_date_finish date DEFAULT NULL,
  r_movie_returned varchar(10) DEFAULT NULL);

--
-- Dumping data for table rentals
--

INSERT INTO rentals (r_id, r_m_id, r_c_id, r_date_start, r_date_finish, r_movie_returned) VALUES (1,5,7,'2018-01-01','2018-01-06','YES');
INSERT INTO rentals (r_id, r_m_id, r_c_id, r_date_start, r_date_finish, r_movie_returned) VALUES (2,1,5,'2018-01-14','2018-01-16','YES');
INSERT INTO rentals (r_id, r_m_id, r_c_id, r_date_start, r_date_finish, r_movie_returned) VALUES (3,2,4,'2018-01-21','2018-01-23','NO');
INSERT INTO rentals (r_id, r_m_id, r_c_id, r_date_start, r_date_finish, r_movie_returned) VALUES (4,3,3,'2018-01-24','2018-01-26','NO');
INSERT INTO rentals (r_id, r_m_id, r_c_id, r_date_start, r_date_finish, r_movie_returned) VALUES (5,4,1,'2018-01-28','2018-01-29','YES');
INSERT INTO rentals (r_id, r_m_id, r_c_id, r_date_start, r_date_finish, r_movie_returned) VALUES (6,6,2,'2018-01-30','2018-01-31',NULL);

-- Dump completed on 2019-03-12 15:32:21
