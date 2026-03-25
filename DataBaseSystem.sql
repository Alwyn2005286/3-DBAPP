CREATE DATABASE IF NOT EXISTS DataBaseSystem;
USE DataBaseSystem;

DROP TABLE IF EXISTS `Violations`, `Assigned_Inspectors`, `Inspections`, `Establishments`, `Establishment_Owners`, `REF_Cities`, `Inspectors`, `REF_Inspection_Requirements`;

CREATE TABLE `REF_Cities` (
    `city_id` INT AUTO_INCREMENT PRIMARY KEY,
    `city_name` VARCHAR(45) NOT NULL UNIQUE
);

CREATE TABLE `Establishment_Owners` (
    `owner_id` INT AUTO_INCREMENT PRIMARY KEY,
    `first_name` VARCHAR(50),
    `last_name` VARCHAR(50)
);

CREATE TABLE `Establishments` (
    `establishment_id` INT AUTO_INCREMENT PRIMARY KEY,
    `owner_id` INT,
    `establishment_name` VARCHAR(45),
    `city_id` INT,
    FOREIGN KEY (`owner_id`) REFERENCES `Establishment_Owners`(`owner_id`),
    FOREIGN KEY (`city_id`) REFERENCES `REF_Cities`(`city_id`)
);

CREATE TABLE `Inspectors` (
    `inspector_id` INT AUTO_INCREMENT PRIMARY KEY,
    `first_name` VARCHAR(50),
    `last_name` VARCHAR(50)
);

CREATE TABLE `REF_Inspection_Requirements` (
    `Requirement_Code` INT PRIMARY KEY,
    `Title` VARCHAR(50),
    `Standard_Fine` DECIMAL(7,2),
    `Description` VARCHAR(100),
    `Weight` INT
);

CREATE TABLE `Inspections` (
    `inspection_id` INT AUTO_INCREMENT PRIMARY KEY,
    `establishment_id` INT,
    `inspection_date` DATE,
    `score` DECIMAL(5,2),
    `grade` ENUM('PASS', 'FAIL'),
    `remarks` VARCHAR(255),
    FOREIGN KEY (`establishment_id`) REFERENCES `Establishments`(`establishment_id`)
);

CREATE TABLE `Assigned_Inspectors` (
    `assignment_id` INT AUTO_INCREMENT PRIMARY KEY,
    `inspection_id` INT,
    `inspector_id` INT,
    FOREIGN KEY (`inspection_id`) REFERENCES `Inspections`(`inspection_id`),
    FOREIGN KEY (`inspector_id`) REFERENCES `Inspectors`(`inspector_id`)
);

CREATE TABLE `Violations` (
    `violation_id` INT AUTO_INCREMENT PRIMARY KEY,
    `inspection_id` INT,
    `Requirement_Code` INT, -- Fixed name to match reference table
    `remarks` VARCHAR(255),
    `status` ENUM('PASS', 'FAIL'),
    FOREIGN KEY (`inspection_id`) REFERENCES `Inspections`(`inspection_id`),
    FOREIGN KEY (`Requirement_Code`) REFERENCES `REF_Inspection_Requirements`(`Requirement_Code`)
);

INSERT INTO `REF_Cities` (city_name) VALUES 
('Manila'), 
('Quezon City'),
 ('Makati');
 
INSERT INTO `Establishment_Owners` (first_name, last_name) VALUES 
('Tony', 'Tan Caktiong'), 
('George', 'Yang'), 
('Edgar', 'Sia');
INSERT INTO `Establishments` (owner_id, establishment_name, city_id) VALUES 
(1, 'Jollibee', 1), 
(2, 'McDonald''s', 2), 
(3, 'Mang Inasal', 3);
INSERT INTO `Inspectors` (first_name, last_name) VALUES 
('Juan', 'Dela Cruz'), 
('Maria', 'Santos'), 
('Pedro', 'Reyes');

INSERT INTO `REF_Inspection_Requirements` (Requirement_Code, Title, Standard_Fine, Description, Weight) VALUES
(1, 'Food Safety Compliance', 100.00, 'Food preparation and processing', 50),
(2, 'Sanitation and Cleanliness', 100.00, 'Environment is free from microbes', 50),
(3, 'Pest Control Measures', 100.00, 'Infestation is kept to a minimal', 50);

INSERT INTO `Inspections` (establishment_id, inspection_date, score, grade, remarks) VALUES
(1, '2024-01-15', 75.5, 'FAIL', 'Needs improvement.'),
(2, '2024-01-16', 60.0, 'FAIL', 'Critical violation.'),
(3, '2024-01-17', 45.0, 'FAIL', 'Serious health risk.');

INSERT INTO `Assigned_Inspectors` (inspection_id, inspector_id) VALUES 
(1, 1), 
(2, 2), 
(3, 3);
INSERT INTO `Violations` (inspection_id, Requirement_Code, remarks, status) VALUES 
(1, 2, 'Floors are dirty.', 'FAIL'), 
(2, 1, 'Food not stored at correct temperature.', 'FAIL'), 
(3, 3, 'Evidence of rodents found.', 'FAIL');