/*
 Navicat Premium Data Transfer

 Source Server         : 本地3307 Mysql8.0
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3307
 Source Schema         : hs_cm

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 08/12/2024 19:01:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for chstcmcatalog
-- ----------------------------
DROP TABLE IF EXISTS `chstcmcatalog`;
CREATE TABLE `chstcmcatalog`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `level1` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '一级分类',
  `level2` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '二级分类，为空时应填入\"未分类\"',
  `level3` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '三级分类，为空时应填入\"未分类\"',
  `type` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '1' COMMENT '药品类型',
  `drugName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '药品名称',
  `constrain` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '使用限制',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of chstcmcatalog
-- ----------------------------
INSERT INTO `chstcmcatalog` VALUES (1, '内科用药', '解表剂', '辛温解表剂', '甲类', '九味羌活颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (2, '内科用药', '解表剂', '辛温解表剂', '甲类', '正柴胡饮颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (3, '内科用药', '解表剂', '辛温解表剂', '乙类', '九味羌活丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (4, '内科用药', '解表剂', '辛凉解表剂', '甲类', '柴胡注射液', NULL);
INSERT INTO `chstcmcatalog` VALUES (5, '内科用药', '解表剂', '辛凉解表剂', '甲类', '感冒清片', NULL);
INSERT INTO `chstcmcatalog` VALUES (6, '内科用药', '解表剂', '辛凉解表剂', '甲类', '银翘解毒片', NULL);
INSERT INTO `chstcmcatalog` VALUES (7, '内科用药', '解表剂', '辛凉解表剂', '乙类', '柴胡口服液', NULL);
INSERT INTO `chstcmcatalog` VALUES (8, '内科用药', '解表剂', '辛凉解表剂', '乙类', '感冒清胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (9, '内科用药', '解表剂', '辛凉解表剂', '乙类', '瓜霜退热灵胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (10, '内科用药', '解表剂', '辛凉解表剂', '乙类', '羚羊感冒胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (11, '内科用药', '解表剂', '辛凉解表剂', '乙类', '羚羊感冒片', NULL);
INSERT INTO `chstcmcatalog` VALUES (12, '内科用药', '解表剂', '辛凉解表剂', '乙类', '桑菊感冒冲剂', NULL);
INSERT INTO `chstcmcatalog` VALUES (13, '内科用药', '解表剂', '辛凉解表剂', '乙类', '桑菊感冒合剂', NULL);
INSERT INTO `chstcmcatalog` VALUES (14, '内科用药', '解表剂', '辛凉解表剂', '乙类', '桑菊感冒片', NULL);
INSERT INTO `chstcmcatalog` VALUES (15, '内科用药', '解表剂', '辛凉解表剂', '乙类', '银翘解毒颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (16, '内科用药', '解表剂', '辛凉解表剂', '乙类', '银翘解毒丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (17, '内科用药', '解表剂', '辛凉解表剂', '乙类', '重感灵片', NULL);
INSERT INTO `chstcmcatalog` VALUES (18, '内科用药', '解表剂', '表里双解、扶正解表剂', '甲类', '防风通圣丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (19, '内科用药', '解表剂', '表里双解、扶正解表剂', '甲类', '小柴胡冲剂', NULL);
INSERT INTO `chstcmcatalog` VALUES (20, '内科用药', '解表剂', '表里双解、扶正解表剂', '乙类', '表虚感冒颗粒（冲剂）', NULL);
INSERT INTO `chstcmcatalog` VALUES (21, '内科用药', '解表剂', '表里双解、扶正解表剂', '乙类', '小柴胡片', NULL);
INSERT INTO `chstcmcatalog` VALUES (22, '内科用药', '解表剂', '表里双解、扶正解表剂', '乙类', '玉屏风颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (23, '内科用药', '解表剂', '表里双解、扶正解表剂', '乙类', '玉屏风口服液', NULL);
INSERT INTO `chstcmcatalog` VALUES (24, '内科用药', '泻下剂', '未分类', '甲类', '麻仁润肠丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (25, '内科用药', '泻下剂', '未分类', '甲类', '三黄片', NULL);
INSERT INTO `chstcmcatalog` VALUES (26, '内科用药', '泻下剂', '未分类', '乙类', '麻仁胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (27, '内科用药', '泻下剂', '未分类', '乙类', '麻仁丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (28, '内科用药', '泻下剂', '未分类', '乙类', '一清胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (29, '内科用药', '泻下剂', '未分类', '乙类', '一清颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (30, '内科用药', '清热剂', '清热泻火剂', '甲类', '黄连上清丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (31, '内科用药', '清热剂', '清热泻火剂', '甲类', '牛黄解毒片', NULL);
INSERT INTO `chstcmcatalog` VALUES (32, '内科用药', '清热剂', '清热泻火剂', '乙类', '黄连上清片', NULL);
INSERT INTO `chstcmcatalog` VALUES (33, '内科用药', '清热剂', '清热泻火剂', '乙类', '凉膈散', NULL);
INSERT INTO `chstcmcatalog` VALUES (34, '内科用药', '清热剂', '清热泻火剂', '乙类', '牛黄解毒丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (35, '内科用药', '清热剂', '清热泻火剂', '乙类', '牛黄上清丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (36, '内科用药', '清热剂', '清热泻火剂', '乙类', '上清丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (37, '内科用药', '清热剂', '清热泻火剂', '乙类', '新癀片', NULL);
INSERT INTO `chstcmcatalog` VALUES (38, '内科用药', '清热剂', '清热泻火剂', '甲类', '板蓝根颗粒（冲剂）', NULL);
INSERT INTO `chstcmcatalog` VALUES (39, '内科用药', '清热剂', '清热泻火剂', '甲类', '穿心莲片', NULL);
INSERT INTO `chstcmcatalog` VALUES (40, '内科用药', '清热剂', '清热泻火剂', '乙类', '北豆根片', NULL);
INSERT INTO `chstcmcatalog` VALUES (41, '内科用药', '清热剂', '清热泻火剂', '乙类', '冬凌草片', NULL);
INSERT INTO `chstcmcatalog` VALUES (42, '内科用药', '清热剂', '清热泻火剂', '乙类', '清热解毒胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (43, '内科用药', '清热剂', '清热泻火剂', '乙类', '清热解毒口服液', NULL);
INSERT INTO `chstcmcatalog` VALUES (44, '内科用药', '清热剂', '清热泻火剂', '乙类', '清热解毒片', NULL);
INSERT INTO `chstcmcatalog` VALUES (45, '内科用药', '清热剂', '清热泻火剂', '乙类', '清热解毒注射液', NULL);
INSERT INTO `chstcmcatalog` VALUES (46, '内科用药', '清热剂', '清热泻火剂', '乙类', '新清宁片', NULL);
INSERT INTO `chstcmcatalog` VALUES (47, '内科用药', '清热剂', '清热泻火剂', '乙类', '鱼金注射液', NULL);
INSERT INTO `chstcmcatalog` VALUES (48, '内科用药', '清热剂', '清热泻火剂', '乙类', '抗病毒颗粒（含糖型）', NULL);
INSERT INTO `chstcmcatalog` VALUES (49, '内科用药', '清热剂', '清热祛暑剂', '甲类', '保济丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (50, '内科用药', '清热剂', '清热祛暑剂', '甲类', '藿香正气水', NULL);
INSERT INTO `chstcmcatalog` VALUES (51, '内科用药', '清热剂', '清热祛暑剂', '乙类', '保济口服液', NULL);
INSERT INTO `chstcmcatalog` VALUES (52, '内科用药', '清热剂', '清热祛暑剂', '乙类', '甘露消毒丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (53, '内科用药', '清热剂', '清热祛暑剂', '乙类', '藿香正气颗粒（冲剂）', NULL);
INSERT INTO `chstcmcatalog` VALUES (54, '内科用药', '清热剂', '清热祛暑剂', '乙类', '藿香正气胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (55, '内科用药', '清热剂', '清热祛暑剂', '乙类', '藿香正气口服液', NULL);
INSERT INTO `chstcmcatalog` VALUES (56, '内科用药', '清热剂', '清热祛暑剂', '乙类', '六合定中丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (57, '内科用药', '清热剂', '清热祛暑剂', '乙类', '十滴水', NULL);
INSERT INTO `chstcmcatalog` VALUES (58, '内科用药', '清热剂', '清热祛暑剂', '乙类', '十滴水胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (59, '内科用药', '清热剂', '清热祛暑剂', '乙类', '玉枢丹', NULL);
INSERT INTO `chstcmcatalog` VALUES (60, '内科用药', '清热剂', '清热宣肺剂', '甲类', '双黄连片', NULL);
INSERT INTO `chstcmcatalog` VALUES (61, '内科用药', '清热剂', '清热宣肺剂', '甲类', '双黄连注射液[含注射用冻干粉针]', NULL);
INSERT INTO `chstcmcatalog` VALUES (62, '内科用药', '清热剂', '清热宣肺剂', '甲类', '银黄片', NULL);
INSERT INTO `chstcmcatalog` VALUES (63, '内科用药', '清热剂', '清热宣肺剂', '甲类', '鱼腥草注射液', NULL);
INSERT INTO `chstcmcatalog` VALUES (64, '内科用药', '清热剂', '清热宣肺剂', '乙类', '穿琥宁注射液', NULL);
INSERT INTO `chstcmcatalog` VALUES (65, '内科用药', '清热剂', '清热宣肺剂', '乙类', '双黄连颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (66, '内科用药', '清热剂', '清热宣肺剂', '乙类', '双黄连口服液', NULL);
INSERT INTO `chstcmcatalog` VALUES (67, '内科用药', '清热剂', '清热宣肺剂', '乙类', '夏枯草膏', NULL);
INSERT INTO `chstcmcatalog` VALUES (68, '内科用药', '清热剂', '清热宣肺剂', '乙类', '新雪颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (69, '内科用药', '清热剂', '清热宣肺剂', '乙类', '银黄冲剂', NULL);
INSERT INTO `chstcmcatalog` VALUES (70, '内科用药', '清热剂', '清热宣肺剂', '乙类', '银黄口服液', NULL);
INSERT INTO `chstcmcatalog` VALUES (71, '内科用药', '清热剂', '清热宣肺剂', '乙类', '银黄注射液', NULL);
INSERT INTO `chstcmcatalog` VALUES (72, '内科用药', '清热剂', '清热宣肺剂', '乙类', '鱼腥草片', NULL);
INSERT INTO `chstcmcatalog` VALUES (73, '内科用药', '清热剂', '清热宣肺剂', '乙类', '清热灵颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (74, '内科用药', '清热剂', '清肝解毒剂', '甲类', '益肝灵片', NULL);
INSERT INTO `chstcmcatalog` VALUES (75, '内科用药', '清热剂', '清肝解毒剂', '乙类', '澳泰乐颗粒（冲剂）', '*');
INSERT INTO `chstcmcatalog` VALUES (76, '内科用药', '清热剂', '清肝解毒剂', '乙类', '护肝宁片', NULL);
INSERT INTO `chstcmcatalog` VALUES (77, '内科用药', '清热剂', '清肝解毒剂', '乙类', '利肝隆冲剂', NULL);
INSERT INTO `chstcmcatalog` VALUES (78, '内科用药', '清热剂', '清肝解毒剂', '乙类', '利肝隆片', NULL);
INSERT INTO `chstcmcatalog` VALUES (79, '内科用药', '清热剂', '清肝解毒剂', '乙类', '乙肝清热解毒胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (80, '内科用药', '清热剂', '清肝解毒剂', '乙类', '乙肝清热解毒片', '限主治以上医师使用');
INSERT INTO `chstcmcatalog` VALUES (81, '内科用药', '清热剂', '清肝解毒剂', '乙类', '乙肝清热解毒冲剂', NULL);
INSERT INTO `chstcmcatalog` VALUES (82, '内科用药', '清热剂', '清利肝胆湿热剂', '甲类', '龙胆泻肝片', NULL);
INSERT INTO `chstcmcatalog` VALUES (83, '内科用药', '清热剂', '清利肝胆湿热剂', '甲类', '茵栀黄注射液', NULL);
INSERT INTO `chstcmcatalog` VALUES (84, '内科用药', '清热剂', '清利肝胆湿热剂', '乙类', '苦黄注射液', NULL);
INSERT INTO `chstcmcatalog` VALUES (85, '内科用药', '清热剂', '清利肝胆湿热剂', '乙类', '龙胆泻肝胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (86, '内科用药', '清热剂', '清利肝胆湿热剂', '乙类', '龙胆泻肝颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (87, '内科用药', '清热剂', '清利肝胆湿热剂', '乙类', '龙胆泻肝口服液', NULL);
INSERT INTO `chstcmcatalog` VALUES (88, '内科用药', '清热剂', '清利肝胆湿热剂', '乙类', '龙胆泻肝丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (89, '内科用药', '清热剂', '清利肝胆湿热剂', '乙类', '乙肝宁冲剂', NULL);
INSERT INTO `chstcmcatalog` VALUES (90, '内科用药', '清热剂', '清利肝胆湿热剂', '乙类', '利胆排石胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (91, '内科用药', '清热剂', '清利肝胆湿热剂', '乙类', '熊胆丸', '限副主任以上医师使用，限用于急慢性肝炎、胆囊炎、胆结石患者');
INSERT INTO `chstcmcatalog` VALUES (92, '内科用药', '清热剂', '清利肝胆湿热剂', '乙类', '熊胆胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (93, '内科用药', '清热剂', '清利肝胆湿热剂', '乙类', '乙肝养阴活血冲剂', NULL);
INSERT INTO `chstcmcatalog` VALUES (94, '内科用药', '清热剂', '清利肝胆湿热剂', '乙类', '茵陈五苓丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (95, '内科用药', '清热剂', '清利肝胆湿热剂', '乙类', '茵栀黄口服液', NULL);
INSERT INTO `chstcmcatalog` VALUES (96, '内科用药', '清热剂', '清利肠胃湿热剂', '甲类', '复方黄连素片', NULL);
INSERT INTO `chstcmcatalog` VALUES (97, '内科用药', '清热剂', '清利肠胃湿热剂', '甲类', '香连丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (98, '内科用药', '清热剂', '清利肠胃湿热剂', '乙类', '葛根芩连片', NULL);
INSERT INTO `chstcmcatalog` VALUES (99, '内科用药', '清热剂', '清利肠胃湿热剂', '乙类', '葛根芩连微丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (100, '内科用药', '清热剂', '清利肠胃湿热剂', '乙类', '香连片', NULL);
INSERT INTO `chstcmcatalog` VALUES (101, '内科用药', '温里剂', '温中散寒剂', '甲类', '附子理中丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (102, '内科用药', '温里剂', '温中散寒剂', '甲类', '香砂养胃丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (103, '内科用药', '温里剂', '温中散寒剂', '甲类', '桂附理中丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (104, '内科用药', '温里剂', '温中散寒剂', '甲类', '黄芪健中丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (105, '内科用药', '温里剂', '温中散寒剂', '甲类', '理中丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (106, '内科用药', '温里剂', '温中散寒剂', '甲类', '良附丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (107, '内科用药', '温里剂', '温中散寒剂', '甲类', '温胃舒胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (108, '内科用药', '温里剂', '温中散寒剂', '甲类', '温胃舒颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (109, '内科用药', '温里剂', '温中散寒剂', '甲类', '香砂理中丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (110, '内科用药', '温里剂', '温中散寒剂', '甲类', '香砂养胃颗粒（冲剂）', NULL);
INSERT INTO `chstcmcatalog` VALUES (111, '内科用药', '温里剂', '温中散寒剂', '甲类', '小建中冲剂', NULL);
INSERT INTO `chstcmcatalog` VALUES (112, '内科用药', '温里剂', '温中散寒剂', '甲类', '小建中口服液', NULL);
INSERT INTO `chstcmcatalog` VALUES (113, '内科用药', '温里剂', '温中散寒剂', '甲类', '虚寒胃痛胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (114, '内科用药', '温里剂', '温中散寒剂', '甲类', '虚寒胃痛颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (115, '内科用药', '温里剂', '回阳救逆、益气复脉剂', '甲类', '参附注射液', '　　　限用于抢救病人、低血压时使用');
INSERT INTO `chstcmcatalog` VALUES (116, '内科用药', '温里剂', '回阳救逆、益气复脉剂', '甲类', '参麦注射液', NULL);
INSERT INTO `chstcmcatalog` VALUES (117, '内科用药', '温里剂', '回阳救逆、益气复脉剂', '甲类', '生脉注射液', NULL);
INSERT INTO `chstcmcatalog` VALUES (118, '内科用药', '温里剂', '回阳救逆、益气复脉剂', '乙类', '生脉胶囊', '　　　　限用于抢救病人、低血压时使用');
INSERT INTO `chstcmcatalog` VALUES (119, '内科用药', '温里剂', '回阳救逆、益气复脉剂', '乙类', '生脉颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (120, '内科用药', '温里剂', '回阳救逆、益气复脉剂', '乙类', '生脉饮', NULL);
INSERT INTO `chstcmcatalog` VALUES (121, '内科用药', '温里剂', '回阳救逆、益气复脉剂', '乙类', '四逆汤', NULL);
INSERT INTO `chstcmcatalog` VALUES (122, '内科用药', '温里剂', '回阳救逆、益气复脉剂', '乙类', '稳心颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (123, '内科用药', '温里剂', '回阳救逆、益气复脉剂', '乙类', '炙甘草合剂', NULL);
INSERT INTO `chstcmcatalog` VALUES (124, '内科用药', '温里剂', '回阳救逆、益气复脉剂', '乙类', '益气复脉胶囊', '　　　　限用于抢救病人、低血压时使用');
INSERT INTO `chstcmcatalog` VALUES (125, '内科用药', '化痰、止咳、平喘剂', '温化寒痰剂', '甲类', '祛痰止咳冲剂', NULL);
INSERT INTO `chstcmcatalog` VALUES (126, '内科用药', '化痰、止咳、平喘剂', '温化寒痰剂', '甲类', '通宣理肺丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (127, '内科用药', '化痰、止咳、平喘剂', '温化寒痰剂', '乙类', '二陈丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (128, '内科用药', '化痰、止咳、平喘剂', '温化寒痰剂', '乙类', '橘红痰咳颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (129, '内科用药', '化痰、止咳、平喘剂', '温化寒痰剂', '乙类', '通宣理肺胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (130, '内科用药', '化痰、止咳、平喘剂', '温化寒痰剂', '乙类', '通宣理肺口服液', NULL);
INSERT INTO `chstcmcatalog` VALUES (131, '内科用药', '化痰、止咳、平喘剂', '温化寒痰剂', '乙类', '小青龙合剂', NULL);
INSERT INTO `chstcmcatalog` VALUES (132, '内科用药', '化痰、止咳、平喘剂', '温化寒痰剂', '乙类', '小青龙颗粒（冲剂）', NULL);
INSERT INTO `chstcmcatalog` VALUES (133, '内科用药', '化痰、止咳、平喘剂', '温化寒痰剂', '乙类', '杏苏止咳颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (134, '内科用药', '化痰、止咳、平喘剂', '温化寒痰剂', '乙类', '杏苏止咳糖浆', NULL);
INSERT INTO `chstcmcatalog` VALUES (135, '内科用药', '化痰、止咳、平喘剂', '温化寒痰剂', '乙类', '镇咳宁胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (136, '内科用药', '化痰、止咳、平喘剂', '温化寒痰剂', '乙类', '镇咳宁糖浆', NULL);
INSERT INTO `chstcmcatalog` VALUES (137, '内科用药', '化痰、止咳、平喘剂', '清热化痰剂', '甲类', '急支糖浆', NULL);
INSERT INTO `chstcmcatalog` VALUES (138, '内科用药', '化痰、止咳、平喘剂', '清热化痰剂', '甲类', '橘红丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (139, '内科用药', '化痰、止咳、平喘剂', '清热化痰剂', '甲类', '鲜竹沥', NULL);
INSERT INTO `chstcmcatalog` VALUES (140, '内科用药', '化痰、止咳、平喘剂', '清热化痰剂', '乙类', '急支颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (141, '内科用药', '化痰、止咳、平喘剂', '清热化痰剂', '乙类', '橘红冲剂', NULL);
INSERT INTO `chstcmcatalog` VALUES (142, '内科用药', '化痰、止咳、平喘剂', '清热化痰剂', '乙类', '橘红片', NULL);
INSERT INTO `chstcmcatalog` VALUES (143, '内科用药', '化痰、止咳、平喘剂', '清热化痰剂', '乙类', '克咳胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (144, '内科用药', '化痰、止咳、平喘剂', '清热化痰剂', '乙类', '牛黄蛇胆川贝散', NULL);
INSERT INTO `chstcmcatalog` VALUES (145, '内科用药', '化痰、止咳、平喘剂', '清热化痰剂', '乙类', '咳喘宁口服液', '限主治以上医师使用');
INSERT INTO `chstcmcatalog` VALUES (146, '内科用药', '化痰、止咳、平喘剂', '清热化痰剂', '乙类', '牛黄蛇胆川贝液', NULL);
INSERT INTO `chstcmcatalog` VALUES (147, '内科用药', '化痰、止咳、平喘剂', '清热化痰剂', '乙类', '清气化痰丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (148, '内科用药', '化痰、止咳、平喘剂', '清热化痰剂', '乙类', '蛇胆陈皮胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (149, '内科用药', '化痰、止咳、平喘剂', '清热化痰剂', '乙类', '蛇胆陈皮片', NULL);
INSERT INTO `chstcmcatalog` VALUES (150, '内科用药', '化痰、止咳、平喘剂', '清热化痰剂', '乙类', '蛇胆陈皮口服液', NULL);
INSERT INTO `chstcmcatalog` VALUES (151, '内科用药', '化痰、止咳、平喘剂', '清热化痰剂', '乙类', '蛇胆川贝胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (152, '内科用药', '化痰、止咳、平喘剂', '清热化痰剂', '乙类', '蛇胆川贝枇杷膏', NULL);
INSERT INTO `chstcmcatalog` VALUES (153, '内科用药', '化痰、止咳、平喘剂', '清热化痰剂', '乙类', '蛇胆川贝液', NULL);
INSERT INTO `chstcmcatalog` VALUES (154, '内科用药', '化痰、止咳、平喘剂', '清热化痰剂', '乙类', '痰咳净片', NULL);
INSERT INTO `chstcmcatalog` VALUES (155, '内科用药', '化痰、止咳、平喘剂', '清热化痰剂', '乙类', '痰咳净散', NULL);
INSERT INTO `chstcmcatalog` VALUES (156, '内科用药', '化痰、止咳、平喘剂', '润肺化痰剂', '甲类', '蜜炼川贝枇杷膏', NULL);
INSERT INTO `chstcmcatalog` VALUES (157, '内科用药', '化痰、止咳、平喘剂', '润肺化痰剂', '甲类', '强力枇杷露', NULL);
INSERT INTO `chstcmcatalog` VALUES (158, '内科用药', '化痰、止咳、平喘剂', '润肺化痰剂', '甲类', '养阴清肺丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (159, '内科用药', '化痰、止咳、平喘剂', '润肺化痰剂', '乙类', '枇杷止咳胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (160, '内科用药', '化痰、止咳、平喘剂', '润肺化痰剂', '乙类', '止咳口服液', '限主治以上医师使用');
INSERT INTO `chstcmcatalog` VALUES (161, '内科用药', '化痰、止咳、平喘剂', '润肺化痰剂', '乙类', '玄麦甘桔颗粒(冲剂）', NULL);
INSERT INTO `chstcmcatalog` VALUES (162, '内科用药', '化痰、止咳、平喘剂', '润肺化痰剂', '乙类', '养阴清肺膏', NULL);
INSERT INTO `chstcmcatalog` VALUES (163, '内科用药', '化痰、止咳、平喘剂', '润肺化痰剂', '乙类', '养阴清肺口服液', NULL);
INSERT INTO `chstcmcatalog` VALUES (164, '内科用药', '化痰、止咳、平喘剂', '平喘剂', '甲类', '桂龙咳喘宁胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (165, '内科用药', '化痰、止咳、平喘剂', '平喘剂', '甲类', '海珠喘息定片', NULL);
INSERT INTO `chstcmcatalog` VALUES (166, '内科用药', '化痰、止咳、平喘剂', '平喘剂', '甲类', '消咳喘糖浆', NULL);
INSERT INTO `chstcmcatalog` VALUES (167, '内科用药', '化痰、止咳、平喘剂', '平喘剂', '乙类', '定喘膏', NULL);
INSERT INTO `chstcmcatalog` VALUES (168, '内科用药', '化痰、止咳、平喘剂', '平喘剂', '乙类', '复方川贝精片', NULL);
INSERT INTO `chstcmcatalog` VALUES (169, '内科用药', '化痰、止咳、平喘剂', '平喘剂', '乙类', '蛤蚧定喘胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (170, '内科用药', '化痰、止咳、平喘剂', '平喘剂', '乙类', '蛤蚧定喘丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (171, '内科用药', '化痰、止咳、平喘剂', '平喘剂', '乙类', '固本咳喘片', NULL);
INSERT INTO `chstcmcatalog` VALUES (172, '内科用药', '化痰、止咳、平喘剂', '平喘剂', '乙类', '固肾定喘丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (173, '内科用药', '化痰、止咳、平喘剂', '平喘剂', '乙类', '苏子降气丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (174, '内科用药', '化痰、止咳、平喘剂', '平喘剂', '乙类', '消咳喘片', NULL);
INSERT INTO `chstcmcatalog` VALUES (175, '内科用药', '化痰、止咳、平喘剂', '平喘剂', '乙类', '止喘灵注射液', NULL);
INSERT INTO `chstcmcatalog` VALUES (176, '内科用药', '开窍剂', '清热开窍剂', '甲类', '清开灵颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (177, '内科用药', '开窍剂', '清热开窍剂', '甲类', '清开灵注射液', NULL);
INSERT INTO `chstcmcatalog` VALUES (178, '内科用药', '开窍剂', '清热开窍剂', '甲类', '紫雪', NULL);
INSERT INTO `chstcmcatalog` VALUES (179, '内科用药', '开窍剂', '清热开窍剂', '乙类', '安宫牛黄胶囊', '限用于抢救患者时使用');
INSERT INTO `chstcmcatalog` VALUES (180, '内科用药', '开窍剂', '清热开窍剂', '乙类', '安宫牛黄丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (181, '内科用药', '开窍剂', '清热开窍剂', '乙类', '安脑丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (182, '内科用药', '开窍剂', '清热开窍剂', '乙类', '局方至宝丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (183, '内科用药', '开窍剂', '清热开窍剂', '乙类', '牛黄至宝丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (184, '内科用药', '开窍剂', '清热开窍剂', '乙类', '清开灵胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (185, '内科用药', '开窍剂', '清热开窍剂', '乙类', '清开灵口服液', NULL);
INSERT INTO `chstcmcatalog` VALUES (186, '内科用药', '开窍剂', '清热开窍剂', '乙类', '速效牛黄丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (187, '内科用药', '开窍剂', '清热开窍剂', '乙类', '清开灵滴丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (188, '内科用药', '开窍剂', '清热开窍剂', '乙类', '万氏牛黄清心丸', '限用于抢救患者时使用');
INSERT INTO `chstcmcatalog` VALUES (189, '内科用药', '开窍剂', '清热开窍剂', '乙类', '醒脑静注射液', NULL);
INSERT INTO `chstcmcatalog` VALUES (190, '内科用药', '开窍剂', '芳香、化痰开窍剂', '甲类', '礞石滚痰丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (191, '内科用药', '开窍剂', '芳香、化痰开窍剂', '甲类', '苏合香丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (192, '内科用药', '开窍剂', '芳香、化痰开窍剂', '乙类', '十香返生丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (193, '内科用药', '固涩剂', '未分类', '甲类', '缩泉丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (194, '内科用药', '固涩剂', '未分类', '乙类', '金锁固精丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (195, '内科用药', '固涩剂', '未分类', '乙类', '固肠止泻丸（原名结肠炎丸）', NULL);
INSERT INTO `chstcmcatalog` VALUES (196, '内科用药', '固涩剂', '未分类', '乙类', '五味子颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (197, '内科用药', '扶正剂', '补气剂', '甲类', '补中益气丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (198, '内科用药', '扶正剂', '补气剂', '甲类', '参苓白术丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (199, '内科用药', '扶正剂', '补气剂', '甲类', '香砂六君丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (200, '内科用药', '扶正剂', '补气剂', '乙类', '补脾益肠丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (201, '内科用药', '扶正剂', '补气剂', '乙类', '补中益气合剂', NULL);
INSERT INTO `chstcmcatalog` VALUES (202, '内科用药', '扶正剂', '补气剂', '乙类', '补中益气口服液', NULL);
INSERT INTO `chstcmcatalog` VALUES (203, '内科用药', '扶正剂', '补气剂', '乙类', '参苓白术散', NULL);
INSERT INTO `chstcmcatalog` VALUES (204, '内科用药', '扶正剂', '补气剂', '乙类', '刺五加片', NULL);
INSERT INTO `chstcmcatalog` VALUES (205, '内科用药', '扶正剂', '补气剂', '乙类', '刺五加注射液', '限副主任以上医师使用，限用于脑血管患者');
INSERT INTO `chstcmcatalog` VALUES (206, '内科用药', '扶正剂', '补气剂', '乙类', '六君子丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (207, '内科用药', '扶正剂', '补气剂', '乙类', '人参健脾丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (208, '内科用药', '扶正剂', '补气剂', '乙类', '四君子丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (209, '内科用药', '扶正剂', '补气剂', '乙类', '胃复春片', NULL);
INSERT INTO `chstcmcatalog` VALUES (210, '内科用药', '扶正剂', '补气剂', '乙类', '香砂平胃丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (211, '内科用药', '扶正剂', '补气剂', '乙类', '养胃颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (212, '内科用药', '扶正剂', '补气剂', '乙类', '养胃舒胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (213, '内科用药', '扶正剂', '补气剂', '乙类', '养胃舒颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (214, '内科用药', '扶正剂', '补气剂', '乙类', '黄芪颗粒', '限主治以上医师使用，限用于放疗、化疗肿瘤患者');
INSERT INTO `chstcmcatalog` VALUES (215, '内科用药', '扶正剂', '补气剂', '乙类', '参芪降糖胶囊', '限主治以上医师使用，限用于糖尿病患者');
INSERT INTO `chstcmcatalog` VALUES (216, '内科用药', '扶正剂', '补气剂', '乙类', '刺五加脑灵液', '限二甲以上医院、主治以上医师使用');
INSERT INTO `chstcmcatalog` VALUES (217, '内科用药', '扶正剂', '补气剂', '乙类', '枫蓼肠胃康颗粒剂', NULL);
INSERT INTO `chstcmcatalog` VALUES (218, '内科用药', '扶正剂', '养血剂', '甲类', '当归注射液', NULL);
INSERT INTO `chstcmcatalog` VALUES (219, '内科用药', '扶正剂', '养血剂', '甲类', '归脾丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (220, '内科用药', '扶正剂', '养血剂', '乙类', '八珍颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (221, '内科用药', '扶正剂', '养血剂', '乙类', '八珍丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (222, '内科用药', '扶正剂', '养血剂', '乙类', '当归补血膏', NULL);
INSERT INTO `chstcmcatalog` VALUES (223, '内科用药', '扶正剂', '养血剂', '乙类', '当归补血丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (224, '内科用药', '扶正剂', '养血剂', '乙类', '当归冲剂', NULL);
INSERT INTO `chstcmcatalog` VALUES (225, '内科用药', '扶正剂', '养血剂', '乙类', '当归膏', NULL);
INSERT INTO `chstcmcatalog` VALUES (226, '内科用药', '扶正剂', '养血剂', '乙类', '当归丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (227, '内科用药', '扶正剂', '养血剂', '乙类', '归脾合剂', NULL);
INSERT INTO `chstcmcatalog` VALUES (228, '内科用药', '扶正剂', '养血剂', '乙类', '人参养荣丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (229, '内科用药', '扶正剂', '养血剂', '乙类', '四物合剂', NULL);
INSERT INTO `chstcmcatalog` VALUES (230, '内科用药', '扶正剂', '养血剂', '乙类', '养血饮口服液', NULL);
INSERT INTO `chstcmcatalog` VALUES (231, '内科用药', '扶正剂', '滋阴剂', '甲类', '六味地黄丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (232, '内科用药', '扶正剂', '滋阴剂', '甲类', '杞菊地黄丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (233, '内科用药', '扶正剂', '滋阴剂', '乙类', '补肾固齿丸', '限主治以上医师使用，增大个人自负比例');
INSERT INTO `chstcmcatalog` VALUES (234, '内科用药', '扶正剂', '滋阴剂', '乙类', '大补阴丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (235, '内科用药', '扶正剂', '滋阴剂', '乙类', '六味地黄颗粒（冲剂）', NULL);
INSERT INTO `chstcmcatalog` VALUES (236, '内科用药', '扶正剂', '滋阴剂', '乙类', '六味地黄口服液', NULL);
INSERT INTO `chstcmcatalog` VALUES (237, '内科用药', '扶正剂', '滋阴剂', '乙类', '六味地黄片', NULL);
INSERT INTO `chstcmcatalog` VALUES (238, '内科用药', '扶正剂', '滋阴剂', '乙类', '麦味地黄口服液', NULL);
INSERT INTO `chstcmcatalog` VALUES (239, '内科用药', '扶正剂', '滋阴剂', '乙类', '麦味地黄丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (240, '内科用药', '扶正剂', '滋阴剂', '乙类', '杞菊地黄胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (241, '内科用药', '扶正剂', '滋阴剂', '乙类', '杞菊地黄口服液', NULL);
INSERT INTO `chstcmcatalog` VALUES (242, '内科用药', '扶正剂', '滋阴剂', '乙类', '杞菊地黄片', NULL);
INSERT INTO `chstcmcatalog` VALUES (243, '内科用药', '扶正剂', '滋阴剂', '乙类', '知柏地黄丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (244, '内科用药', '扶正剂', '滋阴剂', '乙类', '左归丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (245, '内科用药', '扶正剂', '滋阴剂', '甲类', '糖脉康颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (246, '内科用药', '扶正剂', '滋阴剂', '甲类', '消渴丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (247, '内科用药', '扶正剂', '滋阴剂', '乙类', '百合固金口服液', NULL);
INSERT INTO `chstcmcatalog` VALUES (248, '内科用药', '扶正剂', '滋阴剂', '乙类', '玉泉胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (249, '内科用药', '扶正剂', '滋阴剂', '乙类', '玉泉丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (250, '内科用药', '扶正剂', '滋阴剂', '乙类', '滋心阴口服液', NULL);
INSERT INTO `chstcmcatalog` VALUES (251, '内科用药', '扶正剂', '滋阴剂', '乙类', '金水宝胶囊', '限二甲以上医院、副主任以上医师使用，限用于慢性支气管炎、慢性肾功能衰竭');
INSERT INTO `chstcmcatalog` VALUES (252, '内科用药', '扶正剂', '滋阴剂', '乙类', '百令胶囊', '限二甲以上医院、副主任以上医师使用');
INSERT INTO `chstcmcatalog` VALUES (253, '内科用药', '扶正剂', '滋阴剂', '乙类', '骨痨敌注射液', NULL);
INSERT INTO `chstcmcatalog` VALUES (254, '内科用药', '扶正剂', '滋阴剂', '乙类', '心荣胶囊', '限二甲以上医院、副主任以上医师使用，限用于心肌炎患者');
INSERT INTO `chstcmcatalog` VALUES (255, '内科用药', '扶正剂', '温阳剂', '甲类', '金匮肾气丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (256, '内科用药', '扶正剂', '温阳剂', '甲类', '四神丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (257, '内科用药', '扶正剂', '温阳剂', '乙类', '桂附地黄胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (258, '内科用药', '扶正剂', '温阳剂', '乙类', '桂附地黄丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (259, '内科用药', '扶正剂', '温阳剂', '乙类', '四神片', NULL);
INSERT INTO `chstcmcatalog` VALUES (260, '内科用药', '扶正剂', '温阳剂', '乙类', '右归丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (261, '内科用药', '安神剂', '未分类', '甲类', '柏子养心丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (262, '内科用药', '安神剂', '未分类', '甲类', '天王补心丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (263, '内科用药', '安神剂', '未分类', '乙类', '安神补脑液', NULL);
INSERT INTO `chstcmcatalog` VALUES (264, '内科用药', '安神剂', '未分类', '乙类', '安神补心丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (265, '内科用药', '安神剂', '未分类', '乙类', '柏子养心片', NULL);
INSERT INTO `chstcmcatalog` VALUES (266, '内科用药', '安神剂', '未分类', '乙类', '活力苏口服液', NULL);
INSERT INTO `chstcmcatalog` VALUES (267, '内科用药', '安神剂', '未分类', '乙类', '甜梦胶囊', '*');
INSERT INTO `chstcmcatalog` VALUES (268, '内科用药', '安神剂', '未分类', '乙类', '养血安神片', NULL);
INSERT INTO `chstcmcatalog` VALUES (269, '内科用药', '安神剂', '未分类', '乙类', '养血安神糖浆', NULL);
INSERT INTO `chstcmcatalog` VALUES (270, '内科用药', '安神剂', '未分类', '乙类', '养血安神丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (271, '内科用药', '安神剂', '未分类', '乙类', '朱砂安神丸', '*');
INSERT INTO `chstcmcatalog` VALUES (272, '内科用药', '安神剂', '未分类', '甲类', '槐角丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (273, '内科用药', '安神剂', '未分类', '甲类', '三七片', NULL);
INSERT INTO `chstcmcatalog` VALUES (274, '内科用药', '安神剂', '未分类', '乙类', '二至丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (275, '内科用药', '安神剂', '未分类', '乙类', '三七胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (276, '内科用药', '安神剂', '未分类', '乙类', '三七血伤宁胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (277, '内科用药', '安神剂', '未分类', '乙类', '十灰散', NULL);
INSERT INTO `chstcmcatalog` VALUES (278, '内科用药', '安神剂', '未分类', '乙类', '十灰丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (279, '内科用药', '祛瘀剂', '益气活血', '甲类', '麝香保心丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (280, '内科用药', '祛瘀剂', '益气活血', '甲类', '速效救心丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (281, '内科用药', '祛瘀剂', '益气活血', '甲类', '通心络胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (282, '内科用药', '祛瘀剂', '益气活血', '乙类', '补心气口服液', NULL);
INSERT INTO `chstcmcatalog` VALUES (283, '内科用药', '祛瘀剂', '益气活血', '乙类', '黄杨宁片', NULL);
INSERT INTO `chstcmcatalog` VALUES (284, '内科用药', '祛瘀剂', '益气活血', '乙类', '山海丹胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (285, '内科用药', '祛瘀剂', '益气活血', '乙类', '舒心口服液', NULL);
INSERT INTO `chstcmcatalog` VALUES (286, '内科用药', '祛瘀剂', '益气活血', '乙类', '心宝丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (287, '内科用药', '祛瘀剂', '益气活血', '乙类', '心通口服液', NULL);
INSERT INTO `chstcmcatalog` VALUES (288, '内科用药', '祛瘀剂', '益气活血', '乙类', '养心氏片', NULL);
INSERT INTO `chstcmcatalog` VALUES (289, '内科用药', '祛瘀剂', '益气活血', '乙类', '银杏叶片', NULL);
INSERT INTO `chstcmcatalog` VALUES (290, '内科用药', '祛瘀剂', '益气活血', '乙类', '银杏叶胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (291, '内科用药', '祛瘀剂', '养血活血', '甲类', '复方丹参滴丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (292, '内科用药', '祛瘀剂', '养血活血', '甲类', '香丹注射液（复方丹参注射液）', NULL);
INSERT INTO `chstcmcatalog` VALUES (293, '内科用药', '祛瘀剂', '养血活血', '甲类', '血府逐瘀口服液', NULL);
INSERT INTO `chstcmcatalog` VALUES (294, '内科用药', '祛瘀剂', '养血活血', '乙类', '丹参片', NULL);
INSERT INTO `chstcmcatalog` VALUES (295, '内科用药', '祛瘀剂', '养血活血', '乙类', '丹参注射液', NULL);
INSERT INTO `chstcmcatalog` VALUES (296, '内科用药', '祛瘀剂', '养血活血', '乙类', '复方丹参颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (297, '内科用药', '祛瘀剂', '养血活血', '乙类', '复方丹参片', NULL);
INSERT INTO `chstcmcatalog` VALUES (298, '内科用药', '祛瘀剂', '养血活血', '乙类', '冠脉宁片', NULL);
INSERT INTO `chstcmcatalog` VALUES (299, '内科用药', '祛瘀剂', '养血活血', '乙类', '冠心丹参片', NULL);
INSERT INTO `chstcmcatalog` VALUES (300, '内科用药', '祛瘀剂', '养血活血', '乙类', '乐脉颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (301, '内科用药', '祛瘀剂', '养血活血', '乙类', '利脑心胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (302, '内科用药', '祛瘀剂', '养血活血', '乙类', '心可舒胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (303, '内科用药', '祛瘀剂', '养血活血', '乙类', '心可舒片', NULL);
INSERT INTO `chstcmcatalog` VALUES (304, '内科用药', '祛瘀剂', '养血活血', '乙类', '血府逐瘀胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (305, '内科用药', '祛瘀剂', '化瘀宽胸剂', '甲类', '地奥心血康胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (306, '内科用药', '祛瘀剂', '化瘀宽胸剂', '甲类', '冠心苏合丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (307, '内科用药', '祛瘀剂', '化瘀宽胸剂', '乙类', '冠心苏合胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (308, '内科用药', '祛瘀剂', '化瘀宽胸剂', '乙类', '活血通脉胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (309, '内科用药', '祛瘀剂', '化瘀宽胸剂', '乙类', '活血通脉片', NULL);
INSERT INTO `chstcmcatalog` VALUES (310, '内科用药', '祛瘀剂', '化瘀宽胸剂', '乙类', '心可宁胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (311, '内科用药', '祛瘀剂', '化瘀宽胸剂', '乙类', '心脉通片', NULL);
INSERT INTO `chstcmcatalog` VALUES (312, '内科用药', '祛瘀剂', '化瘀宽胸剂', '乙类', '心脑舒通胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (313, '内科用药', '祛瘀剂', '化瘀宽胸剂', '乙类', '心血宁片', NULL);
INSERT INTO `chstcmcatalog` VALUES (314, '内科用药', '祛瘀剂', '化瘀宽胸剂', '乙类', '愈风宁心片', NULL);
INSERT INTO `chstcmcatalog` VALUES (315, '内科用药', '祛瘀剂', '化瘀通脉', '甲类', '灯盏花素片', NULL);
INSERT INTO `chstcmcatalog` VALUES (316, '内科用药', '祛瘀剂', '化瘀通脉', '甲类', '脉络宁注射液', NULL);
INSERT INTO `chstcmcatalog` VALUES (317, '内科用药', '祛瘀剂', '化瘀通脉', '甲类', '血栓心脉宁胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (318, '内科用药', '祛瘀剂', '化瘀通脉', '甲类', '血塞通注射液', NULL);
INSERT INTO `chstcmcatalog` VALUES (319, '内科用药', '祛瘀剂', '化瘀通脉', '乙类', '灯盏细辛注射液', NULL);
INSERT INTO `chstcmcatalog` VALUES (320, '内科用药', '祛瘀剂', '化瘀通脉', '乙类', '脑安胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (321, '内科用药', '祛瘀剂', '化瘀通脉', '乙类', '脑血康胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (322, '内科用药', '祛瘀剂', '化瘀通脉', '乙类', '脑血康口服液', NULL);
INSERT INTO `chstcmcatalog` VALUES (323, '内科用药', '祛瘀剂', '化瘀通脉', '乙类', '脑血康片', NULL);
INSERT INTO `chstcmcatalog` VALUES (324, '内科用药', '祛瘀剂', '化瘀通脉', '乙类', '消栓通络胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (325, '内科用药', '祛瘀剂', '化瘀通脉', '乙类', '消栓通络片', NULL);
INSERT INTO `chstcmcatalog` VALUES (326, '内科用药', '祛瘀剂', '化瘀通脉', '乙类', '消栓再造丸', '*');
INSERT INTO `chstcmcatalog` VALUES (327, '内科用药', '祛瘀剂', '化瘀通脉', '乙类', '心脑康胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (328, '内科用药', '祛瘀剂', '化瘀通脉', '乙类', '血塞通片', NULL);
INSERT INTO `chstcmcatalog` VALUES (329, '内科用药', '祛瘀剂', '化瘀通脉', '乙类', '血栓通注射液', NULL);
INSERT INTO `chstcmcatalog` VALUES (330, '内科用药', '祛瘀剂', '化瘀通脉', '乙类', '益心胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (331, '内科用药', '祛瘀剂', '化瘀通脉', '乙类', '益心丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (332, '内科用药', '理气剂', '疏肝解郁剂', '甲类', '丹栀逍遥丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (333, '内科用药', '理气剂', '疏肝解郁剂', '甲类', '逍遥丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (334, '内科用药', '理气剂', '疏肝解郁剂', '乙类', '柴胡舒肝丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (335, '内科用药', '理气剂', '疏肝解郁剂', '乙类', '加味逍遥丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (336, '内科用药', '理气剂', '疏肝解郁剂', '乙类', '舒肝片', NULL);
INSERT INTO `chstcmcatalog` VALUES (337, '内科用药', '理气剂', '疏肝解郁剂', '乙类', '舒肝丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (338, '内科用药', '理气剂', '疏肝解郁剂', '乙类', '四逆散', NULL);
INSERT INTO `chstcmcatalog` VALUES (339, '内科用药', '理气剂', '疏肝解郁剂', '乙类', '逍遥颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (340, '内科用药', '理气剂', '疏肝解郁剂', '乙类', '乙肝益气解郁冲剂', NULL);
INSERT INTO `chstcmcatalog` VALUES (341, '内科用药', '理气剂', '疏肝解郁剂', '乙类', '越鞠丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (342, '内科用药', '理气剂', '疏肝和胃剂', '甲类', '气滞胃痛颗粒（冲剂）', NULL);
INSERT INTO `chstcmcatalog` VALUES (343, '内科用药', '理气剂', '疏肝和胃剂', '甲类', '胃苏颗粒（冲剂）', NULL);
INSERT INTO `chstcmcatalog` VALUES (344, '内科用药', '理气剂', '疏肝和胃剂', '甲类', '元胡止痛片', NULL);
INSERT INTO `chstcmcatalog` VALUES (345, '内科用药', '理气剂', '疏肝和胃剂', '乙类', '复方陈香胃片', NULL);
INSERT INTO `chstcmcatalog` VALUES (346, '内科用药', '理气剂', '疏肝和胃剂', '乙类', '气滞胃痛片', NULL);
INSERT INTO `chstcmcatalog` VALUES (347, '内科用药', '理气剂', '疏肝和胃剂', '乙类', '三九胃泰胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (348, '内科用药', '理气剂', '疏肝和胃剂', '乙类', '三九胃泰颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (349, '内科用药', '理气剂', '疏肝和胃剂', '乙类', '乌梅丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (350, '内科用药', '理气剂', '疏肝和胃剂', '乙类', '香砂枳术丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (351, '内科用药', '理气剂', '疏肝和胃剂', '乙类', '元胡止痛胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (352, '内科用药', '理气剂', '疏肝和胃剂', '乙类', '元胡止痛颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (353, '内科用药', '理气剂', '疏肝和胃剂', '乙类', '元胡止痛口服液', NULL);
INSERT INTO `chstcmcatalog` VALUES (354, '内科用药', '理气剂', '疏肝和胃剂', '乙类', '枳术丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (355, '内科用药', '理气剂', '疏肝和胃剂', '乙类', '左金丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (356, '内科用药', '消导剂', '疏散外风', '甲类', '保和丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (357, '内科用药', '消导剂', '疏散外风', '乙类', '沉香化滞丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (358, '内科用药', '消导剂', '疏散外风', '乙类', '开胸顺气丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (359, '内科用药', '消导剂', '疏散外风', '乙类', '摩罗丹', NULL);
INSERT INTO `chstcmcatalog` VALUES (360, '内科用药', '消导剂', '疏散外风', '乙类', '木香槟榔丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (361, '内科用药', '消导剂', '疏散外风', '乙类', '木香顺气丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (362, '内科用药', '消导剂', '疏散外风', '乙类', '越鞠保和丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (363, '内科用药', '消导剂', '疏散外风', '乙类', '枳实导滞丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (364, '内科用药', '治风剂', '疏散外风', '甲类', '川芎茶调冲剂', NULL);
INSERT INTO `chstcmcatalog` VALUES (365, '内科用药', '治风剂', '疏散外风', '甲类', '正天丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (366, '内科用药', '治风剂', '疏散外风', '乙类', '川芎茶调口服液', NULL);
INSERT INTO `chstcmcatalog` VALUES (367, '内科用药', '治风剂', '疏散外风', '乙类', '通天口服液', NULL);
INSERT INTO `chstcmcatalog` VALUES (368, '内科用药', '治风剂', '疏散外风', '乙类', '镇脑宁胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (369, '内科用药', '治风剂', '平肝熄风', '甲类', '牛黄降压丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (370, '内科用药', '治风剂', '平肝熄风', '甲类', '松龄血脉康胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (371, '内科用药', '治风剂', '平肝熄风', '乙类', '复方罗布麻冲剂', NULL);
INSERT INTO `chstcmcatalog` VALUES (372, '内科用药', '治风剂', '平肝熄风', '乙类', '脑立清胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (373, '内科用药', '治风剂', '平肝熄风', '乙类', '脑立清丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (374, '内科用药', '治风剂', '平肝熄风', '乙类', '牛黄降压片', NULL);
INSERT INTO `chstcmcatalog` VALUES (375, '内科用药', '治风剂', '平肝熄风', '乙类', '天麻钩藤颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (376, '内科用药', '治风剂', '平肝熄风', '乙类', '养血清脑颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (377, '内科用药', '治风剂', '祛风通络', '甲类', '华佗再造丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (378, '内科用药', '治风剂', '祛风通络', '甲类', '人参再造丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (379, '内科用药', '治风剂', '祛风通络', '乙类', '大活络丸', '*');
INSERT INTO `chstcmcatalog` VALUES (380, '内科用药', '治风剂', '祛风通络', '乙类', '活络丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (381, '内科用药', '治风剂', '祛风通络', '乙类', '小活络丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (382, '内科用药', '治风剂', '祛风通络', '乙类', '再造丸', '*');
INSERT INTO `chstcmcatalog` VALUES (383, '内科用药', '治风剂', '祛风通络', '乙类', '中风回春片', NULL);
INSERT INTO `chstcmcatalog` VALUES (384, '内科用药', '治风剂', '祛风通络', '乙类', '中风回春丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (385, '内科用药', '祛湿剂', '散寒除湿通痹', '甲类', '风湿液', NULL);
INSERT INTO `chstcmcatalog` VALUES (386, '内科用药', '祛湿剂', '散寒除湿通痹', '甲类', '追风透骨丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (387, '内科用药', '祛湿剂', '散寒除湿通痹', '乙类', '风湿骨痛胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (388, '内科用药', '祛湿剂', '散寒除湿通痹', '乙类', '寒湿痹颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (389, '内科用药', '祛湿剂', '散寒除湿通痹', '乙类', '寒湿痹片', NULL);
INSERT INTO `chstcmcatalog` VALUES (390, '内科用药', '祛湿剂', '散寒除湿通痹', '乙类', '痛风定胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (391, '内科用药', '祛湿剂', '散寒除湿通痹', '乙类', '腰痛宁胶囊', '*');
INSERT INTO `chstcmcatalog` VALUES (392, '内科用药', '祛湿剂', '散寒除湿通痹', '乙类', '追风透骨片', NULL);
INSERT INTO `chstcmcatalog` VALUES (393, '内科用药', '祛湿剂', '清热除湿通痹', '甲类', '雷公藤片', NULL);
INSERT INTO `chstcmcatalog` VALUES (394, '内科用药', '祛湿剂', '清热除湿通痹', '乙类', '二妙丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (395, '内科用药', '祛湿剂', '清热除湿通痹', '乙类', '湿热痹颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (396, '内科用药', '祛湿剂', '清热除湿通痹', '乙类', '湿热痹片', NULL);
INSERT INTO `chstcmcatalog` VALUES (397, '内科用药', '祛湿剂', '清热除湿通痹', '乙类', '四妙丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (398, '内科用药', '祛湿剂', '清热除湿通痹', '乙类', '正清风痛宁片', NULL);
INSERT INTO `chstcmcatalog` VALUES (399, '内科用药', '祛湿剂', '清热除湿通痹', '乙类', '正清风痛宁注射液', NULL);
INSERT INTO `chstcmcatalog` VALUES (400, '内科用药', '祛湿剂', '补肝益肾、祛风除湿通痹', '甲类', '痹冲剂', NULL);
INSERT INTO `chstcmcatalog` VALUES (401, '内科用药', '祛湿剂', '补肝益肾、祛风除湿通痹', '甲类', '壮骨关节丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (402, '内科用药', '祛湿剂', '补肝益肾、祛风除湿通痹', '乙类', '独活寄生合剂', NULL);
INSERT INTO `chstcmcatalog` VALUES (403, '内科用药', '祛湿剂', '补肝益肾、祛风除湿通痹', '乙类', '杜仲冲剂', NULL);
INSERT INTO `chstcmcatalog` VALUES (404, '内科用药', '祛湿剂', '补肝益肾、祛风除湿通痹', '乙类', '强力天麻杜仲胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (406, '内科用药', '祛湿剂', '补肝益肾、祛风除湿通痹', '乙类', '壮腰健肾丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (407, '内科用药', '祛湿剂', '补肝益肾、祛风除湿通痹', '乙类', '祖师麻片', NULL);
INSERT INTO `chstcmcatalog` VALUES (408, '内科用药', '祛湿剂', '消肿利水', '甲类', '济生肾气丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (409, '内科用药', '祛湿剂', '消肿利水', '甲类', '肾炎四味片', NULL);
INSERT INTO `chstcmcatalog` VALUES (410, '内科用药', '祛湿剂', '消肿利水', '乙类', '肾炎消肿片', NULL);
INSERT INTO `chstcmcatalog` VALUES (411, '内科用药', '祛湿剂', '消肿利水', '乙类', '五苓片', NULL);
INSERT INTO `chstcmcatalog` VALUES (412, '内科用药', '祛湿剂', '消肿利水', '乙类', '五苓散', NULL);
INSERT INTO `chstcmcatalog` VALUES (413, '内科用药', '祛湿剂', '消肿利水', '乙类', '舟车丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (414, '内科用药', '祛湿剂', '清热利湿通淋', '甲类', '普乐安胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (415, '内科用药', '祛湿剂', '清热利湿通淋', '甲类', '三金片', NULL);
INSERT INTO `chstcmcatalog` VALUES (416, '内科用药', '祛湿剂', '清热利湿通淋', '乙类', '八正合剂', NULL);
INSERT INTO `chstcmcatalog` VALUES (418, '内科用药', '祛湿剂', '清热利湿通淋', '乙类', '复方石淋通片', NULL);
INSERT INTO `chstcmcatalog` VALUES (419, '内科用药', '祛湿剂', '清热利湿通淋', '乙类', '癃闭舒胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (420, '内科用药', '祛湿剂', '清热利湿通淋', '乙类', '癃清片', NULL);
INSERT INTO `chstcmcatalog` VALUES (421, '内科用药', '祛湿剂', '清热利湿通淋', '乙类', '尿感宁冲剂', NULL);
INSERT INTO `chstcmcatalog` VALUES (422, '内科用药', '祛湿剂', '清热利湿通淋', '乙类', '尿塞通片', NULL);
INSERT INTO `chstcmcatalog` VALUES (423, '内科用药', '祛湿剂', '清热利湿通淋', '乙类', '普乐安片', NULL);
INSERT INTO `chstcmcatalog` VALUES (424, '内科用药', '祛湿剂', '清热利湿通淋', '乙类', '前列通片', NULL);
INSERT INTO `chstcmcatalog` VALUES (425, '内科用药', '祛湿剂', '清热利湿通淋', '乙类', '三金胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (426, '内科用药', '祛湿剂', '清热利湿通淋', '乙类', '野菊花栓', NULL);
INSERT INTO `chstcmcatalog` VALUES (427, '内科用药', '其他', '降脂剂', '甲类', '降脂灵颗粒', '*');
INSERT INTO `chstcmcatalog` VALUES (428, '内科用药', '其他', '降脂剂', '甲类', '降脂灵片', '*');
INSERT INTO `chstcmcatalog` VALUES (429, '内科用药', '其他', '降脂剂', '甲类', '绞股蓝总苷胶囊', '*');
INSERT INTO `chstcmcatalog` VALUES (430, '内科用药', '其他', '降脂剂', '甲类', '绞股蓝总苷片', '*');
INSERT INTO `chstcmcatalog` VALUES (431, '内科用药', '其他', '降脂剂', '甲类', '血脂康胶囊', '*');
INSERT INTO `chstcmcatalog` VALUES (432, '内科用药', '其他', '降脂剂', '甲类', '脂必妥片', '*');
INSERT INTO `chstcmcatalog` VALUES (433, '内科用药', '其他', '降脂剂', '甲类', '脂必妥胶囊', '*');
INSERT INTO `chstcmcatalog` VALUES (434, '内科用药', '其他', '肿瘤用药', '乙类', '平消胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (435, '内科用药', '其他', '肿瘤用药', '甲类', '华蟾素注射液', NULL);
INSERT INTO `chstcmcatalog` VALUES (436, '内科用药', '其他', '肿瘤用药', '甲类', '槐耳颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (437, '内科用药', '其他', '肿瘤用药', '甲类', '康莱特注射液', NULL);
INSERT INTO `chstcmcatalog` VALUES (438, '内科用药', '其他', '肿瘤用药', '甲类', '平消片', NULL);
INSERT INTO `chstcmcatalog` VALUES (439, '内科用药', '其他', '肿瘤放化疗辅助用药', '乙类', '参芪片[指11味组方]', '*');
INSERT INTO `chstcmcatalog` VALUES (440, '内科用药', '其他', '肿瘤放化疗辅助用药', '乙类', '复方皂矾丸', '*');
INSERT INTO `chstcmcatalog` VALUES (441, '内科用药', '其他', '肿瘤放化疗辅助用药', '乙类', '黄芪注射液', '*');
INSERT INTO `chstcmcatalog` VALUES (442, '内科用药', '其他', '肿瘤放化疗辅助用药', '乙类', '螺旋藻胶囊', '*');
INSERT INTO `chstcmcatalog` VALUES (443, '内科用药', '其他', '肿瘤放化疗辅助用药', '乙类', '益血生胶囊', '*');
INSERT INTO `chstcmcatalog` VALUES (444, '内科用药', '其他', '肿瘤放化疗辅助用药', '乙类', '贞芪扶正胶囊', '*');
INSERT INTO `chstcmcatalog` VALUES (445, '内科用药', '其他', '肿瘤放化疗辅助用药', '乙类', '贞芪扶正颗粒', '*');
INSERT INTO `chstcmcatalog` VALUES (446, '内科用药', '其他', '肿瘤放化疗辅助用药', '乙类', '猪苓多糖注射液', '*');
INSERT INTO `chstcmcatalog` VALUES (447, '外科用药', '清热剂', '清利肝胆剂', '甲类', '结石通片', NULL);
INSERT INTO `chstcmcatalog` VALUES (448, '外科用药', '清热剂', '清利肝胆剂', '甲类', '消炎利胆片', NULL);
INSERT INTO `chstcmcatalog` VALUES (449, '外科用药', '清热剂', '清利肝胆剂', '乙类', '胆宁片', NULL);
INSERT INTO `chstcmcatalog` VALUES (450, '外科用药', '清热剂', '清利肝胆剂', '乙类', '胆石通胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (451, '外科用药', '清热剂', '清利肝胆剂', '乙类', '胆舒胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (452, '外科用药', '清热剂', '清利肝胆剂', '乙类', '复方胆通片', NULL);
INSERT INTO `chstcmcatalog` VALUES (453, '外科用药', '清热剂', '清利肝胆剂', '乙类', '金胆片', NULL);
INSERT INTO `chstcmcatalog` VALUES (454, '外科用药', '清热剂', '清利肝胆剂', '乙类', '利胆排石颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (455, '外科用药', '清热剂', '清利肝胆剂', '乙类', '排石颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (456, '外科用药', '清热剂', '清利肝胆剂', '乙类', '消炎利胆胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (457, '外科用药', '清热剂', '清热解毒剂', '甲类', '季德胜蛇药片', NULL);
INSERT INTO `chstcmcatalog` VALUES (458, '外科用药', '清热剂', '清热解毒剂', '甲类', '京万红软膏', NULL);
INSERT INTO `chstcmcatalog` VALUES (459, '外科用药', '清热剂', '清热解毒剂', '甲类', '连翘败毒丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (460, '外科用药', '清热剂', '清热解毒剂', '甲类', '锡类散', NULL);
INSERT INTO `chstcmcatalog` VALUES (461, '外科用药', '清热剂', '清热解毒剂', '乙类', '拔毒膏贴膏', NULL);
INSERT INTO `chstcmcatalog` VALUES (462, '外科用药', '清热剂', '清热解毒剂', '乙类', '拔毒生肌散', NULL);
INSERT INTO `chstcmcatalog` VALUES (463, '外科用药', '清热剂', '清热解毒剂', '乙类', '地榆槐角丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (464, '外科用药', '清热剂', '清热解毒剂', '乙类', '康复新液[含喷雾剂]', NULL);
INSERT INTO `chstcmcatalog` VALUES (465, '外科用药', '清热剂', '清热解毒剂', '乙类', '连翘败毒膏', NULL);
INSERT INTO `chstcmcatalog` VALUES (466, '外科用药', '清热剂', '清热解毒剂', '乙类', '牛黄醒消丸', '*');
INSERT INTO `chstcmcatalog` VALUES (467, '外科用药', '清热剂', '清热解毒剂', '乙类', '去腐生肌散', NULL);
INSERT INTO `chstcmcatalog` VALUES (468, '外科用药', '清热剂', '清热解毒剂', '乙类', '生肌玉红膏', '*');
INSERT INTO `chstcmcatalog` VALUES (469, '外科用药', '清热剂', '清热解毒剂', '乙类', '五福化毒丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (470, '外科用药', '清热剂', '清热解毒剂', '乙类', '紫草油', NULL);
INSERT INTO `chstcmcatalog` VALUES (471, '外科用药', '清热剂', '清热利湿剂', '甲类', '马应龙麝香痔疮膏', NULL);
INSERT INTO `chstcmcatalog` VALUES (472, '外科用药', '清热剂', '清热利湿剂', '甲类', '如意金黄散', NULL);
INSERT INTO `chstcmcatalog` VALUES (473, '外科用药', '清热剂', '清热利湿剂', '甲类', '消痔灵注射液', NULL);
INSERT INTO `chstcmcatalog` VALUES (474, '外科用药', '清热剂', '清热利湿剂', '乙类', '肛泰栓', NULL);
INSERT INTO `chstcmcatalog` VALUES (475, '外科用药', '清热剂', '清热利湿剂', '乙类', '九华膏', NULL);
INSERT INTO `chstcmcatalog` VALUES (476, '外科用药', '清热剂', '清热利湿剂', '乙类', '马应龙麝香痔疮栓', NULL);
INSERT INTO `chstcmcatalog` VALUES (477, '外科用药', '清热剂', '清热利湿剂', '乙类', '痔疮片', NULL);
INSERT INTO `chstcmcatalog` VALUES (478, '外科用药', '清热剂', '清热利湿剂', '乙类', '痔疮栓', NULL);
INSERT INTO `chstcmcatalog` VALUES (479, '外科用药', '清热剂', '清热利湿剂', '乙类', '紫金锭软膏', NULL);
INSERT INTO `chstcmcatalog` VALUES (480, '外科用药', '清热剂', '清热利湿剂', '乙类', '紫金锭散', NULL);
INSERT INTO `chstcmcatalog` VALUES (481, '外科用药', '温经理气活血剂', '未分类', '甲类', '内消瘰疬丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (482, '外科用药', '温经理气活血剂', '未分类', '乙类', '茴香橘核丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (483, '外科用药', '温经理气活血剂', '未分类', '乙类', '小金丸', '*');
INSERT INTO `chstcmcatalog` VALUES (484, '外科用药', '温经理气活血剂', '未分类', '乙类', '阳和丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (485, '妇科用药', '理血剂', '理气养血剂', '甲类', '八珍益母丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (486, '妇科用药', '理血剂', '理气养血剂', '甲类', '妇科十味片', NULL);
INSERT INTO `chstcmcatalog` VALUES (487, '妇科用药', '理血剂', '理气养血剂', '乙类', '当归调经丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (488, '妇科用药', '理血剂', '理气养血剂', '乙类', '妇女痛经丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (489, '妇科用药', '理血剂', '理气养血剂', '乙类', '七制香附丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (490, '妇科用药', '理血剂', '活血化瘀剂', '甲类', '乳癖消片', NULL);
INSERT INTO `chstcmcatalog` VALUES (491, '妇科用药', '理血剂', '活血化瘀剂', '甲类', '益母草冲剂', NULL);
INSERT INTO `chstcmcatalog` VALUES (493, '妇科用药', '理血剂', '活血化瘀剂', '乙类', '桂枝茯苓胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (494, '妇科用药', '理血剂', '活血化瘀剂', '乙类', '桂枝茯苓丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (495, '妇科用药', '理血剂', '活血化瘀剂', '乙类', '生化丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (496, '妇科用药', '理血剂', '活血化瘀剂', '乙类', '益母草膏', NULL);
INSERT INTO `chstcmcatalog` VALUES (497, '妇科用药', '理血剂', '活血化瘀剂', '乙类', '益母草流浸膏', NULL);
INSERT INTO `chstcmcatalog` VALUES (498, '妇科用药', '清热剂', '未分类', '甲类', '妇科千金片', NULL);
INSERT INTO `chstcmcatalog` VALUES (499, '妇科用药', '清热剂', '未分类', '甲类', '宫血宁胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (500, '妇科用药', '清热剂', '未分类', '乙类', '白带丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (501, '妇科用药', '清热剂', '未分类', '乙类', '保妇康栓', NULL);
INSERT INTO `chstcmcatalog` VALUES (502, '妇科用药', '清热剂', '未分类', '乙类', '妇乐颗粒（冲剂）', NULL);
INSERT INTO `chstcmcatalog` VALUES (503, '妇科用药', '清热剂', '未分类', '乙类', '妇炎平胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (504, '妇科用药', '清热剂', '未分类', '乙类', '固经丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (505, '妇科用药', '清热剂', '未分类', '乙类', '金刚藤糖浆', NULL);
INSERT INTO `chstcmcatalog` VALUES (506, '妇科用药', '清热剂', '未分类', '乙类', '金鸡颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (507, '妇科用药', '清热剂', '未分类', '乙类', '金鸡片', NULL);
INSERT INTO `chstcmcatalog` VALUES (508, '妇科用药', '扶正剂', '未分类', '甲类', '艾附暖宫丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (509, '妇科用药', '扶正剂', '未分类', '甲类', '乌鸡白凤丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (510, '妇科用药', '扶正剂', '未分类', '乙类', '安坤赞育丸', '*');
INSERT INTO `chstcmcatalog` VALUES (511, '妇科用药', '扶正剂', '未分类', '乙类', '产复康颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (512, '妇科用药', '扶正剂', '未分类', '乙类', '更年安片', NULL);
INSERT INTO `chstcmcatalog` VALUES (513, '妇科用药', '扶正剂', '未分类', '乙类', '女金丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (514, '妇科用药', '扶正剂', '未分类', '乙类', '乌鸡白凤口服液', NULL);
INSERT INTO `chstcmcatalog` VALUES (515, '眼科用药', '清热剂', '未分类', '甲类', '明目上清片', NULL);
INSERT INTO `chstcmcatalog` VALUES (516, '眼科用药', '清热剂', '未分类', '甲类', '熊胆眼药水', NULL);
INSERT INTO `chstcmcatalog` VALUES (517, '眼科用药', '清热剂', '未分类', '乙类', '八宝眼药', NULL);
INSERT INTO `chstcmcatalog` VALUES (518, '眼科用药', '清热剂', '未分类', '乙类', '拨云退翳丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (519, '眼科用药', '清热剂', '未分类', '乙类', '当归龙荟丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (520, '眼科用药', '清热剂', '未分类', '乙类', '黄连羊肝丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (521, '眼科用药', '清热剂', '未分类', '乙类', '马应龙八宝眼膏', NULL);
INSERT INTO `chstcmcatalog` VALUES (522, '眼科用药', '扶正剂', '未分类', '甲类', '明目地黄丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (523, '眼科用药', '扶正剂', '未分类', '甲类', '石斛夜光丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (524, '眼科用药', '扶正剂', '未分类', '甲类', '珍珠明目滴眼液', NULL);
INSERT INTO `chstcmcatalog` VALUES (525, '眼科用药', '扶正剂', '未分类', '乙类', '石斛明目丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (526, '眼科用药', '扶正剂', '未分类', '乙类', '障眼明片', NULL);
INSERT INTO `chstcmcatalog` VALUES (527, '耳鼻喉科用药', '耳病', '未分类', '甲类', '滴耳油', NULL);
INSERT INTO `chstcmcatalog` VALUES (528, '耳鼻喉科用药', '耳病', '未分类', '甲类', '耳聋左慈丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (529, '耳鼻喉科用药', '鼻病', '未分类', '甲类', '鼻炎康片', NULL);
INSERT INTO `chstcmcatalog` VALUES (530, '耳鼻喉科用药', '鼻病', '未分类', '甲类', '藿胆丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (531, '耳鼻喉科用药', '鼻病', '未分类', '乙类', '鼻窦炎口服液', NULL);
INSERT INTO `chstcmcatalog` VALUES (532, '耳鼻喉科用药', '鼻病', '未分类', '乙类', '鼻咽清毒颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (533, '耳鼻喉科用药', '鼻病', '未分类', '乙类', '鼻炎滴剂', NULL);
INSERT INTO `chstcmcatalog` VALUES (534, '耳鼻喉科用药', '鼻病', '未分类', '乙类', '鼻炎片', NULL);
INSERT INTO `chstcmcatalog` VALUES (535, '耳鼻喉科用药', '鼻病', '未分类', '乙类', '鼻炎糖浆', NULL);
INSERT INTO `chstcmcatalog` VALUES (536, '耳鼻喉科用药', '鼻病', '未分类', '乙类', '鼻渊舒胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (537, '耳鼻喉科用药', '鼻病', '未分类', '乙类', '鼻渊舒口服液', NULL);
INSERT INTO `chstcmcatalog` VALUES (538, '耳鼻喉科用药', '鼻病', '未分类', '乙类', '滴通鼻炎水', NULL);
INSERT INTO `chstcmcatalog` VALUES (539, '耳鼻喉科用药', '鼻病', '未分类', '乙类', '藿胆片', NULL);
INSERT INTO `chstcmcatalog` VALUES (540, '耳鼻喉科用药', '鼻病', '未分类', '乙类', '千柏鼻炎片', NULL);
INSERT INTO `chstcmcatalog` VALUES (541, '耳鼻喉科用药', '咽喉病', '未分类', '甲类', '黄氏响声丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (542, '耳鼻喉科用药', '咽喉病', '未分类', '甲类', '六神丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (543, '耳鼻喉科用药', '咽喉病', '未分类', '乙类', '冰硼散', NULL);
INSERT INTO `chstcmcatalog` VALUES (544, '耳鼻喉科用药', '咽喉病', '未分类', '乙类', '桂林西瓜霜', NULL);
INSERT INTO `chstcmcatalog` VALUES (545, '耳鼻喉科用药', '咽喉病', '未分类', '乙类', '清咽滴丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (546, '耳鼻喉科用药', '咽喉病', '未分类', '乙类', '清咽润喉丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (547, '耳鼻喉科用药', '咽喉病', '未分类', '乙类', '清音片', NULL);
INSERT INTO `chstcmcatalog` VALUES (548, '耳鼻喉科用药', '咽喉病', '未分类', '乙类', '清音丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (549, '耳鼻喉科用药', '咽喉病', '未分类', '乙类', '珠黄散', NULL);
INSERT INTO `chstcmcatalog` VALUES (550, '骨伤科用药', '活血化瘀剂', '内服药', '甲类', '跌打丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (551, '骨伤科用药', '活血化瘀剂', '内服药', '甲类', '三七伤药片', NULL);
INSERT INTO `chstcmcatalog` VALUES (552, '骨伤科用药', '活血化瘀剂', '内服药', '甲类', '云南白药[指散剂]', NULL);
INSERT INTO `chstcmcatalog` VALUES (553, '骨伤科用药', '活血化瘀剂', '内服药', '乙类', '跌打七厘散', NULL);
INSERT INTO `chstcmcatalog` VALUES (554, '骨伤科用药', '活血化瘀剂', '内服药', '乙类', '回生第一散（丹）', NULL);
INSERT INTO `chstcmcatalog` VALUES (555, '骨伤科用药', '活血化瘀剂', '内服药', '乙类', '接骨七厘片', NULL);
INSERT INTO `chstcmcatalog` VALUES (556, '骨伤科用药', '活血化瘀剂', '内服药', '乙类', '七厘胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (557, '骨伤科用药', '活血化瘀剂', '内服药', '乙类', '七厘散', NULL);
INSERT INTO `chstcmcatalog` VALUES (558, '骨伤科用药', '活血化瘀剂', '内服药', '乙类', '伤科接骨片', NULL);
INSERT INTO `chstcmcatalog` VALUES (559, '骨伤科用药', '活血化瘀剂', '内服药', '乙类', '沈阳红药胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (560, '骨伤科用药', '活血化瘀剂', '内服药', '乙类', '沈阳红药[指片剂]', NULL);
INSERT INTO `chstcmcatalog` VALUES (561, '骨伤科用药', '活血化瘀剂', '内服药', '乙类', '云南白药胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (562, '骨伤科用药', '活血化瘀剂', '外用药', '甲类', '云南白药膏', NULL);
INSERT INTO `chstcmcatalog` VALUES (563, '骨伤科用药', '活血化瘀剂', '外用药', '乙类', '沈阳红药气雾剂', NULL);
INSERT INTO `chstcmcatalog` VALUES (564, '骨伤科用药', '活血化瘀剂', '外用药', '乙类', '云南白药酊', NULL);
INSERT INTO `chstcmcatalog` VALUES (565, '骨伤科用药', '活血通络剂', '内服药', '甲类', '活血止痛散', NULL);
INSERT INTO `chstcmcatalog` VALUES (566, '骨伤科用药', '活血通络剂', '内服药', '甲类', '舒筋活血片', NULL);
INSERT INTO `chstcmcatalog` VALUES (567, '骨伤科用药', '活血通络剂', '内服药', '乙类', '骨刺宁胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (568, '骨伤科用药', '活血通络剂', '内服药', '乙类', '活血止痛胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (569, '骨伤科用药', '活血通络剂', '内服药', '乙类', '木瓜丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (570, '骨伤科用药', '活血通络剂', '内服药', '乙类', '舒筋活血丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (571, '骨伤科用药', '活血通络剂', '内服药', '乙类', '瘀血痹颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (572, '骨伤科用药', '活血通络剂', '外用药', '甲类', '狗皮膏', NULL);
INSERT INTO `chstcmcatalog` VALUES (573, '骨伤科用药', '活血通络剂', '外用药', '乙类', '跌打万花油', NULL);
INSERT INTO `chstcmcatalog` VALUES (574, '骨伤科用药', '活血通络剂', '外用药', '乙类', '骨通贴膏', NULL);
INSERT INTO `chstcmcatalog` VALUES (575, '骨伤科用药', '活血通络剂', '外用药', '乙类', '骨友灵擦剂', NULL);
INSERT INTO `chstcmcatalog` VALUES (576, '骨伤科用药', '活血通络剂', '外用药', '乙类', '骨质宁擦剂', NULL);
INSERT INTO `chstcmcatalog` VALUES (577, '骨伤科用药', '活血通络剂', '外用药', '乙类', '正骨水', NULL);
INSERT INTO `chstcmcatalog` VALUES (578, '骨伤科用药', '补益肝肾剂', '未分类', '甲类', '骨刺片', NULL);
INSERT INTO `chstcmcatalog` VALUES (579, '骨伤科用药', '补益肝肾剂', '未分类', '甲类', '颈复康颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (580, '骨伤科用药', '补益肝肾剂', '未分类', '乙类', '骨刺丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (581, '骨伤科用药', '补益肝肾剂', '未分类', '乙类', '骨仙片', NULL);
INSERT INTO `chstcmcatalog` VALUES (582, '骨伤科用药', '补益肝肾剂', '未分类', '乙类', '抗骨质增生胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (583, '骨伤科用药', '补益肝肾剂', '未分类', '乙类', '抗骨质增生丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (584, '皮肤科用药', '未分类', '未分类', '甲类', '青黛散', NULL);
INSERT INTO `chstcmcatalog` VALUES (585, '皮肤科用药', '未分类', '未分类', '乙类', '白斑膏', NULL);
INSERT INTO `chstcmcatalog` VALUES (586, '皮肤科用药', '未分类', '未分类', '乙类', '斑秃丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (587, '皮肤科用药', '未分类', '未分类', '乙类', '复方青黛胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (588, '皮肤科用药', '未分类', '未分类', '乙类', '复方青黛丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (589, '皮肤科用药', '未分类', '未分类', '乙类', '皮肤康洗液', NULL);
INSERT INTO `chstcmcatalog` VALUES (590, '皮肤科用药', '未分类', '未分类', '乙类', '青黛丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (591, '皮肤科用药', '未分类', '未分类', '乙类', '乌蛇止痒丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (592, '皮肤科用药', '未分类', '未分类', '乙类', '消风止痒颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (593, '皮肤科用药', '未分类', '未分类', '乙类', '癣湿药水（鹅掌风药水）', NULL);
INSERT INTO `chstcmcatalog` VALUES (594, '皮肤科用药', '未分类', '未分类', '乙类', '银屑灵', NULL);
INSERT INTO `chstcmcatalog` VALUES (595, '民族药', '未分类', '未分类', '未分类', '阿拉坦五味丸', '　一、单味或复方均不支付费用的中药饮片及药材');
INSERT INTO `chstcmcatalog` VALUES (596, '民族药', '未分类', '未分类', '未分类', '巴特日七味丸', '　　白糖参、朝鲜红参、玳瑁、冬虫夏草、蜂蜜、蛤蚧、狗宝、海龙、海马、红参、猴枣、琥珀、灵芝、羚羊角尖粉、鹿茸、马宝、玛瑙、牛黄、珊瑚、麝香、西红花、西洋参、血竭、燕窝、野山参、移山参、珍珠（粉）、紫河车?');
INSERT INTO `chstcmcatalog` VALUES (597, '民族药', '未分类', '未分类', '未分类', '白脉软膏', '　　各种动物脏器（鸡内金除外）和胎、鞭、尾、筋、骨');
INSERT INTO `chstcmcatalog` VALUES (598, '民族药', '未分类', '未分类', '未分类', '百癣夏塔热片', '　　二、单味使用不予支付费用的中药饮片及药材');
INSERT INTO `chstcmcatalog` VALUES (599, '民族药', '未分类', '未分类', '未分类', '冰黄肤乐软膏', '阿胶、阿胶珠、八角茴香、白果、白芷、百合、鳖甲、鳖甲胶、薄荷、莱菔子、陈皮、赤小豆、川贝母、代代花、淡豆豉、淡竹叶、当归、党参、刀豆、丁香、榧子、佛手、茯苓、蝮蛇、甘草、高良姜、葛根、枸杞子、龟甲、龟甲胶、广藿香、何首乌、荷叶、黑芝麻、红花、胡椒、花椒、黄芥子、黄芪、火麻仁、核桃仁、胡桃仁、姜（生姜、干姜）、金钱白花蛇、金银花、桔红、菊花、菊苣、决明子、昆布、莲子、灵芝、芦荟、鹿角胶、绿豆、罗汉果、龙眼肉、马齿苋、麦芽、牡蛎、木瓜、南瓜子、胖大海、蒲公英、蕲蛇、芡实、青果、全蝎、肉苁蓉、肉豆蔻、肉桂、山楂、桑椹、桑叶、沙棘、砂仁、山药、生晒参、石斛、酸枣仁、天麻、甜杏仁、乌梅、乌梢蛇、鲜白茅根、鲜芦根、香薷、香橼、小茴香、薤白、饴糖、益智、薏苡仁、罂粟壳、余甘子、鱼腥草、玉竹、郁李仁、枣（大枣、酸枣、黑枣）、栀子、紫苏。');
INSERT INTO `chstcmcatalog` VALUES (600, '民族药', '未分类', '未分类', '未分类', '独一味胶囊', '　　注:本目录所列药品均包括生药及炮制后的药材及饮片。');
INSERT INTO `chstcmcatalog` VALUES (601, '民族药', '未分类', '未分类', '未分类', '二十五味珊瑚丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (602, '民族药', '未分类', '未分类', '未分类', '二十五味松石丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (603, '民族药', '未分类', '未分类', '未分类', '二十五味珍珠丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (604, '民族药', '未分类', '未分类', '未分类', '风湿二十五味丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (605, '民族药', '未分类', '未分类', '未分类', '复方木尼孜其颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (606, '民族药', '未分类', '未分类', '未分类', '寒喘祖帕颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (607, '民族药', '未分类', '未分类', '未分类', '红花消肝十三味丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (608, '民族药', '未分类', '未分类', '未分类', '吉祥安坤丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (609, '民族药', '未分类', '未分类', '未分类', '洁白丸[含胶囊]', NULL);
INSERT INTO `chstcmcatalog` VALUES (610, '民族药', '未分类', '未分类', '未分类', '流感丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (611, '民族药', '未分类', '未分类', '未分类', '六味安消散[含胶囊]', NULL);
INSERT INTO `chstcmcatalog` VALUES (612, '民族药', '未分类', '未分类', '未分类', '六味能消胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (613, '民族药', '未分类', '未分类', '未分类', '暖宫七味散', NULL);
INSERT INTO `chstcmcatalog` VALUES (614, '民族药', '未分类', '未分类', '未分类', '诺迪康胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (615, '民族药', '未分类', '未分类', '未分类', '七味红花殊胜丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (616, '民族药', '未分类', '未分类', '未分类', '青鹏膏剂', NULL);
INSERT INTO `chstcmcatalog` VALUES (617, '民族药', '未分类', '未分类', '未分类', '清感九味丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (618, '民族药', '未分类', '未分类', '未分类', '清热八味散', NULL);
INSERT INTO `chstcmcatalog` VALUES (619, '民族药', '未分类', '未分类', '未分类', '清肾热十味散', NULL);
INSERT INTO `chstcmcatalog` VALUES (620, '民族药', '未分类', '未分类', '未分类', '清心沉香八味丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (621, '民族药', '未分类', '未分类', '未分类', '仁青芒觉', NULL);
INSERT INTO `chstcmcatalog` VALUES (622, '民族药', '未分类', '未分类', '未分类', '肉蔻五味丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (623, '民族药', '未分类', '未分类', '未分类', '如意珍宝丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (624, '民族药', '未分类', '未分类', '未分类', '十味蒂达胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (625, '民族药', '未分类', '未分类', '未分类', '十味龙胆花颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (626, '民族药', '未分类', '未分类', '未分类', '石榴健胃散', NULL);
INSERT INTO `chstcmcatalog` VALUES (627, '民族药', '未分类', '未分类', '未分类', '通滞苏润江胶囊', NULL);
INSERT INTO `chstcmcatalog` VALUES (628, '民族药', '未分类', '未分类', '未分类', '外用溃疡散', NULL);
INSERT INTO `chstcmcatalog` VALUES (629, '民族药', '未分类', '未分类', '未分类', '五味麝香丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (630, '民族药', '未分类', '未分类', '未分类', '西帕依固龈液', NULL);
INSERT INTO `chstcmcatalog` VALUES (631, '民族药', '未分类', '未分类', '未分类', '消痛贴膏', NULL);
INSERT INTO `chstcmcatalog` VALUES (632, '民族药', '未分类', '未分类', '未分类', '雪山金罗汉止痛涂膜剂', NULL);
INSERT INTO `chstcmcatalog` VALUES (633, '民族药', '未分类', '未分类', '未分类', '炎消迪娜尔糖浆', NULL);
INSERT INTO `chstcmcatalog` VALUES (634, '民族药', '未分类', '未分类', '未分类', '养心达瓦依米西克蜜膏', NULL);
INSERT INTO `chstcmcatalog` VALUES (635, '民族药', '未分类', '未分类', '未分类', '益心巴迪然吉布亚颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (636, '民族药', '未分类', '未分类', '未分类', '扎冲十三味丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (637, '民族药', '未分类', '未分类', '未分类', '珍宝丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (638, '民族药', '未分类', '未分类', '未分类', '止血八味散', NULL);
INSERT INTO `chstcmcatalog` VALUES (639, '民族药', '未分类', '未分类', '未分类', '智托洁白丸', NULL);
INSERT INTO `chstcmcatalog` VALUES (640, '民族药', '未分类', '未分类', '未分类', '祖卡木颗粒', NULL);
INSERT INTO `chstcmcatalog` VALUES (641, '民族药', '未分类', '未分类', '未分类', '坐珠达西', NULL);

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table`  (
  `table_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `tpl_web_type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '前端模板类型（element-ui模版 element-plus模版）',
  `package_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gen_table
-- ----------------------------
INSERT INTO `gen_table` VALUES (1, 'chstcmcatalog', '药品信息', NULL, NULL, 'chstcmcatalog', 'crud', 'element-ui', 'com.ruoyi.system', 'system', 'chstcmcatalog', '药品信息', 'ruoyi', '0', '/', '{\"parentMenuId\":2006}', 'admin', '2024-12-08 16:45:23', '', '2024-12-08 16:55:56', NULL);

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column`  (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` bigint(20) NULL DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gen_table_column
-- ----------------------------
INSERT INTO `gen_table_column` VALUES (8, 1, 'id', 'ID', 'int(11)', 'Long', 'id', '1', '1', '0', '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, '', '2024-12-08 18:50:51', '', NULL);
INSERT INTO `gen_table_column` VALUES (9, 1, 'level1', '一级分类', 'varchar(10)', 'String', 'level1', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, '', '2024-12-08 18:50:51', '', NULL);
INSERT INTO `gen_table_column` VALUES (10, 1, 'level2', '二级分类，为空时应填入\"未分类\"', 'varchar(20)', 'String', 'level2', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, '', '2024-12-08 18:50:51', '', NULL);
INSERT INTO `gen_table_column` VALUES (11, 1, 'level3', '三级分类，为空时应填入\"未分类\"', 'varchar(20)', 'String', 'level3', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, '', '2024-12-08 18:50:51', '', NULL);
INSERT INTO `gen_table_column` VALUES (12, 1, 'type', '药品类型', 'char(3)', 'String', 'type', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 5, '', '2024-12-08 18:50:51', '', NULL);
INSERT INTO `gen_table_column` VALUES (13, 1, 'drugName', '药品名称', 'varchar(20)', 'String', 'drugName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 6, '', '2024-12-08 18:50:51', '', NULL);
INSERT INTO `gen_table_column` VALUES (14, 1, 'constrain', '使用限制', 'varchar(512)', 'String', 'constrain', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'textarea', '', 7, '', '2024-12-08 18:50:51', '', NULL);

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `blob_data` blob NULL COMMENT '存放持久化Trigger对象',
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'Blob类型的触发器表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `calendar_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '日历名称',
  `calendar` blob NOT NULL COMMENT '存放持久化calendar对象',
  PRIMARY KEY (`sched_name`, `calendar_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '日历信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `cron_expression` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'cron表达式',
  `time_zone_id` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '时区',
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'Cron类型的触发器表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `entry_id` varchar(95) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度器实例id',
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `instance_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度器实例名',
  `fired_time` bigint(20) NOT NULL COMMENT '触发的时间',
  `sched_time` bigint(20) NOT NULL COMMENT '定时器制定的时间',
  `priority` int(11) NOT NULL COMMENT '优先级',
  `state` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态',
  `job_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `job_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务组名',
  `is_nonconcurrent` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否并发',
  `requests_recovery` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否接受恢复执行',
  PRIMARY KEY (`sched_name`, `entry_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '已触发的触发器表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `job_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务名称',
  `job_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务组名',
  `description` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '相关介绍',
  `job_class_name` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '执行任务类名称',
  `is_durable` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '是否持久化',
  `is_nonconcurrent` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '是否并发',
  `is_update_data` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '是否更新数据',
  `requests_recovery` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '是否接受恢复执行',
  `job_data` blob NULL COMMENT '存放持久化job对象',
  PRIMARY KEY (`sched_name`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '任务详细信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `lock_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '悲观锁名称',
  PRIMARY KEY (`sched_name`, `lock_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '存储的悲观锁信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  PRIMARY KEY (`sched_name`, `trigger_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '暂停的触发器表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `instance_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '实例名称',
  `last_checkin_time` bigint(20) NOT NULL COMMENT '上次检查时间',
  `checkin_interval` bigint(20) NOT NULL COMMENT '检查间隔时间',
  PRIMARY KEY (`sched_name`, `instance_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '调度器状态表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `repeat_count` bigint(20) NOT NULL COMMENT '重复的次数统计',
  `repeat_interval` bigint(20) NOT NULL COMMENT '重复的间隔时间',
  `times_triggered` bigint(20) NOT NULL COMMENT '已经触发的次数',
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '简单触发器的信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `str_prop_1` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'String类型的trigger的第一个参数',
  `str_prop_2` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'String类型的trigger的第二个参数',
  `str_prop_3` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'String类型的trigger的第三个参数',
  `int_prop_1` int(11) NULL DEFAULT NULL COMMENT 'int类型的trigger的第一个参数',
  `int_prop_2` int(11) NULL DEFAULT NULL COMMENT 'int类型的trigger的第二个参数',
  `long_prop_1` bigint(20) NULL DEFAULT NULL COMMENT 'long类型的trigger的第一个参数',
  `long_prop_2` bigint(20) NULL DEFAULT NULL COMMENT 'long类型的trigger的第二个参数',
  `dec_prop_1` decimal(13, 4) NULL DEFAULT NULL COMMENT 'decimal类型的trigger的第一个参数',
  `dec_prop_2` decimal(13, 4) NULL DEFAULT NULL COMMENT 'decimal类型的trigger的第二个参数',
  `bool_prop_1` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Boolean类型的trigger的第一个参数',
  `bool_prop_2` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Boolean类型的trigger的第二个参数',
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '同步机制的行锁表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '触发器的名字',
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '触发器所属组的名字',
  `job_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_job_details表job_name的外键',
  `job_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_job_details表job_group的外键',
  `description` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '相关介绍',
  `next_fire_time` bigint(20) NULL DEFAULT NULL COMMENT '上一次触发时间（毫秒）',
  `prev_fire_time` bigint(20) NULL DEFAULT NULL COMMENT '下一次触发时间（默认为-1表示不触发）',
  `priority` int(11) NULL DEFAULT NULL COMMENT '优先级',
  `trigger_state` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '触发器状态',
  `trigger_type` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '触发器的类型',
  `start_time` bigint(20) NOT NULL COMMENT '开始时间',
  `end_time` bigint(20) NULL DEFAULT NULL COMMENT '结束时间',
  `calendar_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日程表名称',
  `misfire_instr` smallint(6) NULL DEFAULT NULL COMMENT '补偿执行的策略',
  `job_data` blob NULL COMMENT '存放持久化job对象',
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  INDEX `sched_name`(`sched_name`, `job_name`, `job_group`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '触发器详细信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `config_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '参数配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2024-12-08 16:29:05', '', NULL, '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES (2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2024-12-08 16:29:05', '', NULL, '初始化密码 123456');
INSERT INTO `sys_config` VALUES (3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin', '2024-12-08 16:29:05', '', NULL, '深色主题theme-dark，浅色主题theme-light');
INSERT INTO `sys_config` VALUES (4, '账号自助-验证码开关', 'sys.account.captchaEnabled', 'true', 'Y', 'admin', '2024-12-08 16:29:05', '', NULL, '是否开启验证码功能（true开启，false关闭）');
INSERT INTO `sys_config` VALUES (5, '账号自助-是否开启用户注册功能', 'sys.account.registerUser', 'false', 'Y', 'admin', '2024-12-08 16:29:05', '', NULL, '是否开启注册用户功能（true开启，false关闭）');
INSERT INTO `sys_config` VALUES (6, '用户登录-黑名单列表', 'sys.login.blackIPList', '', 'Y', 'admin', '2024-12-08 16:29:05', '', NULL, '设置登录IP黑名单限制，多个匹配项以;分隔，支持匹配（*通配、网段）');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父部门id',
  `ancestors` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `order_num` int(11) NULL DEFAULT 0 COMMENT '显示顺序',
  `leader` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 200 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (100, 0, '0', '若依科技', 0, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2024-12-08 16:29:05', '', NULL);
INSERT INTO `sys_dept` VALUES (101, 100, '0,100', '深圳总公司', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2024-12-08 16:29:05', '', NULL);
INSERT INTO `sys_dept` VALUES (102, 100, '0,100', '长沙分公司', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2024-12-08 16:29:05', '', NULL);
INSERT INTO `sys_dept` VALUES (103, 101, '0,100,101', '研发部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2024-12-08 16:29:05', '', NULL);
INSERT INTO `sys_dept` VALUES (104, 101, '0,100,101', '市场部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2024-12-08 16:29:05', '', NULL);
INSERT INTO `sys_dept` VALUES (105, 101, '0,100,101', '测试部门', 3, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2024-12-08 16:29:05', '', NULL);
INSERT INTO `sys_dept` VALUES (106, 101, '0,100,101', '财务部门', 4, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2024-12-08 16:29:05', '', NULL);
INSERT INTO `sys_dept` VALUES (107, 101, '0,100,101', '运维部门', 5, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2024-12-08 16:29:05', '', NULL);
INSERT INTO `sys_dept` VALUES (108, 102, '0,100,102', '市场部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2024-12-08 16:29:05', '', NULL);
INSERT INTO `sys_dept` VALUES (109, 102, '0,100,102', '财务部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2024-12-08 16:29:05', '', NULL);

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(11) NULL DEFAULT 0 COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '性别男');
INSERT INTO `sys_dict_data` VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '性别女');
INSERT INTO `sys_dict_data` VALUES (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '性别未知');
INSERT INTO `sys_dict_data` VALUES (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '显示菜单');
INSERT INTO `sys_dict_data` VALUES (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (10, 1, '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '默认分组');
INSERT INTO `sys_dict_data` VALUES (11, 2, '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '系统分组');
INSERT INTO `sys_dict_data` VALUES (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '系统默认是');
INSERT INTO `sys_dict_data` VALUES (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '系统默认否');
INSERT INTO `sys_dict_data` VALUES (14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '通知');
INSERT INTO `sys_dict_data` VALUES (15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '公告');
INSERT INTO `sys_dict_data` VALUES (16, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (17, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '关闭状态');
INSERT INTO `sys_dict_data` VALUES (18, 99, '其他', '0', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '其他操作');
INSERT INTO `sys_dict_data` VALUES (19, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '新增操作');
INSERT INTO `sys_dict_data` VALUES (20, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '修改操作');
INSERT INTO `sys_dict_data` VALUES (21, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '删除操作');
INSERT INTO `sys_dict_data` VALUES (22, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '授权操作');
INSERT INTO `sys_dict_data` VALUES (23, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '导出操作');
INSERT INTO `sys_dict_data` VALUES (24, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '导入操作');
INSERT INTO `sys_dict_data` VALUES (25, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '强退操作');
INSERT INTO `sys_dict_data` VALUES (26, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '生成操作');
INSERT INTO `sys_dict_data` VALUES (27, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '清空操作');
INSERT INTO `sys_dict_data` VALUES (28, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (29, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '停用状态');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`dict_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '用户性别', 'sys_user_sex', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '用户性别列表');
INSERT INTO `sys_dict_type` VALUES (2, '菜单状态', 'sys_show_hide', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES (3, '系统开关', 'sys_normal_disable', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '系统开关列表');
INSERT INTO `sys_dict_type` VALUES (4, '任务状态', 'sys_job_status', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '任务状态列表');
INSERT INTO `sys_dict_type` VALUES (5, '任务分组', 'sys_job_group', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '任务分组列表');
INSERT INTO `sys_dict_type` VALUES (6, '系统是否', 'sys_yes_no', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '系统是否列表');
INSERT INTO `sys_dict_type` VALUES (7, '通知类型', 'sys_notice_type', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '通知类型列表');
INSERT INTO `sys_dict_type` VALUES (8, '通知状态', 'sys_notice_status', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '通知状态列表');
INSERT INTO `sys_dict_type` VALUES (9, '操作类型', 'sys_oper_type', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '操作类型列表');
INSERT INTO `sys_dict_type` VALUES (10, '系统状态', 'sys_common_status', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '登录状态列表');

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务调度表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES (1, '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams', '0/10 * * * * ?', '3', '1', '1', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_job` VALUES (2, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '0/15 * * * * ?', '3', '1', '1', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_job` VALUES (3, '系统默认（多参）', 'DEFAULT', 'ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)', '0/20 * * * * ?', '3', '1', '1', 'admin', '2024-12-08 16:29:05', '', NULL, '');

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log`  (
  `job_log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日志信息',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '异常信息',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务调度日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor`  (
  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作系统',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '提示消息',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`) USING BTREE,
  INDEX `idx_sys_logininfor_s`(`status`) USING BTREE,
  INDEX `idx_sys_logininfor_lt`(`login_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 103 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统访问记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------
INSERT INTO `sys_logininfor` VALUES (100, 'admin', '127.0.0.1', '内网IP', 'Chrome 13', 'Windows 10', '0', '登录成功', '2024-12-08 16:45:13');
INSERT INTO `sys_logininfor` VALUES (101, 'admin', '127.0.0.1', '内网IP', 'Chrome 13', 'Windows 10', '1', '验证码错误', '2024-12-08 18:08:23');
INSERT INTO `sys_logininfor` VALUES (102, 'admin', '127.0.0.1', '内网IP', 'Chrome 13', 'Windows 10', '0', '登录成功', '2024-12-08 18:08:27');
INSERT INTO `sys_logininfor` VALUES (103, 'admin', '127.0.0.1', '内网IP', 'Chrome 13', 'Windows 10', '0', '登录成功', '2024-12-08 18:45:07');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int(11) NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `query` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由参数',
  `route_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '路由名称',
  `is_frame` int(11) NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
  `is_cache` int(11) NULL DEFAULT 0 COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2013 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 1, 'system', NULL, '', '', 1, 0, 'M', '0', '0', '', 'system', 'admin', '2024-12-08 16:29:05', '', NULL, '系统管理目录');
INSERT INTO `sys_menu` VALUES (2, '系统监控', 0, 2, 'monitor', NULL, '', '', 1, 0, 'M', '0', '0', '', 'monitor', 'admin', '2024-12-08 16:29:05', '', NULL, '系统监控目录');
INSERT INTO `sys_menu` VALUES (3, '系统工具', 0, 3, 'tool', NULL, '', '', 1, 0, 'M', '0', '0', '', 'tool', 'admin', '2024-12-08 16:29:05', '', NULL, '系统工具目录');
INSERT INTO `sys_menu` VALUES (4, '若依官网', 0, 4, 'http://ruoyi.vip', NULL, '', '', 0, 0, 'M', '0', '0', '', 'guide', 'admin', '2024-12-08 16:29:05', '', NULL, '若依官网地址');
INSERT INTO `sys_menu` VALUES (100, '用户管理', 1, 1, 'user', 'system/user/index', '', '', 1, 0, 'C', '0', '0', 'system:user:list', 'user', 'admin', '2024-12-08 16:29:05', '', NULL, '用户管理菜单');
INSERT INTO `sys_menu` VALUES (101, '角色管理', 1, 2, 'role', 'system/role/index', '', '', 1, 0, 'C', '0', '0', 'system:role:list', 'peoples', 'admin', '2024-12-08 16:29:05', '', NULL, '角色管理菜单');
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 3, 'menu', 'system/menu/index', '', '', 1, 0, 'C', '0', '0', 'system:menu:list', 'tree-table', 'admin', '2024-12-08 16:29:05', '', NULL, '菜单管理菜单');
INSERT INTO `sys_menu` VALUES (103, '部门管理', 1, 4, 'dept', 'system/dept/index', '', '', 1, 0, 'C', '0', '0', 'system:dept:list', 'tree', 'admin', '2024-12-08 16:29:05', '', NULL, '部门管理菜单');
INSERT INTO `sys_menu` VALUES (104, '岗位管理', 1, 5, 'post', 'system/post/index', '', '', 1, 0, 'C', '0', '0', 'system:post:list', 'post', 'admin', '2024-12-08 16:29:05', '', NULL, '岗位管理菜单');
INSERT INTO `sys_menu` VALUES (105, '字典管理', 1, 6, 'dict', 'system/dict/index', '', '', 1, 0, 'C', '0', '0', 'system:dict:list', 'dict', 'admin', '2024-12-08 16:29:05', '', NULL, '字典管理菜单');
INSERT INTO `sys_menu` VALUES (106, '参数设置', 1, 7, 'config', 'system/config/index', '', '', 1, 0, 'C', '0', '0', 'system:config:list', 'edit', 'admin', '2024-12-08 16:29:05', '', NULL, '参数设置菜单');
INSERT INTO `sys_menu` VALUES (107, '通知公告', 1, 8, 'notice', 'system/notice/index', '', '', 1, 0, 'C', '0', '0', 'system:notice:list', 'message', 'admin', '2024-12-08 16:29:05', '', NULL, '通知公告菜单');
INSERT INTO `sys_menu` VALUES (108, '日志管理', 1, 9, 'log', '', '', '', 1, 0, 'M', '0', '0', '', 'log', 'admin', '2024-12-08 16:29:05', '', NULL, '日志管理菜单');
INSERT INTO `sys_menu` VALUES (109, '在线用户', 2, 1, 'online', 'monitor/online/index', '', '', 1, 0, 'C', '0', '0', 'monitor:online:list', 'online', 'admin', '2024-12-08 16:29:05', '', NULL, '在线用户菜单');
INSERT INTO `sys_menu` VALUES (110, '定时任务', 2, 2, 'job', 'monitor/job/index', '', '', 1, 0, 'C', '0', '0', 'monitor:job:list', 'job', 'admin', '2024-12-08 16:29:05', '', NULL, '定时任务菜单');
INSERT INTO `sys_menu` VALUES (111, '数据监控', 2, 3, 'druid', 'monitor/druid/index', '', '', 1, 0, 'C', '0', '0', 'monitor:druid:list', 'druid', 'admin', '2024-12-08 16:29:05', '', NULL, '数据监控菜单');
INSERT INTO `sys_menu` VALUES (112, '服务监控', 2, 4, 'server', 'monitor/server/index', '', '', 1, 0, 'C', '0', '0', 'monitor:server:list', 'server', 'admin', '2024-12-08 16:29:05', '', NULL, '服务监控菜单');
INSERT INTO `sys_menu` VALUES (113, '缓存监控', 2, 5, 'cache', 'monitor/cache/index', '', '', 1, 0, 'C', '0', '0', 'monitor:cache:list', 'redis', 'admin', '2024-12-08 16:29:05', '', NULL, '缓存监控菜单');
INSERT INTO `sys_menu` VALUES (114, '缓存列表', 2, 6, 'cacheList', 'monitor/cache/list', '', '', 1, 0, 'C', '0', '0', 'monitor:cache:list', 'redis-list', 'admin', '2024-12-08 16:29:05', '', NULL, '缓存列表菜单');
INSERT INTO `sys_menu` VALUES (115, '表单构建', 3, 1, 'build', 'tool/build/index', '', '', 1, 0, 'C', '0', '0', 'tool:build:list', 'build', 'admin', '2024-12-08 16:29:05', '', NULL, '表单构建菜单');
INSERT INTO `sys_menu` VALUES (116, '代码生成', 3, 2, 'gen', 'tool/gen/index', '', '', 1, 0, 'C', '0', '0', 'tool:gen:list', 'code', 'admin', '2024-12-08 16:29:05', '', NULL, '代码生成菜单');
INSERT INTO `sys_menu` VALUES (117, '系统接口', 3, 3, 'swagger', 'tool/swagger/index', '', '', 1, 0, 'C', '0', '0', 'tool:swagger:list', 'swagger', 'admin', '2024-12-08 16:29:05', '', NULL, '系统接口菜单');
INSERT INTO `sys_menu` VALUES (500, '操作日志', 108, 1, 'operlog', 'monitor/operlog/index', '', '', 1, 0, 'C', '0', '0', 'monitor:operlog:list', 'form', 'admin', '2024-12-08 16:29:05', '', NULL, '操作日志菜单');
INSERT INTO `sys_menu` VALUES (501, '登录日志', 108, 2, 'logininfor', 'monitor/logininfor/index', '', '', 1, 0, 'C', '0', '0', 'monitor:logininfor:list', 'logininfor', 'admin', '2024-12-08 16:29:05', '', NULL, '登录日志菜单');
INSERT INTO `sys_menu` VALUES (1000, '用户查询', 100, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:query', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1001, '用户新增', 100, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:add', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1002, '用户修改', 100, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:edit', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1003, '用户删除', 100, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:remove', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1004, '用户导出', 100, 5, '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:export', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1005, '用户导入', 100, 6, '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:import', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1006, '重置密码', 100, 7, '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:resetPwd', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1007, '角色查询', 101, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'system:role:query', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1008, '角色新增', 101, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'system:role:add', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1009, '角色修改', 101, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'system:role:edit', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1010, '角色删除', 101, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'system:role:remove', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1011, '角色导出', 101, 5, '', '', '', '', 1, 0, 'F', '0', '0', 'system:role:export', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1012, '菜单查询', 102, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'system:menu:query', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1013, '菜单新增', 102, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'system:menu:add', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1014, '菜单修改', 102, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'system:menu:edit', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1015, '菜单删除', 102, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'system:menu:remove', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1016, '部门查询', 103, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'system:dept:query', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1017, '部门新增', 103, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'system:dept:add', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1018, '部门修改', 103, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'system:dept:edit', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1019, '部门删除', 103, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'system:dept:remove', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1020, '岗位查询', 104, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'system:post:query', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1021, '岗位新增', 104, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'system:post:add', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1022, '岗位修改', 104, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'system:post:edit', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1023, '岗位删除', 104, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'system:post:remove', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1024, '岗位导出', 104, 5, '', '', '', '', 1, 0, 'F', '0', '0', 'system:post:export', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1025, '字典查询', 105, 1, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:dict:query', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1026, '字典新增', 105, 2, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:dict:add', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1027, '字典修改', 105, 3, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:dict:edit', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1028, '字典删除', 105, 4, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:dict:remove', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1029, '字典导出', 105, 5, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:dict:export', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1030, '参数查询', 106, 1, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:config:query', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1031, '参数新增', 106, 2, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:config:add', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1032, '参数修改', 106, 3, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:config:edit', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1033, '参数删除', 106, 4, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:config:remove', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1034, '参数导出', 106, 5, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:config:export', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1035, '公告查询', 107, 1, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:notice:query', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1036, '公告新增', 107, 2, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:notice:add', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1037, '公告修改', 107, 3, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:notice:edit', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1038, '公告删除', 107, 4, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:notice:remove', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1039, '操作查询', 500, 1, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:query', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1040, '操作删除', 500, 2, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:remove', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1041, '日志导出', 500, 3, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:export', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1042, '登录查询', 501, 1, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:query', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1043, '登录删除', 501, 2, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:remove', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1044, '日志导出', 501, 3, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:export', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1045, '账户解锁', 501, 4, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:unlock', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1046, '在线查询', 109, 1, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:online:query', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1047, '批量强退', 109, 2, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:online:batchLogout', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1048, '单条强退', 109, 3, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:online:forceLogout', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1049, '任务查询', 110, 1, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:job:query', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1050, '任务新增', 110, 2, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:job:add', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1051, '任务修改', 110, 3, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:job:edit', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1052, '任务删除', 110, 4, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:job:remove', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1053, '状态修改', 110, 5, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:job:changeStatus', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1054, '任务导出', 110, 6, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:job:export', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1055, '生成查询', 116, 1, '#', '', '', '', 1, 0, 'F', '0', '0', 'tool:gen:query', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1056, '生成修改', 116, 2, '#', '', '', '', 1, 0, 'F', '0', '0', 'tool:gen:edit', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1057, '生成删除', 116, 3, '#', '', '', '', 1, 0, 'F', '0', '0', 'tool:gen:remove', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1058, '导入代码', 116, 4, '#', '', '', '', 1, 0, 'F', '0', '0', 'tool:gen:import', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1059, '预览代码', 116, 5, '#', '', '', '', 1, 0, 'F', '0', '0', 'tool:gen:preview', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1060, '生成代码', 116, 6, '#', '', '', '', 1, 0, 'F', '0', '0', 'tool:gen:code', '#', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2000, '药品信息', 1, 1, 'chstcmcatalog', 'system/chstcmcatalog/index', NULL, '', 1, 0, 'C', '0', '0', 'system:chstcmcatalog:list', '#', 'admin', '2024-12-08 16:46:53', '', NULL, '药品信息菜单');
INSERT INTO `sys_menu` VALUES (2001, '药品信息查询', 2000, 1, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'system:chstcmcatalog:query', '#', 'admin', '2024-12-08 16:46:53', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2002, '药品信息新增', 2000, 2, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'system:chstcmcatalog:add', '#', 'admin', '2024-12-08 16:46:53', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2003, '药品信息修改', 2000, 3, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'system:chstcmcatalog:edit', '#', 'admin', '2024-12-08 16:46:53', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2004, '药品信息删除', 2000, 4, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'system:chstcmcatalog:remove', '#', 'admin', '2024-12-08 16:46:53', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2005, '药品信息导出', 2000, 5, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'system:chstcmcatalog:export', '#', 'admin', '2024-12-08 16:46:53', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2006, '药品信息', 0, 1, 'medicine', NULL, NULL, '', 1, 0, 'M', '0', '0', NULL, 'documentation', 'admin', '2024-12-08 16:55:14', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2007, '药品信息', 2006, 1, 'chstcmcatalog', 'system/chstcmcatalog/index', NULL, '', 1, 0, 'C', '0', '0', 'system:chstcmcatalog:list', '#', 'admin', '2024-12-08 16:56:19', '', NULL, '药品信息菜单');
INSERT INTO `sys_menu` VALUES (2008, '药品信息查询', 2007, 1, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'system:chstcmcatalog:query', '#', 'admin', '2024-12-08 16:56:19', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2009, '药品信息新增', 2007, 2, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'system:chstcmcatalog:add', '#', 'admin', '2024-12-08 16:56:19', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2010, '药品信息修改', 2007, 3, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'system:chstcmcatalog:edit', '#', 'admin', '2024-12-08 16:56:19', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2011, '药品信息删除', 2007, 4, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'system:chstcmcatalog:remove', '#', 'admin', '2024-12-08 16:56:19', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2012, '药品信息导出', 2007, 5, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'system:chstcmcatalog:export', '#', 'admin', '2024-12-08 16:56:19', '', NULL, '');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `notice_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告标题',
  `notice_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` longblob NULL COMMENT '公告内容',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '通知公告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (1, '温馨提醒：2018-07-01 若依新版本发布啦', '2', 0xE696B0E78988E69CACE58685E5AEB9, '0', 'admin', '2024-12-08 16:29:05', '', NULL, '管理员');
INSERT INTO `sys_notice` VALUES (2, '维护通知：2018-07-01 若依系统凌晨维护', '1', 0xE7BBB4E68AA4E58685E5AEB9, '0', 'admin', '2024-12-08 16:29:05', '', NULL, '管理员');

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
  `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '模块标题',
  `business_type` int(11) NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求方式',
  `operator_type` int(11) NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '返回参数',
  `status` int(11) NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `cost_time` bigint(20) NULL DEFAULT 0 COMMENT '消耗时间',
  PRIMARY KEY (`oper_id`) USING BTREE,
  INDEX `idx_sys_oper_log_bt`(`business_type`) USING BTREE,
  INDEX `idx_sys_oper_log_s`(`status`) USING BTREE,
  INDEX `idx_sys_oper_log_ot`(`oper_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 113 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
INSERT INTO `sys_oper_log` VALUES (100, '代码生成', 6, 'com.ruoyi.generator.controller.GenController.importTableSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/importTable', '127.0.0.1', '内网IP', '{\"tables\":\"chstcmcatalog\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-12-08 16:45:23', 92);
INSERT INTO `sys_oper_log` VALUES (101, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', '研发部门', '/tool/gen', '127.0.0.1', '内网IP', '{\"businessName\":\"chstcmcatalog\",\"className\":\"chstcmcatalog\",\"columns\":[{\"capJavaField\":\"ID\",\"columnComment\":\"ID\",\"columnId\":1,\"columnName\":\"ID\",\"columnType\":\"int\",\"createBy\":\"admin\",\"createTime\":\"2024-12-08 16:45:23\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"1\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"ID\",\"javaType\":\"Long\",\"list\":true,\"params\":{},\"pk\":true,\"query\":true,\"queryType\":\"EQ\",\"required\":false,\"sort\":1,\"superColumn\":false,\"tableId\":1,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"Level1\",\"columnComment\":\"一级分类\",\"columnId\":2,\"columnName\":\"Level1\",\"columnType\":\"varchar(10)\",\"createBy\":\"admin\",\"createTime\":\"2024-12-08 16:45:23\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"1\",\"javaField\":\"Level1\",\"javaType\":\"String\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":true,\"sort\":2,\"superColumn\":false,\"tableId\":1,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"Level2\",\"columnComment\":\"二级分类，为空时应填入\\\"未分类\\\"\",\"columnId\":3,\"columnName\":\"Level2\",\"columnType\":\"varchar(20)\",\"createBy\":\"admin\",\"createTime\":\"2024-12-08 16:45:23\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"1\",\"javaField\":\"Level2\",\"javaType\":\"String\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":true,\"sort\":3,\"superColumn\":false,\"tableId\":1,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"Level3\",\"columnComment\":\"三级分类，为空时应填入\\\"未分类\\\"\",\"columnId\":4,\"columnName\":\"Level3\",\"columnType\":\"varchar(20)\",\"createBy\":\"admin\",\"createTime\":\"2024-12-08 16:45:23\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"i', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-12-08 16:45:59', 47);
INSERT INTO `sys_oper_log` VALUES (102, '代码生成', 8, 'com.ruoyi.generator.controller.GenController.batchGenCode()', 'GET', 1, 'admin', '研发部门', '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{\"tables\":\"chstcmcatalog\"}', NULL, 0, NULL, '2024-12-08 16:46:08', 211);
INSERT INTO `sys_oper_log` VALUES (103, '代码生成', 8, 'com.ruoyi.generator.controller.GenController.batchGenCode()', 'GET', 1, 'admin', '研发部门', '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{\"tables\":\"chstcmcatalog\"}', NULL, 0, NULL, '2024-12-08 16:46:08', 45);
INSERT INTO `sys_oper_log` VALUES (104, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.add()', 'POST', 1, 'admin', '研发部门', '/system/menu', '127.0.0.1', '内网IP', '{\"children\":[],\"createBy\":\"admin\",\"icon\":\"documentation\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuName\":\"药品信息\",\"menuType\":\"M\",\"orderNum\":1,\"params\":{},\"parentId\":0,\"path\":\"medicine\",\"status\":\"0\",\"visible\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-12-08 16:55:15', 135);
INSERT INTO `sys_oper_log` VALUES (105, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', '研发部门', '/tool/gen', '127.0.0.1', '内网IP', '{\"businessName\":\"chstcmcatalog\",\"className\":\"chstcmcatalog\",\"columns\":[{\"capJavaField\":\"ID\",\"columnComment\":\"ID\",\"columnId\":1,\"columnName\":\"ID\",\"columnType\":\"int\",\"createBy\":\"admin\",\"createTime\":\"2024-12-08 16:45:23\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"1\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"ID\",\"javaType\":\"Long\",\"list\":true,\"params\":{},\"pk\":true,\"query\":true,\"queryType\":\"EQ\",\"required\":false,\"sort\":1,\"superColumn\":false,\"tableId\":1,\"updateBy\":\"\",\"updateTime\":\"2024-12-08 16:45:59\",\"usableColumn\":false},{\"capJavaField\":\"Level1\",\"columnComment\":\"一级分类\",\"columnId\":2,\"columnName\":\"Level1\",\"columnType\":\"varchar(10)\",\"createBy\":\"admin\",\"createTime\":\"2024-12-08 16:45:23\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"1\",\"javaField\":\"Level1\",\"javaType\":\"String\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":true,\"sort\":2,\"superColumn\":false,\"tableId\":1,\"updateBy\":\"\",\"updateTime\":\"2024-12-08 16:45:59\",\"usableColumn\":false},{\"capJavaField\":\"Level2\",\"columnComment\":\"二级分类\",\"columnId\":3,\"columnName\":\"Level2\",\"columnType\":\"varchar(20)\",\"createBy\":\"admin\",\"createTime\":\"2024-12-08 16:45:23\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"1\",\"javaField\":\"Level2\",\"javaType\":\"String\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":true,\"sort\":3,\"superColumn\":false,\"tableId\":1,\"updateBy\":\"\",\"updateTime\":\"2024-12-08 16:45:59\",\"usableColumn\":false},{\"capJavaField\":\"Level3\",\"columnComment\":\"三级分类\",\"columnId\":4,\"columnName\":\"Level3\",\"columnType\":\"varchar(20)\",\"createBy\":\"admin\",\"createTime\":\"2024-12-08 16:45:23\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":fals', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-12-08 16:55:43', 221);
INSERT INTO `sys_oper_log` VALUES (106, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', '研发部门', '/tool/gen', '127.0.0.1', '内网IP', '{\"businessName\":\"chstcmcatalog\",\"className\":\"chstcmcatalog\",\"columns\":[{\"capJavaField\":\"ID\",\"columnComment\":\"ID\",\"columnId\":1,\"columnName\":\"ID\",\"columnType\":\"int\",\"createBy\":\"admin\",\"createTime\":\"2024-12-08 16:45:23\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"1\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"ID\",\"javaType\":\"Long\",\"list\":true,\"params\":{},\"pk\":true,\"query\":true,\"queryType\":\"EQ\",\"required\":false,\"sort\":1,\"superColumn\":false,\"tableId\":1,\"updateBy\":\"\",\"updateTime\":\"2024-12-08 16:55:42\",\"usableColumn\":false},{\"capJavaField\":\"Level1\",\"columnComment\":\"一级分类\",\"columnId\":2,\"columnName\":\"Level1\",\"columnType\":\"varchar(10)\",\"createBy\":\"admin\",\"createTime\":\"2024-12-08 16:45:23\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"1\",\"javaField\":\"Level1\",\"javaType\":\"String\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":true,\"sort\":2,\"superColumn\":false,\"tableId\":1,\"updateBy\":\"\",\"updateTime\":\"2024-12-08 16:55:42\",\"usableColumn\":false},{\"capJavaField\":\"Level2\",\"columnComment\":\"二级分类\",\"columnId\":3,\"columnName\":\"Level2\",\"columnType\":\"varchar(20)\",\"createBy\":\"admin\",\"createTime\":\"2024-12-08 16:45:23\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"1\",\"javaField\":\"Level2\",\"javaType\":\"String\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":true,\"sort\":3,\"superColumn\":false,\"tableId\":1,\"updateBy\":\"\",\"updateTime\":\"2024-12-08 16:55:42\",\"usableColumn\":false},{\"capJavaField\":\"Level3\",\"columnComment\":\"三级分类\",\"columnId\":4,\"columnName\":\"Level3\",\"columnType\":\"varchar(20)\",\"createBy\":\"admin\",\"createTime\":\"2024-12-08 16:45:23\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":fals', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-12-08 16:55:56', 29);
INSERT INTO `sys_oper_log` VALUES (107, '代码生成', 8, 'com.ruoyi.generator.controller.GenController.batchGenCode()', 'GET', 1, 'admin', '研发部门', '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{\"tables\":\"chstcmcatalog\"}', NULL, 0, NULL, '2024-12-08 16:55:58', 210);
INSERT INTO `sys_oper_log` VALUES (108, '代码生成', 8, 'com.ruoyi.generator.controller.GenController.batchGenCode()', 'GET', 1, 'admin', '研发部门', '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{\"tables\":\"chstcmcatalog\"}', NULL, 0, NULL, '2024-12-08 16:55:58', 43);
INSERT INTO `sys_oper_log` VALUES (109, '药品信息', 5, 'com.ruoyi.system.controller.chstcmcatalogController.export()', 'POST', 1, 'admin', '研发部门', '/system/chstcmcatalog/export', '127.0.0.1', '内网IP', '{\"pageSize\":\"10\",\"pageNum\":\"1\"}', NULL, 0, NULL, '2024-12-08 16:59:34', 1466);
INSERT INTO `sys_oper_log` VALUES (110, '药品信息', 1, 'com.ruoyi.system.controller.chstcmcatalogController.add()', 'POST', 1, 'admin', '研发部门', '/system/chstcmcatalog', '127.0.0.1', '内网IP', '{\"params\":{}}', NULL, 1, '\r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'\' at line 1\r\n### The error may exist in file [C:\\Users\\huangshaozheng\\Desktop\\RuoYi-Vue-master\\ruoyi-system\\target\\classes\\mapper\\system\\chstcmcatalogMapper.xml]\r\n### The error may involve com.ruoyi.system.mapper.chstcmcatalogMapper.insertchstcmcatalog-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into chstcmcatalog\r\n### Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'\' at line 1\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'\' at line 1', '2024-12-08 17:02:03', 69);
INSERT INTO `sys_oper_log` VALUES (111, '药品信息', 1, 'com.ruoyi.system.controller.chstcmcatalogController.add()', 'POST', 1, 'admin', '研发部门', '/system/chstcmcatalog', '127.0.0.1', '内网IP', '{\"params\":{}}', NULL, 1, '\r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'\' at line 1\r\n### The error may exist in file [C:\\Users\\huangshaozheng\\Desktop\\RuoYi-Vue-master\\ruoyi-system\\target\\classes\\mapper\\system\\chstcmcatalogMapper.xml]\r\n### The error may involve com.ruoyi.system.mapper.chstcmcatalogMapper.insertchstcmcatalog-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into chstcmcatalog\r\n### Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'\' at line 1\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'\' at line 1', '2024-12-08 17:07:09', 24);
INSERT INTO `sys_oper_log` VALUES (112, '药品信息', 1, 'com.ruoyi.system.controller.chstcmcatalogController.add()', 'POST', 1, 'admin', '研发部门', '/system/chstcmcatalog', '127.0.0.1', '内网IP', '{\"params\":{}}', NULL, 1, '\r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'\' at line 1\r\n### The error may exist in file [C:\\Users\\huangshaozheng\\Desktop\\RuoYi-Vue-master\\ruoyi-system\\target\\classes\\mapper\\system\\chstcmcatalogMapper.xml]\r\n### The error may involve com.ruoyi.system.mapper.chstcmcatalogMapper.insertchstcmcatalog-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into chstcmcatalog\r\n### Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'\' at line 1\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'\' at line 1', '2024-12-08 18:13:25', 79);
INSERT INTO `sys_oper_log` VALUES (113, '药品信息', 1, 'com.ruoyi.system.controller.chstcmcatalogController.add()', 'POST', 1, 'admin', '研发部门', '/system/chstcmcatalog', '127.0.0.1', '内网IP', '{\"params\":{}}', NULL, 1, '\r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'\' at line 1\r\n### The error may exist in file [D:\\project\\ruoyi类型的项目\\50若依正删改查\\RuoYi-Vue-master\\ruoyi-system\\target\\classes\\mapper\\system\\chstcmcatalogMapper.xml]\r\n### The error may involve com.ruoyi.system.mapper.chstcmcatalogMapper.insertchstcmcatalog-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into chstcmcatalog\r\n### Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'\' at line 1\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'\' at line 1', '2024-12-08 18:47:22', 35);
INSERT INTO `sys_oper_log` VALUES (114, '药品信息', 1, 'com.ruoyi.system.controller.chstcmcatalogController.add()', 'POST', 1, 'admin', '研发部门', '/system/chstcmcatalog', '127.0.0.1', '内网IP', '{\"params\":{}}', NULL, 1, '\r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'\' at line 1\r\n### The error may exist in file [D:\\project\\ruoyi类型的项目\\50若依正删改查\\RuoYi-Vue-master\\ruoyi-system\\target\\classes\\mapper\\system\\chstcmcatalogMapper.xml]\r\n### The error may involve com.ruoyi.system.mapper.chstcmcatalogMapper.insertchstcmcatalog-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into chstcmcatalog\r\n### Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'\' at line 1\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'\' at line 1', '2024-12-08 18:47:39', 4);
INSERT INTO `sys_oper_log` VALUES (115, '药品信息', 1, 'com.ruoyi.system.controller.chstcmcatalogController.add()', 'POST', 1, 'admin', '研发部门', '/system/chstcmcatalog', '127.0.0.1', '内网IP', '{\"params\":{}}', NULL, 1, '\r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'\' at line 1\r\n### The error may exist in file [D:\\project\\ruoyi类型的项目\\50若依正删改查\\RuoYi-Vue-master\\ruoyi-system\\target\\classes\\mapper\\system\\chstcmcatalogMapper.xml]\r\n### The error may involve com.ruoyi.system.mapper.chstcmcatalogMapper.insertchstcmcatalog-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into chstcmcatalog\r\n### Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'\' at line 1\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'\' at line 1', '2024-12-08 18:49:31', 58);
INSERT INTO `sys_oper_log` VALUES (116, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.synchDb()', 'GET', 1, 'admin', '研发部门', '/tool/gen/synchDb/chstcmcatalog', '127.0.0.1', '内网IP', '{}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-12-08 18:50:51', 101);
INSERT INTO `sys_oper_log` VALUES (117, '药品信息', 1, 'com.ruoyi.system.controller.chstcmcatalogController.add()', 'POST', 1, 'admin', '研发部门', '/system/chstcmcatalog', '127.0.0.1', '内网IP', '{\"constrain\":\"123\",\"drugName\":\"123\",\"level1\":\"123\",\"level2\":\"123\",\"level3\":\"123\",\"params\":{}}', NULL, 1, '\r\n### Error updating database.  Cause: java.sql.SQLException: Field \'type\' doesn\'t have a default value\r\n### The error may exist in file [D:\\project\\ruoyi类型的项目\\50若依正删改查\\RuoYi-Vue-master\\ruoyi-system\\target\\classes\\mapper\\system\\chstcmcatalogMapper.xml]\r\n### The error may involve com.ruoyi.system.mapper.chstcmcatalogMapper.insertchstcmcatalog-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into chstcmcatalog          ( level1,             level2,             level3,                          drugName,             constrain )           values ( ?,             ?,             ?,                          ?,             ? )\r\n### Cause: java.sql.SQLException: Field \'type\' doesn\'t have a default value\n; Field \'type\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'type\' doesn\'t have a default value', '2024-12-08 18:54:53', 61);
INSERT INTO `sys_oper_log` VALUES (118, '药品信息', 1, 'com.ruoyi.system.controller.chstcmcatalogController.add()', 'POST', 1, 'admin', '研发部门', '/system/chstcmcatalog', '127.0.0.1', '内网IP', '{\"constrain\":\"123\",\"drugName\":\"123\",\"level1\":\"123\",\"level2\":\"123\",\"level3\":\"123\",\"params\":{}}', NULL, 1, '\r\n### Error updating database.  Cause: java.sql.SQLException: Field \'type\' doesn\'t have a default value\r\n### The error may exist in file [D:\\project\\ruoyi类型的项目\\50若依正删改查\\RuoYi-Vue-master\\ruoyi-system\\target\\classes\\mapper\\system\\chstcmcatalogMapper.xml]\r\n### The error may involve com.ruoyi.system.mapper.chstcmcatalogMapper.insertchstcmcatalog-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into chstcmcatalog          ( level1,             level2,             level3,                          drugName,             constrain )           values ( ?,             ?,             ?,                          ?,             ? )\r\n### Cause: java.sql.SQLException: Field \'type\' doesn\'t have a default value\n; Field \'type\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'type\' doesn\'t have a default value', '2024-12-08 18:55:15', 23);
INSERT INTO `sys_oper_log` VALUES (119, '药品信息', 1, 'com.ruoyi.system.controller.chstcmcatalogController.add()', 'POST', 1, 'admin', '研发部门', '/system/chstcmcatalog', '127.0.0.1', '内网IP', '{\"constrain\":\"123\",\"drugName\":\"123\",\"id\":642,\"level1\":\"123\",\"level2\":\"123\",\"level3\":\"123\",\"params\":{}}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-12-08 18:56:56', 44);
INSERT INTO `sys_oper_log` VALUES (120, '药品信息', 2, 'com.ruoyi.system.controller.chstcmcatalogController.edit()', 'PUT', 1, 'admin', '研发部门', '/system/chstcmcatalog', '127.0.0.1', '内网IP', '{\"constrain\":\"123\",\"drugName\":\"123\",\"id\":642,\"level1\":\"阿斯达岁的\",\"level2\":\"123\",\"level3\":\"123\",\"params\":{},\"type\":\"1\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-12-08 19:00:31', 118);
INSERT INTO `sys_oper_log` VALUES (121, '药品信息', 2, 'com.ruoyi.system.controller.chstcmcatalogController.edit()', 'PUT', 1, 'admin', '研发部门', '/system/chstcmcatalog', '127.0.0.1', '内网IP', '{\"constrain\":\"123\",\"drugName\":\"123\",\"id\":642,\"level1\":\"阿斯达岁的\",\"level2\":\"asdddd爱爱爱\",\"level3\":\"123\",\"params\":{},\"type\":\"1\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-12-08 19:00:53', 80);
INSERT INTO `sys_oper_log` VALUES (122, '药品信息', 2, 'com.ruoyi.system.controller.chstcmcatalogController.edit()', 'PUT', 1, 'admin', '研发部门', '/system/chstcmcatalog', '127.0.0.1', '内网IP', '{\"constrain\":\"123\",\"drugName\":\"123\",\"id\":642,\"level1\":\"阿斯达岁的\",\"level2\":\"asdddd爱爱爱\",\"level3\":\"阿斯达岁的\",\"params\":{},\"type\":\"1\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-12-08 19:00:58', 14);
INSERT INTO `sys_oper_log` VALUES (123, '药品信息', 2, 'com.ruoyi.system.controller.chstcmcatalogController.edit()', 'PUT', 1, 'admin', '研发部门', '/system/chstcmcatalog', '127.0.0.1', '内网IP', '{\"constrain\":\"123\",\"drugName\":\"爱爱爱\",\"id\":642,\"level1\":\"阿斯达岁的\",\"level2\":\"asdddd爱爱爱\",\"level3\":\"阿斯达岁的\",\"params\":{},\"type\":\"1\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-12-08 19:01:08', 6);
INSERT INTO `sys_oper_log` VALUES (124, '药品信息', 3, 'com.ruoyi.system.controller.chstcmcatalogController.remove()', 'DELETE', 1, 'admin', '研发部门', '/system/chstcmcatalog/642', '127.0.0.1', '内网IP', '[642]', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-12-08 19:01:12', 26);

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位名称',
  `post_sort` int(11) NOT NULL COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '岗位信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES (1, 'ceo', '董事长', 1, '0', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_post` VALUES (2, 'se', '项目经理', 2, '0', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_post` VALUES (3, 'hr', '人力资源', 3, '0', 'admin', '2024-12-08 16:29:05', '', NULL, '');
INSERT INTO `sys_post` VALUES (4, 'user', '普通员工', 4, '0', 'admin', '2024-12-08 16:29:05', '', NULL, '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(11) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `menu_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '菜单树选择项是否关联显示',
  `dept_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '部门树选择项是否关联显示',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', 1, '1', 1, 1, '0', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '超级管理员');
INSERT INTO `sys_role` VALUES (2, '普通角色', 'common', 2, '2', 1, 1, '0', '0', 'admin', '2024-12-08 16:29:05', '', NULL, '普通角色');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和部门关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES (2, 100);
INSERT INTO `sys_role_dept` VALUES (2, 101);
INSERT INTO `sys_role_dept` VALUES (2, 105);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2);
INSERT INTO `sys_role_menu` VALUES (2, 3);
INSERT INTO `sys_role_menu` VALUES (2, 4);
INSERT INTO `sys_role_menu` VALUES (2, 100);
INSERT INTO `sys_role_menu` VALUES (2, 101);
INSERT INTO `sys_role_menu` VALUES (2, 102);
INSERT INTO `sys_role_menu` VALUES (2, 103);
INSERT INTO `sys_role_menu` VALUES (2, 104);
INSERT INTO `sys_role_menu` VALUES (2, 105);
INSERT INTO `sys_role_menu` VALUES (2, 106);
INSERT INTO `sys_role_menu` VALUES (2, 107);
INSERT INTO `sys_role_menu` VALUES (2, 108);
INSERT INTO `sys_role_menu` VALUES (2, 109);
INSERT INTO `sys_role_menu` VALUES (2, 110);
INSERT INTO `sys_role_menu` VALUES (2, 111);
INSERT INTO `sys_role_menu` VALUES (2, 112);
INSERT INTO `sys_role_menu` VALUES (2, 113);
INSERT INTO `sys_role_menu` VALUES (2, 114);
INSERT INTO `sys_role_menu` VALUES (2, 115);
INSERT INTO `sys_role_menu` VALUES (2, 116);
INSERT INTO `sys_role_menu` VALUES (2, 117);
INSERT INTO `sys_role_menu` VALUES (2, 500);
INSERT INTO `sys_role_menu` VALUES (2, 501);
INSERT INTO `sys_role_menu` VALUES (2, 1000);
INSERT INTO `sys_role_menu` VALUES (2, 1001);
INSERT INTO `sys_role_menu` VALUES (2, 1002);
INSERT INTO `sys_role_menu` VALUES (2, 1003);
INSERT INTO `sys_role_menu` VALUES (2, 1004);
INSERT INTO `sys_role_menu` VALUES (2, 1005);
INSERT INTO `sys_role_menu` VALUES (2, 1006);
INSERT INTO `sys_role_menu` VALUES (2, 1007);
INSERT INTO `sys_role_menu` VALUES (2, 1008);
INSERT INTO `sys_role_menu` VALUES (2, 1009);
INSERT INTO `sys_role_menu` VALUES (2, 1010);
INSERT INTO `sys_role_menu` VALUES (2, 1011);
INSERT INTO `sys_role_menu` VALUES (2, 1012);
INSERT INTO `sys_role_menu` VALUES (2, 1013);
INSERT INTO `sys_role_menu` VALUES (2, 1014);
INSERT INTO `sys_role_menu` VALUES (2, 1015);
INSERT INTO `sys_role_menu` VALUES (2, 1016);
INSERT INTO `sys_role_menu` VALUES (2, 1017);
INSERT INTO `sys_role_menu` VALUES (2, 1018);
INSERT INTO `sys_role_menu` VALUES (2, 1019);
INSERT INTO `sys_role_menu` VALUES (2, 1020);
INSERT INTO `sys_role_menu` VALUES (2, 1021);
INSERT INTO `sys_role_menu` VALUES (2, 1022);
INSERT INTO `sys_role_menu` VALUES (2, 1023);
INSERT INTO `sys_role_menu` VALUES (2, 1024);
INSERT INTO `sys_role_menu` VALUES (2, 1025);
INSERT INTO `sys_role_menu` VALUES (2, 1026);
INSERT INTO `sys_role_menu` VALUES (2, 1027);
INSERT INTO `sys_role_menu` VALUES (2, 1028);
INSERT INTO `sys_role_menu` VALUES (2, 1029);
INSERT INTO `sys_role_menu` VALUES (2, 1030);
INSERT INTO `sys_role_menu` VALUES (2, 1031);
INSERT INTO `sys_role_menu` VALUES (2, 1032);
INSERT INTO `sys_role_menu` VALUES (2, 1033);
INSERT INTO `sys_role_menu` VALUES (2, 1034);
INSERT INTO `sys_role_menu` VALUES (2, 1035);
INSERT INTO `sys_role_menu` VALUES (2, 1036);
INSERT INTO `sys_role_menu` VALUES (2, 1037);
INSERT INTO `sys_role_menu` VALUES (2, 1038);
INSERT INTO `sys_role_menu` VALUES (2, 1039);
INSERT INTO `sys_role_menu` VALUES (2, 1040);
INSERT INTO `sys_role_menu` VALUES (2, 1041);
INSERT INTO `sys_role_menu` VALUES (2, 1042);
INSERT INTO `sys_role_menu` VALUES (2, 1043);
INSERT INTO `sys_role_menu` VALUES (2, 1044);
INSERT INTO `sys_role_menu` VALUES (2, 1045);
INSERT INTO `sys_role_menu` VALUES (2, 1046);
INSERT INTO `sys_role_menu` VALUES (2, 1047);
INSERT INTO `sys_role_menu` VALUES (2, 1048);
INSERT INTO `sys_role_menu` VALUES (2, 1049);
INSERT INTO `sys_role_menu` VALUES (2, 1050);
INSERT INTO `sys_role_menu` VALUES (2, 1051);
INSERT INTO `sys_role_menu` VALUES (2, 1052);
INSERT INTO `sys_role_menu` VALUES (2, 1053);
INSERT INTO `sys_role_menu` VALUES (2, 1054);
INSERT INTO `sys_role_menu` VALUES (2, 1055);
INSERT INTO `sys_role_menu` VALUES (2, 1056);
INSERT INTO `sys_role_menu` VALUES (2, 1057);
INSERT INTO `sys_role_menu` VALUES (2, 1058);
INSERT INTO `sys_role_menu` VALUES (2, 1059);
INSERT INTO `sys_role_menu` VALUES (2, 1060);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '密码',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 103, 'admin', '若依', '00', 'ry@163.com', '15888888888', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2024-12-08 18:45:08', 'admin', '2024-12-08 16:29:05', '', '2024-12-08 18:45:07', '管理员');
INSERT INTO `sys_user` VALUES (2, 105, 'ry', '若依', '00', 'ry@qq.com', '15666666666', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2024-12-08 16:29:05', 'admin', '2024-12-08 16:29:05', '', NULL, '测试员');

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`, `post_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户与岗位关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES (1, 1);
INSERT INTO `sys_user_post` VALUES (2, 2);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);

SET FOREIGN_KEY_CHECKS = 1;
