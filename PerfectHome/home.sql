-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema homedb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `homedb` ;

-- -----------------------------------------------------
-- Schema homedb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `homedb` DEFAULT CHARACTER SET utf8 ;
USE `homedb` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(255) NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `home`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `home` ;

CREATE TABLE IF NOT EXISTS `home` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `zp_id` INT NULL,
  `address` VARCHAR(255) NULL,
  `zillow_image` VARCHAR(255) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `home_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `home_user` ;

CREATE TABLE IF NOT EXISTS `home_user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `home_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_home_has_user_user1_idx` (`user_id` ASC),
  INDEX `fk_home_has_user_home_idx` (`home_id` ASC),
  CONSTRAINT `fk_home_has_user_home`
    FOREIGN KEY (`home_id`)
    REFERENCES `home` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_home_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `note`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `note` ;

CREATE TABLE IF NOT EXISTS `note` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NULL,
  `notes` VARCHAR(500) NULL,
  `home_user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_note_home_user1_idx` (`home_user_id` ASC),
  CONSTRAINT `fk_note_home_user1`
    FOREIGN KEY (`home_user_id`)
    REFERENCES `home_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `todo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `todo` ;

CREATE TABLE IF NOT EXISTS `todo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NULL,
  `task` VARCHAR(255) NULL,
  `completed` TINYINT(1) NULL,
  `home_user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_todo_home_user1_idx` (`home_user_id` ASC),
  CONSTRAINT `fk_todo_home_user1`
    FOREIGN KEY (`home_user_id`)
    REFERENCES `home_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `image`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `image` ;

CREATE TABLE IF NOT EXISTS `image` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `url` VARCHAR(255) NULL,
  `home_user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_image_home_user1_idx` (`home_user_id` ASC),
  CONSTRAINT `fk_image_home_user1`
    FOREIGN KEY (`home_user_id`)
    REFERENCES `home_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
GRANT USAGE ON *.* TO homeuser;
 DROP USER homeuser;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'homeuser' IDENTIFIED BY 'home';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'homeuser';

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `homedb`;
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`) VALUES (1, 'testUser', 'password', 'John', 'Doe');
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`) VALUES (2, 'anotherUser', 'password', 'Jane', 'Doe');

COMMIT;


-- -----------------------------------------------------
-- Data for table `home`
-- -----------------------------------------------------
START TRANSACTION;
USE `homedb`;
INSERT INTO `home` (`id`, `zp_id`, `address`, `zillow_image`) VALUES (1, 7796919, '6303 N 15th St', 'http://photos.zillowstatic.com/p_d/IS2ny0atnkvzz90000000000.jpg');
INSERT INTO `home` (`id`, `zp_id`, `address`, `zillow_image`) VALUES (2, 48749425, '2114 Bigelow Ave N', 'http://photos.zillowstatic.com/p_d/ISxb3qa8s1cwx01000000000.jpg');
INSERT INTO `home` (`id`, `zp_id`, `address`, `zillow_image`) VALUES (3, 113218284, '1235 Sunset Rd SW', 'http://photos.zillowstatic.com/p_d/ISe8jcpydjedym1000000000.jpg');

COMMIT;


-- -----------------------------------------------------
-- Data for table `home_user`
-- -----------------------------------------------------
START TRANSACTION;
USE `homedb`;
INSERT INTO `home_user` (`id`, `home_id`, `user_id`) VALUES (1, 1, 1);
INSERT INTO `home_user` (`id`, `home_id`, `user_id`) VALUES (2, 2, 1);
INSERT INTO `home_user` (`id`, `home_id`, `user_id`) VALUES (3, 3, 1);
INSERT INTO `home_user` (`id`, `home_id`, `user_id`) VALUES (4, 1, 2);
INSERT INTO `home_user` (`id`, `home_id`, `user_id`) VALUES (5, 2, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `note`
-- -----------------------------------------------------
START TRANSACTION;
USE `homedb`;
INSERT INTO `note` (`id`, `date`, `notes`, `home_user_id`) VALUES (1, '2016-10-30 14:50:00', 'Loved the master bathroom', 1);
INSERT INTO `note` (`id`, `date`, `notes`, `home_user_id`) VALUES (2, '2016-10-30 15:00:00', 'Really spacious kitchen', 1);
INSERT INTO `note` (`id`, `date`, `notes`, `home_user_id`) VALUES (3, '2016-10-31 16:05:00', 'Great patio. Lots of room for parties.', 2);
INSERT INTO `note` (`id`, `date`, `notes`, `home_user_id`) VALUES (4, '2016-11-01 13:44:00', 'Love the vaulted ceilings!', 3);
INSERT INTO `note` (`id`, `date`, `notes`, `home_user_id`) VALUES (5, '2016-11-01 14:13:00', 'Beautiful stained glass on front door', 3);
INSERT INTO `note` (`id`, `date`, `notes`, `home_user_id`) VALUES (6, '2016-11-10 13:32:00', 'All new stainless appliances in the kitchen!', 4);
INSERT INTO `note` (`id`, `date`, `notes`, `home_user_id`) VALUES (7, '2016-11-10 14:28:00', 'Bathrooms recently remodeled', 4);
INSERT INTO `note` (`id`, `date`, `notes`, `home_user_id`) VALUES (8, '2016-11-13 12:43:00', 'Fantastic view of the mountains!', 5);
INSERT INTO `note` (`id`, `date`, `notes`, `home_user_id`) VALUES (9, '2016-11-13 13:36:00', 'Close to work', 5);
INSERT INTO `note` (`id`, `date`, `notes`, `home_user_id`) VALUES (10, '2016-11-14 15:17:00', 'Neighbors seem lovely!', 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `todo`
-- -----------------------------------------------------
START TRANSACTION;
USE `homedb`;
INSERT INTO `todo` (`id`, `date`, `task`, `completed`, `home_user_id`) VALUES (1, '2016-10-30', 'Call roofer', false, 1);
INSERT INTO `todo` (`id`, `date`, `task`, `completed`, `home_user_id`) VALUES (2, '2016-10-30', 'Ask about schools in district', false, 1);
INSERT INTO `todo` (`id`, `date`, `task`, `completed`, `home_user_id`) VALUES (3, '2016-11-04', 'Call painter for living room', false, 1);
INSERT INTO `todo` (`id`, `date`, `task`, `completed`, `home_user_id`) VALUES (4, '2016-11-03', 'See about carpools in neighborhood', false, 2);
INSERT INTO `todo` (`id`, `date`, `task`, `completed`, `home_user_id`) VALUES (5, '2016-11-02', 'Call about hot water heater', false, 2);
INSERT INTO `todo` (`id`, `date`, `task`, `completed`, `home_user_id`) VALUES (6, '2016-11-18', 'See about installing central air', false, 3);
INSERT INTO `todo` (`id`, `date`, `task`, `completed`, `home_user_id`) VALUES (7, '2016-11-10', 'Compare pricing for carpet', false, 4);
INSERT INTO `todo` (`id`, `date`, `task`, `completed`, `home_user_id`) VALUES (8, '2016-11-11', 'Call painter for master bedroom', false, 4);
INSERT INTO `todo` (`id`, `date`, `task`, `completed`, `home_user_id`) VALUES (9, '2016-11-12', 'Price new hot water heater', false, 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `image`
-- -----------------------------------------------------
START TRANSACTION;
USE `homedb`;
INSERT INTO `image` (`id`, `url`, `home_user_id`) VALUES (1, 'http://res.cloudinary.com/dyllookxn/image/upload/v1477543178/sample.jpg', 1);

COMMIT;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
