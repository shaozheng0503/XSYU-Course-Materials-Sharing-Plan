/*

 Navicat Premium Data Transfer



 Source Server         : MySQL8

 Source Server Type    : MySQL

 Source Server Version : 80019

 Source Host           : localhost:3306

 Source Schema         : book



 Target Server Type    : MySQL

 Target Server Version : 80019

 File Encoding         : 65001



 Date: 12/11/2021 21:05:49

*/



SET NAMES utf8mb4;

SET FOREIGN_KEY_CHECKS = 0;



-- ----------------------------

-- Table structure for usrinfo

-- ----------------------------

DROP TABLE IF EXISTS `usrinfo`;

CREATE TABLE `usrinfo`  (

  `age` int NULL DEFAULT NULL,

  `gender` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,

  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL

) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;



-- ----------------------------

-- Records of usrinfo

-- ----------------------------

INSERT INTO `usrinfo` VALUES (77, '男', '刘民');

INSERT INTO `usrinfo` VALUES (45, '女', '王梅');

INSERT INTO `usrinfo` VALUES (27, '女', '美云');

INSERT INTO `usrinfo` VALUES (39, '女', '东风');



SET FOREIGN_KEY_CHECKS = 1;

