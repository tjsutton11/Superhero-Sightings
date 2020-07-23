DROP DATABASE IF EXISTS SuperheroSightings;

CREATE DATABASE SuperheroSightings;

USE SuperheroSightings;

CREATE TABLE Location (
    LocationID INT NOT NULL auto_increment,
    Name VARCHAR(45) NOT NULL,
    `description` VARCHAR(100) NULL,
    `street` VARCHAR(40) NOT NULL,
    `city` VARCHAR(40) NOT NULL,
    `state` VARCHAR(20) NOT NULL,
    `zip` VARCHAR(15) NOT NULL,
    Latitude DECIMAL(9,6) NOT NULL,
    Longitude DECIMAL(9,6) NOT NULL,
    PRIMARY KEY(LocationID)
);

CREATE TABLE Organization (
	`organizationId` INT NOT NULL auto_increment,
    `name` VARCHAR(45) NOT NULL,
    `description` VARCHAR(240) NULL,
    LocationID INT NOT NULL,
    Phone VARCHAR(10) NOT NULL,
    Email VARCHAR(30) NOT NULL,
    PRIMARY KEY(`organizationId`),
    FOREIGN KEY(LocationID) REFERENCES Location(LocationID)
);

CREATE TABLE Sighting (
	`sightingId` INT NOT NULL auto_increment,
    LocationID INT NOT NULL,
    `date` DATE NOT NULL,
    `time` TIME NOT NULL,
    PRIMARY KEY(`sightingId`),
    FOREIGN KEY(LocationID) REFERENCES Location(LocationID)
);

CREATE TABLE Hero (
	`heroId` INT NOT NULL auto_increment,
    `name` VARCHAR(45) NOT NULL,
    `description` VARCHAR(240) NOT NULL,
    `power` VARCHAR(50) NOT NULL,
    PRIMARY KEY(`heroId`)
);

CREATE TABLE HeroOrganization (
	`heroId` INT NOT NULL,
    `organizationId` INT NOT NULL,
	PRIMARY KEY(`heroId`, `organizationId`),
    FOREIGN KEY(`heroId`) REFERENCES Hero(`heroId`),
    FOREIGN KEY(`organizationId`) REFERENCES Organization(`organizationId`)
);

CREATE TABLE HeroSighting (
	`heroId` INT NOT NULL,
    `sightingId` INT NOT NULL,
	PRIMARY KEY(`heroId`, `sightingId`),
    FOREIGN KEY(`heroId`) REFERENCES Hero(`heroId`),
    FOREIGN KEY(`sightingId`) REFERENCES Sighting(`sightingId`)
);