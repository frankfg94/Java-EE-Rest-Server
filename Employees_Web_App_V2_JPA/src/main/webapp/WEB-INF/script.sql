/*Creation of the database*/
CREATE DATABASE IF NOT EXISTS JEEPRJ;

/*Creation of the table EMPLOYEES and USERS*/
USE JEEPRJ;
DROP TABLE IF EXISTS EMPLOYEES;
CREATE TABLE EMPLOYEES ( 
    ID int NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(25) NOT NULL,
    FIRSTNAME VARCHAR(25) NOT NULL, 
    TELHOME VARCHAR(10) NOT NULL, 
    TELMOB VARCHAR(10) NOT NULL, 
    TELPRO VARCHAR(10) NOT NULL, 
    ADRESS VARCHAR(150) NOT NULL, 
    POSTALCODE VARCHAR(5) NOT NULL, 
    CITY VARCHAR(25) NOT NULL, 
    EMAIL VARCHAR(25) NOT NULL, 
    CONSTRAINT primary_key_membre PRIMARY KEY (ID) 
);
DROP TABLE IF EXISTS Users;
CREATE TABLE Users( 
    ID int AUTO_INCREMENT PRIMARY KEY NOT NULL, 
    LOGIN varchar(25) NOT NULL, PWD varchar(25), 
    ROLE varchar(15) DEFAULT "empl" 
);

/*Add the data to the tables*/
INSERT INTO EMPLOYEES(NAME,FIRSTNAME,TELHOME,TELMOB,TELPRO,ADRESS,POSTALCODE,CITY,EMAIL) VALUES
('Simpson','Homer','0123456789','0612345678','0698765432','2 avenue Duff','92700','Colombes','hsimpson@gmail.com'),
('Simpson','Bart','0145362787','0645362718','0611563477','10 rue des Rebelles','92270','Bois-colombes','bsimpson@gmail.com'),
('Lagaffe','Gaston','0187665987','0623334256','0654778654','65 rue de la Paresse','92700','Colombes','glagaffe@yahoo.fr'),
('Mafalda','Querida','0187611987','0783334256','0658878654','6 rue de Buenos Aires','75016','Paris','qmafalda@hotmail.ar'),
('Woodpecker','Woody','0187384987','0622494256','0674178654','5 bvd des Picoreurs','21000','Dijon','woody@mail.co.uk'),
('Brown','Charlie','0122456678','0699854673','0623445166','140 avenue Foche','90000','Nanterre','cbrown@live.com');

INSERT INTO Users(LOGIN,PWD,ROLE) VALUES ('admin','admin','admin'), ('empl','empl','empl')

