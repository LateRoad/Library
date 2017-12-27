SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=1;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

SET NAMES 'utf8';
CREATE DATABASE library;
USE library;

CREATE TABLE IF NOT EXISTS `library`.`user` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NULL,
  `surname` VARCHAR(45) NULL,
  `role` ENUM('admin', 'user') NOT NULL DEFAULT 'user',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `library`.`book` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `author` VARCHAR(45) NOT NULL,
  `login` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


INSERT INTO `library`.`user` (`id`, `login`, `password`, `name`, `surname`, `role`) VALUES (1, 'lateroad', '1234', 'Roman', 'Pozdnyakov', 'admin');
INSERT INTO `library`.`user` (`id`, `login`, `password`, `name`, `surname`, `role`) VALUES (2, 'user', '1234', 'Name', 'Surname', 'user');
INSERT INTO `library`.`user` (`id`, `login`, `password`, `name`, `surname`, `role`) VALUES (3, 'galadopter', '1234', 'Yan', 'Schneider', 'user');


INSERT INTO `library`.`book` (`id`, `name`, `author`, `login`) VALUES (1, 'Book1', 'Author1', 'user');
INSERT INTO `library`.`book` (`id`, `name`, `author`, `login`) VALUES (2, 'Book2', 'Author2', NULL);
INSERT INTO `library`.`book` (`id`, `name`, `author`, `login`) VALUES (3, 'Book3', 'Author3', 'galadopter');
INSERT INTO `library`.`book` (`id`, `name`, `author`, `login`) VALUES (4, 'Book4', 'Author4', NULL);
INSERT INTO `library`.`book` (`id`, `name`, `author`, `login`) VALUES (5, 'Book5', 'Author5', NULL);
