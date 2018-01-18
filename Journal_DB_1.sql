-- MySQL Script generated by MySQL Workbench
-- Thu Jan 18 01:48:35 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema journal
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema journal
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `journal` DEFAULT CHARACTER SET utf8 ;
USE `journal` ;

-- -----------------------------------------------------
-- Table `journal`.`Test`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `journal`.`Test` (
  `idTest` INT NOT NULL AUTO_INCREMENT,
  `entry` VARCHAR(45) NOT NULL,
  `flag` INT NULL,
  PRIMARY KEY (`idTest`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `journal`.`workspaceLog`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `journal`.`workspaceLog` (
  `idLog` INT NOT NULL AUTO_INCREMENT,
  `entry` VARCHAR(45) NOT NULL,
  `timestamp` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idLog`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `journal`.`flag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `journal`.`flag` (
  `idflag` INT NOT NULL AUTO_INCREMENT,
  `flag_name` VARCHAR(255) NOT NULL,
  `book` VARCHAR(255) NULL,
  `quest` VARCHAR(45) NULL,
  `icon_location` VARCHAR(255) NULL,
  PRIMARY KEY (`idflag`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `journal`.`log`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `journal`.`log` (
  `idlog` INT NOT NULL AUTO_INCREMENT,
  `entry` VARCHAR(255) NOT NULL,
  `flag` INT NULL,
  `flag2` INT NULL,
  `timestamp` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idlog`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
