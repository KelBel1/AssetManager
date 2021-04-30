CREATE SCHEMA if not exists AssetDB;

USE AssetDB;

CREATE TABLE if not exists Assets (
AssetID int AUTO_INCREMENT NOT NULL PRIMARY KEY,
RoomNum int,
EmployeeID int,
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
RoomNum int,
AssetID int,
FOREIGN KEY (AssetID) REFERENCES Assets(AssetID)
);

CREATE TABLE if not exists Location (
RoomNum int NOT NULL PRIMARY KEY,
EmployeeID int,
AssetID int,
FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID),
FOREIGN KEY (AssetID) REFERENCES Assets(AssetID)
);

ALTER TABLE Assets
ADD FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID); 
ALTER TABLE Assets
ADD FOREIGN KEY (RoomNum) REFERENCES Location(RoomNum);
ALTER TABLE Employee
ADD FOREIGN KEY (RoomNum) REFERENCES Location(RoomNum);





