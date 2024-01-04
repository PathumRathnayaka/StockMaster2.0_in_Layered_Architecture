CREATE DATABASE stockmaster;

USE stockmaster;

CREATE TABLE Admin (
                       AdminID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                       Name VARCHAR(50) NOT NULL,
                       Password VARCHAR(50) NOT NULL
);
CREATE TABLE supplier(
                         supplierID VARCHAR(50)PRIMARY KEY,
                         supplierName VARCHAR(50)NOT NULL,
                         invoiceNumber VARCHAR(50),
                         date date,
                         supplierContact int(10)
);
CREATE TABLE inventory(
                          inventoryId VARCHAR(50)PRIMARY KEY,
                          godownID VARCHAR(50)NOT NULL,
                          trackID VARCHAR(50)NOT NULL,
                          invoiceNumber VARCHAR(50),
                          quantity int ,
                          date date
);
CREATE TABLE expired(
                        expiredID VARCHAR(50)PRIMARY KEY,
                        inventoryId VARCHAR(50),
                        itemName VARCHAR(50),
                        quantity int ,
                        supplierID VARCHAR(50),
                        supplierName VARCHAR(50),
                        supplierContact int(10),
                        foreign KEY(inventoryId) references inventory(inventoryId) on delete cascade on update cascade
);
CREATE TABLE item(
                     itemID VARCHAR(50)PRIMARY KEY,
                     supplierID VARCHAR(50),
                     itemName VARCHAR(50) not null,
                     price double not null,
                     category VARCHAR(50) not null,
                     expiryDate date,
                     description VARCHAR(50),
                     degrees VARCHAR(50),
                     inventoryId VARCHAR(50),
                     foreign KEY(supplierID) references supplier(supplierID) on delete cascade on update cascade
);
CREATE TABLE marketplace(
                            marketplaceId VARCHAR(50)PRIMARY KEY,
                            marketName VARCHAR(50),
                            address VARCHAR(50),
                            hotline int(10),
                            email VARCHAR(255)
);
CREATE TABLE shipment(
                         shipmentID VARCHAR(50)PRIMARY KEY,
                         marketplaceId VARCHAR(50),
                         date date,
                         foreign KEY (marketplaceId) references marketplace(marketplaceId) on delete cascade on update cascade
);
CREATE TABLE shipmentDetail(
                               shipmentID VARCHAR(50),
                               itemID VARCHAR(50),
                               qty int not null,
                               unitPtice double not null,
                               foreign KEY (shipmentID) references shipment(shipmentID) on delete cascade on update cascade,
                               foreign KEY (itemID) references item(itemID) on delete cascade on update cascade
);

