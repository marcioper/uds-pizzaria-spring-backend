# ************************************************************
# Sequel Pro SQL dump
# Versão 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.6.35)
# Base de Dados: pizzaria
# Tempo de Geração: 2019-08-19 03:25:54 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump da tabela additional
# ------------------------------------------------------------

DROP TABLE IF EXISTS `additional`;

CREATE TABLE `additional` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` decimal(7,2) DEFAULT NULL,
  `preparation_time` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `additional` WRITE;
/*!40000 ALTER TABLE `additional` DISABLE KEYS */;

INSERT INTO `additional` (`id`, `name`, `price`, `preparation_time`)
VALUES
	(1,'Extra bacon',3.00,0),
	(2,'Sem cebola',0.00,0),
	(3,'Borda recheada',5.00,5);

/*!40000 ALTER TABLE `additional` ENABLE KEYS */;
UNLOCK TABLES;


# Dump da tabela flavor
# ------------------------------------------------------------

DROP TABLE IF EXISTS `flavor`;

CREATE TABLE `flavor` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `preparation_time` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `flavor` WRITE;
/*!40000 ALTER TABLE `flavor` DISABLE KEYS */;

INSERT INTO `flavor` (`id`, `name`, `preparation_time`)
VALUES
	(1,'Calabresa',0),
	(2,'Marguerita',0),
	(3,'Portuguesa',5);

/*!40000 ALTER TABLE `flavor` ENABLE KEYS */;
UNLOCK TABLES;


# Dump da tabela ordered
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ordered`;

CREATE TABLE `ordered` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `size_id` int(11) NOT NULL,
  `flavor_id` int(11) NOT NULL,
  `price_total` decimal(7,2) DEFAULT NULL,
  `preparation_time` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `ordered` WRITE;
/*!40000 ALTER TABLE `ordered` DISABLE KEYS */;

INSERT INTO `ordered` (`id`, `user_id`, `product_id`, `size_id`, `flavor_id`, `price_total`, `preparation_time`, `created_at`)
VALUES
	(5,4,1,3,1,48.00,30,'2019-08-19 00:23:56');

/*!40000 ALTER TABLE `ordered` ENABLE KEYS */;
UNLOCK TABLES;


# Dump da tabela ordered_additional
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ordered_additional`;

CREATE TABLE `ordered_additional` (
  `order_id` int(11) NOT NULL,
  `additional_id` int(11) NOT NULL,
  PRIMARY KEY (`order_id`,`additional_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `ordered_additional` WRITE;
/*!40000 ALTER TABLE `ordered_additional` DISABLE KEYS */;

INSERT INTO `ordered_additional` (`order_id`, `additional_id`)
VALUES
	(5,1),
	(5,3);

/*!40000 ALTER TABLE `ordered_additional` ENABLE KEYS */;
UNLOCK TABLES;


# Dump da tabela product
# ------------------------------------------------------------

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;

INSERT INTO `product` (`id`, `name`)
VALUES
	(1,'Pizza');

/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;


# Dump da tabela size
# ------------------------------------------------------------

DROP TABLE IF EXISTS `size`;

CREATE TABLE `size` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` decimal(7,2) DEFAULT NULL,
  `preparation_time` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `size` WRITE;
/*!40000 ALTER TABLE `size` DISABLE KEYS */;

INSERT INTO `size` (`id`, `name`, `price`, `preparation_time`)
VALUES
	(1,'Pequena',20.00,15),
	(2,'Média',30.00,20),
	(3,'Grande',40.00,25);

/*!40000 ALTER TABLE `size` ENABLE KEYS */;
UNLOCK TABLES;


# Dump da tabela user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`id`, `name`, `email`, `phone`)
VALUES
	(4,'Teste','teste@email.com','(111) 111-1111');

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
