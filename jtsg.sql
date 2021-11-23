/*
Navicat MySQL Data Transfer

Source Server         : jtsg
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : jtsg

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2018-05-21 16:44:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `jtsg`
-- ----------------------------
DROP TABLE IF EXISTS `jtsg`;
CREATE TABLE `jtsg` (
  `bh` int(11) NOT NULL,
  `sgsj` char(1) DEFAULT NULL,
  `fhsslx` char(1) DEFAULT NULL,
  `lbqk` char(1) DEFAULT NULL,
  `dlxx` char(1) DEFAULT NULL,
  `lkldlx` char(1) DEFAULT NULL,
  `zhdmwz` char(1) DEFAULT NULL,
  PRIMARY KEY (`bh`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jtsg
-- ----------------------------
