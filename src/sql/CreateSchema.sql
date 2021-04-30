CREATE SCHEMA if not exists AssetDB;

USE AssetDB;

CREATE TABLE if not exists Assets (
AssetID int AUTO_INCREMENT NOT NULL PRIMARY KEY,
Brand varchar(255),
Model varchar(255),
Series int, 
ServiceTag varchar(255),
SerialNum int, 
AssetType varchar(255),
PurchaseDate date,
DateAssigned date,
Cost decimal(19,4)
);

CREATE TABLE if not exists Employee (
EmployeeID int AUTO_INCREMENT NOT NULL PRIMARY KEY,
EmpFirstName varchar(255),
EmpLastName varchar(255),
RoomNum int
);

CREATE TABLE if not exists AssetCheckout (
CheckoutID int AUTO_INCREMENT NOT NULL PRIMARY KEY,
EmployeeID int,
AssetID int,
CONSTRAINT FK_Chk_EmployeeID FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID) ON UPDATE CASCADE,
CONSTRAINT FK_Chk_AssetID FOREIGN KEY (AssetID) REFERENCES Assets(AssetID) ON UPDATE CASCADE
);



