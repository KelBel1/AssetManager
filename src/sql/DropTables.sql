USE AssetDB;

ALTER TABLE assetcheckout
DROP FOREIGN KEY FK_Chk_EmployeeID;
ALTER TABLE assetcheckout
DROP FOREIGN KEY FK_Chk_AssetID;

DROP TABLE assetcheckout;
DROP TABLE employee;
DROP TABLE assets;