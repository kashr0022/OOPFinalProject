DROP DATABASE IF EXISTS ptfms;
CREATE DATABASE ptfms;
USE ptfms;

-- Staff Table with Role ENUM to separate privelages
CREATE TABLE Staff (
    StaffID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(30),
    LastName VARCHAR(30) NOT NULL,
    Email VARCHAR(100) NOT NULL,
    Role ENUM('Operator', 'TransitManager') NOT NULL
);

-- Users Table
CREATE TABLE Users (
    Username VARCHAR(20),
    Password VARCHAR(20),
    StaffID INT NOT NULL PRIMARY KEY,
    FOREIGN KEY (StaffID) REFERENCES Staff(StaffID)
);

-- Vehicles Table with Type ENUM and ConsumptionRate
CREATE TABLE Vehicles (
    VehicleNumber INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    VehicleType ENUM('DieselBus', 'DieselElectricTrain', 'ElectricLightRail') NOT NULL,
    ConsumptionRate DECIMAL(10,2) NOT NULL,
    ConsumptionUnit ENUM('mpg', '1/km', 'kWh/hr') NOT NULL
);

-- Components Table for All Vehicle Types
CREATE TABLE Components (
    ComponentID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    VehicleNumber INT NOT NULL,
    ComponentName VARCHAR(50) NOT NULL,
    ComponentType ENUM('Diesel', 'Electric', 'Hybrid') NOT NULL,
    HoursUsed INT NOT NULL,
    FOREIGN KEY (VehicleNumber) REFERENCES Vehicles(VehicleNumber)
);

-- Staff log of working hours
CREATE TABLE StaffLog (
    LogNo INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    StaffID INT NOT NULL,
    StartTime DATETIME NOT NULL,
    EndTime DATETIME NOT NULL,
    Notes VARCHAR(100),
    FOREIGN KEY (StaffID) REFERENCES Staff(StaffID)
);

-- GPS Tracking Table
CREATE TABLE GPS (
    GPSID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    StaffID INT NOT NULL,
    VehicleNumber INT NOT NULL,
    StartTime DATETIME NOT NULL,
    EndTime DATETIME NOT NULL,
    Notes VARCHAR(100),
    FOREIGN KEY (VehicleNumber) REFERENCES Vehicles(VehicleNumber),
    FOREIGN KEY (StaffID) REFERENCES Staff(StaffID)
);

-- Fuel Usage Reporting
CREATE TABLE FuelReport (
    ReportID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    StaffID INT NOT NULL,
    VehicleNumber INT NOT NULL,
    UsageAmt DECIMAL(10,2) NOT NULL,
    Date DATETIME NOT NULL,
    Status VARCHAR(20),
    FOREIGN KEY (VehicleNumber) REFERENCES Vehicles(VehicleNumber),
    FOREIGN KEY (StaffID) REFERENCES Staff(StaffID)
);

-- Maintenance Logging Table
CREATE TABLE MaintenanceLog (
    LogID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    StaffID INT NOT NULL,
    GPSID INT NOT NULL,
    VehicleNumber INT NOT NULL,
    ComponentID INT NOT NULL,
    UsageAmt DECIMAL(10,2) NOT NULL,
    Date DATETIME NOT NULL,
    Status VARCHAR(20),
    FOREIGN KEY (GPSID) REFERENCES GPS(GPSID),
    FOREIGN KEY (StaffID) REFERENCES Staff(StaffID),
    FOREIGN KEY (VehicleNumber) REFERENCES Vehicles(VehicleNumber),
    FOREIGN KEY (ComponentID) REFERENCES Components(ComponentID)
);

-- FOR TESTING Staff INSERT
INSERT INTO Staff (FirstName, LastName, Email, Role) VALUES
    ('cst8288', 'cst8288', 'cst8288@gmail.com', 'TransitManager');

-- FOR TESTING Users INSERT FOR TESTING
INSERT INTO Users (Username, Password, StaffID) VALUES
    ('cst8288', 'cst8288', '1' );

-- Staff INSERT
INSERT INTO Staff (FirstName, LastName, Email, Role) VALUES
('Bruce', 'Wayne', 'bruce.wayne@gothamtransit.com', 'TransitManager'),
('Clark', 'Kent', 'clark.kent@metropolistt.com', 'Operator'),
('Diana', 'Prince', 'diana.prince@themysciranrail.com', 'TransitManager'),
('Barry', 'Allen', 'barry.allen@centralspeed.com', 'Operator'),
('Victor', 'Stone', 'victor.stone@techrail.com', 'Operator');

-- Vehicles INSERT
INSERT INTO Vehicles (VehicleType, ConsumptionRate) VALUES
('DieselBus', '8', 'mpg'),
('DieselElectricTrain', '5.5, 'l/km'),
('ElectricLightRail', '120, 'kWh/hr'),
('DieselElectricTrain', '6', 'l/km'),
('ElectricLightRail', '110', 'kWh/hr');

-- Components INSERT
INSERT INTO Components (VehicleNumber, ComponentName, ComponentType, HoursUsed) VALUES
(1, 'Diesel Engine', 'Diesel', 1200),
(2, 'Battery System', 'Electric', 800),
(3, 'Pantograph', 'Electric', 900),
(4, 'Hybrid Drive Unit', 'Hybrid', 1000),
(5, 'Brake Pads', 'Electric', 600);

-- StaffLog INSERT
INSERT INTO StaffLog (StaffID, StartTime, EndTime, Notes) VALUES
(1, '2025-07-28 08:00:00', '2025-07-28 16:00:00', 'Morning shift'),
(3, '2025-07-28 09:00:00', '2025-07-28 17:00:00', 'Late start'),
(5, '2025-07-28 07:30:00', '2025-07-28 15:30:00', 'Early shift'),
(2, '2025-07-28 10:00:00', '2025-07-28 18:00:00', 'Manager review'),
(4, '2025-07-28 11:00:00', '2025-07-28 19:00:00', 'Overseeing repairs');

-- GPS INSERT
INSERT INTO GPS (StaffID, VehicleNumber, StartTime, EndTime, Notes) VALUES
(1, 1, '2025-07-28 08:00:00', '2025-07-28 12:00:00', 'Downtown route'),
(3, 3, '2025-07-28 09:30:00', '2025-07-28 13:30:00', 'City loop'),
(5, 5, '2025-07-28 10:00:00', '2025-07-28 14:00:00', 'Night run'),
(1, 2, '2025-07-29 08:00:00', '2025-07-29 12:00:00', 'Train test'),
(3, 4, '2025-07-29 13:00:00', '2025-07-29 17:00:00', 'Hybrid demo');

-- FuelReport INSERT
INSERT INTO FuelReport (StaffID, VehicleNumber, UsageAmt, Date, Status) VALUES
(1, 1, 50.75, '2025-07-28 13:00:00', 'Approved'),
(3, 2, 120.50, '2025-07-28 14:30:00', 'Pending'),
(5, 4, 80.00, '2025-07-28 15:00:00', 'Approved'),
(2, 1, 45.25, '2025-07-29 11:00:00', 'Rejected'),
(4, 3, 100.00, '2025-07-29 12:00:00', 'Approved');

-- MaintenanceLog INSERT
INSERT INTO MaintenanceLog (StaffID, GPSID, VehicleNumber, ComponentID, UsageAmt, Date, Status) VALUES
(2, 1, 1, 1, 50.00, '2025-07-28 16:00:00', 'Completed'),
(4, 2, 2, 2, 30.00, '2025-07-28 17:00:00', 'In Progress'),
(2, 3, 3, 3, 20.00, '2025-07-28 18:00:00', 'Completed'),
(4, 4, 4, 4, 40.00, '2025-07-29 10:00:00', 'Pending'),
(2, 5, 5, 5, 25.00, '2025-07-29 11:00:00', 'Completed');
