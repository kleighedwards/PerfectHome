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
INSERT INTO `home` (`id`, `zp_id`) VALUES (1, 13138711);
INSERT INTO `home` (`id`, `zp_id`) VALUES (2, 102995240);
INSERT INTO `home` (`id`, `zp_id`) VALUES (3, 13028709);

COMMIT;


-- -----------------------------------------------------
-- Data for table `home_user`
-- -----------------------------------------------------
START TRANSACTION;
USE `homedb`;
INSERT INTO `home_user` (`id`, `home_id`, `user_id`) VALUES (1, 1, 1);
INSERT INTO `home_user` (`id`, `home_id`, `user_id`) VALUES (2, 2, 1);
INSERT INTO `home_user` (`id`, `home_id`, `user_id`) VALUES (3, 3, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `note`
-- -----------------------------------------------------
START TRANSACTION;
USE `homedb`;
INSERT INTO `note` (`id`, `date`, `notes`, `home_user_id`) VALUES (1, '2016-10-30 14:50:00', 'Loved the master bathroom', 1);
INSERT INTO `note` (`id`, `date`, `notes`, `home_user_id`) VALUES (2, '2016-10-30 15:00:00', 'Really spacious kitchen', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `todo`
-- -----------------------------------------------------
START TRANSACTION;
USE `homedb`;
INSERT INTO `todo` (`id`, `date`, `task`, `completed`, `home_user_id`) VALUES (1, '2016-10-30', 'Call roofer', false, 1);
INSERT INTO `todo` (`id`, `date`, `task`, `completed`, `home_user_id`) VALUES (2, '2016-10-30', 'Ask about schools in district', false, 1);
INSERT INTO `todo` (`id`, `date`, `task`, `completed`, `home_user_id`) VALUES (3, '2016-11-04', 'Call painter for living room', false, 1);

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
