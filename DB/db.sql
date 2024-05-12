/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.6.12-log : Database - db_conventioncenter
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_conventioncenter` /*!40100 DEFAULT CHARACTER SET latin1 */;

/*Table structure for table `tb_agency` */

DROP TABLE IF EXISTS `tb_agency`;

CREATE TABLE `tb_agency` (
  `a_id` int(10) NOT NULL AUTO_INCREMENT,
  `a_name` varchar(20) DEFAULT NULL,
  `a_lice` varchar(20) DEFAULT NULL,
  `a_place` varchar(20) DEFAULT NULL,
  `a_latti` varchar(20) DEFAULT NULL,
  `a_longi` varchar(20) DEFAULT NULL,
  `a_phone` varchar(20) DEFAULT NULL,
  `a_email` varchar(40) DEFAULT NULL,
  `a_status` int(20) DEFAULT NULL,
  PRIMARY KEY (`a_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `tb_agency` */

insert  into `tb_agency`(`a_id`,`a_name`,`a_lice`,`a_place`,`a_latti`,`a_longi`,`a_phone`,`a_email`,`a_status`) values (1,'ganga','ganga333','kochi','10.1107972','76.3502007','9632580744','ganga@gmail.com',1),(2,'vyshnavi','v444','kochi','10.1108877','76.352215','3255988412','vyshnavi@gmail.com',1);

/*Table structure for table `tb_agency_vehicle` */

DROP TABLE IF EXISTS `tb_agency_vehicle`;

CREATE TABLE `tb_agency_vehicle` (
  `v_id` int(10) NOT NULL AUTO_INCREMENT,
  `a_id` int(15) DEFAULT NULL,
  `vehicle` varchar(20) DEFAULT NULL,
  `seats` int(20) DEFAULT NULL,
  `rate` int(20) DEFAULT NULL,
  `v_status` int(10) DEFAULT NULL,
  PRIMARY KEY (`v_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `tb_agency_vehicle` */

insert  into `tb_agency_vehicle`(`v_id`,`a_id`,`vehicle`,`seats`,`rate`,`v_status`) values (1,1,'inova',4,30,1);

/*Table structure for table `tb_center_facility` */

DROP TABLE IF EXISTS `tb_center_facility`;

CREATE TABLE `tb_center_facility` (
  `fac_id` int(10) NOT NULL AUTO_INCREMENT,
  `fac_name` varchar(30) DEFAULT NULL,
  `fac_desc` varchar(150) DEFAULT NULL,
  `fac_rate` int(30) DEFAULT NULL,
  `fac_status` int(10) DEFAULT NULL,
  PRIMARY KEY (`fac_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `tb_center_facility` */

insert  into `tb_center_facility`(`fac_id`,`fac_name`,`fac_desc`,`fac_rate`,`fac_status`) values (1,'royal','Ac room, 150 sqft, 50 seats, car parking',60000,1);

/*Table structure for table `tb_customer` */

DROP TABLE IF EXISTS `tb_customer`;

CREATE TABLE `tb_customer` (
  `c_id` int(10) NOT NULL AUTO_INCREMENT,
  `c_name` varchar(20) DEFAULT NULL,
  `c_age` varchar(20) DEFAULT NULL,
  `c_aadhaar` varchar(20) DEFAULT NULL,
  `c_address` varchar(50) DEFAULT NULL,
  `c_phone` varchar(20) DEFAULT NULL,
  `c_email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `tb_customer` */

insert  into `tb_customer`(`c_id`,`c_name`,`c_age`,`c_aadhaar`,`c_address`,`c_phone`,`c_email`) values (1,'karishma','25','957994238743','kalamasserry','9622580741','kari@gmail.com'),(2,'savinya','22','932258680548','kochi','9632580711','savi@gmail.com');

/*Table structure for table `tb_hotel` */

DROP TABLE IF EXISTS `tb_hotel`;

CREATE TABLE `tb_hotel` (
  `h_id` int(15) NOT NULL AUTO_INCREMENT,
  `h_name` varchar(30) DEFAULT NULL,
  `h_rating` varchar(30) DEFAULT NULL,
  `h_place` varchar(30) DEFAULT NULL,
  `h_latti` varchar(20) DEFAULT NULL,
  `h_longi` varchar(20) DEFAULT NULL,
  `h_phone` varchar(20) DEFAULT NULL,
  `h_email` varchar(50) DEFAULT NULL,
  `h_status` int(10) DEFAULT NULL,
  PRIMARY KEY (`h_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `tb_hotel` */

insert  into `tb_hotel`(`h_id`,`h_name`,`h_rating`,`h_place`,`h_latti`,`h_longi`,`h_phone`,`h_email`,`h_status`) values (1,'Taj','4.0','aluva','10.1107754','76.350238','9632580741','taj@gmail.com',1);

/*Table structure for table `tb_hotel_package` */

DROP TABLE IF EXISTS `tb_hotel_package`;

CREATE TABLE `tb_hotel_package` (
  `p_id` int(10) NOT NULL AUTO_INCREMENT,
  `h_id` int(10) DEFAULT NULL,
  `p_name` varchar(20) DEFAULT NULL,
  `p_desc` varchar(150) DEFAULT NULL,
  `p_rate` int(50) DEFAULT NULL,
  `p_status` int(10) DEFAULT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `tb_hotel_package` */

insert  into `tb_hotel_package`(`p_id`,`h_id`,`p_name`,`p_desc`,`p_rate`,`p_status`) values (1,1,'1 day with food','Single ac room, breakfast, supper and dinner for 2 person',4000,1);

/*Table structure for table `tb_login` */

DROP TABLE IF EXISTS `tb_login`;

CREATE TABLE `tb_login` (
  `l_id` int(10) NOT NULL AUTO_INCREMENT,
  `reg_id` int(15) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `usertype` varchar(20) DEFAULT NULL,
  `status` int(10) DEFAULT NULL,
  PRIMARY KEY (`l_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `tb_login` */

insert  into `tb_login`(`l_id`,`reg_id`,`username`,`password`,`usertype`,`status`) values (1,1,'admin@gmail.com','admin','admin',1),(2,1,'kari@gmail.com','kari','customer',1),(3,2,'savi@gmail.com','savi','customer',1),(4,1,'taj@gmail.com','taj','hotel',1),(5,1,'ganga@gmail.com','ganga','agency',1),(6,2,'vyshnavi@gmail.com','vyshnavi','agency',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
