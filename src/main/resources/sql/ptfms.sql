DROP DATABASE IF EXISTS ptfms;
CREATE DATABASE ptfms;
USE ptfms;

-- Staff Table with Role ENUM to separate privileges
CREATE TABLE Staff
(
    StaffID   INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(30),
    LastName  VARCHAR(30)  NOT NULL,
    Email     VARCHAR(100) NOT NULL,
    Role      ENUM('Operator', 'TransitManager') NOT NULL
);

-- Users Table
CREATE TABLE Users
(
    Username VARCHAR(20),
    Password VARCHAR(20),
    Role ENUM('Operator', 'TransitManager') NOT NULL,
    StaffID  INT NOT NULL PRIMARY KEY,
    FOREIGN KEY (StaffID) REFERENCES Staff (StaffID)
);

-- Vehicles Table with Type ENUM and ConsumptionRate
CREATE TABLE Vehicles
(
    VehicleID   INT            NOT NULL AUTO_INCREMENT PRIMARY KEY,
    VehicleNumber VARCHAR(50) UNIQUE,
    VehicleType     ENUM('DieselBus', 'DieselElectricTrain', 'ElectricLightRail') NOT NULL,
    ConsumptionRate DECIMAL(10, 2) NOT NULL,
    ConsumptionUnit ENUM('mpg', 'L/km', 'kWh/hr') NOT NULL,
    MaxPassengers INT NOT NULL,
    ActiveRoute VARCHAR(150)
);

-- Components Table for All Vehicle Types
CREATE TABLE Components
(
    ComponentID   INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    VehicleID INT         NOT NULL,
    ComponentName VARCHAR(50) NOT NULL,
    ComponentType ENUM('Diesel', 'Electric', 'Hybrid') NOT NULL,
    HoursUsed     INT         NOT NULL,
    RatePerHour   DECIMAL(10, 2) NOT NULL DEFAULT 50.00,
    FOREIGN KEY (VehicleID) REFERENCES Vehicles (VehicleID)
);

-- Staff log of working hours
CREATE TABLE StaffLog
(
    LogNo     INT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    StaffID   INT      NOT NULL,
    StartTime DATETIME NOT NULL,
    EndTime   DATETIME NOT NULL,
    Notes     VARCHAR(100),
    FOREIGN KEY (StaffID) REFERENCES Staff (StaffID)
);

-- GPS Tracking Table
CREATE TABLE GPS
(
    GPSID         INT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    StaffID       INT      NOT NULL,
    VehicleID INT      NOT NULL,
    StartingLocation VARCHAR(100),
    StartTime     DATETIME NOT NULL,
    EndTime       DATETIME NOT NULL,
    EndingLocation VARCHAR(100),
    ScheduledEndTime DATETIME NOT NULL,
    Notes         VARCHAR(100),
    FOREIGN KEY (VehicleID) REFERENCES Vehicles (VehicleID),
    FOREIGN KEY (StaffID) REFERENCES Staff (StaffID)
);

-- Fuel Usage Reporting
CREATE TABLE FuelReport
(
    ReportID      INT            NOT NULL AUTO_INCREMENT PRIMARY KEY,
    StaffID       INT            NOT NULL,
    VehicleID INT            NOT NULL,
    UsageAmt      DECIMAL(10, 2) NOT NULL,
    DistanceTraveled INT         NOT NULL,
    FuelType      VARCHAR(20)    NOT NULL,
    Date          DATETIME       NOT NULL,
    Status        VARCHAR(20),
    FOREIGN KEY (VehicleID) REFERENCES Vehicles (VehicleID),
    FOREIGN KEY (StaffID) REFERENCES Staff (StaffID)
);

-- Maintenance Logging Table
CREATE TABLE MaintenanceLog
(
    LogID         INT            NOT NULL AUTO_INCREMENT PRIMARY KEY,
    StaffID       INT            NOT NULL,
    GPSID         INT            NOT NULL,
    VehicleID INT            NOT NULL,
    ComponentID   INT            NOT NULL,
    UsageAmt      DECIMAL(10, 2) NOT NULL,
    Date          DATETIME       NOT NULL,
    Status        VARCHAR(20),
    FOREIGN KEY (GPSID) REFERENCES GPS (GPSID),
    FOREIGN KEY (StaffID) REFERENCES Staff (StaffID),
    FOREIGN KEY (VehicleID) REFERENCES Vehicles (VehicleID),
    FOREIGN KEY (ComponentID) REFERENCES Components (ComponentID)
);

-- Fuel Rates Table
CREATE TABLE FuelRates (
    FuelType VARCHAR(50) PRIMARY KEY,
    RatePerUnit DECIMAL(10,2)
);


-- FOR TESTING Staff INSERT
INSERT INTO Staff (FirstName, LastName, Email, Role)
VALUES ('cst8288', 'cst8288', 'cst8288@gmail.com', 'TransitManager');
INSERT INTO Users (Username, Password, Role, StaffID)
VALUES ('cst8288', 'cst8288', 'TransitManager', LAST_INSERT_ID());

-- Staff INSERTS (Separated to include corresponding user inserts, matching IDs)
INSERT INTO Staff (FirstName, LastName, Email, Role)
VALUES ('Bruce', 'Wayne', 'bruce.wayne@gothamtransit.com', 'TransitManager');
INSERT INTO Users (Username, Password, Role, StaffID)
VALUES ('Bruce', 'Wayne', 'TransitManager', LAST_INSERT_ID());

-- Clark Kent insert block
INSERT INTO Staff (FirstName, LastName, Email, Role)
VALUES ('Clark', 'Kent', 'clark.kent@metropolistt.com', 'Operator');
INSERT INTO Users (Username, Password, Role, StaffID)
VALUES ('Clark', 'Kent', 'Operator', LAST_INSERT_ID());

-- Diana Prince insert block
INSERT INTO Staff (FirstName, LastName, Email, Role)
VALUES ('Diana', 'Prince', 'diana.prince@themysciranrail.com', 'TransitManager');
INSERT INTO Users (Username, Password, Role, StaffID)
VALUES ('Diana', 'Prince','TransitManager', LAST_INSERT_ID());

-- Barry Allen
INSERT INTO Staff (FirstName, LastName, Email, Role)
VALUES ('Barry', 'Allen', 'barry.allen@centralspeed.com', 'Operator');
INSERT INTO Users (Username, Password, Role, StaffID)
VALUES ('Barry', 'Allen', 'Operator', LAST_INSERT_ID());

-- Victor Stone
INSERT INTO Staff (FirstName, LastName, Email, Role)
VALUES ('Victor', 'Stone', 'victor.stone@techrail.com', 'Operator');
INSERT INTO Users (Username, Password, Role, StaffID)
VALUES ('Victor', 'Stone', 'Operator', LAST_INSERT_ID());

-- Vehicles INSERT
INSERT INTO Vehicles (VehicleNumber, VehicleType, ConsumptionRate, ConsumptionUnit, MaxPassengers, ActiveRoute)
VALUES ('DB001', 'DieselBus', 8, 'mpg', 40, 'South to Downtown'),
       ('DET001', 'DieselElectricTrain', 5.5, 'L/km', 380, 'City Loop'),
       ('ER001', 'ElectricLightRail', 120, 'kWh/hr', 500, 'Downtown to East'),
       ('DET002', 'DieselElectricTrain', 6, 'L/km', 130, 'Train Testing Route'),
       ('ER002', 'ElectricLightRail', 110, 'kWh/hr', 1000, 'City Core to Outskirts');

-- Components INSERT
INSERT INTO Components (VehicleID, ComponentName, ComponentType, HoursUsed)
VALUES (1, 'Diesel Engine', 'Diesel', 1200),
       (2, 'Battery System', 'Electric', 800),
       (3, 'Pantograph', 'Electric', 900),
       (4, 'Hybrid Drive Unit', 'Hybrid', 1000),
       (5, 'Brake Pads', 'Electric', 600);

-- StaffLog INSERT
INSERT INTO StaffLog (StaffID, StartTime, EndTime, Notes)
VALUES (1, '2025-07-28 08:00:00', '2025-07-28 16:00:00', 'Morning shift'),
       (3, '2025-07-28 09:00:00', '2025-07-28 17:00:00', 'Late start'),
       (5, '2025-07-28 07:30:00', '2025-07-28 15:30:00', 'Early shift'),
       (2, '2025-07-28 10:00:00', '2025-07-28 18:00:00', 'Manager review'),
       (4, '2025-07-28 11:00:00', '2025-07-28 19:00:00', 'Overseeing repairs');

-- GPS INSERT
INSERT INTO GPS (StaffID, VehicleID, StartingLocation, StartTime, EndTime, EndingLocation, ScheduledEndTime, Notes)
VALUES
		(1, 1, 'Central Station', '2025-07-28 08:00:00', '2025-07-28 12:00:00', 'Downtown Terminal', '2025-07-28 12:00:00', 'Downtown route'),
		(3, 3, 'East Side Depot', '2025-07-28 09:30:00', '2025-07-28 13:30:00', 'City Loop', '2025-07-28 13:30:00', 'City loop'),
		(5, 5, 'North Station', '2025-07-28 10:00:00', '2025-07-28 14:00:00', 'East Terminal', '2025-07-28 14:00:00', 'Downtown to East'),
		(1, 2, 'West Yard', '2025-07-29 08:00:00', '2025-07-29 12:00:00', 'Test Route End', '2025-07-29 12:00:00', 'Train Testing Route'),
		(3, 4, 'City Core Start', '2025-07-29 13:00:00', '2025-07-29 17:00:00', 'Outskirts End', '2025-07-29 17:00:00', 'City Core to Outskirts');

-- FuelReport INSERT
INSERT INTO FuelReport (StaffID, VehicleID, UsageAmt, DistanceTraveled, FuelType, Date, Status)
VALUES
        (1, 1, 50.75, 100, 'Diesel', '2025-07-28 13:00:00', 'Approved'),
        (3, 2, 120.50, 200, 'Hybrid', '2025-07-28 14:30:00', 'Pending'),
        (5, 4, 80.00, 150, 'Hybrid', '2025-07-28 15:00:00', 'Approved'),
        (2, 1, 45.25, 90, 'Diesel', '2025-07-29 11:00:00', 'Rejected'),
        (4, 3, 100.00, 180, 'Electric', '2025-07-29 12:00:00', 'Approved');

-- MaintenanceLog INSERT
INSERT INTO MaintenanceLog (StaffID, GPSID, VehicleID, ComponentID, UsageAmt, Date, Status)
VALUES (2, 1, 1, 1, 50.00, '2025-07-28 16:00:00', 'Completed'),
       (4, 2, 2, 2, 30.00, '2025-07-28 17:00:00', 'In Progress'),
       (2, 3, 3, 3, 20.00, '2025-07-28 18:00:00', 'Completed'),
       (4, 4, 4, 4, 40.00, '2025-07-29 10:00:00', 'Pending'),
       (2, 5, 5, 5, 25.00, '2025-07-29 11:00:00', 'Completed');

-- Fuel Rates INSERT
INSERT INTO FuelRates (FuelType, RatePerUnit) VALUES
        ('Diesel', 1.50),
        ('Electric', 0.20),
        ('Hybrid', 1.10);

