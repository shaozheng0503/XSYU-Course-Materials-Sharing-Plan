-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: cinema
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity` (
  `id` int NOT NULL AUTO_INCREMENT,
  `activity_name` varchar(45) NOT NULL,
  `a_description` varchar(255) NOT NULL,
  `end_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `coupon_id` int DEFAULT NULL,
  `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `target_amount` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_movie`
--

DROP TABLE IF EXISTS `activity_movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity_movie` (
  `activity_id` int DEFAULT NULL,
  `movie_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_movie`
--

LOCK TABLES `activity_movie` WRITE;
/*!40000 ALTER TABLE `activity_movie` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity_movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `banner`
--

DROP TABLE IF EXISTS `banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `banner` (
  `img1` varchar(250) NOT NULL,
  `navi1` varchar(1024) NOT NULL,
  `img2` varchar(250) NOT NULL,
  `navi2` varchar(1024) NOT NULL,
  `img3` varchar(1024) NOT NULL,
  `navi3` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banner`
--

LOCK TABLES `banner` WRITE;
/*!40000 ALTER TABLE `banner` DISABLE KEYS */;
INSERT INTO `banner` VALUES ('https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1607701553200&di=7540279c063017ab02a49627d1f29482&imgtype=0&src=http%3A%2F%2Ff.hiphotos.baidu.com%2Fbaike%2Fpic%2Fitem%2Fb17eca8065380cd7b45cd6d3a244ad3459828149.jpg','1','https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1607702208765&di=5f51e88ef30c6c360597d61dcf4e3bd9&imgtype=0&src=http%3A%2F%2Fimage14.m1905.cn%2Fuploadfile%2F2014%2F0710%2F20140710025413610632.jpg','2','https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1607703611084&di=c3f6d9f5d23a1521a58f654d687f4966&imgtype=0&src=http%3A%2F%2Fimg.alicdn.com%2Fimgextra%2Fi4%2F2968329118%2FTB248f3mIjI8KJjSsppXXXbyVXa_%2521%25212968329118-0-weitao.jpg','4');
/*!40000 ALTER TABLE `banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `card_type`
--

DROP TABLE IF EXISTS `card_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `card_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `card_name` varchar(45) NOT NULL,
  `price` double NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  `top_up_target` double NOT NULL,
  `top_up_discount` double NOT NULL,
  `ticket_target` double NOT NULL,
  `ticket_discount` double NOT NULL,
  `state` int DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card_type`
--

LOCK TABLES `card_type` WRITE;
/*!40000 ALTER TABLE `card_type` DISABLE KEYS */;
INSERT INTO `card_type` VALUES (1,'会员卡',20,'#409EFF',100,10,999,0,1),(2,'高级会员卡',50,'#7E57C2',200,25,999,0,1),(3,'新年卡',9.9,'#F56C6C',100,6.6,999,0,1),(4,'至尊黑金卡',100,'#303133',400,100,999,0,1);
/*!40000 ALTER TABLE `card_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consume_history`
--

DROP TABLE IF EXISTS `consume_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consume_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `money` double DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `consumeType` varchar(20) DEFAULT NULL,
  `type` int DEFAULT NULL,
  `contentId` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consume_history`
--

LOCK TABLES `consume_history` WRITE;
/*!40000 ALTER TABLE `consume_history` DISABLE KEYS */;
INSERT INTO `consume_history` VALUES (5,2,9.9,0,'银行卡',102,1);
/*!40000 ALTER TABLE `consume_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon`
--

DROP TABLE IF EXISTS `coupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coupon` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `target_amount` float DEFAULT NULL,
  `discount_amount` float DEFAULT NULL,
  `start_time` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `end_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon`
--

LOCK TABLES `coupon` WRITE;
/*!40000 ALTER TABLE `coupon` DISABLE KEYS */;
INSERT INTO `coupon` VALUES (1,'小优惠券','小优惠券',20,1,'2019-12-31 16:00:00','2020-12-31 16:00:00'),(2,'优惠券','优惠券',20,2,'2019-12-31 16:00:00','2020-12-31 16:00:00'),(3,'大优惠券','大优惠券',20,4,'2019-12-31 16:00:00','2020-12-31 16:00:00'),(4,'半价券','半价券',40,20,'2019-12-31 16:00:00','2020-12-31 16:00:00'),(5,'免单券','免单券',40,40,'2020-12-11 13:56:00','2020-12-31 16:00:00'),(6,'欢乐券','欢乐券',20,8,'2020-12-11 13:56:00','2020-12-31 16:00:00');
/*!40000 ALTER TABLE `coupon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon_user`
--

DROP TABLE IF EXISTS `coupon_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coupon_user` (
  `coupon_id` int NOT NULL,
  `user_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon_user`
--

LOCK TABLES `coupon_user` WRITE;
/*!40000 ALTER TABLE `coupon_user` DISABLE KEYS */;
INSERT INTO `coupon_user` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(6,1);
/*!40000 ALTER TABLE `coupon_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hall`
--

DROP TABLE IF EXISTS `hall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hall` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `seats` varchar(1024) DEFAULT NULL,
  `scale` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hall`
--

LOCK TABLES `hall` WRITE;
/*!40000 ALTER TABLE `hall` DISABLE KEYS */;
INSERT INTO `hall` VALUES (1,'1号厅','[[0,-1,0,0,0,0,0,0,0,0],[0,-1,0,0,0,0,0,0,0,0],[0,-1,0,0,0,0,0,0,0,0],[0,-1,0,0,0,0,0,0,0,0],[0,-1,0,0,0,0,0,0,0,0]]',2),(2,'2号厅','[[0,-1,0,0,0,0,0,0,0,0,-1,0],[0,-1,0,0,0,0,0,0,0,0,-1,0],[0,-1,0,0,0,0,0,0,0,0,-1,0],[0,-1,0,0,0,0,0,0,0,0,-1,0],[0,-1,0,0,0,0,0,0,0,0,-1,0],[0,-1,0,0,0,0,0,0,0,0,-1,0],[0,-1,0,0,0,0,0,0,0,0,-1,0],[0,-1,0,0,0,0,0,0,0,0,-1,0]]',1);
/*!40000 ALTER TABLE `hall` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie` (
  `id` int NOT NULL AUTO_INCREMENT,
  `poster_url` varchar(255) DEFAULT NULL,
  `director` varchar(255) DEFAULT NULL,
  `screen_writer` varchar(255) DEFAULT NULL,
  `starring` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `length` int NOT NULL,
  `start_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(255) NOT NULL,
  `description` text,
  `status` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (1,'https://p0.meituan.net/movie/ba575bc969e11579bfd089c7e417e8d3420469.jpg@464w_644h_1e_1c','路阳','陈舒 / 路阳','张震 / 刘诗诗 / 王千源 / 李东学','剧情 / 动作 / 武侠 / 古装','中国','国语',111,'2020-12-11 13:26:28','绣春刀 ','明崇祯皇帝登基，大太监魏忠贤及其“阉党”被锦衣卫倾巢覆灭。锦衣卫中以卢剑星、沈炼和靳一川三结拜兄弟最为勇猛正直。老大卢剑星为了升官百户，沈炼为替教坊司妓女周妙彤赎身，三弟靳一川为摆脱浪人丁修的纠缠，三兄弟决定接受东厂都督赵靖忠（聂远 饰）追杀魏忠贤的秘密行动。行动中三人遭到魏忠贤义女魏廷带队阻拦，一场腥风血雨后魏忠贤尸首被带回，卢剑星如愿以偿升官百户，本以为三人夙愿皆能实现，但没想到沈炼竟然隐瞒了一个天大的秘密，并且引起了大臣韩旷的怀疑。此后三兄弟都面临着生命危险，沈炼终于说出真相但为时已晚，与一川相好的医馆女子和周妙彤都被牵连其中。面对危机，沈炼决意带着兄弟、爱人离开京城，朝廷腐朽混乱，小人物的命运将何去何从？',0),(2,'https://p0.meituan.net/movie/3fa2a9f0055fbbbc6d26b79da7de3bfa519032.jpg@464w_644h_1e_1c','韩寒','韩寒','冯绍峰 / 陈柏霖 / 钟汉良 / 王珞丹','剧情','中国','国语',106,'2020-12-11 13:26:28','后会无期','故事开始于虚构的中国最东边的小岛“东极岛”，智力低下的青年胡生讲述了关于他的两个哥们儿马浩汉和江河的故事。浩汉在外闯荡多年后回到故乡，却发现全岛面临着拆迁问题，而他最好的朋友，岛上唯一的老师江河也将被调到更为偏远的地区。于是 浩汉做出了一个决定，那就是开车送江河去三千多公里外的学校报到。兄弟三人踏上了前所未有的旅程，途中他们见了当临时演员的童年玩伴周沫，误入歧途的苏米，还有浩汉心仪多年从未见面的笔友刘莺莺，以及不羁的摩托车手阿吕。看似平常的旅程却暗藏伏笔，浩汉和江河最终走到了终点，开始了截然不同的人生轨迹。',0),(3,'https://p1.meituan.net/moviemachine/a448ca6a5f4dafb88067722303ca0cfd96002.jpg@464w_644h_1e_1c','管虎','管虎 / 葛瑞 / 黄东斌','王千源 / 张译 / 姜武 / 黄志忠','剧情 /  战争 / 历史','中国','国语',147,'2020-12-11 13:26:28','八佰','淞沪会战后期，中国军队第88师524团团附谢晋元临危受命，率领400余名官兵（外界称“八百壮士”），坚守闸北四行仓库，掩护主力部队撤退。“八百壮士”抱定为国捐躯的决心，以弹丸之地抗击侵略者，激战四昼夜，打退敌人十余次疯狂进攻。其战斗事迹之英勇，爱国气节之豪壮，振奋国人，震惊世界。',0),(4,'https://p1.meituan.net/movie/e67d52e84be73faa449d19db5dc2b50d394804.jpg@464w_644h_1e_1c','乔斯·韦登','乔斯·韦登 / 斯坦·李 / 杰克·科比','小罗伯特·唐尼 / 克里斯·海姆斯沃斯 / 斯嘉丽·约翰逊','动作 / 科幻 / 奇幻 / 冒险','美国','英语',142,'2020-12-09 16:00:00','复仇者联盟2：奥创纪元','托尼·斯塔克试图重启一个已经废弃的维和项目，不料该项目却成为危机导火索。世上最强大的超级英雄：钢铁侠、美国队长、雷神、绿巨人、黑寡妇和鹰眼，不得不接受终极考验，拯救危在旦夕的地球。神秘反派奥创逐渐崛起，超级英雄们必须重新集结，竭力阻止奥创实施人类灭绝计划。战斗中，复仇者联盟成员们还遇到了两个新人物：旺达·马克西莫夫和皮特罗·马克西莫夫，以及以全新面目归来的老朋友：幻视。 世界秩序濒临崩溃，复仇者联盟必须阻止奥创的阴谋。强敌不断出现，一场空前绝后的全球浩战即将展开……',0);
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_like`
--

DROP TABLE IF EXISTS `movie_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_like` (
  `movie_id` int NOT NULL,
  `user_id` int NOT NULL,
  `like_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`movie_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_like`
--

LOCK TABLES `movie_like` WRITE;
/*!40000 ALTER TABLE `movie_like` DISABLE KEYS */;
/*!40000 ALTER TABLE `movie_like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refund`
--

DROP TABLE IF EXISTS `refund`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refund` (
  `id` int NOT NULL AUTO_INCREMENT,
  `movie_id` int NOT NULL,
  `time` double NOT NULL,
  `discount` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refund`
--

LOCK TABLES `refund` WRITE;
/*!40000 ALTER TABLE `refund` DISABLE KEYS */;
/*!40000 ALTER TABLE `refund` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedule` (
  `id` int NOT NULL AUTO_INCREMENT,
  `hall_id` int NOT NULL,
  `movie_id` int NOT NULL,
  `start_time` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `end_time` timestamp NOT NULL,
  `fare` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `user_id` int DEFAULT NULL,
  `schedule_id` int DEFAULT NULL,
  `column_index` int DEFAULT NULL,
  `row_index` int DEFAULT NULL,
  `state` tinyint DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `actual_pay` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topup_history`
--

DROP TABLE IF EXISTS `topup_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topup_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `money` double DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `balance` double DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topup_history`
--

LOCK TABLES `topup_history` WRITE;
/*!40000 ALTER TABLE `topup_history` DISABLE KEYS */;
INSERT INTO `topup_history` VALUES (1,2,106.6,6.599999999999994,106.6,'2020-12-11 12:53:26');
/*!40000 ALTER TABLE `topup_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `auth` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_uindex` (`id`),
  UNIQUE KEY `user_username_uindex` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'zhourui','123456',0),(2,'cz','123456',0),(3,'zhm','123456',0),(4,'hxw','123456',0),(5,'cph','123456',0),(6,'root','123456',2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `view`
--

DROP TABLE IF EXISTS `view`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `view` (
  `id` int NOT NULL AUTO_INCREMENT,
  `day` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `view`
--

LOCK TABLES `view` WRITE;
/*!40000 ALTER TABLE `view` DISABLE KEYS */;
INSERT INTO `view` VALUES (1,7);
/*!40000 ALTER TABLE `view` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vip_card`
--

DROP TABLE IF EXISTS `vip_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vip_card` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `balance` double DEFAULT '0',
  `join_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `state` int DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vip_card`
--

LOCK TABLES `vip_card` WRITE;
/*!40000 ALTER TABLE `vip_card` DISABLE KEYS */;
INSERT INTO `vip_card` VALUES (1,3,2,106.6,'2020-12-11 12:53:18',1);
/*!40000 ALTER TABLE `vip_card` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-11 21:56:36
