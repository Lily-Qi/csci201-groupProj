CREATE SCHEMA `final_project` ;

CREATE TABLE `final_project`.`groups` (
  `groupID` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`groupID`),
  UNIQUE INDEX `groupID_UNIQUE` (`groupID` ASC) VISIBLE);
  
  CREATE TABLE `final_project`.`users_has_groups` (
  `users_userID` INT NOT NULL,
  `groups_groupID` INT NOT NULL);

CREATE TABLE `final_project`.`users` (
  `userID` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`userID`));
  
  CREATE TABLE `final_project`.`tasks` (
  `taskID` INT NOT NULL,
  `tasks_groupID` INT NOT NULL,
  `taskInfo` VARCHAR(45) NULL,
  `taskDueDate` VARCHAR(45) NULL,
  PRIMARY KEY (`taskID`));

ALTER TABLE `final_project`.`tasks` 
ADD INDEX `groupID_idx` (`tasks_groupID` ASC) VISIBLE;
;
ALTER TABLE `final_project`.`tasks` 
ADD CONSTRAINT `groupID`
  FOREIGN KEY (`tasks_groupID`)
  REFERENCES `final_project`.`groups` (`groupID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
ALTER TABLE `final_project`.`users_has_groups` 
ADD INDEX `userID_idx` (`users_userID` ASC) VISIBLE;
;
ALTER TABLE `final_project`.`users_has_groups` 
ADD CONSTRAINT `userID`
  FOREIGN KEY (`users_userID`)
  REFERENCES `final_project`.`users` (`userID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `final_project`.`groups` 
ADD COLUMN `groupID_joining` VARCHAR(45) NULL AFTER `description`;

ALTER TABLE `final_project`.`users_has_groups` 
ADD CONSTRAINT `groupID_joining`
  FOREIGN KEY (`groups_groupID`)
  REFERENCES `final_project`.`groups` (`groupID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `final_project`.`groups` 
DROP COLUMN `groupID_joining`;