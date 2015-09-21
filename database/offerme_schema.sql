SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema offerme
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `offerme` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `offerme` ;

-- -----------------------------------------------------
-- Table `offerme`.`administrator`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offerme`.`administrator` (
  `email` VARCHAR(60) NOT NULL,
  `password` VARCHAR(45) NULL,
  `name` VARCHAR(65) NULL,
  PRIMARY KEY (`email`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `offerme`.`restaurant_manager`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offerme`.`restaurant_manager` (
  `email` VARCHAR(60) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `name` VARCHAR(65) NOT NULL,
  `address` VARCHAR(100) NULL,
  `tel` VARCHAR(15) NULL,
  `state` VARCHAR(10) NULL,
  `remarks` TEXT NULL,
  `administrator_email` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`email`),
  INDEX `fk_restaurant_manager_administrator1_idx` (`administrator_email` ASC),
  CONSTRAINT `fk_restaurant_manager_administrator1`
    FOREIGN KEY (`administrator_email`)
    REFERENCES `offerme`.`administrator` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `offerme`.`restaurant`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offerme`.`restaurant` (
  `idrestaurant` INT NOT NULL AUTO_INCREMENT,
  `location` VARCHAR(45) NULL,
  `address` VARCHAR(100) NULL,
  `tel` VARCHAR(15) NULL,
  `web` VARCHAR(45) NULL,
  `logo_url` VARCHAR(100) NULL,
  `banner_url` VARCHAR(100) NULL,
  `opening_hrs` VARCHAR(100) NULL,
  `description` TEXT NULL,
  `rating` FLOAT NULL,
  `state` VARCHAR(10) NULL,
  `restaurant_manager_email` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`idrestaurant`),
  INDEX `fk_restaurant_restaurant_manager_idx` (`restaurant_manager_email` ASC),
  CONSTRAINT `fk_restaurant_restaurant_manager`
    FOREIGN KEY (`restaurant_manager_email`)
    REFERENCES `offerme`.`restaurant_manager` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `offerme`.`offer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offerme`.`offer` (
  `idoffer` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  `description` TEXT NULL,
  `start_date` DATETIME NULL,
  `end_date` DATETIME NULL,
  `repeat` VARCHAR(100) NULL,
  `valid` TINYINT(1) NULL,
  `image_url` VARCHAR(100) NULL,
  `type` VARCHAR(45) NULL,
  `offer_code` VARCHAR(15) NULL,
  `restaurant_idrestaurant` INT NOT NULL,
  PRIMARY KEY (`idoffer`),
  INDEX `fk_offer_restaurant1_idx` (`restaurant_idrestaurant` ASC),
  CONSTRAINT `fk_offer_restaurant1`
    FOREIGN KEY (`restaurant_idrestaurant`)
    REFERENCES `offerme`.`restaurant` (`idrestaurant`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `offerme`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offerme`.`customer` (
  `email` VARCHAR(60) NOT NULL,
  `access_token` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `tel` VARCHAR(15) NULL,
  `facebook` VARCHAR(60) NULL,
  `offer_count` INT NULL,
  `subscribe` TINYINT(1) NULL,
  PRIMARY KEY (`email`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `offerme`.`customer_has_offer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offerme`.`customer_has_offer` (
  `customer_email` VARCHAR(60) NOT NULL,
  `offer_idoffer` INT NOT NULL,
  PRIMARY KEY (`customer_email`, `offer_idoffer`),
  INDEX `fk_customer_has_offer_offer1_idx` (`offer_idoffer` ASC),
  INDEX `fk_customer_has_offer_customer1_idx` (`customer_email` ASC),
  CONSTRAINT `fk_customer_has_offer_customer1`
    FOREIGN KEY (`customer_email`)
    REFERENCES `offerme`.`customer` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_customer_has_offer_offer1`
    FOREIGN KEY (`offer_idoffer`)
    REFERENCES `offerme`.`offer` (`idoffer`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- changes v1

ALTER TABLE `offerme`.`restaurant_manager` 
DROP FOREIGN KEY `fk_restaurant_manager_administrator1`;

ALTER TABLE `offerme`.`restaurant_manager` 
CHANGE COLUMN `administrator_email` `administrator_email` VARCHAR(60) NULL DEFAULT NULL ;

ALTER TABLE `offerme`.`restaurant` 
DROP COLUMN `location`,
ADD COLUMN `name` VARCHAR(45) NOT NULL AFTER `idrestaurant`,
ADD COLUMN `latitude` DOUBLE NOT NULL AFTER `name`,
ADD COLUMN `longitude` DOUBLE NOT NULL AFTER `latitude`;

ALTER TABLE `offerme`.`offer` 
CHANGE COLUMN `title` `title` VARCHAR(45) NOT NULL ,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`idoffer`, `title`);

ALTER TABLE `offerme`.`customer_has_offer` 
DROP PRIMARY KEY;

ALTER TABLE `offerme`.`restaurant_manager` 
ADD CONSTRAINT `fk_restaurant_manager_administrator1`
  FOREIGN KEY (`administrator_email`)
  REFERENCES `offerme`.`administrator` (`email`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
-- changes v2

ALTER TABLE `offerme`.`offer` 
CHANGE COLUMN `title` `title` VARCHAR(45) NULL DEFAULT NULL ,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`idoffer`);

-- changes v3

ALTER TABLE `offerme`.`restaurant_manager` 
CHANGE COLUMN `address` `address` VARCHAR(300) NULL DEFAULT NULL ;

ALTER TABLE `offerme`.`restaurant` 
CHANGE COLUMN `address` `address` VARCHAR(300) NULL DEFAULT NULL ,
CHANGE COLUMN `web` `web` VARCHAR(300) NULL DEFAULT NULL ,
CHANGE COLUMN `logo_url` `logo_url` VARCHAR(300) NULL DEFAULT NULL ,
CHANGE COLUMN `banner_url` `banner_url` VARCHAR(300) NULL DEFAULT NULL ,
CHANGE COLUMN `opening_hrs` `opening_hrs` VARCHAR(160) NULL DEFAULT NULL ,
CHANGE COLUMN `state` `state` VARCHAR(20) NULL DEFAULT NULL ,
ADD COLUMN `type` VARCHAR(45) NULL DEFAULT NULL AFTER `state`;

ALTER TABLE `offerme`.`offer` 
CHANGE COLUMN `title` `title` VARCHAR(100) NULL DEFAULT NULL ,
CHANGE COLUMN `image_url` `image_url` VARCHAR(300) NULL DEFAULT NULL ;

ALTER TABLE `offerme`.`customer` 
CHANGE COLUMN `name` `name` VARCHAR(60) NULL DEFAULT NULL ,
CHANGE COLUMN `facebook` `facebook` VARCHAR(100) NULL DEFAULT NULL ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

