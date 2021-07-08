-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bd-hibernate-uno-a-muchos
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bd-hibernate-uno-a-muchos
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bd-hibernate-uno-a-muchos` DEFAULT CHARACTER SET latin1 ;
USE `bd-hibernate-uno-a-muchos` ;

-- -----------------------------------------------------
-- Table `bd-hibernate-uno-a-muchos`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd-hibernate-uno-a-muchos`.`cliente` (
  `idCliente` INT NOT NULL AUTO_INCREMENT,
  `apellido` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `dni` INT NOT NULL,
  `fechaDeNacimiento` DATE NULL DEFAULT NULL,
  `baja` BIT(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`idCliente`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bd-hibernate-uno-a-muchos`.`prestamo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd-hibernate-uno-a-muchos`.`prestamo` (
  `idPrestamo` INT NOT NULL AUTO_INCREMENT,
  `fecha` DATE NOT NULL,
  `monto` DOUBLE NOT NULL,
  `interes` DOUBLE NOT NULL,
  `cantCuotas` INT NOT NULL,
  `cancelado` BIT(1) NOT NULL DEFAULT b'0',
  `idCliente` INT NOT NULL,
  PRIMARY KEY (`idPrestamo`),
  INDEX `fkCliente_idx` (`idCliente` ASC) VISIBLE,
  CONSTRAINT `fkCliente`
    FOREIGN KEY (`idCliente`)
    REFERENCES `bd-hibernate-uno-a-muchos`.`cliente` (`idCliente`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bd-hibernate-uno-a-muchos`.`cuota`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd-hibernate-uno-a-muchos`.`cuota` (
  `idCuota` INT NOT NULL AUTO_INCREMENT,
  `nroCuota` INT NOT NULL,
  `fechaVencimiento` DATE NOT NULL,
  `saldoPendiente` DOUBLE NOT NULL,
  `amortizacion` DOUBLE NOT NULL,
  `interesCuota` DOUBLE NOT NULL,
  `cuota` DOUBLE NOT NULL,
  `deuda` DOUBLE NOT NULL,
  `cancelada` BIT(1) NOT NULL,
  `fechaDePago` DATE NULL DEFAULT NULL,
  `punitorios` DOUBLE NOT NULL,
  `idPrestamo` INT NOT NULL,
  PRIMARY KEY (`idCuota`),
  INDEX `fkPrestamo_idx` (`idPrestamo` ASC) VISIBLE,
  CONSTRAINT `fkPrestamo`
    FOREIGN KEY (`idPrestamo`)
    REFERENCES `bd-hibernate-uno-a-muchos`.`prestamo` (`idPrestamo`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
