CREATE TABLE IF NOT EXISTS `mydb`.`books` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `img_url` VARCHAR(45) NULL,
  `score` DOUBLE NULL,
  `author` VARCHAR(45) NULL,
  `publish_company` VARCHAR(45) NULL,
  `publish_at` TIMESTAMP NULL,
  `create_at` TIMESTAMP NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8

CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `id` INT NULL,
  `name` VARCHAR(45) NULL,
  `sex` VARCHAR(45) NULL,
  `age` INT NULL,
  `password` VARCHAR(45) NULL,
  `account` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8