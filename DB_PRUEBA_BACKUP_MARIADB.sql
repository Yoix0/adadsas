/*
SQLyog Ultimate v12.4.3 (64 bit)
MySQL - 11.8.2-MariaDB-ubu2404 : Database - adasas
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`adasas` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_uca1400_ai_ci */;

USE `adasas`;

/*Table structure for table `TMP_LLENAR_CAMPOS` */

DROP TABLE IF EXISTS `TMP_LLENAR_CAMPOS`;

CREATE TABLE `TMP_LLENAR_CAMPOS` (
  `id_company` int(11) DEFAULT NULL,
  `codigo_company` varchar(255) DEFAULT NULL,
  `name_company` varchar(255) DEFAULT NULL,
  `description_company` varchar(255) DEFAULT NULL,
  `version_id` int(11) DEFAULT NULL,
  `app_id` int(11) DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL,
  `version_description` varchar(255) DEFAULT NULL,
  `version_company_id` int(11) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  `app_code` varchar(255) DEFAULT NULL,
  `app_name` varchar(255) DEFAULT NULL,
  `app_description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

/*Data for the table `TMP_LLENAR_CAMPOS` */

insert  into `TMP_LLENAR_CAMPOS`(`id_company`,`codigo_company`,`name_company`,`description_company`,`version_id`,`app_id`,`version`,`version_description`,`version_company_id`,`company_id`,`app_code`,`app_name`,`app_description`) values 
(1,'COMPANIA1','ARLEY COMPANY','COMPNIA DESCRIPTION',1,1,'V1','V1 DESCRIPTION',1,1,'APP1','APP ALCH','APP DESCRIPTION COMPANY');

/*Table structure for table `application` */

DROP TABLE IF EXISTS `application`;

CREATE TABLE `application` (
  `app_id` int(11) NOT NULL AUTO_INCREMENT,
  `app_code` varchar(50) NOT NULL,
  `app_name` varchar(255) NOT NULL,
  `app_description` tinytext DEFAULT NULL,
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `app_code` (`app_code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

/*Data for the table `application` */

insert  into `application`(`app_id`,`app_code`,`app_name`,`app_description`) values 
(1,'APP1','APP ALCH','APP DESCRIPTION COMPANY');

/*Table structure for table `company` */

DROP TABLE IF EXISTS `company`;

CREATE TABLE `company` (
  `id_company` int(11) NOT NULL AUTO_INCREMENT,
  `codigo_company` varchar(50) NOT NULL,
  `name_company` varchar(255) NOT NULL,
  `description_company` tinytext DEFAULT NULL,
  PRIMARY KEY (`id_company`),
  UNIQUE KEY `codigo_company` (`codigo_company`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

/*Data for the table `company` */

insert  into `company`(`id_company`,`codigo_company`,`name_company`,`description_company`) values 
(1,'COMPANIA1','ARLEY COMPANY','COMPNIA DESCRIPTION'),
(2,'COMPANIA2','ARLEY COMPANY 2 MODIFICADA','COMPNIA DESCRIPTION 2  MODIFICADA'),
(3,'COMPANIA3','ARLEY COMPANY 3','COMPNIA DESCRIPTION 3');

/*Table structure for table `version` */

DROP TABLE IF EXISTS `version`;

CREATE TABLE `version` (
  `version_id` int(11) NOT NULL AUTO_INCREMENT,
  `app_id` int(11) NOT NULL,
  `version` varchar(50) NOT NULL,
  `version_description` tinytext DEFAULT NULL,
  PRIMARY KEY (`version_id`),
  UNIQUE KEY `unique_version_per_app` (`app_id`),
  CONSTRAINT `version_ibfk_1` FOREIGN KEY (`app_id`) REFERENCES `application` (`app_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

/*Data for the table `version` */

insert  into `version`(`version_id`,`app_id`,`version`,`version_description`) values 
(1,1,'V1','V1 DESCRIPTION');

/*Table structure for table `version_company` */

DROP TABLE IF EXISTS `version_company`;

CREATE TABLE `version_company` (
  `version_company_id` int(11) NOT NULL AUTO_INCREMENT,
  `company_id` int(11) NOT NULL,
  `version_id` int(11) NOT NULL,
  `version_company_description` tinytext DEFAULT NULL,
  PRIMARY KEY (`version_company_id`),
  UNIQUE KEY `unique_version_per_company` (`company_id`),
  KEY `version_id` (`version_id`),
  CONSTRAINT `version_company_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `company` (`id_company`),
  CONSTRAINT `version_company_ibfk_2` FOREIGN KEY (`version_id`) REFERENCES `version` (`version_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

/*Data for the table `version_company` */

insert  into `version_company`(`version_company_id`,`company_id`,`version_id`,`version_company_description`) values 
(1,1,1,'Relación empresa-versión para ARLEY COMPANY');

/* Procedure structure for procedure `InsertarDatosDesdeTemp` */

/*!50003 DROP PROCEDURE IF EXISTS  `InsertarDatosDesdeTemp` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `InsertarDatosDesdeTemp`()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE v_id_company INT;
    DECLARE v_codigo_company VARCHAR(255);
    DECLARE v_name_company VARCHAR(255);
    DECLARE v_description_company VARCHAR(255);
    DECLARE v_version_id INT;
    DECLARE v_app_id INT;
    DECLARE v_version VARCHAR(255);
    DECLARE v_version_description VARCHAR(255);
    DECLARE v_version_company_id INT;
    DECLARE v_company_id INT;
    DECLARE v_app_code VARCHAR(255);
    DECLARE v_app_name VARCHAR(255);
    DECLARE v_app_description VARCHAR(255);
    
    
    DECLARE CTemporal CURSOR FOR 
        SELECT id_company, codigo_company, name_company, description_company,
               version_id, app_id, version, version_description,
               version_company_id, company_id, app_code, app_name, app_description
        FROM TMP_LLENAR_CAMPOS;
    
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
    OPEN CTemporal;
    
    read_loop: LOOP
        FETCH CTemporal INTO v_id_company, v_codigo_company, v_name_company, v_description_company,
                           v_version_id, v_app_id, v_version, v_version_description,
                           v_version_company_id, v_company_id, v_app_code, v_app_name, v_app_description;
        
        IF done THEN
            LEAVE read_loop;
        END IF;
         
        #se inician los insert
        INSERT IGNORE INTO company (id_company, codigo_company, name_company, description_company)
        VALUES (v_id_company, v_codigo_company, v_name_company, v_description_company);
         
        INSERT IGNORE INTO application (app_id, app_code, app_name, app_description)
        VALUES (v_app_id, v_app_code, v_app_name, v_app_description);
         
        INSERT IGNORE INTO version (version_id, app_id, version, version_description)
        VALUES (v_version_id, v_app_id, v_version, v_version_description);
         
        INSERT IGNORE INTO version_company (version_company_id, company_id, version_id, version_company_description)
        VALUES (v_version_company_id, v_company_id, v_version_id, CONCAT('Relación empresa-versión para ', v_name_company));
        
    END LOOP;
    
    CLOSE CTemporal;
END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
