/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80031
Source Host           : localhost:3306
Source Database       : express_system

Target Server Type    : MYSQL
Target Server Version : 80031
File Encoding         : 65001

Date: 2022-12-26 18:52:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_admin
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin`;
CREATE TABLE `tb_admin` (
  `id` varchar(20) NOT NULL,
  `password` varchar(30) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Table structure for tb_bill
-- ----------------------------
DROP TABLE IF EXISTS `tb_bill`;
CREATE TABLE `tb_bill` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sender` varchar(30) DEFAULT NULL,
  `receiver` varchar(30) DEFAULT NULL,
  `origin` varchar(30) DEFAULT NULL,
  `destination` varchar(30) DEFAULT NULL,
  `courier_id` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Table structure for tb_courier
-- ----------------------------
DROP TABLE IF EXISTS `tb_courier`;
CREATE TABLE `tb_courier` (
  `id` varchar(20) NOT NULL,
  `password` varchar(30) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
